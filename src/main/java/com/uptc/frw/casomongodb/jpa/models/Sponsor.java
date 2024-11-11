package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "PATROCINADORES")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sponsor_seq")
    @SequenceGenerator(name = "sponsor_seq", sequenceName = "PATROCINADORES_SEQ", allocationSize = 1)
    @Column (name = "ID_PATROCINADOR")
    private long idSponsor;
    @Column (name = "NIT")
    private long nitSponsor;
    @Column (name = "NOMBRE_PATROCINADOR")
    private String nameSponsor;

    /*Mapeo Relación De Uno a Muchos con la Tabla TeamSponsor*/
    @OneToMany (mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeamSponsor> teamSponsors;


    public Sponsor() {
    }

    public long getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(long idSponsor) {
        this.idSponsor = idSponsor;
    }

    public long getNitSponsor() {
        return nitSponsor;
    }

    public void setNitSponsor(long nitSponsor) {
        this.nitSponsor = nitSponsor;
    }

    public String getNameSponsor() {
        return nameSponsor;
    }

    public void setNameSponsor(String nameSponsor) {
        this.nameSponsor = nameSponsor;
    }

    public List<TeamSponsor> getTeamSponsors() {
        return teamSponsors;
    }

    public void setTeamSponsors(List<TeamSponsor> teamSponsors) {
        this.teamSponsors = teamSponsors;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "idSponsor=" + idSponsor +
                ", nitSponsor=" + nitSponsor +
                ", nameSponsor='" + nameSponsor + '\'' +
                '}';
    }
}
