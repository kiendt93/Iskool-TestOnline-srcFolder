package com.vietnamobile.tools;

import java.util.Comparator;

import com.vietnamobile.model.VnmArticleReportEntry;


public class VnmComperatorArticleByColumns implements Comparator<VnmArticleReportEntry> 
{
	private String orderByCol = "";
	private final String TITLE = "Title";
	private final String CREATEDATE = "CreateDate";
	private final String MODIFYDATE = "ModifiedDate";
	private final String UPDATER = "Updater";

	
	public VnmComperatorArticleByColumns(String columns)
	{
		this.orderByCol = columns; 
	}
	@Override
	public int compare(VnmArticleReportEntry item1, VnmArticleReportEntry item2) 
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
		if(orderByCol.equals(UPDATER))
		{
			res = String.CASE_INSENSITIVE_ORDER.compare(item1.getUserName(), item2.getUserName());
			if( res == 0)
			{
				res = item1.getUserName().compareTo(item2.getUserName());
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
