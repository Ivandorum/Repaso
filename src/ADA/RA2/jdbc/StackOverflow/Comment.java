package ADA.RA2.jdbc.StackOverflow;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Comment {

    private int id;
    private String descripcion;
    private int type;
    private LocalDateTime createdAt;
    private int authorId;

    public Comment(String descripcion, int type, LocalDateTime createdAt) {
        this.descripcion = descripcion;
        this.type = type;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }
}
