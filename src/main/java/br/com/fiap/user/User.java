package br.com.fiap.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Entity
@Data
@Table(name = "doauser")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.name.notblank}")
    @Size(min = 2, max = 100, message = "{user.name.size}")
    private String name;

    @NotBlank(message = "{user.email.notblank}")
    @Email(message = "{user.email.invalid}")
    @Column(unique = true)
    private String email;

    @Size(max = 500, message = "{user.avatarurl.size}")
    private String avatarUrl;

    public User(OAuth2User principal) {
        this.name = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.avatarUrl = principal.getAttribute("avatar_url");
    }
}