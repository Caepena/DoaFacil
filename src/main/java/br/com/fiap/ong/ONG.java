package br.com.fiap.ong;

import br.com.fiap.campanha.Campanha;
import br.com.fiap.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 100, message = "{ong.nome.size}")
    private String nome;

    @NotBlank(message = "{ong.cnpj.notblank}")
    @Size(min = 14, max = 18, message = "{ong.cnpj.size}")
    @Column(unique = true)
    private String cnpj;

    @NotBlank(message = "{ong.email.notblank}")
    @Email(message = "{ong.email.invalid}")
    @Column(unique = true)
    private String email;

    @Size(min = 10, max = 20, message = "{ong.telefone.size}")
    private String telefone;

    @Size(min = 10, max = 1000, message = "{ong.descricao.size}")
    private String descricao;

    @ManyToOne
    @NotNull(message = "{ong.user.notnull}")
    private User user;

    @OneToMany(mappedBy = "ong")
    private List<Campanha> campanhas;
}