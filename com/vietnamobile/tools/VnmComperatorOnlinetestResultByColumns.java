package com.vietnamobile.tools;

import java.util.Comparator;

import com.vietnamobile.model.OnlinetestResultEntry;


public class VnmComperatorOnlinetestResultByColumns implements Comparator<OnlinetestResultEntry> 
{
	private String orderByCol = "";
	private final String USERNAME = "UserName";
	private final String CORRECTRATE = "CorrectRate";
	private final String WRONGRATE = "WrongRate";
	private final String TIME = "ExecutedTime";

	
	public VnmComperatorOnlinetestResultByColumns(String columns)
	{
		this.orderByCol = columns; 
	}
	@Override
	public int compare(OnlinetestResultEntry item1, OnlinetestResultEntry item2) 
	{
		int res = 0;
		if(orderByCol.equals(USERNAME))
		{
			res = String.CASE_INSENSITIVE_ORDER.compare(item1.getUserName(), item2.getUserName());
			if( res == 0)
			{
				res = item1.getUserName().compareTo(item2.getUserName());
			}
		}
		if(orderByCol.equals(CORRECTRATE))
		{
			if (item1.getNumberOfWrongAnswer() > item2.getNumberOfWrongAnswer())
			{
				res = -1;
			}
			else if (item1.getNumberOfWrongAnswer() ==  item2.getNumberOfWrongAnswer())
			{
				res = 0;
			}
			else
			{
				res = 1;
			}
		}			
		if(orderByCol.equals(WRONGRATE))
		{
			if (item1.getNumberOfWrongAnswer() > item2.getNumberOfWrongAnswer())
			{
				res = 1;
			}
			else if (item1.getNumberOfWrongAnswer() == item2.getNumberOfWrongAnswer())
			{
				res = 0;
			}
			else
			{
				res =-1;
			}
		}
		if(orderByCol.equals(TIME))
		{
			if (item1.getTimeRemaining() > item2.getTimeRemaining())
			{
				res = -1;
			}
			else if (item1.getTimeRemaining() == item2.getTimeRemaining())
			{
				res = 0;
			}
			else
			{
				res =-1;
			}
		}			
		return res;
	}
}
