package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.entity.UserClub;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    UserClub findByUserIdAndClubId(Long userId, Long clubId);
}
