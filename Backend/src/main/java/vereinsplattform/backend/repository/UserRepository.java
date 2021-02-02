package vereinsplattform.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vereinsplattform.backend.entity.Role;
import vereinsplattform.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<AdminUserView> findBy();

    //Projection für Mitgliederverwaltung
    interface AdminUserView {
        String getId();
        String getUsername();
        String getEmail();
        Set<Role> getRoles();
    }
}


