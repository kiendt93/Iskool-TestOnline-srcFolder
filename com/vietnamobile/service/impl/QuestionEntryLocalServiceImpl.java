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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.service.base.QuestionEntryLocalServiceBaseImpl;

/**
 * The implementation of the question entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.QuestionEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.QuestionEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.QuestionEntryLocalServiceUtil
 */
public class QuestionEntryLocalServiceImpl
	extends QuestionEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.QuestionEntryLocalServiceUtil} to access the question entry local service.
	 */
	public QuestionEntry createQuestionEntry(long userId, String title,String subject, String questionContent, String solutionA, String solutionB, String solutionC, String solutionD, int level, String typeExam, String answer, 
											 double score, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long questionEntryId = counterLocalService.increment();
		QuestionEntry questionEntry = questionEntryPersistence.create(questionEntryId);
		User user = UserLocalServiceUtil.getUserById(userId);
		long groupId = serviceContext.getScopeGroupId();
		
		questionEntry.setUserId(userId);
		questionEntry.setUserName(user.getFullName());
		questionEntry.setGroupId(groupId);
		questionEntry.setCompanyId(user.getCompanyId());
		
		questionEntry.setCreateDate(serviceContext.getCreateDate(new Date()));
		questionEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		
		questionEntry.setTitle(title);
		questionEntry.setSubject(subject);
		questionEntry.setLevelQuestion(level);
		questionEntry.setTypeExam(typeExam);
		questionEntry.setAnswer(answer);
		questionEntry.setScore(score);
		questionEntry.setQuestionContent(questionContent);
		questionEntry.setA(solutionA);
		questionEntry.setB(solutionB);
		questionEntry.setC(solutionC);
		questionEntry.setD(solutionD);
		
		questionEntryPersistence.update(questionEntry);
		
		resourceLocalService.addResources(user.getCompanyId(), 
				groupId, userId, QuestionEntry.class.getName(), 
				questionEntryId, false, true, true);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				QuestionEntry.class);
		
		indexer.reindex(questionEntry);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
                groupId, questionEntry.getCreateDate(),
                questionEntry.getModifiedDate(), QuestionEntry.class.getName(),
                questionEntryId, serviceContext.getUuid(), 0,
                serviceContext.getAssetCategoryIds(),
                serviceContext.getAssetTagNames(), true, null, null, null,
                ContentTypes.TEXT_HTML, questionEntry.getTitle(), null, null, null,
                null, 0, 0, null, false);
		
		return questionEntry;
	}
	public QuestionEntry updateQuestionEntry (long questionEntryId, long userId, String title, String subject, String questionContent, String solutionA, String solutionB, String solutionC, String solutionD, int level, String typeExam, String answer, double score, ServiceContext serviceContext) throws SystemException, PortalException
	{
		QuestionEntry updateQuestionEntry = questionEntryPersistence.findByPrimaryKey(questionEntryId);
		updateQuestionEntry.setTitle(title);
		updateQuestionEntry.setSubject(subject);
		updateQuestionEntry.setQuestionContent(questionContent);
		updateQuestionEntry.setA(solutionA);
		updateQuestionEntry.setB(solutionB);
		updateQuestionEntry.setC(solutionC);
		updateQuestionEntry.setD(solutionD);
		updateQuestionEntry.setLevelQuestion(level);
		updateQuestionEntry.setTypeExam(typeExam);
		updateQuestionEntry.setAnswer(answer);
		updateQuestionEntry.setScore(score);
		updateQuestionEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		
		User user = UserLocalServiceUtil.getUser(userId);
		updateQuestionEntry.setUserId(userId);
		updateQuestionEntry.setUserName(user.getFullName());
		
		questionEntryPersistence.update(updateQuestionEntry);
		
		resourceLocalService.updateResources(user.getCompanyId(),
				serviceContext.getScopeGroupId(), title, questionEntryId,
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				QuestionEntry.class);
		
		indexer.reindex(updateQuestionEntry);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				serviceContext.getScopeGroupId(), updateQuestionEntry.getCreateDate(),
                updateQuestionEntry.getModifiedDate(), QuestionEntry.class.getName(),
                questionEntryId, serviceContext.getUuid(), 0,
                serviceContext.getAssetCategoryIds(),
                serviceContext.getAssetTagNames(), true, null, null, null,
                ContentTypes.TEXT_HTML, updateQuestionEntry.getTitle(), null, null, null,
                null, 0, 0, null, false);
		
		return updateQuestionEntry;
	}
	public QuestionEntry softDeleteQuestionEntry(long userId, long questionEntryId, ServiceContext serviceContext) throws SystemException, PortalException
	{
		QuestionEntry questionEntry = questionEntryPersistence.findByPrimaryKey(questionEntryId);
		questionEntry.setStatus(1);
		questionEntryPersistence.update(questionEntry);
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                QuestionEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                questionEntryId);

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				QuestionEntry.class.getName(), questionEntryId);
		
		assetEntryLocalService.deleteEntry(assetEntry);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				QuestionEntry.class);
		
		indexer.delete(questionEntry);
		
		return questionEntry;
	}
    public List<QuestionEntry> searchQuestioEntry(long userId,String subject,String content,double level, boolean andSearch) throws SystemException
    {
        List<QuestionEntry> result = new ArrayList<QuestionEntry>();
        
        result = questionEntryPersistence.findWithDynamicQuery(buildDynamicQuery(userId, "", subject ,content,level, QuestionEntry.class, andSearch));
        
        return result;
    } 
    public List<QuestionEntry> searchQuestioEntry(long userId,String title,String subject, String content,double level, boolean andSearch) throws SystemException
    {
        List<QuestionEntry> result = new ArrayList<QuestionEntry>();
        
        result = questionEntryPersistence.findWithDynamicQuery(buildDynamicQuery(userId, title,subject ,content,level, QuestionEntry.class, andSearch));
        
        return result;
    } 
    private DynamicQuery buildDynamicQuery(long userId, String title, String subject,
            String content,double level, Class<?> clazz, boolean andSearch)
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
        if (Validator.isNotNull(title))
        {
            Property property = PropertyFactoryUtil.forName("title");
            String value = (new StringBuilder("%")).append(title)
                    .append("%").toString();               
            junction.add(property.like(value));       	
        }
        if (Validator.isNotNull(subject))
        {
            Property property = PropertyFactoryUtil.forName("subject");
            String value = (new StringBuilder("%")).append(subject)
                    .append("%").toString();               
            junction.add(property.like(value));
            
        }
        if (Validator.isNotNull(content))
        {
            Property property = PropertyFactoryUtil.forName("questionContent");
            String value = (new StringBuilder("%")).append(content)
                    .append("%").toString();           
            junction.add(property.like(value));        	
        }
        if (level > 0)
        {
            Property property = PropertyFactoryUtil.forName("levelQuestion");
            junction.add(property.eq(level));       	
        }
        Property property = PropertyFactoryUtil.forName("status");
        junction.add(property.eq(Integer.valueOf(0)));
        
        return dynamicQuery.add(junction);
    } 
    
//    Kien dev
    public List<QuestionEntry> getQuestionEntryByLevel(long userId,String subject,int level, String typeExam) throws SystemException
    {
    	
        List<QuestionEntry> result = new ArrayList<QuestionEntry>();
        
        result = questionEntryPersistence.findWithDynamicQuery(buildDynamicQuery(userId,subject ,level,typeExam ,QuestionEntry.class));
        
        return result;
    } 
    private DynamicQuery buildDynamicQuery(long userId, String subject,
            int level,String typeExam ,Class<?> clazz)
    {
    	
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(clazz,
                getClassLoader());
        Junction junction = null;
        junction = RestrictionsFactoryUtil.conjunction();
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
        
        if (level > 0)
        {
            Property property = PropertyFactoryUtil.forName("levelQuestion");
            junction.add(property.eq(level));       	
        }
        
        if (Validator.isNotNull(typeExam))
        {
            Property property = PropertyFactoryUtil.forName("typeExam");
            String value = (new StringBuilder("%")).append(typeExam)
                    .append("%").toString();           
            junction.add(property.like(value));        	
        }
        
        Property property = PropertyFactoryUtil.forName("status");
        junction.add(property.eq(Integer.valueOf(0)));
        
        return dynamicQuery.add(junction);
    } 
}