package br.edu.iff.ccc.mindly.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class DisponibilidadeDTO {

    private Long id;

    @NotNull(message = "O início é obrigatório")
    private LocalDateTime inicio;

    @NotNull(message = "O fim é obrigatório")
    private LocalDateTime fim;

    @NotBlank(message = "O tipo de profissional é obrigatório")
    private String tipoProfissional;

    private boolean reservado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public String getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(String tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
