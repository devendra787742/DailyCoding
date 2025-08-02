public class Example5 {
    public static void main(String[] args) {
        int encoded [] = {7,6,8,16,12,3};
        int n = encoded.length;
        int original [] = new int[n];
        original[n-1] = encoded[n-1];
        for(int i=n-2;i>=0;i--){
            original[i] = encoded[i] - original[i+1];
        }
        System.out.print("original Array :");
        for(int i=0; i<original.length; i++){
            System.out.print(original[i]+ " ");
        }
        System.out.println();
    }
}
