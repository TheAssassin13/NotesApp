//Made by Vicente Díaz and Benjamín Farías

public class Main {
    public static void main(String[] args) {
        MenuApp program = new MenuApp();
        if (!program.signIn()) {
            program.showMenu();
        }
    }
}