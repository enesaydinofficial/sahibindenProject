package SetPropertiesFile;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import static BasePackage.BaseClass.BrowserType;

public class SetProperties {

    public static void SetValueProperties() throws Exception {
        Properties prop = new Properties();
        OutputStream output = null;
        output = new FileOutputStream(System.getProperty("user.dir") + "//src//main//resources//Config//config.properties");
        prop.setProperty("browserType", BrowserType);
        prop.setProperty("WindowsChromeDriverPath", "/drivers/chromedriver.exe");
        prop.setProperty("MacOSChromeDriverPath", "/drivers/chromedriver");
        prop.setProperty("WindowsFirefoxDriverPath", "/drivers/geckodriver.exe");
        prop.setProperty("MacOSFirefoxDriverPath", "/drivers/geckodriver");
        prop.setProperty("EdgeDriverPath", "/drivers/MicrosoftWebDriver.exe");
        prop.setProperty("IEDriverPath", "/drivers/IEDriverServer.exe");
        prop.setProperty("ImplicitlyWait", "25");
        prop.setProperty("WaitTimeOutSeconds", "25");
        prop.setProperty("reportConfigPath", "/extent-config.xml");
        prop.setProperty("PageLoadTimeOut", "15");
        prop.setProperty("twitterUserName", "sahibinden18");
        prop.setProperty("twitterPassword", "sahibindentest1");
        prop.setProperty("BaseURL", "https://twitter.com/");
        prop.setProperty("MailAddress", "enesaydin@testkalite.com");
        prop.setProperty("MailPassword", "Deadlydream61");
        prop.store(output, null);

    }
}
