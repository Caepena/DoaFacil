package br.com.fiap.campanha;

import br.com.fiap.ong.ONG;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{campaign.titulo.notblank}")
    @Size(min = 5, max = 100, message = "{campaign.titulo.size}")
    private String titulo;

    @NotBlank(message = "{campaign.descricao.notblank}")
    @Size(min = 20, max = 1000, message = "{campaign.descricao.size}")
    private String descricao;

    @NotNull(message = "{campaign.metavalor.notnull}")
    @Min(value = 1, message = "{campaign.metavalor.min}")
    @Max(value = 10000000, message = "{campaign.metavalor.max}")
    private Integer metaValor;

    @NotNull(message = "{campaign.valorarrecadado.notnull}")
    @Min(value = 0, message = "{campaign.valorarrecadado.min}")
    @Max(value = 10000000, message = "{campaign.valorarrecadado.max}")
    private Integer valorArrecadado;

    @NotNull(message = "{campaign.datainicio.notnull}")
    @FutureOrPresent(message = "{campaign.datainicio.future}")
    private LocalDate dataInicio;

    @NotNull(message = "{campaign.datafim.notnull}")
    @Future(message = "{campaign.datafim.future}")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{campaign.status.notnull}")
    private statusCampanha status;

    @ManyToOne
    @NotNull(message = "{campaign.ong.notnull}")
    private ONG ong;
}