import java.util.*;
public class SumOfSecondLastDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("give the length of array here ");
        int n = sc.nextInt();
        int []number =  new int [n];
        for(int i=0; i<n; i++){
            number[i] = sc.nextInt();
        }
        int sum=0;
        for(int i=0; i<number.length; i++){
            int absNumber = Math.abs(number[i]);
            int secondLastDigit;
            if(absNumber<10){
                secondLastDigit = 0;
            }
            else{
                secondLastDigit = (absNumber/10)%10;
                
            }

            sum += secondLastDigit;

        }
        System.out.println("the sum of second last digit is :"+ sum);
    }
    
}
