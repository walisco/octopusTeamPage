package pageObjects;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Based on shared webdriver implementation in cucumber-jvm examples
 * A new instance of SharedDriver is created for each Scenario and passed to  Stepdef classes via Dependency Injection
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SharedDriver extends EventFiringWebDriver {
    static RemoteWebDriver REAL_DRIVER = null;
    static String BROWSER;
    static final String PLATFORM;
    static String DRIVER_PATH;
    static final String FILE_SEPARATOR;
    private static final String DRIVER_ROOT = "browsers";

    static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };


    private SharedDriver() {
        super(REAL_DRIVER);
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    static {
        try {
            Props.loadRunConfigProps();

        } catch (IOException e) {
            e.getMessage();
        }


        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = System.getProperty("os.name").toLowerCase().trim();
        BROWSER = System.getProperty("browser");

        try {
            initiateDriverForBrowser();
        } catch (IOException e) {
            e.getMessage();
        }

        REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        REAL_DRIVER.manage().window().maximize();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static void initiateDriverForBrowser() throws IOException {
        if (BROWSER == null) {
            BROWSER = System.getenv("browser");
            if (BROWSER == null) {
                //default to chrome if no browser is supplied in the commandline
                BROWSER = "chrome";
            }
        }
        switch (BROWSER) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getDriverPath());
                REAL_DRIVER = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", getDriverPath());
                FirefoxOptions options = new FirefoxOptions();
                REAL_DRIVER = new FirefoxDriver(options);
                break;

            default:
                System.out.println("Incorrect browser type supplied");
        }


        REAL_DRIVER.manage().deleteAllCookies();
    }


    private static String getDriverPath() {
        if (BROWSER.equals("chrome") && PLATFORM.contains("mac")) {
            DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + "mac" + FILE_SEPARATOR
                    + "chromedriver";
        } else if (BROWSER.equals("firefox") && PLATFORM.contains("mac")) {
            DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "geckodriver"
                    + FILE_SEPARATOR + "mac" + FILE_SEPARATOR
                    + "geckodriver";
        } else if (BROWSER.equals("chrome") && PLATFORM.contains("windows")) {
            DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + "win32" + FILE_SEPARATOR
                    + "chromedriver.exe";
        } else if (BROWSER.equals("firefox") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "geckodriver"
                    + FILE_SEPARATOR + "win32" + FILE_SEPARATOR
                    + "geckodriver.exe";
        }

        return DRIVER_PATH;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

}
