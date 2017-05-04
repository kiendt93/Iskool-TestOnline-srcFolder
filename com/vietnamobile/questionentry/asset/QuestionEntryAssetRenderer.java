package com.vietnamobile.questionentry.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.permission.QuestionEntryPermission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

public class QuestionEntryAssetRenderer extends BaseAssetRenderer{

	private QuestionEntry _questionEntry;
	
	public QuestionEntryAssetRenderer(QuestionEntry questionEntry) {
		_questionEntry = questionEntry;
	}
	
	@Override
    public boolean hasEditPermission(PermissionChecker permissionChecker) {

		long questionEntryId = _questionEntry.getQuestionEntryId();

        boolean contains = false;

        try {
                contains = QuestionEntryPermission.contains(permissionChecker,
                		questionEntryId, ActionKeys.UPDATE);
        } catch (PortalException pe) {
        	_log.error(pe.getLocalizedMessage());
        } catch (SystemException se) {
        	_log.error(se.getLocalizedMessage());
        }

        return contains;
    }
	
	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		long questionEntryId = _questionEntry.getQuestionEntryId();

        boolean contains = false;

        try {
                contains = QuestionEntryPermission.contains(permissionChecker,
                		questionEntryId, ActionKeys.VIEW);
        } catch (PortalException pe) {
        	_log.error(pe.getLocalizedMessage());
        } catch (SystemException se) {
        	_log.error(se.getLocalizedMessage());
        }

        return contains;
	}
	
	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return QuestionEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _questionEntry.getQuestionEntryId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _questionEntry.getGroupId();
	}

	@Override
	public String getSummary(Locale arg0) {
		// TODO Auto-generated method stub
		return _questionEntry.getTitle();
	}

	@Override
	public String getTitle(Locale arg0) {
		// TODO Auto-generated method stub
		return _questionEntry.getTitle();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _questionEntry.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _questionEntry.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String render(RenderRequest renderRequest,
            RenderResponse renderResponse, String template) throws Exception {
		// TODO Auto-generated method stub
		if (template.equals(TEMPLATE_FULL_CONTENT)) {
            return "/html/questionstore/" + template + ".jsp";
	    }
	    else {
	            return null;
	    }
	}
	
	private Log _log;
}
