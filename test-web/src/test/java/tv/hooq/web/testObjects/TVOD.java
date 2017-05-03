package tv.hooq.web.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class TVOD extends ActionEngine {
	
	//Rent page	
	public static By Rent = By.xpath(".//*[@id='rental']");
	public static By onboardclose = By.xpath(".//*[@class='btn-cross']");  
	public static By onboardheader = By.xpath(".//*[@class='header-text']");
	public static By onboardmessage1 = By.xpath(".//*[@id='tvod-onboard-modal']/div/div/div/div[2]/h5/strong");
	public static By onboardmessage2 = By.xpath(".//*[@id='tvod-onboard-modal']/div/div/div/div[2]/ul/li[1]/p");
	public static By onboardticketmessage = By.xpath(".//*[@id='tvod-onboard-modal']/div/div/div/div[2]/ul/li[2]/p");
	public static By btnExploreRental= By.xpath(".//*[@id='tvod-onboard-modal']/div/div/div/div[2]/button");
	public static By meSubscriptiontext= By.xpath(".//*[@id='subscription']/h5");
	public static By meSubscriptiondays= By.xpath(".//*[@id='subscription']/p");
	public static By meTickettext= By.xpath(".//*[@id='ticket']/h5");
	public static By meTicketcount= By.xpath(".//*[@id='ticket']/p/span");
	public static By rentHeader= By.xpath(".//*[@id='collection-list-header']/div[1]/div/h2");
	public static By rentMessage=By.xpath(".//*[@id='collection-list-header']/div[1]/div/p");
	public static By rentTicketInfo=By.xpath(".//*[@id='collection-list-header']/div[2]/div/div/p[1]");
	public static By rentTicketExpireDate=By.xpath(".//*[@id='collection-list-header']/div[2]/div/div/p[2]/span[3]");
	public static By rentCollection=By.xpath(".//*[@id='rental-mount']/div/div/div/div/div[2]");
	public static By rentCollectionTitle=By.xpath(".//*[@id='collection-title']");
	public static By rentCollectionTitleList=By.xpath(".//*[@class='titles-list hooq-gold']");
	public static By rentPurchase=By.xpath(".//*[@class='modal-content']");
	public static By rentnow=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/h5/strong");
	public static By rentinfo=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/p");
	public static By rentByCC=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/button[1]");
	public static By rentByTicket=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/button[2]");
	public static By rentTermsofUse=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/a");
	public static By rentTersmofUseHeader=By.xpath("/html/body/div/div/div/div/h1");
	public static By rentRedimTicketHeader=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/h5/strong");
	public static By rentRedimTicketInfo=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/p");
	public static By rentRedimTicketButton=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/button");
	public static By rentRedimTicketConfirmHeader=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/h5/strong");
	public static By rentRedimTicketConfirmInfo=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/p");
	public static By rentRedimTicketConfirmWatchNowButton=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/button");
	public static By rentRedimTicketConfirmWatchLater=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/a");
	public static By rentRedimTicketMovieName1=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/p/strong[2]");
	public static By rentRedimTicketMovieName2=By.xpath(".//*[@id='rent-modal']/div/div/div/div[2]/p/strong[1]");
	public static By tansactionHistoryMovieName=By.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[1]/div[1]/div[2]/h4[1]");
	public static By tansactionHistoryTransactionDate=By.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[1]/div[2]/p[2]");
	public static By titleRental=By.xpath(".//*[@id='profile-title']");
	public static By titleRentalHelp=By.xpath(".//*[@class='ticket-help']");
	public static By RentalTicketCount=By.xpath(".//*[@id='profile-mount']/div/div[2]/div/div/div[1]/div/div[1]/div[1]/h3/span[1]");
	public static By RentalTicketExpireDate=By.xpath(".//*[@id='profile-mount']/div/div[2]/div/div/div[1]/div/div[1]/div[1]/p");
	public static By RentalTicketUseNow=By.xpath(".//*[@class='btn btn-yellow']");
	public static By RentedMovieList=By.xpath(".//*[@class='rental-info']");
	public static By RentedMovieContenDetailsMovieName=By.xpath(".//*[@class='container-wrapper']/div/div/h1");
	public static By RentedMovieContenDetailsNotification=By.xpath(".//*[@class='rental-notification']/div/div/p/span");
	public static By RentedMovieContenDetailsCountDown=By.xpath(".//*[@class='rental-period']");
	public static By RentedMovieContenDetailsWatchNow=By.xpath(".//*[@class='container-wrapper']/div/div/div/a");
	public static By RentPaymentMethodHeader=By.xpath(".//*[@class='payment']/section/div/h1");
	public static By RentPaymentMethodMovieName=By.xpath(".//*[@id='payment-methods-content']/form/div[1]/div[1]/div[1]/p");
	public static By RentPaymentMethodMoviePrice=By.xpath(".//*[@class='price']/p");
	public static By RentPaymentMethodMoviePaymentMethod=By.xpath(".//*[@id='payment-methods-content']/form/div[1]/div[2]/ul/li/fieldset/label");
	public static By RentPaymentMethodMakePayment=By.xpath(".//*[@id='btn_submit']");
	public static By RentCCPopUpMovieName=By.xpath(".//*[@id='creditcard_form']/div/div[1]/div[1]/span");
	public static By RentCCPopUpMoviePrice=By.xpath(".//*[@id='creditcard_form']/div/div[1]/div[2]/p");
	public static By RentCCPopUpMovieCCPaymentText=By.xpath(".//*[@id='credit_card']/div[1]");
	public static By RentCCPopUpMovieCCPaymentCCNumberText=By.xpath(".//*[@id='credit_card']/div[2]/div[1]/label");
	public static By RentCCPopUpMovieCCPaymentCCNumberField=By.xpath(".//*[@id='cardnumber']");
	public static By RentCCPopUpMovieCCPaymentCCNameOnCardText=By.xpath(".//*[@id='credit_card']/div[2]/div[2]/label");
	public static By RentCCPopUpMovieCCPaymentCCNameOnCardField=By.xpath(".//*[@id='nameoncard']");
	public static By RentCCPopUpMovieCCPaymentCCExpireDateText=By.xpath(".//*[@id='creditcardcvv']/div[1]/label");
	public static By RentCCPopUpMovieCCPaymentCCExpireDateField=By.xpath(".//*[@id='expiry_date']");
	public static By RentCCPopUpMovieCCPaymentCCCVVText=By.xpath(".//*[@id='creditcardcvv']/div[2]/label");
	public static By RentCCPopUpMovieCCPaymentCCCVVField=By.xpath(".//*[@id='cvv']");
	public static By RentCCPopUpMovieCCPaymentCCMakePayment=By.xpath(".//*[@id='cvv']");
	
	
	
	
	
	
	
	
	
	
	
}
