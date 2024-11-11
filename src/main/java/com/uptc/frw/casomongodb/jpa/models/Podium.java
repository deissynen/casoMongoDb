package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.*;

@Entity
@Table (name="PODIOS")
public class Podium {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "podium_seq")
    @SequenceGenerator(name = "podium_seq", sequenceName = "PODIOS_SEQ", allocationSize = 1)
    @Column (name ="ID_PODIO")
    private long idPodium;
    @Column (name ="ID_ETAPA", insertable=false, updatable=false)
    private long idStage;
    @Column (name ="ID_CORREDOR", insertable=false, updatable=false)
    private long idRunner;
    @Column (name ="LUGAR_PODIO")
    private int podiumPlace;
    @Column (name ="TIEMPO")
    private int time;

    /*Mapeo Relación Muchos a Uno con la Tabla Runner [Corredor]*/
    @ManyToOne
    @JoinColumn (name = "ID_CORREDOR", referencedColumnName = "ID_CORREDOR")
    private Runner runner;

    /*Mapeo Relación Muchos a Uno con la Tabla Stage [Etapa]*/
    @ManyToOne
    @JoinColumn (name = "ID_ETAPA", referencedColumnName = "ID_ETAPA")
    private Stage stage;

    public Podium() {
    }

    public long getIdPodium() {
        return idPodium;
    }

    public void setIdPodium(long idPodium) {
        this.idPodium = idPodium;
    }

    public long getIdStage() {
        return idStage;
    }

    public void setIdStage(long idStage) {
        this.idStage = idStage;
    }

    public long getIdRunner() {
        return idRunner;
    }

    public void setIdRunner(long idRunner) {
        this.idRunner = idRunner;
    }

    public int getPodiumPlace() {
        return podiumPlace;
    }

    public void setPodiumPlace(int podiumPlace) {
        this.podiumPlace = podiumPlace;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Podium{" +
                "idPodium=" + idPodium +
                ", idStage=" + idStage +
                ", idRunner=" + idRunner +
                ", podiumPlace=" + podiumPlace +
                ", time=" + time +
                '}';
    }
}
