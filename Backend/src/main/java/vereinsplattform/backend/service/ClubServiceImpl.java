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

    // Get all Clubs
    public List<Club> getClubs (){
        return clubRepository.findAll();
    }

    // Get Club from requesting User
    public Club getClub (String jwt){
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserClub userClub = userClubRepository.findByUserId(user.getId());
        if (userClub == null) return null;
        return userClub.getClub();
    }

    // save a new Club
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    // Update Club
    public Club updateClub(Club club) {
        Club clubToUpdate = clubRepository.getOne(club.getId());
        clubToUpdate.setName(club.getName());
        clubToUpdate.setStreet(club.getStreet());
        clubToUpdate.setZipcode(club.getZipcode());
        clubToUpdate.setCity(club.getCity());
        return clubRepository.save(clubToUpdate);
    }

    // Delete Club
    public void deleteClub(Long id) {
        Club club = clubRepository.getOne(id);
        clubRepository.delete(club);
    }

    // User Joins Club
    public Club joinClub(Long clubid, Long userid) {
        User user = userRepository.getOne(userid);
        Club club = clubRepository.getOne(clubid);

        // User can only join one Club
        Long inClub = this.userService.inClub(user);
        if (inClub == null) {
            UserClub userClub = new UserClub(user, club, "member");
            userClubRepository.save(userClub);
            return club;
        }
        return null;
    }

    // Requesting User leaves his Club
    public void leaveClub(Long clubid, String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Club club = clubRepository.getOne(clubid);

        UserClub userClub = userClubRepository.findByUserIdAndClubId(user.getId(), club.getId());
        userClubRepository.delete(userClub);
    }
    
}
