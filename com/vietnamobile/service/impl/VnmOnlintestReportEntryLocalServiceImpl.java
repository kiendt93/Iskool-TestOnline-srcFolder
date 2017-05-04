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

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.model.VnmOnlintestReportEntry;
import com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil;
import com.vietnamobile.service.base.VnmOnlintestReportEntryLocalServiceBaseImpl;

/**
 * The implementation of the vnm onlintest report entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.VnmOnlintestReportEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.VnmOnlintestReportEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.VnmOnlintestReportEntryLocalServiceUtil
 */
public class VnmOnlintestReportEntryLocalServiceImpl
	extends VnmOnlintestReportEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.VnmOnlintestReportEntryLocalServiceUtil} to access the vnm onlintest report entry local service.
	 */
	public void updateOnlineTestReportEntry(long onlineTestEntryId, long questionEntryId,int correctAnswer,int wrongAnswer)
	{
		try
		{
			List<VnmOnlintestReportEntry> reportList = getReportFillterBy(onlineTestEntryId,questionEntryId,true);
			if (reportList.size() > 0)
			{
				//Update
				for(VnmOnlintestReportEntry item : reportList)
				{
					item.setCorrectTime(item.getCorrectTime() + correctAnswer);
					item.setWrongTime(item.getWrongTime() + wrongAnswer);
					vnmOnlintestReportEntryPersistence.update(item);
				}
			}
			else
			{
				// Create new 
				long reportEntryId = counterLocalService.increment();
				VnmOnlintestReportEntry reportEntry  = vnmOnlintestReportEntryPersistence.create(reportEntryId);
				reportEntry.setOnlinetestEntryId(onlineTestEntryId);
				reportEntry.setQuestionEntryId(questionEntryId);
				reportEntry.setCorrectTime(correctAnswer);
				reportEntry.setWrongTime(wrongAnswer);
				vnmOnlintestReportEntryPersistence.update(reportEntry);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public List<VnmOnlintestReportEntry> getReportFillterBy(long onlinetestEntryId, long questionEntryId, boolean andSearch) throws SystemException
	{
		List<VnmOnlintestReportEntry> reportList = new ArrayList<VnmOnlintestReportEntry>();
		reportList = vnmOnlintestReportEntryPersistence.findWithDynamicQuery(
				buildDynamicQuery(onlinetestEntryId, questionEntryId, VnmOnlintestReportEntry.class, andSearch));
		return reportList;
	}
	public List<VnmOnlintestReportEntry> getReportFillterBy(long onlinetestEntryId, long questionEntryId,String company, boolean andSearch) throws SystemException
	{
		List<VnmOnlintestReportEntry> reportList = new ArrayList<VnmOnlintestReportEntry>();
		reportList = vnmOnlintestReportEntryPersistence.findWithDynamicQuery(
				buildDynamicQuery(onlinetestEntryId, questionEntryId, VnmOnlintestReportEntry.class, andSearch));
		// Refill deail for company.
		List<OnlinetestResultEntry> resultList = OnlinetestResultEntryLocalServiceUtil.getUserExamed(0, onlinetestEntryId, company, -1, null, null, "Exam",true);
		for(VnmOnlintestReportEntry item : reportList)
		{

		}
		
		return reportList;
	}	
    private DynamicQuery buildDynamicQuery(long onlinetestEntryId, long questionEntryId,Class<?> clazz, boolean andSearch)
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
         if (onlinetestEntryId > 0)
         {
             Property property = PropertyFactoryUtil.forName("onlinetestEntryId");
             junction.add(property.eq(Long.valueOf(onlinetestEntryId)));        	
         }
         if (questionEntryId > 0)
         {
             Property property = PropertyFactoryUtil.forName("questionEntryId");
             junction.add(property.eq(Long.valueOf(questionEntryId)));        	
         }         	
         return dynamicQuery.add(junction);
     } 	
}