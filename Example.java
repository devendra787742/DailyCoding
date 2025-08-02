import java.util.*;
public class Example {
    public static void main(String[] args) {
        String s;
        System.out.println("enter the string");
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        int l = s.length();
        for(int i=l-1;i>=1/2;i--){
            System.out.print(s.charAt(i));
        }
        for(int i=0; i<1/2; i++){
            System.out.print(s.charAt(i));
        }      
        
    }
    
}
