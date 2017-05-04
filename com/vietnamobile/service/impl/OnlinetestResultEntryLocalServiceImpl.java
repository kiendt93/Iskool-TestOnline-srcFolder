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
import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.VnmOnlintestReportEntryLocalServiceUtil;
import com.vietnamobile.service.VnmOnlintestReportEntryServiceUtil;
import com.vietnamobile.service.base.OnlinetestResultEntryLocalServiceBaseImpl;

/**
 * The implementation of the onlinetest result entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.OnlinetestResultEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.OnlinetestResultEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil
 */
public class OnlinetestResultEntryLocalServiceImpl
	extends OnlinetestResultEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil} to access the onlinetest result entry local service.
	 */
	public OnlinetestResultEntry storeOnlinetestResults(long userId,long onlineTestEntryId,
			ArrayList<String> answerSelected, int timeRemaining, String examType, String questionIds, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long onlinetestResultEntryId = counterLocalService.increment();
		long groupId = serviceContext.getScopeGroupId();
		OnlinetestResultEntry onlinetestResultEntry = onlinetestResultEntryPersistence.create(onlinetestResultEntryId);
		User user = UserLocalServiceUtil.getUser(userId);
		onlinetestResultEntry.setOnlineTestEntryId(onlineTestEntryId);
		onlinetestResultEntry.setAnswerSelected(convertToString(answerSelected));
		onlinetestResultEntry.setScore(calculatePoint(onlineTestEntryId, answerSelected,questionIds));
		onlinetestResultEntry.setNumberOfWrongAnswer(countWrongAnswer(onlineTestEntryId, answerSelected,questionIds));
		onlinetestResultEntry.setTimeRemaining(timeRemaining);
		onlinetestResultEntry.setUserId(userId);
		onlinetestResultEntry.setUserName(user.getFullName());
		onlinetestResultEntry.setGroupId(groupId);
		onlinetestResultEntry.setCreateDate(serviceContext.getCreateDate(new Date()));
		onlinetestResultEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		onlinetestResultEntry.setExamType(examType);
		onlinetestResultEntry.setQuestionIds(questionIds);
		onlinetestResultEntryPersistence.update(onlinetestResultEntry);
		
		return onlinetestResultEntry;
	}
	private double calculatePoint(long onlineTestEntryId, ArrayList<String> answerSelected, String questionIds) throws NumberFormatException, PortalException, SystemException
	{
		
		OnlinetestEntry onlinetestEntry = null;
		String[] arrQuestionIds = {};
		int pointOfExam = 0;
		int basePoint = 0;
		if (onlineTestEntryId > 0)
		{
			onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlineTestEntryId);
			arrQuestionIds = questionIds.split(",");
			pointOfExam = onlinetestEntry.getPoint();
			basePoint = pointOfExam / onlinetestEntry.getNumberOfQuestions();
		}
		else
		{
			arrQuestionIds = questionIds.split(",");
		}
		double point = 0;
		for (int i=0; i < arrQuestionIds.length; i++)
		{
			int correctAnswer = 0;
			int wrongAnswer = 0;
			QuestionEntry questionEntry = QuestionEntryLocalServiceUtil.getQuestionEntry(Long.valueOf(arrQuestionIds[i]));
			if (questionEntry.getAnswer().equalsIgnoreCase(answerSelected.get(i)))
			{
				point = point + basePoint;
				correctAnswer = 1;
			}
			else
			{
				wrongAnswer = 1;
			}
			VnmOnlintestReportEntryLocalServiceUtil.updateOnlineTestReportEntry(onlineTestEntryId, questionEntry.getQuestionEntryId(), correctAnswer, wrongAnswer);
		}
		return point;
	}
	private int countWrongAnswer(long onlineTestEntryId, ArrayList<String> answerSelected,String questionIds) throws NumberFormatException, PortalException, SystemException
	{
		String[] arrQuestionIds = {};
		arrQuestionIds = questionIds.split(",");
		int wrongAnswered = 0;
		for (int i=0; i < arrQuestionIds.length; i++)
		{
			QuestionEntry questionEntry = QuestionEntryLocalServiceUtil.getQuestionEntry(Long.valueOf(arrQuestionIds[i]));
			if (questionEntry.getAnswer().equalsIgnoreCase(answerSelected.get(i)))
			{
			}
			else
			{
				wrongAnswered++;
			}
		}
		return wrongAnswered;
	}	
	private String convertToString(ArrayList<String> answerSelected)
	{
		int size = answerSelected.size();
		String result = "";
		for (int i = 0; i < size; i++)
		{
			if(Validator.isNotNull(answerSelected.get(i)))
			{
				result = result + answerSelected.get(i) + ";";
			}
			else
			{
				result = result + " ;";
			}
		}
		//remove last ;
		result = result.substring(0,result.length()-1);
		return result;
	}
	public List<OnlinetestResultEntry> getUserExamed(long userId, long onlinetestEntryId,String userName,double score, Date fromDate, Date toDate,String examType, boolean andSearch) throws SystemException
	{
		List<OnlinetestResultEntry> result  = new ArrayList<OnlinetestResultEntry>();
		result = onlinetestEntryPersistence.findWithDynamicQuery(buildDynamicQuery(userId,onlinetestEntryId, userName, score, fromDate, toDate,examType,OnlinetestResultEntry.class, andSearch));
		return result;
	}
    private DynamicQuery buildDynamicQuery(long userId,long onlinetestEntryId, String userName,
           double score,Date fromDate, Date toDate, String examType, Class<?> clazz, boolean andSearch)
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
        if (onlinetestEntryId > 0)
        {
            Property property = PropertyFactoryUtil.forName("onlineTestEntryId");
            junction.add(property.eq(Long.valueOf(onlinetestEntryId)));        	
        }
        if (Validator.isNotNull(userName))
        {
            Property property = PropertyFactoryUtil.forName("userName");
            String value = (new StringBuilder("%")).append(userName)
                    .append("%").toString();               
            junction.add(property.like(value));
            
        }
        if (Validator.isNotNull(examType))
        {
            Property property = PropertyFactoryUtil.forName("examType");
            String value = (new StringBuilder("")).append(examType).toString();               
            junction.add(property.eq(value));       	
        }
        if (score > 0)
        {
            Property property = PropertyFactoryUtil.forName("score");
            junction.add(property.eq(score));       	
        }
        if (Validator.isNotNull(fromDate) && Validator.isNull(toDate))
        {
        	dynamicQuery.add(RestrictionsFactoryUtil.ge("createDate", fromDate));
        }
        if (Validator.isNotNull(toDate) && Validator.isNull(fromDate))
        {
        	dynamicQuery.add(RestrictionsFactoryUtil.le("createDate", toDate));
        }
        if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate))
        {
        	dynamicQuery.add(RestrictionsFactoryUtil.between("createDate", fromDate, toDate));
        }
        	
        return dynamicQuery.add(junction);
    } 	
}