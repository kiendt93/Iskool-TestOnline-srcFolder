package com.vietnamobile.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;

public class QuestionEntryPermission 
{
    public static void check(PermissionChecker permissionChecker,
            long guestbookId, String actionId) throws PortalException,
            SystemException {

        if (!contains(permissionChecker, guestbookId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static boolean contains(PermissionChecker permissionChecker,
            long questionEntryId, String actionId) throws PortalException,
            SystemException {

        QuestionEntry questionEntry = QuestionEntryLocalServiceUtil.getQuestionEntry(questionEntryId);

        return permissionChecker
                .hasPermission(questionEntry.getGroupId(),
                		QuestionEntry.class.getName(), questionEntry.getQuestionEntryId(),
                        actionId);

    }
}
