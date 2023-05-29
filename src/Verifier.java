import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Verifier {

    public static boolean verifyContentNote(String content) {
        return (content.length() <= 200);
    }

    public static boolean verifyTitleNote(String title) {
        return (title.length() <= 30);
    }

    public static Priority verifyPriority(String priority) {
        priority = priority.toLowerCase();
        if (Objects.equals(priority, "baja")) return Priority.LOW;
        if (Objects.equals(priority, "media")) return Priority.MEDIUM;
        if (Objects.equals(priority, "alta")) return Priority.HIGH;
        if (Objects.equals(priority, "critica")) return Priority.CRITIC;
        return null;
    }

    public static boolean verifyUserLogin(User user, String userName) {
        return (Objects.equals(user.email, userName));
    }

    public static boolean verifyPasswordLogin(User user, String password) {
        return (Objects.equals(user.getPassword(), password));
    }

    public static boolean verifyRegistrationNumber(String number) {
        return (number.length() > 6 && number.length() < 11);
    }

    public static int verifyInputInt(int size) {
        size -= 1;
        int number = 0;
        Scanner scan = new Scanner(System.in);
        do {
            try {
                number = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException exception) {
                System.out.println("No es un numero valido.");
            }
            if (number < 0) System.out.println("Se requiere un numero positivo.");
            if (number > size) System.out.println("Se requiere un numero dentro de los limites.");
        } while (number < 0 || number > size);
        return number;
    }

    public static LocalDate verifyDate() {
        int day = 0;
        int month = 0;
        int year = 0;
        Scanner scan = new Scanner(System.in);
        do {
            do {
                try {
                    day = scan.nextInt();
                    month = scan.nextInt();
                    year = scan.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("Ingrese la fecha en el formato indicado.");
                    scan.nextLine();
                }
            } while (day <= 0 && month <= 0 && year <= 1850);
            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException exception) {
                System.out.println("Ingrese una fecha valida.");
            }
        } while (true);
    }

    public static boolean verifyPhoneNumber(String phoneNumber) {
        return (phoneNumber.startsWith("+569") && phoneNumber.length() == 12);
    }

    public static boolean verifyEmail(String email, ArrayList<Environment> environments) {
        boolean bool = Pattern.compile("^[A-Za-z0-9_.]+@([A-Za-z]+)(\\.[A-Za-z]+)$").matcher(email).matches();
        for (Environment environment : environments) {
            if (environment.user.email.equals(email)) {
                return false;
            }
        }
        return bool;
    }
}