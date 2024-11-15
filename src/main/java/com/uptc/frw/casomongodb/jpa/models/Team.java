package com.uptc.frw.casomongodb.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table (name = "EQUIPOS")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", sequenceName = "EQUIPOS_SEQ", allocationSize = 1)
    @Column(name="ID_EQUIPO")
    private long idTeam;

    @Column (name="NOMBRE_EQUIPO")
    private String nameTeam;

    @Column (name ="FECHA_FUNDACION")
    private Date creationDateTeam;

    /* Relación con la Tabla TeamSponsor - Equipos Patrocinadores (Uno a Muchos)*/
    @OneToMany (mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeamSponsor> teamSponsors;

    /*Mapeo Relación De Uno a Muchos con la Tabla EditionTeams [Edicion Equipo] */
    @OneToMany (mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EditionTeam> editionTeam;


    public Team() {
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public Date getCreationDateTeam() {
        return creationDateTeam;
    }

    public void setCreationDateTeam(Date creationDateTeam) {
        this.creationDateTeam = creationDateTeam;
    }

    public List<TeamSponsor> getTeamSponsors() {
        return teamSponsors;
    }

    public void setTeamSponsors(List<TeamSponsor> teamSponsors) {
        this.teamSponsors = teamSponsors;
    }

    public List<EditionTeam> getEditionTeam() {
        return editionTeam;
    }

    public void setEditionTeam(List<EditionTeam> editionTeam) {
        this.editionTeam = editionTeam;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idTeam=" + idTeam +
                ", nameTeam='" + nameTeam + '\'' +
                ", creationDateTeam=" + creationDateTeam +
                '}';
    }
}
