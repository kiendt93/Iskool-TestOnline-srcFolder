package com.vietnamobile.examonline.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

public class ConfigurationActionImpl extends DefaultConfigurationAction
{
	@Override
	public void processAction(
		    PortletConfig portletConfig, ActionRequest actionRequest,
		    ActionResponse actionResponse) throws Exception 
	{  
		    super.processAction(portletConfig, actionRequest, actionResponse);
		    PortletPreferences prefs = actionRequest.getPreferences();


		    // Add any preference processing here.
		    String examType1 = ParamUtil.getString(actionRequest, "preferences--examType--");
		    prefs.setValue("examType", examType1);
		    prefs.store();
		    
	}
}
