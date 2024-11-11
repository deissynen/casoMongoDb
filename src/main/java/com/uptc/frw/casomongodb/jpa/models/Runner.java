package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name ="CORREDORES")
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "runner_seq")
    @SequenceGenerator(name = "runner_seq", sequenceName = "CORREDORES_SEQ", allocationSize = 1)
    @Column (name="ID_CORREDOR")
    private long idRunner;
    @Column (name="NOMBRE_CORREDOR")
    private String nameRunner;
    @Column (name ="PAIS_NACIMIENTO")
    private String countryRunner;
    @Column (name ="FECHA_NACIMIENTO")
    private Date birthdayRunner;

    /*Mapeo Relación De Uno a Muchos con la Tabla Podium [Podio]*/
    @OneToMany (mappedBy = "runner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Podium> podiums;

    public Runner() {
    }

    public long getIdRunner() {
        return idRunner;
    }

    public void setIdRunner(long idRunner) {
        this.idRunner = idRunner;
    }

    public String getNameRunner() {
        return nameRunner;
    }

    public void setNameRunner(String nameRunner) {
        this.nameRunner = nameRunner;
    }

    public String getCountryRunner() {
        return countryRunner;
    }

    public void setCountryRunner(String countryRunner) {
        this.countryRunner = countryRunner;
    }

    public Date getBirthdayRunner() {
        return birthdayRunner;
    }

    public void setBirthdayRunner(Date birthdayRunner) {
        this.birthdayRunner = birthdayRunner;
    }

    public List<Podium> getPodiums() {
        return podiums;
    }

    public void setPodiums(List<Podium> podiums) {
        this.podiums = podiums;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "idRunner=" + idRunner +
                ", nameRunner='" + nameRunner + '\'' +
                ", countryRunner='" + countryRunner + '\'' +
                ", birthdayRunner=" + birthdayRunner +
                '}';
    }
}
