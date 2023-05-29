import java.io.*;
import java.util.ArrayList;

public class Persistence {

    public static void serializeEnvironment(ArrayList<Environment> environmentsList) {
        try {
            FileOutputStream file = new FileOutputStream("environments.txt");
            ObjectOutputStream outputObject = new ObjectOutputStream(file);
            outputObject.writeObject(environmentsList);
            outputObject.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Serialize Environments file error");
        }
    }

    public static ArrayList<Environment> deserializeEnvironments() {
        ArrayList<Environment> environmentsList;
        try {
            FileInputStream file = new FileInputStream("environments.txt");
            ObjectInputStream inputObject = new ObjectInputStream(file);
            environmentsList = (ArrayList<Environment>) inputObject.readObject();
            inputObject.close();
            file.close();
            return environmentsList;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Deserialize Environments file error");
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Deserialize Environments class not found");
            return null;
        }
    }
}
