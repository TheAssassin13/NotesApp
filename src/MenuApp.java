import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuApp {

    private final Scanner scan = new Scanner(System.in);
    public ArrayList<Environment> environments = new ArrayList<>((Persistence.deserializeEnvironments()));
    public Environment currentEnvironment;

    public void showMenu() {
        int option = 0;
        System.out.println("¡Bienvenido " + this.currentEnvironment.user.firstName + " !");
        while (option != 10) {
            System.out.println("\n[0] Crear Nota\n[1] Editar nota\n[2] Mostrar notas\n[3] Mostrar notas ordenadas por\n[4] Eliminar nota\n[5] Eliminar todas las notas");
            System.out.println("[6] Papelera de reciclaje\n[7] Mostrar informacion de usuario\n[8] Editar informacion de usuario\n[9] Cerrar sesión\n[10] Salir del programa");
            System.out.print("Ingrese una opcion: ");
            option = Verifier.verifyInputInt(15); //Cantidad de opciones
            System.out.println();

            switch (option) {
                case 0:
                    createNote();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 1:
                    editNote();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 2:
                    showNotes();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 3:
                    showBy();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 4:
                    deleteNote();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 5:
                    deleteAllNotes();
                    Persistence.serializeEnvironment(this.environments);
                    break;

                case 6:
                    recycleBin();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 7:
                    showUserData();
                    Persistence.serializeEnvironment(this.environments);

                    break;
                case 8:
                    editUserData();
                    Persistence.serializeEnvironment(this.environments);

                    break;
                case 9:
                    if (signIn()) {
                        option = 10;
                    } else {
                        System.out.println("¡Bienvenido " + this.currentEnvironment.user.firstName + " !\n");
                    }
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 10:
                    logoutUser();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta.");
                    break;
            }
        }
    }

    public boolean signIn() {
        System.out.println("[0] Iniciar sesion\n[1] Registrar usuario\n[2] Salir");
        System.out.print("Ingrese opcion: ");
        int option = Verifier.verifyInputInt(3);
        switch (option) {
            case 0:
                loginUser();
                break;
            case 1:
                registerUser();
                Persistence.serializeEnvironment(this.environments);
                break;
            case 2:
                System.out.println("Has salido.");
                return true;
            default:
                System.out.println("Ingrese una opcion correcta.");
                break;
        }
        return false;
    }

    public void loginUser() {
        String userName;
        String userPassword;
        int index;
        int attempts = 3;
        System.out.println("---------- Iniciar sesion ----------");
        System.out.print("Nombre de usuario (email): ");
        userName = scan.nextLine();

        for (index = 0; index < this.environments.size(); index++) {
            if (Verifier.verifyUserLogin(this.environments.get(index).user, userName)) {
                break;
            }
        }
        if (index != this.environments.size()) {
            do {
                if (attempts < 3) {
                    System.out.println("Intentos restantes: " + attempts);
                }
                System.out.print("Contraseña: ");
                userPassword = scan.nextLine();
                attempts -= 1;
                if (!Verifier.verifyPasswordLogin(this.environments.get(index).user, userPassword))
                    System.out.println("Contraseña incorrecta.");
            } while (!Verifier.verifyPasswordLogin(this.environments.get(index).user, userPassword) && attempts > 0);
            if (attempts != 0) {
                System.out.println("Usuario logeado correctamente.\n");
                this.currentEnvironment = this.environments.get(index);
            } else {
                System.out.println("No quedan intentos.\n");
                signIn();
            }

        } else {
            System.out.println("Usuario no encontrado.\n");
            signIn();

        }
    }

    public void logoutUser() {
        this.currentEnvironment = null;
        System.out.println("Has salido del programa.");
        //loginUser();
    }

    public void registerUser() {
        String registrationNumber;
        String firstName;
        String lastName;
        String email;
        String phoneNumber;
        LocalDate birthdate;
        String option;

        System.out.println("---------- Registrarse ----------");
        System.out.print("Ingrese su nombre: ");
        firstName = scan.nextLine();

        System.out.print("Ingrese su apellido: ");
        lastName = scan.nextLine();

        do {
            System.out.print("Ingrese un email: ");
            email = scan.nextLine();
            if (!Verifier.verifyEmail(email)) System.out.println("No es un email valido.");
        } while (!Verifier.verifyEmail(email));

        do {
            System.out.print("Ingrese su numero de matricula: ");
            registrationNumber = scan.nextLine();
            if (!Verifier.verifyRegistrationNumber(registrationNumber))
                System.out.println("No es una matricula valida.");
        } while (!Verifier.verifyRegistrationNumber(registrationNumber));

        do {
            System.out.print("¿Deseas agregar un numero de telefono? (si/no): ");
            option = scan.nextLine();
            option = option.toLowerCase();
            if (!option.equals("si") && !option.equals("no")) System.out.println("Ingrese la opcion como se indica.");
        } while (!option.equals("si") && !option.equals("no"));

        if (option.equals("si")) {
            do {
                System.out.print("Ingrese su numero de telefono (+569XXXXXXXX): ");
                phoneNumber = scan.nextLine();
                if ((!Verifier.verifyPhoneNumber(phoneNumber))) System.out.println("No es un numero valido.");
            } while (!Verifier.verifyPhoneNumber(phoneNumber));
        } else {
            phoneNumber = null;
        }

        do {
            System.out.print("¿Deseas agregar una fecha de nacimiento? (si/no): ");
            option = scan.nextLine();
            option = option.toLowerCase();
            if (!option.equals("si") && !option.equals("no")) System.out.println("Ingrese la opcion como se indica.");
        } while (!option.equals("si") && !option.equals("no"));

        if (option.equals("si")) {
            System.out.print("Ingrese la fecha de nacimieto (DD MM YYYY): ");
            birthdate = Verifier.verifyDate();
        } else {
            birthdate = null;
        }

        this.environments.add(new Environment(new User(registrationNumber, firstName, lastName, email, phoneNumber, birthdate)));
        this.currentEnvironment = this.environments.get(this.environments.size() - 1);
        System.out.println("Te has registrado correctamente.\n");
    }

    public void showUserData() {
        System.out.println("Nombre: " + this.currentEnvironment.user.firstName + " " + this.currentEnvironment.user.lastName);
        System.out.println("Email: " + this.currentEnvironment.user.email);
        System.out.println("Matricula: " + this.currentEnvironment.user.getRegistrationNumber());
        if (this.currentEnvironment.user.getPhoneNumber() != null) {
            System.out.println("Numero de telefono: " + this.currentEnvironment.user.getPhoneNumber());
        }
        if (this.currentEnvironment.user.getBirthdate() != null) {
            System.out.println("Fecha de nacimiento: " + this.currentEnvironment.user.getBirthdate());
        }
        System.out.println();
    }

    public void editUserData() {
        System.out.println("[0] Numero de telefono\n[1] Fecha de nacimiento\n[2] Email");
        System.out.print("¿Que desea editar o agregar?: ");
        int option = Verifier.verifyInputInt(3);
        if (option == 0) {
            String phoneNumber;
            do {
                System.out.print("Ingrese su numero de telefono (+569XXXXXXXX): ");
                phoneNumber = scan.nextLine();
                if ((!Verifier.verifyPhoneNumber(phoneNumber))) System.out.println("No es un numero valido.");
            } while (!Verifier.verifyPhoneNumber(phoneNumber));
            this.currentEnvironment.user.setPhoneNumber(phoneNumber);
            System.out.println("Se ha editado/agregado el numero de telefono correctamente.");
        } else if (option == 1) {
            System.out.print("Ingrese la fecha de nacimieto (DD MM YYYY): ");
            LocalDate birthdate = Verifier.verifyDate();
            this.currentEnvironment.user.setBirthdate(birthdate);
            System.out.println("Se ha editado/agregado la fecha de nacimiento correctamente.");
        } else {
            String email;
            do {
                System.out.print("Ingrese un email: ");
                email = scan.nextLine();
                if (!Verifier.verifyEmail(email)) System.out.println("No es un email valido.");
            } while (!Verifier.verifyEmail(email));
            System.out.println("Se ha editado el email correctamente.\n");
        }
    }
    public void recycleBin() {
        int option = 0;
        while (option != 5) {
            System.out.println();
            System.out.println("[0] Mostrar papelera\n[1] Eliminar nota permanentemente\n[2] Eliminar todas las notas permanentemente\n[3] Restaurar nota de la papelera\n[4] Restaurar todas las notas de la papelera\n[5] Salir de la papelera");
            System.out.print("Ingrese una opcion: ");
            option = Verifier.verifyInputInt(6); //Cantidad de opciones
            System.out.println();
            switch (option) {
                case 0:
                    showRecycleBin();
                    Persistence.serializeEnvironment(this.environments);
                    break;
                case 1:
                    deleteNotePermanently();
                    Persistence.serializeEnvironment(this.environments);

                    break;
                case 2:
                    deleteAllNotesPermanently();
                    Persistence.serializeEnvironment(this.environments);

                break;
                case 3:
                    restoreNote();
                    Persistence.serializeEnvironment(this.environments);
                break;
                case 4:
                    restoreAll();
                    Persistence.serializeEnvironment(this.environments);
                break;
                case 5:
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta.");
                    break;
            }

        }
    }
    public void showNotes() {
        if (this.currentEnvironment.getNotes().size() == 0) {
            System.out.println("Aun no hay notas. ¡Crea una! :)");
            return;
        }
        System.out.println("Notas del usuario " + this.currentEnvironment.user.firstName + " " + this.currentEnvironment.user.lastName + "\n");
        this.currentEnvironment.showNotes(this.currentEnvironment.getNotes());
        System.out.println();
        String option;
        do {
            System.out.print("¿Desea ver los datos de alguna nota? (si/no): ");
            option = scan.nextLine();
            option = option.toLowerCase();
            if (!option.equals("si") && !option.equals("no")) System.out.println("Ingrese la opcion como se indica.");
        } while (!option.equals("si") && !option.equals("no"));

        if (option.equals("no")) return;
        this.currentEnvironment.showNotes();
        System.out.print("Ingrese la nota a mostrar: ");
        int index = Verifier.verifyInputInt(this.currentEnvironment.getNotes().size());
        System.out.println(this.currentEnvironment.getNotes().get(index));
    }

    public void createNote() {
        String title;
        String content;
        String color;
        String theme;
        Priority priority;
        String strPriority;
        do {
            System.out.print("Ingrese el titulo de la nota: ");
            title = scan.nextLine();
            if (!Verifier.verifyTitleNote(title))
                System.out.println("El titulo sobrepasa los 30 caracteres permitidos.");
        } while (!Verifier.verifyTitleNote(title));

        do {
            System.out.print("Ingrese el contenido: ");
            content = scan.nextLine();
            if (!Verifier.verifyContentNote(content))
                System.out.println("El contenido sobrepasa los 200 caracteres permitidos.");
        } while (!Verifier.verifyContentNote(content));

        System.out.print("Ingrese un color: ");
        color = scan.nextLine();

        System.out.print("Ingrese un tema: ");
        theme = scan.nextLine();

        do {
            System.out.print("¿Es de prioridad baja, media, alta o critica?: ");
            strPriority = scan.nextLine();
            if (Verifier.verifyPriority(strPriority) == null) System.out.println("La prioridad ingresada no es valida.");
        } while (Verifier.verifyPriority(strPriority) == null);
        priority = Verifier.verifyPriority(strPriority);

        this.currentEnvironment.addNote(new Note(title, content, color, theme, priority));
    }

    public void editNote() {
        int index;
        int option;
        if (this.currentEnvironment.getNotes().size() == 0) {
            System.out.println("Aun no hay notas. ¡Crea una! :)");
            return;
        }
        this.currentEnvironment.showNotes();
        System.out.print("Ingrese indice de la nota a editar: ");
        index = Verifier.verifyInputInt(this.currentEnvironment.getNotes().size());
        System.out.println(this.currentEnvironment.getNotes().get(index));
        System.out.println("[0] Titulo\n[1] Color\n[2] Tema\n[3] Prioridad\n[4] Contenido");
        System.out.print("Ingrese elemento a editar: ");
        option = Verifier.verifyInputInt(5);
        switch (option) {
            case 0:
                editNoteTitle(this.currentEnvironment.getNotes().get(index));
                break;
            case 1:
                editNoteColor(this.currentEnvironment.getNotes().get(index));
                break;
            case 2:
                editNoteTheme(this.currentEnvironment.getNotes().get(index));
                break;
            case 3:
                editNotePriority(this.currentEnvironment.getNotes().get(index));
                break;
            case 4:
                editNoteContent(this.currentEnvironment.getNotes().get(index));
                break;
            default:
                System.out.println("Ingrese una opcion correcta.");
                break;
        }
        this.currentEnvironment.getNotes().get(index).lastModified = LocalDateTime.now();
    }

    public void editNoteTitle(Note note) {
        String title;
        do {
            System.out.println("Titulo anterior: ");
            System.out.println(note.getTitle());
            System.out.print("Ingrese nuevo titulo: ");
            title = scan.nextLine();
            if (!Verifier.verifyTitleNote(title))
                System.out.println("El titulo sobrepasa los 30 caracteres permitidos.");
        } while (!Verifier.verifyTitleNote(title));
        note.setTitle(title);
    }

    public void editNoteContent(Note note) {
        String content;
        do {
            System.out.println("Contenido anterior: ");
            System.out.println(note.getContent());
            System.out.print("Ingrese nuevo contenido: ");
            content = scan.nextLine();
            if (!Verifier.verifyContentNote(content))
                System.out.println("El contenido sobrepasa los 200 caracteres permitidos.");
        } while (!Verifier.verifyContentNote(content));
        note.setContent(content);
    }

    public void editNoteColor(Note note) {
        String color;
        System.out.print("Color anterior: ");
        System.out.println(note.getColor());
        System.out.print("Ingrese nuevo color: ");
        color = scan.nextLine();
        note.setColor(color);
    }

    public void editNoteTheme(Note note) {
        String theme;
        System.out.print("Tema anterior: ");
        System.out.println(note.getTheme());
        System.out.print("Ingrese nuevo tema: ");
        theme = scan.nextLine();
        note.setTheme(theme);
    }

    public void editNotePriority(Note note) {
        String strPriority;
        Priority priority;
        System.out.print("Prioridad anterior: ");
        System.out.println(note.getPriority());
        System.out.print("Ingrese nueva prioridad: ");
        do {
            System.out.print("¿Es de prioridad baja, media, alta o critica?: ");
            strPriority = scan.nextLine();
            if (Verifier.verifyPriority(strPriority) == null) System.out.println("La prioridad ingresada no es valida.");
        } while (Verifier.verifyPriority(strPriority) == null);
        priority = Verifier.verifyPriority(strPriority);
        note.setPriority(priority);
    }

    public void deleteNote() {
        if (this.currentEnvironment.getNotes().size() == 0) {
            System.out.println("No existen notas para eliminar.");
            return;
        }
        this.currentEnvironment.showNotes();
        System.out.print("Ingrese indice de la nota a eliminar: ");
        int i = Verifier.verifyInputInt(this.currentEnvironment.getNotes().size());
        this.currentEnvironment.deleteNote(i);
        System.out.println("Nota enviada a la papelera correctamente.");
    }

    public void deleteAllNotes() {
        if (this.currentEnvironment.getNotes().size() == 0) {
            System.out.println("No existen notas para eliminar.");
            return;
        }
        this.currentEnvironment.deleteAllNotes();
        System.out.println("Todas las notas han sido enviadas a la papelera.");

    }

    public void deleteNotePermanently() {
        if (this.currentEnvironment.getRecycleBin().size() == 0) {
            System.out.println("No existen notas para eliminar.");
            return;
        }
        this.currentEnvironment.showRecycleBin();
        System.out.print("Ingrese indice de la nota a eliminar: ");
        int i = Verifier.verifyInputInt(this.currentEnvironment.getNotes().size());
        this.currentEnvironment.deleteNotePermanently(i);
        System.out.println("Nota eliminada correctamente.");
    }

    public void deleteAllNotesPermanently() {
        if (this.currentEnvironment.getRecycleBin().size() == 0) {
            System.out.println("No existen notas para eliminar.");
            return;
        }
        this.currentEnvironment.deleteAllPermanently();
        System.out.println("Todas las notas de la papelera han sido eliminadas definitivamente.");
    }

    public void restoreNote() {
        if (this.currentEnvironment.getRecycleBin().size() == 0) {
            System.out.println("No existen notas para restaurar.");
            return;
        }
        this.currentEnvironment.showRecycleBin();
        System.out.print("Ingrese indice de la nota a restaurar: ");
        int i = Verifier.verifyInputInt(this.currentEnvironment.getRecycleBin().size());
        this.currentEnvironment.restoreNote(i);
        System.out.println("Nota restaurada correctamente.");
    }

    public void restoreAll() {
        if (this.currentEnvironment.getRecycleBin().size() == 0) {
            System.out.println("No existen notas para restaurar.");
            return;
        }
        this.currentEnvironment.restoreAll();
        System.out.println("Todas las notas de la papelera han sido restauradas.");
    }

    public void showRecycleBin() {
        if (this.currentEnvironment.getRecycleBin().size() == 0) {
            System.out.println("Papelera vacia.");
            return;
        }
        this.currentEnvironment.showNotes(this.currentEnvironment.getRecycleBin());
    }

    public void showBy() {
        int option;
        if (this.currentEnvironment.getNotes().size() == 0) {
            System.out.println("Aun no hay notas. ¡Crea una! :)");
            return;
        }
        System.out.println("Mostar notas ordenadas por: ");
        System.out.println("[0] Titulo\n[1] Color\n[2] Tema\n[3] Prioridad\n[4] Fecha de creacion");
        option = Verifier.verifyInputInt(5);
        switch (option) {
            case 0:
                this.currentEnvironment.showByTitle();
                break;
            case 1:
                this.currentEnvironment.showByColor();
                break;
            case 2:
                this.currentEnvironment.showByTheme();
                break;
            case 3:
                this.currentEnvironment.showByPriority();
                break;
            case 4:
                this.currentEnvironment.showByCreationDate();
                break;
            default:
                System.out.println("Ingrese una opcion correcta.");
                break;

        }

    }
}
