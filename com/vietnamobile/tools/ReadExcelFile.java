package com.vietnamobile.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.service.QuestionEntryLocalServiceUtil;

public class ReadExcelFile 
{
	private String souceFile = "";
	private static final String SPACE_TAB = "<br>";
	public String getSouceFile() {
		return souceFile;
	}

	public void setSouceFile(String souceFile) {
		this.souceFile = souceFile;
	}

	public ReadExcelFile(String file)
	{
		this.setSouceFile(file);
	}
	public ReadExcelFile()
	{
	}
	public void readXLSFile(long userId, File file, ServiceContext serviceContext)
	{
		FileInputStream fileInputStream = null;
		HSSFWorkbook workbook = null;
		try
		{
			fileInputStream = new FileInputStream(file);
			workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			String title = "";
			String subject = "";
			String questionContent = "";
			String solutionA = "";
			String solutionB = "";
			String solutionC = "";
			String solutionD = "";
			double level = 0;
			String answer = "";
			String answerSelection = "";
			double score = 0;
			Iterator<Row> rowIterator = sheet.iterator();
			// By pass header 
			rowIterator.next();
			
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				subject = (String) getCellValue(row.getCell(1));
				title = (String) getCellValue(row.getCell(2));
				questionContent = (String) getCellValue(row.getCell(3));
				answer = (String) getCellValue(row.getCell(4));
				for (int i =5;i<9;i++)
				{
					if (Validator.isNotNull(getCellValue(row.getCell(i))))
					{
						String temp = (String) getCellValue(row.getCell(i));
						if (!questionContent.contains(temp))
						{
							answerSelection = answerSelection + temp + SPACE_TAB;
						}
					}
				}
				//define solutionA number column in file
				StringBuilder content = new StringBuilder(questionContent);
				content.append("<br>").append(answerSelection);
				answerSelection = "";
				if (!isDuplicate(title, content.toString()))
				{
					//QuestionEntryLocalServiceUtil.createQuestionEntry(userId,title,subject, content.toString(),solutionA,solutionB,solutionC,solutionD, level, answer, score, serviceContext);
				}
			}			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try {
				workbook.close();
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void readXLSXFile(long userId, File file, ServiceContext serviceContext)
	{
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try
		{
			fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			String title = "";
			String subject = "";
			String questionContent = "";
			String solutionA = "";
			String solutionB = "";
			String solutionC = "";
			String solutionD = "";
			double level = 0;
			String answer = "";
			String answerSelection = "";
			double score = 0;
			Iterator<Row> rowIterator = sheet.iterator();
			// By pass header 
			rowIterator.next();
			
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				subject = String.valueOf(getCellValue(row.getCell(1)));
				title =  String.valueOf(getCellValue(row.getCell(2)));
				questionContent =  String.valueOf( getCellValue(row.getCell(3)));
				answer = String.valueOf(getCellValue(row.getCell(4)));
				for (int i =5;i<9;i++)
				{
					if (Validator.isNotNull(getCellValue(row.getCell(i))))
					{
						String temp =  String.valueOf(getCellValue(row.getCell(i)));
						if (!questionContent.contains(temp))
						{
							answerSelection = answerSelection + temp + SPACE_TAB;
						}
					}
				}
				StringBuilder content = new StringBuilder(questionContent);
				content.append("<br>").append(answerSelection);
				answerSelection = "";
				if (!isDuplicate(title, content.toString()))
				{
					//QuestionEntryLocalServiceUtil.createQuestionEntry(userId,title,subject, content.toString(),solutionA,solutionB,solutionC,solutionD, level, answer, score, serviceContext);
				}
			}			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try {
				workbook.close();
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}		
	public void processExcelFile(long userId, File file, ServiceContext serviceContext)
	{
		if (FileUtil.getExtension(file.getName()).equals("xlsx"))
		{
			readXLSXFile(userId, file, serviceContext);
		}
		else if (FileUtil.getExtension(file.getName()).equals("xls"))
		{
			readXLSFile(userId, file, serviceContext);
		}
	}
	private boolean isDuplicate(String title, String content)
	{
		boolean duplicate = false;
		try
		{
			List<QuestionEntry> listTemp =  QuestionEntryLocalServiceUtil.searchQuestioEntry(0, title, content.toString(), 0, false);
			if (listTemp.size() > 0)
			{
				duplicate = true;
			}
		}catch(SystemException ex)
		{
			ex.printStackTrace();
		}
		return duplicate;
	}
	private Object getCellValue(Cell cell) 
	{
	    switch (cell.getCellType()) {
	    
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	        
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return Math.round(cell.getNumericCellValue());
	    }
	    return null;
	}
}
