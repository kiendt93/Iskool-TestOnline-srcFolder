package com.vietnamobile.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil;


public class OnlinetestResultEntryPermission 
{
    public static void check(PermissionChecker permissionChecker,
            long guestbookId, String actionId) throws PortalException,
            SystemException {

        if (!contains(permissionChecker, guestbookId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static boolean contains(PermissionChecker permissionChecker,
            long onlinetestResultEntryId, String actionId) throws PortalException,
            SystemException {

        OnlinetestResultEntry onlinetestResult = OnlinetestResultEntryLocalServiceUtil.getOnlinetestResultEntry(onlinetestResultEntryId);

        return permissionChecker
                .hasPermission(onlinetestResult.getGroupId(),
                		OnlinetestResultEntry.class.getName(), onlinetestResult.getOnlinetestResultEntryId(),
                        actionId);

    }
}
