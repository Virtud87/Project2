package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SharkHomePage {
    private WebDriver driver;

    public SharkHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // get driver from this object
    }

    // Make Comment
    @FindBy(id = "commentButtonShark")
    public WebElement commentButtonShark;

    @FindBy(id = "commentShark")
    public WebElement commentInput;

    @FindBy(id = "sendButtonShark")
    public WebElement sendCommentButton;

    @FindBy(id = "commentSent")
    public WebElement commentPostSuccessMessage;

    // Make Offer
    @FindBy(id = "pitchButtonShark")
    public WebElement pitchButtonShark;

    @FindBy(id = "percent")
    public WebElement percentageInput;

    @FindBy(id = "pitchAmount")
    public WebElement amountInput;

    @FindBy(id = "submitPitchButton")
    public WebElement submitOfferButton;

    @FindBy(id = "pitchAccepted")
    public WebElement offerPostSuccessMessage;

    // Accept Offer
    @FindBy(id = "statusButton")
    public WebElement sharkAcceptButton;

    @FindBy(id = "pitchAccepted")
    public WebElement acceptedMessage;

    // Logout
    @FindBy(id = "sharkLogoutEl")
    public WebElement sharkLogoutButton;

    // Exceptions
    @FindBy(id = "")
    public WebElement nullValuesMessage;

    @FindBy(id = "")
    public WebElement tooLongMessage;

    @FindBy(id = "")
    public WebElement tooShortMessage;

    @FindBy(id = "")
    public WebElement incorrectDataTypeMessage;

    @FindBy(id = "")
    public WebElement usernameTakenMessage;

    @FindBy(id = "")
    public WebElement usernamePasswordIncorrectMessage;

}
