package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vereinsplattform.backend.entity.JoinRequest;

import java.util.List;

public interface JoinRequestRepository extends JpaRepository <JoinRequest, Long> {

    List<JoinRequest> findByClubId(Long clubId);
    boolean existsByUserId(Long userId);

}
