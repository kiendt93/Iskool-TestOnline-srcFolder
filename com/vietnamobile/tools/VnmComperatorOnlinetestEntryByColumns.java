package com.vietnamobile.tools;

import java.util.Comparator;

import com.vietnamobile.model.OnlinetestEntry;



public class VnmComperatorOnlinetestEntryByColumns implements Comparator<OnlinetestEntry> 
{
	private String orderByCol = "";
	private final String TITLE = "Title";
	private final String CREATEDATE = "CreateDate";
	private final String MODIFYDATE = "ModifiedDate";
	private final String STARTDATE = "StartExam";	
	private final String ENDDATE = "EndExam";	

	
	public VnmComperatorOnlinetestEntryByColumns(String columns)
	{
		this.orderByCol = columns;
	}
	@Override
	public int compare(OnlinetestEntry item1, OnlinetestEntry item2) 
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
		if(orderByCol.equals(CREATEDATE))
		{
			res = item1.getCreateDate().compareTo(item2.getCreateDate());
		}
		if(orderByCol.equals(MODIFYDATE))
		{
			res = item1.getModifiedDate().compareTo(item2.getModifiedDate());
		}
		if(orderByCol.equals(STARTDATE))
		{
			res = item1.getTestStartTime().compareTo(item2.getTestStartTime());
		}
		if(orderByCol.equals(ENDDATE))
		{
			res = item1.getTestEndTime().compareTo(item2.getTestEndTime());
		}			
		return res;
	}
}
