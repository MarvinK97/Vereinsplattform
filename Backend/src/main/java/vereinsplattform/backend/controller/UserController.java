package vereinsplattform.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.payload.request.JoinClubRequest;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    //Get all users of platform
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRepository.AdminUserView> findAllUsers() {
        return service.getUsers();
    }

}
