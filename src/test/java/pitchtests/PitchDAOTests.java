package pitchtests;

import com.investing_app.dao.PitchDAO;
import com.investing_app.dao.PitchDAOImplemented;
import com.investing_app.entities.Pitch;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PitchDAOTests {
    PitchDAO pitchDAO = new PitchDAOImplemented();

    @Test
    void createPitch() {
        Pitch pitch = new Pitch(0, 1, "My Business", "01-12-2022",
                "This is my pitch", 100_000, 2.6, "");
        Pitch returnedPitch = pitchDAO.createPitch(pitch);
        Assert.assertTrue(returnedPitch.getPitchId() != 0);
    }

    @Test
    void viewPitches() {
        List<Pitch> pitches = pitchDAO.viewPitches();
        for (Pitch p : pitches) {
            System.out.println(p);
        }
        Assert.assertTrue(pitches.size() >= 1);
    }

    @Test
    void makeOffer() {
        boolean offer = pitchDAO.makeOffer(1, 120_000, 3.0);
        Assert.assertTrue(offer);
    }

    @Test
    void acceptOffer() {
        boolean accepted = pitchDAO.acceptOffer(2);
        Assert.assertTrue(accepted);
    }
}
