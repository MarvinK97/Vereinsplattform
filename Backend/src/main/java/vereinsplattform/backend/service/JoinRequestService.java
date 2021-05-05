package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.JoinRequest;

import java.util.List;

public interface JoinRequestService {

    default List<JoinRequest> getJoinRequests(){
        return null;
    }

    default List<JoinRequest> getJoinRequests(Long clubId) {return null; }

    default JoinRequest saveJoinRequest(JoinRequest joinRequest) {
        return null;
    }

    default JoinRequest updateJoinRequest(JoinRequest joinRequest) {
        return null;
    }

    default boolean existsJoinRequest(Long userId) {return true; }

    void deleteJoinRequest(JoinRequest joinRequest);

    default void accomplishJoinRequest(Long id) {}

}
