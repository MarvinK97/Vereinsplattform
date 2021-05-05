package vereinsplattform.backend.service;

import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.JoinRequest;
import vereinsplattform.backend.repository.JoinRequestRepository;

import java.util.List;

@Service
public class JoinRequestServiceImpl implements JoinRequestService{

    private final JoinRequestRepository joinRequestRepository;

    public JoinRequestServiceImpl(JoinRequestRepository joinRequestRepository) {
        this.joinRequestRepository = joinRequestRepository;
    }

    // Get all JoinRequests
    @Override
    public List<JoinRequest> getJoinRequests() {
        return joinRequestRepository.findAll();
    }

    // Get all JoinRequests for a Club
    @Override
    public List<JoinRequest> getJoinRequests(Long clubId) {
        return joinRequestRepository.findByClubId(clubId);
    }

    // Create a new JoinRequest
    @Override
    public JoinRequest saveJoinRequest(JoinRequest joinRequest) {
        return joinRequestRepository.save(joinRequest);
    }

    // Update an existing JoinRequest
    @Override
    public JoinRequest updateJoinRequest(JoinRequest joinRequest) {
        JoinRequest RequestToUpdate = joinRequestRepository.getOne(joinRequest.getId());
        RequestToUpdate.setClubId(joinRequest.getClubId());
        RequestToUpdate.setUserId(joinRequest.getUserId());
        RequestToUpdate.setEditedAt(joinRequest.getEditedAt());
        RequestToUpdate.setAccepted(joinRequest.isAccepted());
        return joinRequestRepository.save(RequestToUpdate);
    }

    // Delete JoinRequest
    public void deleteJoinRequest(JoinRequest joinRequest) {
        joinRequestRepository.delete(joinRequest);
    }

    // Accomplish a JoinRequest and delete it
    @Override
    public void accomplishJoinRequest(Long id) {


        JoinRequestService.super.accomplishJoinRequest(id);
    }

    @Override
    public boolean existsJoinRequest(Long userId) {
        return joinRequestRepository.existsByUserId(userId);
    }
}
