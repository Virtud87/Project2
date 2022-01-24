package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // get driver from this object
    }

    // Create Profile
    @FindBy(id = "firstName")
    public WebElement firstNameInput;

    @FindBy(id = "lastName")
    public WebElement lastNameInput;

    @FindBy(id = "businessName")
    public WebElement businessNameInput;

    @FindBy(id = "userName")
    public WebElement usernameInput;

    @FindBy(id = "signupPassword")
    public WebElement passwordInput;

    @FindBy(id = "role")
    public WebElement dropDownList;

    @FindBy(css = "[value='Shark']")
    public WebElement sharkOption;

    @FindBy(id = "submitSignUpButton")
    public WebElement submitProfileButton;


    // Login
    @FindBy(id = "userName")
    public WebElement loginUsernameInput;

    @FindBy(id = "password")
    public WebElement loginPasswordInput;

    @FindBy(id = "submitButton")
    public WebElement loginButton;
}
