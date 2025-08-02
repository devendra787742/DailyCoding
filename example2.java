public class example2 {
    public static void main(String[] args) {
        int arr[] = {123, 4567, 89, 12, 5, -246};
        int sum = 0;
        int secondLastDigit ;
        for(int i=0; i<arr.length; i++){
            arr[i] = Math.abs(arr[i]);
            
            if(arr[i]<10){
                secondLastDigit = 0;
            }
            else{
                secondLastDigit = (arr[i]/10)%10;
            }
            sum = sum + secondLastDigit;
        }
        System.out.println("sum of secondLastDigit is "+ sum);
    }
    
}
