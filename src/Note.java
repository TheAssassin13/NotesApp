import java.io.Serializable;
import java.time.LocalDateTime;

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
        this.title = title;
        this.content = content;
        this.color = color;
        this.theme = theme;
        this.priority = priority;
        this.creationDate = LocalDateTime.now();
        this.lastAccessed = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastModified = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
        this.lastModified = LocalDateTime.now();
    }

    public void setColor(String color) {
        this.color = color;
        this.lastModified = LocalDateTime.now();

    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public void showTitle() {
        System.out.print("Titulo: " + this.title);
    }

    public void showTitleContent() {
        System.out.print("Titulo: " + this.title + "\nContenido: " + this.content);

    }

    @Override
    public String toString() {
        this.lastAccessed = LocalDateTime.now();
        return "Titulo: " + this.title + "\nColor: " + this.color + "\nTema: " + this.theme + "\nPrioridad: " + this.priority + "\nFecha de creacion: " + this.creationDate + "\nFecha ultimo acceso: " + this.lastAccessed + "\nFecha ultima modificacion: " + this.lastModified + "\n\nContenido: " + this.content;

    }

}

enum Priority {
    CRITIC,
    HIGH,
    MEDIUM,
    LOW
}
