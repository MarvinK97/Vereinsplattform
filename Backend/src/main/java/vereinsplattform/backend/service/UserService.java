package vereinsplattform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vereinsplattform.backend.entity.User;

import vereinsplattform.backend.entity.UserClub;
import vereinsplattform.backend.repository.UserClubRepository;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.security.jwt.JwtUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserClubRepository userClubRepository;

    @Autowired
    JwtUtils jwtUtils;

    public List<UserRepository.AdminUserView> getUsers() {
        return userRepository.findBy();
    }

    public boolean inClub(String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserClub userClub = userClubRepository.findByUserId(user.getId());

        if (userClub != null) {
            return true;
        } else {
            return false;
        }


    }

}
