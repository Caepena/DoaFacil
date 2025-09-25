package br.com.fiap.user;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(OAuth2User principal) {
        var user = userRepository.findByEmail(principal.getAttributes().get("email").toString());
        return user.orElseGet(() -> userRepository.save(new User(principal)));
    }
}
