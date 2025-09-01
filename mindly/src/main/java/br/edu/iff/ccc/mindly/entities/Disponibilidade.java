package br.edu.iff.ccc.mindly.entities;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.*;

    @Entity
    @Table(name = "disponibilidades")
    public class Disponibilidade {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(optional = false)
   private Usuario profissional;

   @Column(nullable = false)
   private DayOfWeek diaSemana;

   @Column(nullable = false)
   private LocalTime horaInicio;

   @Column(nullable = false)
   private LocalTime horaFim;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}
