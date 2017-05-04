package com.vietnamobile.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.service.SubjectEntryLocalServiceUtil;


public class SubjectEntryPermission 
{
    public static void check(PermissionChecker permissionChecker,
            long subjectEntryId, String actionId) throws PortalException,
            SystemException {

        if (!contains(permissionChecker, subjectEntryId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static boolean contains(PermissionChecker permissionChecker,
            long subjectEntryId, String actionId) throws PortalException,
            SystemException {

        SubjectEntry subjectEntry = SubjectEntryLocalServiceUtil.getSubjectEntry(subjectEntryId);

        return permissionChecker
                .hasPermission(subjectEntry.getGroupId(),
                		SubjectEntry.class.getName(), subjectEntry.getSubjectEntryId(),
                        actionId);

    }
}
