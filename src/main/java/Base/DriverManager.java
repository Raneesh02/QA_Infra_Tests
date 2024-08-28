package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<>();;

    public static WebDriver initDriver()  {
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--headless");

        threadLocalDriver.set(new ChromeDriver(chromeOptions));
        // try {
        //     threadLocalDriver.set(new RemoteWebDriver(new URL("http://a23e678ea555349409f2bf001b158f16-1466972497.ap-south-1.elb.amazonaws.com:4444"),chromeOptions));
        // } catch (MalformedURLException e) {
        //     throw new RuntimeException(e);
        // }
        threadLocalDriver.get().manage().window().maximize();
        return threadLocalDriver.get();
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    public static void quitDriver(){
        threadLocalDriver.get().quit();
        threadLocalDriver.remove();
    }

}
