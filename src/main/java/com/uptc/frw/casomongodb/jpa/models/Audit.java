package com.uptc.frw.casomongodb.jpa.models;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "audit")
public class Audit {
    @Id
    private String id;
    private String table;
    private String operation;
    private Map<String, Object> oldData; // Para los cambios de tipo UPDATE o DELETE
    private Map<String, Object> newData; // Para los cambios de tipo INSERT o UPDATE
    private String date;
    private String idRelationalTable;

    public Audit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Map<String, Object> getOldData() {
        return oldData;
    }

    public void setOldData(Map<String, Object> oldData) {
        this.oldData = oldData;
    }

    public Map<String, Object> getNewData() {
        return newData;
    }

    public void setNewData(Map<String, Object> newData) {
        this.newData = newData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdRelationalTable() {
        return idRelationalTable;
    }

    public void setIdRelationalTable(String idRelationalTable) {
        this.idRelationalTable = idRelationalTable;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id='" + id + '\'' +
                ", table='" + table + '\'' +
                ", operation='" + operation + '\'' +
                ", oldData=" + oldData +
                ", newData=" + newData +
                ", date='" + date + '\'' +
                ", idRelationalTable='" + idRelationalTable + '\'' +
                '}';
    }
}
