import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by niladrib on 7/12/2017.
 */
public class ToDecimal {
    String input;
    int decimal;
    public ToDecimal(String iString) {
        input=iString;
        Boolean isokay=formatChecker(input);
        if(isokay){
            decimal=decimalConverter(iString);
        }
    }
    public int retun(){
        return decimal;
    }
    public static int decimalConverter(String input){
        int res=0;
        int values[]=new int[input.length()];
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)== 'I'){
                values[i]=1;
            }else if(input.charAt(i)=='V'){
                values[i]=5;
            }else if(input.charAt(i)=='X'){
                values[i]=10;
            }else if(input.charAt(i)=='L'){
                values[i]=50;
            }else if(input.charAt(i)=='C'){
                values[i]=100;
            }else if(input.charAt(i)=='D'){
                values[i]=500;
            }else if(input.charAt(i)=='M'){
                values[i]=1000;
            }
        }
        for(int i=(input.length()-1); i>0; i--){
            if(values[i]>values[i-1]){
                res+=(values[i]-values[i-1]);
                i-=1;
            }else if(values[i]<values[i-1]){
                res+=values[i];
            }else if(values[i]<values[i-1] && i==1){
                res+=values[i]+values[i-1];
            }else if(values[i]==values[i-1] && i>1){
                res+=values[i];
            }else if(values[i]==values[i-1] && i==1){
                res+=values[i]+values[i-1];
            }
        }if(values[1]<values[0]){
            res+=values[0];
        }
        return res;
    }
    public static Boolean formatChecker(String s){
        Boolean isOkay=true;
        //"D", "L", and "V" can never be repeated.
        if(s.contains("DD")||s.contains("LL")||s.contains("VV")){
            isOkay=false;
            System.out.println("ERR! Contains DD / LL / VV");
        }
        //The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more
        if(s.contains("IIII")||s.contains("XXXX")||s.contains("CCCC")||s.contains("MMMM")){
            isOkay=false;
            System.out.println("ERR! Contains IIII / XXXX / CCCC / MMMM");
        }
        //"I" can be subtracted from "V" and "X" only
        if(s.contains("IL")||s.contains("IC")||s.contains("ID")||s.contains("IM")){
            isOkay=false;
            System.out.println("ERR! contains IL / IC / ID / IM ");
        }
        //"X" can be subtracted from "L" and "C" only
        if(s.contains("XD")||s.contains("XM")){
            isOkay=false;
            System.out.println("ERR! contains XD / XM ");
        }
        //"V", "L", and "D" can never be subtracted.
        if(s.contains("DM")||s.contains("LC")||s.contains("LD")||s.contains("LM")||s.contains("VX")||s.contains("VL")||s.contains("VC")||s.contains("VD")||s.contains("VM")){
            isOkay=false;
            System.out.println("ERR! contains DM / LC / LD / LM / VX / VL / VC / VD / VM");
        }
        //s.matches("[^(I|V|X|L|C|D|M)]")
        if(!(s.contains("I") || s.contains("V") || s.contains("X") || s.contains("L") || s.contains("C") || s.contains("D") || s.contains("M"))){
            isOkay=false;
            System.out.println("ERR! can't find I / V / X / L / C / D / M");
        }
        Pattern pattern=Pattern.compile("[a-z]+|[0-9]+");
        Matcher matcher=pattern.matcher(s);
        if(matcher.find()){
            isOkay=false;
            System.out.println("Err! contains lowercase or number");
        }
        return isOkay;
    }
}