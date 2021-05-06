package vereinsplattform.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.dto.request.UpdateUser;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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
        Long clubId = userService.inClub(token.substring(7));

        if (clubId != null) {
            return ResponseEntity.ok().body(clubId);
        } else {
            return ResponseEntity.ok().body("");
        }
    }

    // Update existing user
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser user) {
        System.out.println(user.getUsername());
        User userToModify = userRepository.getOne(user.getId());
        userToModify.setEmail(user.getEmail());
        userToModify.setUsername(user.getUsername());
        User updatedUser = userService.updateUser(userToModify);
        return ResponseEntity.ok().body(updatedUser);
    }

    // Delete existing club
    @DeleteMapping("{userid}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
