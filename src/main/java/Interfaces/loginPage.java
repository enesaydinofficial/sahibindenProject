package Interfaces;

import org.openqa.selenium.By;

public interface loginPage {

    By userName = By.name("session[username_or_email]");
    By password = By.name("session[password]");
    By loginInButton = By.cssSelector(".StaticLoggedOutHomePage-login .EdgeButton");
}
