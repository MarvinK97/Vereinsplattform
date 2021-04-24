package vereinsplattform.backend.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.entity.UserClub;
import vereinsplattform.backend.repository.UserClubRepository;
import vereinsplattform.backend.repository.UserRepository;
import vereinsplattform.backend.security.jwt.JwtUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository,
                           UserClubRepository userClubRepository,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userClubRepository = userClubRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public List<UserRepository.AdminUserView> getUsers() {
        return userRepository.findBy();
    }

    @Override
    public boolean inClub(String jwt) {
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserClub userClub = userClubRepository.findByUserId(user.getId());

        return userClub != null;

    }

}
