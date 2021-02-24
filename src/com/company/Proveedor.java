package com.company;

public class Proveedor {

    private int IdProveedor;
    private String NombreProveedor;
    private String NIF;
    private int TeléfonoContacto;

    // Proveedor constructor
    public Proveedor(int idProveedor, String nombreProveedor, String NIF, int teléfonoContacto) {
        this.IdProveedor = idProveedor;
        this.NombreProveedor = nombreProveedor;
        this.NIF = NIF;
        this.TeléfonoContacto = teléfonoContacto;
    }

    // Getters
    public int getIdProveedor() {
        return IdProveedor;
    }
    public String getNombreProveedor() {
        return NombreProveedor;
    }
    public String getNIF() {
        return NIF;
    }
    public int getTeléfonoContacto() {
        return TeléfonoContacto;
    }

    // Setters
    public void setNombreProveedor(String nombreProveedor) {
        NombreProveedor = nombreProveedor;
    }
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
    public void setTeléfonoContacto(int teléfonoContacto) {
        TeléfonoContacto = teléfonoContacto;
    }
}
