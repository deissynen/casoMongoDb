package com.uptc.frw.casomongodb.aspect;

import com.uptc.frw.casomongodb.jpa.models.Audit;
import com.uptc.frw.casomongodb.jpa.models.Team;
import com.uptc.frw.casomongodb.jpa.repository.TeamRepository;
import com.uptc.frw.casomongodb.service.AuditService;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Aspect
@Component
public class AuditAspectTeam {

    @Autowired
    private AuditService auditService;

    private ThreadLocal<Map<String, Object>> datosAntes = new ThreadLocal<>();

    @Autowired
    private TeamRepository teamRepository;

    // Antes de guardar una entidad (insertar o actualizar)
    @Before("execution(* com.uptc.frw.casomongodb.jpa.repository.*.save*(..))")
    public void auditarAntesDeSave(JoinPoint joinPoint) {
        Object entidad = joinPoint.getArgs()[0]; // Obtener la entidad que se va a insertar/actualizar
        if (entidad instanceof Audit) {
            return; // Evita la recursión si la entidad es de tipo Audit
        }

        Optional<Team> entidadExistente = Optional.empty();

        if (entidad instanceof Team) {
            Team entidadObjeto = (Team) entidad;
            Long id = entidadObjeto.getIdTeam();  // Suponiendo que getIdTeam() devuelve un Long
            if (id != null && id > 0) {
                entidadExistente = teamRepository.findById(id);  // Consulta el registro existente en la base de datos
            }
        }

        // Capturar los datos antes de la operación (si existe)
        Map<String, Object> dataBefore = obtenerDatosAntes(entidadExistente.orElse(null));
        // Almacenar los datos antes de la operación usando ThreadLocal
        datosAntes.set(dataBefore);
    }

    // Después de guardar una entidad (insertar o actualizar)
    @AfterReturning(value = "execution(* com.uptc.frw.casomongodb.jpa.repository.*.save*(..))", returning = "resultado")
    public void auditarDespuesDeSave(JoinPoint joinPoint, Object resultado) {
        Object entidadA = joinPoint.getArgs()[0]; // La entidad que se insertó/actualizó
        if (entidadA instanceof Audit) {
            return; // Evita la recursión si la entidad es de tipo Audit
        }

        String tableName = entidadA.getClass().getSimpleName();  // Nombre de la clase (tabla)

        // Recuperar los datos antes de la operación
        Map<String, Object> dataBefore = datosAntes.get();
        // Los datos después de la operación (entidad ya modificada)
        Map<String, Object> dataAfter = obtenerDatosDespues(resultado);

        // Determinar si es un Insert o un Update
        Object idBefore = dataBefore.get("idTeam");
        Object idAfter = dataAfter.get("idTeam");

        String operation = (idBefore == null || idBefore.equals(0L)) ? "INSERT" : "UPDATE";

        // Realizar la auditoría del cambio
        auditService.registrarAuditoria(
                tableName,
                operation,
                dataBefore,   // Datos antes del cambio
                dataAfter,    // Datos después del cambio
                "idTeam"      // Identificador único de la entidad
        );

        // Limpiar los datos de ThreadLocal después de usarlo
        datosAntes.remove();
    }

    // Después de eliminar una entidad
    @Before("execution(* com.uptc.frw.casomongodb.jpa.repository.TeamRepository.delete*(..))")
    public void auditarEliminacion(JoinPoint joinPoint) {
        // Obtener el primer argumento, que en este caso debería ser el ID o la entidad
        Object argumento = joinPoint.getArgs()[0];

        // Suponiendo que el argumento es un ID de tipo Long o String (si es una entidad completa, necesitarás ajustarlo)
        String idEntidad = null;
        if (argumento instanceof String) {
            idEntidad = (String) argumento; // Si es un ID de tipo String
        } else if (argumento instanceof Long) {
            idEntidad = String.valueOf(argumento); // Si es un ID de tipo Long
        }

        if (idEntidad != null) {
            // Realizar la consulta para obtener la entidad desde el repositorio antes de eliminarla
            Optional<Team> entidadOptional = teamRepository.findById(Long.valueOf(idEntidad));  // Busca por el ID de la entidad.

            if (entidadOptional.isPresent()) {
                Team entidad = entidadOptional.get();

                // Crear el mapa con los datos de la entidad para la auditoría
                Map<String, Object> data = new HashMap<>();
                data.put("id", entidad.getIdTeam());
                data.put("nombre", entidad.getNameTeam());
                data.put("otrosDatos", entidad.getCreationDateTeam()); // Agrega los campos relevantes

                // Llamada al servicio de auditoría
                auditService.registrarAuditoria(
                        "Team",              // Nombre de la tabla o entidad
                        "DELETE",            // Acción realizada
                        data,                // Los datos de la entidad
                        null,                // No hay datos nuevos, ya que es eliminación
                        idEntidad            // ID de la entidad eliminada
                );
            } else {
                // Si no se encuentra la entidad, puedes manejarlo como un error o loguearlo.
                System.out.println("Entidad con ID " + idEntidad + " no encontrada antes de la eliminación.");
            }
        }
    }

    // Método para obtener los datos antes de la operación (antes de insertar o actualizar)
    private Map<String, Object> obtenerDatosAntes(Team entidadExistente) {
        Map<String, Object> datos = new HashMap<>();
        if (entidadExistente != null) {
            // Usar los métodos getter de la entidad para obtener los valores
            datos.put("idTeam", entidadExistente.getIdTeam());
            datos.put("nameTeam", entidadExistente.getNameTeam());
            datos.put("creationDateTeam", entidadExistente.getCreationDateTeam());
        }
        return datos;
    }

    // Método para obtener los datos después de la operación (después de insertar o actualizar)
    private Map<String, Object> obtenerDatosDespues(Object entidad) {
        Map<String, Object> datos = new HashMap<>();

        if (entidad instanceof Team) {
            Team team = (Team) entidad;
            datos.put("idTeam", team.getIdTeam());
            datos.put("nameTeam", team.getNameTeam());
            datos.put("creationDateTeam", team.getCreationDateTeam());
        }

        return datos;
    }
}
