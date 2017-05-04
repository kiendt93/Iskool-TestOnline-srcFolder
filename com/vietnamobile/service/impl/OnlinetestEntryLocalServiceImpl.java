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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
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
import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.base.OnlinetestEntryLocalServiceBaseImpl;

/**
 * The implementation of the onlinetest entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vietnamobile.service.OnlinetestEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author duymb
 * @see com.vietnamobile.service.base.OnlinetestEntryLocalServiceBaseImpl
 * @see com.vietnamobile.service.OnlinetestEntryLocalServiceUtil
 */
public class OnlinetestEntryLocalServiceImpl
	extends OnlinetestEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vietnamobile.service.OnlinetestEntryLocalServiceUtil} to access the onlinetest entry local service.
	 */
	public OnlinetestEntry createOnlinetestEntry(long userId,
            int numberOfQuestions, ArrayList<String> subject,ArrayList<Integer> percentOfEasyLevel,
            ArrayList<Integer> percentOfNormalLevel, ArrayList<Integer> percentOfHighLevel, ArrayList<Integer> percentOfVeryHighLevel,
            Date testStartTime,Date testEndTime, int duration, String title, int point, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long onlinetestEntryId = counterLocalService.increment();
		OnlinetestEntry onlineTestEntry = onlinetestEntryPersistence.create(onlinetestEntryId);
		onlineTestEntry.setQuestionId(createQuestionListsNew(numberOfQuestions, subject,percentOfEasyLevel, percentOfNormalLevel, percentOfHighLevel, percentOfVeryHighLevel));
		
		onlineTestEntry.setTestStartTime(testStartTime);
		onlineTestEntry.setTestEndTime(testEndTime);
		onlineTestEntry.setDuration(duration);
		onlineTestEntry.setTitle(title);
		// Get real number of questions
		numberOfQuestions = onlineTestEntry.getQuestionId().split(",").length;
		
		onlineTestEntry.setNumberOfQuestions(numberOfQuestions);
		User user = UserLocalServiceUtil.getUser(userId);
		onlineTestEntry.setUserId(userId);
		onlineTestEntry.setUserName(user.getFullName());
		onlineTestEntry.setCreateDate(serviceContext.getCreateDate( new Date()));
		onlineTestEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		
		onlinetestEntryPersistence.update(onlineTestEntry);
		return onlineTestEntry;
	}
	
	public OnlinetestEntry createOnlinetestEntry(long userId,
            int numberOfQuestions, ArrayList<String> subject,ArrayList<Integer> percentOfHighLevel,
            ArrayList<Integer> percentOfNormalLevel, ArrayList<Integer> percentOfEasyLevel, ArrayList<Integer> specialNumberOfQuestion, 
            Date testStartTime,Date testEndTime, int duration, String title, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long onlinetestEntryId = counterLocalService.increment();
		OnlinetestEntry onlineTestEntry = onlinetestEntryPersistence.create(onlinetestEntryId);
		onlineTestEntry.setQuestionId(createQuestionLists(numberOfQuestions, subject, percentOfHighLevel, percentOfNormalLevel, percentOfEasyLevel, specialNumberOfQuestion));
		onlineTestEntry.setTestStartTime(testStartTime);
		onlineTestEntry.setTestEndTime(testEndTime);
		onlineTestEntry.setDuration(duration);
		onlineTestEntry.setTitle(title);
		// Get real number of questions
		numberOfQuestions = onlineTestEntry.getQuestionId().split(",").length;
		
		onlineTestEntry.setNumberOfQuestions(numberOfQuestions);
		User user = UserLocalServiceUtil.getUser(userId);
		onlineTestEntry.setUserId(userId);
		onlineTestEntry.setUserName(user.getFullName());
		onlineTestEntry.setCreateDate(serviceContext.getCreateDate( new Date()));
		onlineTestEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		
		onlinetestEntryPersistence.update(onlineTestEntry);
		return onlineTestEntry;
	}
	public OnlinetestEntry createOnlinetestEntry(long userId,
            ArrayList<String> subject,ArrayList<Integer> specialNumberOfQuestion, 
            Date testStartTime,Date testEndTime, int duration, String title, int point, ServiceContext serviceContext) throws SystemException, PortalException
	{
		long onlinetestEntryId = counterLocalService.increment();
		OnlinetestEntry onlineTestEntry = onlinetestEntryPersistence.create(onlinetestEntryId);
		onlineTestEntry.setQuestionId(createQuestionLists(subject,specialNumberOfQuestion));
		onlineTestEntry.setTestStartTime(testStartTime);
		onlineTestEntry.setTestEndTime(testEndTime);
		onlineTestEntry.setDuration(duration);
		onlineTestEntry.setTitle(title);
		onlineTestEntry.setPoint(point);
		// Get real number of questions
		int numberOfQuestions = onlineTestEntry.getQuestionId().split(",").length;
		
		onlineTestEntry.setNumberOfQuestions(numberOfQuestions);
		User user = UserLocalServiceUtil.getUser(userId);
		onlineTestEntry.setUserId(userId);
		onlineTestEntry.setUserName(user.getFullName());
		onlineTestEntry.setCreateDate(serviceContext.getCreateDate( new Date()));
		onlineTestEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		
		onlinetestEntryPersistence.update(onlineTestEntry);
		return onlineTestEntry;
	}	
	public OnlinetestEntry updateOnlinetestEntry(long userId,long onlinetestEntryId, String questionIds,int numberOfQuestions,
												 String title, ServiceContext serviceContext) throws SystemException, PortalException
	{
		OnlinetestEntry updateOnlinetestEntry = onlinetestEntryPersistence.findByPrimaryKey(onlinetestEntryId);
		
		updateOnlinetestEntry.setQuestionId(questionIds);
		updateOnlinetestEntry.setNumberOfQuestions(numberOfQuestions);
		updateOnlinetestEntry.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		updateOnlinetestEntry.setTitle(title);
		
		User user = UserLocalServiceUtil.getUser(userId);
		updateOnlinetestEntry.setUserId(userId);
		updateOnlinetestEntry.setUserName(user.getFullName());
		onlinetestEntryPersistence.update(updateOnlinetestEntry);
		return updateOnlinetestEntry;
	}
	
	public OnlinetestEntry softDeleteOnlineTestEntry(long userId, long onlineTestEntryId, ServiceContext serviceContext) throws SystemException, PortalException
	{
		OnlinetestEntry onlineTestEntry = onlinetestEntryPersistence.findByPrimaryKey(onlineTestEntryId);
		onlineTestEntry.setStatus(1);
		onlinetestEntryPersistence.update(onlineTestEntry);
		return onlineTestEntry;
	}
	
