package tv.hooq.web.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HeaderLocators extends ActionEngine {
	
	//Home page 
	
	public static By headerExploreMenu = By.xpath(".//a[@href='/profile'][@class='nav-link']");
	public static By headerSignUp = By.xpath(".//*[@class='get-hooqd-wrapper']/div/div/div/a");
	public static By SignUpAnynomous = By.xpath("//*[@id='userbar-container']/ul[2]/li[1]/a");
	public static By lnkSkip = By.xpath(".//*[@id='skip-button']");
	
	public static By headerSignIn = By.xpath(".//*[@id='welcome-mount']/div/nav/ul/li[2]/a");
	public static By lnkLoginWithEmail = By.xpath(".//a[@id='login-email']");
	public static By headerSubscription = By.xpath(".//a[text()='Subscription']");
	public static By headerFavorite = By.xpath(".//*[text()='Favorites']");
	public static By headerWatchlater = By.xpath(".//*[text()='Watch later']");
	public static By headerHistory = By.xpath(".//*[text()='History']");
	public static By headerSettings=By.xpath(".//*[text()='Settings']");
	public static By headerSupport=By.xpath(".//*[text()='Support']");
	
	public static By headerUserProfile = By.xpath(".//a[@href='/profile'][@class='nav-link']");
	
	public static By headerLogin = By.xpath(".//*[@id='userbar-container']");  
	public static By headerLogout = By.xpath(".//a[text()='Logout']");
	
	
	public static By headerSearch = By.xpath(".//span[contains(text(),'Search')]");
	public static By txtSearch = By.xpath(".//*[@id='search-panel-input']");
	
	public static By btnWatchNow = By.xpath(".//*[@class='btn btn-watch-now btn-purple']");
	public static By sbarSeekBar = By.xpath(".//*[@id='player-seeker']");
	public static By sbarSeekBarHandle = By.xpath(".//*[@id='seeker-scrubber']");
	public static By btnPause = By.xpath(".//*[@class='icon-pause']");
	public static By lblCurrentPlayTime = By.xpath(".//*[@id='currentTime']");
	public static By lblTotalPlayTIme = By.xpath(".//*[@id='duration']");
	
	public static By btnResize = By.xpath(".//*[@class='icon-resize-full-alt']");
	public static By btnPlay = By.xpath(".//*[@class='icon-play']");
	public static By btnSetting = By.xpath(".//*[@class='icon-cog-outline']");
	public static By vdoPlayerArea = By.xpath(".//*[@id='player-viewport']");
	public static By sbarSeekBarProgress = By.xpath(".//*[@id='seeker-progress']");
	
	
	//subscription page
	
	public static By lblSubscriptionPageCurrentPlanPeriodLapsed = By.xpath(".//h4[text()='No Active Subscription']");
	public static By lblSubscriptionPageCurrentPlanPeriod = By.xpath(".//*[@id='plan-days-left']");
	public static By lblSubscriptionPagePlanPeriod = By.xpath(".//*[@id='plan-period']");
	public static By lblSubscriptionPageAutoRenew = By.xpath(".//*[@id='plan-recur']");
	public static By lblSubscriptionPageVoucherRedemptionHeader = By.xpath(".//*[@class='card-block voucher']//h5");
	public static By lblSubscriptionPageVoucherRedemptionNote = By.xpath(".//*[@class='card-block voucher']//p");
	public static By lblSubscriptionPageTerminationHeader = By.xpath(".//*[@class='card-block termination']//h5");
	public static By lblSubscriptionPageTerminationNote = By.xpath(".//*[@class='card-block termination']//p");
	public static By txtSubscriptionPageVoucherCode = By.xpath(".//*[@id='voucher_code']");
	public static By btnSubscriptionPageSubmitVoucher = By.xpath(".//*[@id='btn-submit-voucher']");
	public static By btnSubscriptionPageManage = By.xpath(".//a[text()='Manage']");
	public static By scnManageSubscriptionPageGeneralSection = By.xpath(".//*[@class='card-block duration']");
	public static By lblManageSubscriptionPageGeneralDays = By.xpath(".//*[@class='card-block duration']");
	
	public static By h4SubscriptionTitle = By.xpath("//h4");
	public static By lblVoucherMsg = By.xpath(".//small[@id='voucher_message']");
	
	//Plans Page
	public static By btnPlanPageManage = By.xpath(".//a[@href='/payment']");
	
	//Plan selector page
	public static By btnSelectPlan(String plan)
	{
		return By.xpath("//h2[text()='"+plan+"']/ancestor::div[@class='card']//button");
	}
	public static By btnAutoRenew(String plan)
	{
		return By.xpath("//h2[text()='"+plan+"']/ancestor::div[@class='card']//input");
	}
	
	//Payment page	
	public static By inpPaymentMethod(String billing)
	{
		return By.xpath("//label[text()='"+billing+"']");
	}
	//public static By btnContinue = By.xpath(".//button[text()='Continue']");
	public static By btnContinue = By.xpath(".//*[@id='btn_submit']");
	
	public static By inpCardNumber = By.xpath(".//input[@id='cardnumber']");
	public static By inpNameOnCard = By.xpath(".//input[@id='nameoncard']");
	public static By inpExpiryDate = By.xpath(".//input[@id='expiry_date']");
	public static By inpCVV = By.xpath(".//input[@id='cvv']");
	public static By btnMakePayment = By.xpath("//button[text()=' Make Payment']");
	public static By pCardErrorMessage = By.xpath("//label[@for='cardnumber']/p");
	public static By txtPrice = By.xpath("//div[@class='price']/p");
	public static By txtMobileNumber = By.xpath("//input[@id='mno']");
	public static By lstCarrier = By.xpath("//select[@id='oprtr_list']");
	public static By pMobileErrorMsg = By.xpath("//label[@for='mno']/p");
	public static By lstCashCard = By.xpath("//select[@id='cashcardbank']");
	public static By lstNetBanking = By.xpath("//select[@id='netbank']");
	public static By pGlobeErrorMsg = By.xpath("//*[@id='mobilenumber_form']/div/div[2]/p");
	public static By txtPhMobileNumber = By.xpath("//*[@id='mobilenumber']");
	
	
	public static By lblTimesUpPageSubscribeNow = By.xpath("//*[@class='timesup-header']");
	public static By lblTimesUpPageSubscribeNowHelpText = By.xpath("//*[@class='timesup-header']/../p");
	public static By btnTimesUpPageSubscribeNowActivate = By.xpath("//*[@class='btn btn-purple btn-timesup-cta btn-block']");
	public static By btnTimesUpPageSubscribeNowCancel = By.xpath("//*[@class='icon-cancel']");
	public static By scnPagePlanSelectionPlanSelectionBlock = By.xpath("//*[@id='plans-selector']");
	public static By lblPaymentMethodsPagePaymentDescription = By.xpath("//*[@class='card-block selected-plan']//*[@class='info']/h4");
	public static By lblPaymentMethodsPagePaymentPeriod = By.xpath("//*[@id='plan-period']");
	public static By lblPaymentMethodsPageRecurrance = By.xpath("//*[@id='plan-recur']");
	public static By lblPaymentMethodsPagePrice = By.xpath("//*[@class='card-block selected-plan']//*[@class='price']");
	public static By lblCreditDebitInfo=By.xpath("//*[@id='credit_card']/div[1]");
	public static By lblcarrierbillinginfo=By.xpath("//*[@id='carrierbilling']/div[1]");
	public static By lblCashCardInfo=By.xpath("//*[@id='icashcard']/div[1]");
	public static By lblSearchResultError=By.xpath(".//*[@class='search-result-error']");
	public static By lblSearchResultErrorMessage=By.xpath(".//*[@class='search-result-error']/span");
	public static By lblSearchResultType=By.xpath(".//*[@class='search-result-type']/p");
	public static By lblCDPlayerTitle=By.xpath(".//*[@class='player-episode-title']");
	public static By lblCDSubtitle=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[1]/div[1]/strong");
	public static By lblCDSubtitleText=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[1]/div[2]");
	public static By lblCDDuration=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[2]/div[1]/strong");
	public static By lblCDDurationText=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[2]/div[2]");
	public static By lblCDCast=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[3]/div[1]/strong");
	public static By lblCDCastText=By.xpath(".//*[@id='card-description']/div/div/div[1]/div[3]/div[2]");												
	public static By lblCDTitleDescription=By.xpath(".//*[@id='description']");
	public static By lblCDTitleLinks=By.xpath(".//*[@id='card-description']/div/div/div[3]");
	public static By lblCDImage=By.xpath(".//*[@id='card-buttons']/div");
	public static By lblCDTVPlayerTitle=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/h4");
	public static By lblCDTVSubtitle=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/div[1]/div[1]/div[1]/strong");
	public static By lblCDTVSubtitleText=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]");
	public static By lblCDTVCast=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/div[1]/div[2]/div[1]/strong");
	public static By lblCDTVCastText=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/div[1]/div[2]/div[2]");												
	public static By lblCDTVTitleDescription=By.xpath(".//*[@id='description']");
	public static By lblCDTVTitleLinks=By.xpath(".//*[@id='title-details']/div[1]/div/div[1]/div/div[2]/div[3]");
	public static By lblCDTVImage=By.xpath(".//*[@id='card-buttons']/div");
	public static By lblCDTVPlayList=By.xpath(".//*[@class='playlist']");
	public static By lblCDRNTPlayerTitle=By.xpath(".//*[@id='card-description']/div[2]/h4");
	public static By lblCDRNTDuration=By.xpath(".//*[@id='card-description']/div[2]/div/div[1]/div[1]/div[1]/strong");
	public static By lblCDRNTDurationText=By.xpath(".//*[@id='card-description']/div[2]/div/div[1]/div[1]/div[2]");
	public static By lblCDRNTCast=By.xpath(".//*[@id='card-description']/div[2]/div/div[1]/div[2]/div[1]/strong");
	public static By lblCDRNTCastText=By.xpath(".//*[@id='card-description']/div[2]/div/div[1]/div[2]/div[2]");												
	public static By lblCDRNTTitleDescription=By.xpath("//*[@id='description']");
	public static By lblCDRNTTitleLinks=By.xpath("//*[@id='card-description']/div[2]/div/div[3]");
	public static By lblCDRNTImage=By.xpath(".//*[@id='card-buttons']/div");
	public static By lnksinup=By.xpath("//*[@class='card-block new-user']/a");
	public static By lnkAlreadyaccount=By.xpath("//*[@class='card-block old-user']/a");
	public static By lnkloginwithmail=By.xpath("//*[@id='card-block-form']/a");
	public static By lnkresendemail=By.xpath("//*[@id='resend-button']");
	public static By lnkmobileNo=By.xpath("//*[@id='login-mobile']");
	public static By lnksignuphere=By.xpath("//*[@id='signup-link']");
	public static By lnkTermsandCndtn=By.xpath("//*[@class='signup-note']/a");
	public static By lnkPrivacyPolicy=By.xpath("//*[@class='signup-note']/a[2]");
	public static By hooqHeader=By.xpath("//*[@class='navbar-brand']/img");
	public static By lnkskipsignup=By.xpath(" //*[@id='skip-button']");
	public static By errorMsg=By.xpath(".//*[@class='error-message']");
	
	
	


	
	
	
}
