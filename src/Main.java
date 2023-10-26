import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("What is i");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        if ((i + 2) == 5){
            System.out.println("it is 5, i was "+i);
        } else {
            System.out.println("it is not 5");
        }
    }
}