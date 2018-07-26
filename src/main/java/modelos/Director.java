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
public class Director extends Empleado{
    
    public Director(String nombre, String apellido ) {
        super(nombre, apellido, true, 3);
    }
    
}
