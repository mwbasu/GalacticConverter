import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by niladrib on 7/13/2017.
 */
public class ParaInput {

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

    public void run() {
        Scanner x;
        try {
            x = new Scanner(new File("C://Users//niladrib//Desktop//GalacticMerchant//src//main//input.txt"));
            while (x.hasNextLine()) {
                String s = x.nextLine();
                String[] sArr = s.split(" ");
                // System.out.println(sArr.length);
                if (sArr.length == 3) {
                    mappRe(s);
                } else {
                    if (s.contains("Credits") && !s.contains("how")) {
                        statementCollect(s);
                    } else if (s.contains("?") && s.contains("how")) {
                        statementToDeci(s);
                    }else {
                        System.out.println("\n"+s);
                        System.out.println("I have no idea what you are talking about!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Err in I/O");
        }
    }

    void mappRe(String sip) throws ArrayIndexOutOfBoundsException {
        String[] arr = sip.split(" ");
        if (map.containsKey(arr[2]) && arr[2].length() == 1 && arr.length == 3) {
            map.put(arr[2], arr[0]);
        } else {
            System.out.println("Input error! Try again.");
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
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (ss.equals(entry.getValue())) {
                        sb.append(entry.getKey());
                    }
                }
            } else {
                ToDecimal td = new ToDecimal(sb.toString());
                romVal = td.retun();
                double diValue = (Integer.parseInt(arr[count + 1]) / romVal);
                map1.put(ss, diValue);
                System.out.println("Value of " + ss + " is " + diValue);
                break;
            }
        }
    }

    void statementToDeci(String stmnt) {
        double romVal;
        //optional
        System.out.println("\n" + stmnt);
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
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (ss.equals(entry.getValue())) {
                        sb.append(entry.getKey());
                        if (count == arr.size()) {
                            ToDecimal td = new ToDecimal(sb.toString());
                            romVal = td.retun();
                            if (romVal > 0) {
                                System.out.println(outputStmnt + "is " + (int)romVal);
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
                    for (Map.Entry<String, Double> entry : map1.entrySet()) {
                        if (ss.equals(entry.getKey().toString())) {
                            valueOfVar = entry.getValue();
                        }
                    }
                } else {
                    System.out.println("I have no idea what you are talking about");
                    break;
                }
                double mulValue = romVal * valueOfVar;
                if(mulValue%1==0.5){
                    System.out.println(outputStmnt + "is " + mulValue + " Credits");
                }else {
                    System.out.println(outputStmnt + "is " + (int)mulValue + " Credits");
                }

                break;
            }
        }
    }
}