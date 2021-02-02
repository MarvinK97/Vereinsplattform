package vereinsplattform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vereinsplattform.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserRepository.AdminUserView> getUsers() {
        return repository.findBy();
    }

}