//	public List<OnlinetestEntry> getAllOnlinetests() throws SystemException
//	{
//		List<OnlinetestEntry> finalResult = new ArrayList<OnlinetestEntry>();
//		List<OnlinetestEntry> temp = onlinetestEntryPersistence.findAll();
//		if (temp.size() > 0)
//		{
//			for (OnlinetestEntry entry : temp)
//			{
//				finalResult.add(entry);
//			}
//		}
//		return finalResult;
//	}
	
	public List<OnlinetestEntry> getAllOnlinetests() throws SystemException
	{
		List<OnlinetestEntry> finalResult = new ArrayList<OnlinetestEntry>();
		//List<OnlinetestEntry> temp = onlinetestEntryPersistence.findAll();
		
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OnlinetestEntry.class,
                getClassLoader());
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(new Integer ("0")));
		
		List<OnlinetestEntry> temp = onlinetestEntryPersistence.findWithDynamicQuery(dynamicQuery);
		
		if (temp.size() > 0)
		{
			for (OnlinetestEntry entry : temp)
			{
				finalResult.add(entry);
			}
		}
		return finalResult;
	}

	public String createQuestionListsNew(int numberOfQuestion, 
            ArrayList<String> subject, ArrayList<Integer> percentOfEasyLevel ,
            ArrayList<Integer> percentOfNormalLevel,  ArrayList<Integer> percentOfHighLevel,  ArrayList<Integer> percentOfVeryHighLevel
            ) throws SystemException
    {
		
		int loopNumber = subject.size();
		//System.out.println(loopNumber);
        String questionLists = "";
        for (int i =0; i < loopNumber; i ++)
        {
            // Get all question of subject and specialSubject
            List<QuestionEntry> listOfQuestionsEasy = QuestionEntryLocalServiceUtil.getQuestionEntryByLevel(0, subject.get(i),1,"Exam");   
            List<QuestionEntry> listOfQuestionsNormal = QuestionEntryLocalServiceUtil.getQuestionEntryByLevel(0, subject.get(i),2,"Exam");
            List<QuestionEntry> listOfQuestionsHigh = QuestionEntryLocalServiceUtil.getQuestionEntryByLevel(0, subject.get(i),3,"Exam");
            List<QuestionEntry> listOfQuestionsVeryHigh = QuestionEntryLocalServiceUtil.getQuestionEntryByLevel(0, subject.get(i),4,"Exam");
            
            String questionsEasy = getRandomFromList(listOfQuestionsEasy, percentOfEasyLevel.get(i));
            if (Validator.isNotNull(questionsEasy))
            {
                questionLists = questionLists + questionsEasy + ",";
            }
            String questionsNormal = getRandomFromList(listOfQuestionsNormal, percentOfNormalLevel.get(i));
            if (Validator.isNotNull(questionsNormal))
            {
                questionLists = questionLists + questionsNormal + ",";
            }
            String questionsHigh = getRandomFromList(listOfQuestionsHigh, percentOfHighLevel.get(i));
            if (Validator.isNotNull(questionsHigh))
            {
                questionLists = questionLists + questionsHigh + ",";
            }
            String questionsVeryHigh = getRandomFromList(listOfQuestionsVeryHigh, percentOfVeryHighLevel.get(i));
            if (Validator.isNotNull(questionsHigh))
            {
                questionLists = questionLists + questionsVeryHigh + ",";
            }
        }
        return questionLists;
    }
	public String createQuestionLists(ArrayList<String> subject,
			ArrayList<Integer> specialNumberOfQuestion) throws SystemException 
	{
        int loopNumber = subject.size();
        String questionLists = "";
        for (int i =0; i < loopNumber; i ++)
        {
            // Get all question of subject and specialSubject
            List<QuestionEntry> listOfQuestions = QuestionEntryLocalServiceUtil.searchQuestioEntry(0, subject.get(i), null,0, true);    
            String questions = getRandomFromList(listOfQuestions, specialNumberOfQuestion.get(i));
            
            if (Validator.isNotNull(questions))
            {
                questionLists = questionLists + questions + ",";
            }
        }
        if(Validator.isNotNull(questionLists))
        {
            questionLists = questionLists.substring(0, questionLists.length()-1);
        }
        return questionLists;
    }	
	public String createQuestionLists(int numberOfQuestion, 
            ArrayList<String> subject, ArrayList<Integer> percentOfHighLevel,
            ArrayList<Integer> percentOfNormalLevel,  ArrayList<Integer> percentOfEasyLevel,
            ArrayList<Integer> specialNumberOfQuestion ) throws SystemException
    {

        int loopNumber = subject.size();
        String questionLists = "";
        for (int i =0; i < loopNumber; i ++)
        {
            long numberOfHighQuestion = Math.round(specialNumberOfQuestion.get(i) * (percentOfHighLevel.get(i) * 0.01));
            long numberOfNormalQuestion = Math.round(specialNumberOfQuestion.get(i) * (percentOfNormalLevel.get(i) * 0.01));
            long numberOfEasyQuestion = specialNumberOfQuestion.get(i) - numberOfHighQuestion - numberOfNormalQuestion;
            // Get all question of subject and specialSubject
            List<QuestionEntry> listOfHighQuestion = QuestionEntryLocalServiceUtil.searchQuestioEntry(0, subject.get(i), null,3, true);
            List<QuestionEntry> listOfNormalQuestion = QuestionEntryLocalServiceUtil.searchQuestioEntry(0, subject.get(i), null,2, true);
            List<QuestionEntry> listOfEasyQuestion = QuestionEntryLocalServiceUtil.searchQuestioEntry(0, subject.get(i), null,1, true);
            
            String easyQuestion = getRandomFromList(listOfEasyQuestion, numberOfEasyQuestion);
            String normalQuestion = getRandomFromList(listOfNormalQuestion, numberOfNormalQuestion);
            String highQuestion = getRandomFromList(listOfHighQuestion, numberOfHighQuestion);
            
            if (Validator.isNotNull(easyQuestion))
            {
                questionLists = questionLists + easyQuestion + ",";
            }
            if (Validator.isNotNull(normalQuestion))
            {
                questionLists = questionLists + normalQuestion + ",";
            }
            if (Validator.isNotNull(highQuestion))
            {
                questionLists = questionLists + highQuestion + ",";
            }
        }
        if(Validator.isNotNull(questionLists))
        {
            questionLists = questionLists.substring(0, questionLists.length()-1);
        }
        return questionLists;
    }
    private String getRandomFromList(List<QuestionEntry> listOfQuestion, long number)
    {
        String result = "";
        List<QuestionEntry> copyLists = new ArrayList<QuestionEntry>();
        for(QuestionEntry entry : listOfQuestion)
        {
            copyLists.add(entry);
        }
        Random randomGenerator = new Random();
        for (int i = 0; i < number; i++)
        {
            if (copyLists.size() <= 0)
            {
                break;
            }
            int randomIndex = randomGenerator.nextInt(copyLists.size());
            result = result + copyLists.get(randomIndex).getQuestionEntryId() + ",";
            copyLists.remove(randomIndex);
        }
        if (Validator.isNotNull(result))
        {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }
	public List<OnlinetestEntry> searchOnlinetestEntry(String title,String userName, Date fromDate, Date toDate, boolean andSearch) throws SystemException
	{
		List<OnlinetestEntry> result  = new ArrayList<OnlinetestEntry>();
		result = onlinetestEntryPersistence.findWithDynamicQuery(buildDynamicQuery(title, userName,fromDate, toDate,OnlinetestEntry.class, andSearch));
		return result;
	}
	
	public List<Object[]> getNumberQuestionOfSubject(){
		List<Object[]> results = new ArrayList<Object[]>();
		
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(QuestionEntry.class, getClassLoader());
		

	    ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

	    projectionList.add(ProjectionFactoryUtil.groupProperty("subject"));
	    projectionList.add(ProjectionFactoryUtil.groupProperty("levelQuestion"));
	    projectionList.add(ProjectionFactoryUtil.rowCount(), "_count");
	    dynamicQuery.add(PropertyFactoryUtil.forName("typeExam").like("Exam"));
	    dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(new Integer ("0")));

	    dynamicQuery.setProjection(projectionList);

	    try {
			results = QuestionEntryLocalServiceUtil.dynamicQuery(dynamicQuery);
//			for (Object[] r : results) {
//
//				 System.out.println(r[0]+" "+r[1]+" "+r[2]);
//				}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
    private DynamicQuery buildDynamicQuery(String title, String userName,
           Date fromDate, Date toDate, Class<?> clazz, boolean andSearch)
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

        if (Validator.isNotNull(userName))
        {
            Property property = PropertyFactoryUtil.forName("userName");
            String value = (new StringBuilder("%")).append(userName)
                    .append("%").toString();               
            junction.add(property.like(value));
            
        }
        if (Validator.isNotNull(title))
        {
            Property property = PropertyFactoryUtil.forName("title");
            String value = (new StringBuilder("%")).append(title)
                    .append("%").toString();               
            junction.add(property.like(value));                   	
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