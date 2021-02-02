package vereinsplattform.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.payload.request.JoinClubRequest;
import vereinsplattform.backend.service.ClubService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    // Get all clubs
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Club> findAllClubs (){
        return clubService.getClubs();
    }

    // Create new club
    @PostMapping("createClub")
    @PreAuthorize("hasRole('ADMIN')")
    public Club createClub (@RequestBody Club club){
      return clubService.saveClub(club);
    }

    // Update existing club
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Club updateClub(@RequestBody Club club) {
        return clubService.updateClub(club);
    }

    // Delete existing club
    @PutMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteClub(@RequestBody Club club){
        clubService.deleteClub(club);
    }

    // Join a club
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void joinClub(@RequestBody JoinClubRequest request) {
        clubService.joinClub(request);
    }

    // Leave a club
    @PostMapping("/leave")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void leaveClub(@RequestBody JoinClubRequest request) {
        clubService.leaveClub(request);
    }

}
