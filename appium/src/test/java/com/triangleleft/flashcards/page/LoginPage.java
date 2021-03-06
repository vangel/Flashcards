package com.triangleleft.flashcards.page;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_email")
    @iOSFindBy(accessibility = "login_email")
    public MobileElement emailText;

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_password")
    @iOSFindBy(accessibility = "login_password")
    public MobileElement passwordText;

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_switch")
    @iOSFindBy(accessibility = "login_switch")
    public WebElement rememberSwitch;

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_button")
    @iOSFindBy(accessibility = "login_button")
    public WebElement buttonSignIn;

    @AndroidFindBy(xpath = "//TextInputLayout[@resource-id='com.triangleleft.flashcards:id/login_email_layout']/android.widget.LinearLayout/android.widget.TextView")
    @iOSFindBy(accessibility = "login_email_error")
    public WebElement loginError;

    @AndroidFindBy(xpath = "//TextInputLayout[@resource-id='com.triangleleft.flashcards:id/login_password_layout']/android.widget.LinearLayout/android.widget.TextView")
    @iOSFindBy(accessibility = "login_password_error")
    public WebElement passwordError;

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_progress")
    @iOSFindBy(accessibility = "login_progress")
    public WebElement progressBar;

    public LoginPage(AppiumFieldDecorator driver) {
        super(driver);
    }

    public void setLogin(String login) {
        emailText.click();
        emailText.sendKeys(login);
    }

    public void setPassword(String password) {
        passwordText.click();
        passwordText.sendKeys(password);
    }

    public void setRememberSwitch(boolean remember) {
        if (rememberSwitch.isSelected() != remember) {
            rememberSwitch.click();
        }
    }

    public void login() {
        buttonSignIn.click();
    }
}
