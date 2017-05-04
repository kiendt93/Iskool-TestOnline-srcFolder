/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vietnamobile.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.vietnamobile.model.VnmArticleReportEntry;
import com.vietnamobile.service.base.VnmArticleReportEntryLocalServiceBaseImpl;

/**
 * The implementation of the vnm article report entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.VnmArticleReportEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.VnmArticleReportEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.VnmArticleReportEntryLocalServiceUtil
 */
public class VnmArticleReportEntryLocalServiceImpl
	extends VnmArticleReportEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.VnmArticleReportEntryLocalServiceUtil} to access the vnm article report entry local service.
	 */
	public void saveLogVnmArticleModify(long userId,String articleId, String title, double version, String action,int status) throws SystemException, PortalException
	{
		long vnmArticleReportEntryId = counterLocalService.increment();
		User user = userLocalService.getUserById(userId);
		VnmArticleReportEntry vnmArticleReportEntry  = vnmArticleReportEntryPersistence.create(vnmArticleReportEntryId);
		vnmArticleReportEntry.setUserId(userId);
		vnmArticleReportEntry.setUserName(user.getFullName());
		vnmArticleReportEntry.setGroupId(user.getGroupId());
		vnmArticleReportEntry.setCreateDate(new Date());
		vnmArticleReportEntry.setModifiedDate(new Date());
		vnmArticleReportEntry.setCompanyId(user.getCompanyId());
		vnmArticleReportEntry.setArticleId(articleId);
		vnmArticleReportEntry.setTitle(title);
		vnmArticleReportEntry.setAction(action);
		vnmArticleReportEntry.setVersion(version);
		vnmArticleReportEntry.setStatus(status);
		
		vnmArticleReportEntryPersistence.update(vnmArticleReportEntry);
	}

	public List<VnmArticleReportEntry> searchLogArticle(String title, String userName, String action,int status,
            Date fromDate,Date toDate,boolean andSearch) throws SystemException
	{
		List<VnmArticleReportEntry> result = new ArrayList<VnmArticleReportEntry>();
		result = vnmArticleReportEntryPersistence.findWithDynamicQuery(
				buildDynamicQuery(title, userName, action, status,fromDate, toDate, VnmArticleReportEntry.class, andSearch));
		return result;
	}
    private DynamicQuery buildDynamicQuery(String title, String userName, String action,int status,
            Date fromDate,Date toDate, Class<?> clazz, boolean andSearch)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(clazz,
                getClassLoader());
        Junction junction = null;
        if (andSearch)
        {
            junction = RestrictionsFactoryUtil.conjunction();
        } else
        {
            junction = RestrictionsFactoryUtil.disjunction();
        }

        if (Validator.isNotNull(title))
        {
            Property property = PropertyFactoryUtil.forName("title");
            String value = (new StringBuilder("%")).append(title)
                    .append("%").toString();               
            junction.add(property.like(value));
            
        }
        if (Validator.isNotNull(userName))
        {
            Property property = PropertyFactoryUtil.forName("userName");
            String value = (new StringBuilder("%")).append(userName)
                    .append("%").toString();           
            junction.add(property.like(value));        	
        }
        if (Validator.isNotNull(action))
        {
            Property property = PropertyFactoryUtil.forName("action");
            String value = (new StringBuilder("%")).append(action)
                    .append("%").toString();           
            junction.add(property.like(value));        	
        }        
        if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate))
        {
            Criterion dateRangeCriterion = RestrictionsFactoryUtil.between("modifiedDate",fromDate,toDate);
            dynamicQuery.add(dateRangeCriterion);           
        }
        if (status >= 0)
        {
            Property property = PropertyFactoryUtil.forName("status");
            junction.add(property.eq(status));
        }
        return dynamicQuery.add(junction);
    } 	
}