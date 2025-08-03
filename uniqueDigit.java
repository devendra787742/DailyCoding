import java.util.*;
public class uniqueDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number ");
        int number = sc.nextInt();
        int arr[] = new int [10];
        for (int i=0; i<10; i++){
            arr[i]=0;
        }
        while (number>0) {
            int digit = number%10;
            arr[digit] = arr[digit]+1;
            number = number/10;
        }
        System.out.print("unique digit is ");
        for(int i=0; i<10; i++){
            if(arr[i]==1){
                System.out.print(i+ " ");
            }
        }
    }
}
