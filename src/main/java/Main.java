import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by niladrib on 7/12/2017.
 */

public class Main {
    public static void main(String[] args) {
        boolean aBoolean = false;
        Scanner s=new Scanner(System.in);
        System.out.println("1)Roman To Decimal 2)Decimal To Roman 3)Add Statement");
        String option=s.nextLine();
        if(option.contains("2")){
            do {
                aBoolean = false;
                try {
                    System.out.println("Enter a Decimal number: ");
                    ToRoman tr=new ToRoman(Integer.parseInt(s.nextLine()));
                    tr.getter();

                }catch (NumberFormatException ne){
                    System.out.println("Input mismatch!");
                    aBoolean=true;
                }
            }while(aBoolean);
        }else if(option.contains("1")){
            System.out.println("Enter a Roman number: ");
            ToDecimal td=new ToDecimal(new String(s.nextLine()));
            System.out.println(td.retun());
        }else if(option.contains("3")){
            Parser p=new Parser();
        }
    }
}