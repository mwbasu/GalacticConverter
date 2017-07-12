import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by niladrib on 7/12/2017.
 */
public class RomanTest {

    @Test
    public void IntToRomanTest1() {
        assertEquals(ToRoman.romanConverter(35), "XXXV");
    }
    @Test
    public void IntToRomanTest2() {
        assertEquals(ToRoman.romanConverter(20), "XX");
    }
}
