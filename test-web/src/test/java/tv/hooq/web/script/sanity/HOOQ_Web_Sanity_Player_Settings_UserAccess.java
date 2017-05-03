package tv.hooq.web.script.sanity;


import org.openqa.selenium.By;
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

public class HOOQ_Web_Sanity_Player_Settings_UserAccess extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA_SANITY"),"PLRST_05");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Player_Settings_UserAccess (String email,String type,String userType,String description) throws Throwable {
			try{
				
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			if(userType.equals("ACTIVE"))
			{
				//Click on SignUp
					click(HeaderLocators.headerSignUp,"Sign Up");
				//Click on Skip
					click(HeaderLocators.lnkSkip,"Skip");
					click(HeaderLocators.lnkSkip,"Skip");
					Thread.sleep(10000);
				//Search Specific Item	
					browseSpecificItem("DUPLEX");
					Thread.sleep(10000);
			//Verify the Played Item
					verifyPlaybackOfItem("DUPLEX","Annonymous");
			}
			else
			{				
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Verify Player Settings
					if(userType.equals("FREE"))
					{
						//Normal Movie should Play
							browseSpecificItem("DUPLEX");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("Duplex", "Free");						
						//Flash TV Series should be played Both 1st Episode and 2nd Episode
							//Play 1st Episode and Verify it should be Play
								browseSpecificEpisode("Flash","1");
								verifyPlaybackOfItem("Flash Episode 1", "Free");		
							//Play 2nd Episode and it should be Play
								browseSpecificEpisode("Flash","2");
								verifyPlaybackOfItem("Flash Episode 2", "Free");
						//Premium Movie should not be Played Like PK, The Avenger
							browseSpecificItem("PK");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("PK", "FreePremium");													
					}
					else if(userType.equals("LAPSED"))
					{
						//Normal Movie should not Play
							browseSpecificItem("DUPLEX");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("DUPLEX", "Lapsed");	
						//Flash TV Series Only 1st Episode should be Play
							//Play 1st Episode and Verify it should be Play
							browseSpecificEpisode("Flash","1");
							verifyPlaybackOfItem("Flash Episode 1", "Lapsed1stEpisode");	
							//Play 2nd Episode and Verify it should not Play
							browseSpecificEpisode("Flash","2");
							verifyPlaybackOfItem("Flash Episode 2", "Lapsed");
						//Premium Movie PK Should Not Play
							browseSpecificItem("PK");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("PK", "Lapsed");	
					}
					else if(userType.equals("ACTIVE"))
					{
						//Normal Movie Should be Play
							browseSpecificItem("DUPLEX");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("DUPLEX", "Active");	
						//Flash TV Series Should be Play Both 1st Episode and 2nd Episode
							//Play 1st Episode and Verify it should be Play
								browseSpecificEpisode("Flash","1");
								verifyPlaybackOfItem("Flash Episode 1", "Active");	
							//Play 2nd Episode and Verify it should be Play
								browseSpecificEpisode("Flash","2");	
								verifyPlaybackOfItem("Flash Episode 2", "Active");	
						//Premium Movie Should be Played Lik PK, The Avenger
							browseSpecificItem("PK");
						//Click on Play or Continue Watching
							verifyPlaybackOfItem("PK", "Active");	
					}
					//Logout from Hooq
					logOut();
				}						
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("LogIn", "Failed");
			}
		}
				
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying HOOQ_Player_Settings_UserAccess scenarios for IN_FREE_USER"},
		    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying HOOQ_Player_Settings_UserAccess scenarios for IN_LAPSED_USER"},
		    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying HOOQ_Player_Settings_UserAccess scenarios for IN_ACTIVE_USER"},
		    		{"Anonymos","Anonymos","Anonymos","Verifying HOOQ_Web_Sanity_Player_Settings_UserAccess scenarios for IN_ANONYMOS_USER"}
		};
		}
}


