package Interfaces;

import org.openqa.selenium.By;

public interface myProfile {

    By editProfileButton = By.cssSelector(".UserActions-editButton");
    By changeAvatarEditing = By.cssSelector(".ProfileAvatarEditing-changeAvatarHelp");
    By removeOption = By.id("photo-delete-image");
    By removePhotoButton = By.cssSelector("#avatar_confirm_remove_dialog-dialog .ok-btn");
    By saveChangesButton = By.cssSelector(".ProfilePage-saveButton");
}
