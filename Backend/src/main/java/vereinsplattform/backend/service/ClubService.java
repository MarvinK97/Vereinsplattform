package vereinsplattform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.entity.UserClub;
import vereinsplattform.backend.payload.request.JoinClubRequest;
import vereinsplattform.backend.repository.ClubRepository;
import vereinsplattform.backend.repository.UserClubRepository;
import vereinsplattform.backend.repository.UserRepository;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserClubRepository userClubRepository;

    public List<Club> getClubs (){
        return clubRepository.findAll();
    }

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    public Club updateClub(Club club) {
        Club clubToUpdate = clubRepository.getOne(club.getId());
        clubToUpdate.setName(club.getName());
        clubToUpdate.setStreet(club.getStreet());
        clubToUpdate.setZipcode(club.getZipcode());
        clubToUpdate.setCity(club.getCity());
        return clubRepository.save(clubToUpdate);
    }

    public void deleteClub(Club club) {
        clubRepository.delete(club);
    }

    public void joinClub(JoinClubRequest request) {
        User user = userRepository.getOne(request.getUserId());
        Club club = clubRepository.getOne(request.getClubId());

        UserClub userClub = new UserClub(user, club, "member");
        userClubRepository.save(userClub);
    }

    public void leaveClub(JoinClubRequest request) {
        User user = userRepository.getOne(request.getUserId());
        Club club = clubRepository.getOne(request.getClubId());

        UserClub userClub = userClubRepository.findByUserIdAndClubId(user.getId(), club.getId());
        userClubRepository.delete(userClub);
    }
    
}
