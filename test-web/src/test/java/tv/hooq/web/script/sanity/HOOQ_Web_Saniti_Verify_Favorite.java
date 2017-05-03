package tv.hooq.web.script.sanity;


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

public class HOOQ_Web_Saniti_Verify_Favorite extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA_SANITY"),"Faviorite_01");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Saniti_Verify_Favorite (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Favorite from Banner
					fnVerifyHomePageBannerFavorite(userType);
				//Favorite from Home Page
					fnVerifyFavioritesHomeScreen();				
				//Favorite from Content Details Page
					fnVerifyFavoritesContenDetails("Duplex","Movies");
					fnVerifyFavoritesContenDetails("Arrow","TVSeries");		
					fnVerifyFavoritesContenDetails("Suicide Squad","Rent");
				//Favorite from Play Details
					fnVerifyFavoritesFromPlay("Duplex","Movies");
					fnVerifyFavoritesFromPlay("Arrow","TVSeries");
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
	    };
		}
}


