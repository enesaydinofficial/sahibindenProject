package BasePackage;

import Helper.Utils;
import SetPropertiesFile.SetProperties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static KeywordLayer.Command.ImplicitlyWait;
import static KeywordLayer.Command.PageLoad;


public class BaseClass {

    protected static WebDriver driver;
    public static String BrowserType = "Chrome";
    public static Properties config = null;
    private static String OS = System.getProperty("os.name").toUpperCase();
    private String sFileName = System.getProperty("user.dir") + "//src//main//resources//Config//twitterAllCase.har";
    public static BrowserMobProxy proxy;
    protected static ExtentReports extent;
    protected static ExtentTest logger;

    public static void LoadConfigProperty() throws IOException {
        config = new Properties();
        FileInputStream ConfigFile = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//Config//config.properties");
        config.load(ConfigFile);
    }


    @BeforeTest
    public void Init() throws Exception {

        SetProperties.SetValueProperties(); // Set value config.properties file
        LoadConfigProperty();

        // Extent Report
        extent = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
        extent.addSystemInfo("Host Name", "TWITTER")
                .addSystemInfo("Environment", "Microsoft")
                .addSystemInfo("User Name", "Enes Aydin");
        extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

        // Har File
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);


        if (config.getProperty("browserType").equals("Chrome")) {

            if (OS.contains("MAC OS X")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + config.getProperty("MacOSChromeDriverPath"));
            } else if (OS.contains("WİNDOWS 10") || OS.contains("WINDOWS 10")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + config.getProperty("WindowsChromeDriverPath"));
            }

            driver = new ChromeDriver(capabilities);

        } else if (config.getProperty("browserType").equals("Firefox")) {

            if (OS.contains("MAC OS X")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + config.getProperty("MacOSFirefoxDriverPath"));
            } else if (OS.contains("WİNDOWS 10") || OS.contains("WINDOWS 10")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + config.getProperty("WindowsFirefoxDriverPath"));
            }

            driver = new FirefoxDriver(capabilities);

        } else if (config.getProperty("browserType").equals("Edge")) {

            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + config.getProperty("EdgeDriverPath"));
            driver = new EdgeDriver(capabilities);

        }

        driver.manage().window().maximize();

        ImplicitlyWait();
        PageLoad();

    }


    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());

            // Test Failure ScreenShot
            if (ITestResult.FAILURE == result.getStatus()) {

                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + ".png"));

            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
        }
        extent.endTest(logger);
        extent.flush();
    }

    @AfterTest
    public void Teardown() throws IOException {

        try {
            // Get the HAR data
            Har har = proxy.getHar();

            // Write HAR Data in a File
            File harFile = new File(sFileName);
            try {
                har.writeTo(harFile);
                // Please "http://www.softwareishard.com/har/viewer/" site open and report viewed.
            } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("Could not find file " + sFileName);
            }
        } catch (Exception ex) {
        }


        if (driver != null) {
            driver.quit();
        }

        Utils.sendMailHTMLReport("enes611998@gmail.com");

    }

}