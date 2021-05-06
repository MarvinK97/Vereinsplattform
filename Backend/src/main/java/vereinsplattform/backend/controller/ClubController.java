package vereinsplattform.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.JoinRequest;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.repository.JoinRequestRepository;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.security.jwt.JwtUtils;
import vereinsplattform.backend.service.ClubService;
import vereinsplattform.backend.service.JoinRequestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final JwtUtils jwtUtils;
    private final ClubService clubService;
    private final UserRepository userRepository;
    private final JoinRequestService joinRequestService;
    private final JoinRequestRepository joinRequestRepository;

    public ClubController(JwtUtils jwtUtils,
                          ClubService clubService,
                          UserRepository userRepository,
                          JoinRequestService joinRequestService,
                          JoinRequestRepository joinRequestRepository) {
        this.jwtUtils = jwtUtils;
        this.clubService = clubService;
        this.userRepository = userRepository;
        this.joinRequestService = joinRequestService;
        this.joinRequestRepository = joinRequestRepository;
    }

    // Get all clubs
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Club> findAllClubs (){
        return clubService.getClubs();
    }

    // Get club from the requesting user
    @GetMapping("users")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Club> findClub (HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Club club = clubService.getClub(token.substring(7));
        return ResponseEntity.ok().body(club);
    }

    // Create new club
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Club createClub (@RequestBody Club club){
      return clubService.saveClub(club);
    }

    // Update existing club
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Club> updateClub(@RequestBody Club club) {
        Club updatedClub = clubService.updateClub(club);
        return ResponseEntity.ok().body(updatedClub);
    }

    // Delete existing club
    @DeleteMapping("{clubid}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteClub(@PathVariable Long clubid){
        clubService.deleteClub(clubid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // Request to join a club
    @PutMapping("users/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> joinClubRequest(@PathVariable Long clubid, HttpServletRequest header) {
        String token = header.getHeader("Authorization");
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token.substring(7)))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        //User can only make one request at a time
        JoinRequest exists = joinRequestRepository.findByUserIdAndClubIdAndAccepted(user.getId(), clubid, false);

        if (exists != null) {
            return ResponseEntity.ok().body(null);
        }

        JoinRequest request = new JoinRequest(user.getId(), clubid);
        JoinRequest requestCheck = joinRequestService.saveJoinRequest(request);
        if (requestCheck == null) return null;
        return ResponseEntity.ok().body(request.getId());
    }

    // Leave a club
    @DeleteMapping("users/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, Boolean> leaveClub(@PathVariable Long clubid, HttpServletRequest header) {
        String token = header.getHeader("Authorization");
        clubService.leaveClub(clubid, token.substring(7));

        Map<String, Boolean> response = new HashMap<>();
        response.put("left", Boolean.TRUE);
        return response;
    }

    // Get all Requests of a Club
    @GetMapping("{clubid}/requests")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<JoinRequest> findAllRequests (@PathVariable Long clubid) {
        return joinRequestService.getJoinRequests(clubid);
    }

    // Get all active Requests of a Club
    @GetMapping("{clubid}/activerequests")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<JoinRequest> findAllActiveRequests (@PathVariable Long clubid) {
        return joinRequestRepository.findAllByClubIdAndAccepted(clubid, false);
    }

    // Get active Request of User
    @GetMapping("users/requests")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public JoinRequest findActiveRequests (HttpServletRequest header) {
        String token = header.getHeader("Authorization");
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token.substring(7)))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return joinRequestRepository.findByUserIdAndAccepted(user.getId(), false);
    }

    // Accept a JoinRequest
    @PutMapping("requests/{requestid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> acceptRequest(@PathVariable Long requestid) {
        JoinRequest request = joinRequestRepository.getOne(requestid);
        Club joinedClub = clubService.joinClub(request.getClubId(), request.getUserId());
        if (joinedClub == null) return null;
        request.setAccepted(true);
        request.setEditedAt(new Timestamp(System.currentTimeMillis()));
        joinRequestService.updateJoinRequest(request);
        return ResponseEntity.ok().body(true);
    }

    // Delete active JoinRequest
    @DeleteMapping("requests")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, Boolean> deleteJoinRequest(HttpServletRequest header){
        String token = header.getHeader("Authorization");
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token.substring(7)))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        JoinRequest request = joinRequestRepository.findByUserIdAndAccepted(user.getId(), false);
        joinRequestService.deleteJoinRequest(request);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
