package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.User;
import vereinsplattform.backend.repository.UserRepository;

import java.util.List;

public interface UserService {

    default List<UserRepository.AdminUserView> getUsers() {
        return null;
    }

    default Long inClub(String jwt) {
        return null;
    }
    default Long inClub(User user) {
        return null;
    }
}
