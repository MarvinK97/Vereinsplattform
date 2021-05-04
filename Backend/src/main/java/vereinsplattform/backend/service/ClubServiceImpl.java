package vereinsplattform.backend.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.entity.UserClub;
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
    private final UserService userService;

    public ClubServiceImpl(JwtUtils jwtUtils, ClubRepository clubRepository, UserRepository userRepository, UserClubRepository userClubRepository, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.userClubRepository = userClubRepository;
        this.userService = userService;
    }

    public List<Club> getClubs (){
        return clubRepository.findAll();
    }

    public Club getClub (String jwt){
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserClub userClub = userClubRepository.findByUserId(user.getId());
        if (userClub == null) return null;
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

    public void deleteClub(Long id) {
        Club club = clubRepository.getOne(id);
        clubRepository.delete(club);
    }

    public Club joinClub(Long clubid, String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Club club = clubRepository.getOne(clubid);
        Long inClub = this.userService.inClub(user);
        if (inClub == null) {
            UserClub userClub = new UserClub(user, club, "member");
            userClubRepository.save(userClub);
            return club;
        }
        return null;
    }

    public void leaveClub(Long clubid, String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Club club = clubRepository.getOne(clubid);

        UserClub userClub = userClubRepository.findByUserIdAndClubId(user.getId(), club.getId());
        userClubRepository.delete(userClub);
    }
    
}
