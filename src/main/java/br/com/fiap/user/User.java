package br.com.fiap.user;

import jakarta.persistence.*;
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

    private String name;

    @Column(unique = true)
    private String email;

    private String avatarUrl;

    public User(OAuth2User principal) {
        this.name = principal.getAttributes().get("name").toString();
        this.email = principal.getAttributes().get("email").toString();
        this.avatarUrl = principal.getAttributes().get("avatar_url").toString();
    }
}
