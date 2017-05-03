package tv.hooq.web.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_Player_Settings_AnnonymousUserAttemptingContentPlayback extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"PLRST_04");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_Player_Settings_AnnonymousUserAttemptingContentPlayback (String email,String type,String userType,String itemToPlay,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Click on SignUp
				click(HeaderLocators.headerSignUp,"Sign Up");
			//Click on Skip
				click(HeaderLocators.lnkSkip,"Skip");
				click(HeaderLocators.lnkSkip,"Skip");
				Thread.sleep(10000);
			//Search Specific Item	
				browseSpecificItem(itemToPlay);
				Thread.sleep(10000);
			//Verify the Played Item
				verifyPlaybackOfItem(itemToPlay,"Annonymous");
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
	}
	
	
	
	
	@DataProvider(name="testData")
	public Object[][] createData() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying Playback scenario for Annonymous_USER"},	    	
	};
	}

}
