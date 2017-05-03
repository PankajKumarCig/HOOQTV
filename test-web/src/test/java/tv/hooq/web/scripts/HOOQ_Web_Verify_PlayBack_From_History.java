package tv.hooq.web.scripts;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.testObjects.ContactPageLocators;
import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.MailLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class HOOQ_Web_Verify_PlayBack_From_History extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Faviorite_01");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Verify_PlayBack_From_History (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Clear the content from History
					fnClearListHistory();
				//Normal Movie should Play
					browseSpecificItem("DUPLEX"); 
				//Play the Movie till Specified Time for Capture the History
					verifyPlaybackAtSpecifiedTime("Duplex","Free","00:06:00");
				//Verify Movie in History
					Reporter.reportStep("Verify History for Movies");
					if(fnVerifyHistory("Duplex","Free"))
					{
						if(fnPlayfromFavoriteWatchLaterHistory())
						{	
								Reporter.SuccessReport("Verify Playback Movies From History","Video is Play from History");
							//Verify Playback
								verifyPlaybackOfItem("Duplex", "Free");
						}
						else
						{
							Reporter.failureReportContinue("Verify Playback Movies From History","Video is not Play from History");
						}
					}																
				//Logout from HOOQ
					logOut();	
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("LogIn", "Failed");
			}
		}
	
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying HOOQ_Verify_FavoriteHomePage scenarios for IN_FREE_USER"},
	    		//{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying HOOQ_Verify_FavoriteHomePage scenarios for IN_ACTIVE_USER"},
	    		//{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying HOOQ_Verify_FavoriteHomePage scenarios for IN_LAPSED_USER"},	    											
		};
		}
}


