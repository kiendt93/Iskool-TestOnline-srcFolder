package com.vietnamobile.tools;

import java.util.Comparator;

import com.vietnamobile.model.QuestionEntry;


public class VnmComperatorQuestionByColumns implements Comparator<QuestionEntry> 
{
	private String orderByCol = "";
	private final String TITLE = "Title";
	private final String CREATEDATE = "CreateDate";
	private final String MODIFYDATE = "ModifiedDate";
	private final String SUBJECT = "Subject";	
	
	public VnmComperatorQuestionByColumns(String columns)
	{
		this.orderByCol = columns;
	}
	@Override
	public int compare(QuestionEntry item1, QuestionEntry item2) 
	{
		int res = 0;
		if(orderByCol.equals(TITLE))
		{
			res = String.CASE_INSENSITIVE_ORDER.compare(item1.getTitle(), item2.getTitle());
			if( res == 0)
			{
				res = item1.getTitle().compareTo(item2.getTitle());
			}
		}
		if(orderByCol.equals(SUBJECT))
		{
			res = String.CASE_INSENSITIVE_ORDER.compare(item1.getSubject(), item2.getSubject());
			if( res == 0)
			{
				res = item1.getSubject().compareTo(item2.getSubject());
			}
		}		
		if(orderByCol.equals(CREATEDATE))
		{
			res = item1.getCreateDate().compareTo(item2.getCreateDate());
		}
		if(orderByCol.equals(MODIFYDATE))
		{
			res = item1.getModifiedDate().compareTo(item2.getModifiedDate());
		}			
		return res;
	}
}
