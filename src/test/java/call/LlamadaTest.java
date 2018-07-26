/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para las instancias de objeto llamadas
 * @author Rafael Medrano
 */
public class LlamadaTest {
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public LlamadaTest() {
    }
    
    /**
     * Prueba para comprobar que la duracion de las llamadas se encuentra entre 5 y 10 segundos de duracion.
     * Prueba ejecutada para 10 llamadas 
     */
    @Test
    public void testGetTiempoSegundos() {
        logger.info("getTiempoSegundos");        
        //Se efectuan 10 comprobaciones
        for(int i = 0; i<10; i++){
            Llamada instance = new Llamada();
            int result = instance.getTiempoSegundos();
            assertTrue( (result >= 5 && result <=10) );
        }                 
    }
    
    /**
     * Prueba para comprobar que al momento de que se crea la lllamada se crea con estado Activa / True
     */
    @Test
    public void testGetActiva() {
        logger.info("getActiva");
        Llamada instance = new Llamada();
        Boolean expResult = true;
        Boolean result = instance.getActiva();
        assertEquals(expResult, result);        
    }
    
}
