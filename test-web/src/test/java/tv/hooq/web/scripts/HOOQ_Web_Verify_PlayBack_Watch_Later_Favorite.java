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

public class HOOQ_Web_Verify_PlayBack_Watch_Later_Favorite extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Faviorite_01");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Verify_PlayBack_Watch_Later_Favorite (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Make as Favorite Movie
					fnVerifyFavoritesContenDetails("Duplex","Movies");
				//Play from Favorite
					Reporter.reportStep("Verify Playback From Favorites Movies");
					if(fnPlayfromFavoriteWatchLaterHistory())
					{	
							Reporter.SuccessReport("Verify Playback Movies From Favorites","Video is Play from Favorite");
						//Verify Playback
							verifyPlaybackOfItem("Duplex", "Free");
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback Movies From Favorites","Video is not Play from Favorite");
					}
				//Make as Favorite TV Series
					fnVerifyFavoritesContenDetails("Arrow","TVSeries");
				//Play from Favorite
					Reporter.reportStep("Verify Playback From Favorites TVSeries");
					if(fnPlayfromFavoriteWatchLaterHistory())
					{	
							Reporter.SuccessReport("Verify Playback TVSeries From Favorites","Video is Play from Favorite");
						//Verify Playback
							verifyPlaybackOfItem("Arrow", "Free");
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback TVSeries From Favorites","Video is not Play from Favorite");
					}	
				//Make as Watch Later Movie
					fnVerifyWatchLaterContenDetails("Duplex","Movies");
				//Play from Favorite
					Reporter.reportStep("Verify Playback From Watch Later Movies");
					if(fnPlayfromFavoriteWatchLaterHistory())
					{		Reporter.SuccessReport("Verify Playback Movies From Watch Later","Video is Play from Favorite");					
						//Verify Playback
							verifyPlaybackOfItem("Duplex", "Free");
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback From Watch Later","Video is not Play from Favorite");
					}
				//Make as Favorite TV Series
					fnVerifyWatchLaterContenDetails("Arrow","TVSeries");
				//Play from Favorite
					Reporter.reportStep("Verify Playback From Watch Later TVSeries");
					if(fnPlayfromFavoriteWatchLaterHistory())
					{						
							Reporter.SuccessReport("Verify Playback TVSeries From Watch Later","Video is Play from Favorite");
						//Verify Playback
							verifyPlaybackOfItem("Arrow", "Free");
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback From Watch Later","Video is not Play from Favorite");
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
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying HOOQ_Verify_FavoriteHomePage scenarios for IN_ACTIVE_USER"},
	    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying HOOQ_Verify_FavoriteHomePage scenarios for IN_LAPSED_USER"},	    											
		};
		}
}


