package Pages;

import Reusable.Reusable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public class Midtrans extends Reusable {


    public void user_launch_the_store_site() {
        try{
            launchSite();
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while launching site",false);
        }
    }

    public void user_clicks_on_buy_now() {
        try{
            clickAnElement(getDataFromPropertiesFile("midtrans","buynow"),driver);
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while clicking on buy now",false);
        }
    }

    public void click_on_checkout_with_credit_card(String cardnumber,String expirydate,String cvv,String otp) {
        try{
            clickAnElement(getDataFromPropertiesFile("midtrans","checkout"),driver);
            driver.switchTo().frame("snap-midtrans");
            clickAnElement(getDataFromPropertiesFile("midtrans","continuePay"),driver);
            clickAnElement(getDataFromPropertiesFile("midtrans","creditcard"),driver);
            driver.findElement(By.name("cardnumber")).sendKeys(cardnumber);
            driver.findElement(By.xpath(getDataFromPropertiesFile("midtrans","expirydate"))).sendKeys(expirydate);
            driver.findElement(By.xpath(getDataFromPropertiesFile("midtrans","cvv"))).sendKeys(cvv);
            clickAnElement(getDataFromPropertiesFile("midtrans","paynow"),driver);
            //driver.switchTo().defaultContent();
            WebElement e=waitForElementAndReturnElement(getDataFromPropertiesFile("midtrans","otpiframe"));
            driver.switchTo().frame(e);
            e=waitForElementAndReturnElement(getDataFromPropertiesFile("midtrans","otp"));
            e.sendKeys(otp);
            clickAnElement(getDataFromPropertiesFile("midtrans","okotp"),driver);
            driver.switchTo().parentFrame();
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while checking out with credit card",false);
        }
    }

    public void transaction_successful_message() {
        try{
            WebElement e=waitForElementAndReturnElement(getDataFromPropertiesFile("midtrans","transactionsuccessful"));
            errorHandler("Error=> Expected: Transaction successful <==> Actual: "+e.getText(),e.getText().equalsIgnoreCase("Transaction successful"));
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while checking out transaction successful message",false);
        }
    }

    public void user_navigates_to_home_page() {
        try{
            Thread.sleep(4000);
            driver.switchTo().defaultContent();
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while navigating to home page",false);
        }
    }

    public void verify_purchase_successful_message() {
        try{
            WebElement e=waitForElementAndReturnElement(getDataFromPropertiesFile("midtrans","purchasesuccessful"));
            errorHandler("Error while checking out transaction successful message",e.getText().contains("Thank you for your purchase."));
            cleanup();
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while verifying message",false);
        }
    }

    public void transaction_failure_message() {
        try{
            WebElement e=waitForElementAndReturnElement(getDataFromPropertiesFile("midtrans","transactionfailed"));
            errorHandler("Error-> Expected : Transaction failed <==> Actual: "+e.getText(),e.getText().equalsIgnoreCase("Transaction failed"));
            cleanup();
        }catch (Exception e){
            e.printStackTrace();
            errorHandler("Exception while checking out transaction successful message",false);
        }
    }

}
