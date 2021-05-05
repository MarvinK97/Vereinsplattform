package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vereinsplattform.backend.entity.JoinRequest;

import java.util.List;

public interface JoinRequestRepository extends JpaRepository <JoinRequest, Long> {

    List<JoinRequest> findByClubId(Long clubId);
    boolean existsByUserId(Long userId);

    // multiple bind parameters
    @Query("SELECT j FROM JoinRequest j WHERE j.userId = ?1 AND j.clubId = ?2 AND j.accepted = ?3")
    JoinRequest findByUserIdAndClubIdAndAccepted(Long userId, Long clubId, boolean accepted);

    @Query("SELECT j FROM JoinRequest j WHERE j.userId = ?1 AND j.accepted = ?2")
    JoinRequest findByUserIdAndAccepted(Long userId, boolean accepted);

    @Query("SELECT j FROM JoinRequest j WHERE j.clubId = ?1 AND j.accepted = ?2")
    List<JoinRequest> findAllByClubIdAndAccepted(Long clubId, boolean accepted);
}
