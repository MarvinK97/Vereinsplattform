package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vereinsplattform.backend.entity.Club;

@Repository
public interface ClubRepository extends JpaRepository <Club, Long> {



}
