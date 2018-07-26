/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import call.Llamada;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase empleado
 * @author Rafael Medrano
 */
public class EmpleadoTest {
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public EmpleadoTest() {
    }
       
    /**
     * Prueba para asignar una llamada a un empleado disponible
     * @throws InterruptedException 
     */
    
    @Test
    public void testAtencionEmpleadoDisponible() throws InterruptedException {
        logger.info("Estado empleado disponible para atender");
        Empleado empleado = new Operador("Sara", "Marquez");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute( empleado );
        Llamada llamada = new Llamada();
        empleado.responderLlamada( new Llamada());
        executorService.awaitTermination(3, TimeUnit.SECONDS);       
    }

    /**
     * Prueba para conocer el estado del empleado cuando se encuentra atendiendo una llamada
     * El estado debe ser no disponible (false)
     * @throws InterruptedException 
     */
    @Test
    public void testEstadoEmpleadoRepondeLlamada() throws InterruptedException {
        logger.info("Estado empleado cuando seencuentra no disponible");
        Empleado empleado = new Operador("Leonel", "Messi");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(empleado);        
        empleado.responderLlamada( new Llamada() );        
        Llamada llamada = new Llamada();
        empleado.responderLlamada( llamada );
        TimeUnit.SECONDS.sleep( llamada.getTiempoSegundos() );       
        assertEquals( false , empleado.getDisponible() );
    }    
}
