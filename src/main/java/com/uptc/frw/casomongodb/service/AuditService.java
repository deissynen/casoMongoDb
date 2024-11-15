package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Audit;
import com.uptc.frw.casomongodb.jpa.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AuditService {
    @Autowired
    private AuditRepository auditRepository;

    public void registrarAuditoria(String table, String operation, Map<String, Object> oldData, Map<String, Object> newData, String idRelationalTable) {
        Audit audit = new Audit();
        audit.setTable(table);
        audit.setOperation(operation);
        audit.setOldData(oldData);
        audit.setNewData(newData);
        audit.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        audit.setIdRelationalTable(idRelationalTable);

        auditRepository.save(audit);
    }
}