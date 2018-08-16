package LoginPage;

import BasePackage.BaseClass;
import Helper.Utils;
import Interfaces.loginPage;
import Interfaces.mainPage;
import net.lightbody.bmp.proxy.CaptureType;
import org.testng.annotations.Test;

import static KeywordLayer.Command.*;

public class loginTest extends BaseClass implements loginPage, mainPage {

    @Test
    public static void twitterLogin() throws Exception {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT, CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Twitter Login Page Request Load Time");

        logger = extent.startTest("twitter Login");
        LoadConfigProperty();

        Utils.startRecording(); // Start Recorder

        GetURL(config.getProperty("BaseURL"));
        WaitElementVisible(userName);
        SendKeys(userName, config.getProperty("twitterUserName"));
        SendKeys(password, config.getProperty("twitterPassword"));
        Click(loginInButton);
        isDisplayed(dashboardProfileCard); // Verify Successfull Login

        Utils.stopRecording(); // Stop Recorder

    }
}
