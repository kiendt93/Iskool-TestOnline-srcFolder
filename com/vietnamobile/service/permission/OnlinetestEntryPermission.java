package com.vietnamobile.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;


public class OnlinetestEntryPermission {
    public static void check(PermissionChecker permissionChecker,
            long guestbookId, String actionId) throws PortalException,
            SystemException {

        if (!contains(permissionChecker, guestbookId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static boolean contains(PermissionChecker permissionChecker,
            long onlinetestEntryId, String actionId) throws PortalException,
            SystemException {

        OnlinetestEntry onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlinetestEntryId);

        return permissionChecker
                .hasPermission(onlinetestEntry.getGroupId(),
                		OnlinetestEntry.class.getName(), onlinetestEntry.getOnlinetestEntryId(),
                        actionId);

    }
}
