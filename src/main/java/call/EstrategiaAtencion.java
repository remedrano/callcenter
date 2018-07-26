/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * Estrategias de atencion para la asignacion de llamadas
 * @author Rafael Medrano
 */
public class EstrategiaAtencion {

    private Logger logger = Logger.getLogger(getClass().getName());

    public EstrategiaAtencion() {
    }

    /**
     * Metodo que busca sobre el listado de empleados, los que se encuentran disponibles
     * La prioridad de busqueda son los operadores, supervisores y luego los directores
     * @param empleados  Empleados actuales registrados
     * @return Empleado
     */
    public Empleado buscarEmpleado(Collection<Empleado> empleados) {

        List<Empleado> empleadosDisponibles = empleados.stream().filter(e -> e.getDisponible() == true).collect(Collectors.toList());
        logger.info("Operadores disponibles: " + empleadosDisponibles.size());

        Optional<Empleado> empleado = empleadosDisponibles.stream().filter(e -> e.getTipo() == 1).findAny();
        if (!empleado.isPresent()) {
            logger.info("No se encuentran operadores disponibles");
            empleado = empleadosDisponibles.stream().filter(e -> e.getTipo() == 2).findAny();
            if (!empleado.isPresent()) {
                logger.info("No se encuentran supervisores disponibles");
                empleado = empleadosDisponibles.stream().filter(e -> e.getTipo() == 3).findAny();
                if (!empleado.isPresent()) {
                    logger.info("No se encuentran directores disponibles ");
                    return null;
                }
            }
        }
        logger.info("Empleado de tipo " + empleado.get().getNombreTipo() + " encontrado ! ");
        return empleado.get();
    }
}
