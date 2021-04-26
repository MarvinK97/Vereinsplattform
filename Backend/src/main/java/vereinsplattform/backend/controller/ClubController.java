package vereinsplattform.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.service.ClubService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
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

    // Join a club
    @PutMapping("users/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> joinClub(@PathVariable Long clubid, HttpServletRequest header) {
        String token = header.getHeader("Authorization");
        Club joinedClub = clubService.joinClub(clubid, token.substring(7));
        return ResponseEntity.ok().body(joinedClub.getId());
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

}
