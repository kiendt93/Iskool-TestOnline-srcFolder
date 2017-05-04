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
import java.util.Date;
import java.util.List;

import javax.security.auth.Subject;

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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.base.SubjectEntryLocalServiceBaseImpl;

/**
 * The implementation of the subject entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.SubjectEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.SubjectEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.SubjectEntryLocalServiceUtil
 */
public class SubjectEntryLocalServiceImpl
	extends SubjectEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.SubjectEntryLocalServiceUtil} to access the subject entry local service.
	 */
	public SubjectEntry createSubjectEntry(long userId, String course, String subject, String description, String examType, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long subjectEntryId = counterLocalService.increment();
		SubjectEntry subjectEntry = subjectEntryPersistence.create(subjectEntryId);
		User user = UserLocalServiceUtil.getUser(userId);
		subjectEntry.setUserId(userId);
		subjectEntry.setUserName(user.getFullName());
		subjectEntry.setCreateDate(serviceContext.getCreateDate(new Date()));
		subjectEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		subjectEntry.setExamType(examType);
		
		subjectEntry.setCourse(course);
		subjectEntry.setSubject(subject);
		subjectEntry.setDescription(description);
		
		subjectEntryPersistence.update(subjectEntry);
		return subjectEntry;
	}
	public SubjectEntry updateSubjectEntry(long userId, long subjectEntryId, String course, String subject, String description,String examType, ServiceContext serviceContext) throws SystemException, PortalException
	{
		SubjectEntry subjectEntry = subjectEntryPersistence.findByPrimaryKey(subjectEntryId);
		User user = UserLocalServiceUtil.getUser(userId);
		
		subjectEntry.setUserId(userId);
		subjectEntry.setUserName(user.getFullName());
		subjectEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		subjectEntry.setCourse(course);
		subjectEntry.setSubject(subject);
		subjectEntry.setDescription(description);
		subjectEntry.setExamType(examType);
		subjectEntryPersistence.update(subjectEntry);
		return subjectEntry;
	}
	public SubjectEntry softDeleteSubjectEntry(long userId, long subjectEntryId, ServiceContext serviceContext) throws SystemException, PortalException
	{
		SubjectEntry subjectEntry = subjectEntryPersistence.findByPrimaryKey(subjectEntryId);
		subjectEntry.setStatus(1);
		subjectEntryPersistence.update(subjectEntry);
		return subjectEntry;
	}
//	public List<SubjectEntry> getAllSubjects() throws SystemException
//	{
//		List<SubjectEntry> finalList = new ArrayList<SubjectEntry>();
//		List<SubjectEntry> temp = subjectEntryPersistence.findAll();
//		if (temp.size() > 0)
//		{
//			for (SubjectEntry entry :temp )
//			{
//				finalList.add(entry);
//			}
//		}
//		return finalList;
//	}
	
	public List<SubjectEntry> getAllSubjects() throws SystemException
	{
		List<SubjectEntry> finalList = new ArrayList<SubjectEntry>();
		//List<SubjectEntry> temp = subjectEntryPersistence.findAll();
		
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SubjectEntry.class,
                getClassLoader());
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(new Integer ("0")));
        
        List<SubjectEntry> temp = subjectEntryPersistence.findWithDynamicQuery(dynamicQuery);
        
		if (temp.size() > 0)
		{
			for (SubjectEntry entry :temp )
			{
				finalList.add(entry);
			}
		}
		return finalList;
	}
	
	public List<SubjectEntry> findBySubjectKey(long userId, String subject, String examType, String description, boolean andSearch) throws SystemException
	{
		List<SubjectEntry> result = new ArrayList<SubjectEntry>();
		List<SubjectEntry> tempSubjectList = subjectEntryPersistence.findWithDynamicQuery(
				buildDynamicQuery(userId, subject, examType, description, SubjectEntry.class, andSearch));
		for (SubjectEntry item : tempSubjectList)
		{
			List<QuestionEntry> tempQuestionList = QuestionEntryLocalServiceUtil.searchQuestioEntry(0, item.getSubject(), "", 0, true);
			item.setTotalQuestion(tempQuestionList.size());
			result.add(item);
		}
		return result;
	}
    private DynamicQuery buildDynamicQuery(long userId,String subject, String examType, String description,
           Class<?> clazz, boolean andSearch)
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

         if (userId > 0)
         {
             Property property = PropertyFactoryUtil.forName("userId");
             junction.add(property.eq(Long.valueOf(userId)));
         }
         if (Validator.isNotNull(subject))
         {
             Property property = PropertyFactoryUtil.forName("subject");
             String value = (new StringBuilder("%")).append(subject)
                     .append("%").toString();               
             junction.add(property.like(value));
             
         }

         if (Validator.isNotNull(examType))
         {
             Property property = PropertyFactoryUtil.forName("examType");
             String value = (new StringBuilder("")).append(examType).toString();               
             junction.add(property.eq(value));	
         }
         if (Validator.isNotNull(description))
         {
             Property property = PropertyFactoryUtil.forName("description");
             String value = (new StringBuilder("%")).append(description)
                     .append("%").toString();               
             junction.add(property.like(value));
             
         }     
         Property property = PropertyFactoryUtil.forName("status");
         junction.add(property.eq(Integer.valueOf(0)));
         
         return dynamicQuery.add(junction);
     } 		
}