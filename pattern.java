public class pattern {
    public static void main(String[] args) {
        int rows = 4; // Number of rows in the pattern

        for (int i = 1; i <= rows; i++) {
            // Print leading spaces
            for (int j = i; j < rows; j++) {
                System.out.print("  ");
            }

            // Print 'x' with spaces in between
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("x ");
            }

            // Move to the next line after each row
            System.out.println();
        }
    }
}

