package com.vietnamobile.portlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;
import com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil;

/**
 * Portlet implementation class ExamOnline
 */
public class ExamOnline extends MVCPortlet 
{
	public void finishTestonline(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		
		int numberOfQuestions = ParamUtil.getInteger(actionRequest, "numbOfQuestion");
		String[] questionIdsList = ParamUtil.getString(actionRequest, "questionListSelected").split(",");
		long onlinetestEntryId = ParamUtil.getLong(actionRequest, "onlinetestEntryId");
		long userId = Long.valueOf(actionRequest.getRemoteUser());
		int timeRemaining = convertToSeconds(ParamUtil.getString(actionRequest, "timeRemaining"));
		String examType = ParamUtil.getString(actionRequest, "examType");
		if (hasExamedByUser(userId, onlinetestEntryId))
		{
			SessionErrors.add(actionRequest, "error-you-had-finished-the-exam");
            actionResponse.setRenderParameter("mvcPath","/html/examonline/view.jsp");
            return;
		}
		ArrayList<String> answerSelected = new ArrayList<String>();
		for (int i =0;i< numberOfQuestions; i++)
		{
			String answer = "";
			answer = ParamUtil.getString(actionRequest,questionIdsList[i]);
//			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "A"))
//			{
//				answer  = answer + "A,";
//			}
//			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "B"))
//			{
//				answer  = answer +  "B,";
//			}
//			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "C"))
//			{
//				answer  = answer + "C,";
//			}
//			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "D"))
//			{
//				answer  = answer + "D,";
//			}
			// Remove last comma.
//			if (answer.length() > 1)
//			{
//				answer = answer.substring(0,answer.length()-1);
//			}
			answerSelected.add(answer);
		}
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                OnlinetestResultEntry.class.getName(), actionRequest);
        OnlinetestResultEntry resultEntry = null;
		try
		{
			resultEntry = OnlinetestResultEntryLocalServiceUtil.storeOnlinetestResults(userId, onlinetestEntryId, answerSelected,timeRemaining,examType,ParamUtil.getString(actionRequest, "questionListSelected"),serviceContext);
            actionResponse.setRenderParameter("onlinetestResultEntryId", String.valueOf(resultEntry.getOnlinetestResultEntryId()));
            actionResponse.setRenderParameter("mvcPath","/html/examonline/viewTestResult.jsp");

		}
		catch (Exception ex)
		{
			SessionErrors.add(actionRequest, "error when save online test result");
            actionResponse.setRenderParameter("mvcPath","/html/examonline/view.jsp");
		}
		finally
		{
		}
	}
	public void executePractice(ActionRequest actionRequest, ActionResponse actionResponse)
	{
        String rowIndexes = actionRequest.getParameter("rowIndexes");
        String[] indexesOfRow = rowIndexes.split(",");	
        int numOfSubjects = indexesOfRow.length;
        ArrayList<String> subject =  new ArrayList<String>();
        ArrayList<Integer> numberOfQuestionEachSubject = new ArrayList<Integer>();
        String time = ParamUtil.getString(actionRequest, "timeLimit");
        String examType = ParamUtil.getString(actionRequest, "examType");
        for (int i = 0; i < numOfSubjects; i ++)
        {
        	subject.add(actionRequest.getParameter("subject" + indexesOfRow[i]));
        	numberOfQuestionEachSubject.add(Integer.valueOf(actionRequest.getParameter("numberQuestionEachSubject" + indexesOfRow[i])));
        }
    	String questionIds = "";
        try
        {
			questionIds = OnlinetestEntryLocalServiceUtil.createQuestionLists(subject, numberOfQuestionEachSubject);
			if (Validator.isNull(questionIds))
			{
	        	SessionErrors.add(actionRequest, "dont-have-any-questions");
				actionResponse.setRenderParameter("mvcPath","/html/examonline/view.jsp");
			}
			else
			{
				actionResponse.setRenderParameter("mvcPath","/html/examonline/executePractice.jsp");
			}
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-create-online-test");
			actionResponse.setRenderParameter("mvcPath","/html/examonline/view.jsp");
        }
        finally
        {
			actionResponse.setRenderParameter("questionIds", questionIds);
			actionResponse.setRenderParameter("timeLitmit", time);
			actionResponse.setRenderParameter("examType", examType);	
			
        }
	}
	public void finishPractice(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		int numberOfQuestions = ParamUtil.getInteger(actionRequest, "numbOfQuestion");
		String[] questionIdsList = ParamUtil.getString(actionRequest, "questionListSelected").split(",");
		long userId = Long.valueOf(actionRequest.getRemoteUser());
		int timeRemaining = convertToSeconds(ParamUtil.getString(actionRequest, "timeRemaining"));
		String examType = ParamUtil.getString(actionRequest, "examType");
		String timeLimit = ParamUtil.getString(actionRequest,"timeLimit");
		
		ArrayList<String> answerSelected = new ArrayList<String>();
		for (int i =0;i< numberOfQuestions; i++)
		{
			String answer = "";
			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "A"))
			{
				answer  = answer + "A,";
			}
			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "B"))
			{
				answer  = answer +  "B,";
			}
			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "C"))
			{
				answer  = answer + "C,";
			}
			if (ParamUtil.getBoolean(actionRequest, questionIdsList[i] + "D"))
			{
				answer  = answer + "D,";
			}
			// Remove last comma.
			answer = answer.substring(0,answer.length()-1);
			answerSelected.add(answer);
		}
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                OnlinetestResultEntry.class.getName(), actionRequest);
        OnlinetestResultEntry resultEntry = null;
		try
		{
			resultEntry = OnlinetestResultEntryLocalServiceUtil.storeOnlinetestResults(userId, 0, answerSelected,
					timeRemaining, examType, ParamUtil.getString(actionRequest, "questionListSelected"),
					serviceContext);
            actionResponse.setRenderParameter("onlinetestResultEntryId", String.valueOf(resultEntry.getOnlinetestResultEntryId()));
            actionResponse.setRenderParameter("timeLimit",timeLimit);
            actionResponse.setRenderParameter("mvcPath","/html/examonline/viewTestResult.jsp");

		}
		catch (Exception ex)
		{
			SessionErrors.add(actionRequest, "error when save online test result");
            actionResponse.setRenderParameter("mvcPath","/html/examonline/view.jsp");
		}
		finally
		{
		}		
	}
	private boolean hasExamedByUser(long userId,long onlinetestEntryId) throws SystemException
	{
		List<OnlinetestResultEntry> resultList = OnlinetestResultEntryLocalServiceUtil.getUserExamed(userId, onlinetestEntryId, "", 0, null, null, "Exam", true);
		return resultList.size() > 0;
	}
	private int convertToSeconds(String time)
	{
		String minutes = "0";
		String seconds = "0";
		if(time.contains(":"))
		{
			minutes = time.substring(0,time.indexOf(":"));
			seconds = time.substring(time.indexOf(":") + 1);			
		}
		return Integer.valueOf(minutes) * 60 + Integer.valueOf(seconds);
	}
}
