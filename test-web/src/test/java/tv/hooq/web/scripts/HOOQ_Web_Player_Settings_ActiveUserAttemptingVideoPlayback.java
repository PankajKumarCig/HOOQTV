package tv.hooq.web.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_Player_Settings_ActiveUserAttemptingVideoPlayback extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"PLRST_03");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_Player_Settings_ActiveUserAttemptingVideoPlayback (String email,String type,String userType,String itemToPlay,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Login to Hooq
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
			//Search the Specific Item
				browseSpecificItem(itemToPlay);
			//Verify Playback	
				verifyPlaybackOfItem(itemToPlay,"Active");
			//Logout from Hooq
				logOut();
		
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
	}
	
	
	
	
	@DataProvider(name="testData")
	public Object[][] createData() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying Playback scenario for IN_ACTIVE_USER"},
	    		//{xlsLogin.getCellValue("ID_ACTIVE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying Playback scenario for ID_ACTIVE_USER"},
	    		//{xlsLogin.getCellValue("PH_ACTIVE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying Playback scenario for PH_ACTIVE_USER"},
	    		//{xlsLogin.getCellValue("TH_ACTIVE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying Playback scenario for TH_ACTIVE_USER"}
	    		
	};
	}
}
