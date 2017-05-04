package com.vietnamobile.portlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.model.VnmArticleReportEntry;
import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;
import com.vietnamobile.service.OnlinetestResultEntryLocalServiceUtil;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;
import com.vietnamobile.service.VnmArticleReportEntryLocalServiceUtil;

/**
 * Portlet implementation class VnmReport
 */
public class VnmReport extends MVCPortlet
{
	public void ExportToExcel (ActionRequest actionRequest, ActionResponse actionResponse)
	{
		String listOfActionIds = ParamUtil.getString(actionRequest, "listOfActionIds");
		if (Validator.isNotNull(listOfActionIds))
		{
			List<VnmArticleReportEntry> listOfItems = new ArrayList<VnmArticleReportEntry>();
			HSSFWorkbook wb = new HSSFWorkbook();
			String sheetName = "Report About Article";//name of sheet
			HSSFSheet sheet = wb.createSheet(sheetName);		
			
			try
			{
				String[] vnmReportIds = listOfActionIds.split(",");
				for (int i = 0;i < vnmReportIds.length; i ++)
				{
					listOfItems.add(VnmArticleReportEntryLocalServiceUtil.getVnmArticleReportEntry(Long.valueOf(vnmReportIds[i])));
				}
			}
			catch (Exception ex)
			{
				SessionErrors.add(actionRequest, "error-when-export-report");
			}
		    try 
		    {
		    	String[] headers = new String[] {"Id"
		    			, "Action"
		    			, "Updater"
		    			, "ModifiedDate"
		    			, "Title"
		    			, "Status" };

				//header
				HSSFRow row = sheet.createRow(0);
		    	for (int rn = 0; rn <headers.length; rn++) 		    	
		    	{
		    	   
		    		row.createCell(rn).setCellValue(headers[rn]);
		    	}
		    	
				//iterating r number of rows
				int r =1;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				for (VnmArticleReportEntry item : listOfItems) 
				{
					row = sheet.createRow(r);
					
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(String.valueOf(listOfItems.indexOf(item) + 1));
					
					cell = row.createCell(1);
					cell.setCellValue(item.getAction());
					
					cell = row.createCell(2);
					cell.setCellValue(item.getUserName());
					
					cell = row.createCell(3);
					cell.setCellValue(df.format(item.getModifiedDate()));
					
					cell = row.createCell(4);
					cell.setCellValue(item.getTitle());

					cell = row.createCell(5);
					cell.setCellValue(String.valueOf(item.getStatus()));
					r++;
				}				

				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
				HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
				try 
				{
					response.addHeader("content-disposition","attachment; filename=fname.xls");
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				ServletResponseUtil.sendFile(request, response, "ooooo.xls",outArray, ContentTypes.APPLICATION_VND_MS_EXCEL);
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
		    	{					e.printStackTrace();
				}
		    }
		}
	}
	public void ExportExamResultToExcel(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException
	{
		String listOfResultIds  = ParamUtil.getString(actionRequest,"listOfResultIds");
		long onlinetestEntryId  = ParamUtil.getLong(actionRequest,"onlinetestEntryId");
		if (Validator.isNotNull(listOfResultIds))
		{
			List<OnlinetestResultEntry> listOfItems = new ArrayList<OnlinetestResultEntry>();
			HSSFWorkbook wb = new HSSFWorkbook();
			OnlinetestEntry onlinetestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(onlinetestEntryId);
			String sheetName = "Report About Exam" + onlinetestEntry.getTitle();//name of sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			DecimalFormat numberFormat = new DecimalFormat(".##"); 

			numberFormat.setMinimumFractionDigits(3);
			try
			{
				String[] vnmReportIds =   listOfResultIds.split(",");
				for (int i = 0;i < vnmReportIds.length; i ++)
				{
					listOfItems.add(OnlinetestResultEntryLocalServiceUtil.getOnlinetestResultEntry(Long.valueOf(vnmReportIds[i])));
				}
			}
			catch (Exception ex)
			{
				SessionErrors.add(actionRequest, "error-when-export-report");
			}
		    try 
		    {
		    	String[] headers = new String[] {"STT"
		    			, "UserName"
		    			, "ScreenName"
		    			, "ExamTitle"
		    			, "ExecutedDate"
		    			, "CorrectRate"
		    			, "WrongRate"
		    			, "Score"
		    			, "ExecutedTime"
		    			, "WrongQuestionDetail"};

				//header
				HSSFRow row = sheet.createRow(0);
		    	for (int rn = 0; rn <headers.length; rn++) 		    	
		    	{
		    	   
		    		row.createCell(rn).setCellValue(headers[rn]);
		    	}
		    	// Cell Style
		    	HSSFCellStyle cellStyle = wb.createCellStyle();
		    	cellStyle.setDataFormat(wb.createDataFormat().getFormat("0%"));
				//iterating r number of rows
				int r =1;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				for (OnlinetestResultEntry item : listOfItems) 
				{
					OnlinetestEntry onlineTestEntry = OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(item.getOnlineTestEntryId());
					row = sheet.createRow(r);
					
					HSSFCell cell = row.createCell(0);
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cell.setCellValue(String.valueOf(listOfItems.indexOf(item) + 1));
					
					cell = row.createCell(1);
					cell.setCellValue(item.getUserName());
					//Get User infor
					cell = row.createCell(2);
					User user = UserLocalServiceUtil.getUser(item.getUserId());
					cell.setCellValue(user.getScreenName());
					cell = row.createCell(3);
					cell.setCellValue(onlineTestEntry.getTitle());
					
					cell = row.createCell(4);
					cell.setCellValue(df.format(item.getCreateDate()));
					cell = row.createCell(5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((double)(onlinetestEntry.getNumberOfQuestions() - item.getNumberOfWrongAnswer())/onlinetestEntry.getNumberOfQuestions());
					
					cell = row.createCell(6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((double) item.getNumberOfWrongAnswer()/onlinetestEntry.getNumberOfQuestions());

					cell = row.createCell(7);
					cell.setCellValue(String.valueOf(item.getScore()));

					cell = row.createCell(8);
					cell.setCellValue(String.valueOf(onlinetestEntry.getDuration()*60 - item.getTimeRemaining()));
					cell = row.createCell(9);
					String listOfWrongQuestions = getQuestionTitleFrom(onlineTestEntry,item);
					cell.setCellValue(listOfWrongQuestions);
					r++;
				}				

				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
				HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
				try 
				{
					response.addHeader("content-disposition","attachment; filename=fname.xls");
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				ServletResponseUtil.sendFile(request, response, "ooooo.xls",outArray, ContentTypes.APPLICATION_VND_MS_EXCEL);
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
	private String getQuestionTitleFrom(OnlinetestEntry onlinetestEntry, OnlinetestResultEntry onlinetestResult)
	{
		String[] questionList = onlinetestResult.getQuestionIds().split(",");
		String[] answeredList = onlinetestResult.getAnswerSelected().split(",");
		String result = "";
		for (int i = 0; i <questionList.length; i ++ )
		{
			try
			{
				QuestionEntry question = QuestionEntryLocalServiceUtil.getQuestionEntry(Long.valueOf(questionList[i]));
				if (!question.getAnswer().equalsIgnoreCase(answeredList[i]))
				{
					result = result + question.getTitle() + ",";
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}
}
