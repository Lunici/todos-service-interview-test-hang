package cat.ohmycode.pruebatecnicahangxing.service;

import cat.ohmycode.pruebatecnicahangxing.entity.UserEntity;
import cat.ohmycode.pruebatecnicahangxing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
