import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Environment implements Serializable {
    public User user;
    private ArrayList<Note> notes;
    private ArrayList<Note> recycleBin;

    public Environment(User user) {
        this.notes = new ArrayList<>();
        this.recycleBin = new ArrayList<>();
        this.user = user;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void deleteNote(int i) {
        i -= 1;
        Note temp = new Note(this.notes.get(i).getTitle(), this.notes.get(i).getContent(), this.notes.get(i).getColor(), this.notes.get(i).getTheme(), this.notes.get(i).getPriority());
        this.recycleBin.add(temp);
        this.notes.remove(i);
    }

    public void deleteAllNotes() {
        ArrayList<Note> temp = new ArrayList<>(this.notes);
        this.recycleBin.addAll(temp);
        this.notes.removeAll(this.notes);
    }

    public void deleteNotePermanently(int i) {
        this.recycleBin.remove(i - 1);
    }

    public void deleteAllPermanently() {
        this.recycleBin.removeAll(this.recycleBin);
    }

    public void restoreNote(int i) {
        i -= 1;
        Note temp = new Note(this.notes.get(i).getTitle(), this.notes.get(i).getContent(), this.notes.get(i).getColor(), this.notes.get(i).getTheme(), this.notes.get(i).getPriority());
        this.notes.add(temp);
        this.recycleBin.remove(i);
    }

    public void restoreAll() {
        ArrayList<Note> temp = new ArrayList<>(this.recycleBin);
        this.notes.addAll(temp);
        this.recycleBin.removeAll(this.recycleBin);
    }

    public void showNotes(ArrayList<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            notes.get(i).showTitle();
        }
    }

    public void showNotes() {
        for (int i = 0; i < notes.size(); i++) {
            System.out.print("[" + i + "] Titulo: " + notes.get(i).getTitle());
        }
    }

    public void showRecycleBin() {
        System.out.println("Papelera de reciclaje: ");
        showNotes(this.recycleBin);
    }

    public void showByTitle() {
        ArrayList<Note> tempNotes = new ArrayList<>(this.notes);
        tempNotes.sort(Comparator.comparing(Note::getTitle));

        System.out.println("Notas: ");
        for (int i = 0; i < tempNotes.size(); i++) {
            tempNotes.get(i).showTitle();
            System.out.println();
        }
    }

    public void showByColor() {
        ArrayList<Note> tempNotes = new ArrayList<>(this.notes);
        tempNotes.sort(Comparator.comparing(Note::getColor));

        System.out.println("Notas: ");
        for (int i = 0; i < tempNotes.size(); i++) {
            tempNotes.get(i).showTitle();
            System.out.println(" Color: " + tempNotes.get(i).getColor());
        }

    }

    public void showByTheme() {
        ArrayList<Note> tempNotes = new ArrayList<>(this.notes);
        tempNotes.sort(Comparator.comparing(Note::getTheme));

        System.out.println("Notas: ");
        for (int i = 0; i < tempNotes.size(); i++) {
            tempNotes.get(i).showTitle();
            System.out.println(" Tema: " + tempNotes.get(i).getTheme());
        }
    }

    public void showByPriority() {
        ArrayList<Note> tempNotes = new ArrayList<>(this.notes);
        tempNotes.sort(Comparator.comparing(Note::getPriority));

        System.out.println("Notas: ");
        for (int i = 0; i < tempNotes.size(); i++) {
            tempNotes.get(i).showTitle();
            System.out.println(" Prioridad: " + tempNotes.get(i).getPriority());
        }
    }

    public void showByCreationDate() {
        ArrayList<Note> tempNotes = new ArrayList<>(this.notes);
        tempNotes.sort(Comparator.comparing(Note::getCreationDate));

        System.out.println("Notas: ");
        for (int i = 0; i < tempNotes.size(); i++) {
            tempNotes.get(i).showTitle();
            System.out.println(" Fecha de creacion: " + tempNotes.get(i).getCreationDate());
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public ArrayList<Note> getRecycleBin() {
        return recycleBin;
    }
}