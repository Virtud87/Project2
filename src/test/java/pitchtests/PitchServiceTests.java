package pitchtests;

import com.investing_app.customexceptions.IncorrectDataType;
import com.investing_app.customexceptions.NullValue;
import com.investing_app.customexceptions.TooManyChar;
import com.investing_app.customexceptions.ValueTooLarge;
import com.investing_app.dao.PitchDAO;
import com.investing_app.entities.Pitch;
import com.investing_app.service.PitchService;
import com.investing_app.service.PitchServiceImplemented;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PitchServiceTests {
    static PitchDAO pitchDAO;
    static PitchService pitchService;
    static Pitch pitch;
    static Pitch pitch2;
    static List<Pitch> returnedPitches;
    static Pitch returnedPitch;
    static Pitch pitchTooManyChar;
    static Pitch pitchNullValues;
    static Pitch pitchIncorrectDataType;
    static Pitch AmountTooHigh;
    static Pitch PercentTooHigh;

    @BeforeClass
    public void setup() {
        pitchDAO = Mockito.mock(PitchDAO.class);
        pitchService = new PitchServiceImplemented(pitchDAO);
        pitch = new Pitch(0, 1, "My Business", "01/06/2022",
                "Send us your kitchen knives and we sharpen them for you!",
                100000, 4.5, "");
        pitch2 = new Pitch(2, 2, "Our Business", "01-06-2022", "Mench on a Bench toy",
                120000, 5.5, "");
        returnedPitches = new ArrayList<>();
        returnedPitches.add(pitch);
        returnedPitches.add(pitch2);
        returnedPitch = new Pitch(1, 1, "My Business", "01-06-2022",
                "Send us your kitchen knives and we sharpen them for you!",
                100000, 4.5, "");
        pitchTooManyChar = new Pitch(0, 1, ";slkdjalsjljaslfj", "01-28-20222022",
                "a;lksjdf asjdkf dfklj lkasjdfkjsdfl;kaeinlsiehg lsjfoawilejfaiefja;wiejfawoie iowleijfaw;liefja " +
                        "lkasdjflasjfa;slkdalskjfkdsjfa sflkajsdlkfjaiwefjisdfaksdjfa;liwejiegj aowejlfingao;weiganwiijf" +
                        "s;ldkfjal;ksjf;laskdjfl;aksdjflka",
                100_000, 5.55, "");
        pitchNullValues = new Pitch(0, 1, "", "", "", 50000,
                1.0, "");
        pitchIncorrectDataType = new Pitch(0, 1, "My Business", "alsk", "my pitch",
                100_000, 3.3, "");
        AmountTooHigh = new Pitch(0, 1, "My Business", "2022/01/08", "My pitch!",
                1_000_001, 5.6, "");
        PercentTooHigh = new Pitch(0, 2, "My Business", "2022/01/08", "My pitch!",
                900_000, 50.0, "");
    }

    // Stubbing DAO results(positive)
    @Test
    public void createPitchReturnedValue() {
        Mockito.when(pitchDAO.createPitch(pitch)).thenReturn(returnedPitch);
        Pitch result = pitchService.createPitchService(pitch);
        Assert.assertEquals(result, returnedPitch);
    }


    @Test
    public void viewPitchesReturnedValue() {
        Mockito.when(pitchDAO.viewPitches()).thenReturn(returnedPitches);
        List<Pitch> result = pitchService.viewPitchesService();
        Assert.assertEquals(result, returnedPitches);
    }

    @Test
    public void makeOfferReturnedValue() {
        Mockito.when(pitchDAO.makeOffer(1, 110000, 5.0)).thenReturn(true);
        boolean result = pitchService.makeOfferService(1, 110000, 5.0);
        Assert.assertTrue(result);
    }

    @Test
    public void acceptOfferReturnedValue() {
        Mockito.when(pitchDAO.acceptOffer(1)).thenReturn(true);
        boolean result = pitchService.acceptOfferService(1);
        Assert.assertTrue(result);
    }

    // Catching too long an input for createPitch
    @Test(expectedExceptions = TooManyChar.class)
    public void createPitchTooManyChar() {
        Mockito.when(pitchDAO.createPitch(pitchTooManyChar))
                .thenThrow(new TooManyChar("You are exceeding the value length!"));
        pitchService.createPitchService(pitchTooManyChar);
    }

    // Catching null values
    @Test(expectedExceptions = NullValue.class)
    public void createPitchNullValue() {
        Mockito.when(pitchDAO.createPitch(pitchNullValues)).thenThrow(new NullValue("You must enter a value!"));
        pitchService.createPitchService(pitchNullValues);
    }

    // Catching incorrect data types
    @Test(expectedExceptions = IncorrectDataType.class)
    public void createPitchIncorrectDataType() {
        Mockito.when(pitchDAO.createPitch(pitchIncorrectDataType))
                .thenThrow(new IncorrectDataType("Input type not allowed!"));
        pitchService.createPitchService(pitchIncorrectDataType);
    }

    // Catching too large an amount
    @Test(expectedExceptions = ValueTooLarge.class)
    public void createPitchAmountTooHigh() {
        Mockito.when(pitchDAO.createPitch(AmountTooHigh))
                .thenThrow(new ValueTooLarge("Please enter an amount below one million dollars."));
        pitchService.createPitchService(AmountTooHigh);
    }

    // Catching percent > 100
    @Test(expectedExceptions = ValueTooLarge.class)
    public void createPitchPercentTooHigh() {
        Mockito.when(pitchDAO.createPitch(PercentTooHigh))
                .thenThrow(new ValueTooLarge("Please enter a percentage less than or equal to 100."));
        pitchService.createPitchService(PercentTooHigh);
    }
}
