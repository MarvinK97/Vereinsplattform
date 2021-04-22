package vereinsplattform.backend.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.entity.UserClub;
import vereinsplattform.backend.payload.request.JoinClubRequest;
import vereinsplattform.backend.repository.ClubRepository;
import vereinsplattform.backend.repository.UserClubRepository;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.security.jwt.JwtUtils;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    final JwtUtils jwtUtils;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;

    public ClubServiceImpl(JwtUtils jwtUtils, ClubRepository clubRepository, UserRepository userRepository, UserClubRepository userClubRepository) {
        this.jwtUtils = jwtUtils;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.userClubRepository = userClubRepository;
    }

    public List<Club> getClubs (){
        return clubRepository.findAll();
    }

    public Club getClub (String jwt){
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserClub userClub = userClubRepository.findByUserId(user.getId());
        return userClub.getClub();
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

    public void joinClub(JoinClubRequest request, String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Club club = clubRepository.getOne(request.getClubId());

        //TODO: User can only join one club

        UserClub userClub = new UserClub(user, club, "member");
        userClubRepository.save(userClub);
    }

    public void leaveClub(JoinClubRequest request, String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Club club = clubRepository.getOne(request.getClubId());

        UserClub userClub = userClubRepository.findByUserIdAndClubId(user.getId(), club.getId());
        userClubRepository.delete(userClub);
    }
    
}
