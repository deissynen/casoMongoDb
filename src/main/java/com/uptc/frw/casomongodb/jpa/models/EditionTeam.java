package com.uptc.frw.casomongodb.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table (name = "EDICIONES_EQUIPOS")
public class EditionTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editionTeam_seq")
    @SequenceGenerator(name = "editionTeam_seq", sequenceName = "EDICIONES_EQUIPOS_SEQ", allocationSize = 1)
    @Column (name = "ID_EDICION_EQUIPO")
    private long idEditionTeam;
    @Column (name = "ID_EDICION", insertable = false, updatable = false)
    private long idEdition;
    @Column (name = "ID_EQUIPO", insertable = false, updatable = false)
    private long idTeam;
    @Column (name = "ID_CORREDOR", insertable = false, updatable = false)
    private long idRunner;

    /*Mapeo Relación De Muchos a uno con la Tabla Edition [Edicion] */
    @ManyToOne
    @JoinColumn (name = "ID_EDICION", referencedColumnName = "ID_EDICION")
    private Edition edition;


    /*Mapeo Relación De Muchos a uno con la Tabla Team [Equipo] */
    @ManyToOne
    @JoinColumn (name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    private Team team;

    /*Mapeo Relación De Muchos a uno con la Tabla Tunner [Corredor] */
    @ManyToOne
    @JoinColumn (name = "ID_CORREDOR", referencedColumnName = "ID_CORREDOR")
    private Runner runner;

    public EditionTeam() {
    }

    public long getIdEditionTeam() {
        return idEditionTeam;
    }

    public void setIdEditionTeam(long idEditionTeam) {
        this.idEditionTeam = idEditionTeam;
    }

    public long getIdEdition() {
        return idEdition;
    }

    public void setIdEdition(long idEdition) {
        this.idEdition = idEdition;
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }

    public long getIdRunner() {
        return idRunner;
    }

    public void setIdRunner(long idRunner) {
        this.idRunner = idRunner;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }


    @Override
    public String toString() {
        return "EditionTeam{" +
                "idEditionTeam=" + idEditionTeam +
                ", idEdition=" + idEdition +
                ", idTeam=" + idTeam +
                ", idRunner=" + idRunner +
                '}';
    }
}
