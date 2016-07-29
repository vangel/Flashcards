package com.triangleleft.flashcards.page;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_email")
    public WebElement email;
    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_password")
    public WebElement password;
    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_checkbox")
    public WebElement checkbox;
    @AndroidFindBy(id = "com.triangleleft.flashcards:id/login_button")
    public WebElement buttonSignIn;

    public LoginPage(AppiumFieldDecorator driver) {
        super(driver);
    }
}
