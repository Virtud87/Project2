package sharktests;

import com.investing_app.customexceptions.*;
import com.investing_app.dao.SharkDAO;
import com.investing_app.entities.Shark;
import com.investing_app.service.SharkService;
import com.investing_app.service.SharkServiceImplemented;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SharkServiceTests {

    // made static so that they are accessible without having to instantiate SharkServiceTests class
    static SharkDAO sharkDAO;
    static SharkService sharkService;
    static Shark sharkProfile;
    static Shark returnedSharkProfile;
    static Shark sharkProfileTooManyChar;
    static Shark sharkProfileNullValue;
    static Shark sharkProfileIncorrectDataType;
    static Shark shortUsername;
    static Shark shortPassword;


    @BeforeClass
    public void setup() {
       sharkDAO = Mockito.mock(SharkDAO.class);
       sharkService = new SharkServiceImplemented(sharkDAO);
       sharkProfile = new Shark(0, "Lori", "Greiner", "QVC, Inc",
               "QueenQVC", "qvc12345", "Shark");
       returnedSharkProfile = new Shark(1, "Lori", "Greiner", "QVC, Inc",
               "QueenQVC", "qvc12345", "Shark");
       sharkProfileTooManyChar = new Shark(0, "jgkdkjeighejekfjghfjej", "fjgkdkjeighejekfjghfjej",
               "kasiwuehnvlaskeuhwkjenfalksjnf", "jgkdkjeighejekfjghfjej",
               "kwowkgjthfmbnalkjwkdjhgkskejdjgheef", "kdjfkrjekdnf");
        sharkProfileNullValue = new Shark(0, "", "", "", "",
                "", "");
        sharkProfileIncorrectDataType = new Shark(0, "Danie1", "Landero5", "MyBiz",
                 "TexasDan", "heyYou765", "5hark");
        shortUsername = new Shark(0, "Texas", "Dan", "MyBiz",
                "sjdh", "atleast8", "Shark");
        shortPassword = new Shark(0, "Texas", "Dan", "MyBiz",
                "AtLeast5", "sj", "Shark");
    }

    // Catching null inputs for login
    @Test(expectedExceptions = NullValue.class)
    public void sharkLoginNullValues() {
        Mockito.when(sharkDAO.getSharkByUsername("Kevin")).thenThrow(new NullValue("You must enter a value!"));
        sharkService.sharkLoginService("", "");
    }

    // Catching too long an entry for login
    @Test(expectedExceptions = TooManyChar.class)
    public void sharkLoginTooManyChar() {
        Mockito.when(sharkDAO.getSharkByUsername("Troy")).thenThrow(new TooManyChar("You are exceeding the value length"));
        sharkService.sharkLoginService("a;lskdjfjdkslslekejldk", "gjkfndheuthfndjethfndjakqpoekjsnf");
    }

    // Catching incorrect credentials
    @Test(expectedExceptions = UsernameOrPasswordIncorrect.class)
    public void sharkLoginServiceUsernamePasswordFail() {
        Mockito.when(sharkDAO.getSharkByUsername("Bobby"))
                .thenThrow(new UsernameOrPasswordIncorrect("Either your username or password or both are incorrect!"));
        sharkService.sharkLoginService("Bobby", "bobby1");
    }

    @Test(expectedExceptions = UsernameOrPasswordIncorrect.class)
    public void sharkLoginUsernameFail() {
        Mockito.when(sharkDAO.getSharkByUsername("Kelso"))
                .thenThrow(new UsernameOrPasswordIncorrect("Either your username or password or both are incorrect!"));
        sharkService.sharkLoginService("Teslo", "70sShow1");
    }

    @Test(expectedExceptions = UsernameOrPasswordIncorrect.class)
    public void sharkLoginPasswordFail() {
        Mockito.when(sharkDAO.getSharkByUsername("Kelso"))
                .thenThrow(new UsernameOrPasswordIncorrect("Either your username or password or both are incorrect!"));
        sharkService.sharkLoginService("Kelso", "Show1");
    }

    // Correct credentials
    @Test
    public void sharkLoginSuccess() {
        Mockito.when(sharkDAO.getSharkByUsername("QueenQVC")).thenReturn(returnedSharkProfile);
        Shark result = sharkService.sharkLoginService("QueenQVC", "qvc12345");
        Assert.assertEquals(result, returnedSharkProfile);
    }


    // stub DAO results
    @Test
    public void createSharkProfileReturnedValue() {
        Mockito.when(sharkDAO.createSharkProfile(sharkProfile)).thenReturn(returnedSharkProfile);
        Shark result = sharkService.createSharkProfileService(sharkProfile);
        Assert.assertEquals(result, returnedSharkProfile);
    }

    // Catching too long of an input for creating profile
    @Test(expectedExceptions = TooManyChar.class)
    public void createSharkProfileTooManyChar() {
        Mockito.when(sharkDAO.createSharkProfile(sharkProfileTooManyChar))
                .thenThrow(new TooManyChar("You are exceeding the value length"));
        sharkService.createSharkProfileService(sharkProfileTooManyChar);
    }

    // Catching null input for creating profile
    @Test(expectedExceptions = NullValue.class)
    public void createSharkProfileNullValue() {
        Mockito.when(sharkDAO.createSharkProfile(sharkProfileNullValue))
                .thenThrow(new NullValue("You must enter a value!"));
        sharkService.createSharkProfileService(sharkProfileNullValue);
    }

    // Catching incorrect data type
    @Test(expectedExceptions = IncorrectDataType.class)
    public void createSharkProfileIncorrectDataType() {
        Mockito.when(sharkDAO.createSharkProfile(sharkProfileIncorrectDataType))
                .thenThrow(new IncorrectDataType("Input type not allowed"));
        sharkService.createSharkProfileService(sharkProfileIncorrectDataType);
    }

    // Testing for unique username
    @Test(expectedExceptions = UsernameTaken.class)
    public void createSharkProfileUniqueUsername() {
        Mockito.when(sharkDAO.createSharkProfile(sharkProfile))
                .thenThrow(new UsernameTaken("That username is already taken! Please try again."));
        sharkService.createSharkProfileService(sharkProfile);
    }

    // Catching too short a username
    @Test(expectedExceptions = UsernameTooShort.class)
    public void createSharkProfileShortUsername() {
        Mockito.when(sharkDAO.createSharkProfile(shortUsername))
                .thenThrow(new UsernameTooShort("Username must be at least 5 characters!"));
        sharkService.createSharkProfileService(shortUsername);
    }

    // Catching too short a password
    @Test(expectedExceptions = PasswordTooShort.class)
    public void createSharkProfileShortPassword() {
        Mockito.when(sharkDAO.createSharkProfile(shortPassword))
                .thenThrow(new PasswordTooShort("Password must be at least 8 characters!"));
        sharkService.createSharkProfileService(shortPassword);
    }
}
