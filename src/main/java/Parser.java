import java.util.*;
import java.util.Map.Entry;

/**
 * Created by niladrib on 7/12/2017.
 */
public class Parser {
    Scanner s = new Scanner(System.in);
    Boolean cont1 = true, cont2 = true, cont3 = true, cont4 = true;
    private final static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    private final LinkedHashMap<String, Double> map1 = new LinkedHashMap<String, Double>();

    static {
        map.put("M", "0");
        map.put("D", "0");
        map.put("C", "0");
        map.put("L", "0");
        map.put("X", "0");
        map.put("V", "0");
        map.put("I", "0");
    }

    public void parserCall() {
        //mapp();
        mappReCaller();
        statementCollectCaller();
        statementToDeciCaller();
    }

    void mappReCaller() {
        try {
            System.out.println("Enter a relation:");
            String s2 = s.nextLine();
            mappRe(s2);
        } catch (Exception e) {
            System.out.println("Error in input statement! Try again.");
            mappReCaller();
        }
    }

    void mappRe(String sip) throws ArrayIndexOutOfBoundsException {

        String[] arr = sip.split(" ");
        if (map.containsKey(arr[2]) && arr[2].length() == 1 && arr.length == 3) {
            map.put(arr[2], arr[0]);
        } else {
            System.out.println("Input error! Try again.");
            mappReCaller();
        }
        System.out.println("Add more? 1)Yes  2)No");
        if (new String(s.nextLine()).contains("2")) {
            cont4 = false;
        } else {
            mappReCaller();
        }

    }

    void statementToDeciCaller() {
        System.out.println("Enter a statement to translate: ");
        String stmnt = s.nextLine();
        statementToDeci(stmnt);
    }

    void statementToDeci(String stmnt) {
        double romVal;

            StringBuilder sb = new StringBuilder();
            StringBuilder outputStmnt = new StringBuilder();
            String[] arrTemp = stmnt.split(" ");
            ArrayList<String> arr = new ArrayList<String>();
            if (stmnt.contains("much")) {
                for (int x = 3; x < (arrTemp.length - 1); x++) {
                    arr.add(arrTemp[x]);
                }
            } else if (stmnt.contains("many")) {
                for (int x = 4; x < (arrTemp.length - 1); x++) {
                    arr.add(arrTemp[x]);
                }
            } else {
                for (int x = 0; x < arrTemp.length; x++) {
                    arr.add(arrTemp[x]);
                }
            }
            for (String s1 : arr) {
                outputStmnt.append(s1 + " ");
            }
            double count = 0, valueOfVar = 0;
            for (String ss : arr) {
                count += 1;
                if (map.containsValue(ss)) {
                    for (Entry<String, String> entry : map.entrySet()) {
                        if (ss.equals(entry.getValue())) {
                            sb.append(entry.getKey());
                            if (count == arr.size()) {
                                ToDecimal td = new ToDecimal(sb.toString());
                                romVal = td.retun();
                                if (romVal > 0) {
                                    System.out.println(outputStmnt + "is " + (int) romVal);
                                } else {
                                    System.out.println("I have no idea what you are talking about!");
                                }
                            }
                        }
                    }
                } else {
                    ToDecimal td = new ToDecimal(sb.toString());
                    romVal = td.retun();
                    if (map1.containsKey(ss)) {
                        for (Entry<String, Double> entry : map1.entrySet()) {
                            if (ss.equals(entry.getKey().toString())) {
                                valueOfVar = entry.getValue();
                            }
                        }
                    } else {
                        System.out.println("I have no idea what you are talking about");
                        break;
                    }
                    double mulValue = romVal * valueOfVar;
                    System.out.println(outputStmnt + "is " + (int) mulValue + " Credits");
                    break;
                }
            }
            System.out.println("Enter more statement to translate? 1)Yes 2)No");
            if (new String(s.nextLine()).contains("2")) {
                cont3 = false;
            }else {
                statementToDeciCaller();
            }
    }

    void mapp() {
        Iterator<String> itr = map.keySet().iterator();
        while (itr.hasNext() && cont1) {
            String rom = itr.next();
            System.out.println("What is " + rom + "? (Press 0 to skip)");
            String tmp1 = s.nextLine();
            if (!tmp1.contains("0")) {
                map.put(rom, tmp1);
            }
        }
    }

    void statementCollectCaller() {
        try {
            System.out.println("Enter a statement: ");
            String stmnt = s.nextLine();
            statementCollect(stmnt);
        } catch (Exception e) {
            System.out.println("Error in input statement! Try again.");
            statementCollectCaller();
        }
    }

    void statementCollect(String stmnt) throws ArrayIndexOutOfBoundsException {
        double romVal;
        StringBuilder sb = new StringBuilder();
        String[] arr = stmnt.split(" ");
        int count = 0;
        for (String ss : arr) {
            count += 1;
            if (map.containsValue(ss)) {
                for (Entry<String, String> entry : map.entrySet()) {
                    if (ss.equals(entry.getValue())) {
                        sb.append(entry.getKey());
                    }
                }
            } else {
                ToDecimal td = new ToDecimal(sb.toString());
                romVal = td.retun();
                double diValue = (Integer.parseInt(arr[count + 1]) / romVal);
                map1.put(ss, diValue);
                if (diValue-(int)diValue==0.5){
                    System.out.println("Value of " + ss + " is " + diValue);
                }else {

                    System.out.println("Value of " + ss + " is " + (int)diValue);
                }
                break;
            }
        }
        System.out.println("Enter more statement? 1)Yes 2)No");
        if (new String(s.nextLine()).contains("2")) {
            cont2 = false;
            statementToDeciCaller();
        } else {
            statementCollectCaller();
        }
    }
}