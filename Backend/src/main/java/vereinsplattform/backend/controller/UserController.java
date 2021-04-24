package vereinsplattform.backend.controller;

import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vereinsplattform.backend.dto.response.MessageResponse;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Get all users of platform
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRepository.AdminUserView> findAllUsers() {
        return userService.getUsers();
    }

    // Check if user is in a club
    @GetMapping("/clubs")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> checkUserClub(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        boolean inClub = userService.inClub(token.substring(7));

        if (inClub) {
            return ResponseEntity.ok().body(new MessageResponse("User has a club!"));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
