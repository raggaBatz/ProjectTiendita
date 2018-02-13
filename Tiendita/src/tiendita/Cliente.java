/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita;

/**
 *
 * @author RAGGA-PC
 */
public class Cliente {
    private int nit;
    private String nombre;
    private String apellido;
    private Double limite;
    
    public Cliente(){}

    public Cliente(int nit, String nombre, String apellido, Double limite) {
        this.nit = nit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.limite = limite;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
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

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }   
    
}
