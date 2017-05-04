package com.vietnamobile.tools;

import java.util.Comparator;

import com.vietnamobile.model.VnmOnlintestReportEntry;


public class VnmComperatorByWrongCount implements Comparator<VnmOnlintestReportEntry> {
	@Override
	public int compare(VnmOnlintestReportEntry item1, VnmOnlintestReportEntry item2) {
		if (item1.getWrongTime() > item2.getWrongTime()) {
			return -1;
		}
		else if (item1.getWrongTime() == item2.getWrongTime()){
			return 0;
		}
		else
			return 1;
	}
}
