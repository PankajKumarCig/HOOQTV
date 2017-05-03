package tv.hooq.web.workflows;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.ContactPageLocators;
import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.testObjects.MailLocators;
import tv.hooq.web.testObjects.TVOD;

public class HOOQWorkflows extends HomePageLocators {	

	public static boolean verifyHomePage()throws Throwable
	{
		boolean res = false;
		try {
			//click(HeaderLocators.headerExploreMenu,"Explore Menu");
			click(HeaderLocators.headerSignIn,"Sign In");
			verifyElementDisplayed(HomePageLocators.imgLogo,"Hooq Logo");
			verifyElementDisplayed(HomePageLocators.headerLogin,"Login Header");
			verifyElementDisplayed(HomePageLocators.txtEmail,"Email Field");
			verifyElementDisplayed(HomePageLocators.btnDone,"Done");
			verifyElementDisplayed(HomePageLocators.lnkHelp,"Need Help? Contact us");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyHomePageLinksWork()throws Throwable
	{
		boolean res = false;
		try {
			
			boolean clickFlag = false;
			click(lnkHelp, "Need Help? Contact us");
			clickFlag = verifyElementDisplayed(ContactPageLocators.headerContactUs,"Contact Us Header");
			if(clickFlag)
			{
				
				driver.navigate().back();
			}			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyLoginWithExistingAndInvalidEmails(String email,String type, String userType)throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("Login and Verification");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");
			type(txtEmail, email, "Email Address");
			Thread.sleep(2000);
			JSClick(btnDone, "Done");			
			//Thread.sleep(10000);
			if(type.equals("valid")||type.equals("new"))
			{
				if(isElementDisplayed(HomePageLocators.lblVerifyEmail))
				{
					click(HomePageLocators.btnPassword,"Enter Pasword");
					Thread.sleep(2000);
					if(isElementDisplayed(txtEmail))
					{
						type(txtEmail, email, "Email Address");
					}
					type(HomePageLocators.edtPassword, "123456", "Enter Password");
					//type(HomePageLocators.edtPassword, "password1", "Enter Password");
					Thread.sleep(3000);
					click(HomePageLocators.btnPasswordOK,"OK");					
				}
			}			
			if(type.equals("invalid"))
			{
				verifyElementDisplayed(lblInvalidEmail, "Invalid email address");
			}
			else if(type.equals("invalidNonExistent"))
			{
				verifyElementDisplayed(lblNonExistingEmail, "Account does not exist");
			}
			else if(type.equals("valid")||type.equals("new"))
			{
				res=verifyElementDisplayed(HeaderLocators.headerUserProfile, "User Profile");				
			}			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyUserProfile()
	{
		boolean res = false;
		try {
			WebDriverWait newWait = new WebDriverWait(driver,5,5);
			WebElement element = null;
			element  = newWait.until(ExpectedConditions.presenceOfElementLocated(HeaderLocators.headerUserProfile));
			System.out.println(element.isDisplayed());
			res=element.isDisplayed();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean verifySignUpWithEmail()throws Throwable
	{
		boolean res = false;
		try {
			
			
			Reporter.reportStep("Signup and Verification");
			/*Thread.sleep(3000);*/
			//click(HeaderLocators.headerExploreMenu,"Header Explore Menu");
			click(HeaderLocators.headerSignUp,"Sign Up");
			//click(HeaderLocators.lnkLoginWithEmail,"Log in with Email");
			int RandomNumberForEmail = ThreadLocalRandom.current().nextInt(1, 9999999 + 1);
			String RandomEmailAddress =  "Hooq" + RandomNumberForEmail + "@yopmail.com";
			//click(HeaderLocators.lnkSkip,"Skip");
			
			type(txtEmail, RandomEmailAddress, "Email Address");
			
			Thread.sleep(2000);
			JSClick(btnDone, "Done");			
			confirmEmailAddress(RandomEmailAddress);
			Thread.sleep(5000);
			click(HeaderLocators.headerSignIn,"Log In");
			//click(HeaderLocators.lnkLoginWithEmail,"Log in with Email");				
			type(txtEmail, RandomEmailAddress, "Email Address");
			JSClick(btnDone, "Done");		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean browseSpecificItem(String itemToPlay)throws Throwable
	{
		boolean res = false;
		try {
			
			Reporter.reportStep("Browsing Specific Item");
			click(HeaderLocators.headerSearch,"Search Button");
			type(HeaderLocators.txtSearch,itemToPlay,"Search Textbox");
			Thread.sleep(5000);
			String strText=getText(By.xpath(".//*[@class='search-result-title']/p"), "Search Result");
			System.out.println(strText);
			if(strText.toLowerCase().equals(itemToPlay.toLowerCase()))
			{
				System.out.println(strText);
				JSClick(By.xpath(".//*[@class='search-result-title']/p"),strText);	
				JSClick(HeaderLocators.btnWatchNow,"Watch Now");
				res=true;
			}			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean SearchSpecificItem(String itemToPlay)throws Throwable
	{
		boolean res = false;
		try {			
			Reporter.reportStep("Browsing Specific Item");
			click(HeaderLocators.headerSearch,"Search Button");
			type(HeaderLocators.txtSearch,itemToPlay,"Search Textbox");
			Thread.sleep(10000);
			String strText=getText(By.xpath(".//*[@class='search-result-title']/p"), "Search Result");
			System.out.println(strText);
			if(strText.toLowerCase().equals(itemToPlay.toLowerCase()))
			{
				System.out.println(strText);
				JSClick(By.xpath(".//*[@class='search-result-title']/p"),strText);			
				res=true;
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyTimesUpScreen(String typeOfUser)throws Throwable
	{
		boolean res = false;
		try {
			if (typeOfUser.equals("Lapsed"))
			{
			Reporter.reportStep("Verifying Timesup screen for Lapsed user");
			verifyElementDisplayed(HeaderLocators.lblTimesUpPageSubscribeNow,"Subscribe to HOOQ");
			verifyText(HeaderLocators.lblTimesUpPageSubscribeNow, "Subscribe to HOOQ", "Subscribe to HOOQ");
			verifyElementDisplayed(HeaderLocators.lblTimesUpPageSubscribeNowHelpText,"Subscribe Now Help Text");
			verifyText(HeaderLocators.lblTimesUpPageSubscribeNowHelpText, "You need to see how this one ends! Choose a plan that’s right for you and activate your HOOQ subscription today!", "Subscribe Now Help Text");
			verifyElementDisplayed(HeaderLocators.btnTimesUpPageSubscribeNowActivate,"Activate Now Button");
			verifyText(HeaderLocators.btnTimesUpPageSubscribeNowActivate, "Activate my HOOQ Subscription", "Activate Now Button");
			verifyElementDisplayed(HeaderLocators.btnTimesUpPageSubscribeNowCancel,"Cancel Button");
			click(HeaderLocators.btnTimesUpPageSubscribeNowActivate,"Activate Now");
			verifyElementDisplayed(HeaderLocators.scnPagePlanSelectionPlanSelectionBlock,"Plan Selection Screen");
			}
			else if(typeOfUser.equals("Annonymous"))
			{
				Reporter.reportStep("Verifying Timesup screen for Annonymous User");
				verifyElementDisplayed(HeaderLocators.lblTimesUpPageSubscribeNow,"Subscribe to HOOQ");
				verifyText(HeaderLocators.lblTimesUpPageSubscribeNow, "Subscribe to HOOQ", "Subscribe to HOOQ");
				verifyElementDisplayed(HeaderLocators.lblTimesUpPageSubscribeNowHelpText,"Subscribe Now Help Text");
				verifyText(HeaderLocators.lblTimesUpPageSubscribeNowHelpText, "You need to see how this one ends! Choose a plan that’s right for you and activate your HOOQ subscription today!", "Subscribe Now Help Text");
				verifyElementDisplayed(HeaderLocators.btnTimesUpPageSubscribeNowActivate,"Activate Now Button");
				verifyText(HeaderLocators.btnTimesUpPageSubscribeNowActivate, "Activate my HOOQ Subscription", "Activate Now Button");
				verifyElementDisplayed(HeaderLocators.btnTimesUpPageSubscribeNowCancel,"Cancel Button");
				click(HeaderLocators.btnTimesUpPageSubscribeNowActivate,"Activate Now");
				verifyElementDisplayed(HomePageLocators.lblSignUP,"Sign Up");
			}
			else if(typeOfUser.equals("FreePremium"))
			{
				Reporter.reportStep("Verifying Timesup screen for Free Premium User");
				verifyElementDisplayed(HomePageLocators.lblPlayerErrorFree,"Title is not available for free trial users");
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPlaybackOfItem(String itemToPlay, String userType)throws Throwable
	{
		boolean res = false;
		try {
			if(userType.equalsIgnoreCase("Rent"))
			{
				Reporter.reportStep("Verifying Playback of Rented Title");
			}
			else if(userType.equalsIgnoreCase("Lapsed1stEpisode"))
			{
				Reporter.reportStep("Verifying Playback of TVSeries");
			}
			else
			{
				Reporter.reportStep("Verifying Playback");
			}
			if((userType.equalsIgnoreCase("Active"))||(userType.equalsIgnoreCase("Free")) ||(userType.equalsIgnoreCase("Lapsed1stEpisode")) ||(userType.equalsIgnoreCase("Rent")))
			{
				Thread.sleep(30000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				verifyElementDisplayed(HeaderLocators.sbarSeekBar, "Seekbar");
				mouseover(HeaderLocators.sbarSeekBar, "Video Area");
				verifyElementDisplayed(HeaderLocators.btnPause, "Pause Button");	
				verifyElementDisplayed(HeaderLocators.btnResize, "Pause Button");
				verifyElementDisplayed(HeaderLocators.btnResize, "Resize Button");
				verifyElementDisplayed(HeaderLocators.btnSetting, "Setting Button");
				String currentPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
				String totalPlayTimeLabel = getText(HeaderLocators.lblTotalPlayTIme, "Total PlayTime");
				Thread.sleep(30000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				String newPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
				if(!newPlayTimeLabel.equals(currentPlayTimeLabel))
				{
					Reporter.SuccessReport("Verify Playback", "Video successfully played from <b>" + currentPlayTimeLabel + "</b> to <b>" + newPlayTimeLabel + "</b>");					
				}
				else
				{
					Reporter.failureReportContinue("Verify Playback", "Video did <b> NOT </b> play successfully");
				}
				return true;
			}
			else if(userType.equalsIgnoreCase("Lapsed"))
			{
				verifyTimesUpScreen("Lapsed");
			}
			
			else if(userType.equalsIgnoreCase("Annonymous"))
			{
				verifyTimesUpScreen("Annonymous");
			}
			else if(userType.equalsIgnoreCase("FreePremium"))
			{
				verifyTimesUpScreen("FreePremium");
			}
			
			
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean logOut()throws Throwable
	{
		boolean res = false;
		try {
			
			Reporter.reportStep("Logout");
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerLogout,"Log out");		
			if(verifyElementDisplayed(By.xpath(".//*[@id='submit-button']"),"Logout")==true)
			{
				res=true;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean browseSpecificEpisode(String itemToPlay,String strEpisodeNo)throws Throwable
	{
		boolean res = false;
		try {
			
			Reporter.reportStep("Browsing Specific Item");
			click(HeaderLocators.headerSearch,"Search Button");
			type(HeaderLocators.txtSearch,itemToPlay,"Search Textbox");
			//click(By.xpath(".//span[text()='"+itemToPlay+"']"), "Item: "+ itemToPlay);
			click(By.xpath(".//*[@class='search-result-title']"), "Item: "+ itemToPlay);
			JSClick(By.xpath("//*[@id='season-1']/div["+strEpisodeNo+"]/a/i"),"Play Episode "+ strEpisodeNo);
			res=true;
			//Thread.sleep(10000);			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean confirmEmailAddress(String validEmail)throws Throwable
	{
		boolean res = false;
		try {
			//Thread.sleep(3000);
			Reporter.reportStep("Confirming Email Address");
		
			driver.navigate().to("http://www.yopmail.com");
			type(MailLocators.txtLoginEmail,validEmail,"Login Email");
			click(MailLocators.btnCheckEmail,"Check Email");
			//Thread.sleep(2000);
			driver.switchTo().frame("ifinbox");
			//Thread.sleep(2000);
			click(MailLocators.lblVerificationEmail,"Confirmation Email Link");
			//Thread.sleep(2000);
			//click(MailLocators.btnConfirmEmail,"Confirm Email");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("ifmail");
			String lnkConfirmationEmail = getAttribute(MailLocators.btnConfirmEmail, "href", "Confirm Email");
			//Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("ifinbox");
			click(MailLocators.lnkDelete,"Delete");
			click(MailLocators.lnkDeleteAll,"Empty Inbox");
			//Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.navigate().to(lnkConfirmationEmail);
			//Thread.sleep(5000);
			driver.navigate().to(configProps.getProperty("URL"));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyAllPlans(String planDuration, String planPricing, String subHeader, String planDescription)throws Throwable
	{
		boolean res = false;
		try {
			
			Reporter.reportStep("Verifying Each Plan and Payment Details");
			
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifySubscriptionPageScreen(String subscriptionPageInfo,String subscriptionPageInfoActual)throws Throwable
	{
		boolean res = false;
		try {
			
			String[] subscriptionPageInfoArray = subscriptionPageInfo.split("\\^");
			String[] subscriptionPageInfoActualArray = subscriptionPageInfoActual.split("\\^");
			int arraySize = subscriptionPageInfoArray.length;
			Reporter.reportStep("Verifying Subscription page details");
			for (int i = 0; i < arraySize; i++) {
			    
			   if(checkRegularExpression(subscriptionPageInfoArray[i], subscriptionPageInfoActualArray[i]))
			   {
				   Reporter.SuccessReport("Check Subscription Page", "Subscription page detail is correct. Expected: <b> "+subscriptionPageInfoArray[i] +", Actual: "+subscriptionPageInfoActualArray[i] +"</b>");
			   }
			   else
			   {
				   Reporter.failureReport("Check Subscription Page", "Subscription page detail is <b> NOT </b> correct. Expected: <b> "+subscriptionPageInfoArray[i] +", Actual: "+subscriptionPageInfoActualArray[i] +"</b>");
			   }
			}
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public static boolean verifyManageSubscriptionPageScreen(String planDetails)throws Throwable
	{
		boolean res = false;
		try {
			
			String[] allPlansArray = planDetails.split("\\|");
			String[] planDetailsArray;
			int arrayPlanSize = allPlansArray.length;
			Reporter.reportStep("Verifying Plan page details");
			
			List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card']")); 
			int elementSize = elements.size();
			if(arrayPlanSize != elementSize)
			{
				Reporter.failureReport("Checking Number of Plans", "Number of Plans are greater than expected, Expected: <b>" + (arrayPlanSize-1 ) + "</b> Actual: <b>" + (elementSize) + "</b");
			}
			for (int i = 0; i < arrayPlanSize; i++) {
				planDetailsArray = allPlansArray[i].split("\\^");
				int arrayplanDetailsSize = allPlansArray.length;
				for (int j = 0; j < arrayplanDetailsSize; j++) {
					if(planDetailsArray[j].length() > 0)
					{
						switch(j){
						
					    case 0 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-title']//h2"),planDetailsArray[j], "Plan Title");
					    	break;
					    case 1 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-title']//p"),planDetailsArray[j], "Plan Title Note");
					    	break;
					    case 2 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block pricing']//p"),planDetailsArray[j], "Plan Pricing");
					    	break;
					    case 3 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block details']//p"),planDetailsArray[j], "Plan Details");
					    	break;
					    case 4 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block selection']//p"),planDetailsArray[j], "Auto Renew Detail");
					    	break;
					    case 5 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block selection']//label"),planDetailsArray[j], "Auto Renew");
					    	break;
					    case 6 :
					    	verifyText(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block selection']//button"),planDetailsArray[j], "Select Plan");
					    	break;
					}
					}
					else
					{
						switch(j){
					    case 4 :
					    	verifyElementAbsent(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block selection']//p"), "Auto Renew Detail");      
					    	break;
					    case 5 :
					    	verifyElementAbsent(By.xpath("(.//div[@class='card'])["+(i+1)+"]//div[@class='card-block selection']//label"),"Auto Renew");      
					    	break;
					}
					}
				}
			   
			}
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fillCardDetails(String cardNumber,String nameOnCard,String expiryDate,String cvv,boolean clickMakePayment) throws Throwable{
		boolean res=false;
		try {
			
			switchToFrameByIndex(0);
			
			if(cardNumber.length()>0)
				type(HeaderLocators.inpCardNumber, cardNumber, "Card Number");
			
			if(nameOnCard.length()>0)
				type(HeaderLocators.inpNameOnCard, nameOnCard, "Name on Card");
			
			if(expiryDate.length()>0)
			type(HeaderLocators.inpExpiryDate, expiryDate, "Expiry date");
			
			if(cvv.length()>0)
				type(HeaderLocators.inpCVV, cvv, "CVV");
			
			if(clickMakePayment)
			{
				click(HeaderLocators.btnMakePayment, "Make Payment");
			}
			
			switchToDefaultFrame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean applyVoucherCodeAndVerify(String voucher,String voucherType,String voucherMessage) throws Throwable{
		boolean res=false;
		try {
			if(voucherType.equalsIgnoreCase("valid")){
				type(HeaderLocators.txtSubscriptionPageVoucherCode, voucher, "Valid voucher Code");
				click(HeaderLocators.btnSubscriptionPageSubmitVoucher, "Voucher code submission");
				//Thread.sleep(1000);
				assertText(HeaderLocators.lblVoucherMsg, voucherMessage);
			}
			else if(voucherType.equalsIgnoreCase("invalid")){
				type(HeaderLocators.txtSubscriptionPageVoucherCode, voucher, "Invalid voucher Code");
				click(HeaderLocators.btnSubscriptionPageSubmitVoucher, "Voucher code submission");
				Thread.sleep(10000);
				assertText(HeaderLocators.lblVoucherMsg, voucherMessage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToPlanSelector(String billingCycle) throws Throwable{
		boolean res=false;
		try {
			//header options
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerSubscription,"Subscription");
			//click on manage 
			click(HeaderLocators.btnPlanPageManage,"Manage");
			if(billingCycle.length()>0)
			{
				//click on selector plan
				click(HeaderLocators.btnSelectPlan(billingCycle), billingCycle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyCarrierBilling(String carrierBilling,boolean click) throws Throwable{
		boolean res=false;
		try {
			switchToFrameByIndex(0);
			String[] carrier = carrierBilling.split("\\^");
			if(carrier.length>1){
				type(HeaderLocators.txtMobileNumber, carrier[0], "Mobile Number");
				selectByOptionText(HeaderLocators.lstCarrier, carrier[1], "Carrier plan");
			}
			else{
				type(HeaderLocators.txtMobileNumber, carrier[0], "Mobile Number");
			}
			if(click){
				click(HeaderLocators.btnMakePayment, "Make Payment");
			}
			switchToDefaultFrame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyGlobeAIS(String carrierBilling,boolean click) throws Throwable{
		boolean res=false;
		try {
			switchToFrameByIndex(0);
			String[] carrier = carrierBilling.split("\\^");
			type(HeaderLocators.txtPhMobileNumber, carrier[0], "Mobile Number");
			if(click){
				click(HeaderLocators.btnMakePayment, "Make Payment");
			}
			switchToDefaultFrame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyCashCard(String cashCard,boolean click) throws Throwable{
		boolean res = false;
		try {
			switchToFrameByIndex(0);
			selectByOptionText(HeaderLocators.lstCashCard, cashCard, "Cash Card");
			if(click){
				click(HeaderLocators.btnMakePayment, "Make Payment");
			}
			switchToDefaultFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyNetBanking(String netBanking,boolean click) throws Throwable{
		boolean res=false;
		try {
			switchToFrameByIndex(0);
			selectByOptionText(HeaderLocators.lstNetBanking, netBanking, "NetBanking");
			if(click){
				click(HeaderLocators.btnMakePayment, "Make Payment");
			}
			switchToDefaultFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPayTm(String paytm,boolean click) throws Throwable{
		boolean res=false;
		try {
			switchToFrameByIndex(0);
			Reporter.SuccessReport("PayTm", "In payTm block");
			//need to write code for payTM
			switchToDefaultFrame();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToSubscription() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerSubscription,"Subscription");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToFavorite() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerFavorite,"Favorite");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToWatchlater() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerWatchlater,"Watch later");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToHistory() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerHistory,"History");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToTransactionHistory() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HomePageLocators.lblHeaderTransactionHistory,"History");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToRentals() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HomePageLocators.lblHeaderRentals,"Rentals");
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean navigateToSettings() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerSettings,"Settings");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean navigateToSupport() throws Throwable{
		boolean res=false;
		try {
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			JSClick(HeaderLocators.headerSupport,"Support");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPaymentMethodsForAllPlans(String paymentDetails1,String paymentDetails2, String paymentDetails3, String paymentDetails4, String paymentDetails5) throws Throwable{
		boolean res=false;
		try {
			boolean autoRenewFlag = false;
			//7 days plan block
			Reporter.reportStep("Verifying payment details for 7 Days Plan");
			
			String[] plan1Details =  paymentDetails1.split("\\;");
			
			if(!(plan1Details[1].equals("NA")))
			{
				/*autoRenewFlag = verifyElementDisplayed(By.xpath("//form[1]//input[@type='checkbox']/../label"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[1]//input[@type='checkbox']/../label"),"Auto-Renew");
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}*/
			}
			
			click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
			verifyPaymentsMethodsPage(plan1Details[0],7);
			driver.navigate().back();
			/*Reporter.reportStep("Verifying payment details for 7 Days Plan - Recurring");
			if(!(plan1Details[1].equals("NA")))
			{
				//flag = verifyElementDisplayed(By.xpath("/form[1]//input[@type='checkbox']"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
					click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
					verifyPaymentsMethodsPage(plan1Details[1],7);
					driver.navigate().back();
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}*/
			
			//30 Days plan block
			Reporter.reportStep("Verifying payment details for 30 Days Plan");
			String[] plan2Details =  paymentDetails2.split("\\;");
			
			if(!(plan2Details[1].equals("NA")))
			{
				autoRenewFlag = verifyElementDisplayed(By.xpath("//form[2]//input[@type='checkbox']/../label"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");					
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
			verifyPaymentsMethodsPage(plan2Details[0],30);
			driver.navigate().back();
			
			Reporter.reportStep("Verifying payment details for 30 Days Plan - Recurring");
			if(!(plan2Details[1].equals("NA")))
			{
				//boolean flag = verifyElementDisplayed(By.xpath("/form[2]//input[@type='checkbox']"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
					click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
					verifyPaymentsMethodsPage(plan2Details[1],30);
					driver.navigate().back();
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			
			//90 Days plan block
			Reporter.reportStep("Verifying payment details for 90 Days Plan");
			String[] plan3Details =  paymentDetails3.split("\\;");
			
			if(!(plan3Details[1].equals("NA")))
			{
				autoRenewFlag = verifyElementDisplayed(By.xpath("//form[3]//input[@type='checkbox']/../label"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
					
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
			verifyPaymentsMethodsPage(plan3Details[0],90);
			driver.navigate().back();
			
			Reporter.reportStep("Verifying payment details for 90 Days Plan - Recurring");
			if(!(plan3Details[1].equals("NA")))
			{
				//boolean flag = verifyElementDisplayed(By.xpath("/form[3]//input[@type='checkbox']"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
					click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
					verifyPaymentsMethodsPage(plan3Details[1],90);
					driver.navigate().back();
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			
			
			//1800 Days plan block
			Reporter.reportStep("Verifying payment details for 180 Days Plan");
			String[] plan4Details =  paymentDetails4.split("\\;");
			
			if(!(plan4Details[1].equals("NA")))
			{
				autoRenewFlag = verifyElementDisplayed(By.xpath("//form[4]//input[@type='checkbox']/../label"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
					
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
			verifyPaymentsMethodsPage(plan4Details[0],180);
			driver.navigate().back();
			
			Reporter.reportStep("Verifying payment details for 180 Days Plan - Recurring");
			if(!(plan4Details[1].equals("NA")))
			{
				//boolean flag = verifyElementDisplayed(By.xpath("/form[4]//input[@type='checkbox']"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
					click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
					verifyPaymentsMethodsPage(plan4Details[1],180);
					driver.navigate().back();
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			
			//360 Days plan block
			Reporter.reportStep("Verifying payment details for 360 Days Plan");
			String[] plan5Details =  paymentDetails5.split("\\;");
			
			if(!(plan5Details[1].equals("NA")))
			{
				autoRenewFlag = verifyElementDisplayed(By.xpath("//form[5]//input[@type='checkbox']/../label"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
					
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
			verifyPaymentsMethodsPage(plan5Details[0],360);
			driver.navigate().back();
			
			Reporter.reportStep("Verifying payment details for 360 Days Plan - Recurring");
			if(!(plan5Details[1].equals("NA")))
			{
				//boolean flag = verifyElementDisplayed(By.xpath("/form[5]//input[@type='checkbox']"), "Auto-Renew");
				if (autoRenewFlag)
				{
					click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
					click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
					verifyPaymentsMethodsPage(plan5Details[1],360);
					driver.navigate().back();
				}
				else
				{
					Reporter.failureReportContinue("Checking Auto-Renew option", "<b> Auto-Renew </b> is <b> NOT </b> displayed for plan");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPaymentsMethodsPage(String paymentDetails,long planDays) throws Throwable{
		boolean res=false;
		try {
			
			String[] paymentDetailsSections = paymentDetails.split("\\|");
			String[] paymentHeaderInfo = paymentDetailsSections[0].split("\\^");
			String[] paymentPlansInfo = paymentDetailsSections[1].split("\\^");
			List<WebElement> fieldsets= driver.findElements(By.xpath("//fieldset//label"));
			  if(!(paymentPlansInfo.length == fieldsets.size()))
			  {
				  Reporter.failureReportContinue("Checking payment option count", "Payment method count is not matching, Expected: <b>"+ (paymentPlansInfo.length) + "</b> Actual: <b>" + (fieldsets.size()));
			  }
			  for(int i=0;i <fieldsets.size();i++ )
			  {
				  if(!(paymentDetailsSections[1].contains(fieldsets.get(i).getText())))
						  {
					  		Reporter.failureReportContinue("Checking availability of payment methods", "Payment Method: <b>" + fieldsets.get(i).getText() + "</b> is <b> NOT </b> available");
						  }
				  else
				  {
					  Reporter.SuccessReport("Checking availability of payment methods", "Payment Method: <b>" + fieldsets.get(i).getText() + "</b> is available");
				  }
			  }
			  
			  if(paymentDetailsSections[0].contains(driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentDescription).getText()))
			  {
				  Reporter.SuccessReport("Checking Plan Description", "Description is correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentDescription).getText() + "</b>");
			  }
			  
			  else
			  {
				  Reporter.failureReportContinue("Checking Plan Description", "Description is <b> NOT </b> correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentDescription).getText() + "</b>");
			  }
			  
			  String[] periodDates = driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentPeriod).getText().split("to");
			  
			  String startDate = periodDates[0].trim();
			  String endDate = periodDates[1].trim();
			  java.util.Date startformatteddate = new SimpleDateFormat("MMM dd, yyyy").parse(startDate);
			  java.util.Date endformatteddate = new SimpleDateFormat("MMM dd, yyyy").parse(endDate);
			  
			  long diff = endformatteddate.getTime() - startformatteddate.getTime();
			    long planDaysFromDifference = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			  if(planDaysFromDifference == planDays)
			  {
				  Reporter.SuccessReport("Checking Payment Period", "Payment Period is correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentPeriod).getText() + "</b>");
			  }
			  
			  else
			  {
				  Reporter.failureReportContinue("Checking Payment Period", "Payment Period is <b> NOT </b> correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePaymentPeriod).getText() + "</b>");
			  }
			  
			  if(paymentDetailsSections[0].contains(driver.findElement(HeaderLocators.lblPaymentMethodsPageRecurrance).getText()))
			  {
				  Reporter.SuccessReport("Checking Payment Recurrance", "Recurrance is correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPageRecurrance).getText() + "</b>");
			  }
			  
			  else
			  {
				  Reporter.failureReportContinue("Checking Payment Recurrance", "Recurrance is <b> NOT </b> correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPageRecurrance).getText() + "</b>");
			  }
			  
			  
			  if(paymentDetailsSections[0].contains(driver.findElement(HeaderLocators.lblPaymentMethodsPagePrice).getText()))
			  {
				  Reporter.SuccessReport("Checking Price", "Price is correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePrice).getText() + "</b>");
			  }
			  
			  else
			  {
				  Reporter.failureReportContinue("Checking Price", "Price is <b> NOT </b> correctly displayed: <b>" + driver.findElement(HeaderLocators.lblPaymentMethodsPagePrice).getText() + "</b>");
			  }
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void verifyPaymentOptions(String billingCycle,String modeOfPayments,String identifier,String cardErrorMsg) throws Throwable{
		try {
			ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Payments");
			String[] payments = modeOfPayments.split("\\^"); 
			for (int i = 0; i < payments.length; i++) {
				// it will navigate to billing cycle
				navigateToPlanSelector(billingCycle);
				// it will select payment options
				click(HeaderLocators.inpPaymentMethod(payments[i]), payments[i]);
				//click on continue
				click(HeaderLocators.btnContinue, "Continue button");
				switch (payments[i]) {
				case "Debit Card":
				case "Credit Card":
					String cardNumber = xlsLogin.getCellValue("CARDNUMBER", "Value");
					String nameOnCard = xlsLogin.getCellValue("NAMEOFCARD", "Value");
					String expiryDate = xlsLogin.getCellValue("EXPIRYDATE", "Value");
					String cvv = xlsLogin.getCellValue("CVV", "Value");
					fillCardDetails(cardNumber,nameOnCard,expiryDate,cvv,true);
					switchToFrameByIndex(0);
					verifyText(HeaderLocators.pCardErrorMessage, cardErrorMsg, "Card error message");
					switchToDefaultFrame();
					Thread.sleep(2000);
					break;
				
				case "Carrier Billing":
					String key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String carrierBilling = xlsLogin.getCellValue(key, "Value");
					String errorMsg = xlsLogin.getCellValue("MOBILEERRORMSG", "Value");
					verifyCarrierBilling(carrierBilling,true);
					switchToFrameByIndex(0);
					verifyText(HeaderLocators.pMobileErrorMsg,errorMsg , "Mobile number");
					switchToDefaultFrame();
					Thread.sleep(1000);
					break;
				case "Cash Card":
					key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String cashCard = xlsLogin.getCellValue(key, "Value");
					verifyCashCard(cashCard,true);
					//need to write code for cash card verification
					Thread.sleep(1000);
					break;
				case "Netbanking":
					key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String netBanking = xlsLogin.getCellValue(key, "Value");
					verifyNetBanking(netBanking,false);
					//need to write code for net banking
					Thread.sleep(1000);
					break;
				case "PayTM":
					key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String paytm = xlsLogin.getCellValue(key, "Value");
					verifyPayTm(paytm,true);
					Thread.sleep(1000);
					break;
				case "Charge to Globe Mobile":
					key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String globeMobile = xlsLogin.getCellValue(key, "Value");
					errorMsg = xlsLogin.getCellValue("MOBILEERRORMSG", "Value");
					verifyGlobeAIS(globeMobile,true);
					switchToFrameByIndex(0);
					verifyText(HeaderLocators.pGlobeErrorMsg,errorMsg , "Mobile number");
					switchToDefaultFrame();
					break;
				case "Charge to AIS Mobile":
					key = identifier+"_"+payments[i].replaceAll("\\s", "").toUpperCase();
					String aisMobile = xlsLogin.getCellValue(key, "Value");
					errorMsg = xlsLogin.getCellValue("PH_ERRORMSG", "Value");
					verifyGlobeAIS(aisMobile,true);
					switchToFrameByIndex(0);
					verifyText(HeaderLocators.pGlobeErrorMsg,errorMsg , "Mobile number");
					switchToDefaultFrame();
					break;
				default:
					Reporter.failureReportContinue("PaymentDetails", "Invalid payment option");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean verifyLoginWithFacebook(String email,String type, String userType)throws Throwable
	{
		boolean res = false;
		try {
			
			
			Reporter.reportStep("Login with Facebook");
			click(HeaderLocators.headerSignIn,"Log In");
			//API Selection
			//click(By.xpath("//*[@id='btn-current-api']"),"API Selection");
			//Thread.sleep(2000);
			//String strAPIName="Test";
			//click(By.xpath(".//a[text()='"+strAPIName+"']"),strAPIName + " Selected");
			Thread.sleep(2000);	
			String  handle= driver.getWindowHandle();
			JSClick(btnFacebook, "Login with Facebook");
			Thread.sleep(5000);
			waitForNewWindowAndSwitchToIt();
			//System.out.println("Title ==> " + driver.getTitle());
			asserTitle("Facebook"); 
						 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void waitForNewWindowAndSwitchToIt() throws InterruptedException {
        String cHandle = driver.getWindowHandle();
        String newWindowHandle = null;
        Set<String> allWindowHandles = driver.getWindowHandles();        
        //Wait for 20 seconds for the new window and throw exception if not found
        for (int i = 0; i < 20; i++) {
            if (allWindowHandles.size() > 1) {
                for (String allHandlers : allWindowHandles) {
                    if (!allHandlers.equals(cHandle))
                    	newWindowHandle = allHandlers;
                }
                driver.switchTo().window(newWindowHandle);
                break;
            } else {
                Thread.sleep(1000);
            }
        }
        if (cHandle == newWindowHandle) {
            throw new RuntimeException(
                    "Time out - No window found");
        }
    }
	
	public static boolean fnClearListFaviorite() throws Throwable
	{
		boolean res = false;
		try {		
			Reporter.reportStep("Clear Faviorite List");
			navigateToFavorite();			
			Thread.sleep(15000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItem=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("icon-cancel"));
				for(int i=0;i<eleItem.size();i++)
				{
					eleItem.get(i).click();
					Thread.sleep(5000);
				}
				navigateToFavorite();
				Thread.sleep(5000);
				//Verify List is Empty			
				if(isElementDisplay(By.className("error-empty-state-wrapper")))
				{
					System.out.println("Clear List Faviorite Done");
					Reporter.SuccessReport("Verify Faviorite Section", "Faviorite Section is Empty Successfully");
				}
				else
				{
					System.out.println("Clear List Faviorite Not Done");
					Reporter.failureReportContinue("Verify Faviorite Section", "Faviorite Section is not Empty.");
				}
			}
			else
			{
				System.out.println("Faviorite is Empty");
				Reporter.SuccessReport("Verify Faviorite Section", "Faviorite Section is Empty Successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static boolean fnClearListHistory() throws Throwable
	{
		boolean res = false;
		try {		
			Reporter.reportStep("Clear History List");
			navigateToHistory();			
			Thread.sleep(15000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItem=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("icon-cancel"));
				for(int i=0;i<eleItem.size();i++)
				{
					eleItem.get(i).click();
					Thread.sleep(5000);
				}
				navigateToHistory();
				Thread.sleep(5000);
				//Verify List is Empty			
				if(isElementDisplay(By.className("error-empty-state-wrapper")))
				{
					System.out.println("Clear List History Done");
					Reporter.SuccessReport("Verify History Section", "History Section is Empty Successfully");
				}
				else
				{
					System.out.println("Clear List History Not Done");
					Reporter.failureReportContinue("Verify History Section", "History Section is Empty Successfully");
				}
			}
			else
			{
				System.out.println("History is Empty");
				Reporter.SuccessReport("Verify History Section", "History Section is Empty Successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	public static boolean fnPlayfromFavoriteWatchLaterHistory() throws Throwable
	{
		boolean blnPlay=false;
		try
		{
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItem=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("play"));
				for(int i=0;i<eleItem.size();i++)
				{
					eleItem.get(i).click();
					Thread.sleep(5000);
					blnPlay=true;
				}			
			}
			else
			{
				System.out.println("Faviorite is Empty");
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnPlay;
	}
	
	
	
	public static boolean fnClearListWatchlater() throws Throwable
	{
		boolean res = false;
		try {		
			Reporter.reportStep("Clear Watch later List");
			navigateToWatchlater();
			Thread.sleep(15000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItem=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("icon-cancel"));
				for(int i=0;i<eleItem.size();i++)
				{
					eleItem.get(i).click();
					Thread.sleep(5000);
				}
				navigateToWatchlater();
				Thread.sleep(15000);
				//Verify List is Empty			
				if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
				{
					System.out.println("Clear List Watch later Done");
					Reporter.failureReportContinue("Verify  Watch later Section", " Watch later Section is not Empty.");
				}
				else
				{
					System.out.println("Clear List Watch later Not Done");
					Reporter.SuccessReport("Verify  Watch later Section", " Watch later Section is Empty.");
				}
			}
			else
			{
				System.out.println("Watch later is Empty");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean fnVerifySupport() throws Throwable
	{
		boolean res = false;
		try {
				Reporter.reportStep("Verify Hooq Support");
			//Navigate to Support
				navigateToSupport();
				Thread.sleep(5000);
			//Verify Support Page
				fnVerifyHooqSupportPageDetails();	
				Thread.sleep(5000);
			//Verify Support Page Link
				Reporter.reportStep("Verify Hooq Support Link");
				fnVerifySupportLink(lnkFooterAboutUs,"About Us",By.xpath(".//h1"),"About Us");
				fnVerifySupportLink(lnkFooterFAQ,"FAQ",By.xpath(".//h2"),"Support Center");
				fnVerifySupportLink(lnkFooterPrivacyPolicy,"Privacy Policy",By.xpath(".//h1"),"Privacy Policy");
				fnVerifySupportLink(lnkFoterTermsOfUse,"Terms of Use",By.xpath(".//h1"),"GENERAL TERMS AND CONDITIONS FOR HOOQ");		
				Thread.sleep(5000);
			//Verify Hooq Footer Support Link	
				Reporter.reportStep("Verify Hooq Footer Support Link");
				fnVerifySupportLink(lnkFooterAboutUs,"About Us",By.xpath(".//h1"),"About Us");
				fnVerifySupportLink(lnkFooterFAQ,"FAQ",By.xpath(".//h2"),"Support Center");
				fnVerifySupportLink(lnkFooterPrivacyPolicy,"Privacy Policy",By.xpath(".//h1"),"Privacy Policy");
				fnVerifySupportLink(lnkFoterTermsOfUse,"Terms of Use",By.xpath(".//h1"),"GENERAL TERMS AND CONDITIONS FOR HOOQ");		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void fnVerifyHooqSupportPageDetails() throws Throwable
	{
		Reporter.reportStep("Verify HOOQ Support Page Details");
		String strPageTitle=getText(lblSuportTitle,"Hooq Support Title");
		String strAboutUs=getText(lblAboutUs,"About Us");
		String strFAQ=getText(lblFAQ,"FAQ");
		String strPrivacyPolicy=getText(lblPrivacy,"Privacy Policy");
		String strTermsofUse=getText(lblTermsofUse,"Terms of Use");
		String strNeedHelp=getText(lblNeedHelp,"Need Help");
		String strNeedHelpInfo=getText(lblNeedHelpDetails,"Need Help Info");
		boolean blnContactus=driver.findElement(btnContactUs).isDisplayed();
		System.out.println(strPageTitle);
		System.out.println(strAboutUs);
		System.out.println(strFAQ);
		System.out.println(strPrivacyPolicy);
		System.out.println(strTermsofUse);
		System.out.println(strNeedHelp);
		System.out.println(strNeedHelpInfo);
		System.out.println(blnContactus);
		if(strPageTitle.contains("HOOQ SUPPORT CENTRE"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "HOOQ Support Page is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "HOOQ Support Page is not Displayed.");
		}
		if(strAboutUs.equals("About Us"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "About Us is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "About Us is not Displayed.");
		}
		if(strFAQ.equals("FAQ"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "FAQ is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "FAQ is not Displayed.");
		}
		if(strPrivacyPolicy.equals("Privacy Policy"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "Privacy Policy is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "Privacy Policy is not Displayed.");
		}
		if(strTermsofUse.equals("Terms of Use"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "Terms of Use is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "Terms of Use is not Displayed.");
		}
		if(strNeedHelp.equals("Need help?"))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "Need help? is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "Need help? is not Displayed.");
		}
		if(strNeedHelpInfo.equals("If you were not able to find what you were looking for, drop us an email and we will respond as soon as possible."))
		{
			Reporter.SuccessReport("Verify HOOQ Support Page Details", "Need help? Info is Displayed Successfully");
		}
		else
		{
			Reporter.failureReportContinue("Verify HOOQ Support Page Details", "Need help? Info is not Displayed.");
		}	
	}
	
	public static void fnVerifySupportLink(By loc,String LinkName,By VerifyText,String strExpected) throws Throwable
	{
		try
		{
			String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
			click(loc,LinkName);
			Thread.sleep(5000);
			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			String strText=driver.getPageSource();
			//System.out.println(strText);
			if(strText.contains(strExpected))
			{
				Reporter.SuccessReport("Verify Support Link Page", strExpected + " is displayed successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Support Link Page",strExpected + " is not displayed successfully");
			}
			// Close the new window, if that window no more required
			driver.close();
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			
		}		
	}
	
	
	public static boolean fnVerifyWatchLaterHomeScreen()throws Throwable
	{
		boolean res = false;
		try {	

			//Clear List of Watch Later
				fnClearListWatchlater();
			//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie or Show Watch Later");
				//Get the Count of the Title
				WebElement ele=driver.findElement(By.xpath("//*[@id='titles-mount']/div[2]/div/div[2]"));
				String strMovieName=ele.findElement(By.className("galleria-thumbnail-title")).getText();
				System.out.println("Movie Name ==> " + strMovieName);
				mouseHoverByJavaScript(ele);
				mouseHoverByJavaScript(ele);
				Thread.sleep(5000);
				WebElement eleSeeMore=ele.findElement(By.className("more-info"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", eleSeeMore);
				Thread.sleep(5000);
				//Make it as Faviorite and Watch Later
				JSClick(discoverwatchlater, "Watch Later");
				Thread.sleep(10000);
				Reporter.reportStep("Verify Movie or Show Watch Later");
				JSClick(HomePageLocators.imgLogo,"Home Page");
				navigateToWatchlater();
				Thread.sleep(15000);
				//Get the Count of the Title
				if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
				{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				boolean blnFound=false;
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify Watch Later From Home Page", strMovieName +" successfully displayed in Watch Later");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify Watch Later From Home Page", strMovieName +" is not displayed in Watch Later");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
				}
				else
				{
					Reporter.failureReportContinue("Verify Watch Later From Home Page", strMovieName +" is not displayed in Watch Later");
					System.out.println(strMovieName + " is not added to Watch Later ");
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean fnVerifyFavioritesHomeScreen()throws Throwable
	{
		boolean res = false;
		try {		
				//Clear Favirote List
				fnClearListFaviorite();
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie or Show Faviorites");
				//Get the Count of the Title
				WebElement ele=driver.findElement(By.xpath("//*[@id='titles-mount']/div[2]/div/div[2]"));
				String strMovieName=ele.findElement(By.className("galleria-thumbnail-title")).getText();
				System.out.println("Movie Name ==> " + strMovieName);
				mouseHoverByJavaScript(ele);
				mouseHoverByJavaScript(ele);
				Thread.sleep(5000);
				WebElement eleSeeMore=ele.findElement(By.className("more-info"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", eleSeeMore);
				Thread.sleep(5000);
				//Make it as Faviorite and Watch Later
				Thread.sleep(10000);
				JSClick(discoverFavorite, "Faviorites");
				Thread.sleep(20000);
				Reporter.reportStep("Verify Movie or Show Faviorites");
				navigateToFavorite();
				Thread.sleep(15000);
				//Get the Count of the Title
				if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
				{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				boolean blnFound=false;
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify Faviorite From Home Page", strMovieName +" successfully displayed in Faviorites");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
				}
				else
				{
					Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean fnVerifyFavoritesContenDetails(String strMovieName,String strType)throws Throwable
	{
		boolean res = false;
		try {		
				//Clear Favirote List
				fnClearListFaviorite();
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie / TVSeries Faviorites from Content Details Page");
				//Make Movie or TVShow Favorite
				boolean blnSearch=SearchSpecificItem(strMovieName);
				System.out.println(blnSearch);
				if(blnSearch)
				{
					Thread.sleep(5000);
					JSClick(HomePageLocators.cdFavorite,"Content Details Favorites");
					Reporter.reportStep("Verify Movie or Show Faviorites");
					navigateToFavorite();
					Thread.sleep(20000);
					//Get the Count of the Title
					if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
					{
						List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
						boolean blnFound=false;
						String strFavMovieName="";
						for(int i=0;i<eleItemverify.size();i++)
						{
							strFavMovieName=eleItemverify.get(i).getText();
							if(strMovieName.equals(strFavMovieName))
							{
								blnFound=true;
								break;
							}
						}
						if(blnFound)
						{
							Reporter.SuccessReport("Verify Faviorite From Content Details Page for " + strType, strMovieName +" successfully displayed in Faviorites");
							System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
						}
						else
						{
							Reporter.failureReportContinue("Verify Faviorite From Content Details Page for " + strType, strMovieName +" is not displayed in Faviorites");
							System.out.println("Movie Name Not Match ==> " + strMovieName);
						}	
					}
					else
					{
						Reporter.failureReportContinue("Verify Faviorite From Content Details Page " + strType, strMovieName +" is not displayed in Faviorites");
					}
					}
					else
					{
						Reporter.failureReportContinue("Verify Faviorite From Content Details Page for " + strType, strMovieName +" is not Searched to Make in Faviorites");
					}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return res;
	}
	public static boolean fnVerifyWatchLaterContenDetails(String strMovieName,String strType)throws Throwable
	{
		boolean res = false;
		try {		
				//Clear Favirote List
			//Clear List of Watch Later
				fnClearListWatchlater();
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie / TVSeries Watch Later from Content Details Page");
				//Make Movie or TVShow Favorite
				boolean blnSearch=SearchSpecificItem(strMovieName);
				System.out.println(blnSearch);
				if(blnSearch)
				{
					Thread.sleep(5000);
					JSClick(HomePageLocators.cdWatchLater,"Content Details Watch Later");
					Reporter.reportStep("Verify Movie or Show Watch Later");
					navigateToWatchlater();
					Thread.sleep(20000);
					//Get the Count of the Title
					if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
					{
						List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
						boolean blnFound=false;
						String strFavMovieName="";
						for(int i=0;i<eleItemverify.size();i++)
						{
							strFavMovieName=eleItemverify.get(i).getText();
							if(strMovieName.equals(strFavMovieName))
							{
								blnFound=true;
								break;
							}
						}
						if(blnFound)
						{
							Reporter.SuccessReport("Verify Watch Later From Content Details Page for " + strType, strMovieName +" successfully displayed in Watch Later");
							System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
						}
						else
						{
							Reporter.failureReportContinue("Verify Watch Later From Content Details Page for " + strType, strMovieName +" is not displayed in Watch Later");
							System.out.println("Movie Name Not Match ==> " + strMovieName);
						}	
					}
					else
					{
						Reporter.failureReportContinue("Verify Watch Later From Content Details Page " + strType, strMovieName +" is not displayed in Watch Later");
					}
					}
					else
					{
						Reporter.failureReportContinue("Verify Watch Later From Content Details Page for " + strType, strMovieName +" is not Searched to Make in Watch Later");
					}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return res;
	}
	
	public static boolean fnVerifyHistory(String strMovieName,String strType) throws Throwable
	{
		boolean blnFound=false;
		try
		{
			Reporter.reportStep("Verify Movie or Show History");
			navigateToHistory();
			Thread.sleep(20000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify History for " + strType, strMovieName +" successfully displayed in History");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify History for " + strType, strMovieName +" is not displayed in History");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
			}
			else
			{
				Reporter.failureReportContinue("Verify History From Content Details Page " + strType, strMovieName +" is not displayed in History");
			}
		
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnFound;
		
		
		
	}
	
	
	public static boolean fnVerifyFavoritesFromPlay(String strMovieName,String strType)throws Throwable
	{
		boolean res = false;
		try {		
				//Clear Favirote List
				fnClearListFaviorite();
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie / TVSeries Faviorites from Play Screen");
				//Make Movie or TVShow Favorite
				boolean blnSearch=false;
				if(strType.toLowerCase().equals("tvseries"))
				{
					blnSearch=browseSpecificEpisode(strMovieName,"1");
				}
				else
				{
					blnSearch=browseSpecificItem(strMovieName);	
				}				
				System.out.println(blnSearch);
				if(blnSearch)
				{
					Thread.sleep(5000);
					JSClick(HomePageLocators.cdFavorite,"Play Screen Favorites");
					res=fnVerifyMovieInFavorite(strMovieName,strType);
					}
					else
					{
						Reporter.failureReportContinue("Verify Faviorite From Content Play Page for " + strType, strMovieName +" is not Searched to Make in Faviorites");
					}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return res;
	}
	
	public static boolean fnVerifyMovieInFavorite(String strMovieName, String strType) throws Throwable
	{
		boolean blnFound=false;
		try
		{
			Reporter.reportStep("Verify Movie or Show Favorites");
			navigateToFavorite();
			Thread.sleep(15000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify Faviorite From Content Play Page for Rent" + strType, strMovieName +" successfully displayed in Faviorites");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify Faviorite From Content Play Page for " + strType, strMovieName +" is not displayed in Faviorites");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
			}
			else
			{
				Reporter.failureReportContinue("Verify Faviorite From Content Play Page " + strType, strMovieName +" is not displayed in Faviorites");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnFound;
	}
	
	
	public static boolean fnVerifyWatchLaterFromPlay(String strMovieName,String strType)throws Throwable
	{
		boolean res = false;
		try {		
				//Clear List of Watch Later
				fnClearListWatchlater();
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Make Movie / TVSeries Watch Later from Play Screen");
				//Make Movie or TVShow Favorite
				boolean blnSearch=false;
				if(strType.toLowerCase().equals("tvseries"))
				{
					blnSearch=browseSpecificEpisode(strMovieName,"1");
				}
				else
				{
					blnSearch=browseSpecificItem(strMovieName);	
				}				
				System.out.println(blnSearch);
				if(blnSearch)
				{
					Thread.sleep(5000);
					JSClick(HomePageLocators.cdWatchLater,"Play Screen Watch Later");
					Reporter.reportStep("Verify Movie or Show Watch Later");
					res=fnVerifyMovieInWatchLater(strMovieName,strType);
				}
				else
				{
					Reporter.failureReportContinue("Verify Watch Later From Content Play Page for " + strType, strMovieName +" is not Searched to Make in Watch Later");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return res;
	}
	
	public static boolean fnVerifyMovieInWatchLater(String strMovieName,String strType) throws Throwable
	{
		boolean blnFound=false;
		try
		{
			Reporter.reportStep("Verify Movie or Show Watch Later");
			navigateToWatchlater();
			Thread.sleep(15000);
			//Get the Count of the Title
			if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
			{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify Watch Later From Content Play Page for " + strType, strMovieName +" successfully displayed in Watch Later");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify Watch Later From Content Play Page for " + strType, strMovieName +" is not displayed in Watch Later");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
			}
			else
			{
				Reporter.failureReportContinue("Verify Watch Later From Content Play Page Rent", strMovieName +" is not displayed in Watch Later");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return blnFound;
	}
	
	
	public static boolean fnVerifyFavioritesSearch(String strMovieName)throws Throwable
	{
		boolean res = false;
		try {		
				Reporter.reportStep("Make Movie or Show Faviorites From Search");
				//Search The Movie and make it faviorite
				Thread.sleep(10000);
				JSClick(discoverFavorite, "Faviorites");
				Thread.sleep(20000);
				Reporter.reportStep("Verify Movie or Show Faviorites");
				navigateToFavorite();
				Thread.sleep(5000);
				//Get the Count of the Title
				if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
				{
				List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
				boolean blnFound=false;
				String strFavMovieName="";
				for(int i=0;i<eleItemverify.size();i++)
				{
					strFavMovieName=eleItemverify.get(i).getText();
					if(strMovieName.equals(strFavMovieName))
					{
						blnFound=true;
						break;
					}
				}
				if(blnFound)
				{
					Reporter.SuccessReport("Verify Faviorite From Home Page", strMovieName +" successfully displayed in Faviorites");
					System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
				}
				else
				{
					Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
					System.out.println("Movie Name Not Match ==> " + strMovieName);
				}	
				}
				else
				{
					Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnVerifyPlayerSettings(String strShowName,String strShowType,String strEpisodeNo,String strUserType) throws Throwable
	{
		boolean res = false;
		try {	
			if(strUserType.equals("LAPSED"))
			{
			//Search Show Epesode and Play
				if(strShowType.equalsIgnoreCase("TVSeries"))
				{
					browseSpecificEpisode(strShowName,strEpisodeNo);
				}
				else
				{
					//Search Movie Name
					browseSpecificItem(strShowName);
				}
				if(strEpisodeNo.equals("1"))
				{
					//Click on Play or Continue Watching
					verifyPlaybackOfItem(strShowName, "Lapsed1stEpisode");
				}
				else
				{
					//Click on Play or Continue Watching
						verifyPlaybackOfItem(strShowName, strUserType);	
				}
			}
			else
			{
				//Search Show Epesode and Play
				if(strShowType.equalsIgnoreCase("TVSeries"))
				{
					browseSpecificEpisode(strShowName,strEpisodeNo);
				}
				else
				{
					//Search Movie Name
					browseSpecificItem(strShowName);
				}
				verifyPlaybackOfItem(strShowName, strUserType);	
			}	
			if(strUserType.equals("LAPSED")==false)
			{
			//Verify the Episode List
				verifyPlaybackSettingDetails();
				fnVerifyPlayPause();				
				if(strShowType.equalsIgnoreCase("TVSeries"))
				{
					//Verify Episode List
						verifyPlaybackEpisode();
					if(strUserType.equals("LAPSED")==false)
					{
					//Verify Next Episode
						verifyNextPreviousEpisode();
					}
				}
				//Verify Maximize and Minimize of Player
				verifyMaximizeMinimize();
			}
			else
			{
				if(strEpisodeNo.equals("1"))
				{
					//Verify the Episode List
					verifyPlaybackSettingDetails();
					fnVerifyPlayPause();				
					if(strShowType.equalsIgnoreCase("TVSeries"))
					{
						//Verify Episode List
							verifyPlaybackEpisode();
						if(strUserType.equals("LAPSED")==false)
						{
						//Verify Next Episode
							verifyNextPreviousEpisode();
						}
					}
					//Verify Maximize and Minimize of Player
					verifyMaximizeMinimize();
				}
			}			
			//Click on Home
			JSClick(HomePageLocators.imgLogo,"Home Page");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPlaybackEpisode() throws Throwable
	{
		boolean res = false;
		try {	
				Reporter.reportStep("Verify Episode List");
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerEpisode,"Episode");
			//Verify the Episode List Displayed in the TV Series
			List<WebElement> eleEpisodeList=driver.findElement(playerEpisodeList).findElements(By.className("episode-item-name"));
			for(int i=0;i<eleEpisodeList.size();i++)
			{
				System.out.println(eleEpisodeList.get(i).getText());
			}
			if(eleEpisodeList.size() > 1)
			{
				Reporter.SuccessReport("Verify Playback Episode List", "Episode list successfully displayed");
			}
			else
			{
				Reporter.failureReportContinue("Verify Playback Episode List", "Episode list not successfully displayed");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnMoveNextEpisode() throws Throwable
	{
		boolean res = false;
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerEpisodeNext,"Next Episode");				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnMovePreviousEpisode() throws Throwable
	{
		boolean res = false;
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerEpisodePrevious,"Previous Episode");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnPlayShow() throws Throwable
	{
		boolean res = false;
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerEpisodePlay,"Play Episode");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnPauseShow() throws Throwable
	{
		boolean res = false;
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerEpisodepause,"Pause Episode");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyMaximizeMinimize() throws Throwable
	{
		boolean res = false;
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerMaximize,"Maximize the Video");
				if(isVisible(playerMinimize,"Maximize"))
				{
					System.out.println("Video Maximized");
					Reporter.SuccessReport("Verify Maximize Video", "Video is Maximized successfully");
				}
				else
				{
					System.out.println("Video not Maximized");
					Reporter.failureReportContinue("Verify Maximize Video", "Video is not Maximized successfully");
				}
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerMinimize,"Maximize the Video");
				if(isVisible(playerMaximize,"Maximize"))
				{
					System.out.println("Video Minimized");
					Reporter.SuccessReport("Verify Minimized Video", "Video is Minimized successfully");
				}
				else
				{
					System.out.println("Video notMinimized");
					Reporter.failureReportContinue("Verify Minimized Video", "Video is not Minimized successfully");
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	public static boolean verifyNextPreviousEpisode() throws Throwable
	{
		boolean res = false;
		try {	
			//Get The First Episode Details 
			String strFirstEpisode=fngetShowDetails();
			System.out.println(strFirstEpisode);
			//Click on Player Next Button
			fnMoveNextEpisode();
			String strNextEpisode=fngetShowDetails();
			System.out.println(strNextEpisode);
			if(!strFirstEpisode.equals(strNextEpisode))
			{
				System.out.println("Move successfully to Next Episode");
				Reporter.SuccessReport("Verify Next Episode Playback", "Next Episode is played successfully");
			}
			else
			{
				System.out.println("Not Move successfully to Next Episode");
				Reporter.failureReportContinue("Verify Next Episode Playback", "Next Episode is not played successfully");
			}
			fnMovePreviousEpisode();
			String strPreviousEpisode=fngetShowDetails();
			if(!strPreviousEpisode.equals(strNextEpisode))
			{
				System.out.println("Move Successfully to Previous Episode");
				Reporter.SuccessReport("Verify Previous Episode Playback", "Previous Episode is played successfully");
			}			
			else
			{
				System.out.println(" Not Move Successfully to Previous Episode");
				Reporter.failureReportContinue("Verify Previous Episode Playback", "Previous Episode is not played successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean fnVerifyPlayPause() throws Throwable
	{
		boolean res = false;
		try {	
			fnPauseShow();
			String currentPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
			Thread.sleep(20000);
			mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			String newPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
			if(currentPlayTimeLabel.equals(newPlayTimeLabel))
			{
				System.out.println("Video is Pause");
				Reporter.SuccessReport("Verify Video is Pause", "Video is Pause successfully");
			}
			else
			{
				System.out.println("Video is not Pause");
				Reporter.failureReportContinue("Verify Video is Pause", "Video is not Pause successfully");
			}
			fnPlayShow();
			Thread.sleep(20000);
			mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			String newPlayTimeLabelPlay = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
			if(!newPlayTimeLabelPlay.equals(newPlayTimeLabel))
			{
				System.out.println("Video is Playing");
				Reporter.SuccessReport("Verify Video is Playing", "Video is Playing successfully");
			}
			else
			{
				System.out.println("Video is not  Playing");
				Reporter.failureReportContinue("Verify Video is Playing", "Video is not Playing successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public static boolean verifyPlaybackSettingDetails() throws Throwable
	{
		boolean res = false;
		try {	
				Reporter.reportStep("Verify Playback Setting Details");
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
			//Click on Episode
				JSClick(playerSettings,"Player Settings");
			//Verify the Episode List Displayed in the TV Series
				WebElement eleSettings=driver.findElement(playerSettingDetails);
				List<WebElement> eleList=eleSettings.findElements(By.tagName("h4"));
				System.out.println("Total Count of Settings are ==> " + eleList.size());
				if(eleList.size()>=2)
				{
					Reporter.SuccessReport("Verify Playback Settings", "Playback Settings is displayed successfully");
					List<WebElement> eleSubSettings=eleSettings.findElements(By.tagName("ul"));
					if(eleList.get(0).getText().equals("Subtitles"))
					{
						Reporter.SuccessReport("Verify Playback Settings - Subtitles", "Playback Settings - Subtitles is displayed successfully");
						List<WebElement> eleSubTitle=eleSubSettings.get(0).findElements(By.tagName("li"));
						if(eleSubTitle.size() > 1)
						{
							Reporter.SuccessReport("Verify Playback Settings - Subtitles Language", "Playback Settings - Subtitles Language is displayed successfully");
							for(int i=0;i<eleSubTitle.size();i++)
							{
								System.out.println(eleSubTitle.get(i).getText());
							}
						}
						else
						{
							Reporter.failureReportContinue("Verify Playback Settings - Subtitles Language", "Playback Settings - Subtitles Language is not displayed successfully");
						}
						
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback Settings - Subtitles", "Playback Settings - Subtitles is not displayed successfully");
					}
					if(eleList.get(1).getText().equals("Audio Track"))
					{
						Reporter.SuccessReport("Verify Playback Settings - Audio Track", "Playback Settings - Audio Track is displayed successfully");
					/*	List<WebElement> eleSubAudioTrack=eleSubSettings.get(1).findElements(By.tagName("li"));
						if(eleSubAudioTrack.size() > 1)
						{
							Reporter.SuccessReport("Verify Playback Settings - Audio Track Language", "Playback Settings - Audio Track Language is displayed successfully");
							for(int i=0;i<eleSubAudioTrack.size();i++)
							{
								System.out.println(eleSubAudioTrack.get(i).getText());
							}
						}
						else
						{
							Reporter.failureReport("Verify Playback Settings - Audio Track Language", "Playback Settings - Audio Track Language is not displayed successfully");
						}*/
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback Settings - Audio Track", "Playback Settings - Audio Track is not displayed successfully");
					}
					if(eleList.get(2).getText().equals("Quality"))
					{
						Reporter.SuccessReport("Verify Playback Settings - Quality", "Playback Settings - Quality is displayed successfully");
						List<WebElement> eleSubQuality=eleSubSettings.get(2).findElements(By.tagName("li"));
						if(eleSubQuality.size() > 1)
						{
							Reporter.SuccessReport("Verify Playback Settings - Quality Type", "Playback Settings - Quality Type is displayed successfully");
							for(int i=0;i<eleSubQuality.size();i++)
							{
								System.out.println(eleSubQuality.get(i).getText());
							}
						}
						else
						{
							Reporter.failureReportContinue("Verify Playback Settings - Quality Type", "Playback Settings - Quality Type is not displayed successfully");
						}
					}
					else
					{
						Reporter.failureReportContinue("Verify Playback Settings - Quality", "Playback Settings Quality is not displayed successfully");
					}
				}
				else
				{
					Reporter.failureReportContinue("Verify Playback Settings", "Playback Settings is not displayed successfully");
				}
				
				for(int i=0;i<eleList.size();i++)
				{
					System.out.println(eleList.get(i).getText());
				}
			
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static String fngetShowDetails() throws Throwable
	{
		String strShowDetails="";
		try {	
				Thread.sleep(20000);
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
				List<WebElement> eleList=driver.findElement(playerEpisodeDetails).findElements(By.tagName("span"));
				if(eleList.size()>0)
				{
					for(int i=0;i<eleList.size();i++)
					{
						strShowDetails=strShowDetails + eleList.get(i).getText();
					}
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strShowDetails;
	}
	
	public static String fnVerifySubscription() throws Throwable
	{
		String strShowDetails="";
		try {	
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strShowDetails;
	}
	public static void fnVerifyPayTM(String strPlanName,String strPlanValue,boolean blnAutoRenew,String strUserType) throws Throwable
	{
		try
		{
			Reporter.reportStep("Verify Net Banking Payment with PayTM  with User Type  " + strUserType + "  Plan Type " +strPlanName +" Auto Renew " + blnAutoRenew);
			//Navigate to Subscription
				navigateToSubscription();
			//Click on Manage Subscription
				click(HeaderLocators.btnPlanPageManage,"Manage");
			//Select Plan Based on the Plan Type	
				if(strPlanName.equals("7 Days"))
				{
					//Select 7 Days Play
						click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
				}
				else if(strPlanName.equals("30 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
				} 
				else if(strPlanName.equals("90 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
				} 
				else if(strPlanName.equals("180 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
				}
				else if(strPlanName.equals("360 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
				}
			//Click on Net Banking
				boolean blnPayment=fnSelectPaymentMethod("PayTM");
				if(blnPayment)
				{
					String winHandleBefore="";
					try
					{
						winHandleBefore = driver.getWindowHandle();
					//Click on Make Payment
						click(HeaderLocators.btnMakePayment,"Make Payment");
						Thread.sleep(5000);
					//Verify Popup
						// Switch to new window opened
						for(String winHandle : driver.getWindowHandles()){
							driver.switchTo().window(winHandle);
						}
						//Perform the actions on new window
						//Verify Net Banking Payment Details
						Thread.sleep(15000);
						String strPaymentWindowTitle=driver.getTitle();
						System.out.println(strPaymentWindowTitle);
						if(strPaymentWindowTitle.contains("Paytm Secure Online Payment Gateway"))
						{
							Reporter.SuccessReport("Verify PayTM Payment Gateway","PayTM Payment Gateway is display Successfuly  for " + strPlanValue );
							WebElement elem=driver.findElement(By.xpath("//*[@id='payment-details']"));
							String strAmount=elem.getAttribute("data-amount");						
							System.out.println("Value Available in PayTM " + strAmount);
							if(strAmount.replace(",","").contains(strPlanValue))
							{
								System.out.println("Value Match" + strPlanValue);	
								Reporter.SuccessReport("Verify PayTM Amount", strPlanValue + " is display Successfuly");
							}
							else
							{
								System.out.println("Value Not Match" + strPlanValue);
								Reporter.failureReportContinue("Verify PayTM Amount", strPlanValue + " is not display Successfuly");
							}
						}
						else
						{
							Reporter.failureReportContinue("Verify PayTM Payment Gateway","PayTM Payment Gateway is not display Successfuly  for " + strPlanValue );
						}											
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();					
					}				
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fnVerifyDebitCreditCard(String strPlanName,String strPlanValue,boolean blnAutoRenew,String strUserType,String strCardType) throws Throwable
	{
		try
		{
			String strCardDetails="";
			String strCardPaymentInfo="";
			String strMakePaymentButton="";
			if(strCardType.equals("credit"))
			{
				Reporter.reportStep("Verify Payment with Credit Card  with User Type  " + strUserType + "  Plan Type " +strPlanName +" Auto Renew " + blnAutoRenew);
				strCardDetails="Credit";
				strCardPaymentInfo="CREDIT CARD DETAILS";
				strMakePaymentButton="creditcard_btn";
			}
			else
			{
				Reporter.reportStep("Verify Pyment with Debit Card  with User Type  " + strUserType + "  Plan Type " +strPlanName +" Auto Renew " + blnAutoRenew);
				strCardDetails="Debit";
				strCardPaymentInfo="DEBIT CARD DETAILS";
				strMakePaymentButton="debitcard_btn";
			}
			//Navigate to Subscription
				navigateToSubscription();
			//Click on Manage Subscription
				click(HeaderLocators.btnPlanPageManage,"Manage");
			//Select Plan Based on the Plan Type	
				if(strPlanName.equals("7 Days"))
				{
					//Select 7 Days Play
						click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
				}
				else if(strPlanName.equals("30 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
				} 
				else if(strPlanName.equals("90 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
				} 
				else if(strPlanName.equals("180 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
				}
				else if(strPlanName.equals("360 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
				}
			//Click on Net Banking
				boolean blnPayment=false;
				String strAmountXPath="";
				if(strCardType.equals("credit")==false)
				{
					blnPayment=fnSelectPaymentMethod("Debit Card");
					strAmountXPath="//*[@id='debitcard_form']/div/div[1]/div[2]/p"; 
					
				}
				else
				{
					blnPayment=fnSelectPaymentMethod("Credit Card");					
					strAmountXPath="//*[@id='creditcard_form']/div/div[1]/div[2]/p";
				}
				if(blnPayment)
				{
					String winHandleBefore="";
					try
					{
						winHandleBefore = driver.getWindowHandle();
					//Click on Make Payment
						click(HeaderLocators.btnMakePayment,"Make Payment");
						Thread.sleep(15000);
					//Verify Popup
						// Switch to new window opened
						for(String winHandle : driver.getWindowHandles()){
							driver.switchTo().window(winHandle);
						}
						//Perform the actions on new window
						//Verify Net Banking Payment Details
						Thread.sleep(15000);
						String strPaymentWindowTitle=driver.findElement(HeaderLocators.lblCreditDebitInfo).getText();
						System.out.println(strPaymentWindowTitle);
						if(strPaymentWindowTitle.contains(strCardPaymentInfo))
						{
							Reporter.SuccessReport("Verify "+ strCardDetails + " Card Payment Gateway", strCardDetails + " Card Payment Gateway is display Successfuly  for " + strPlanValue );
							String strAmount=driver.findElement(By.xpath(strAmountXPath)).getText();
							System.out.println("Value Available in  " + strAmount);
							if(strAmount.replace(",", "").contains(strPlanValue))
							{
								System.out.println("Value Match" + strPlanValue);	
								Reporter.SuccessReport("Verify "+ strCardDetails+" Amount", strPlanValue + " is display Successfuly");
							}
							else
							{
								System.out.println("Value Not Match" + strPlanValue);
								Reporter.failureReportContinue("Verify "+ strCardDetails+" Amount", strPlanValue + " is not display Successfuly");
							}	
							type(HeaderLocators.inpCardNumber, "3433322344554437", "Card Number");
							type(HeaderLocators.inpNameOnCard, "Hooq User " +strCardDetails, "Card User");
							type(HeaderLocators.inpExpiryDate, "1224", "Card Expire Date");
							type(HeaderLocators.inpCVV, "122", "Card CVV Number");
							Thread.sleep(10000);
							boolean blnMakePayment=isEnabled(By.name(strMakePaymentButton), "Make Payment");
							System.out.println(blnMakePayment);
							if(blnMakePayment)
							{
								Reporter.SuccessReport("Verify Make Payment Button with "+ strCardDetails+" Card", "Make Payment Button with "+ strCardDetails +" is Enabled  Successfuly");
							}
							else
							{
								Reporter.failureReportContinue("Verify Make Payment Button with "+ strCardDetails+" Card", "Make Payment Button with "+ strCardDetails +" is not Enabled  Successfuly");
							}						
						}
						else
						{
							Reporter.failureReportContinue("Verify "+ strCardDetails + " Card Payment Gateway", strCardDetails + " Card Payment Gateway is not display Successfuly  for " + strPlanValue );
						}											
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();					
					}				
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void fnVerifyDebitCreditCardEntry(String strPlanName,String strPlanValue,boolean blnAutoRenew,String strUserType,String strCardType,String strCardNumber,String strNameOnCard,String strExpiryDate,String strCVVNumber,String strErrorType) throws Throwable
	{
		try
		{
			String strCardDetails="";
			String strCardPaymentInfo="";
			String strMakePaymentButton="";
			if(strCardType.equals("credit"))
			{
				Reporter.reportStep("Verify CREDIT CARD Payment with Credit Card  with User Type  " + strUserType + "  Plan Type " +strPlanName +" Auto Renew " + blnAutoRenew);
				strCardDetails="Credit";
				strCardPaymentInfo="CREDIT CARD DETAILS";
				strMakePaymentButton="creditcard_btn";
			}
			else
			{
				Reporter.reportStep("Verify DEBIT CARD Payment with Credit Card  with User Type  " + strUserType + "  Plan Type " +strPlanName +" Auto Renew " + blnAutoRenew);
				strCardDetails="Debit";
				strCardPaymentInfo="DEBIT CARD DETAILS";
				strMakePaymentButton="debitcard_btn";
			}
			//Navigate to Subscription
				navigateToSubscription();
			//Click on Manage Subscription
				click(HeaderLocators.btnPlanPageManage,"Manage");
			//Select Plan Based on the Plan Type	
				if(strPlanName.equals("7 Days"))
				{
					//Select 7 Days Play
						click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
				}
				else if(strPlanName.equals("30 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
				} 
				else if(strPlanName.equals("90 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
				} 
				else if(strPlanName.equals("180 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
						click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
				}
				else if(strPlanName.equals("360 Days"))
				{
					if(!blnAutoRenew)
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
					}
					//Select 7 Days Play
						click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
				}
			//Click on Net Banking
				boolean blnPayment=false;
				String strAmountXPath="";
				if(!blnAutoRenew)
				{
					blnPayment=fnSelectPaymentMethod("Debit Card");
					strAmountXPath="//*[@id='debitcard_form']/div/div[1]/div[2]/p"; 
					
				}
				else
				{
					blnPayment=fnSelectPaymentMethod("Credit Card");					
					strAmountXPath="//*[@id='creditcard_form']/div/div[1]/div[2]/p";
				}
				if(blnPayment)
				{
					String winHandleBefore="";
					try
					{
						winHandleBefore = driver.getWindowHandle();
					//Click on Make Payment
						click(HeaderLocators.btnMakePayment,"Make Payment");
						Thread.sleep(5000);
					//Verify Popup
						// Switch to new window opened
						for(String winHandle : driver.getWindowHandles()){
							driver.switchTo().window(winHandle);
						}
						//Perform the actions on new window
						//Verify Net Banking Payment Details
						Thread.sleep(10000);
						String strPaymentWindowTitle=driver.findElement(HeaderLocators.lblCreditDebitInfo).getText();
						System.out.println(strPaymentWindowTitle);
						if(strPaymentWindowTitle.contains(strCardPaymentInfo))
						{
							Reporter.SuccessReport("Verify "+ strCardDetails + " Card Payment Gateway", strCardDetails + " Card Payment Gateway is display Successfuly  for " + strPlanValue );
							String strAmount=driver.findElement(By.xpath(strAmountXPath)).getText();
							System.out.println("Value Available in  " + strAmount);
							if(strAmount.replace(",", "").contains(strPlanValue))
							{
								System.out.println("Value Match" + strPlanValue);	
								Reporter.SuccessReport("Verify "+ strCardDetails+" Amount", strPlanValue + " is display Successfuly");
							}
							else
							{
								System.out.println("Value Not Match" + strPlanValue);
								Reporter.failureReportContinue("Verify "+ strCardDetails+" Amount", strPlanValue + " is not display Successfuly");
							}	
							type(HeaderLocators.inpCardNumber,strCardNumber, "Card Number");
							type(HeaderLocators.inpNameOnCard, strNameOnCard +strCardDetails, "Card User");
							type(HeaderLocators.inpExpiryDate, strExpiryDate, "Card Expire Date");
							type(HeaderLocators.inpCVV, strCVVNumber, "Card CVV Number");
							boolean blnMakePayment=isEnabled(By.name(strMakePaymentButton), "Make Payment");
							if(blnMakePayment)
							{								
								Reporter.SuccessReport("Verify Make Payment Button with "+ strCardDetails+" Card", "Make Payment Button with "+ strCardDetails +" is Enabled  Successfuly");
								JSClick(By.name(strMakePaymentButton), "Done");							
								Thread.sleep(10000);
								if(strErrorType.equals("Card_No_Wrong"))
								{
									String strText=driver.findElement(By.xpath("//*[@id='credit_card']/div[2]/div[1]/label[2]/p")).getText();
									if(strText.equals("Please enter a valid card number."))
									{
										System.out.println("Error Message is Correct ==> " + strText);
									}
									else
									{
										System.out.println("Error Message is Incorrect  ==> "  + strText );
									}
								}
								else if(strErrorType.equals("Card_Name"))
								{
									String strText=driver.findElement(By.xpath("//*[@id='credit_card']/div[2]/div[2]/label[2]/p")).getText();
									if(strText.equals("Please enter a valid name."))
									{
										System.out.println("Error Message is Correct ==> " + strText);
									}
									else
									{
										System.out.println("Error Message is Incorrect  ==> "  + strText );
									}
								}
								else if(strErrorType.equals("Expiry_Date"))
								{
									String strText=driver.findElement(By.xpath("//*[@id='creditcardcvv']/div[1]/label[2]/p")).getText();
									if(strText.equals("Please enter valid expiry date."))
									{
										System.out.println("Error Message is Correct ==> " + strText);
									}
									else
									{
										System.out.println("Error Message is Incorrect  ==> "  + strText );
									}
								}	
								else if(strErrorType.equals("CVV"))
								{
									String strText=driver.findElement(By.xpath("//*[@id='creditcardcvv']/div[2]/label[2]/p")).getText();
									if(strText.equals("Please enter only digits for the CVV number."))
									{
										System.out.println("Error Message is Correct ==> " + strText);
									}
									else
									{
										System.out.println("Error Message is Incorrect  ==> "  + strText );
									}
								}	
								
								
								
							}
							else
							{
								Reporter.failureReportContinue("Verify Make Payment Button with "+ strCardDetails+" Card", "Make Payment Button with "+ strCardDetails +" is not Enabled  Successfuly");
							}		
											
						}
						else
						{
							Reporter.failureReportContinue("Verify "+ strCardDetails + " Card Payment Gateway", strCardDetails + " Card Payment Gateway is not display Successfuly  for " + strPlanValue );
						}											
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();					
					}				
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void fnVerifyNetBanking(String strPlanName,String strPlanValue,String strUserType) throws Throwable
	{
		try
		{
			String[] arrBankName=new String[48];
			arrBankName[0]="Andhra Bank<>Retail Signon<>.//*[@value=' Login ']";
			arrBankName[1]="Axis Bank<>Axis Bank Internet Banking<>.//*[@value='Login']";
			arrBankName[2]="Bank of Baharin and Kuwait<>Retail Signon<>.//*[@value=' Login ']";
			arrBankName[3]="Bank of Baroda Corporate<>Signon Option<>.//*[@src='Corporate.gif']";
			arrBankName[4]="Bank of Baroda Retail<>Signon Option<>.//*[@src='Retail.gif']";
			arrBankName[5]="Bank of India<>Bank of India Online e Payment<>.//*[@value='Click to RETURN']";
			arrBankName[6]="Bank of Maharashtra<>:: Welcome to Bank of Maharashtra - Internet Banking<>.//*[@alt='Login Retail']";
			arrBankName[7]="Catholic Syrian Bank<>CSB NetBanking<>.//*[@name='txtUser']";
			arrBankName[8]="Central Bank of India<>CBI Net Banking<>.//*[@value='Login']";
			arrBankName[9]="Citibank<>:: Citibank Payment Gateway ::<>.//*[@alt='Submit']";
			arrBankName[10]="City Union Bank<>CITY UNION BANK INTERNET BANKING<>.//*[@src='/image/PERSONAL.gif']";
			arrBankName[11]="Corporation Bank<>Welcome to CorpNet - Internet Retail Banking<>.//*[@class='style3']";
			arrBankName[12]="Cosmos Bank<>Cosmos Bank - Login<>.//*[@value='Continue']";
			arrBankName[13]="DBS Bank Ltd<>DBS iBanking<>.//*[@value='Submit']";
			arrBankName[14]="DCB BANK Business<>Welcome to DCB Corporate Banking<>.//*[@value='Sign In']";
			arrBankName[15]="DCB BANK Personal<>Welcome to DCB Online Banking<>.//*[@value='Sign In']";
			arrBankName[16]="Deutsche Bank<>Deutsche Bank<>.//*[@value='    Submit    ']";
			arrBankName[17]="Dhanlaxmi Bank<>Welcome to Dhanlaxmi Bank Payment Gateway<>.//*[@class='loginbutton']";
			arrBankName[18]="Federal Bank<>FedNet :Log in to Internet Banking<>.//*[@title='Sign In']";
			arrBankName[19]="HDFC Bank<>Welcome to HDFC Bank Direct Pay<>.//*[@alt='continue']";
			arrBankName[20]="ICICI Bank<>Log in to Internet Banking<>.//*[@title='Log In']";
			arrBankName[21]="IDBI Bank<>Shopping Signon<>.//*[@value=' Login ']";
			arrBankName[22]="Indian Bank<>Indian Bank<>.//*[@value='I  Agree']";
			arrBankName[23]="Indian Overseas Bank<>TPPEntry.jsp<>.//*[a='Individual Login']";
			arrBankName[24]="IndusInd Bank<>Welcome to IndusNet<>.//*[@value='login']";
			arrBankName[25]="Jammu and kashmir Bank<>J&K Bank E-Banking:Log in to Internet Banking<>.//*[@value='Log In']";
			arrBankName[26]="Karnataka Bank<>Karnataka Bank<>.//*[@name='CorporateSignonCorpId']";
			arrBankName[27]="Kotak Mahindra Bank<>Kotak Mahindra Bank - Payment Gateway<>.//*[@placeholder='Enter CRN or Customer ID']";
			arrBankName[28]="Lakshmi Vilas Bank<>The Lakshmi Vilas Bank Limited - Internet Banking<>.//*[@value='Retail Login']";
			arrBankName[29]="Oriental Bank Of Commerce<>OBC E-Banking: Log in to Internet Banking<>.//*[@value='LogIn']";
			arrBankName[30]="Punjab National Bank [Corporate]<>PNB E-Banking: Existing users login<>.//*[@value='Continue']";
			arrBankName[31]="Punjab National Bank [Retail]<>PNB E-Banking:Existing users login<>.//*[@value='Continue']";
			arrBankName[32]="Saraswat Bank<>Saraswat Online Banking<>.//*[@value='Submit']";
			arrBankName[33]="Shamrao Vithal Bank<>SVC Co-Operative Bank Ltd. - PIB<>.//*[@value='Continue']";
			arrBankName[34]="South Indian Bank<>South Indian Bank -Log in to Internet Banking<>.//*[@value='Log In']";
			arrBankName[35]="Standard Chartered Bank<>Standard Chartered Online Banking<>.//*[@value='Login']";
			arrBankName[36]="State Bank Of Bikaner and Jaipur<>State Bank Of Bikaner and Jaipur<>.//*[@value='Login']";
			arrBankName[37]="State Bank Of Hyderabad<>State Bank Of Hyderabad<>.//*[@value='Login']";
			arrBankName[38]="State Bank of India<>State Bank of India<>.//*[@value='Login']";
			arrBankName[39]="State Bank Of Mysore<>State Bank of Mysore<>.//*[@value='Login']";
			arrBankName[40]="State Bank of Patiala<>State Bank of Patiala<>.//*[@value='Login']";
			arrBankName[41]="State Bank of Travancore<>State Bank of Travancore<>.//*[@value='Login']";
			arrBankName[42]="Tamilnad Mercantile Bank<>Tamilnad Mercantile Bank Ltd.Log in to Internet Banking<>.//*[@value='Login']";
			arrBankName[43]="UCO Bank<>UCO Bank<>.//*[@value=' Login ']";
			arrBankName[44]="Union Bank of India<>Retail Signon.<>.//*[@value=' Login ']";
			arrBankName[45]="United Bank of India<>.Retail Signon.<>.//*[@value=' Login ']";
			arrBankName[46]="Vijaya Bank<>Vijaya Bank Net Banking:Log in to Internet Banking<>.//*[@value='Log In']";
			arrBankName[47]="YES Bank<>Welcome to YES Bank Internet Banking<>.//*[@alt='Login']";
			//Verify The Net Banking			
			System.out.println(arrBankName.length);
			int[] arrNetBank=fnGetCounterForInternetBanking(strUserType,strPlanName);
			for(int i=arrNetBank[0];i<=arrNetBank[1];i++)
			{
				System.out.println("******************************");				
				String strBankDetails=arrBankName[i];
				System.out.println(strBankDetails);
				String[] arrBankDetails=strBankDetails.split("<>");
				System.out.println(arrBankDetails.length);
				String strNetBankName=arrBankDetails[0];
				System.out.println("strNetBankName ==> "+strNetBankName);
				String strNetBankTitle=arrBankDetails[1];
				System.out.println("strNetBankTitle ==> " +strNetBankTitle);
				String strNetBankElement=arrBankDetails[2];				
				System.out.println("strNetBankElement ==>" +strNetBankElement);
				Reporter.reportStep("Verify Net Banking Payment with " + strNetBankName +" with User Type  " + strUserType + "  Plan Type " +strPlanName);
				//Navigate to Subscription
					navigateToSubscription();
				//Click on Manage Subscription
					click(HeaderLocators.btnPlanPageManage,"Manage");
				//Select Plan Based on the Plan Type	
					if(strPlanName.equals("7 Days"))
					{
						//Select 7 Days Play
							click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
					}
					else if(strPlanName.equals("30 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
					} 
					else if(strPlanName.equals("90 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
					} 
					else if(strPlanName.equals("180 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
					}
					else if(strPlanName.equals("360 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
					}
				//Click on Net Banking
					boolean blnPayment=fnSelectPaymentMethod("Netbanking");
					if(blnPayment)
					{
						String winHandleBefore="";
						try
						{
							winHandleBefore = driver.getWindowHandle();
						//Click on Make Payment
							click(HeaderLocators.btnMakePayment,"Make Payment");
							Thread.sleep(5000);
						//Verify Popup
							// Switch to new window opened
							for(String winHandle : driver.getWindowHandles()){
								driver.switchTo().window(winHandle);
							}
							//Perform the actions on new window
							//Verify Net Banking Payment Details
							String strPrice=driver.findElement(lblPrice).getText();
							System.out.println("Price Displayes ==> "+ strPrice);
							if(strPrice.replace(",", "").contains(strPlanValue))
							{
								Reporter.SuccessReport("Verify Net Banking Amount", strPlanValue + " is display Successfuly");
							}
							else
							{
								Reporter.failureReportContinue("Verify Net Banking Amount", strPlanValue + " is not display Successfuly");
							}
							//Select Net Banking Bank Name
							selectByValue(cboNetBank,strNetBankName,strNetBankName);
							Thread.sleep(5000);
							click(btnNetBankMakePayment,"Make Payment Net Bank");
							Thread.sleep(20000);							
							//Verify the Page Title
							String strBankTitle=driver.getTitle();
							System.out.println("Page Title Found ==> " +strBankTitle);
							System.out.println("Expected ==> " + strNetBankTitle);
							if(strBankTitle.toLowerCase().replace(" ", "").contains(strNetBankTitle.toLowerCase().replace(" ", "")))
							{
								System.out.println("Bank Title Match ==> " + strNetBankName);
								Reporter.SuccessReport("Verify Net Banking Page", strNetBankName + " Page for Payment is display Successfuly");
							}
							else
							{
								System.out.println("Bank Title Not  Match ==> " + strNetBankName);
								Reporter.failureReportContinue("Verify Net Banking Page", strNetBankName + " Page for Payment is not display Successfuly");
							}
							
							//Verify Element Present in the Bank Page
							/*if(isElementPresent(By.xpath(strNetBankElement),strNetBankName+ " Website"))
							{
								System.out.println("Bank Web site successfully Open " +strNetBankName );
							}
							else
							{
								System.out.println("Bank Web site not successfully Open " +strNetBankName );
							}*/					
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();}					
					}				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fnVerifyNetBankingSanity(String strPlanName,String strPlanValue,String strUserType) throws Throwable
	{
		try
		{
			String[] arrBankName=new String[2];
			arrBankName[0]="HDFC Bank<>Welcome to HDFC Bank Direct Pay<>.//*[@alt='continue']";
			arrBankName[1]="ICICI Bank<>Log in to Internet Banking<>.//*[@title='Log In']";
			//Verify The Net Banking			
			System.out.println(arrBankName.length);
			//int[] arrNetBank=fnGetCounterForInternetBanking(strUserType,strPlanName);
			for(int i=0;i<=1;i++)
			{
				System.out.println("******************************");				
				String strBankDetails=arrBankName[i];
				System.out.println(strBankDetails);
				String[] arrBankDetails=strBankDetails.split("<>");
				System.out.println(arrBankDetails.length);
				String strNetBankName=arrBankDetails[0];
				System.out.println("strNetBankName ==> "+strNetBankName);
				String strNetBankTitle=arrBankDetails[1];
				System.out.println("strNetBankTitle ==> " +strNetBankTitle);
				String strNetBankElement=arrBankDetails[2];				
				System.out.println("strNetBankElement ==>" +strNetBankElement);
				Reporter.reportStep("Verify Net Banking Payment with " + strNetBankName +" with User Type  " + strUserType + "  Plan Type " +strPlanName);
				//Navigate to Subscription
					navigateToSubscription();
				//Click on Manage Subscription
					click(HeaderLocators.btnPlanPageManage,"Manage");
				//Select Plan Based on the Plan Type	
					if(strPlanName.equals("7 Days"))
					{
						//Select 7 Days Play
							click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
					}
					else if(strPlanName.equals("30 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
					} 
					else if(strPlanName.equals("90 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
					} 
					else if(strPlanName.equals("180 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
					}
					else if(strPlanName.equals("360 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
					}
				//Click on Net Banking
					boolean blnPayment=fnSelectPaymentMethod("Netbanking");
					if(blnPayment)
					{
						String winHandleBefore="";
						try
						{
							winHandleBefore = driver.getWindowHandle();
						//Click on Make Payment
							click(HeaderLocators.btnMakePayment,"Make Payment");
							Thread.sleep(5000);
						//Verify Popup
							// Switch to new window opened
							for(String winHandle : driver.getWindowHandles()){
								driver.switchTo().window(winHandle);
							}
							//Perform the actions on new window
							//Verify Net Banking Payment Details
							String strPrice=driver.findElement(lblPrice).getText();
							System.out.println("Price Displayes ==> "+ strPrice);
							if(strPrice.replace(",", "").contains(strPlanValue))
							{
								Reporter.SuccessReport("Verify Net Banking Amount", strPlanValue + " is display Successfuly");
							}
							else
							{
								Reporter.failureReportContinue("Verify Net Banking Amount", strPlanValue + " is not display Successfuly");
							}
							//Select Net Banking Bank Name
							selectByValue(cboNetBank,strNetBankName,strNetBankName);
							Thread.sleep(5000);
							click(btnNetBankMakePayment,"Make Payment Net Bank");
							Thread.sleep(20000);							
							//Verify the Page Title
							String strBankTitle=driver.getTitle();
							System.out.println("Page Title Found ==> " +strBankTitle);
							System.out.println("Expected ==> " + strNetBankTitle);
							if(strBankTitle.toLowerCase().replace(" ", "").contains(strNetBankTitle.toLowerCase().replace(" ", "")))
							{
								System.out.println("Bank Title Match ==> " + strNetBankName);
								Reporter.SuccessReport("Verify Net Banking Page", strNetBankName + " Page for Payment is display Successfuly");
							}
							else
							{
								System.out.println("Bank Title Not  Match ==> " + strNetBankName);
								Reporter.failureReportContinue("Verify Net Banking Page", strNetBankName + " Page for Payment is not display Successfuly");
							}
							
							//Verify Element Present in the Bank Page
							/*if(isElementPresent(By.xpath(strNetBankElement),strNetBankName+ " Website"))
							{
								System.out.println("Bank Web site successfully Open " +strNetBankName );
							}
							else
							{
								System.out.println("Bank Web site not successfully Open " +strNetBankName );
							}*/					
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();}					
					}				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static int[] fnGetCounterForInternetBanking(String strUserType,String strPlanType)
	{
		int[] arr={1,1};
		try{
			if(strUserType.equals("FREE"))
			{
				if(strPlanType.equals("7 Days"))
				{
				  arr[0]=0;	
				  arr[1]=4;
				}
				else if(strPlanType.equals("30 Days"))
				{
					arr[0]=5;	
					arr[1]=9;
				} 
				else if(strPlanType.equals("90 Days"))
				{
					arr[0]=10;	
					arr[1]=13;
				} 
				else if(strPlanType.equals("180 Days"))
				{
					arr[0]=14;	
					arr[1]=16;
				}
				else if(strPlanType.equals("360 Days"))
				{
					arr[0]=17;	
					arr[1]=19;
				}
			}
			else if(strUserType.equals("LAPSED"))
			{
				if(strPlanType.equals("7 Days"))
				{
					arr[0]=20;	
					arr[1]=22;
				}
				else if(strPlanType.equals("30 Days"))
				{
					arr[0]=23;	
					arr[1]=25;
				} 
				else if(strPlanType.equals("90 Days"))
				{
					arr[0]=26;	
					arr[1]=28;
				} 
				else if(strPlanType.equals("180 Days"))
				{
					arr[0]=29;	
					arr[1]=31;
				}
				else if(strPlanType.equals("360 Days"))
				{
					arr[0]=32;	
					arr[1]=34;
				}
			}
			else if(strUserType.equals("ACTIVE"))
			{
				if(strPlanType.equals("7 Days"))
				{
					arr[0]=35;	
					arr[1]=37;
				}
				else if(strPlanType.equals("30 Days"))
				{
					arr[0]=38;	
					arr[1]=40;
				} 
				else if(strPlanType.equals("90 Days"))
				{
					arr[0]=41;	
					arr[1]=43;
				} 
				else if(strPlanType.equals("180 Days"))
				{
					arr[0]=44;	
					arr[1]=46;
				}
				else if(strPlanType.equals("360 Days"))
				{
					arr[0]=45;	
					arr[1]=47;
				}
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arr;
	}
	
	
	public static void fnVerifyCarrierBilling(String strPlanName,String strPlanValue,String strUserType) throws Throwable
	{
		try
		{
					Reporter.reportStep("Verify Carrier Billing Payment with  with User Type  " + strUserType + "  Plan Type " +strPlanName);
				//Navigate to Subscription
					navigateToSubscription();
				//Click on Manage Subscription
					click(HeaderLocators.btnPlanPageManage,"Manage");
				//Select Plan Based on the Plan Type	
					if(strPlanName.equals("7 Days"))
					{
						//Select 7 Days Play
							click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
					}
					else if(strPlanName.equals("30 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
					}					
				//Click on Net Banking
					boolean blnPayment=fnSelectPaymentMethod("Carrier Billing");
					if(blnPayment)
					{
						String winHandleBefore="";
						try
						{
							winHandleBefore = driver.getWindowHandle();
						//Click on Make Payment
							click(HeaderLocators.btnMakePayment,"Make Payment");
							Thread.sleep(5000);
						//Verify Popup
							// Switch to new window opened
							for(String winHandle : driver.getWindowHandles()){
								driver.switchTo().window(winHandle);
							}
							//Perform the actions on new window
							String strPaymentWindowTitle=driver.findElement(HeaderLocators.lblcarrierbillinginfo).getText();
							System.out.println(strPaymentWindowTitle);
							if(strPaymentWindowTitle.contains("MOBILE BILL PAYMENT"))
							{
								Reporter.SuccessReport("Verify Carrier Billing Payment Window", " Carrier Billing Payment Window is display Successfuly for " + strPlanName);
								//Verify Net Banking Payment Details
								String strPrice=driver.findElement(lblCarrierbillingPrice).getText();
								System.out.println("Price Displayes ==> "+ strPrice);
								if(strPrice.replace(",", "").contains(strPlanValue))
								{
									Reporter.SuccessReport("Verify Carrier Billing Amount", strPlanValue + " is display Successfuly");
								}
								else
								{
									Reporter.failureReportContinue("Verify Carrier Billing Amount", strPlanValue + " is not display Successfuly");
								}
								//Enter Mobile Number
									type(tctCarrierBillMobileNumber, "8888888888", "Enter Mobile Number");
								//Select Carrier Billing Operator Name
									selectByValue(cboOperator,"Airtel","Select Operator as Airtel");
									Thread.sleep(5000);
								//Click on Make Payment and Wait for Next Screen for Pin Txt Box	
									click(btnCarrierBillMakePayment,"Make Payment Carrier Billing");
									Thread.sleep(20000);	
								//Verify Pin Edit Box is Displayed or Not
									if(isElementDisplayed(txtCarrierBillPin))
									{
										Reporter.SuccessReport("Verify Carrier Billing Pin Text Box","Carrier Billing Pin Text Box is display Successfuly for Payment for " + strPlanValue );
									}
									else
									{
										Reporter.failureReportContinue("Verify Carrier Billing Pin Text Box","Carrier Billing Pin Text Box is display Successfuly for Payment for " + strPlanValue );
									}
							}
							else
							{
								Reporter.failureReportContinue("Verify Carrier Billing Payment Window", " Carrier Billing Payment Window is not display Successfuly for " + strPlanName);
							}										
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();}					
					}				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fnVerifyCashCard(String strPlanName,String strPlanValue,String strUserType) throws Throwable
	{
		try
		{
					Reporter.reportStep("Verify Cash Card Payment with  with User Type  " + strUserType + "  Plan Type " +strPlanName);
				//Navigate to Subscription
					navigateToSubscription();
				//Click on Manage Subscription
					click(HeaderLocators.btnPlanPageManage,"Manage");
				//Select Plan Based on the Plan Type	
					//Select Plan Based on the Plan Type	
					if(strPlanName.equals("7 Days"))
					{
						//Select 7 Days Play
							click(By.xpath("//form[1]//button"),"Plan: <b>7 Days</b>");
					}
					else if(strPlanName.equals("30 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[2]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[2]//button"),"Plan: <b>30 Days</b>");
					} 
					else if(strPlanName.equals("90 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[3]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[3]//button"),"Plan: <b>90 Days</b>");
					} 
					else if(strPlanName.equals("180 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[4]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[4]//button"),"Plan: <b>180 Days</b>");
					}
					else if(strPlanName.equals("360 Days"))
					{
						//DeSelect Auto Renew Option
							click(By.xpath("//form[5]//input[@type='checkbox']/../label"),"Auto-Renew");
						//Select 7 Days Play
							click(By.xpath("//form[5]//button"),"Plan: <b>360 Days</b>");
					}				
				//Click on Net Banking
					boolean blnPayment=fnSelectPaymentMethod("Cash Card");
					if(blnPayment)
					{
						String winHandleBefore="";
						try
						{
							winHandleBefore = driver.getWindowHandle();
						//Click on Make Payment
							click(HeaderLocators.btnMakePayment,"Make Payment");
							Thread.sleep(5000);
						//Verify Popup
							// Switch to new window opened
							for(String winHandle : driver.getWindowHandles()){
								driver.switchTo().window(winHandle);
							}
							//Perform the actions on new window
							String strPaymentWindowTitle=driver.findElement(HeaderLocators.lblCashCardInfo).getText();
							System.out.println(strPaymentWindowTitle);
							if(strPaymentWindowTitle.contains("CASH CARD PAYMENT"))
							{
								Reporter.SuccessReport("Verify Cash Card Payment Payment Window", " Cash Card Payment Window is display Successfuly for " + strPlanName);
								//Verify Net Banking Payment Details
								String strPrice=driver.findElement(lblPrice).getText();
								System.out.println("Price Displayes ==> "+ strPrice);
								if(strPrice.replace(",", "").contains(strPlanValue))
								{
									Reporter.SuccessReport("Verify Cash Card Amount", strPlanValue + " is display Successfuly");
								}
								else
								{
									Reporter.failureReportContinue("Verify Cash Card Amount", strPlanValue + " is not display Successfuly");
								}
								//Select Carrier Billing Operator Name
									selectByValue(cboCashCard,"ITZ Cash Card","Select Cash Card as ITZ Cash Card");
									Thread.sleep(5000);
								//Click on Make Payment and Wait for Next Screen for Pin Txt Box	
									click(btnCashCard,"Make Payment Cash Card");
									Thread.sleep(20000);
									String strCashCardPmtWindowTitle=driver.getTitle();
									System.out.println(strCashCardPmtWindowTitle);
									
								//Verify Pin Edit Box is Displayed or Not
									if(strCashCardPmtWindowTitle.contains("Itz Cash Payment Gateway"))
									{
										Reporter.SuccessReport("Verify Cash Card Payment Gateway","Cash Card Payment Gateway is display Successfuly for Payment for " + strPlanValue );
									}
									else
									{
										Reporter.failureReportContinue("Verify Cash Card Payment Gateway","Cash Card Payment Gateway is not display Successfuly for Payment for " + strPlanValue );
									}
							}
							else
							{
								Reporter.failureReportContinue("Verify Carrier Billing Payment Window", " Carrier Billing Payment Window is not display Successfuly for " + strPlanName);
							}										
						// Close the new window, if that window no more required
						driver.close();
						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);
						Thread.sleep(5000);
						}
						catch(Exception j){
							// Close the new window, if that window no more required
							driver.close();
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							Thread.sleep(5000);
							j.printStackTrace();}					
					}				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean fnSelectPaymentMethod(String strPaymentMrthod) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			List<WebElement> elePaymentMethod=driver.findElement(By.xpath("//*[@id='payment-methods-content']/form/div[1]/div[2]/ul")).findElements(By.tagName("li"));
			for(int i=1;i<=elePaymentMethod.size();i++)
			{
				String strPaymentXPath=".//*[@id='payment-methods-content']/form/div[1]/div[2]/ul/li["+i+"]/fieldset/label";
				String strAppPaymentMethod=driver.findElement(By.xpath(strPaymentXPath)).getText();
				if(strPaymentMrthod.equals(strAppPaymentMethod))
				{
					click(By.xpath(strPaymentXPath),strPaymentMrthod);
					blnStatus=true;
					break;
				}				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnStatus;
	}
	
	
	public static boolean fnVerifySettings() throws Throwable
	{
		boolean blnflag=true;
		try {	
			//Verify Settings Page
				Reporter.reportStep("Verifying Hooq Settings Page");
				navigateToSettings();
				Thread.sleep(10000);
			//Verify the Combobox Item
				fnVerifySettingsLanguage();
				fnVerifySettingsAudioLanguage();
				fnVerifySettingsSubTitleLanguage();
				fnVerifySettingsPlayBackQuality();
			//Set Application language as English
				selectByVisibleText(cboSettingAppDisplayLanguage,"English","Application Display Language");
				JSClick(btnSettingsSave,"Save");
				Thread.sleep(10000);
				//Verify the App Settings as Engligh
				fnVerifySettingsEnglish();
			//Set Application Language as Bahasa Indonesia
				selectByVisibleText(cboSettingAppDisplayLanguage,"Bahasa Indonesia","Application Display Language");
				Thread.sleep(10000);
				JSClick(btnSettingsSave,"Save");
				Thread.sleep(10000);
				//Verify the App Settings as Bahasa Indonesia
				fnVerifySettingsBahasaIndonesia();
			//Set the Application language as Thai
				selectByVisibleText(cboSettingAppDisplayLanguage,"Thai","Application Display Language");
				Thread.sleep(10000);
				JSClick(btnSettingsSave,"Save");
				Thread.sleep(10000);
				//Verify the App Settings as Thai
				fnVerifySettingsThai();
			//Make Default Application Language as English
				selectByVisibleText(cboSettingAppDisplayLanguage,"English","Application Display Language");
				Thread.sleep(10000);
				JSClick(btnSettingsSave,"Save");
				Thread.sleep(10000);				
				return blnflag;
		}
		catch(Exception e){
			navigateToSettings();
			Thread.sleep(10000);
			selectByVisibleText(cboSettingAppDisplayLanguage,"English","Application Display Language");
			JSClick(btnSettingsSave,"Save");
			Thread.sleep(10000);
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public static void fnVerifySettingsLanguage() throws Throwable
	{
		try
		{
			Select oSelect=new Select(driver.findElement(cboSettingAppDisplayLanguage));
			List<WebElement> eleList=oSelect.getOptions();
			if(eleList.size() >0)
			{
				for(int i=0;i<eleList.size();i++)
				{
					Reporter.SuccessReport("Verify Language Options",eleList.get(i).getText() + " Language Option is displayed Successfuly");
					System.out.println(eleList.get(i).getText());
				}				
			}
			else
			{
				Reporter.failureReportContinue("Verify Language Options","Language Option is not displayed.");
				System.out.println("No Options are available");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}		
	}
	
	public static void fnVerifySettingsAudioLanguage() throws Throwable
	{
		try
		{
			Select oSelect=new Select(driver.findElement(cboSettingsAudioLanguage));
			List<WebElement> eleList=oSelect.getOptions();
			if(eleList.size() >0)
			{
				for(int i=0;i<eleList.size();i++)
				{
					Reporter.SuccessReport("Verify Audio Language Options",eleList.get(i).getText() + " Audio Language Option is displayed Successfuly");
					System.out.println(eleList.get(i).getText());
				}				
			}
			else
			{
				Reporter.failureReportContinue("Verify Audio Language Options"," Audio Language Option is not displayed.");
				System.out.println("No Options are available");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}		
	}
	
	public static void fnVerifySettingsSubTitleLanguage() throws Throwable
	{
		try
		{
			Select oSelect=new Select(driver.findElement(cboSettingsSubtitleLanguage));
			List<WebElement> eleList=oSelect.getOptions();
			if(eleList.size() >0)
			{
				for(int i=0;i<eleList.size();i++)
				{
					Reporter.SuccessReport("Verify SubTitle Language Options",eleList.get(i).getText() + " SubTitle Language Option is displayed Successfuly");
					System.out.println(eleList.get(i).getText());
				}				
			}
			else
			{
				Reporter.failureReportContinue("Verify SubTitle Language Options"," SubTitle Language Option is not displayed.");
				System.out.println("No Options are available");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}		
	}
	
	public static void fnVerifySettingsPlayBackQuality() throws Throwable
	{
		try
		{
			Select oSelect=new Select(driver.findElement(cboSettingsPlaybackQuality));
			List<WebElement> eleList=oSelect.getOptions();
			if(eleList.size() >0)
			{
				for(int i=0;i<eleList.size();i++)
				{
					Reporter.SuccessReport("Verify Playback Quality Options",eleList.get(i).getText() + " Playback Quality Option is displayed Successfuly");
					System.out.println(eleList.get(i).getText());
				}				
			}
			else
			{
				Reporter.failureReportContinue("Verify Playback Quality Options"," Playback Quality Option is not displayed.");
				System.out.println("No Options are available");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}		
	}
	public static void fnVerifySettingsEnglish() throws Throwable
	{
		try
		{
			//Verify Applucation Setting Application Language
			String strAppcboLanguage=getSelectedOption(cboSettingAppDisplayLanguage,"App Display Language");
			String strlblSettingsTitle=getText(lblSettingsTitle,"Settings Title");
			String strlblAppDisplayLanguage=getText(lblSettingAppDisplayLanguage,"App Display Language");
			String strlblAudioLanguage=getText(lblSettingsAudioLanguage,"Audio Language");
			String strlblSubTitleLanguage=getText(lblSettingsSubtitleLanguage,"Subtitle Language");
			String strlblPlaybackQuality=getText(lblSettingsPlaybackQuality,"Playback Quality");
			String strHeaderBrowse=getText(lblHeaderBrowse,"Browse Component");
			String strHeaderSearch=getText(lblHeaderSearch,"Search Component");
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			String strRentals=getText(lblHeaderRentals,"Rental Componnet");
			String strHeaderWatchLater=getText(lblHeaderWatchLater,"Watch Later Componnet");
			String strHeaderFaviorite=getText(lblHeaderFaviorites,"Faviorites Components");
			String strHistory=getText(lblHeaderHistory,"History Component");
			String strSettings=getText(lblHeaderSettings,"Settings Component");
			String strSubscription=getText(lblHeaderSubscription,"Subscription Component");
			String strTransactionHistory=getText(lblHeaderTransactionHistory,"Transaction History Component");
			String strSupport=getText(lblHeaderSupport,"Support");
			String strLogout=getText(lblHeaderLogout,"Logout");
			
			if(strlblSettingsTitle.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Settings Title Label is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Settings Title Label is not Changed to English Successfuly");
			}
			if(strlblAppDisplayLanguage.toLowerCase().equals("app display language"))
			{
				Reporter.SuccessReport("Verify Settings with English", "App Display Language Label is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "App Display Language Label is not Changed to English Successfuly");
			}			
			if(strAppcboLanguage.toLowerCase().equals("english"))
			{
				Reporter.SuccessReport("Verify Settings with English", "App Display Language Dropdown is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "App Display Language Dropdown is not Changed to English Successfuly");
			}
			if(strlblAudioLanguage.toLowerCase().equals("audio language"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Audio Language Label is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Audio Language Label is not Changed to English Successfuly");
			}
			if(strlblSubTitleLanguage.toLowerCase().equals("subtitle language"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Subtitle Language Label is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Subtitle Language Label is not Changed to English Successfuly");
			}
			if(strlblPlaybackQuality.toLowerCase().equals("playback quality"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Playback Quality Label is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Playback Quality Label is not Changed to English Successfuly");
			}
			if(strHeaderBrowse.toLowerCase().equals("browse"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Browse Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Browse Text is not Changed to English Successfuly");
			}
			if(strHeaderSearch.toLowerCase().equals("search"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Search Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Search Text is not Changed to English Successfuly");
			}
			if(strRentals.toLowerCase().equals("rentals"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Rentals Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Rentals Text is not Changed to English Successfuly");
			}
			if(strHeaderWatchLater.toLowerCase().equals("watch later"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Watch Later Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Watch Later Text is not Changed to English Successfuly");
			}
			if(strHeaderFaviorite.toLowerCase().equals("favorites"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Favorites Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Favorites Text is not Changed to English Successfuly");
			}
			if(strHistory.toLowerCase().equals("history"))
			{
				Reporter.SuccessReport("Verify Settings with English", "History Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "History Text is not Changed to English Successfuly");
			}
			if(strSettings.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Settings Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Settings Text is not Changed to English Successfuly");
			}
			if(strTransactionHistory.toLowerCase().equals("transaction history") )
			{
				Reporter.SuccessReport("Verify Settings with English", "Transaction History Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Transaction History Text is not Changed to English Successfuly");
			}
			if(strSubscription.toLowerCase().equals("subscription") )
			{
				Reporter.SuccessReport("Verify Settings with English", "Subscription Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Subscription Text is not Changed to English Successfuly");
			}
			if(strSupport.toLowerCase().equals("support"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Support Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Support Text is not Changed to English Successfuly");
			}
			if(strLogout.toLowerCase().equals("logout"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Logout Text is Changed to English Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Logout Text is not Changed to English Successfuly");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fnVerifySettingsBahasaIndonesia() throws Throwable
	{
		try
		{
			//Verify Applucation Setting Application Language
			String strAppcboLanguage=getSelectedOption(cboSettingAppDisplayLanguage,"App Display Language");
			String strlblSettingsTitle=getText(lblSettingsTitle,"Settings Title");
			String strlblAppDisplayLanguage=getText(lblSettingAppDisplayLanguage,"App Display Language");
			String strlblAudioLanguage=getText(lblSettingsAudioLanguage,"Audio Language");
			String strlblSubTitleLanguage=getText(lblSettingsSubtitleLanguage,"Subtitle Language");
			String strlblPlaybackQuality=getText(lblSettingsPlaybackQuality,"Playback Quality");
			String strHeaderBrowse=getText(lblHeaderBrowse,"Browse Component");
			String strHeaderSearch=getText(lblHeaderSearch,"Search Component");
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			String strRentals=getText(lblHeaderRentals,"Rental Componnet");
			String strHeaderWatchLater=getText(lblHeaderWatchLater,"Watch Later Componnet");
			String strHeaderFaviorite=getText(lblHeaderFaviorites,"Faviorites Components");
			String strHistory=getText(lblHeaderHistory,"History Component");
			String strSettings=getText(lblHeaderSettings,"Settings Component");
			String strSubscription=getText(lblHeaderSubscription,"Subscription Component");
			String strTransactionHistory=getText(lblHeaderTransactionHistory,"Transaction History Component");
			String strSupport=getText(lblHeaderSupport,"Support");
			String strLogout=getText(lblHeaderLogout,"Logout");
			if(strlblSettingsTitle.toLowerCase().equals("pengaturan") && !strlblSettingsTitle.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Settings Title Label is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Settings Title Label is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strlblAppDisplayLanguage.toLowerCase().equals("tampilan bahasa aplikasi") && !strlblAppDisplayLanguage.toLowerCase().equals("app display language"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "App Display Language Label is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "App Display Language Label is not Changed to Bahasa Indonesia Successfuly");
			}			
			if(strAppcboLanguage.toLowerCase().equals("bahasa indonesia") && !strAppcboLanguage.toLowerCase().equals("english"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "App Display Language Dropdown is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "App Display Language Dropdown is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strlblAudioLanguage.toLowerCase().equals("audio bahasa") && !strlblAudioLanguage.toLowerCase().equals("audio language"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Audio Language Label is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Audio Language Label is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strlblSubTitleLanguage.toLowerCase().equals("bahasa terjemahan") && !strlblSubTitleLanguage.toLowerCase().equals("subtitle language"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Subtitle Language Label is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Subtitle Language Label is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strlblPlaybackQuality.toLowerCase().equals("kualiatas playback") && !strlblPlaybackQuality.toLowerCase().equals("playback quality"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Playback Quality Label is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Playback Quality Label is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strHeaderBrowse.toLowerCase().equals("telusuri") && !strHeaderBrowse.toLowerCase().equals("browse"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Browse Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Browse Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strHeaderSearch.toLowerCase().equals("cari") && !strHeaderSearch.toLowerCase().equals("search"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Search Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Search Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strRentals.toLowerCase().equals("sewa"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Rentals Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Rentals Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strHeaderWatchLater.toLowerCase().equals("tonton nanti") && !strHeaderWatchLater.toLowerCase().equals("watch later"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Watch Later Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Watch Later Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strHeaderFaviorite.toLowerCase().equals("favorit") && !strHeaderFaviorite.toLowerCase().equals("favorites"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Favorites Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Favorites Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strHistory.toLowerCase().equals("sejarah") && !(strHistory.toLowerCase().equals("history")))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "History Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "History Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strSettings.toLowerCase().equals("pengaturan") && !strSettings.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Settings Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Settings Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strTransactionHistory.toLowerCase().equals("daftar transaksi") )
			{
				Reporter.SuccessReport("Verify Settings with English", "Transaction History Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Transaction History Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strSubscription.toLowerCase().equals("paket berlangganan") && !strSubscription.toLowerCase().equals("subscription") )
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Subscription Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Subscription Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strSupport.toLowerCase().equals("bantuan") && !strSupport.toLowerCase().equals("support"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Support Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Support Text is not Changed to Bahasa Indonesia Successfuly");
			}
			if(strLogout.toLowerCase().equals("keluar") && !strLogout.toLowerCase().equals("logout"))
			{
				Reporter.SuccessReport("Verify Settings with Bahasa Indonesia", "Logout Text is Changed to Bahasa Indonesia Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Bahasa Indonesia", "Logout Text is not Changed to Bahasa Indonesia Successfuly");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void fnVerifySettingsThai() throws Throwable
	{
		try
		{
			//Verify Applucation Setting Application Language
			String strAppcboLanguage=getSelectedOption(cboSettingAppDisplayLanguage,"App Display Language");
			String strlblSettingsTitle=getText(lblSettingsTitle,"Settings Title");
			String strlblAppDisplayLanguage=getText(lblSettingAppDisplayLanguage,"App Display Language");
			String strlblAudioLanguage=getText(lblSettingsAudioLanguage,"Audio Language");
			String strlblSubTitleLanguage=getText(lblSettingsSubtitleLanguage,"Subtitle Language");
			String strlblPlaybackQuality=getText(lblSettingsPlaybackQuality,"Playback Quality");
			String strHeaderBrowse=getText(lblHeaderBrowse,"Browse Component");
			String strHeaderSearch=getText(lblHeaderSearch,"Search Component");
			mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			String strRentals=getText(lblHeaderRentals,"Rental Componnet");
			String strTransactionHistory=getText(lblHeaderTransactionHistory,"Transaction History Component");
			String strHeaderWatchLater=getText(lblHeaderWatchLater,"Watch Later Componnet");
			String strHeaderFaviorite=getText(lblHeaderFaviorites,"Faviorites Components");
			String strHistory=getText(lblHeaderHistory,"History Component");
			String strSettings=getText(lblHeaderSettings,"Settings Component");
			String strSubscription=getText(lblHeaderSubscription,"Subscription Component");
			String strSupport=getText(lblHeaderSupport,"Support");
			String strLogout=getText(lblHeaderLogout,"Logout");
			if(strlblSettingsTitle.length() >1 && !strlblSettingsTitle.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Settings Title Label is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Settings Title Label is not Changed to Thai Successfuly");
			}
			if(strlblAppDisplayLanguage.length() >1 && !strlblAppDisplayLanguage.toLowerCase().equals("app display language"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "App Display Language Label is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "App Display Language Label is not Changed to Thai Successfuly");
			}			
			if(strAppcboLanguage.length()>1 && !strAppcboLanguage.toLowerCase().equals("english"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "App Display Language Dropdown is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "App Display Language Dropdown is not Changed to Thai Successfuly");
			}
			if(strlblAudioLanguage.length()>1 && !strlblAudioLanguage.toLowerCase().equals("audio language"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Audio Language Label is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Audio Language Label is not Changed to Thai Successfuly");
			}
			if(strlblSubTitleLanguage.length()>1 && !strlblSubTitleLanguage.toLowerCase().equals("subtitle language"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Subtitle Language Label is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Subtitle Language Label is not Changed to Thai Successfuly");
			}
			if(strlblPlaybackQuality.length()>1 && !strlblPlaybackQuality.toLowerCase().equals("playback quality"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Playback Quality Label is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Playback Quality Label is not Changed to Thai Successfuly");
			}
			if(strHeaderBrowse.length()>1 && !strHeaderBrowse.toLowerCase().equals("browse"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Browse Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Browse Text is not Changed to Thai Successfuly");
			}
			if(strHeaderSearch.length()>1 && !strHeaderSearch.toLowerCase().equals("search"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Search Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Search Text is not Changed to Thai Successfuly");
			}
			if(strHeaderWatchLater.length()>1 && !strHeaderWatchLater.toLowerCase().equals("watch later"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Watch Later Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Watch Later Text is not Changed to Thai Successfuly");
			}
			if(strHeaderFaviorite.length()>1 && !strHeaderFaviorite.toLowerCase().equals("favorites"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Favorites Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Favorites Text is not Changed to Thai Successfuly");
			}
			if(strHistory.length()>1 && !strHistory.toLowerCase().equals("history"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "History Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "History Text is not Changed to Thai Successfuly");
			}
			if(strSettings.length()>1 && !strSettings.toLowerCase().equals("settings"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Settings Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Settings Text is not Changed to Thai Successfuly");
			}
			if(strSubscription.length()>1 && !strSubscription.toLowerCase().equals("subscription") )
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Subscription Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Subscription Text is not Changed to Thai Successfuly");
			}
			if(strRentals.length() >1  && !strRentals.toLowerCase().equals("rentals"))
			{
				Reporter.SuccessReport("Verify Settings with English", "Rentals Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Rentals Text is not Changed to Thai Successfuly");
			}
			if(strTransactionHistory.length()>1 && !strTransactionHistory.toLowerCase().equals("transaction history") )
			{
				Reporter.SuccessReport("Verify Settings with English", "Transaction History Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with English", "Transaction History Text is not Changed to Thai Successfuly");
			}
			if(strSupport.length()>1 && !strSupport.toLowerCase().equals("support"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Support Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Support Text is not Changed to Thai Successfuly");
			}
			if(strLogout.length()>1 && !strLogout.toLowerCase().equals("logout"))
			{
				Reporter.SuccessReport("Verify Settings with Thai", "Logout Text is Changed to Thai Successfuly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Settings with Thai", "Logout Text is not Changed to Thai Successfuly");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static String fnVerifyPaymentWithNetBanking(String strBankName) throws Throwable
	{
		String strShowDetails="";
		try {	
			//Get the Plan Name
				String strPlanName=getText(By.xpath(".//*[@class='info']/h4"),"Plan Details");
			//Get The AutoRenew Option Value
				String strAutoRenew=getText(By.xpath(".//*[@class='info']/p[2]/span"),"Auto Renew Value");
			//Get The Price
				String strPrice=getText(By.xpath(".//*[@class='price']/p"),"Price");
			//Select Net Banking Options
				
			//Click on Make Payment
			
			//Verify The Popup
			
			//Verify the Plan Name
			
			//Verify the AutoRenew
			
			//Verify the Price
			
			//Select the Bank Name from Drop Down
			
			//Click on Make Payment
			
			//Verify the Respective Bank Page
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strShowDetails;
	}
	
	public static boolean fnVerifyHomePageScreenBeforeLogin() throws Throwable
	{
		boolean res = false;
		try {	
				Reporter.reportStep("Verifying Hooq Home Page");
				//Verify Login Button
				if(isElementDisplayed(headerLogin))
				{
					Reporter.SuccessReport("Verify Login Button", "Login Button is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Login Button", "Login Button is not Displayed Successfully");
				}
				//Verify Start Your Free Trail Now Link
				if(isElementDisplayed(By.xpath("//*[@class='buttons']/a")))
				{
					Reporter.SuccessReport("Verify Start Your Free Trail Now Link at Header", "Start Your Free Trail Now Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Start Your Free Trail Now Link at Header", "Start Your Free Trail Now Link is not Displayed Successfully");
				}
				
				/*//Verify Movies and TVSeries Link
				if(isElementDisplayed(lnkMovies))
				{
					Reporter.SuccessReport("Verify Movies Link", "Movies Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Movies Link", "Movies Link is not Displayed Successfully");
				}
				if(isElementDisplayed(lnkTVSeries))
				{
					Reporter.SuccessReport("Verify TV Seriess Link", "TV Series Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify TV Seriess Link", "TV Series Link is not Displayed Successfully");
				}*/
				
				if(isElementDisplayed(lnkshowmemore))
				{
					Reporter.SuccessReport("Verify Show me more Link", "Show me more Link  is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Show me more Link", "Show me more Link is not Displayed Successfully");
				}
				
				
				
				//Verify Pricing and Bundle
				if(isElementDisplayed(lblPriceBundle))
				{
					Reporter.SuccessReport("Verify Price Information Label", "Price Information Label is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Price Information Label", "Price Information Label is not Displayed Successfully");
				}
				if(isElementDisplayed(lnkGetHooqNow))
				{
					Reporter.SuccessReport("Verify Get Hooq Now Link", "Get Hooq Now Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Get Hooq Now Link", "Get Hooq Now Link is not Displayed Successfully");
				}				
				//Verify Bottom Start your Trail Link
				if(isElementDisplayed(By.xpath("//*[@class='container get-hooqd no-border']/div/div/a")))
				{
					Reporter.SuccessReport("Verify Start Your Free Trail Now Link at Header", "Start Your Free Trail Now Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Start Your Free Trail Now Link at Header", "Start Your Free Trail Now Link is not Displayed Successfully");
				}
				//Verify Android Play Store Link
				if(isElementDisplayed(lnkAndroidPlayStore))
				{
					Reporter.SuccessReport("Verify Android Play Store Link", "Android Play Store Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Android Play Store Link", "Android Play Store Link is not Displayed Successfully");
				}
				//Verify AppStore Link
				if(isElementDisplayed(lnkIOSAppStore))
				{
					Reporter.SuccessReport("Verify IOS App Store Link", "IOS App Store Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify IOS App Store Link", "IOS App Store Link is not Displayed Successfully");
				}
				//Verify Bottom Support Link
				fnVerifyBottomSupportLink();
				
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	
	public static boolean fnVerifyHomePageScreenAfterLogin(String strUserType) throws Throwable
	{
		boolean res = false;
		try {	
				Reporter.reportStep("Verifying Hooq Home Page After " + strUserType + " User");
				//Verify Browse
				List<WebElement> eleList=driver.findElements(By.className("galleria-thumbnail"));
				if(eleList.size() >0)
				{
					Reporter.SuccessReport("Verify Collection","Movie/TV Collection is found successfully Total Count : " + eleList.size());
				}
				else
				{
					Reporter.failureReportContinue("Verify Collection","Movie/TV Collection is not found" );
				}
				List<WebElement> eleListSeeMore=driver.findElements(lnkSeeMore);
				if(eleListSeeMore.size() >0)
				{
					Reporter.SuccessReport("Verify See More Link","See More Link found successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify See More Link","See More Link found not found.");
				}
				
				if(isElementDisplayed(lblHeaderBrowse))
				{
					Reporter.SuccessReport("Verify Browse Link", "Browse Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Browse Link", "Browse Link is not Displayed Successfully");
				}				
				//Verify Search
				if(isElementDisplayed(lblHeaderSearch))
				{
					Reporter.SuccessReport("Verify Search Link", "Search Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Search Link", "Search Link is not Displayed Successfully");
				}	
				if(strUserType.equals("valid"))
				{
					//Verify Menu
					mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
					//Verify Watch Later
					if(isElementDisplayed(lblHeaderWatchLater))
					{
						Reporter.SuccessReport("Verify Watch Later Under Me Menu", "Watch Later is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Watch Later Under Me Menu", "Watch Later is not Displayed Successfully");
					}				
					//Verify Faviorite
					if(isElementDisplayed(lblHeaderFaviorites))
					{
						Reporter.SuccessReport("Verify Faviorite Under Me Menu", "Faviorite is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Faviorite Under Me Menu", "Faviorite is not Displayed Successfully");
					}
					//Verify History
					if(isElementDisplayed(lblHeaderHistory))
					{
						Reporter.SuccessReport("Verify History Under Me Menu", "History is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify History Under Me Menu", "History is not Displayed Successfully");
					}				
					//Verify Settings
					if(isElementDisplayed(lblHeaderSettings))
					{
						Reporter.SuccessReport("Verify Settings Under Me Menu", "Settings is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Settings Under Me Menu", "Settings is not Displayed Successfully");
					}
					//Verify Subscription
					if(isElementDisplayed(lblHeaderSubscription))
					{
						Reporter.SuccessReport("Verify Subscription Under Me Menu", "Subscription is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Subscription Under Me Menu", "Subscription is not Displayed Successfully");
					}
					//Verify Support
					if(isElementDisplayed(lblHeaderSupport))
					{
						Reporter.SuccessReport("Verify Support Under Me Menu", "Support is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Support Under Me Menu", "Support is not Displayed Successfully");
					}
					//Verify user Info
					if(isElementDisplayed(lblHeaderLoginInfo))
					{
						Reporter.SuccessReport("Verify Logged User Info Under Me Menu", "Logged User Info is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Logged User Info Under Me Menu", "Logged User Info is not Displayed Successfully");
					}
					//Verify Logout
					if(isElementDisplayed(lblHeaderLogout))
					{
						Reporter.SuccessReport("Verify Logout Under Me Menu", "Logout is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Logout Under Me Menu", "Logout is not Displayed Successfully");
					}
					if(!isElementDisplayed(headerLogin))
					{
						Reporter.SuccessReport("Verify Login Button", "Login Button is not Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Login Button", "Login Button Displayed Successfully");
					}
				}
				else
				{
					if(isElementDisplayed(headerLogin))
					{
						Reporter.SuccessReport("Verify Login Button", "Login Button is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Login Button", "Login is not Displayed Successfully");
					}
					if(isElementDisplayed(HeaderLocators.SignUpAnynomous))
					{
						Reporter.SuccessReport("Verify SignUp Button", "SignUp Button is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify SignUp Button", "SignUp is not Displayed Successfully");
					}					
				}	
				//Verify Bottom Support Link After Login
					fnVerifyBottomSupportLink();
				//Click on Home
					JSClick(HomePageLocators.imgLogo,"Home Page");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void fnVerifyBottomSupportLink() throws Throwable
	{
		try
		{	//Verify Bottom Support Link
			if(isElementDisplayed(lnkFooterAboutUs))
			{
				Reporter.SuccessReport("Verify About Us Link", "About Us Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify About Us Link", "About Us Link is not Displayed Successfully");
			}
			if(isElementDisplayed(lnkFoterTermsOfUse))
			{
				Reporter.SuccessReport("Verify Term Of Use Link", "Term Of Use Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Term Of Use Link", "Term Of Use Link is not Displayed Successfully");
			}
			if(isElementDisplayed(lnkFooterPrivacyPolicy))
			{
				Reporter.SuccessReport("Verify Privacy Policy Link", "Privacy Policy Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Privacy Policy Link", "Privacy Policy Link is not Displayed Successfully");
			}
			if(isElementDisplayed(lnkFooterFAQ))
			{
				Reporter.SuccessReport("Verify FAQ Link", "FAQ Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify FAQ Link", "FAQ Link is not Displayed Successfully");
			}
			if(isElementDisplayed(lnkFooterContactUs))
			{
				Reporter.SuccessReport("Verify Contact US Link", "Contact Us Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Contact US Link", "Contact Us Link is not Displayed Successfully");
			}
			//Verify Copyright Message
			String strCopyRightText=getText(HomePageLocators.lblCopyRight,"CopyRight");
			System.out.println("Copyright Text ==> " + strCopyRightText);
			if(strCopyRightText.contains("2017 HOOQ. All rights reserved."))
			{
				Reporter.SuccessReport("Verify Copyright Text", "Copyright Text is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Copyright Text", "Copyright Text is not Displayed Successfully");
			}
		}
		catch(Exception e){ e.printStackTrace();}
		
	}
	
	public static void fnVerifyHomePageBannerAfterLogin(String strUserType) throws Throwable
	{
		try
		{
			Reporter.reportStep("Verifying HOOQ Home Page Banner for " + strUserType + " User");
			WebElement eleBanner=driver.findElement(HomePageLocators.lstBanner);
			List<WebElement> lstBanner=eleBanner.findElements(By.tagName("li"));
			System.out.println("Size of List " + lstBanner.size());
			//Verify the Banner if the Total Movie in Banner is 5
			if(lstBanner.size() == 5)
			{
				//Report Banner is displays
				Reporter.SuccessReport("Verify Banner at HOOQ Home Screen", "Banner at HOOQ Screen is Displayed Successfully");
				for(int i=0;i<lstBanner.size();i++)
				{					
					//Click on First Movie / TV Series and Verify the Details
						lstBanner.get(i).click();
					//Get the Name of Movie / TV Series Details	
						String strMovieName=getText(HomePageLocators.lblBannerMovieName,"Movie Name " + (i+1));
						System.out.println(strMovieName);
					//Verify Watch Later Button from Banner	
						if(isElementDisplayed(HomePageLocators.btnBannerWatchLater))
						{
							Reporter.SuccessReport("Verify Watch Later Button for " + strMovieName, "Watch Later Button for " + strMovieName + " is Displayed Successfully");
						}
						else
						{
							Reporter.failureReportContinue("Verify Watch Later Button for " + strMovieName, "Watch Later Button for " + strMovieName + " is not Displayed Successfully");
						}
						if(strUserType.equals("valid"))
						{
							//Verify Faviorites Button from Banner	
							if(isElementDisplayed(HomePageLocators.btnBanerFaviorites))
							{
								Reporter.SuccessReport("Verify Faviorite Button for " + strMovieName, "Faviorite Button for " + strMovieName + " is Displayed Successfully");
							}
							else
							{
								Reporter.failureReportContinue("Verify Faviorite Button for " + strMovieName, "Faviorite Button for " + strMovieName + " is not Displayed Successfully");
							}
						}
				}
			}
			else
			{
				Reporter.failureReportContinue("Verify Banner at HOOQ Home Screen", "Banner at HOOQ Screen is not Displayed Successfully");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void fnVerifyHomePageBrowseAfterLogin(String strUserType) throws Throwable
	{
	try
	{
		Reporter.reportStep("Verifying HOOQ Home Page Browse for " + strUserType + " User");
		WebElement eleBanner=driver.findElement(HomePageLocators.lstBrowse);
		List<WebElement> lstBanner=eleBanner.findElements(By.tagName("li"));
		System.out.println("Size of List " + lstBanner.size());
		//Verify the Banner if the Total Movie in Banner is 5
		//Report Banner is displays
			Reporter.SuccessReport("Verify Banner at HOOQ Home Screen", "Banner at HOOQ Screen is Displayed Successfully");
			for(int i=0;i<lstBanner.size();i++)
			{	
				//Click on Home
				//	
				JSClick(lblHeaderBrowse,"Browse");
				Thread.sleep(3000);
				String strText=lstBanner.get(i).getText();
				System.out.println(strText);
				driver.findElement(By.linkText(strText)).click();
				Thread.sleep(5000);
				String strGetText=getText(lblContentTitle,"Content Title for" + strText);
				System.out.println(strGetText);
				if(strText.toLowerCase().contains(strGetText.toLowerCase()))
				{
					System.out.println("Match Content Title ==> " + strText);
				}
				else
				{
					System.out.println("Match not Content Title ==> " + strText);
				}	
				JSClick(HomePageLocators.imgLogo,"Home Page");
				//Click on SignUp
				click(HeaderLocators.headerSignUp,"Sign Up");
			//Click on Skip
				click(HeaderLocators.lnkSkip,"Skip");
				click(HeaderLocators.lnkSkip,"Skip");
				//Click on First Movie / TV Series and Verify the Details
					//lstBanner.get(i).click();
				//Get the Name of Movie / TV Series Details	
					//String strMovieName=getText(HomePageLocators.lblBannerMovieName,"Movie Name " + (i+1));
				//Verify Watch Later Button from Banner	
		/*			if(isElementDisplayed(HomePageLocators.btnBannerWatchLater))
					{
						Reporter.SuccessReport("Verify Watch Later Button for " + strMovieName, "Watch Later Button for " + strMovieName + " is Displayed Successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Watch Later Button for " + strMovieName, "Watch Later Button for " + strMovieName + " is not Displayed Successfully");
					}
					if(strUserType.equals("valid"))
					{
						//Verify Faviorites Button from Banner	
						if(isElementDisplayed(HomePageLocators.btnBanerFaviorites))
						{
							Reporter.SuccessReport("Verify Faviorite Button for " + strMovieName, "Faviorite Button for " + strMovieName + " is Displayed Successfully");
						}
						else
						{
							Reporter.failureReportContinue("Verify Faviorite Button for " + strMovieName, "Faviorite Button for " + strMovieName + " is not Displayed Successfully");
						}
					}*/
			}
		
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	public static boolean verifyPlayback(String itemToPlay, String userType)throws Throwable
	{
		boolean res = false;
		try {			
			Reporter.reportStep("Verifying Playback");
			if((userType.equalsIgnoreCase("Active"))||(userType.equalsIgnoreCase("Free")) ||(userType.equalsIgnoreCase("Lapsed1stEpisode")))
			{
				Thread.sleep(20000);
				boolean blnSeekbar=true;
				while(blnSeekbar)
				{
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					if(isElementDisplayed(HeaderLocators.sbarSeekBar, "Seekbar")==true);
					{
						blnSeekbar=false;
					}
				}				
				mouseover(HeaderLocators.sbarSeekBar, "Video Area");
				verifyElementDisplayed(HeaderLocators.btnPause, "Pause Button");	
				verifyElementDisplayed(HeaderLocators.btnResize, "Pause Button");
				verifyElementDisplayed(HeaderLocators.btnResize, "Resize Button");
				verifyElementDisplayed(HeaderLocators.btnSetting, "Setting Button");
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				long starttime=System.currentTimeMillis();
				String currentPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
				String totalPlayTimeLabel = getText(HeaderLocators.lblTotalPlayTIme, "Total PlayTime");
				//Thread.sleep(12000);
				//System.out.println("Player Time Counter Starts ==>  " + starttime);
				boolean blnPlayerFlag=true;
				long endTime=00;
				int i=0;
				while(blnPlayerFlag)
				{
					//System.out.println("Counter ==> " + i);
					//i++;
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					String newPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
					if(newPlayTimeLabel.equals(totalPlayTimeLabel))
					{
						blnPlayerFlag=false;
						endTime=System.currentTimeMillis();
					}
				}		
				res=true;
				//System.out.println("Player Time Counter Ends  => " + endTime);		
				long difference=((endTime-starttime)/1000);
				//System.out.println("Player Total Time Taken ==> " +difference + " Seconds");
				}			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	//TVOD Functions
	public static String fnGetTicketCount(boolean blnTicketEnable) throws Throwable
	{
		String strTicket="";
		if(blnTicketEnable==false)
		{
			return strTicket;
		}
		else
		{
		
		Reporter.reportStep("Verify Ticket Details");
		try
		{
			//Go to Home Page			
				JSClick(HomePageLocators.imgLogo,"Home Page");
			//Go to Me Section
				mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			//Verify the Ticket text
				boolean blnTicket=verifyElementDisplayed(TVOD.meTickettext, "Ticket Information");
				if(blnTicket)
				{
					String strMessage= getText(TVOD.meTickettext, "Ticket Information");
					if(strMessage.toLowerCase().equals("ticket"))
					{
						strTicket=getText(TVOD.meTicketcount, "Ticket Count");
						Reporter.SuccessReport("Verify Ticket Available ", strTicket + " Tickets available ");
					}					
				}
			//Verify the Ticket count
			return strTicket;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.failureReportContinue("Verify Ticket Available ", "Unable to get Ticket Details");
			return strTicket;
		}finally {
			//Go to Home Page			
			JSClick(HomePageLocators.imgLogo,"Home Page");		
		}
		}				
	}
	
	public static String fnGetSubscriptionDaysCount() throws Throwable
	{
		String strSubscriptionDays="";
		//Getting Subscription Days Available
		Reporter.reportStep("Verify Subscription Days Details");
		try
		{
			//Go to Home Page			
				JSClick(HomePageLocators.imgLogo,"Home Page");
			//Go to Me Section
				mouseHoverByJavaScript(HeaderLocators.headerExploreMenu,"Header Navigation");
			//Verify the Ticket text
				boolean blnTicket=verifyElementDisplayed(TVOD.meSubscriptiontext, "Ticket Information");
				if(blnTicket)
				{
					String strMessage= getText(TVOD.meSubscriptiontext, "Subscription Information");
					if(strMessage.toLowerCase().equals("subscription"))
					{						
						strSubscriptionDays=getText(TVOD.meSubscriptiondays, "Subscription Days");		
						Reporter.SuccessReport("Subscription Days Available ", strSubscriptionDays + " available ");
					}					
				}
			//Verify the Ticket count
			return strSubscriptionDays;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.failureReportContinue("Subscription Days Available ", "Unable to get Subscription Details ");
			return strSubscriptionDays;
		}finally {
			//Go to Home Page			
			JSClick(HomePageLocators.imgLogo,"Home Page");		
		}		
	}
	
	public static boolean fnVerifyOnBoardMessage(String strTicketCount) throws Throwable
	{
		boolean blnFlag=true;
		String strMessageHeaderVerify="WHAT'S NEW ON HOOQ";
		String strMessage1Verify="LATEST MOVIES NOW AVAILABLE FOR RENT!";
		String strMessage2Verify="Catch the latest movies fresh off the cinemas!";
		String strMessageTicketVerify="Already on a monthly HOOQ subscription? Congratulations! Each month’s subscription comes with 1 ticket for a movie rental!";
		String strButtonTextVerify="Explore rental movies";
		Reporter.reportStep("Verifying OnBoard Message for Rent");
		try
		{		
			//Verify OnBoard Message
				boolean blnOnboardMessage=verifyElementDisplayed(TVOD.onboardclose,"OnBaord Popup Close Button");
				if(blnOnboardMessage)
				{
					Reporter.SuccessReport("Verify OnBoard Message popup", "OnBoard Message Popup is displayed successfully");
					boolean blnOnBoardMessageHeader=verifyElementDisplayed(TVOD.onboardheader,"OnBaord Header");
					if(blnOnBoardMessageHeader)
					{
						String strMessageHeader=getText(TVOD.onboardheader, "OnBaoard Message Header");				
						if(strMessageHeader.equals(strMessageHeaderVerify)==false)
						{
							blnFlag=false;
							Reporter.failureReportContinue("Verify OnBoard Message Header ", strMessageHeader + " is  not displayed successfully");
							System.out.println(strMessageHeader);
						}
						else
						{
							Reporter.SuccessReport("Verify OnBoard Message Header ", strMessageHeader + " is displayed successfully");						
						}
						boolean blnOnBoardMessage1=verifyElementDisplayed(TVOD.onboardmessage1,"OnBaord Message 1");
						if(blnOnBoardMessage1)
						{
							String strMessage1=getText(TVOD.onboardmessage1, "OnBaoard Message1 Text");
							if(strMessage1.equals(strMessage1Verify)==false)
							{
								blnFlag=false;
								Reporter.failureReportContinue("Verify OnBoard Message ", strMessage1Verify + " is  not displayed successfully Displayed Text " + strMessage1);
								System.out.println(strMessage1);
							}	
							else
							{
								Reporter.SuccessReport("Verify OnBoard Message ", strMessage1Verify + " is  displayed successfully.");
							}
						}
						boolean blnOnBoardMessage2=verifyElementDisplayed(TVOD.onboardmessage2,"OnBaord Message 2");
						if(blnOnBoardMessage2)
						{
							String strMessage2=getText(TVOD.onboardmessage2, "OnBaoard Message2 Text");
							if(strMessage2.equals(strMessage2Verify)==false)
							{
								blnFlag=false;
								Reporter.failureReportContinue("Verify OnBoard Message ", strMessage2Verify + " is  not displayed successfully Displayed Text " + strMessage2);
								System.out.println(strMessage2);
							}
							else
							{
								Reporter.SuccessReport("Verify OnBoard Message ", strMessage2Verify + " is  not displayed successfully Displayed Text " + strMessage2);
							}
						}
						if(strTicketCount.length()>0)
						{
							boolean blnOnBoardTicketMessage=verifyElementDisplayed(TVOD.onboardticketmessage,"OnBaord Ticket Message");
							if(blnOnBoardTicketMessage)
							{
								String strMessageTicket=getText(TVOD.onboardticketmessage, "OnBaoard Ticket Message Text");
								if(strMessageTicket.equals(strMessageTicketVerify)==false)
								{
									blnFlag=false;
									Reporter.failureReportContinue("Verify OnBoard Message for Ticket ", strMessageTicketVerify + " is  not displayed successfully Displayed Text " + strMessageTicket);
									System.out.println(strMessageTicket);
								}
								else
								{
									Reporter.SuccessReport("Verify OnBoard Message for Ticket ", strMessageTicketVerify + " is  not displayed successfully.");
								}
							}
						}
						boolean blnOnBoardExploreRentalButtone=verifyElementDisplayed(TVOD.btnExploreRental,"OnBaord Explore Rental Button");
						if(blnOnBoardExploreRentalButtone)
						{
							String strButtonText=getText(TVOD.btnExploreRental, "OnBaoard Button Text");
							if(strButtonText.equals(strButtonTextVerify)==false)
							{
								blnFlag=false;
								Reporter.failureReportContinue("Verify OnBoard Explore Rental Button ", "Explore Rental Button is not displaying successfully Button Text Displayed " + strButtonText);
								System.out.println(strButtonText);
							}
							else
							{
								Reporter.SuccessReport("Verify OnBoard Explore Rental Button ", "Explore Rental Button is displaying successfully.");
							}
							JSClick(TVOD.btnExploreRental,"Explore Rental");
						}
					}
					else
					{
						blnFlag=false;
						Reporter.failureReportContinue("Verify OnBoard Message popup", "OnBoard Message Popup is not displayed successfully");
					}				
				}
				if(blnFlag)
				{
					System.out.println("On Board Message is displaying Successfully");
					Reporter.SuccessReport("Verify On Board Message", "On Board Message is displaying Successfully.");
				}
				else
				{
					System.out.println("On Board Message is not displaying Successfully");
					Reporter.failureReportContinue("Verify On Board Message", "On Board Message is not displaying Successfully.");
				}
				return blnFlag;
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean fnVerifyRentPage(String strTicket) throws Throwable
	{
		boolean blnFlag=true;
		try{
			Reporter.reportStep("Verifying Rent Page");
			String strRentHeaderVerify="RENTAL COLLECTIONS";
			String strRentMessageVerify="Get early access to the freshest titles in HOOQ available for rental. Once you rent a movie, you have 30 days to start watching before it expires and 48 hours to view the movie once you hit play. You can only stream on one device at a time.";
			String strRentHeader=getText(TVOD.rentHeader, "Rent Header");
			System.out.println(strRentHeader);
			String strRentMessage=getText(TVOD.rentMessage, "Rent Message");
			System.out.println(strRentMessage);
			if(strRentHeader.equals(strRentHeaderVerify)==false)
			{
				blnFlag=false;
				Reporter.failureReportContinue("Verify Rent Page Header ",strRentHeaderVerify + " is not displayed");
			}			
			else
			{
				Reporter.SuccessReport("Verify Rent Page Header ",strRentHeaderVerify + " is correctly displayed");
			}
			if(strRentMessage.equals(strRentMessageVerify)==false)
			{
				blnFlag=false;
				Reporter.failureReportContinue("Verify Rent Page Message ",strRentMessage + " is not displayed");
			}	
			else
			{
				Reporter.SuccessReport("Verify Rent Page Message ",strRentMessage + " is correctly displayed");
			}
			System.out.println(strTicket.length());
			if(strTicket.length()>0)
			{
				//strTicket="0";
				String strTicketInfo=getText(TVOD.rentTicketInfo, "Ticket Information");
				System.out.println(strTicketInfo);
				if(strTicketInfo.contains(strTicket))
				{
					System.out.println("Ticket Count is Same");	
					Reporter.SuccessReport("Verify Rent Page Ticket Information ","Ticket Count is Same , Me : " + strTicket + ", Rent Page :" + strTicketInfo);
				}
				else
				{
					System.out.println("Ticket Count is not Same");
					Reporter.failureReportContinue("Verify Rent Page Ticket Information ","Ticket Count is not Same , Me : " + strTicket + ", Rent Page :" + strTicketInfo);
				}
				String strExpireDate=getText(TVOD.rentTicketExpireDate, "Ticket Expire Date");
				System.out.println(strExpireDate);
				if(strExpireDate.length() > 0)
				{
					Reporter.SuccessReport("Verify Rent Page Ticket Expire Date ","Ticket Expire Date is displayed correctly");	
				}
				else
				{
					Reporter.failureReportContinue("Verify Rent Page Ticket Expire Date ","Ticket Expire Date is not displayed correctly");
				}
			}
			boolean blnCollection=verifyElementDisplayed(TVOD.rentCollection,"Rent Collection");
			System.out.println(blnCollection);
			if(blnCollection)
			{
				Reporter.SuccessReport("Verify Rent Page Collection","Collection is displayed Successfully");
				List<WebElement> eleCollectionList=driver.findElement(TVOD.rentCollection).findElements(By.className("thumbnail-landscape-wrapper"));
				if(eleCollectionList.size()<=0)
				{
					blnFlag=false;
				}
			}
			else
			{
				Reporter.failureReportContinue("Verify Rent Page Collection","Collection is not displayed correctly");
			}
			if(blnFlag)
			{
				Reporter.SuccessReport("Verify Rent Page","Rent Page is displayed correctly");
			}
			else
			{
				Reporter.failureReportContinue("Verify Rent Page","Rent Page is not displayed correctly");
			}
			return blnFlag;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean fnGoToRent() throws Throwable
	{
		try
		{
			//Click on Rent
				JSClick(TVOD.Rent,"Rent Button");	
			//Click on Rental
				Thread.sleep(10000);				
				boolean blnOnBoardExploreRentalButton=isElementDisplayed(TVOD.btnExploreRental);
				System.out.println(blnOnBoardExploreRentalButton);
				if(blnOnBoardExploreRentalButton)
				{
					Reporter.SuccessReport("Verify OnBoard Popup Message","Rent OnBoard Popup Message is displayed");
					JSClick(TVOD.btnExploreRental,"Explore Rental");
				}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static String[] fnGetRentedMovieCollectionName() throws Throwable
	{
		String[] strText=new String[19];
		try
		{
			//Go To Rent
				fnGoToRent();
			//Get the Rented Collection	
				boolean blnCollection=verifyElementDisplayed(TVOD.rentCollection,"Rent Collection");
				//System.out.println(blnCollection);
				if(blnCollection)
				{
					List<WebElement> eleCollectionList=driver.findElement(TVOD.rentCollection).findElements(By.className("thumbnail-landscape-wrapper"));
					if(eleCollectionList.size()>0)
					{
						
						for(int i=0;i< eleCollectionList.size();i++)
						{
							List<WebElement> eleCollectionName=eleCollectionList.get(i).findElements(By.tagName("h3"));
							String strCollectionName=eleCollectionName.get(0).getText();
							strText[i]=strCollectionName;
						}						
					}
				}
				return strText;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return strText;
		}
	}
	
	public static boolean fnClickRentedCollection(String strCollectionName) throws Throwable
	{
		try
		{
			Thread.sleep(10000);
			//Go To Rent
			fnGoToRent();
			Thread.sleep(10000);
		//Get the Rented Collection	
			boolean blnCollection=verifyElementDisplayed(TVOD.rentCollection,"Rent Collection");
			//System.out.println(blnCollection);
			if(blnCollection)
			{
				List<WebElement> eleCollectionList=driver.findElement(TVOD.rentCollection).findElements(By.className("thumbnail-landscape-wrapper"));
				if(eleCollectionList.size()>0)
				{
					
					for(int i=0;i< eleCollectionList.size();i++)
					{
						List<WebElement> eleCollectionName=eleCollectionList.get(i).findElements(By.tagName("h3"));
						String strCollection=eleCollectionName.get(0).getText();
						if(strCollection.equals(strCollectionName))
						{
							eleCollectionName.get(0).click();
							String strCollectionTitle=getText(TVOD.rentCollectionTitle, "Collection Title for " + strCollectionName);
							if(strCollectionTitle.equals(strCollectionName))
							{
								Reporter.SuccessReport("Click on Rented Collection", strCollectionName + " Collection is clicked");
								break;
							}					
						}					
					}						
				}
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static String[] fnGetCollectionList(String strCollectionName,String strModule)
	{
		String[] strText=null;
		try
		{
			fnKeyDown();
			List<WebElement> eleMovieList=null;
			if(strModule.toLowerCase().equals("rent"))
			{
				eleMovieList=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("content-title"));
			}
			else
			{
				eleMovieList=driver.findElement(HomePageLocators.browseCollectionTitleList).findElements(By.className("content-title"));
			}
			
			//System.out.println(eleMovieList.size());
			if(eleMovieList.size() >0 )
			{
				String[] strTextCollection=new String[eleMovieList.size()];
				for(int i=0;i<eleMovieList.size();i++)
				{
					String strMovieTitle=eleMovieList.get(i).getText();
					//System.out.println(strMovieTitle);
					strTextCollection[i]=strMovieTitle;
				}
				return strTextCollection;
			}
			else
			{
				return strText;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return strText;
		}		
	}
	
	public static void fnKeyDown()
	{
		try{
			for(int i=0;i<10;i++)
			{
				Thread.sleep(5000);
				 JavascriptExecutor jse = ((JavascriptExecutor) driver);
				 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static String[] fnFindDuplicate(String[] strCollection)
	{
		//SYSO
		//System.out.println("Find Duplicate Starts");
		String[] strDuplicate=null;
		try
		{
			for(int i=0;i<strCollection.length-1;i++)
			{
				for(int j=i+1;j<strCollection.length;j++)
				{
					//System.out.println(strCollection[i]);
					//System.out.println(strCollection[j]);					
					if(strCollection[i].equals(strCollection[j]))
					{
						strDuplicate=fnAddCollectionToArray(strDuplicate,strCollection[j]);
					}
				}
			}
			return strDuplicate;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static String[] fnAddCollectionToArray(String[] strCollection,String strNewCollection)
	{
		if(strCollection==null)
		{
			String[] strCollectionReturn=new String[1];
			strCollectionReturn[0]=strNewCollection;
			return strCollectionReturn;
		}
		else
		{
			String[] strNewCollectionArray=new String[strCollection.length+1];
			for(int i=0;i<strCollection.length;i++)
			{
				strNewCollectionArray[i]=strCollection[i];
			}
			strNewCollectionArray[strCollection.length]=strNewCollection;
			return strNewCollectionArray;
		}
		
		
	}

	public static String[] fnGetBrowseMovieCollectionName() throws Throwable
	{
		String[] strText=new String[25];
		try
		{
			//Go To Rent
				JSClick(HomePageLocators.lblHeaderBrowse,"Click on Browse");
			//Get the Rented Collection	
				boolean blnCollection=verifyElementDisplayed(HomePageLocators.browseCollection,"Rent Collection");
				//System.out.println(blnCollection);
				if(blnCollection)
				{
					List<WebElement> eleCollectionList=driver.findElement(HomePageLocators.browseCollection).findElements(By.className("thumbnail-landscape-wrapper"));
					//System.out.println(eleCollectionList.size());
					if(eleCollectionList.size()>0)
					{
						
						for(int i=0;i< eleCollectionList.size();i++)
						{
							List<WebElement> eleCollectionName=eleCollectionList.get(i).findElements(By.tagName("h3"));
							String strCollectionName=eleCollectionName.get(0).getText();
							strText[i]=strCollectionName;
						}						
					}
				}
				return strText;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return strText;
		}
	}
	
	public static boolean fnClickBrowseCollection(String strCollectionName) throws Throwable
	{
		try
		{
			//Go To Rent
			JSClick(HomePageLocators.lblHeaderBrowse,"Click on Browse");
		//Get the Rented Collection	
			boolean blnCollection=verifyElementDisplayed(HomePageLocators.browseCollection,"Rent Collection");
			//System.out.println(blnCollection);
			if(blnCollection)
			{
				List<WebElement> eleCollectionList=driver.findElement(HomePageLocators.browseCollection).findElements(By.className("thumbnail-landscape-wrapper"));
				if(eleCollectionList.size()>0)
				{
					
					for(int i=0;i< eleCollectionList.size();i++)
					{
						List<WebElement> eleCollectionName=eleCollectionList.get(i).findElements(By.tagName("h3"));
						String strCollection=eleCollectionName.get(0).getText();
						if(strCollection.equals(strCollectionName))
						{
							eleCollectionName.get(0).click();
							String strCollectionTitle=getText(TVOD.rentCollectionTitle, "Collection Title for " + strCollectionName);
							if(strCollectionTitle.equals(strCollectionName))
							{
								Reporter.SuccessReport("Click on Browse Collection", strCollectionName + " Collection is clicked");
								break;
							}					
						}					
					}						
				}
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean fnVerifyRentPurchasePopup(String strAction,String strTicket,String strRentPrice,String StrUserType,boolean blnTicketEnable) throws Throwable
	{
		try
		{
			Reporter.reportStep("Verifying Rent Purchase Popup");
			boolean blnRentPopUp=true;		
			boolean blnRentPopupVerify=verifyElementDisplayed(TVOD.rentPurchase,"Rent Purchase Popup");
			if(blnRentPopupVerify)
			{
				Reporter.SuccessReport("Verify Rent Purchase Popup ","Rent Purchase popup is displayed successfully");
				String strRentPopupHederTextVerify="RENT NOW";
				String strRentPopuoInfo="Start within 30 days, finish within 48 hours.";
				boolean blnRentHeader=verifyElementDisplayed(TVOD.rentnow,"Rent Pop-Up Header");
				if(blnRentHeader)
				{
					String strRentPopupHeader=getText(TVOD.rentnow, "Rent Popup Header Text");
					if(strRentPopupHeader.equals(strRentPopupHederTextVerify)==false)
					{
						blnRentPopUp=false;
						Reporter.failureReportContinue("Verify Rent Purchase Popup Header Text ", strRentPopupHederTextVerify + " Header Text is not displayed successfully, Text Displayed : " + strRentPopupHeader);
					}
					else
					{
						Reporter.SuccessReport("Verify Rent Purchase Popup Header Text ", strRentPopupHederTextVerify + " Header Text is displayed successfully");
					}
				}
				boolean blnrentinfo=verifyElementDisplayed(TVOD.rentinfo,"Rent Pop-Up info");
				if(blnrentinfo)				{
					
					String strRentPopupInfo=getText(TVOD.rentinfo, "Rent Popup Info Text");
					if(strRentPopupInfo.equals(strRentPopuoInfo)==false)
					{
						blnRentPopUp=false;
						Reporter.failureReportContinue("Verify Rent Purchase Popup Info Text ", strRentPopuoInfo + " Header Text is not displayed successfully, Text Displayed : " + strRentPopupInfo);
					}
					else
					{
						Reporter.SuccessReport("Verify Rent Purchase Popup Info Text ", strRentPopuoInfo + " Header Text is not displayed successfully");
					}
				}
				boolean blnrentByCC=verifyElementDisplayed(TVOD.rentByCC,"Rent Pop-Up CC Button");
				if(blnrentByCC==false)
				{
					blnRentPopUp=false;
					Reporter.failureReportContinue("Verify Rent Purchase Popup Rent By CC ", "Rent By Credit Card Button is not displayed successfully");
				}
				else
				{
					Reporter.SuccessReport("Verify Rent Purchase Popup Rent By CC ", "Rent By Credit Card Button is displayed successfully");
				}
				if(blnTicketEnable){
				if(StrUserType.equals("Anonymos")==false)
				{
					boolean blnrentByTicket=verifyElementDisplayed(TVOD.rentByTicket,"Rent Pop-Up Ticket Button");
					if(blnrentByTicket==false)
					{
						blnRentPopUp=false;
						Reporter.failureReportContinue("Verify Rent Purchase Popup Rent By Ticket ", "Rent By Ticket Button is not displayed successfully");
					}
					else
					{
						Reporter.SuccessReport("Verify Rent Purchase Popup Rent By Ticket ", "Rent By Ticket Button is displayed successfully");
					}
				}}
				boolean blnrentTermsofUse=verifyElementDisplayed(TVOD.rentTermsofUse,"Terms of Use Link");
				if(blnrentTermsofUse==false)
				{
					blnRentPopUp=false;
					Reporter.failureReportContinue("Verify Rent Purchase Popup Terms of Use Link ", "Terms of Use Link is not displayed successfully");
				}
				else
				{
					Reporter.SuccessReport("Verify Rent Purchase Popup Terms of Use Link ", "Terms of Use Link is displayed successfully");
				}
				if(strAction.equals("verifypopup"))
				{
					return blnRentPopUp;
				}
				else if(strAction.equals("verifytermofuselink"))
				{
					Reporter.reportStep("Verifying Terms of Use Link");
					JSClick(TVOD.rentTermsofUse,"Terms of Use Link");
					Thread.sleep(5000);
					String strText=getText(TVOD.rentTersmofUseHeader,"TVOD Terms of Use Page");
					String strTextVerify="GENERAL TERMS AND CONDITIONS FOR HOOQ (“GENERAL TERMS”)";
					driver.navigate().to(configProps.getProperty("URL"));
					if(strText.equals(strTextVerify))
					{
						blnRentPopUp=true;
						Reporter.SuccessReport("Verify Terms of Use Link ", "Terms of Use Page is displayed successfully");
					}
					else
					{
						blnRentPopUp=false;
						Reporter.SuccessReport("Verify Terms of Use Link ", "Terms of Use Page is not displayed successfully");
					}
				}			
				return blnRentPopUp;
			}
			else
			{
				Reporter.failureReportContinue("Verify Rent Purchase Popup ","Rent Purchase popup is not displayed successfully");
				return false;
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}	
		}
	
	public static String fnRentMoviewithTicket() throws Throwable
	{
		Reporter.reportStep("Verifying Rent Collection to purchase with Ticket");
		String strReturnMovieName="";
		try
		{
			String strCollectionName="HOT & TRENDING";
			boolean blnCollection=fnClickRentedCollection(strCollectionName);
			//Click on Rent
			if(blnCollection)
			{
				//Find the Total Count of Collection
				String[] strCollectionTitle=fnGetCollectionList(strCollectionName,"rent");
				if(strCollectionTitle==null)
				{
					System.out.println("No Collection Found under " + strCollectionName);
					Reporter.failureReportContinue("Verify Collection Under " + strCollectionName, " No Collection Found Under " + strCollectionName);
				}
				else
				{
					Reporter.SuccessReport("Verify Collection Under " + strCollectionName, "Collection Found Under " + strCollectionName);
					List<WebElement> eleMovieList=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("title-content"));
					boolean blnRent=false;
					for(int i=0;i<eleMovieList.size();i++)
					{
						List<WebElement> eleRent=eleMovieList.get(i).findElements(By.className("icon-rented"));
						System.out.println(eleRent.size());
						if(eleRent.size()==0)
						{									
							eleMovieList.get(i).click();
							blnRent=true;
							break;
						}							
					}
					if(blnRent)
					{
						Thread.sleep(10000);
						Reporter.SuccessReport("Verify Collection for Rent  Under " + strCollectionName, "Collection Found For Rent Under " + strCollectionName);
						boolean blnVerify=fnVerifyRentPurchasePopup("verifypopup","","","",true);		
						if(blnVerify)
						{		
							Reporter.reportStep("Verifying Purchase with Ticket");
							Reporter.SuccessReport("Verify Rent Purchase Popup ", "Rent Purchase popup is displayed successfully");
							//Click on Ticket
							JSClick(TVOD.rentByTicket,"Rent By Ticket");
							Thread.sleep(10000);
							//Get the Header from Confirmation Window
							String strHeader=getText(TVOD.rentRedimTicketHeader,"Header Ticket Confirmation Popup");
							System.out.println(strHeader);
							if(strHeader.equals("REDEEM TICKET NOW"))
							{
								System.out.println("Header Ticket Confirmation Popup is displayed successfully ==> " + strHeader);
								Reporter.SuccessReport("Verify Header Ticket Confirmation Popup Text", strHeader + " is displayed successfully");
							}
							else
							{
								System.out.println("Header Ticket Confirmation Popup is not displayed successfully ==> " + strHeader);
								Reporter.failureReportContinue("Verify Header Ticket Confirmation Popup Text", strHeader + " is not displayed successfully");
							}
							String strInfo=getText(TVOD.rentRedimTicketInfo,"Info Ticket Confirmation Popup");
							System.out.println(strInfo);							
							String strInfoVerify="You are about to redeem 1 ticket for"; 
							if(strInfo.startsWith(strInfoVerify))
							{
								System.out.println("Info Ticket Confirmation Popup is displayed successfully ==> " + strInfoVerify);
								Reporter.SuccessReport("Verify Header Ticket Confirmation Redim Ticket Info", strInfo + " is displayed successfully");
							}
							else
							{
								System.out.println("Info Ticket Confirmation Popup is not displayed successfully ==> " + strInfoVerify);
								Reporter.failureReportContinue("Verify Header Ticket Confirmation Redim Ticket Info", strInfo + " is not displayed successfully");
							}
							String strMoveName=getText(TVOD.rentRedimTicketMovieName1,"Movie Name");
							System.out.println(strMoveName);
							Reporter.SuccessReport("Verify Header Ticket Confirmation Movie Name ", strMoveName + " is displayed successfully");
							JSClick(TVOD.rentRedimTicketButton,"Ticket Confirm");
							Thread.sleep(10000);
							String strHeaderConfirm=getText(TVOD.rentRedimTicketConfirmHeader,"Header Confirmation Popup");
							System.out.println(strHeaderConfirm);
							String strInfoConfirmation=getText(TVOD.rentRedimTicketConfirmInfo,"Info Confirmation Popup");
							System.out.println(strInfoConfirmation);
							if(strInfoConfirmation.startsWith("Thank you for renting"))
							{
								System.out.println("Popup Header Text is displayed successfully");	
								Reporter.SuccessReport("Verify Header Ticket Confirmation Popup", strInfoConfirmation + " is displayed successfully");
							}
							else
							{
								System.out.println("Popup Header Text is not displayed successfully");
								Reporter.failureReportContinue("Verify Header Ticket Confirmation Popup", strInfoConfirmation + " is not displayed successfully");
							}							
							String strMovieName2=getText(TVOD.rentRedimTicketMovieName2,"Movie Name");
							System.out.println(strMovieName2);
							boolean blnWatchNow=verifyElementDisplayed(TVOD.rentRedimTicketConfirmWatchNowButton,"Watch Now Button");
							System.out.println(blnWatchNow);
							boolean blnWatchLater=verifyElementDisplayed(TVOD.rentRedimTicketConfirmWatchLater,"Watch Later Link");
							System.out.println(blnWatchLater);
							JSClick(TVOD.rentRedimTicketConfirmWatchLater,"Watch Later Link");
							Thread.sleep(10000);
							if(strMovieName2.equals(strMoveName))
							{
								System.out.println(strMovieName2 + " is displayed successfullu");
								Reporter.SuccessReport("Verify Movie name Ticket Confirmation Popup", strMoveName + " is displayed successfully");
							}
							else
							{
								System.out.println(strMovieName2 + " is not displayed successfullu");
								Reporter.failureReportContinue("Verify Movie name Ticket Confirmation Popup", strMoveName + " is not displayed successfully");
							}
							strReturnMovieName=strMoveName;					
					}
					else
					{
						Reporter.failureReportContinue("Verify Rent Purchase Popup ", "Rent Purchase popup is not displayed successfully");
					}						
				}
				}
				}
			if(strReturnMovieName.length() >0)
			{
				Reporter.SuccessReport("Verify Movie name for Rent", strReturnMovieName + " is Rented with Ticket successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Movie name for Rent", "No Movie is Rented with Ticket.");
			}
				return strReturnMovieName;
			}		
			catch(Exception e)
			{
				e.printStackTrace();
				return strReturnMovieName;
		}	
	}
	
	public static boolean fnRentTermsAndCondition(String strUserType,boolean blnTicketEnable) throws Throwable
	{
		Reporter.reportStep("Verifying Rent Collection to purchase with Credit Card for " + strUserType);
		boolean blnStatus=false;
		try
		{
			String strCollectionName="HOT & TRENDING";
			boolean blnCollection=fnClickRentedCollection(strCollectionName);
			//Click on Rent
			if(blnCollection)
			{
				//Find the Total Count of Collection
				String[] strCollectionTitle=fnGetCollectionList(strCollectionName,"rent");
				if(strCollectionTitle==null)
				{
					System.out.println("No Collection Found under " + strCollectionName);
					Reporter.failureReportContinue("Verify Collection Under " + strCollectionName, " No Collection Found Under " + strCollectionName);
				}
				else
				{
					Reporter.SuccessReport("Verify Collection Under " + strCollectionName, "Collection Found Under " + strCollectionName);
					List<WebElement> eleMovieList=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("title-content"));
					boolean blnRent=false;
					for(int i=0;i<eleMovieList.size();i++)
					{
						List<WebElement> eleRent=eleMovieList.get(i).findElements(By.className("icon-rented"));
						System.out.println(eleRent.size());
						if(eleRent.size()==0)
						{									
							eleMovieList.get(i).click();
							blnRent=true;
							break;
						}							
					}
					if(blnRent)
					{
						Thread.sleep(10000);
						Reporter.SuccessReport("Verify Collection for Rent  Under " + strCollectionName, "Collection Found For Rent Under " + strCollectionName);
						boolean blnVerify=fnVerifyRentPurchasePopup("verifytermofuselink","","",strUserType,blnTicketEnable);	
					}
					else
					{
						Reporter.failureReportContinue("Verify Collection for Rent  Under " + strCollectionName, "Collection not Found For Rent Under " + strCollectionName);					
					}
				}				
				}
			}		
			catch(Exception e)
			{
				e.printStackTrace();				
		}
		return blnStatus;	
	}
	
	public static boolean fnRentMoviewithCC(String strUserType,boolean blnTicketEnable) throws Throwable
	{
		Reporter.reportStep("Verifying Rent Collection to purchase with Credit Card for " + strUserType);
		boolean blnStatus=false;
		try
		{
			String strCollectionName="HOT & TRENDING";
			boolean blnCollection=fnClickRentedCollection(strCollectionName);
			//Click on Rent
			if(blnCollection)
			{
				//Find the Total Count of Collection
				String[] strCollectionTitle=fnGetCollectionList(strCollectionName,"rent");
				if(strCollectionTitle==null)
				{
					System.out.println("No Collection Found under " + strCollectionName);
					Reporter.failureReportContinue("Verify Collection Under " + strCollectionName, " No Collection Found Under " + strCollectionName);
				}
				else
				{
					Reporter.SuccessReport("Verify Collection Under " + strCollectionName, "Collection Found Under " + strCollectionName);
					List<WebElement> eleMovieList=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("title-content"));
					boolean blnRent=false;
					for(int i=0;i<eleMovieList.size();i++)
					{
						List<WebElement> eleRent=eleMovieList.get(i).findElements(By.className("icon-rented"));
						System.out.println(eleRent.size());
						if(eleRent.size()==0)
						{									
							eleMovieList.get(i).click();
							blnRent=true;
							break;
						}							
					}
					if(blnRent)
						{
							Thread.sleep(10000);
							Reporter.SuccessReport("Verify Collection for Rent  Under " + strCollectionName, "Collection Found For Rent Under " + strCollectionName);
							boolean blnVerify=fnVerifyRentPurchasePopup("verifypopup","","",strUserType,blnTicketEnable);		
							if(blnVerify)
							{		
								Reporter.reportStep("Verifying Purchase with Credit Card");
								Reporter.SuccessReport("Verify Rent Purchase Popup ", "Rent Purchase popup is displayed successfully");
								//Click on Ticket
								JSClick(TVOD.rentByCC,"Rent By Credit Card");
								Thread.sleep(10000);
								if(strUserType.equals("Anonymos"))
								{
									verifyElementDisplayed(HomePageLocators.lblSignUP,"Sign Up");
									return true;
								}
								else
								{
									String strHeaderText=getText(TVOD.RentPaymentMethodHeader,"Payment Method Header");
									if(strHeaderText.equals("SELECT YOUR PAYMENT METHOD"))
									{
										System.out.println("SELECT YOUR PAYMENT METHOD page is displayed successfully");
										Reporter.SuccessReport("Verify SELECT YOUR PAYMENT METHOD Page ", "SELECT YOUR PAYMENT METHOD Page is displayed correctly");
									}
									else
									{
										System.out.println("SELECT YOUR PAYMENT METHOD page is not displayed successfully");
										Reporter.failureReportContinue("Verify SELECT YOUR PAYMENT METHOD Page ", "SELECT YOUR PAYMENT METHOD Page is not displayed correctly");
									}
									String strMovieName=getText(TVOD.RentPaymentMethodMovieName,"Movie Name");
									if(strMovieName.length() >0 )
									{
										System.out.println("Movie Name is displayed : " + strMovieName );
										Reporter.SuccessReport("Verify Movie Name ", strMovieName + " is displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									else
									{
										System.out.println("Movie Name is not displayed");
										Reporter.failureReportContinue("Verify Movie Name ", "Movie Name is not displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									String strMoviePrice=getText(TVOD.RentPaymentMethodMoviePrice,"Movie Price");
									if(strMoviePrice.length() >0)
									{
										System.out.println("Movie Price is displayed : " + strMoviePrice );
										Reporter.SuccessReport("Verify Movie Price ", strMoviePrice + " is displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									else
									{
										System.out.println("Movie Price is not displayed");
										Reporter.failureReportContinue("Verify Movie Price ","Movie Price is not displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									String strMoviePaymentMethod=getText(TVOD.RentPaymentMethodMoviePaymentMethod,"Payment Method");
									if(strMoviePaymentMethod.equals("1 Standard TVOD purchase"))
									{
										System.out.println("1 Standard TVOD purchase is display as payment method");
										Reporter.SuccessReport("Verify Payment Method ", "1 Standard TVOD purchase is displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									else
									{
										System.out.println("1 Standard TVOD purchase is not display as payment method");
										Reporter.failureReportContinue("Verify Payment Method ", "1 Standard TVOD purchase is not displayed in SELECT YOUR PAYMENT METHOD Page");
									}
									boolean blnWatchNow=verifyElementDisplayed(TVOD.RentPaymentMethodMakePayment,"Make Payment");
									System.out.println(blnWatchNow);
									if(blnWatchNow)
									{
										Reporter.SuccessReport("Verify Make Payment Button ", "Make Payment Button is displayed in SELECT YOUR PAYMENT METHOD Page");
										boolean blnCCPopUp=fnVerifyCCPopupForRent(strMovieName,strMoviePrice);
										if(blnCCPopUp)
										{
											System.out.println("Credit Card Payment Popup is displayed successfully");
											Reporter.SuccessReport("Verify Credit Card Payment Popup ", "Credit Card Payment Popup is displayed successfully");
										}
										else
										{
											System.out.println("Credit Card Payment Popup is not displayed successfully");
											Reporter.failureReportContinue("Verify Credit Card Payment Popup ", "Credit Card Payment Popup is not displayed successfully");
										}								
									}							
									else
									{
										System.out.println("Make payment Button is not displayed");
										Reporter.failureReportContinue("Verify Make Payment Button ", "Make Payment Button is not displayed in SELECT YOUR PAYMENT METHOD Page");
									}
								}
							}
							}
							else
							{
								Reporter.failureReportContinue("Verify Rent Purchase Popup ", "Rent Purchase popup is not displayed successfully");
							}	
							//Go to Home Page			
							JSClick(HomePageLocators.imgLogo,"Home Page");
							return blnStatus;
						}						
					}				
			}		
			catch(Exception e)
			{
				e.printStackTrace();				
		}
		return blnStatus;	
	}
	
	
	public static boolean fnVerifyCCPopupForRent(String strMovieName,String strPrice) throws Throwable
	{
		boolean blnFound=true;
		String winHandleBefore="";
		try
		{
			Reporter.reportStep("Verifying Purchase with Credit Card Popup");
			winHandleBefore = driver.getWindowHandle();
			JSClick(TVOD.RentPaymentMethodMakePayment,"Make Payment");
			Thread.sleep(10000);
			//Verify Popup
			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			//Verify Movie Name
			String strMovieNamePopup=getText(TVOD.RentCCPopUpMovieName,"Payment Method");
			if(strMovieNamePopup.equals(strMovieName))
			{
				System.out.println(strMovieName + " is displayed in Popup");
				Reporter.SuccessReport("Verify Movie Name in Credit Card Payment Popup ", strMovieName + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strMovieName + " is not displayed in Popup");
				Reporter.failureReportContinue("Verify Movie Name in Credit Card Payment Popup ", strMovieName + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			String strPricePopup=getText(TVOD.RentCCPopUpMoviePrice,"Payment Method");
			if(strPrice.equals(strPricePopup.replace(".00", "")))
			{
				System.out.println(strPrice + " is displayed in Popup");
				Reporter.SuccessReport("Verify Movie Price in Credit Card Payment Popup ", strPrice + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strPrice + " is not displayed in Popup");
				Reporter.failureReportContinue("Verify Movie Price in Credit Card Payment Popup ", strPrice + " is displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			String strCCHeader=getText(TVOD.RentCCPopUpMovieCCPaymentText,"Payment Method");
			if(strCCHeader.equals("CREDIT CARD PAYMENT"))
			{
				System.out.println(strCCHeader);
				System.out.println("CREDIT CARD PAYMENT Text is displayed in Credit Card Payment Popup");
				Reporter.SuccessReport("Verify Credit Card Text in Credit Card Payment Popup ", strCCHeader + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strCCHeader);
				System.out.println("CREDIT CARD PAYMENT Text is not displayed in Credit Card Payment Popup");
				Reporter.failureReportContinue("Verify Credit Card Text in Credit Card Payment Popup ", strCCHeader + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}			
			String strCCNumberText=getText(TVOD.RentCCPopUpMovieCCPaymentCCNumberText,"Payment Method");
			if(strCCNumberText.equals("Card Number"))
			{
				System.out.println(strCCNumberText);
				System.out.println("Card Number Text is displayed in Credit Card Payment Popup");
				Reporter.SuccessReport("Verify Credit Card Number Text in Credit Card Payment Popup ", strCCNumberText + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strCCNumberText);
				System.out.println("Card Number Text is not displayed in Credit Card Payment Popup");
				Reporter.failureReportContinue("Verify Credit Card Number Text in Credit Card Payment Popup ", strCCNumberText + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			String strCCNameOnCardText=getText(TVOD.RentCCPopUpMovieCCPaymentCCNameOnCardText,"Payment Method");
			if(strCCNameOnCardText.equals("Name on Card"))
			{
				System.out.println(strCCNameOnCardText);
				System.out.println("Name on Card Text is displayed in Credit Card Payment Popup");
				Reporter.SuccessReport("Verify Name on Card Text in Credit Card Payment Popup ", strCCNameOnCardText + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strCCNameOnCardText);
				System.out.println("Name on Card Text is not displayed in Credit Card Payment Popup");
				Reporter.failureReportContinue("Verify Name on Card Text in Credit Card Payment Popup ", strCCNameOnCardText + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			String strCCExpireDateText=getText(TVOD.RentCCPopUpMovieCCPaymentCCExpireDateText,"Payment Method");
			if(strCCExpireDateText.equals("Expiry Date"))
			{
				System.out.println(strCCExpireDateText);
				System.out.println("Expire Date Text is displayed in Credit Card Payment Popup");
				Reporter.SuccessReport("Verify Expire Date Text in Credit Card Payment Popup ", strCCExpireDateText + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strCCExpireDateText);
				System.out.println("Expire Date Text is not displayed in Credit Card Payment Popup");
				Reporter.failureReportContinue("Verify Expire Date Text in Credit Card Payment Popup ", strCCExpireDateText + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			String strCCCVVText=getText(TVOD.RentCCPopUpMovieCCPaymentCCCVVText,"Payment Method");
			if(strCCCVVText.equals("CVV"))
			{
				System.out.println(strCCCVVText);
				System.out.println("CVV Text is displayed in Credit Card Payment Popup");
				Reporter.SuccessReport("Verify CVV Text in Credit Card Payment Popup ", strCCCVVText + " is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println(strCCCVVText);
				System.out.println("CVV Text is not displayed in Credit Card Payment Popup");
				Reporter.failureReportContinue("Verify CVV Text in Credit Card Payment Popup ", strCCCVVText + " is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}		
			boolean blnCCNumber=verifyElementDisplayed(TVOD.RentCCPopUpMovieCCPaymentCCNumberField,"Watch Now Button");
			if(blnCCNumber)
			{
				System.out.println("Credit Card Field is displayed correctly");
				Reporter.SuccessReport("Verify Credit Card Number Field in Credit Card Payment Popup ", "Credit Card Number Field is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println("Credit Card Field is not displayed correctly");
				Reporter.failureReportContinue("Verify Credit Card Number Field in Credit Card Payment Popup ", "Credit Card Number Field is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			boolean blnCCNameOnCard=verifyElementDisplayed(TVOD.RentCCPopUpMovieCCPaymentCCNameOnCardField,"Watch Now Button");
			if(blnCCNameOnCard)
			{
				System.out.println("Name on Card Field is displayed correctly");
				Reporter.SuccessReport("Verify Name on Card Field in Credit Card Payment Popup ", "Name on Card Field is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println("Name on Card Field is not displayed correctly");
				Reporter.failureReportContinue("Verify Name on Card Field in Credit Card Payment Popup ", "Name on Card Field is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}			
			boolean blnCCExpireDate=verifyElementDisplayed(TVOD.RentCCPopUpMovieCCPaymentCCExpireDateField,"Watch Now Button");
			if(blnCCExpireDate)
			{
				System.out.println("Expire Date Field is displayed correctly");
				Reporter.SuccessReport("Verify Expire Date Field in Credit Card Payment Popup ", "Expire Date Field is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println("Expire Field is not displayed correctly");
				Reporter.failureReportContinue("Verify Expire Date Field in Credit Card Payment Popup ", "Expire Date Field is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}
			boolean blnCCCVV=verifyElementDisplayed(TVOD.RentCCPopUpMovieCCPaymentCCCVVField,"Watch Now Button");
			if(blnCCCVV)
			{
				System.out.println("CVV Field is displayed correctly");
				Reporter.SuccessReport("Verify CVV Field in Credit Card Payment Popup ", "CVV Field is displayed in Credit Card Payment Popup");
			}
			else
			{
				System.out.println("CVV Field is not displayed correctly");
				Reporter.failureReportContinue("Verify CVV Field in Credit Card Payment Popup ", "CVV Field is not displayed in Credit Card Payment Popup");
				blnFound=false;
			}			
			// Close the new window, if that window no more required
			driver.close();
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);
			return blnFound;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			// Close the new window, if that window no more required
			driver.close();
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);
			return false;
		}
	}
	
	public static boolean fnVerifyRentals(String strTicket) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			//Go to Rentals
			navigateToRentals();
			//Key down to load full Page
			fnKeyDown();			
			//Verify Rental Header
			Reporter.reportStep("Verifying RENTALS Page");
			String strRentalHelpTextVerify="You can use a ticket to rent a movie from the rental catalog.";
			String strTitle=getText(TVOD.titleRental,"Title Rental");
			if(strTitle.toLowerCase().equals("rentals"))
			{
				Reporter.SuccessReport("Verify RENTALS Page", "Rental Page is displayed successfully");
				System.out.println("Rental Page is displayed successfully");
				String strRentalHelpText=getText(TVOD.titleRentalHelp,"Title Rental Help Text");
				if(strRentalHelpText.equals(strRentalHelpTextVerify))
				{
					System.out.println(strRentalHelpText);
					System.out.println("Help Text is displayed successfully");
					Reporter.SuccessReport("Verify Rental Page Help Text", strRentalHelpText + " in RENTALS Page is displayed successfully");
				}
				else
				{
					System.out.println(strRentalHelpText);
					System.out.println("Help Text is not displayed successfully");
					Reporter.failureReportContinue("Verify RENTALS Page Help Text", strRentalHelpText + " in RENTALS Page is not displayed successfully");
				}				
				String strRentalTicketCount=getText(TVOD.RentalTicketCount,"Title Count");
				if(strRentalTicketCount.equals(strTicket))
				{
					System.out.println(strRentalTicketCount);
					System.out.println("Ticket Count is same");
					Reporter.SuccessReport("Verify RENTALS Page Ticket Count", "Ticket Count is same Ticket Count : " + strRentalTicketCount);
				}
				else
				{
					System.out.println(strRentalTicketCount);
					System.out.println("Ticket Count is not same");
					Reporter.failureReportContinue("Verify RENTALS Page Ticket Count", "Ticket Count is not same ME : Ticket Count : " + strTicket + " Rental Ticket Count : " + strRentalTicketCount);
				}				
				String strRentalTicketExpireDate=getText(TVOD.RentalTicketExpireDate,"Title Count");
				System.out.println(strRentalTicketExpireDate);
				if(strRentalTicketExpireDate.length() >0)
				{
					Reporter.SuccessReport("Verify RENTALS Page Expire Date", "Expire Date is displayed in RENTALS Page");
				}
				else
				{
					Reporter.failureReportContinue("Verify RENTALS Page Expire Date", "Expire Date is not displayed in RENTALS Page");
				}
				boolean blnUseTicket=verifyElementDisplayed(TVOD.RentalTicketUseNow,"Use Now");
				System.out.println(blnUseTicket);
				if(blnUseTicket)
				{
					Reporter.SuccessReport("Verify RENTALS Page Use Now Button", "Use Now Button is displayed in RENTALS Page");
				}
				else
				{
					Reporter.failureReportContinue("Verify RENTALS Page Use Now Button", "Use Now Button is not displayed in RENTALS Page");
				}
				blnStatus=true;
			}		
			else
			{
				System.out.println("Rental Page is not displayed successfully");
				Reporter.failureReportContinue("Verify Rental Page", "Rental Page is not displayed successfully");
			}
			return blnStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return blnStatus;
		}
	}
	
	
	public static boolean fnVerifyRentedMoveInRental(String strTicket,String strMovieName,boolean blnFirstTime) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			boolean blnRentals=fnVerifyRentals(strTicket);
			System.out.println(blnRentals);
			if(blnRentals)
			{
				Reporter.reportStep("Verifying Movie in Rentals");
			//Count the Total Movie
			List<WebElement> eleRetalMovieList=driver.findElements(By.className("rental-info"));
			System.out.println(eleRetalMovieList.size());
			for(int i=1;i<eleRetalMovieList.size();i++)
			{
				String strContentType=eleRetalMovieList.get(i).findElement(By.className("content-type")).getText();
				System.out.println(strContentType);
				String strContentTitle=eleRetalMovieList.get(i).findElement(By.className("content-title")).getText();
				System.out.println(strContentTitle);
				String strContentDuration=eleRetalMovieList.get(i).findElement(By.className("content-duration")).getText();
				System.out.println(strContentDuration);
				String strContentCountdown=eleRetalMovieList.get(i).findElement(By.className("content-countdown")).getText();
				System.out.println(strContentCountdown);
				if(strContentTitle.equals(strMovieName))
				{					
					if(blnFirstTime)
					{
						String strCountDownText="1 month left to start watching";
						if(strCountDownText.equals(strContentCountdown))
						{
							blnStatus=true;							
						}
					}
					else
					{
						if(strContentCountdown.endsWith("left to start watching"))
						{
							blnStatus=true;							
						}
						
					}
					break;
				}				
			}
			}	
			if(blnStatus)
			{
				Reporter.SuccessReport("Verify Rented Movie in RENTALS",strMovieName + " is displayed in RENTALS Page");
			}
			else
			{
				Reporter.failureReportContinue("Verify Rented Movie in RENTALS",strMovieName + " is not displayed in RENTALS Page");
			}
			Thread.sleep(10000);
			return blnStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return blnStatus;
		}
	}
	
	public static boolean fnPlayMovieFromRentals(String strMovieName) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			Reporter.reportStep("Verifying Play Content from Rentals Page");
			//Go to Rentals
				navigateToRentals();
			//Key down to load full Page
				fnKeyDown();	
			//Verify and click on Rented Title
				String strTitle=getText(TVOD.titleRental,"Title Rental");
				if(strTitle.toLowerCase().equals("rentals"))
				{
					Reporter.SuccessReport("Verify RENTALS Page","RENTALS is displayed successfully");
					//Count the Total Movie
					List<WebElement> eleRetalMovieList=driver.findElements(By.className("rental-info"));
					System.out.println(eleRetalMovieList.size());
					for(int i=1;i<eleRetalMovieList.size();i++)
					{
						String strContentTitle=eleRetalMovieList.get(i).findElement(By.className("content-title")).getText();
						System.out.println(strContentTitle);
						String strContentCountdown=eleRetalMovieList.get(i).findElement(By.className("content-countdown")).getText();
						System.out.println(strContentCountdown);
						if(strContentTitle.equals(strMovieName))
						{		
							Reporter.SuccessReport("Verify Rented Movie in RENTALS",strMovieName + "is displayed in RENTALS Page");
							String strXPath="//div[@id='profile-mount']/div/div[2]/div/div/div["+(i+1)+"]/div/div/a";
							JSClick(By.xpath(strXPath),strContentTitle + " Rented Title");
							Thread.sleep(10000);
							//Find the Move Name
							String strMovieNameContent=getText(TVOD.RentedMovieContenDetailsMovieName,"Title Name");
							String strMovieNameNotification=getText(TVOD.RentedMovieContenDetailsNotification,"Title Notification");
							String strMovieCountdown=getText(TVOD.RentedMovieContenDetailsCountDown,"Title Countdown");
							if(strMovieName.equals(strMovieNameContent))
							{
								System.out.println(strMovieNameContent);
								System.out.println("Rented Movie " + strMovieNameContent + " is clicked Successfully");
								Reporter.SuccessReport("Verify Rented Movie in RENTALS",strMovieName + "is Selected successfully in RENTALS Page");
							}
							else
							{
								System.out.println(strMovieNameContent);
								System.out.println("Rented Movie " + strMovieNameContent + " is not clicked Successfully");
								Reporter.failureReportContinue("Verify Rented Movie in RENTALS",strMovieName + "is not Selected successfully in RENTALS Page");
							}
							if(strMovieNameNotification.equals("You rented this title."))
							{
								System.out.println(strMovieNameNotification);
								System.out.println("Rented Notification is displayed sucessfully");
								Reporter.SuccessReport("Verify Rented Movie Notification in Content Details",strMovieNameNotification + "is displayed successfully in Content Details Page");
							}
							else
							{
								System.out.println(strMovieNameNotification);
								System.out.println("Rented Notification is not displayed sucessfully");
								Reporter.failureReportContinue("Verify Rented Movie Notification in Content Details",strMovieNameNotification + "is not displayed successfully in Content Details Page");
							}	
							if(strMovieCountdown.equals(strContentCountdown))
							{
								System.out.println(strMovieCountdown);
								System.out.println("Movie countdown is same");
								Reporter.SuccessReport("Verify Rented Movie Countdows information in Content Details",strMovieCountdown + "is displayed successfully in Content Details Page");
							}
							else
							{
								System.out.println(strMovieCountdown);
								System.out.println("Movie countdown is not same");
								Reporter.failureReportContinue("Verify Rented Movie Countdows information in Content Details",strMovieCountdown + "is not displayed successfully in Content Details Page");
							}							
							boolean blnWatchNow=verifyElementDisplayed(TVOD.RentedMovieContenDetailsWatchNow,"Watch Now");
							System.out.println(blnWatchNow);
							if(blnWatchNow)
							{
								JSClick(TVOD.RentedMovieContenDetailsWatchNow,"Watch Now");
								blnStatus=true;
								Reporter.SuccessReport("Verify Rented Movie Watch Now in Content Details", "Watch Now Button is displayed successfully in Content Details Page");
							}
							else
							{
								Reporter.failureReportContinue("Verify Rented Movie Watch Now in Content Details", "Watch Now Button is not displayed successfully in Content Details Page");
							}
							break;
						}						
					}					
				}
				else
				{
					Reporter.failureReportContinue("Verify RENTALS Page","RENTALS is not displayed successfully");
				}
				if(blnStatus)
				{
					Reporter.SuccessReport("Verify Video Playback from RENTALS","Watch Now button is clicked successfully for Movie " + strMovieName);
				}
				else
				{
					Reporter.failureReportContinue("Verify Video Playback from RENTALS","Watch Now button is not clicked successfully for Movie " + strMovieName);
				}
			return blnStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return blnStatus;
		}
	}

	public static boolean fnVerifyTransactionHistory(String strMovieName) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			Reporter.reportStep("VerifyingTransaction History For Rented Movie : " + strMovieName);
			//Go to Transaction History
			navigateToTransactionHistory();
			String strMovieNameTH=getText(TVOD.tansactionHistoryMovieName,"Movie Name In Transaction History");
			String strRentTransactionDate=getText(TVOD.tansactionHistoryTransactionDate,"Transaction Date");
			System.out.println(strMovieNameTH);
			System.out.println(strRentTransactionDate);
			if(strMovieName.equals(strMovieNameTH))
			{
				System.out.println("Transaction History is Captured for Rented Movie with Ticket ==> " + strMovieNameTH);
				Reporter.SuccessReport("Verify Transaction History for Rented Movie","Transaction History For " + strMovieName + " is displayed successfully");
				blnStatus=true;
			}
			else
			{
				System.out.println("Transaction History is Captured for Rented Movie with Ticket ==> " + strMovieName);
				Reporter.failureReportContinue("Verify Transaction History for Rented Movie","Transaction History For " + strMovieName + " is not displayed successfully");
			}
			if(strRentTransactionDate.length() >0)
			{
				Reporter.SuccessReport("Verify Transaction History for Rented Movie","Transaction Date For " + strMovieName + " is displayed successfully Transaction Date : " + strRentTransactionDate);
			}
			else
			{
				Reporter.failureReportContinue("Verify Transaction History for Rented Movie","Transaction Date For " + strMovieName + " is not displayed successfully");
			}
			return blnStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verifyPlaybackAtSpecifiedTime(String itemToPlay, String userType,String strTime)throws Throwable
	{
		boolean res = false;
		try {			
			Reporter.reportStep("Verifying Playback");
			if((userType.equalsIgnoreCase("Active"))||(userType.equalsIgnoreCase("Free")) ||(userType.equalsIgnoreCase("Lapsed1stEpisode")))
			{
				Thread.sleep(20000);
				boolean blnSeekbar=true;
				while(blnSeekbar)
				{
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					if(isElementDisplayed(HeaderLocators.sbarSeekBar, "Seekbar")==true);
					{
						blnSeekbar=false;
					}
				}				
				mouseover(HeaderLocators.sbarSeekBar, "Video Area");
				verifyElementDisplayed(HeaderLocators.btnPause, "Pause Button");	
				verifyElementDisplayed(HeaderLocators.btnResize, "Pause Button");
				verifyElementDisplayed(HeaderLocators.btnResize, "Resize Button");
				verifyElementDisplayed(HeaderLocators.btnSetting, "Setting Button");
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				long starttime=System.currentTimeMillis();
				String currentPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
				String totalPlayTimeLabel = getText(HeaderLocators.lblTotalPlayTIme, "Total PlayTime");
				//Thread.sleep(12000);
				//System.out.println("Player Time Counter Starts ==>  " + starttime);
				boolean blnPlayerFlag=true;
				long endTime=00;
				int i=0;
				while(blnPlayerFlag)
				{
					//System.out.println("Counter ==> " + i);
					//i++;
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					mouseover(HeaderLocators.vdoPlayerArea, "Video Area");
					String newPlayTimeLabel = getText(HeaderLocators.lblCurrentPlayTime, "Current PlayTime");
					if(newPlayTimeLabel.equals(strTime))
					{
						blnPlayerFlag=false;
						endTime=System.currentTimeMillis();
					}
				}		
				res=true;
				//System.out.println("Player Time Counter Ends  => " + endTime);		
				long difference=((endTime-starttime)/1000);
				//System.out.println("Player Total Time Taken ==> " +difference + " Seconds");
				}			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnSearchContent(String strMovieName,String strType) throws Throwable
	{
		boolean blnFound=false;
		try
		{
			Reporter.reportStep("Search Specific Item for " + strType);
			click(HeaderLocators.headerSearch,"Search Button");
			type(HeaderLocators.txtSearch,strMovieName,"Search Textbox");
			Thread.sleep(5000);
			if(strType.equals("invalid"))
			{
				if(isElementPresent(HeaderLocators.lblSearchResultError,"Search Result Error"))
				{
					String strErrorTextVerify=getText(HeaderLocators.lblSearchResultErrorMessage, "Search Result Error Message");
					String strErrorText="Sorry, we could not find what you were looking for. Try again?";
					System.out.println(strErrorTextVerify);
					if(strErrorText.equals(strErrorTextVerify))
					{
						Reporter.SuccessReport("Verify Search for Invalid Movie","Error Message is Displayed successfully Error Message : " + strErrorTextVerify);
						blnFound=true;					
					}
					else
					{
						Reporter.failureReportContinue("Verify Search for Invalid Movie","Error Message is not Displayed successfully Error Message : " + strErrorTextVerify);
					}					
				}
				else
				{
					Reporter.failureReportContinue("Verify Search for Invalid Movie","Error Message is not Displayed successfully");
				}
				//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
			}
			else
			{
				String strText=getText(By.xpath(".//*[@class='search-result-title']/p"), "Search Result");
				System.out.println(strText);
				if(strText.toLowerCase().equals(strMovieName.toLowerCase()))
				{
					System.out.println(strText);
					String strTypeVerify=getText(HeaderLocators.lblSearchResultType, "Search Result Type");
					System.out.println(strTypeVerify);
					String strMatch=strType;
					if(strType.toLowerCase().equals("rent"))
					{
						strMatch="Movie";
						//Verify Movie Poster
						String strTag=driver.findElement(By.xpath("//*[@id='search']/a")).getAttribute("class");
						System.out.println(strTag);
						if(strTag.contains("hooq-callout-flag"))
						{
							Reporter.SuccessReport("Verify Search for Content Type","Rent Callout Flag is Displayed successfully.");
						}
						else
						{
							Reporter.failureReportContinue("Verify Search for Content Type","Rent Callout Flag is not Displayed successfully.");
						}						
					}
					if(strTypeVerify.equals(strMatch))
					{
						Reporter.SuccessReport("Verify Search for Content Type",strTypeVerify + "Content Type is Displayed successfully.");
					}
					else
					{
						Reporter.failureReportContinue("Verify Search for Content Type",strTypeVerify + "Content Type is not Displayed successfully.");
					}				
					JSClick(By.xpath(".//*[@class='search-result-title']/p"),strText);	
					Thread.sleep(10000);
					//Verify the Content Details Page
					fnVerifyContentDetails(strMovieName,strType);					
					blnFound=true;
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}		
		return blnFound;		
	}	
	
	public static boolean fnVerifyContentDetails(String strMovieName,String strType) throws Throwable
	{
		boolean blnStatus=false;
		try
		{
			if(strType.toLowerCase().equals("movie"))
			{
				Reporter.reportStep("Verify Content Details for Movie " + strMovieName);
			//Title
			String strPlayerTitle=getText(HeaderLocators.lblCDPlayerTitle,"Player Title");
			if(strPlayerTitle.toLowerCase().equals(strMovieName.toLowerCase()))
			{
				System.out.println(strPlayerTitle);
				Reporter.SuccessReport("Verify Content Detail Page",strMovieName + " is Displayed successfully.");
				blnStatus=true;
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strMovieName + " is not Displayed.");
			}
			String strSubTitle=getText(HeaderLocators.lblCDSubtitle,"SubTitle");
			if(strSubTitle.equals("Subtitles:"))
			{
				System.out.println(strSubTitle);
				Reporter.SuccessReport("Verify Content Detail Page",strSubTitle + " Label is Displayed successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strSubTitle + " Label is not Displayed.");
			}			
			String strSubTitleText=getText(HeaderLocators.lblCDSubtitleText,strType +" SubTitle ");
			if(strSubTitleText.length()>0)
			{
				System.out.println(strSubTitleText);
				Reporter.SuccessReport("Verify Content Detail Page",strSubTitleText + "  is Displayed in Subtitle successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strSubTitleText + "  is not Displayed in Subtitle.");
			}
			String strDuration=getText(HeaderLocators.lblCDDuration,"Duration");
			if(strDuration.equals("Duration:"))
			{
				System.out.println(strDuration);
				Reporter.SuccessReport("Verify Content Detail Page",strDuration + "  Label is Displayed successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strDuration + "  Label is not Displayed.");
			}
			String strDurationValue=getText(HeaderLocators.lblCDDurationText,strType +" Duration");
			if(strDurationValue.length()>0)
			{
				System.out.println(strDurationValue);
				Reporter.SuccessReport("Verify Content Detail Page",strDurationValue + "  is Displayed in Duration successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strDurationValue + "  is not Displayed in Duration.");
			}
			String strCast=getText(HeaderLocators.lblCDCast,"Cast");
			if(strCast.equals("Cast:"))
			{
				System.out.println(strCast);
				Reporter.SuccessReport("Verify Content Detail Page",strCast + "  Label is Displayed successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strCast + "  Label is not Displayed.");
			}
			String strCastValue=getText(HeaderLocators.lblCDCastText,strType +" Cast");
			if(strCastValue.length()>0)
			{
				System.out.println(strCastValue);
				Reporter.SuccessReport("Verify Content Detail Page",strCastValue + "  is Displayed in Cast successfully.");
			}			
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strCastValue + "  is not Displayed in Cast.");
			}
			String strTitleDescription=getText(HeaderLocators.lblCDTitleDescription,strType +" Title Description");
			if(strTitleDescription.length()>0)
			{
				System.out.println(strTitleDescription);
				Reporter.SuccessReport("Verify Content Detail Page",strTitleDescription + "  is Displayed in Title Description successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strTitleDescription + "  is not Displayed in Title Description.");
			}
			List<WebElement> eleList=driver.findElement(HeaderLocators.lblCDTitleLinks).findElements(By.tagName("a"));
			System.out.println(eleList.size());
			if(eleList.size() >0)
			{
				System.out.println(eleList.size());
				for(int i=0;i<eleList.size();i++)
				{
					Reporter.SuccessReport("Verify Content Detail Page",eleList.get(i).getText() + " genres link is Displayed in successfully.");
				}
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page","Genres link is not Displayed.");
			}
			if(isElementPresent(HeaderLocators.lblCDImage,strType +" Image"))
			{
				System.out.println(strType + " Image is Presenrt");
				Reporter.SuccessReport("Verify Content Detail Page",strType + " Poster is Displayed in successfully.");
			}
			else
			{
				Reporter.failureReportContinue("Verify Content Detail Page",strType + " Poster is not Displayed.");
			}	
			}
			else if(strType.toLowerCase().equals("tv series"))
			{
				Reporter.reportStep("Verify Content Details for TV Series " + strMovieName);
				//Title
				String strPlayerTitle=getText(HeaderLocators.lblCDTVPlayerTitle,"Player Title");
				if(strPlayerTitle.toLowerCase().equals(strMovieName.toLowerCase()))
				{
					System.out.println(strPlayerTitle);
					Reporter.SuccessReport("Verify Content Detail Page",strMovieName + " is Displayed successfully.");
					blnStatus=true;
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strMovieName + " is not Displayed.");
				}
				String strSubTitle=getText(HeaderLocators.lblCDTVSubtitle,"SubTitle");
				if(strSubTitle.equals("Subtitles:"))
				{
					System.out.println(strSubTitle);
					Reporter.SuccessReport("Verify Content Detail Page",strSubTitle + " Label is Displayed successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strSubTitle + " Label is not Displayed.");
				}			
				String strSubTitleText=getText(HeaderLocators.lblCDTVSubtitleText,strType +" SubTitle ");
				if(strSubTitleText.length()>0)
				{
					System.out.println(strSubTitleText);
					Reporter.SuccessReport("Verify Content Detail Page",strSubTitleText + "  is Displayed in Subtitle successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strSubTitleText + "  is not Displayed in Subtitle.");
				}
				String strCast=getText(HeaderLocators.lblCDTVCast,"Cast");
				if(strCast.equals("Cast:"))
				{
					System.out.println(strCast);
					Reporter.SuccessReport("Verify Content Detail Page",strCast + "  Label is Displayed successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strCast + "  Label is not Displayed.");
				}
				String strCastValue=getText(HeaderLocators.lblCDTVCastText,strType +" Cast");
				if(strCastValue.length()>0)
				{
					System.out.println(strCastValue);
					Reporter.SuccessReport("Verify Content Detail Page",strCastValue + "  is Displayed in Cast successfully.");
				}			
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strCastValue + "  is not Displayed in Cast.");
				}
				String strTitleDescription=getText(HeaderLocators.lblCDTVTitleDescription,strType +" Title Description");
				if(strTitleDescription.length()>0)
				{
					System.out.println(strTitleDescription);
					Reporter.SuccessReport("Verify Content Detail Page",strTitleDescription + "  is Displayed in Title Description successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strTitleDescription + "  is not Displayed in Title Description.");
				}
				List<WebElement> eleList=driver.findElement(HeaderLocators.lblCDTVTitleLinks).findElements(By.tagName("a"));
				System.out.println(eleList.size());
				if(eleList.size() >0)
				{
					System.out.println(eleList.size());
					for(int i=0;i<eleList.size();i++)
					{
						Reporter.SuccessReport("Verify Content Detail Page",eleList.get(i).getText() + " genres link is Displayed in successfully.");
					}
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page","Genres link is not Displayed.");
				}
				if(isElementPresent(HeaderLocators.lblCDTVImage,strType +" Image"))
				{
					System.out.println(strType + " Image is Presenrt");
					Reporter.SuccessReport("Verify Content Detail Page",strType + " Poster is Displayed in successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strType + " Poster is not Displayed.");
				}	
				//Verify the Seasons and Episode
				List<WebElement> eleListSeason=driver.findElement(HeaderLocators.lblCDTVPlayList).findElements(By.className("seasons"));
				if(eleListSeason.size() > 0)
				{
					Reporter.SuccessReport("Verify Content Detail Page",strType + " Season is Displayed in successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strType + " Season is not Displayed.");
				}
				List<WebElement> eleListEpisode=driver.findElement(HeaderLocators.lblCDTVPlayList).findElements(By.className("episode-item-name"));
				if(eleListEpisode.size()>0)
				{
					Reporter.SuccessReport("Verify Content Detail Page",strType + " Episode List is Displayed in successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strType + " Episode List is not Displayed.");
				}
			}
			else
			{
				Reporter.reportStep("Verify Content Details for Rent " + strMovieName);
				//Title
				String strPlayerTitle=getText(HeaderLocators.lblCDRNTPlayerTitle,"Player Title");
				if(strPlayerTitle.toLowerCase().equals(strMovieName.toLowerCase()))
				{
					System.out.println(strPlayerTitle);
					Reporter.SuccessReport("Verify Content Detail Page",strMovieName + " is Displayed successfully.");
					blnStatus=true;
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strMovieName + " is not Displayed.");
				}
				String strDuration=getText(HeaderLocators.lblCDRNTDuration,"Duration");
				if(strDuration.equals("Duration:"))
				{
					System.out.println(strDuration);
					Reporter.SuccessReport("Verify Content Detail Page",strDuration + "  Label is Displayed successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strDuration + "  Label is not Displayed.");
				}
				String strDurationValue=getText(HeaderLocators.lblCDRNTDurationText,strType +" Duration");
				if(strDurationValue.length()>0)
				{
					System.out.println(strDurationValue);
					Reporter.SuccessReport("Verify Content Detail Page",strDurationValue + "  is Displayed in Duration successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strDurationValue + "  is not Displayed in Duration.");
				}
				String strCast=getText(HeaderLocators.lblCDRNTCast,"Cast");
				if(strCast.equals("Cast:"))
				{
					System.out.println(strCast);
					Reporter.SuccessReport("Verify Content Detail Page",strCast + "  Label is Displayed successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strCast + "  Label is not Displayed.");
				}
				String strCastValue=getText(HeaderLocators.lblCDRNTCastText,strType +" Cast");
				if(strCastValue.length()>0)
				{
					System.out.println(strCastValue);
					Reporter.SuccessReport("Verify Content Detail Page",strCastValue + "  is Displayed in Cast successfully.");
				}			
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strCastValue + "  is not Displayed in Cast.");
				}
				String strTitleDescription=getText(HeaderLocators.lblCDRNTTitleDescription,strType +" Title Description");
				if(strTitleDescription.length()>0)
				{
					System.out.println(strTitleDescription);
					Reporter.SuccessReport("Verify Content Detail Page",strTitleDescription + "  is Displayed in Title Description successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strTitleDescription + "  is not Displayed in Title Description.");
				}
				List<WebElement> eleList=driver.findElement(HeaderLocators.lblCDRNTTitleLinks).findElements(By.tagName("a"));
				System.out.println(eleList.size());
				if(eleList.size() >0)
				{
					System.out.println(eleList.size());
					for(int i=0;i<eleList.size();i++)
					{
						Reporter.SuccessReport("Verify Content Detail Page",eleList.get(i).getText() + " genres link is Displayed in successfully.");
					}
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page","Genres link is not Displayed.");
				}
				if(isElementPresent(HeaderLocators.lblCDRNTImage,strType +" Image"))
				{
					System.out.println(strType + " Image is Presenrt");
					Reporter.SuccessReport("Verify Content Detail Page",strType + " Poster is Displayed in successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strType + " Poster is not Displayed.");
				}	
				List<WebElement> eleListImage=driver.findElements(By.xpath(".//*[@class='image-wrapper rental-callout-flag']"));
				System.out.println(eleListImage.size());
				if(eleListImage.size() > 0)
				{
					Reporter.SuccessReport("Verify Content Detail Page",strType + " Poster is Displayed with Rental Call Out Flag in successfully.");
				}
				else
				{
					Reporter.failureReportContinue("Verify Content Detail Page",strType + " Poster is not Displayed with Rental Call Out Flag.");
				}
				List<WebElement> eleRentButton=driver.findElements(By.id("btnRent"));
				if(eleRentButton.size() >0)
				{
					String strButtonText=eleRentButton.get(0).getText();
					System.out.println(strButtonText);
					if(strButtonText.startsWith("Rent for"))
					{
						Reporter.SuccessReport("Verify Content Detail Page",strButtonText + " Price is Displayed successfully.");
					}
					else
					{
						Reporter.failureReportContinue("Verify Content Detail Page",strButtonText + " Price is Displayed successfully.");
					}
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnStatus;
	}
	
	public static boolean verifyLoginWithAlreadyHaveAccount(String email,String type, String userType)throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("AlReady Have an Account Verification");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");			
			click(HeaderLocators.lnksinup,"Clicked on Dont have an account");			
			click(HeaderLocators.lnkAlreadyaccount,"Clicked on Already have an account");			
			click(HeaderLocators.lnkloginwithmail,"Clicked on login with mail or facebook");
			
			//Thread.sleep(10000);
			//click(HeaderLocators.headerSignIn,"Log In");
			
			type(txtEmail, email, "Email Address");
			Thread.sleep(2000);
			JSClick(btnDone, "Done");		
			//Thread.sleep(10000);
			if(type.equals("valid")||type.equals("new"))
			{
				if(isElementDisplayed(HomePageLocators.lblVerifyEmail))
				{
					click(HomePageLocators.btnPassword,"Enter Pasword");
					Thread.sleep(2000);
					if(isElementDisplayed(txtEmail))
					{
						type(txtEmail, email, "Email Address");
					}
					type(HomePageLocators.edtPassword, "123456", "Enter Password");
					//type(HomePageLocators.edtPassword, "password1", "Enter Password");
					Thread.sleep(3000);
					click(HomePageLocators.btnPasswordOK,"OK");					
				}
			}			
			if(type.equals("invalid"))
			{
				verifyElementDisplayed(lblInvalidEmail, "Invalid email address");
			}
			else if(type.equals("invalidNonExistent"))
			{
				verifyElementDisplayed(lblNonExistingEmail, "Account does not exist");
			}
			else if(type.equals("valid")||type.equals("new"))
			{
				res=verifyElementDisplayed(HeaderLocators.headerUserProfile, "User Profile");				
			}			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyLoginWithResendEmail(String email,String type, String userType)throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("Login With Email Confirmation");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");
			//driver.findElementByXPath(".//*[@id='email']").sendKeys( "cigniinacti01@yopmail.com");
			type(txtEmail, email, "Email Address");
			Thread.sleep(2000);
			JSClick(btnDone, "Done");		
			Thread.sleep(10000);
			if(isElementDisplayed(HomePageLocators.lblVerifyEmail))
			{
				click(HeaderLocators.lnkresendemail,"Clicked on Resend Email");
				Thread.sleep(10000);
				boolean blnStatus=confirmEmailAddress(email);
				System.out.println(blnStatus);
				Reporter.reportStep("Login With Resend Email Confirmation");
				if(blnStatus)
				{
					List<WebElement> eleList=driver.findElements(By.id("display-name"));
					if(eleList.size()>0)
					{
						Reporter.SuccessReport("Verify Login With Resend Email", "Login is Successful with Resend Email Confirmation");
					}
					else
					{
						//Thread.sleep(10000);
							click(HeaderLocators.headerSignIn,"Log In");
							type(txtEmail, email, "Email Address");
							Thread.sleep(2000);
							JSClick(btnDone, "Done");		
							Thread.sleep(10000);
							List<WebElement> eleList1=driver.findElements(By.id("display-name"));
							if(eleList1.size()>0)
							{
								Reporter.SuccessReport("Verify Login With Resend Email", "Login is Successful with Resend Email Confirmation");
							}
							else
							{
								Reporter.failureReportContinue("Verify Login With Resend Email", "Login is not done with Resend Email Confirmation");
							}
					}
				}
				else
				{
					Reporter.failureReportContinue("Verify Login With Resend Email", "Login Email Confirmation Fail");
				}
					
			}
				      
	       	      
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean verifyLoginWithInvalidMobileNo()throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("Login with Invalid Nuber Verification");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");
			click(HeaderLocators.lnkmobileNo,"Clicked on Mobile No");
			driver.findElementByXPath("//*[@id='mobile']").sendKeys( "alpha_#$$");
		     Thread.sleep(2000);
			JSClick(btnDone, "Done");		
			Thread.sleep(5000);
			String strErrorMsg=getText(HeaderLocators.errorMsg,"Error Message");
			System.out.println(strErrorMsg);
			if(strErrorMsg.equals("Phone number should be numeric"))
			{
				Reporter.SuccessReport("Verify Invalid Mobile Number ",strErrorMsg +  " is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Invalid Mobile Number ","Eorror Msg : Phone number should be numeric is not Displayed Successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifySignupHere(boolean blnSkip)throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("Verifi Signup Here Link");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");			
			boolean blnFound=click(HeaderLocators.lnksignuphere,"Clicked on Don't have an account? Sign up!");
			if(blnFound)
			{
				Reporter.SuccessReport("Verify Signup Here Link", "Sign Up Link  is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Signup Here Link", "Sign Up Link  is not Displayed Successfully");
			}
			// Verify Sign Up Text 
			if(isElementDisplayed(By.xpath("//*[@class='card-block']")))
			{
				Reporter.SuccessReport("Verify Signup ", "Sign Up Text  is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Signup", "Sign Up Text is not Displayed Successfully");
			}
			
			// verify Email address Text box
			if(isElementDisplayed(By.xpath("//*[@id='email']")))
			{
				Reporter.SuccessReport("Verify Email address Text box", "Email address Text box is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Email address Text box", "Email address Text box is not Displayed Successfully");
			}
			
			// verify Done Button
			if(isElementDisplayed(By.xpath("//*[@id='submit-button']")))
			{
				Reporter.SuccessReport("Verify Done Button", "Done Button is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Done Button", "Done Button is not Displayed Successfully");
			}
			
			// Verify Skip link
			if(isElementDisplayed(By.id("skip-button")))
			{
				Reporter.SuccessReport("Verify Skip link", "Skip link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Skip link", "Skip link is not Displayed Successfully");
			}
			
			// verify Sign up Note
			if(isElementDisplayed(By.xpath("//*[@class='signup-note']")))
			{
				Reporter.SuccessReport("Verify Sign up Note", "Sign up Note is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Sign up Note", "Sign up Note is not Displayed Successfully");
			}
			Reporter.reportStep("Verifi Terms and Conditions of Use Link");
			if(blnSkip)
			{
				boolean blnSkip1=click(By.id("skip-button"),"Skip Link");
				if(blnSkip1)
				{
					Reporter.SuccessReport("Verify TERMS AND CONDITIONS", "Skip Link is Click for Next TERMS AND CONDITIONS Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify TERMS AND CONDITIONS", "Skip Link is Click for Next TERMS AND CONDITIONS Link is not Displayed Successfully");
				}
			}
			//Click on Terms and Condition
			boolean blnTerms=click(HeaderLocators.lnkTermsandCndtn,"Cliked on Terms and Conditions of Use ");
			if(blnTerms)
			{
				Reporter.SuccessReport("Verify TERMS AND CONDITIONS", "TERMS AND CONDITIONS Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify TERMS AND CONDITIONS", "TERMS AND CONDITIONS Link is not Displayed Successfully");
			}
			Thread.sleep(5000);
			//Get the  Text from Tersm and Condition Page
			String strText=driver.findElement(By.xpath(".//*[@class='container ']/div/div/h1")).getText();
			System.out.println(strText);
			if(strText.contains("GENERAL TERMS AND CONDITIONS FOR HOOQ"))
			{
				Reporter.SuccessReport("Verify TERMS AND CONDITIONS", "TERMS AND CONDITIONS Page is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify TERMS AND CONDITIONS", "TERMS AND CONDITIONS Page is Displayed Successfully");
			}
			//Click on HOOQ Logo
				click(HeaderLocators.hooqHeader,"Cliked on HOOQ");
				Thread.sleep(5000);
	      
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifyPrivacyPlocy(boolean blnSkip)throws Throwable
	{
		boolean res = false;
		try {
			Reporter.reportStep("Verifi Signup Here Link");
			//Thread.sleep(10000);
			click(HeaderLocators.headerSignIn,"Log In");			
			boolean blnFound=click(HeaderLocators.lnksignuphere,"Clicked on Don't have an account? Sign up!");
			if(blnFound)
			{
				Reporter.SuccessReport("Verify Signup Here Link", "Sign Up Link  is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Signup Here Link", "Sign Up Link  is not Displayed Successfully");
			}
			// Verify Sign Up Text 
			if(isElementDisplayed(By.xpath("//*[@class='card-block']")))
			{
				Reporter.SuccessReport("Verify Signup ", "Sign Up Text  is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Signup", "Sign Up Text is not Displayed Successfully");
			}
			
			// verify Email address Text box
			if(isElementDisplayed(By.xpath("//*[@id='email']")))
			{
				Reporter.SuccessReport("Verify Email address Text box", "Email address Text box is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Email address Text box", "Email address Text box is not Displayed Successfully");
			}
			
			// verify Done Button
			if(isElementDisplayed(By.xpath("//*[@id='submit-button']")))
			{
				Reporter.SuccessReport("Verify Done Button", "Done Button is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Done Button", "Done Button is not Displayed Successfully");
			}
			
			// Verify Skip link
			if(isElementDisplayed(By.id("skip-button")))
			{
				Reporter.SuccessReport("Verify Skip link", "Skip link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Skip link", "Skip link is not Displayed Successfully");
			}
			
			// verify Sign up Note
			if(isElementDisplayed(By.xpath("//*[@class='signup-note']")))
			{
				Reporter.SuccessReport("Verify Sign up Note", "Sign up Note is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Sign up Note", "Sign up Note is not Displayed Successfully");
			}
			Reporter.reportStep("Verifi PRIVACY POLICY of Use Link");
			if(blnSkip)
			{
				boolean blnSkip1=click(By.id("skip-button"),"Skip Link");
				if(blnSkip1)
				{
					Reporter.SuccessReport("Verify PRIVACY POLICY", "Skip Link is Click for Next PRIVACY POLICY Link is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify PRIVACY POLICY", "Skip Link is Click for Next PRIVACY POLICY Link is not Displayed Successfully");
				}
			}
			//Click on Terms and Condition
			boolean blnTerms=click(HeaderLocators.lnkPrivacyPolicy,"Cliked on Privacy Policy of Use ");
			if(blnTerms)
			{
				Reporter.SuccessReport("Verify PRIVACY POLICY", "PRIVACY POLICY Link is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify PRIVACY POLICY", "PRIVACY POLICY Link is not Displayed Successfully");
			}
			Thread.sleep(5000);
			//Get the  Text from Tersm and Condition Page
			String strText=driver.findElement(By.xpath(".//*[@class='container ']/div/div/h1")).getText();
			System.out.println(strText);
			if(strText.contains("PRIVACY POLICY"))
			{
				Reporter.SuccessReport("Verify PRIVACY POLICY", "PRIVACY POLICY Page is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify PRIVACY POLICY", "PRIVACY POLICY Page is Displayed Successfully");
			}
			//Click on HOOQ Logo
				click(HeaderLocators.hooqHeader,"Cliked on HOOQ");
				Thread.sleep(5000);
	            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public static boolean verifyConitnueWatching(String email,String type, String userType, String itemToPlay)throws Throwable
	{
		boolean res = false;
		try {
				Reporter.reportStep("Verify Continue Watching");
				// Verify You Were Watching section			
				String strcontinuewatching= driver.findElement(By.xpath(" //*[@class='galleria-section hidden-xs-down']/a")).getText();
				System.out.println(" "+ strcontinuewatching);
				//verify content is playing from You were watching section
				if(strcontinuewatching.contains("YOU WERE WATCHING"))
				{
					Reporter.SuccessReport("Verify YOU WERE WATCHING Section ", "YOU WERE WATCHING is Displayed Successfully");
					//Verify Total Item under Continue Watching Section
					List<WebElement> elementList=driver.findElements(By.xpath("//*[@class='galleria-section hidden-xs-down']/div/div"));
					if(elementList.size() >0)
					{
						Reporter.SuccessReport("Verify YOU WERE WATCHING Section ", "Total : " +elementList.size()+" Movie / TVSeries displayed in YOU WERE WATCHING is Displayed Successfully");
						String strMovieName=driver.findElement(By.xpath(".//*[@id='titles-mount']/div[1]/div/div[1]/div[3]")).getText();
						JSClick(playcontinuewatching, "Clicked on Play " + strMovieName);
						verifyPlaybackOfItem(strMovieName,userType);
					}
					else
					{
						Reporter.failureReportContinue("Verify YOU WERE WATCHING Section ", "Total : " +elementList.size()+" Movie / TVSeries displayed in YOU WERE WATCHING is Displayed");
					}
				}
				else
				{
					Reporter.failureReportContinue("Verify YOU WERE WATCHING Section ", "YOU WERE WATCHING is not Displayed.");
				}
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static boolean VerifyContentDetailsPage(String userType) throws Throwable
	{
		boolean res = false;
		try
		{		
			Reporter.reportStep("Login and Verification");
			WebElement ele=driver.findElement(By.xpath("//*[@id='titles-mount']/div[2]/div/div[2]"));
			String strMovieName=ele.findElement(By.className("galleria-thumbnail-title")).getText();
			System.out.println("Movie Name ==> " + strMovieName);
			mouseHoverByJavaScript(ele);
			mouseHoverByJavaScript(ele);
			Thread.sleep(5000);
			WebElement eleSeeMore=ele.findElement(By.className("more-info"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", eleSeeMore);
			Thread.sleep(5000);
			
			JSClick(seemoreViewDetails, "View Details");
			Thread.sleep(10000);
			
			// Verify Movie Name
			if(isElementDisplayed(By.xpath("//*[@id='card-description']/div/h4")))
			{
				Reporter.SuccessReport("Verify Movie Name ", " Movie Name is Displayed Successfully");
				System.out.println("Movie name displayed");
			}
			else
			{
				Reporter.failureReportContinue("Verify  Movie Name", " Movie Name is not Displayed Successfully");
			}
			
			
			WebElement element=driver.findElement(By.xpath("//*[@id='card-description']/div/div/div[1]/div[1]/div[1]"));
			String strsubtitle=element.findElement(By.xpath("//*[@id='card-description']/div/div/div[1]/div[1]/div[2]")).getText();
			String strduration=element.findElement(By.xpath("//*[@id='card-description']/div/div/div[1]/div[2]/div[2]")).getText();
			
			
			 if(strsubtitle!=null)
			{
				System.out.println("Subtitle : " + strsubtitle);
				Reporter.SuccessReport("Verify subtitle details  " + strsubtitle, "Subtitle is not empty " + strsubtitle);
			
			}
			 
			 
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verifyGeoLocation(String userType,String strPrefix)throws Throwable
	{
		boolean res = false;
		try {		
			Reporter.reportStep("Verify Geo Location for Login ");
			String strgeolocation= driver.findElement(By.xpath(" //*[@class='input-group-addon']")).getText();
			if (strgeolocation.equals(strPrefix))
			{ 
				Reporter.SuccessReport("Verify Geo Location for Login ", strPrefix +" Geo Location for Login is Displayed Successfully");
			}
			else
			{
				Reporter.failureReportContinue("Verify Geo Location for Login ", strPrefix +" Geo Location for Login is not Displayed Successfully");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean verifySignupGeolocation(String strPrefix)throws Throwable
	{
		boolean res = false;
		try {		
			Reporter.reportStep("Verify Geo Location for SignUp ");
				Thread.sleep(3000);
				//click(HeaderLocators.headerExploreMenu,"Header Explore Menu");
				click(HeaderLocators.headerSignUp,"Sign Up");
				click(HeaderLocators.lnkskipsignup,"Skip");			
				String strgeolocation= driver.findElement(By.xpath("//*[@id='mobile-prefix']")).getText();
				if (strgeolocation.equals(strPrefix))
				{ 
					Reporter.SuccessReport("Verify Geo Location for SignUp ", strPrefix +" Geo Location for SignUp is Displayed Successfully");
				}
				else
				{
					Reporter.failureReportContinue("Verify Geo Location for SignUp ", strPrefix +" Geo Location for SignUp is not Displayed Successfully");
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean fnVerifyBrowseFilterGenre(String strUserType) throws Throwable
	{
		boolean blnFilter=false;
		try
		{
			//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Verifying Genre Filter for " + strUserType + "  User");
			//Go To Browse and click on All Movies
				fnClickBrowseCollection("ALL MOVIES");
			//Get The Total Count of the Items	
				//Click on Genre
				String[]  eleItemList=fnGetCollectionList("ALL MOVIES","Browse");			
				if(eleItemList==null)
				{
					System.out.println("No Collection Found under ALL MOVIES");
					Reporter.failureReportContinue("Collection under ALL MOVIES", "No Collection Found under ALL MOVIES");
				}
				else
				{						
							Reporter.SuccessReport("Collection under ALL MOVIES", "Collection Found under ALL MOVIES");
						//Click on Genre
							JSClick(HomePageLocators.genre,"Genere");
						//Click on All Check Box
							String strGenreValue=getText(HomePageLocators.genreAll,"Genere All");
							if(strGenreValue.equals("All")==false)
							{
								JSClick(HomePageLocators.genreAll,"Genere All");
							}												 			
							String strGenreValue1=getText(HomePageLocators.genreAll,"Genere All");
							System.out.println(strGenreValue1);		
							if(strGenreValue1.equals("All"))
							{
								Reporter.SuccessReport("Select Filter", "Genere All is Selected in All Movies Collection");
								//Get the Total Count of the 
								List<WebElement> eleMovieListAll=driver.findElement(HomePageLocators.browseCollectionTitleList).findElements(By.className("content-title"));
								System.out.println(eleMovieListAll.size());
								if(eleMovieListAll.size() >0)
								{
									Reporter.SuccessReport("Collection under ALL MOVIES For Genre All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
									//Again Go to the Genere and Click on War
									JSClick(HomePageLocators.genreWar,"Genere War");
									Thread.sleep(25000);
									String strGenreValueWar=getText(HomePageLocators.genreAll,"Genere All");
									System.out.println(strGenreValueWar);	
									if(strGenreValueWar.equals("War"))
									{
										Reporter.SuccessReport("Select Filter", "Genere War is Selected in All Movies Collection");
										List<WebElement> eleMovieListWar=driver.findElement(HomePageLocators.browseCollectionTitleList).findElements(By.className("content-title"));
										System.out.println("War ==> " + eleMovieListWar.size());	
										if(eleMovieListWar.size()>0)
										{
											Reporter.SuccessReport("Collection under ALL MOVIES For Genre War ", "Collecton Count : " + eleMovieListWar.size() +" Collection Found.");
											if(eleMovieListAll.size() != eleMovieListWar.size())
											{
												Reporter.SuccessReport("Verify Genre Filter ", "Genre Filter is Working Fine");
												blnFilter=true;
											}
											else
											{
												System.out.println("Filter is not Working Fine");
												Reporter.failureReportContinue("Verify Genre Filter ", "Genre Filter is not Working.");
											}
										}
										else
										{
											Reporter.failureReportContinue("Collection under ALL MOVIES For Genre War ", "Collecton Count : " + eleMovieListWar.size() +" Collection Found.");
										}
										
									}
									else
									{
										Reporter.failureReportContinue("Select Filter", "Genere War is not Selected in All Movies Collection");
									}									
								}
								else
								{
									Reporter.failureReportContinue("Collection under ALL MOVIES For Genre All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
								}
							
							}
							else
							{
								Reporter.failureReportContinue("Select Filter", "Genere All is not Selected in All Movies Collection");
							}
				}				
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return blnFilter;
	}
	
	public static boolean fnVerifyBrowseFilterSubTitles(String strUserType) throws Throwable
	{
		boolean blnFilter=false;
		try
		{
			//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Verifying Subtitle Filter for " + strUserType + "  User");
			//Go To Browse and click on All Movies
				fnClickRentedCollection("ACTION CARTOONS");
			//Get The Total Count of the Items	
				//Click on Genre
				String[]  eleItemList=fnGetCollectionList("ACTION CARTOONS","Browse");			
				if(eleItemList==null)
				{
					System.out.println("No Collection Found under ACTION CARTOONS");
					Reporter.failureReportContinue("Collection under ACTION CARTOONS", "No Collection Found under ACTION CARTOONS");
				}
				else
				{						
							Reporter.SuccessReport("Collection under ACTION CARTOONS", "Collection Found under ACTION CARTOONS");
						//Click on Genre
							JSClick(HomePageLocators.subtitle,"Subtitles");
						//Click on All Check Box
							String strSubTitleValue=getText(HomePageLocators.subtitleValue,"SubTitle");
							if(strSubTitleValue.equals("All")==false)
							{
								JSClick(HomePageLocators.subtitleAll,"Subtitle All");
							}												 			
							String strSubTitleValue1=getText(HomePageLocators.subtitleValue,"SubTitle");
							System.out.println(strSubTitleValue1);		
							if(strSubTitleValue1.equals("All"))
							{
								Reporter.SuccessReport("Select Filter", "Subtitle All is Selected in ACTION CARTOONS Collection");
								//Get the Total Count of the 
								List<WebElement> eleMovieListAll=driver.findElement(HomePageLocators.browseCollectionTitleList).findElements(By.className("content-title"));
								System.out.println(eleMovieListAll.size());
								if(eleMovieListAll.size() >0)
								{
									Reporter.SuccessReport("Collection under ACTION CARTOONS For Subtitle All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
									//Again Go to the Genere and Click on War
									JSClick(HomePageLocators.subtitleBahasa,"Subtitle Bahasa Indonesia");
									Thread.sleep(25000);
									String strGenreValueWar=getText(HomePageLocators.subtitleValue,"Subtitle All");
									System.out.println(strGenreValueWar);	
									if(strGenreValueWar.equals("Bahasa Indonesia"))
									{
										Reporter.SuccessReport("Select Filter", "Subtitle Bahasa Indonesia is Selected in ACTION CARTOONS Collection");
										List<WebElement> eleMovieListWar=driver.findElement(HomePageLocators.browseCollectionTitleList).findElements(By.className("content-title"));
										System.out.println("War ==> " + eleMovieListWar.size());	
										if(eleMovieListWar.size()>0)
										{
											Reporter.SuccessReport("Collection under ACTION CARTOONS For Subtitle War ", "Collecton Count : " + eleMovieListWar.size() +" Collection Found.");
											if(eleMovieListAll.size() != eleMovieListWar.size())
											{
												Reporter.SuccessReport("Verify Subtitle Filter ", "Subtitle Filter is Working Fine");
												blnFilter=true;
											}
											else
											{
												System.out.println("Filter is not Working Fine");
												Reporter.failureReportContinue("Verify Subtitle Filter ", "Subtitle Filter is not Working.");
											}
										}
										else
										{
											Reporter.failureReportContinue("Collection under ACTION CARTOONS For Subtitle Bahasa Indonesia ", "Collecton Count : " + eleMovieListWar.size() +" Collection Found.");
										}
										
									}
									else
									{
										Reporter.failureReportContinue("Select Filter", "Subtitle Bahasa Indonesia is not Selected in All Movies Collection");
									}									
								}
								else
								{
									Reporter.failureReportContinue("Collection under ACTION CARTOONS For Subtitle All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
								}
							
							}
							else
							{
								Reporter.failureReportContinue("Select Filter", "Subtitle All is not Selected in ACTION CARTOONS Collection");
							}
				}				
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return blnFilter;
	}
	
	public static boolean fnVerifyRentFilterLanguage(String strUserType) throws Throwable
	{
		boolean blnFilter=false;
		try
		{
			//Click on Home
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Verifying Languge Filter on Rent for " + strUserType + "  User");
			//Go To Browse and click on All Movies
				fnClickRentedCollection("HOT & TRENDING");
			//Get The Total Count of the Items	
				//Click on Genre
				String[]  eleItemList=fnGetCollectionList("HOT & TRENDING","Rent");			
				if(eleItemList==null)
				{
					System.out.println("No Collection Found under HOT & TRENDING");
					Reporter.failureReportContinue("Collection under HOT & TRENDING", "No Collection Found under HOT & TRENDING");
				}
				else
				{						
							Reporter.SuccessReport("Collection under HOT & TRENDING", "Collection Found under HOT & TRENDING");
						//Click on Genre
							JSClick(HomePageLocators.language,"Language");
						//Click on All Check Box
							String strSubTitleValue=getText(HomePageLocators.languageValue,"Language Selected");
							if(strSubTitleValue.equals("All")==false)
							{
								JSClick(HomePageLocators.languageAll,"Language All");
							}												 			
							String strSubTitleValue1=getText(HomePageLocators.languageValue,"Language Selected");
							System.out.println(strSubTitleValue1);		
							if(strSubTitleValue1.equals("All"))
							{
								Reporter.SuccessReport("Select Filter", "Language All is Selected in HOT & TRENDING Collection");
								//Get the Total Count of the 
								List<WebElement> eleMovieListAll=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("content-title"));
								System.out.println(eleMovieListAll.size());
								if(eleMovieListAll.size() >0)
								{
									Reporter.SuccessReport("Collection under HOT & TRENDING For Language All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
									//Again Go to the Genere and Click on War
									JSClick(HomePageLocators.langaugeThai,"Language Thai");
									Thread.sleep(25000);
									String strGenreValueWar=getText(HomePageLocators.languageValue,"Language Thai");
									System.out.println(strGenreValueWar);	
									if(strGenreValueWar.length() >0 && strGenreValueWar.equals("All")==false)
									{
										Reporter.SuccessReport("Select Filter", "Language Thai is Selected in HOT & TRENDING Collection");
										List<WebElement> eleMovieListThai=driver.findElement(TVOD.rentCollectionTitleList).findElements(By.className("content-title"));
										System.out.println("Thai ==> " + eleMovieListThai.size());	
										if(eleMovieListThai.size()>0)
										{
											Reporter.SuccessReport("Collection under HOT & TRENDING For Language Thai ", "Collecton Count : " + eleMovieListThai.size() +" Collection Found.");
											if(eleMovieListAll.size() != eleMovieListThai.size())
											{
												Reporter.SuccessReport("Verify Language Filter ", "Language Filter is Working Fine");
												blnFilter=true;
											}
											else
											{
												System.out.println("Filter is not Working Fine");
												Reporter.failureReportContinue("Verify Language Filter ", "Language Filter is not Working.");
											}
										}
										else
										{
											Reporter.failureReportContinue("Collection under ACTION CARTOONS For Language Thai ", "Collecton Count : " + eleMovieListThai.size() +" Collection Found.");
										}
										
									}
									else
									{
										Reporter.failureReportContinue("Select Filter", "Language Thai is not Selected in HOT & TRENDING Collection");
									}									
								}
								else
								{
									Reporter.failureReportContinue("Collection under HOT & TRENDING For Language All ", "Collecton Count : " + eleMovieListAll.size() +" Collection Found.");
								}
							
							}
							else
							{
								Reporter.failureReportContinue("Select Filter", "Language All is not Selected in HOT & TRENDING Collection");
							}
				}				
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return blnFilter;
	}
	
	public static void fnVerifyHomePageBannerFavorite(String strUserType) throws Throwable
	{
		try
		{
			//Clear the Favorite
				fnClearListFaviorite();			
			//Verify Favorite from Banner
				JSClick(HomePageLocators.imgLogo,"Home Page");
				Reporter.reportStep("Verifying HOOQ Favorite in Home Page Banner for " + strUserType + " User");
				WebElement eleBanner=driver.findElement(HomePageLocators.lstBanner);
				List<WebElement> lstBanner=eleBanner.findElements(By.tagName("li"));
				System.out.println("Size of List " + lstBanner.size());
				//Verify the Banner if the Total Movie in Banner is 5
				if(lstBanner.size() == 5)
				{
					//Report Banner is displays
					Reporter.SuccessReport("Verify Banner at HOOQ Home Screen", "Banner at HOOQ Screen is Displayed Successfully");
					String strMovieName="";
					boolean blnFav=false;
					for(int i=0;i<lstBanner.size();i++)
					{
						lstBanner.get(0).click();
						strMovieName=getText(HomePageLocators.lblBannerMovieName,"Movie Name ");
						System.out.println(strMovieName);
						boolean blnClick=JSClick(HomePageLocators.btnBanerFaviorites,"Favorite Banner");
						if(blnClick)
						{
							blnFav=true;
							break;
						}
					}
					if(blnFav)
					{
							Reporter.reportStep("Verify Movie or Show Faviorites");
							navigateToFavorite();
							Thread.sleep(15000);
							//Get the Count of the Title
							if(isElementDisplay(By.className("error-empty-state-wrapper"))==false)
							{
								List<WebElement> eleItemverify=driver.findElement(lstFaviroteWatchLaterTitle).findElements(By.className("content-title"));
								boolean blnFound=false;
								String strFavMovieName="";
								for(int i=0;i<eleItemverify.size();i++)
								{
									strFavMovieName=eleItemverify.get(i).getText();
									if(strMovieName.toLowerCase().equals(strFavMovieName.toLowerCase()))
									{
										blnFound=true;
										break;
									}
								}
								if(blnFound)
								{
									Reporter.SuccessReport("Verify Faviorite From Home Page", strMovieName +" successfully displayed in Faviorites");
									System.out.println("Movie Name Match ==> " + strMovieName + " ==> " + strFavMovieName );
								}
								else
								{
									Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
									System.out.println("Movie Name Not Match ==> " + strMovieName);
								}	
							}
							else
							{
								Reporter.failureReportContinue("Verify Faviorite From Home Page", strMovieName +" is not displayed in Faviorites");
							}
							
				}
				else
				{
					Reporter.failureReportContinue("Verify Favorite at HOOQ Home Screen", "Favorite Button is Not Clicked on Banner");
				}
			}
			else
			{
				Reporter.failureReportContinue("Verify Banner at HOOQ Home Screen", "Banner at HOOQ Screen is not Displayed Successfully");
			}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean fnVerifyFAQLink() throws Throwable
	{
		boolean res = false;
		try {
				Reporter.reportStep("Verify Hooq Support");
			//Navigate to Support
				navigateToSupport();
				Thread.sleep(5000);
			//Verify Support Page
				fnVerifyHooqSupportPageDetails();	
				Thread.sleep(5000);
			//Verify FAQ Page Links
				Reporter.reportStep("Verify Hooq Support Link");
				String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
				click(HomePageLocators.lblFAQ,"FAQ");
				Thread.sleep(5000);
			// Switch to new window opened
				for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
				}
				Thread.sleep(5000);
			// Verify FAQ Page
				if(isElementDisplayed(By.xpath("//*[@id='support-header']/div/h2")))
				{
						Reporter.SuccessReport("Verify FAQ Page ", " Support centre is Displayed Successfully");
						System.out.println(" Support centre displayed");
				}
				else
				{
						Reporter.failureReportContinue("Verify FAQ Page", " Support centre is not Displayed Successfully");
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	return res;
}

	public static boolean fnVerifyFAQsearch() throws Throwable
	{
		boolean res = false;
		try {
			String winHandleBefore = driver.getWindowHandle();
			Reporter.reportStep("Verify Hooq Support");
			//Navigate to Support
				navigateToSupport();
				Thread.sleep(5000);
			//Verify Support Page
				fnVerifyHooqSupportPageDetails();	
				Thread.sleep(5000);
			//Verify FAQ Page Links
				Reporter.reportStep("Verify Hooq Support Link");
				// Perform the click operation that opens new window
				click(HomePageLocators.lblFAQ,"FAQ");
				Thread.sleep(5000);				
			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
			Thread.sleep(5000);			
			// Verify FAQ Page
			String strText=getText(By.xpath("//*[@id='support-header']/div/h2"),"FAQ Page");
			System.out.println(strText);
			if(strText.contains("Support Center"))
			{
					Reporter.SuccessReport("Verify FAQ Page ", " Support centre is Displayed Successfully");
					System.out.println(" Support centre displayed");
			}
			else
			{
					Reporter.failureReportContinue("Verify FAQ Page", " Support centre is not Displayed Successfully");
			}
			driver.findElementByXPath(".//*[@id='q']").sendKeys( "Features of HOOQ ");
			click(HomePageLocators.FQAsearchbtn,"Search button");
			Thread.sleep(5000);
		// Verify Features of HOOQ Page
			String strSearch=getText(By.xpath(".//*[@id='support-main']/div[1]/div/h3"),"Search Result");
			if(strSearch.contains("Features of HOOQ"))
			{
				Reporter.SuccessReport("Verify Search Feature in FAQ ", " Search Functionality is working fine");
				System.out.println(" Support centre displayed");
			}
			else
			{
				Reporter.failureReportContinue("Verify Search Feature in FAQ ", " Search Functionality is not working.");
			}
        // Close the new window, if that window no more required
			driver.close();
		// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

public static boolean fnVerifyFAQLinkPage() throws Throwable
	{
		boolean res = false;
		try {
				Reporter.reportStep("Verify Hooq Support");
			//Navigate to Support
				navigateToSupport();
				Thread.sleep(5000);
			//Verify Support Page
				fnVerifyHooqSupportPageDetails();	
				Thread.sleep(5000);
			//Verify FAQ Page Links
				Reporter.reportStep("Verify Hooq Support Link");
				String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
				click(HomePageLocators.lblFAQ,"FAQ");
				Thread.sleep(5000);
			// Switch to new window opened
				for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
				}
				Thread.sleep(5000);
			// Verify FAQ Page
				String strText=getText(By.xpath("//*[@id='support-header']/div/h2"),"FAQ Page");
				System.out.println(strText);
				if(strText.contains("Support Center"))
				{
						Reporter.SuccessReport("Verify FAQ Page ", " Support centre is Displayed Successfully");
						System.out.println(" Support centre displayed");
				}
				else
				{
						Reporter.failureReportContinue("Verify FAQ Page", " Support centre is not Displayed Successfully");
				}		
				Thread.sleep(5000);
				driver.findElementByXPath("//*[@id='support-main']/div[1]/div/table/tbody/tr[1]/td[1]/div/ul/li[1]/a").click();
			// Verify Features of HOOQ Page
				String strSearch=getText(By.xpath(".//*[@id='support-main']/div[1]/div[2]/div[1]/h3"),"Search Result");
				System.out.println(strSearch);
				if(strSearch.contains("What is HOOQ?"))
				{
					Reporter.SuccessReport("Verify What is HOOQ? Page ", " Link is working fine ");
					System.out.println(" Link is working fine");
			}
			else
			{
					Reporter.failureReportContinue("Verify What is HOOQ?  Page", " Link is not working  ");
			}
		// Close the new window, if that window no more required
			driver.close();
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
}
