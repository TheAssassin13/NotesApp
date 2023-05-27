import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
//Vicente Díaz y Benjamín Farías

public class Main {
    public static void main(String[] args) {
        /*User u1 = new User("202399123", "Camilo", "Briones", "c.briones@estudiantes.org", "+56978781212", null);
        Note n1 = new Note("btitulo 1", "contenidooosoo nota 1", "Rojo", "Fiesta", Priority.HIGH);
        Note n2 = new Note("titulo 2", "contenidooosoo nota 2", "Verde", "Universidad", Priority.LOW);
        Note n3 = new Note("atitulo 3", "contenidooosoo nota 3", "Azul", "Nombres de personas", Priority.HIGH);
        Note n4 = new Note("titulo 4", "contenidooosoo nota 4", "Rosa", "Notas", Priority.CRITIC);
        Note n5 = new Note("gtitulo 5", "contenidooosoo nota 5", "Negro", "Hogar", Priority.MEDIUM);
        Note n6 = new Note("titulo 6", "contenidooosoo nota 6", "Rojo", "Fiesta", Priority.HIGH);
        Note n7 = new Note("atitulo 7", "contenidooosoo nota 7", "Azul", "Personas que me he comido", Priority.HIGH);


        Environment env = new Environment(u1);
        env.addNote(n1);
        env.addNote(n2);
        env.addNote(n3);
        env.addNote(n4);
        env.addNote(n5);
        env.addNote(n6);
        env.addNote(n7);

        env.showByColor();
        System.out.println("\n\n");
        env.showByTitle();
        boolean ver = Verifier.verifyDate(3, 12, 2003);
        System.out.println(ver);*/
        MenuApp program = new MenuApp();
        if (!program.signIn()) {
            program.showMenu();
        }
    }
}