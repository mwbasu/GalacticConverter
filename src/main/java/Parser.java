import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by niladrib on 7/12/2017.
 */
public class Parser {
    Scanner s=new Scanner(System.in);
    Boolean cont1=true, cont2=true, cont3=true, cont4=true;
    private final static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    private final LinkedHashMap<String, Integer> map1 = new LinkedHashMap<String, Integer>();
    static {
        map.put("M", "0");
        map.put("D", "0");
        map.put("C", "0");
        map.put("L", "0");
        map.put("X", "0");
        map.put("V", "0");
        map.put("I", "0");
    }
    public Parser() {
        //mapp();
        mappRe();
        statementCollect();
        statementToDeci();
    }
    void mappRe(){
        while(cont4){
            System.out.println("Enter a relation:");
            String s2=s.nextLine();
            String[] arr = s2.split(" ");
            if(map.containsKey(arr[2])){
                map.put(arr[2],arr[0]);
            }
            System.out.println("Add more? 1)Yes  2)No");
            if(new String(s.nextLine()).contains("2")){
                cont4=false;
            }
        }
    }

    void statementToDeci(){
        int romVal;
        while(cont3){
            System.out.println("Enter a statement to translate: ");
            String stmnt=s.nextLine();
            StringBuilder sb=new StringBuilder();
            String[] arr = stmnt.split(" ");
            int count=0, valueOfVar = 0;
            for(String ss: arr){
                count+=1;

                if(map.containsValue(ss)){
                    for(Entry<String, String> entry:map.entrySet()){

                        if(ss.equals(entry.getValue())){
                            sb.append(entry.getKey());
                            if(count==arr.length){
                                ToDecimal td=new ToDecimal(sb.toString());
                                romVal=td.retun();
                                System.out.println(stmnt+" is "+romVal);
                            }
                        }
                    }
                }else{

                    ToDecimal td=new ToDecimal(sb.toString());
                    romVal=td.retun();
                    if(map1.containsKey(ss)){
                        for(Entry<String, Integer> entry: map1.entrySet()){
                            if(ss.equals(entry.getKey().toString())){
                                valueOfVar=entry.getValue();
                            }
                        }
                    }
                    int mulValue=romVal*valueOfVar;
                    System.out.println(stmnt+" is "+mulValue);
                    break;
                }
            }
            System.out.println("Enter more statement to translate? 1)Yes 2)No");
            if(new String(s.nextLine()).contains("2")){
                cont3=false;
            }
        }
    }
    void mapp(){
        Iterator<String> itr=map.keySet().iterator();
        while(itr.hasNext() && cont1){
            String rom=itr.next();
            System.out.println("What is "+rom+"? (Press 0 to skip)");
            String tmp1=s.nextLine();
            if(!tmp1.contains("0")){
                map.put(rom, tmp1);
            }
        }
    }
    void statementCollect(){
        int romVal;
        while(cont2){
            System.out.println("Enter a statement: ");
            String stmnt=s.nextLine();
            StringBuilder sb=new StringBuilder();
            String[] arr = stmnt.split(" ");
            int count=0;
            for(String ss: arr){
                count+=1;
                if(map.containsValue(ss)){
                    //System.out.println(ss + "1");
                    for(Entry<String, String> entry:map.entrySet()){
                        if(ss.equals(entry.getValue())){
                            sb.append(entry.getKey());
                        }
                    }
                }else{
                    //
                    ToDecimal td=new ToDecimal(sb.toString());
                    romVal=td.retun();
                    int diValue=(Integer.parseInt(arr[count+1])/romVal);
                    map1.put(ss, diValue);
                    System.out.println("Value of "+ss+" is "+diValue);
                    break;
                }
            }
            System.out.println("Enter more statement? 1)Yes 2)No");
            if(new String(s.nextLine()).contains("2")){
                cont2=false;
                //System.out.println("Im");
                statementToDeci();
            }
        }
    }
}
