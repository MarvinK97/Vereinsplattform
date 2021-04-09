package vereinsplattform.backend.service;

import vereinsplattform.backend.repository.UserRepository;
import java.util.List;

public interface UserService {

    default List<UserRepository.AdminUserView> getUsers() {
        return null;
    }

    default boolean inClub(String jwt) {
        return false;
    }

}
