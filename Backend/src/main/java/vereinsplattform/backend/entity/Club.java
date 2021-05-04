package vereinsplattform.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clubs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Club implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String street;

    @NotBlank
    @Size(max = 5)
    private String zipcode;

    @NotBlank
    @Size(max = 64)
    private String city;

    @OneToMany(mappedBy = "club")
    protected Set<UserClub> users = new HashSet<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Notification> notifications;

    public Club() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
