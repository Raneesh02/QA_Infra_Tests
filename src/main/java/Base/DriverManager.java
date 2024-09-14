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

    private static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<>();
    private static String gridUrl="http://localhost:4444";

    public static WebDriver initDriver()  {
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--headless");

        System.out.println("env variable gridurl "+System.getProperty("selenium.gridurl"));
        if(System.getProperty("selenium.gridurl")!=null){
          gridUrl =  "http://"+System.getProperty("selenium.gridurl")+":4444";
        }
        System.out.println("Grid url used: "+gridUrl);
        try {
            threadLocalDriver.set(new RemoteWebDriver(new URL(gridUrl),chromeOptions));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
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
