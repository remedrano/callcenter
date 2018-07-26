/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author User
 */
public class Supervisor extends Empleado {
    
    public Supervisor(String nombre, String apellido ) {
        super(nombre, apellido, true, 2);
    }
    
}
