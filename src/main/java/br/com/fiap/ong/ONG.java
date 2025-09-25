package br.com.fiap.ong;

import br.com.fiap.campanha.Campanha;
import br.com.fiap.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ONG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{ong.nome.notblank}")
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    @Email
    private String email;

    private String telefone;
    private String descricao;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "ong")
    private List<Campanha> campanhas;
}