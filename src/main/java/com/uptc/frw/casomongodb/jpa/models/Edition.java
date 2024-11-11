package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "EDICIONES")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edition_seq")
    @SequenceGenerator(name = "edition_seq", sequenceName = "EDICIONES_SEQ", allocationSize = 1)
    @Column (name ="ID_EDICION")
    private long idEdition;
    @Column (name = "ANIO")
    private int yearEdition;
    @Column (name = "FECHA_INICIO")
    private Date dateStartEdition;
    @Column (name ="FECHA_FIN")
    private Date dateEndEdition;

    /*Mapeo Relación De Uno a Muchos con la Tabla EditionTeams [Edicion Equipo] */
    @OneToMany (mappedBy = "edition", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EditionTeam> editionTeams;

    /*Mapeo Relación De Uno a Muchos con la Tabla Stage [Etapa] */
    @OneToMany(mappedBy = "editionS", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stage> stages;

    public Edition() {
    }

    public long getIdEdition() {
        return idEdition;
    }

    public void setIdEdition(long idEdition) {
        this.idEdition = idEdition;
    }

    public int getYearEdition() {
        return yearEdition;
    }

    public void setYearEdition(int yearEdition) {
        this.yearEdition = yearEdition;
    }

    public Date getDateStartEdition() {
        return dateStartEdition;
    }

    public void setDateStartEdition(Date dateStartEdition) {
        this.dateStartEdition = dateStartEdition;
    }

    public Date getDateEndEdition() {
        return dateEndEdition;
    }

    public void setDateEndEdition(Date dateEndEdition) {
        this.dateEndEdition = dateEndEdition;
    }

    public List<EditionTeam> getEditionTeams() {
        return editionTeams;
    }

    public void setEditionTeams(List<EditionTeam> editionTeams) {
        this.editionTeams = editionTeams;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }


    @Override
    public String toString() {
        return "Editions{" +
                "idEdition=" + idEdition +
                ", yearEdition=" + yearEdition +
                ", dateStartEdition=" + dateStartEdition +
                ", dateEndEdition=" + dateEndEdition +
                '}';
    }
}
