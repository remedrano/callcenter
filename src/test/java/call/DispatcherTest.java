/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelos.Director;
import modelos.Empleado;
import modelos.Operador;
import modelos.Supervisor;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Pruebas unitarias para el asignador de llamadas (Distpacher)
 * @author Rafael Medrano
 */
public class DispatcherTest {
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public DispatcherTest() {
    }
  
    /**
     * Prueba para Dispatcher sin empleados y sin estrategia de atencion 
     */
    @Test
    public void testDispatchSinEmpleados() {
        logger.info("dispatch sin empleados sin estrategia");        
        Dispatcher dispatcher = new Dispatcher( new ArrayList<>());
        assertEquals( Dispatcher.class , dispatcher.getClass() );   
    }
  
    /**
     * Test para recibir maximo 10 llamadas concurrentes
     * Test of dispatchCall method, of class Dispatcher.
     */
    @Test
    public void testDispatchCallMaximoDiez() {        
                
        List<Empleado> empleados = new ArrayList<Empleado>();        

        empleados.add( new Operador( "Anna", "Marquez" ) );
        empleados.add( new Operador( "Hector", "Salgado" ) );
        empleados.add( new Operador( "Benjamin", "Franklin" ) );
        empleados.add( new Operador( "Jose", "Martinez" ) );
        empleados.add( new Operador( "Rafael", "Torres" ) );
        empleados.add( new Supervisor( "Miguel", "Duran" ) );
        empleados.add( new Supervisor( "Franco", "Armani" ) );
        empleados.add( new Supervisor( "Sandra", "Soledo" ) );
        empleados.add( new Director( "Leonardo", "Medrano" ) );
        empleados.add( new Director( "Sara", "Estupiñan" ) );                

        Dispatcher dispatcher = new Dispatcher( empleados  );
        dispatcher.start();                                   
        for( int i = 0; i < dispatcher.MAX_LLAMADAS ; i ++){ 
            dispatcher.dispatchCall( new Llamada());
        }                
    }
    
    /**
     * Test para recibir maximo 30 llamadas concurrentes. 
     * Cuando no existen empleados por la cantidad de llamadas se 
     * Test of dispatchCall method, of class Dispatcher.
     */
    @Test
    public void testDispatchCallMoreMasDeDiez() {        
                
        List<Empleado> empleados = new ArrayList<Empleado>();        

        empleados.add( new Operador( "Anna", "Marquez" ) );
        empleados.add( new Operador( "Hector", "Salgado" ) );
        empleados.add( new Operador( "Benjamin", "Franklin" ) );
        empleados.add( new Operador( "Jose", "Martinez" ) );
        empleados.add( new Operador( "Rafael", "Torres" ) );
        empleados.add( new Supervisor( "Miguel", "Duran" ) );
        empleados.add( new Supervisor( "Franco", "Armani" ) );
        empleados.add( new Supervisor( "Sandra", "Soledo" ) );
        empleados.add( new Director( "Leonardo", "Medrano" ) );
        empleados.add( new Director( "Sara", "Estupiñan" ) );                

        Dispatcher dispatcher = new Dispatcher( empleados );
        dispatcher.start();                                   
        for( int i = 0; i < 15 ; i ++){ 
            dispatcher.dispatchCall( new Llamada());
        }                
    }
        
}
