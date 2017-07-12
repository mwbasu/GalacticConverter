import java.util.TreeMap;

/**
 * Created by niladrib on 7/12/2017.
 */
public class ToRoman {
    int input;
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    String roman;

    public ToRoman(int i) {
        input=i;
        roman=romanConverter(input);
    }
    void getter(){
        System.out.println(roman);
    }

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }
    public final static String romanConverter(int number) {
        int i =  map.floorKey(number);
        if ( number == i ) {
            return map.get(number);
        }
        return map.get(i) + romanConverter(number-i);
    }
}