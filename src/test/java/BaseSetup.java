import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 2 API TiramisuPrivacySandbox");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("appPackage", "com.pozitron.hepsiburada");
        caps.setCapability("appActivity","com.hepsiburada.ui.startup.SplashActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest () throws InterruptedException {
        bekle(5);
        dialogsCloseAndCloseAnimation();
        loginAccount();
    }

    public void loginAccount(){
        // Giriş
        driver.findElement(By.id("account_menu_button")).click();
        bekle(2);
        driver.findElement(By.xpath("//*[@text='Giriş yap']")).click();
        bekle(2);
        // mail giriş
        driver.findElement(By.className("android.widget.EditText")).sendKeys("rparlak368@gmail.com");
        driver.findElement(By.className("android.widget.Button")).click();
        bekle(5);
        //password giriş
        driver.findElement(By.className("android.widget.EditText")).sendKeys("Sifre.1234");
        bekle(10);
        driver.findElement(By.className("android.widget.Button")).click();
        bekle(10);

    }

    public void dialogsCloseAndCloseAnimation(){
        // Kullanıcı cıkan uyarıya izin vermez
        driver.findElement(By.xpath("//*[@text='Don’t allow']")).click();


    }

   /* @AfterMethod
    public void teardown(){
        driver.quit();

    }*/


    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
