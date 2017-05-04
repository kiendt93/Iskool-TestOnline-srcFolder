package com.vietnamobile.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.permission.QuestionEntryPermission;
import com.vietnamobile.service.persistence.QuestionEntryActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;

public class QuestionEntryIndexer extends BaseIndexer{

	public static final String[] CLASS_NAMES = { QuestionEntry.class.getName() };
	
	public static final String PORTLET_ID = "question-store";
	
	public QuestionEntryIndexer(){
		setPermissionAware(true);
	}
	
	@Override
	public String[] getClassNames() {
		// TODO Auto-generated method stub
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}
	
	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
            String questionEntryClassName, long questionEntryClassPK, String actionId)
            throws Exception {

		return QuestionEntryPermission.contains(permissionChecker, questionEntryClassPK,
                    ActionKeys.VIEW);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntry questionEntry = (QuestionEntry)obj;
		
		deleteDocument(questionEntry.getCompanyId(), questionEntry.getQuestionEntryId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntry questionEntry = (QuestionEntry)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, questionEntry);

		document.addDate(Field.MODIFIED_DATE, questionEntry.getModifiedDate());
        document.addText(Field.TITLE, questionEntry.getTitle());
        document.addText(Field.DESCRIPTION, questionEntry.getQuestionContent());
        document.addKeyword(Field.GROUP_ID, getSiteGroupId(questionEntry.getGroupId()));
        document.addKeyword(Field.SCOPE_GROUP_ID, questionEntry.getGroupId());

        return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
            String snippet, PortletURL portletURL) throws Exception {

	    Summary summary = createSummary(document);
	
	    summary.setMaxContentLength(200);
	
	    return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntry questionEntry = (QuestionEntry)obj;
		
		Document document = getDocument(questionEntry);

        SearchEngineUtil.updateDocument(
                getSearchEngineId(), questionEntry.getCompanyId(), document, true);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);

        reindexEntries(companyId);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntry questionEntry = QuestionEntryLocalServiceUtil.getQuestionEntry(classPK);

        doReindex(questionEntry);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

	protected void reindexEntries(long companyId) throws PortalException,
    SystemException {

		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new QuestionEntryActionableDynamicQuery() {
		
		    @Override
		    protected void addCriteria(DynamicQuery dynamicQuery) {
		    }
		
		    @Override
		    protected void performAction(Object object) throws PortalException {
		            QuestionEntry questionEntry = (QuestionEntry) object;
		
		            Document document = getDocument(questionEntry);
		
		            documents.add(document);
		    }
		
		};
		
		actionableDynamicQuery.setCompanyId(companyId);
		
		actionableDynamicQuery.performActions();
		
		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents, true);
	}
}
