package pageObjects;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class SharedDriver extends EventFiringWebDriver {


    public static WebDriver REAL_DRIVER;
    public static String SELENIUM_HOST;

    private static final Thread CLOSE_THREAD = new Thread() {
        public void run() {
            try {
                REAL_DRIVER.quit();
            } catch (Throwable t) {
            }

        }
    };
    static {
        startChromeDriver();
        REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        REAL_DRIVER.manage().window().maximize();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver (){ super(REAL_DRIVER);}

    private static void startChromeDriver(){

        System.setProperty("webdriver.chrome.driver","src/browsers/chromedriver/win32/chromedriver.exe");
        REAL_DRIVER = new ChromeDriver();

    }
    public static WebDriver  getWebDriver(){
        return REAL_DRIVER;
    }

}
