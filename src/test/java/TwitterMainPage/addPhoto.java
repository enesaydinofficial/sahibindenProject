package TwitterMainPage;

import BasePackage.BaseClass;
import Helper.Utils;
import Interfaces.mainPage;
import Interfaces.messageInside;
import Interfaces.myProfile;
import LoginPage.loginTest;
import net.lightbody.bmp.proxy.CaptureType;
import org.testng.annotations.Test;

import static KeywordLayer.Command.*;

public class addPhoto extends BaseClass implements mainPage, myProfile, messageInside {

    @Test
    public void addProfilePhoto() throws Exception {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT, CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Twitter Add Photo Request Load Time");

        logger = extent.startTest("Twitter Add Photo");
        Utils.startRecording(); // Start Recorder

        loginTest.twitterLogin();
        GetURL(config.getProperty("BaseURL"));
        WaitElementToBeClickable(addAPhotoButton);
        Click(addAPhotoButton);
        SendKeys(uploadAvatar, System.getProperty("user.dir") + "/src/main/resources/Images/img_avatar.png");
        WaitElementToBeClickable(applyButton);
        Click(applyButton);
        AssertEquals(GetText(messageText), "Your profile photo was published successfully.");
        WaitElementToBeClickable(avatarLink);
        Click(avatarLink);
        WaitElementToBeClickable(editProfileButton);
        Click(editProfileButton);
        WaitElementToBeClickable(changeAvatarEditing);
        Click(changeAvatarEditing);
        WaitElementToBeClickable(removeOption);
        Click(removeOption);
        WaitElementToBeClickable(removePhotoButton);
        Click(removePhotoButton);
        AssertEquals(GetText(messageText), "Profile image removed.");
        WaitElementToBeClickable(saveChangesButton);
        Click(saveChangesButton);
        AssertEquals(GetText(messageText), "Your profile has been saved.");

        Utils.stopRecording(); // Stop Recorder

    }
}
