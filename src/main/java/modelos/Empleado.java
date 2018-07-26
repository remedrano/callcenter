/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import call.Llamada;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Medrano
 */
public class Empleado implements Runnable {

    private String nombre;
    private String apellido;
    private Boolean disponible;
    private Integer tipo;
    private Boolean activo;
    private String nombreTipo;
    
    private ConcurrentLinkedDeque<Llamada> llamadaEntrante; // Llamadas entrantes
    private ConcurrentLinkedDeque<Llamada> llamadaAtentida; // Llamadas atentidas
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public Empleado(String nombre, String apellido, Boolean disponible, Integer tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.disponible = disponible;
        this.setNombreTipo(this.evaluarTipo(tipo));
        this.tipo = tipo;
        this.llamadaEntrante = new ConcurrentLinkedDeque<>();
        this.llamadaAtentida = new ConcurrentLinkedDeque<>();
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer type) {
        this.tipo = tipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Dependiendo del tipo se retorna el nombre del tipo del empleado.
     * 1 = Operador, 2 = Supervisor, 3 = Director    
     */
    public String evaluarTipo(Integer tipo) {
        String nombreTipo = "";
        switch (tipo) {
            case 1:
                nombreTipo = "Operario";
                break;
            case 2:
                nombreTipo = "Supervisor";
                break;
            case 3:
                nombreTipo = "Director";
                break;
        }
        return nombreTipo;
    }

    /**
     * Accion de responder la llamada por el empleado
     * 
     */
    public synchronized void responderLlamada(Llamada llamada) {
        logger.info("Empleado(" + this.nombreTipo + ") " + this.getNombre() + " agrego una llamada de " + llamada.getTiempoSegundos() + " segundos");
        this.llamadaEntrante.add(llamada);
    }

    /**
     * Retorna las llamadas atendidas por el empleado  
     * 
     */
    public synchronized List<Llamada> getLlamadasAtendidas() {       
        return new ArrayList<>(this.llamadaAtentida);
    }   

    /**
     * Ejecuta la atencion de las llamadas de parte del empleado. Un empleado
     * siempre estara disponible para atender llamadas
     */
    @Override
    public void run() {

        logger.info("Empleado(" + this.getNombreTipo() + ") -- " + this.getNombre() + " -- empezo a trabajar");
        while ( true ) {
            if (!this.llamadaEntrante.isEmpty()) {
                Llamada llamada = this.llamadaEntrante.poll();

                this.setDisponible(false);
                logger.info("Empleado(" + this.getNombreTipo() + ") --" + (this.getNombre() + " " + this.getApellido()) + "-- empezo a trabajar en la llamada de " + llamada.getTiempoSegundos() + " segundos");
                try {
                    TimeUnit.SECONDS.sleep(llamada.getTiempoSegundos());
                } catch (InterruptedException e) {
                    logger.severe("Empleado(" + this.getNombreTipo() + ") --" + (this.getNombre() + " " + this.getApellido()) + "--- ha interrumpido y podria no finalizar la llamada de " + llamada.getTiempoSegundos() + " segundos");
                } finally {
                    this.setDisponible(true);
                }
                
                this.llamadaAtentida.add(llamada);
                System.out.println("-->"+this.llamadaAtentida+"<-->");
                logger.info("Empleado(" + this.getNombreTipo() + ") --" + (this.getNombre() + " " + this.getApellido()) + "-- finalizo la llamada de " + llamada.getTiempoSegundos() + " segundos");
            } else {
                logger.info(" Esperando llamada ... ");
            }
        }
    }

}
