package vereinsplattform.backend.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Immutable
public class UserClub {

    @Embeddable
    public static class Id implements Serializable{

        @Column(name = "USER_ID")
        private Long userId;

        @Column(name = "CLUB_ID")
        private Long clubId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(userId, id.userId) &&
                    Objects.equals(clubId, id.clubId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, clubId);
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "MEMBER_ROLE")
    private String memberRole;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", insertable = false, updatable = false)
    private Club club;

    public UserClub () {}

    public UserClub (User user, Club club, String memberRole) {
        this.user = user;
        this.club = club;
        this.memberRole = memberRole;

        this.id.userId = user.getId();
        this.id.clubId = club.getId();

    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
