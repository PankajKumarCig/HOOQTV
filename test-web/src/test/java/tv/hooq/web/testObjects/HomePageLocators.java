package tv.hooq.web.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine {
	
	//Home page 
	
	public static By headerLogin = By.xpath(".//*[@class='btn btn-login']");  
	public static By txtEmail = By.xpath(".//*[@id='email']");
	public static By btnDone = By.xpath(".//*[@id='submit-button']");
	public static By lnkHelp = By.xpath(".//*[@id='help-link']");
	public static By imgLogo = By.xpath(".//img[contains(@src,'logo_hooq.png')]");
	public static By lblInvalidEmail = By.xpath(".//*[starts-with(text(),'Invalid email address')]");
	public static By lblNonExistingEmail = By.xpath(".//*[starts-with(text(),'Account does not exist')]");
	public static By lblVerifyEmail = By.xpath(".//p[contains(text(),'We sent you a link to sign in. Please check your inbox') or contains(text(),'e-mail belum di verifikasi. Silahkan periksa e-mail Kamu')]");
	
	
	//Xpaths for Get started window - Home Page
	public static By homeGetStartedBox = By.xpath(".//*[@class='concierge expanded slide-in']");
	public static By homeGetStartedHeaderText(String headerText){
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + headerText + "']");
	}
	public static By homeGetStartedHelpText(String helpText){
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + helpText + "']");
	}
	public static By homeGetStartedButtonText(String buttonText){
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + buttonText + "']");
	}
	
	//Favorite related controls
	public static By lnkFirstMovieSelector = By.xpath("(.//*[@class='paging']//a[text()='·'])[1]");
	public static By lblMovieTile = By.xpath(".//div[@class='info show']//h1");
	public static By lblFavoriteElement = By.xpath(".//*[@class='btn link-white-fav' or @class = 'btn link-green-fav']");
	public static By lblFavoriteTextContainer = By.xpath("((.//*[@class='btn link-white-fav' or @class = 'btn link-green-fav'])//Span)[2]");
	
	
	public static By lblFavoriteMovieTitle(String movieTitle){
		return By.xpath(".//*[@class='content-title'][text() = '" + movieTitle + "']");
	}
	public static By btnFacebook=By.xpath(".//*[@id='fb-button']");
	public static By lstFaviroteWatchLaterTitle=By.xpath("//*[@class='titles-list']");
	public static By discoverwatchlater=By.xpath(".//*[@id='btnWatchLater']");
	public static By discoverFavorite=By.xpath(".//*[@id='btnFavorite']");
	public static By playMovie=By.xpath(".//*[@class='btn btn-watch-now btn-purple']");
	public static By playerEpisode=By.xpath(".//*[@class='hidden-sm-down']");
	public static By playerEpisodeList=By.xpath(".//*[@class='seasons displayed']");
	public static By playerSettings=By.xpath(".//*[@class='icon-cog-outline']");
	public static By playerSettingDetails=By.xpath("//*[@id='player-mount']/section/div[1]/div/div/div[1]/div");
	public static By playerEpisodeDetails=By.xpath(".//*[@id='player-title']");
	public static By playerEpisodeNext=By.xpath(".//*[@class='icon-to-end']");
	public static By playerEpisodePrevious=By.xpath(".//*[@class='icon-to-start']");
	public static By playerEpisodePlay=By.xpath(".//*[@class='icon-play']");
	public static By playerEpisodepause=By.xpath(".//*[@class='icon-pause']");
	public static By playerMaximize=By.xpath(".//*[@class='icon-resize-full-alt']");
	public static By playerMinimize=By.xpath(".//*[@class='icon-resize-small']");
	public static By lblSignUP=By.xpath("//h3[text()='Sign Up']"); 
	public static By lblPlayerErrorFree=By.xpath(".//p[contains(text(),'Title is not available for free trial users')]");
	//Settings Page
	public static By lblSettingsTitle=By.xpath(".//*[@class='page-title']");
	public static By btnSettingsSave=By.xpath(".//*[@id='btn-save']");
	public static By lblSettingAppDisplayLanguage=By.xpath("//*[@id='settings-mount']/div/div/div/section/form/div[1]/div[1]/h6"); 
	public static By cboSettingAppDisplayLanguage=By.xpath("//*[@id='language']");
	public static By lblSettingsAudioLanguage=By.xpath("//*[@id='settings-mount']/div/div/div/section/form/div[1]/div[2]/h6");
	public static By cboSettingsAudioLanguage=By.xpath("//*[@id='audio']");
	public static By lblSettingsSubtitleLanguage=By.xpath("//*[@id='settings-mount']/div/div/div/section/form/div[1]/div[3]/h6");
	public static By cboSettingsSubtitleLanguage=By.xpath("//*[@id='subtitle']");
	public static By lblSettingsPlaybackQuality=By.xpath("//*[@id='settings-mount']/div/div/div/section/form/div[1]/div[4]/h6");
	public static By cboSettingsPlaybackQuality=By.xpath("//*[@id='playbackQuality']");
	public static By lblHeaderBrowse=By.xpath(".//*[@id='userbar-container']/ul[1]/li[1]/a");
	public static By lblHeaderSearch=By.xpath(".//*[@id='userbar-container']/ul[1]/li[3]/a/span");
	public static By lblHeaderRentals=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[1]/a/span");
	public static By lblHeaderWatchLater=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[2]/a/span");
	public static By lblHeaderFaviorites=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[3]/a/span");
	public static By lblHeaderHistory=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[4]/a/span");
	public static By lblHeaderSettings=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[1]/a/span");
	public static By lblHeaderSubscription=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[2]/a/span");
	public static By lblHeaderTransactionHistory=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[3]/a/span");
	public static By lblHeaderSupport=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[4]/a/span");
	public static By lblHeaderLogout=By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[4]/li[2]/a ");
	public static By lblHeaderLoginInfo=By.xpath(".//*[@id='display-name']/span");
	public static By lnkSeeMore=By.xpath(".//*[@class='more-info']");
	
	//Support Page
	public static By lblSuportTitle=By.xpath(".//*[@class='page-title']");
	public static By lblAboutUs=By.xpath(".//span[contains(text(),'About Us')]");
	public static By lblFAQ=By.xpath(".//span[contains(text(),'FAQ')]");
	public static By lblPrivacy=By.xpath(".//span[contains(text(),'Privacy Policy')]");
	public static By lblTermsofUse=By.xpath(".//span[contains(text(),'Terms of Use')]");
	public static By lblNeedHelp=By.xpath(".//span[contains(text(),'Need help')]");
	public static By lblNeedHelpDetails=By.xpath(".//*[@id='support-mount']/div/div/div/section/div[3]/div/div/div/div/div[1]/p");
	public static By btnContactUs=By.xpath("//*[@class='buttons']");
	//Support Footer
	public static By lnkFooterAboutUs=By.xpath(".//*[@class='list-inline-item'][1]");
	public static By lnkFoterTermsOfUse=By.xpath(".//*[@class='list-inline-item'][2]");
	public static By lnkFooterPrivacyPolicy=By.xpath(".//*[@class='list-inline-item'][3]");
	public static By lnkFooterFAQ=By.xpath(".//*[@class='list-inline-item'][4]");
	public static By lnkFooterContactUs=By.xpath(".//*[@class='list-inline-item'][5]");
	//Payment
	public static By lblPrice=By.xpath(".//p[starts-with(.,'INR')]");
	public static By cboNetBank=By.xpath(".//*[@id='netbank']");
	public static By btnNetBankMakePayment=By.xpath(".//*[@name='inetbanking_btn']");
	public static By lblCarrierbillingPrice=By.xpath(" //*[@id='carrierbilling_form']/div/div[1]/div[2]/p");
	public static By cboOperator=By.xpath("//*[@id='oprtr_list']");
	public static By txtCarrierBillPin=By.xpath("//*[@id='pb-pin-input']");
	public static By tctCarrierBillMobileNumber=By.xpath("//*[@id='mno']");
	public static By btnCarrierBillMakePayment=By.xpath("//*[@id='carrierbilling_btn']");
	public static By cboCashCard=By.xpath("//*[@id='cashcardbank']");
	public static By btnCashCard=By.xpath("//*[@id='icashcard_btn']");

	
	
	//Home Page Locators
	public static By lnkMovies=By.xpath(".//a[text()='Movies']");
	public static By lnkTVSeries=By.xpath(".//a[text()='TV Series']");
	public static By lnkshowmemore=By.xpath("//*[@class='whats-hot-header']/div/a");
	public static By lblPriceBundle=By.xpath(".//h1[text()='PRICING & BUNDLES']");
	public static By lnkGetHooqNow=By.xpath(".//a[text()='Get HOOQ Now']");
	public static By lnkAndroidPlayStore=By.xpath(".//*[@href='http://on.hooq.tv/signup-bottom-android']");
	public static By lnkIOSAppStore=By.xpath(".//*[@href='http://on.hooq.tv/signup-bottom-ios']");
	public static By lblCopyRight=By.xpath(".//*[@id='copyright']");
	public static By lstBanner=By.xpath(".//*[@id='highlights']/div[2]/div/ul");
	public static By lblBannerMovieName=By.xpath("//*[@id='highlights']/div[2]/div/h1");
	public static By btnBannerWatchLater=By.xpath("//*[@id='highlights']/div[2]/div/div/a[1]");
	public static By btnBanerFaviorites=By.xpath(".//*[@class='btn btn-action-icon btn-icon-favorite']");
	public static By lstBrowse=By.xpath(".//*[@class='row']");
	public static By lblContentTitle=By.xpath(".//*[@id='collection-title']");
	
	//Login 
	public static By btnPassword=By.xpath(".//*[@id='password-button']");
	public static By edtPassword=By.xpath(".//*[@id='password']");
	public static By btnPasswordOK=By.xpath(".//*[@id='submit-button']");
	
	//Browse Collection
	public static By browseCollection=By.xpath(".//*[@id='browse-mount']/div/div/div/div");
	public static By browseCollectionTitleList=By.xpath(".//*[@class='titles-list']");
	
	
	//Content Details Page
	public static By cdFavorite=By.xpath(".//*[@id='btnAddToFavs']");
	public static By cdFavoriteRemove=By.xpath(".//*[@id='btnRemoveFromFavs']");
	public static By cdFavoriteedText=By.xpath(".//*[@id='btnRemoveFromFavs']/text()");
	public static By cdWatchLater=By.xpath(".//*[@id='btnWatchLater']");
	
	
	//Get started
	//Anonymous user
	public static By GetStarted=By.xpath("//*[@id='concierge-mount']/div/a[1]");
	public static By GetStartedtext=By.xpath("//*[@id='concierge-mount']/div/a[1]/p");
		
	//Pick your Plan  Lapsed User
	public static By PickYourPlan=By.xpath(" //*[@id='concierge-mount']/div/a[1]/h5");
	public static By clckdetails=By.xpath(".//*[@id='titles-mount']/div[2]/div/div[2]/div[1]");
	public static By seemoreViewDetails=By.xpath(".//*[@class='show-details']");
		
	// you were watching
	public static By playcontinuewatching=By.xpath("//*[@id='titles-mount']/div[1]/div/div[1]/a");
													
	//Filter
		public static By genre=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div/a[1]/p[1]/span[1]");
		public static By genreValue=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div/a[1]/p[2]");
		public static By genreAll=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div/a[1]/p[2]");
		public static By genreWar=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div[2]/div[5]/div[7]/a/span[1]");
		public static By subtitle=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div/a[2]/p[1]/span[1]");
		public static By subtitleAll=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div[2]/div/div[1]/a/span[1]");
		public static By subtitleBahasa=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div[2]/div[2]/div/a/span[1]");
		public static By subtitleValue=By.xpath(".//*[@id='collection-mount']/div/div[1]/div/div[1]/a[2]/p[2]");
		public static By language=By.xpath(".//*[@id='collection-rental-mount']/div/div[1]/div/div[1]/a[2]/p[1]/span[1]");
		public static By languageAll=By.xpath(".//*[@id='collection-rental-mount']/div/div[1]/div/div[2]/div[1]/div[1]/a/span[1]");
		public static By langaugeThai=By.xpath(".//*[@id='collection-rental-mount']/div/div[1]/div/div[2]/div[2]/div/a/span[1]");
		public static By languageValue=By.xpath(".//*[@id='collection-rental-mount']/div/div[1]/div/div[1]/a[2]/p[2]");

		//FAQ Links
		public static By FAQlinks=By.xpath("//*[@id='support-main']/div[1]/div/table/tbody/tr[1]/td[1]/div/ul/li[2]/a");
		public static By FQAsearchbtn=By.xpath("//*[@id='support-search-submit']");
        
	
}
