import org.junit.Assert;
import org.junit.Test;

/**
 * Created by niladrib on 7/12/2017.
 */
public class DecimalTest {

    @Test
    public void decimalConverterTest1(){
        Assert.assertEquals(ToDecimal.decimalConverter("XXXV"),35);
    }

    @Test
    public void decimalConverterTest2(){
        Assert.assertEquals(ToDecimal.decimalConverter("XXX"),30);
    }

    @Test
    public void formatCheckerTest1(){
        Assert.assertEquals(ToDecimal.formatChecker("XXX"),true);
    }

    @Test
    public void formatCheckerTest2(){
        Assert.assertEquals(ToDecimal.formatChecker("XXXX"),false);
    }
}