package vereinsplattform.backend.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table (name = "notifications")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String message;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    private Timestamp editedAt = new Timestamp(System.currentTimeMillis());

    @NotBlank
    private Long clubId;

    public Notification(){

    }

    public Notification(String message, Long clubId) {
        this.message = message;
        this.clubId = clubId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Timestamp getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Timestamp editedAt) {
        this.editedAt = editedAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", editedAt=" + editedAt +
                ", clubId=" + clubId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return getId().equals(that.getId()) && getMessage().equals(that.getMessage()) && getCreatedAt().equals(that.getCreatedAt()) && getEditedAt().equals(that.getEditedAt()) && getClubId().equals(that.getClubId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMessage(), getCreatedAt(), getEditedAt(), getClubId());
    }
}
