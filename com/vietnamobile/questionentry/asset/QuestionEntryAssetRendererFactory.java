package com.vietnamobile.questionentry.asset;

import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.permission.QuestionEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

public class QuestionEntryAssetRendererFactory extends BaseAssetRendererFactory{

	public static final String CLASS_NAME = QuestionEntry.class.getName();
	public static final String TYPE = "questionEntry";
	
	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		QuestionEntry questionEntry = QuestionEntryLocalServiceUtil.getQuestionEntry(classPK);
		return new QuestionEntryAssetRenderer(questionEntry);
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}
	
	@Override
    public boolean hasPermission(
                    PermissionChecker permissionChecker, long classPK, String actionId)
            throws Exception {

            return QuestionEntryPermission.contains(permissionChecker, classPK, actionId);
    }

    @Override
    public boolean isLinkable() {
            return _LINKABLE;
    }

    private static final boolean _LINKABLE = true;

}
