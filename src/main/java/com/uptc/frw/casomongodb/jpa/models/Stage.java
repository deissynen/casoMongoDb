package com.uptc.frw.casomongodb.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "ETAPAS")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stage_seq")
    @SequenceGenerator(name = "stage_seq", sequenceName = "ETAPAS_SEQ", allocationSize = 1)
    @Column (name = "ID_ETAPA")
    private long idStage;
    @Column (name = "ID_EDICION", insertable = false, updatable = false)
    private long idEdition;
    @Column (name = "CONSECUTIVO")
    private int consecutive;
    @Column (name = "ORIGEN")
    private String origin;
    @Column (name = "DESTINO")
    private String destination;
    @Column (name = "LONGITUD")
    private int longitude;
    @Column (name = "TIPO")
    private String type;

    /*Mapeo Relación Uno a Muchos con la Tabla Podium [Podio]*/
    @OneToMany (mappedBy = "stage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Podium> podiums;

    /*Mapeo Relación Muchos a uno con la Tabla Edition [Edicion]*/
    @ManyToOne
    @JoinColumn (name = "ID_EDICION", referencedColumnName = "ID_EDICION")
    private Edition editionS;


    public Stage() {
    }

    public long getIdStage() {
        return idStage;
    }

    public void setIdStage(long idStage) {
        this.idStage = idStage;
    }

    public long getIdEdition() {
        return idEdition;
    }

    public void setIdEdition(long idEdition) {
        this.idEdition = idEdition;
    }

    public int getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(int consecutive) {
        this.consecutive = consecutive;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Podium> getPodiums() {
        return podiums;
    }

    public void setPodiums(List<Podium> podiums) {
        this.podiums = podiums;
    }

    public Edition getEditionS() {
        return editionS;
    }

    public void setEditionS(Edition editionS) {
        this.editionS = editionS;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "idStage=" + idStage +
                ", idEdition=" + idEdition +
                ", consecutive=" + consecutive +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", longitude=" + longitude +
                ", type='" + type + '\'' +
                '}';
    }
}
