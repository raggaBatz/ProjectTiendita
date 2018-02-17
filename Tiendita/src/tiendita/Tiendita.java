package tiendita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Tiendita {
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ArrayList<Tienda> listaTiendas = new ArrayList<>();
    ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
    
    public static void main(String[] args) {
       Tiendita t = new Tiendita();
       t.principal();
    }
    
    public void principal(){
        cargar();
        Scanner op = new Scanner( System.in );
        int val = 5;
        do{
            System.out.println("---MENU-PRINCIPAL---");
            System.out.println("--1.Mantenimientos--");
            System.out.println("--2.Movimientos-----");
            System.out.println("--0.Salir-----");
            val = op.nextInt();
            if(val==1){
                //gestionarMantenimientos();
            }
            if(val==2){
                System.out.println("***************************");
                System.out.println("-----MOVIMIENTOS-----");
                gestionarMovimientos();
            }
        }while(val!=0);
    }
    
    public void gestionarMovimientos(){
        Scanner op = new Scanner( System.in );
        int idMovimiento = obtenerNumeroMovimiento();
        System.out.println("***************************");
        System.out.println("--Monto--");
        System.out.println("--2.Movimientos-----");
        System.out.println("--0.Salir-----");
        int val = op.nextInt();
        if(val==1){
            //gestionarMantenimientos();
        }
        if(val==2){
            gestionarMovimientos();
        }
    }
    
    public int obtenerNumeroMovimiento(){
        int id = 0;
        if(listaMovimientos.isEmpty()){
            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            id = dayOfMonth + 0001;
        }else{
            for (int i = 0; i < listaMovimientos.size(); i++) {
                id = listaMovimientos.get(i).getId();
            }
        }
        id++;
        return id;
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
