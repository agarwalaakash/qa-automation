package Reusable;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

public class Reusable {
    public WebDriver driver ;

    public void browser(String browser){
        try{
            switch (browser.toLowerCase()){
                case "firefox":
                    System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
        }catch(Exception e){
            e.printStackTrace();
            errorHandler("Exception while launching browser",false);

        }
    }

    public void launchSite(){
        try{
            browser(getDataFromPropertiesFile("Reusable","browser"));
            driver.get(getDataFromPropertiesFile("Reusable","url"));
        }catch(Exception ex){
            ex.printStackTrace();
            errorHandler("Exception while navigating to the url",false);

        }
    }

    public void clickAnElement(final String identifier, final WebDriver driver){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(identifier)));
            WebElement el=driver.findElement(By.xpath(identifier));

            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",el);
        }catch(Exception e){
            e.printStackTrace();
            errorHandler("Exception while clicking the element",false);

        }

    }

    public final String getDataFromPropertiesFile(final String fileName,final String key){
        try{
            Properties prop= new Properties();
            String propFile = fileName +".properties";
            InputStream is=Reusable.class.getClassLoader().getResourceAsStream(propFile);
            prop.load(is);
            String data=prop.getProperty(key);
            is.close();
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public void errorHandler(String error,boolean result){
        try{
            if(!result) {
                cleanup();
            }
            Assert.assertTrue(error,result);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("exception in error handler");
        }
    }

    public void  cleanup(){
        driver.quit();
    }

    public WebElement waitForElementAndReturnElement(String xpath){
        FluentWait wait=new FluentWait(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);
        WebElement e=(WebElement) wait.until(new Function<WebDriver,WebElement>(){
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.xpath(xpath));
            }
        });
        return e;
    }



}