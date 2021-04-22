package vereinsplattform.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Get all users of platform
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRepository.AdminUserView> findAllUsers() {
        return userService.getUsers();
    }

    // Check if user is in a club
    @GetMapping("inclub")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public boolean inClub(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return userService.inClub(token.substring(7));
    }

}
