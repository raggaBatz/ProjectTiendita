package tiendita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tiendita {
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static ArrayList<Tienda> listaTiendas = new ArrayList<>();
    private static ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
    
    public static void main(String[] args) {
        cargar();
        Scanner op = new Scanner( System.in );
        int val = 5;
        do{
            System.out.println("---MENU-PRINCIPAL---");
            //System.out.println("--2.Mantenimientos--");
            System.out.println("--1.Movimientos-----");
            System.out.println("--0.Salir-----");
            val = op.nextInt();
            if(val==2){
                //gestionarMantenimientos();
            }
            if(val==1){
                System.out.println("***************************");
                System.out.println("-----MOVIMIENTOS-----");
                gestionarMovimientos();
            }
        }while(val!=0);
    }
    
    private static String now(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    
    private static String nowTime(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
       
    private static void gestionarMovimientos(){
        Scanner op = new Scanner( System.in );
        int accion = 1;
        do{
            System.out.println("***************************");
            System.out.println("---1)Registrar-operacion---");
            System.out.println("---0)Salir-----------------");
            accion = op.nextInt();
            if(accion==1){
                registrarOperacion();
            }
        }while(accion!=0);
        
        if(!listaMovimientos.isEmpty()){
            System.out.println("***************************");
            System.out.println("-REALIZANDO-CIERRE-DE-CAJA-");
            System.out.println("***************************");
            String file = "";
            System.out.println("Archivo " + generarArchivo() + ".txt creado exitosamente");
        }
    }
    
    private static String generarArchivo(){
        String ubicacion = "src/tiendita/res/files/Cierre_caja_" + now() + "_";
        String archivo = "";
        String tb = "|";
        int bandera = 0;
        int numero = 0;
        do{
            numero++;
            archivo = ubicacion+String.valueOf(numero)+".txt";
            File f = new File(archivo);
            //if(f.exists() && !f.isDirectory()) { 
            if(!f.exists()){
                bandera = 1;
                FileWriter fos = null;
                try {
                    fos = new FileWriter(archivo);
                    PrintWriter dos = new PrintWriter(fos);
                    dos.println(format("ID",7)+tb+format("FECHA",20)+tb+format("MONTO",10)+tb+format("RECIBIDO",10)+tb+format("VUELTO",10)+tb+format("DESCRIPCION",35)+tb+format("TIPO",15)+tb+format("NIT",10));
                    for (int i=0;i<listaMovimientos.size();i++){
                        Movimiento m = listaMovimientos.get(i);
                        dos.print(format(String.valueOf(m.getId()),7));
                        dos.print(tb);
                        dos.print(format(m.getFecha(),20));
                        dos.print(tb);
                        dos.print(format(String.valueOf(m.getMontoOperacion()),10));
                        dos.print(tb);
                        dos.print(format(String.valueOf(m.getMontoRecibido()),10));
                        dos.print(tb);
                        dos.print(format(String.valueOf(m.getVueltoOperacion()),10));
                        dos.print(tb);
                        dos.print(format(m.getDescripcion(),35));
                        dos.print(tb);
                        dos.print(format(m.getTipo(),15));
                        dos.print(tb);
                        dos.print(format(String.valueOf(m.getNit()),10));
                        dos.println();
                    }   
                    dos.close();
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tiendita.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Tiendita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                System.out.println("Archivo " + numero + "existe");
            }
        }while(bandera==0);
        for(int i = 0; i < listaMovimientos.size(); i++){
            Movimiento m = listaMovimientos.get(i);
        }
        return archivo;
    }
    
    public static String format(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
    
    private static void registrarOperacion(){
        Scanner op = new Scanner( System.in );
        String mensaje = "";
        Double montoRecibido = 0.0;
        Double monto = 0.0;
        Double vuelto = 0.0;
        int nit = 0;
        int tipo = 0;
        int operacion = 0;
        try{
            System.out.println("***************************");
            System.out.println("------Monto--operacion-----");
            monto = op.nextDouble();
            System.out.println("--1)Efectivo---------------");
            System.out.println("--2)Credito----------------");
            System.out.println("--3)Cheque-----------------");
            tipo = op.nextInt();
            if(tipo==1){
                System.out.println("------Monto-recibido-------");
                montoRecibido = op.nextDouble();
                if(montoRecibido>monto){
                    vuelto = montoRecibido - monto;
                    mensaje = "Venta en efectivo";
                }else if(montoRecibido<monto){
                    mensaje = "Monto recibido es menor a monto de compra";
                    throw new Exception();
                }
            }else if(tipo==2){
                System.out.println("-------NIT-De-Cliente------");
                nit = op.nextInt();
                if(!validarCliente(nit)){
                    mensaje = "Cliente para venta a credito no existe";
                    throw new Exception();
                }else{
                    if(validarLimiteCliente(nit, monto)){
                        mensaje = "Venta al credito";
                    }else{
                        mensaje = "Venta supera limite credito";
                        throw new Exception();
                    }
                }
            }else if(tipo==3){
                System.out.println("-----Banco-del-cheque------");
                mensaje = op.nextLine();
                mensaje = "Pago en cheque de " + mensaje;
            }else{
                mensaje = "Seleccione una operacion valida";
                throw new Exception();
            }
            operacion = crearRegistro(monto,montoRecibido,vuelto,mensaje,"E",nit);
            if(vuelto>0.0){
                String m = "Vuelto de operacion " + operacion;
                int v = crearRegistro(vuelto,0.0,0.0,m,"S",nit);
            }
            System.out.println("Operacion " + operacion + " registrada exitosamente");
        }catch(Exception e){
            if(mensaje.equals("")){
                System.out.println("Error de informacion");
            }else{
                System.out.println("Error de informacion | " + mensaje);
            }
        }
    }
    
    private static Boolean validarCliente(int nit){
        if(listaClientes.isEmpty()){
            return false;
        }else{
            for (int i = 0; i < listaClientes.size(); i++) {
                int nitLista = listaClientes.get(i).getNit();
                if(nit == nitLista){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static Boolean validarLimiteCliente(int nit, Double monto){
        Double utilizado = 0.0, disponible = 0.0,limite = 0.0;
        
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cl= listaClientes.get(i);
            if(nit == cl.getNit()){
                limite = cl.getLimite();
                for(int m = 0; m < listaMovimientos.size();m++){
                    Movimiento mov = listaMovimientos.get(m);
                    if(cl.getNit() == mov.getNit()){
                        utilizado = utilizado + mov.getMontoOperacion(); 
                    }
                }
                if(limite>=(utilizado+monto)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    private static int crearRegistro(Double monto, Double montoRecibido, Double vuelto, String descripcion, String tipo, int nit){
        int idMovimiento = obtenerNumeroMovimiento();
        String mov = String.valueOf(idMovimiento);
        mov = mov.substring((mov.length() - 3), mov.length());
        if(mov.equals("001")){
            descripcion = "APERTURA DE CAJA";
        }
        Movimiento m = new Movimiento();
        m.setId(idMovimiento);
        m.setFecha(nowTime());
        m.setMontoOperacion(monto);
        m.setMontoRecibido(montoRecibido);
        m.setVueltoOperacion(vuelto);
        m.setDescripcion(descripcion);
        m.setTipo(tipo);
        m.setNit(nit);
        listaMovimientos.add(m);
        return idMovimiento;
    }
    
    private static int obtenerNumeroMovimiento(){
        int id = 0;
        if(listaMovimientos.isEmpty()){
            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            id = (dayOfMonth * 1000);
        }else{
            for (int i = 0; i < listaMovimientos.size(); i++) {
                id = listaMovimientos.get(i).getId();
            }
        }
        id++;
        return id;
    }
    
    private static void cargar(){
        cargarClientes();
        cargarTiendas();
    }
    
    private static void cargarClientes(){
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
    
    private static void cargarTiendas(){
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
                //System.out.println(id + " - " + nombre);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar lista de tiendas: " + e.getMessage());
        } 
    }
    
}
