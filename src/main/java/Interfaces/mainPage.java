package Interfaces;

import org.openqa.selenium.By;

public interface mainPage {

    By dashboardProfileCard = By.cssSelector(".DashboardProfileCard");
    By addAPhotoButton = By.cssSelector(".upload-profile-photo-btn");
    By uploadAvatar = By.cssSelector(".uploader-avatar .file-input");
    By applyButton = By.cssSelector(".profile-image-save");
    By avatarLink = By.cssSelector(".DashboardProfileCard-avatarLink");

}
