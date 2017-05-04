package com.vietnamobile.portlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.jasper.tagplugins.jstl.core.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.exception.NestableException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;
import com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.SubjectEntryLocalServiceUtil;
import com.vietnamobile.tools.ReadExcelFile;


import java.util.Map.Entry;

/**
 * Portlet implementation class QuestionStore
 */
public class QuestionStore extends MVCPortlet {
 
	public void addQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		String subject = ParamUtil.getString(actionRequest, "assetCategoryIds");
		String questionContent = ParamUtil.getString(actionRequest, "questionContent");
		String solutionA = ParamUtil.getString(actionRequest, "solutionA");
		String solutionB = ParamUtil.getString(actionRequest, "solutionB");
		String solutionC = ParamUtil.getString(actionRequest, "solutionC");
		String solutionD = ParamUtil.getString(actionRequest, "solutionD");
		int level = ParamUtil.getInteger(actionRequest, "level");
		String typeExam = ParamUtil.getString(actionRequest, "typeExam");
		String answer = ParamUtil.getString(actionRequest, "answer");
		double score = ParamUtil.getDouble(actionRequest, "score");
		long userId = Long.valueOf(actionRequest.getRemoteUser());
		String title = ParamUtil.getString(actionRequest,"title");
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                QuestionEntry.class.getName(), actionRequest);
        try
        {
        	QuestionEntryLocalServiceUtil.createQuestionEntry(userId, title,subject, questionContent, solutionA, solutionB, solutionC, solutionD, level, typeExam, answer, score, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-create-question");
            actionResponse.setRenderParameter("mvcPath",
                    "/html/questionstore/createQuestion.jsp");
        }
        finally
        {
            actionResponse.setRenderParameter("mvcPath","/html/questionstore/view.jsp");
            actionResponse.setRenderParameter("questionContent", questionContent);
            actionResponse.setRenderParameter("level", String.valueOf(level));
            actionResponse.setRenderParameter("typeExam", typeExam);
            actionResponse.setRenderParameter("answer", answer);
            actionResponse.setRenderParameter("score", String.valueOf(score));
        }
	}

	public void updateQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		String parentCategoryID = ParamUtil.getString(actionRequest, "parentCategoryID");
		AssetCategory parentCategory = AssetCategoryLocalServiceUtil.getCategory(Integer.parseInt(parentCategoryID));
		String vocabulary = "assetCategoryIds_"+String.valueOf(parentCategory.getVocabularyId());
		String subject = ParamUtil.getString(actionRequest, vocabulary);
		
		String questionContent = ParamUtil.getString(actionRequest, "questionContent");
		String solutionA = ParamUtil.getString(actionRequest, "solutionA");
		String solutionB = ParamUtil.getString(actionRequest, "solutionB");
		String solutionC = ParamUtil.getString(actionRequest, "solutionC");
		String solutionD = ParamUtil.getString(actionRequest, "solutionD");
		int level = ParamUtil.getInteger(actionRequest, "level");
		String typeExam = ParamUtil.getString(actionRequest, "typeExam");
		String answer = ParamUtil.getString(actionRequest, "answer");
		double score = ParamUtil.getDouble(actionRequest, "score");
		long questionEntryId = ParamUtil.getLong(actionRequest, "questionEntryId");
		long userId = Long.valueOf(actionRequest.getRemoteUser());
		String title = ParamUtil.getString(actionRequest,"title");
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                QuestionEntry.class.getName(), actionRequest);
        try
        {
        	
        	QuestionEntryLocalServiceUtil.updateQuestionEntry(questionEntryId, userId, title,subject, questionContent, solutionA, solutionB, solutionC, solutionD, level, typeExam, answer, score, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-update-question");
        	actionResponse.setRenderParameter("mvcPath","/html/questionstore/updateQuesntion.jsp");
        }
        finally
        {
            actionResponse.setRenderParameter("mvcPath","/html/questionstore/view.jsp");
            actionResponse.setRenderParameter("questionContent", questionContent);
            actionResponse.setRenderParameter("level", String.valueOf(level));
            actionResponse.setRenderParameter("answer", answer);
            actionResponse.setRenderParameter("score", String.valueOf(score));       	
        }
        
	}
	public void deleteQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		//long questionEntryId = ParamUtil.getLong(actionRequest, "questionEntryId");
		//QuestionEntryLocalServiceUtil.deleteQuestionEntry(questionEntryId);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
        long questionEntryId = ParamUtil.getLong(actionRequest, "questionEntryId");
		
		try
        {
			QuestionEntryLocalServiceUtil.softDeleteQuestionEntry(userId, questionEntryId, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-delete-question");
        }
        finally
        {
        	actionResponse.setRenderParameter("mvcPath","/html/questionstore/view.jsp");
        }
	}
	public void deleteAllQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
		String[] rowIds  = ParamUtil.getParameterValues(actionRequest, "selectedRows");
		for (int i =0; i < rowIds.length; i ++)
		{
			try 
			{
				//QuestionEntryLocalServiceUtil.deleteQuestionEntry(Long.valueOf(rowIds[i]));
				QuestionEntryLocalServiceUtil.softDeleteQuestionEntry(userId, Long.valueOf(rowIds[i]), serviceContext);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PortalException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
	}
	public void addSubject(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		long userId = Long.valueOf(actionRequest.getRemoteUser());
        String rowIndexes = actionRequest.getParameter("rowIndexes");
        String[] indexesOfRow = rowIndexes.split(",");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
		try
		{
			for (int i =0; i < indexesOfRow.length; i++)
			{
				SubjectEntryLocalServiceUtil.createSubjectEntry(userId, actionRequest.getParameter("course" + indexesOfRow[i]),
																actionRequest.getParameter("subject" + indexesOfRow[i]),
																actionRequest.getParameter("description" + indexesOfRow[i]), 
																actionRequest.getParameter("examType" + indexesOfRow[i]),
																serviceContext);
			}
		}
		catch (Exception ex)
		{
			SessionErrors.add(actionRequest, "error-when-add-subject");
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/editSubject.jsp");
		}
		finally
		{
            actionResponse.setRenderParameter("mvcPath","/html/questionstore/editSubject.jsp"); 			
		}
	}
	public void exportToExcel(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		String[] rowIDs = ParamUtil.getParameterValues(actionRequest,"selectedRows");
		List<QuestionEntry> listOfItems = new ArrayList<QuestionEntry>();

		for(int i = 0; i < rowIDs.length; i ++)
		{
			try
			{
				listOfItems.add(QuestionEntryLocalServiceUtil.getQuestionEntry(Long.valueOf(rowIDs[i])));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		if (Validator.isNotNull(listOfItems))
		{
			//HSSFWorkbook wb = new HSSFWorkbook();
			XSSFWorkbook wb = new XSSFWorkbook();
			String sheetName = "Question";//name of sheet
			XSSFSheet sheet = wb.createSheet(sheetName);		
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMinimumFractionDigits(3);
		    try 
		    {
		    	String[] headers = new String[] {"STT"
		    			, "Subject"
		    			, "Title"
		    			, "QuestionContent"
		    			, "Answer"
		    			, "Selection1" 
		    			, "Selection2" 
		    			, "Selection3" 
		    			, "Selection4"};

				//header
				XSSFRow row = sheet.createRow(0);
		    	for (int rn = 0; rn <headers.length; rn++) 		    	
		    	{
		    	   
		    		row.createCell(rn).setCellValue(headers[rn]);
		    	}
		    	
				//iterating r number of rows
				int r =1;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				for (QuestionEntry item : listOfItems) 
				{
					row = sheet.createRow(r);
					
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(String.valueOf(listOfItems.indexOf(item)));
					
					cell = row.createCell(1);
					cell.setCellValue(item.getSubject());
					
					cell = row.createCell(2);
					cell.setCellValue(item.getTitle());
					
					cell = row.createCell(3);
					cell.setCellValue(item.getQuestionContent());
					
					cell = row.createCell(4);
					cell.setCellValue(item.getAnswer());

					cell = row.createCell(5);
					cell.setCellValue(item.getSelection1());
					cell = row.createCell(6);
					cell.setCellValue(item.getSelection2());
					cell = row.createCell(7);
					cell.setCellValue(item.getSelection3());
					cell = row.createCell(8);
					cell.setCellValue(item.getSelection4());
					r++;
				}				

				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
				HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
				try 
				{
					response.addHeader("content-disposition","attachment; filename=fname.xlsx");
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				ServletResponseUtil.sendFile(request, response, "ooooo.xlsx",outArray, ContentTypes.APPLICATION_VND_MS_EXCEL);
				outByteStream.close();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		    finally
		    {
		    	try 
		    	{
					wb.close();
				} 
		    	catch (IOException e) 
		    	{	
		    		e.printStackTrace();
				}
		    }
		}
	}
	
	public void updateSubject(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
        String course = ParamUtil.getString(actionRequest, "course");        		
        String subject = ParamUtil.getString(actionRequest, "subject");
        String examType = ParamUtil.getString(actionRequest, "examType");
        String description = ParamUtil.getString(actionRequest, "description");
        long subjectEntryId = ParamUtil.getLong(actionRequest, "subjectEntryId");
        try
        {
        	SubjectEntryLocalServiceUtil.updateSubjectEntry(userId, subjectEntryId, course, subject, description, examType, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-update-subject");
        }
        finally
        {
        	actionResponse.setRenderParameter("mvcPath","/html/questionstore/editSubject.jsp");
        }
	}
	public void deleteSubject(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
		long subjectEntryId = ParamUtil.getLong(actionRequest, "subjectEntryId");
		
		try
        {
			SubjectEntryLocalServiceUtil.softDeleteSubjectEntry(userId, subjectEntryId, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-delete-subject");
        }
        finally
        {
        	actionResponse.setRenderParameter("mvcPath","/html/questionstore/editSubject.jsp");
        }
		
		
		
		//SubjectEntryLocalServiceUtil.deleteSubjectEntry(subjectEntryId);
		
	}
	public void uploadFileQuestion(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                QuestionEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
        try
        {
    		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
    		Map<String, FileItem[]> files= uploadPortletRequest.getMultipartParameterMap();
    		for (Entry<String, FileItem[]> file2 : files.entrySet()) 
    		{
    			FileItem item[] = file2.getValue();
    			for (FileItem fileItem : item) 
    			{
    				try
    				{
    					InputStream is = fileItem.getInputStream();
    					File file  = fileItem.getStoreLocation();
    					ReadExcelFile readFile = new ReadExcelFile();
    					readFile.processExcelFile(userId, file, serviceContext);
    				}
    				catch (Exception ex)
    				{
    					throw ex;
    				}
    			}
    		}        	
        }
        catch(Exception ex)
        {
			SessionErrors.add(actionRequest, "error-when-upload-file");
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/createQuestion.jsp");        	
        }
		finally
		{
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/view.jsp");        				
		}

	}
	public void createTestOnline(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{	
		long userId = Long.valueOf(actionRequest.getRemoteUser());
        String rowIndexes = actionRequest.getParameter("rowIndexes");
        String[] indexesOfRow = rowIndexes.split(",");	
        int numOfSubjects = indexesOfRow.length;
        ArrayList<String> subject =  new ArrayList<String>();
        ArrayList<Integer> numberQuestionEachSubjectEasy = new ArrayList<Integer>();
        ArrayList<Integer> numberQuestionEachSubjectNormal = new ArrayList<Integer>();
        ArrayList<Integer> numberQuestionEachSubjectHigh = new ArrayList<Integer>();
        ArrayList<Integer> numberQuestionEachSubjectVeryHigh = new ArrayList<Integer>();
                
        for (int i = 0; i < numOfSubjects; i ++)
        {
        	subject.add(actionRequest.getParameter("subject" + indexesOfRow[i]));

    		if(actionRequest.getParameter("numberQuestionEachSubjectEasy" + indexesOfRow[i]) == null){
    			numberQuestionEachSubjectEasy.add(0);
        	}else{
        		numberQuestionEachSubjectEasy.add(Integer.valueOf(actionRequest.getParameter("numberQuestionEachSubjectEasy" + indexesOfRow[i])));
        	}
    		
    		if(actionRequest.getParameter("numberQuestionEachSubjectNormal" + indexesOfRow[i]) == null){
    			numberQuestionEachSubjectNormal.add(0);
        	}else{
        		numberQuestionEachSubjectNormal.add(Integer.valueOf(actionRequest.getParameter("numberQuestionEachSubjectNormal" + indexesOfRow[i])));
        	}
    		
    		if(actionRequest.getParameter("numberQuestionEachSubjectHigh" + indexesOfRow[i]) == null){
        		numberQuestionEachSubjectHigh.add(0);
        	}else{
        		numberQuestionEachSubjectHigh.add(Integer.valueOf(actionRequest.getParameter("numberQuestionEachSubjectHigh" + indexesOfRow[i])));
        	}
    		
    		if(actionRequest.getParameter("numberQuestionEachSubjectVeryHigh" + indexesOfRow[i]) == null){
        		numberQuestionEachSubjectVeryHigh.add(0);
        	}else{
        		numberQuestionEachSubjectVeryHigh.add(Integer.valueOf(actionRequest.getParameter("numberQuestionEachSubjectVeryHigh" + indexesOfRow[i])));
        	}
        }

        String title = ParamUtil.getString(actionRequest, "title");
        int point = ParamUtil.getInteger(actionRequest, "pointOfExam");
        int duration = ParamUtil.getInteger(actionRequest, "duration");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate  = ParamUtil.getDate(actionRequest, "startDatepicker", dateFormat);
        Date endDate = ParamUtil.getDate(actionRequest, "endDatepicker", dateFormat);
        startDate = getDateTime(startDate, ParamUtil.getString(actionRequest,"startTimepicker"));
        endDate = getDateTime(endDate, ParamUtil.getString(actionRequest,"endTimepicker"));
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                OnlinetestEntry.class.getName(), actionRequest);        
        try
        {
        	
        	OnlinetestEntryLocalServiceUtil.createOnlinetestEntry(userId, 10 ,subject,numberQuestionEachSubjectEasy,numberQuestionEachSubjectNormal,numberQuestionEachSubjectHigh,numberQuestionEachSubjectVeryHigh,
        			startDate, endDate, duration,title,point, serviceContext);
        }
        catch (Exception ex)
        {
        	SessionErrors.add(actionRequest, "error-when-create-online-test");
        }
        finally
        {
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/createTestOnline.jsp");
        }
	}
	public void deleteOnlineTest(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
                SubjectEntry.class.getName(), actionRequest);
        long userId = Long.valueOf(actionRequest.getRemoteUser());
		long onlinetestEntryId = ParamUtil.getLong(actionRequest, "onlinetestEntryId");
		try
		{
			//OnlinetestEntryLocalServiceUtil.deleteOnlinetestEntry(onlinetestEntryId);
			OnlinetestEntryLocalServiceUtil.softDeleteOnlineTestEntry(userId, onlinetestEntryId, serviceContext);
		}
		catch(Exception ex)
		{
			SessionErrors.add(actionRequest, "error-when-delete-testonline");
		}
		finally
		{
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/createTestOnline.jsp");

		}
	}
	public void removeOneRow(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		long questionRemoveId = ParamUtil.getLong(actionRequest, "questionRemoveId");
		long onlinetestEntryId = ParamUtil.getLong(actionRequest, "onlinetestEntryId");
		try
		{
			OnlinetestEntry onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlinetestEntryId);
			String original = onlinetestEntry.getQuestionId();
			String modified = "";
			if (original.indexOf(String.valueOf(questionRemoveId)) > 0)
			{
				modified = onlinetestEntry.getQuestionId().replace("," + String.valueOf(questionRemoveId), "");
			}
			else
			{
				modified = onlinetestEntry.getQuestionId().replace(String.valueOf(questionRemoveId) + ",", "");
			}
			onlinetestEntry.setQuestionId(modified);
			onlinetestEntry.setNumberOfQuestions(modified.split(",").length);
			OnlinetestEntryLocalServiceUtil.updateOnlinetestEntry(onlinetestEntry);
		}
		catch (Exception ex)
		{
			SessionErrors.add(actionRequest, "error-when-remove-online-test-question");
		}
		finally
		{
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/editTestOnline.jsp");
			actionResponse.setRenderParameter("onlinetestEntryId", String.valueOf(onlinetestEntryId));			
		}
	}
	public void choosenQuestion(ActionRequest actionRequest, ActionResponse actionResponse)
	{

		long onlinetestEntryId = ParamUtil.getLong(actionRequest, "onlinetestEntryId");
		String questionReplaceId = ParamUtil.getString(actionRequest, "questionReplaceId");
		String questionSelectedId = ParamUtil.getString(actionRequest,"questionSelectedId");
		String subject = ParamUtil.getString(actionRequest, "subject");
		try
		{
			OnlinetestEntry onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlinetestEntryId);
			String original = onlinetestEntry.getQuestionId();
			String modified = "";
			if(Long.valueOf(questionReplaceId) > 0)
			{
				modified = original.replace(questionReplaceId, questionSelectedId);
			}
			else
			{
				modified = original + "," + questionSelectedId;
			}
			onlinetestEntry.setQuestionId(modified);
			onlinetestEntry.setNumberOfQuestions(modified.split(",").length);
			OnlinetestEntryLocalServiceUtil.updateOnlinetestEntry(onlinetestEntry);
		}
		catch (Exception ex)
		{
			SessionErrors.add(actionRequest, "error-when-update-online-test-question");
		}
		finally
		{
			actionResponse.setRenderParameter("mvcPath","/html/questionstore/editTestOnline.jsp");
			actionResponse.setRenderParameter("onlinetestEntryId", String.valueOf(onlinetestEntryId));
			actionResponse.setRenderParameter("subject", subject);
			actionResponse.setRenderParameter("questionReplaceId",questionReplaceId);
		}		
	}
	
	public void getQuestionList(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		String subject = ParamUtil.getString(actionRequest, "subject");
		String onlintestEntryId = ParamUtil.getString(actionRequest,"onlinetestEntryId");
		String actionType= ParamUtil.getString(actionRequest,"actionType");
		actionResponse.setRenderParameter("mvcPath","/html/questionstore/questionSelection.jsp");
		actionResponse.setRenderParameter("onlinetestEntryId", onlintestEntryId);
		actionResponse.setRenderParameter("subject", subject);	
		actionResponse.setRenderParameter("actionType", actionType);	
	}
	public void updateTestOnlineInfor(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		String title = ParamUtil.getString(actionRequest,"title");
		int point = ParamUtil.getInteger(actionRequest, "pointOfExam");
		int duration = ParamUtil.getInteger(actionRequest, "duration");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate  = ParamUtil.getDate(actionRequest, "startDatepicker", dateFormat);
        Date endDate = ParamUtil.getDate(actionRequest, "endDatepicker", dateFormat);
        startDate = getDateTime(startDate, ParamUtil.getString(actionRequest,"startTimepicker"));
        endDate = getDateTime(endDate, ParamUtil.getString(actionRequest,"endTimepicker"));
 		long onlineTestEntryId = ParamUtil.getLong(actionRequest, "onlineTestEntryId");
 		long userId = Long.valueOf(actionRequest.getRemoteUser());
 		try
 		{
 			OnlinetestEntry onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlineTestEntryId);
 			onlinetestEntry.setTitle(title);
 			onlinetestEntry.setPoint(point);
 			onlinetestEntry.setDuration(duration);
 			onlinetestEntry.setTestStartTime(startDate);
 			onlinetestEntry.setTestEndTime(endDate);
 			onlinetestEntry.setUserId(userId);
 			OnlinetestEntryLocalServiceUtil.updateOnlinetestEntry(onlinetestEntry);
 		}
 		catch(Exception ex)
 		{
 			SessionErrors.add(actionRequest, "error-when-update-testonline-infor");
 		}
 		finally
 		{
 			actionResponse.setRenderParameter("mvcPath","/html/questionstore/createTestOnline.jsp");
 		}
	}
	private Date getDateTime(Date date, String time)
	{
		Calendar calendar = Calendar.getInstance();
		String hour = time.split(":")[0];
		String minutes = time.split(":")[1];
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
		calendar.set(Calendar.MINUTE, Integer.valueOf(minutes));
		return calendar.getTime();
	}	
}
