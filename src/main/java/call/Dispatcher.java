/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import modelos.Empleado;

/**
 * Clase para la atencion de llamadas
 * @author Rafael Medrano
 */
public class Dispatcher implements Runnable {

    private Boolean activo;
    public static final Integer MAX_LLAMADAS = 10;
    private ConcurrentLinkedDeque<Empleado> empleados;
    private ConcurrentLinkedDeque<Llamada> llamadas;
    private EstrategiaAtencion estrategiaAtencion;
    private ExecutorService servicioExe;

    private Logger logger = Logger.getLogger(getClass().getName());

    public Dispatcher(List<Empleado> empleados ) {
        this.empleados = new ConcurrentLinkedDeque<Empleado>(empleados);
        this.llamadas = new ConcurrentLinkedDeque<>();
        this.estrategiaAtencion = new EstrategiaAtencion();
        this.servicioExe = Executors.newFixedThreadPool(empleados.size() + 1);

    }

    /**
     * Metodo para la asignacion de llamadas    
     */
    public synchronized void dispatchCall(Llamada llamada) {
        logger.info("Nueva llamada de " + llamada.getTiempoSegundos() + " segundos ");
        this.llamadas.add(llamada);

    }

    /**
     * Obtener el estado de la asignación
     * @return Boolean 
     */
    public synchronized Boolean getActivo() {
        return activo;
    }

    /**
     * Asignar o detener la ejecucion de la asignacion de llamadas
     * @param activo Boolean
     */
    public synchronized void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Iniciar la ejecucion del dispatcher (Asignacion de llamadas)
     * return void
     */
    public synchronized void start() {
        this.activo = true;
        for (Empleado empleado : this.empleados) {
            this.servicioExe.execute(empleado);
        }
        this.servicioExe.execute(this);
    }

    /**
     * Ejecucion del hilo para asignacion de llamadas a empleados
     */
    @Override
    public void run() {

        while (this.getActivo()) {
            if (this.llamadas.isEmpty()) {
                continue;
            } else {
                Empleado empleado = this.estrategiaAtencion.buscarEmpleado(this.empleados);
                if (empleado == null) {
                    continue;
                }
                try {
                    Llamada llamada = llamadas.pollFirst();
                    empleado.responderLlamada(llamada);
                } catch (Exception e) {
                    logger.severe(e.toString());
                }
            }
        }

    }

}
