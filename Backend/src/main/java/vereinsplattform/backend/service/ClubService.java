package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.Club;

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

    default void deleteClub(Long club) {

    }

    default Club joinClub(Long request, String jwt) {

        return null;
    }

    default void leaveClub(Long request, String jwt) {

    }

}
