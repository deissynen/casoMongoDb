package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.*;

@Entity
@Table (name = "EQUIPOS_PATROCINADORES")
public class TeamSponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamSponsor_seq")
    @SequenceGenerator(name = "teamSponsor_seq", sequenceName = "EQUIPOS_PATROCINADORES_SEQ", allocationSize = 1)
    @Column (name = "ID_EQUIPO_PATROCINADOR")
    private long idTeamSponsor;

    @Column (name = "ID_EQUIPO", insertable = false, updatable = false)
    private long idTeam;

    @Column (name = "ID_PATROCINADOR", insertable = false, updatable = false)
    private long idSponsor;

    @Column (name = "ID_EDICION", insertable = false, updatable = false)
    private long idEdition;

    /*Mapeo Relación De Muchos a Muchos con la Tabla Sponsor*/
    @ManyToOne
    @JoinColumn (name = "ID_PATROCINADOR", referencedColumnName = "ID_PATROCINADOR", nullable = false)
    private Sponsor sponsor;

    /*Mapeo Relación De Muchos a Muchos con la Tabla Team*/
    @ManyToOne
    @JoinColumn (name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO", nullable = false)
    private Team team;

    /*Mapeo Relación De Muchos a Muchos con la Tabla Edition*/
    @ManyToOne
    @JoinColumn (name = "ID_EDICION", referencedColumnName = "ID_EDICION", nullable = false)
    private Edition edition;

    public TeamSponsor() {
    }

    public long getIdTeamSponsor() {
        return idTeamSponsor;
    }

    public void setIdTeamSponsor(long idTeamSponsor) {
        this.idTeamSponsor = idTeamSponsor;
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }

    public long getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(long idSponsor) {
        this.idSponsor = idSponsor;
    }

    public long getIdEdition() {
        return idEdition;
    }

    public void setIdEdition(long idEdition) {
        this.idEdition = idEdition;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "TeamSponsor{" +
                "idTeamSponsor=" + idTeamSponsor +
                ", idTeam=" + idTeam +
                ", idSponsor=" + idSponsor +
                ", idEdition=" + idEdition +
                '}';
    }
}
