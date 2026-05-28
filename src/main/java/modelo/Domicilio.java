package modelo;

import java.util.Objects;

public class Domicilio {

    private String numeroOrden;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String descripcionPedido;
    private String categoria;
    private double costo;
    private String estado;

    public Domicilio(String numeroOrden,
                     String nombreCliente,
                     String direccion,
                     String telefono,
                     String descripcionPedido,
                     String categoria,
                     double costo,
                     String estado) {

        this.numeroOrden = numeroOrden;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcionPedido = descripcionPedido;
        this.categoria = categoria;
        this.costo = costo;
        this.estado = estado;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcionPedido() {
        return descripcionPedido;
    }

    public void setDescripcionPedido(String descripcionPedido) {
        this.descripcionPedido = descripcionPedido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return """
            ------------------------------
            Numero Orden: %s
            Cliente: %s
            Direccion: %s
            Telefono: %s
            Pedido: %s
            Categoria: %s
            Costo: %.2f
            Estado: %s
            ------------------------------
            """.formatted(
                numeroOrden,
                nombreCliente,
                direccion,
                telefono,
                descripcionPedido,
                categoria,
                costo,
                estado
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domicilio)) return false;
        Domicilio domicilio = (Domicilio) o;
        return Objects.equals(numeroOrden, domicilio.numeroOrden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroOrden);
    }
}

