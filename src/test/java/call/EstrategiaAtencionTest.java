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
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias para la estrategia de atencion
 * @author Rafael Medrano
 */
public class EstrategiaAtencionTest {
    
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public EstrategiaAtencionTest() {
    }
               
    /**
     * Prueba para buscar un operador disponible
     */
    @Test
    public void testBuscarOperador() {
        logger.info("buscarEmpleado tipo operador");
        List<Empleado> empleados = new ArrayList<Empleado>();
        
        Empleado ana = new Operador( "Anna", "Marquez" );                
        Empleado franco = new Supervisor( "Franco", "Armani" );       
        Empleado sara = new Director( "Sara", "Estupiñan" );
              
        empleados.add(ana);        
        empleados.add(franco);        
        empleados.add(sara);
        
        EstrategiaAtencion instance = new EstrategiaAtencion();
        Integer expResult = 1; //Tipo 1 Operador
        Empleado empleado = instance.buscarEmpleado(empleados);
        assertNotNull( empleado );
        assertEquals( expResult , empleado.getTipo());
    }
        
    /**
     * Prueba para buscar supervisor disponible
     */
    @Test
    public void testBuscarSupervisor() {
        logger.info("buscarEmpleado Supervisor");                
        List<Empleado> empleados = new ArrayList<Empleado>();
              
        Empleado ana = mock(Empleado.class);
        when(ana.getDisponible()).thenReturn( false  );
        Empleado franco = new Supervisor( "Franco", "Armani" );        
        Empleado sara = new Director( "Sara", "Estupiñan" );
              
        empleados.add(ana);                
        empleados.add(franco);        
        empleados.add(sara);
                
        EstrategiaAtencion instance = new EstrategiaAtencion();
        Integer expResult = 2; //Tipo 2 supervisor
        Empleado empleado = instance.buscarEmpleado(empleados);
        assertNotNull( empleado );
        assertEquals( expResult , empleado.getTipo());
    }
        
    /**
     * Prueba para buscar director disponible
     */
    @Test
    public void testBuscarDirector() {
        logger.info("buscarEmpleado Director");                
        List<Empleado> empleados = new ArrayList<Empleado>();
              
        Empleado ana = mock(Empleado.class);
        when(ana.getDisponible()).thenReturn( false  );
        Empleado franco = mock(Empleado.class);
        when( franco.getDisponible()).thenReturn( false  );        
        Empleado sara = new Director( "Sara", "Estupiñan" );
              
        empleados.add(ana);        
        empleados.add(franco);        
        empleados.add(sara);               
                
        EstrategiaAtencion instance = new EstrategiaAtencion();
        Integer expResult = 3; //Tipo 2 supervisor
        Empleado empleado = instance.buscarEmpleado(empleados);
        assertNotNull( empleado );
        assertEquals( expResult , empleado.getTipo());
    }
        
    /**
     * Prueba para conocer si todos los empleados estan ocupados
     */
    @Test
    public void testAsignarANadie() {
        logger.info("buscarEmpleado para no asignar a nadie");                
        List<Empleado> empleados = new ArrayList<Empleado>();
              
        Empleado ana = mock(Empleado.class);
        when(ana.getDisponible()).thenReturn( false  );
        Empleado franco = mock(Empleado.class);
        when( franco.getDisponible()).thenReturn( false  );        
        Empleado sara = mock(Empleado.class);
              
        empleados.add(ana);        
        empleados.add(franco);        
        empleados.add(sara);               
                
        EstrategiaAtencion instance = new EstrategiaAtencion();        
        Empleado empleado = instance.buscarEmpleado(empleados);
        assertNull( empleado );        
    } 
   
}
