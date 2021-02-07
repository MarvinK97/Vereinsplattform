package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vereinsplattform.backend.entity.UserClub;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    UserClub findByUserIdAndClubId(Long userId, Long clubId);
    UserClub findByUserId(Long userId);
}
