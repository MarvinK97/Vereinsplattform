package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.dto.request.JoinClubRequest;

import java.util.List;

public interface ClubService {

    default List<Club> getClubs(){
        return null;
    }

    default Club getClub(String jwt){
        return null;
    }

    default Club saveClub(Club club) {
        return null;
    }

    default Club updateClub(Club club) {
        return null;
    }

    default void deleteClub(Club club) {

    }

    default void joinClub(JoinClubRequest request, String jwt) {

    }

    default void leaveClub(JoinClubRequest request, String jwt) {

    }

}
