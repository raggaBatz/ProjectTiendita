package tiendita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author RAGGA-PC
 */
public class Tiendita {
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ArrayList<Tienda> listaTiendas = new ArrayList<>();
    
    public static void main(String[] args) {
       Tiendita t = new Tiendita();
       t.principal();
    }
    
    public void principal(){
        cargar();
    }
    
    public void cargar(){
        cargarClientes();
        cargarTiendas();
    }
    
    public void cargarClientes(){
        try {
            File f = new File("src/tiendita//res/files/Clientes.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine;
            while ((readLine = b.readLine()) != null) {
                String[] val = readLine.split("\\|");
                int nit = Integer.parseInt(val[0]);
                String nombre =  val[1];
                String apellido =  val[2];
                Double limite = Double.parseDouble(val[3]);
                Cliente cl = new Cliente(nit,nombre,apellido,limite);
                listaClientes.add(cl);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar lista de clientes: " + e.getMessage());
        } 
    }
    
    public void cargarTiendas(){
        try {
            File f = new File("src/tiendita//res/files/Tiendas.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine;
            while ((readLine = b.readLine()) != null) {
                String[] val = readLine.split("\\|");
                int id = Integer.parseInt(val[0]);
                String nombre =  val[1];
                String ubicacion =  val[2];
                Tienda td = new Tienda(id,nombre,ubicacion);
                listaTiendas.add(td);
                System.out.println(id + " - " + nombre);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar lista de tiendas: " + e.getMessage());
        } 
    }
    
}
