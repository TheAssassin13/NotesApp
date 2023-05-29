import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note implements Serializable {
    private String title;
    private String content;
    private String color;
    private String theme;
    private Priority priority;
    private final LocalDateTime creationDate;
    public LocalDateTime lastAccessed;
    public LocalDateTime lastModified;


    public Note(String title, String content, String color, String theme, Priority priority) {
        this.title = title.toLowerCase();
        this.content = content.toLowerCase();
        this.color = color.toLowerCase();
        this.theme = theme.toLowerCase();
        this.priority = priority;
        this.creationDate = LocalDateTime.now();
        this.lastAccessed = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title.toLowerCase();
        this.lastModified = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content.toLowerCase();
        this.lastModified = LocalDateTime.now();
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
        this.lastModified = LocalDateTime.now();
    }

    public void setTheme(String theme) {
        this.theme = theme.toLowerCase();
        this.lastModified = LocalDateTime.now();

    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        this.lastModified = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getColor() {
        return color;
    }

    public String getTheme() {
        return theme;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void showNote() {
        System.out.println("Titulo: " + this.title + " (Fecha creacion: " + this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "; Cantidad de palabras: " + this.content.length() + ")");
    }


    @Override
    public String toString() {
        this.lastAccessed = LocalDateTime.now();
        return "Titulo: " + this.title + "\nColor: " + this.color + "\nTema: " + this.theme + "\nPrioridad: " + this.priority + "\nFecha de creacion: " + this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\nFecha ultimo acceso: " + this.lastAccessed.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\nFecha ultima modificacion: " + this.lastModified.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n\nContenido: " + this.content;

    }

}

enum Priority {
    CRITIC,
    HIGH,
    MEDIUM,
    LOW
}
