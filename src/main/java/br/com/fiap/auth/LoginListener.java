package br.com.fiap.auth;

import br.com.fiap.user.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;

    public LoginListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        var principal = (OAuth2User) event.getAuthentication().getPrincipal();
        userService.register(principal);
    }
}
