/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.Random;

/**
 * Clase para administrar las instancias de objetos llamadas
 * @author User
 */
public class Llamada  {
    
    public static final Integer MIN_LLAMADAS_DURACION = 5; //Duracion de llamadas minimo de 5 segundos
    public static final Integer MAX_LLAMADAS_DURACION = 10; //Duracion de llamadas maximo de 10 segundos
    private Integer tiempoSegundos; //Tiempo aleatorio asignado a la llamada
    private Boolean activa; //Estado en el cual se encuentra la llamada
        
    public Llamada( ) { 
        this.activa = true;
        Random random = new Random();
        //Asignacion aleatoria del tiempo de llamada entre 5 y 10 segundos
        this.tiempoSegundos = random.nextInt( MAX_LLAMADAS_DURACION + 1 - MIN_LLAMADAS_DURACION) + MIN_LLAMADAS_DURACION;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos( Integer tiempoSegundos ) {
        this.tiempoSegundos = tiempoSegundos;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {        
        this.activa = activa;
    }

}
