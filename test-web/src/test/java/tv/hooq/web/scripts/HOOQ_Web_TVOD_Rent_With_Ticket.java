package tv.hooq.web.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.testObjects.TVOD;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_TVOD_Rent_With_Ticket extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"TVOD_Rent");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_TVOD_Rent_With_Ticket (String email,String type,String userType,String itemToPlay,String description,boolean blnTicketEnable) throws Throwable {
		try{			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Login to Hooq
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
			//Clear the Favorite
				fnClearListFaviorite();
			//Clear the Watch Later
				fnClearListWatchlater();				
			//Get Subscription Days
				String strSubscription=fnGetSubscriptionDaysCount();			
			//Get Ticket Information
				String strTicket=fnGetTicketCount(blnTicketEnable);				
			//Click on Rent
				JSClick(TVOD.Rent,"Rent Button");	
			//Verify Rental On board Message
				fnVerifyOnBoardMessage(strTicket);
				fnVerifyRentPage(strTicket);			
			//Purchase a Movie Using Ticket								
				String strMovieName=fnRentMoviewithTicket();
				if(strMovieName.length() >0 )
				{
					//Verify in Transaction History
						fnVerifyTransactionHistory(strMovieName);		
					//Get Ticket Information After Purchase
						String strTicketAfterPurchase=fnGetTicketCount(blnTicketEnable);
						if(Integer.parseInt(strTicketAfterPurchase) < Integer.parseInt(strTicket))
						{
							Reporter.SuccessReport("Verify Ticket Count after purchase", "Ticket count is updated successfullu Before : " + strTicket + "  After Purchase : " + strTicketAfterPurchase);
						}
						else
						{												
							Reporter.failureReportContinue("Verify Ticket Count after purchase", "Ticket count is not updated successfullu Before : " + strTicket + "  After Purchase : " + strTicketAfterPurchase);
						}				
					//Verify in Movie in Rental
						boolean	blnStatus=fnVerifyRentedMoveInRental(strTicketAfterPurchase,strMovieName,true);						
					//Play from Rental Section
						if(blnStatus)
						{
							boolean blnPlay=fnPlayMovieFromRentals(strMovieName);
							System.out.println(blnPlay);
							if(blnPlay)
							{
								//Make movie as Favorite
									Thread.sleep(5000);
									JSClick(HomePageLocators.cdFavorite,"Play Screen Favorites");
								//Make Movie as Watch Later
									Thread.sleep(5000);
									JSClick(HomePageLocators.cdWatchLater,"Play Screen Watch Later");
								//Verify movie Playback	
									verifyPlaybackOfItem(strMovieName, "Rent");								
								//Verify Movie in Favorite
									fnVerifyMovieInFavorite(strMovieName,"Rent");								
								//Verify Movie in Watch Later
									fnVerifyMovieInWatchLater(strMovieName,"Rent");
							}
						}
				}
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
	    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying HOOQ_TVOD_Rent_With_Ticket",true},	    	
    		
	};
	}

}
