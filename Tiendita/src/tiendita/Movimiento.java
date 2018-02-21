
package tiendita;


public class Movimiento {
    int id;
    String fecha;
    Double montoOperacion;
    Double montoRecibido;
    Double vueltoOperacion;
    String descripcion;
    String tipo;
    int nit;

    public Movimiento(){
    
    }

    public Movimiento(int id, String fecha, Double montoOperacion, Double montoRecibido, Double vueltoOperacion, String descripcion, String tipo, int nit) {
        this.id = id;
        this.fecha = fecha;
        this.montoOperacion = montoOperacion;
        this.montoRecibido = montoRecibido;
        this.vueltoOperacion = vueltoOperacion;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Double montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public Double getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(Double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public Double getVueltoOperacion() {
        return vueltoOperacion;
    }

    public void setVueltoOperacion(Double vueltoOperacion) {
        this.vueltoOperacion = vueltoOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }
}
    