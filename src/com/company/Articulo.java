package com.company;

public class Articulo {

    private int IdArticulo;
    private String Descripcion;
    private String Proveedor;
    private String Categoria;
    private int UnidadesStock;
    private double PrecioCoste;
    private double PrecioVenta;

    // Articulo contructor
    public Articulo(int idArticulo, String descripcion, String proveedor, String categoria, int unidadesStock, double precioCoste, double precioVenta) {
        this.IdArticulo = idArticulo;
        this.Descripcion = descripcion;
        this.Proveedor = proveedor;
        this.Categoria = categoria;
        this.UnidadesStock = unidadesStock;
        this.PrecioCoste = precioCoste;
        this.PrecioVenta = precioVenta;
    }

    // Getters
    public int getIdArticulo() {
        return IdArticulo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public String getProveedor() {
        return Proveedor;
    }
    public String getCategoria() {
        return Categoria;
    }
    public int getUnidadesStock() {
        return UnidadesStock;
    }
    public double getPrecioCoste() {
        return PrecioCoste;
    }
    public double getPrecioVenta() {
        return PrecioVenta;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }
    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
    public void setUnidadesStock(int unidadesStock) {
        UnidadesStock = unidadesStock;
    }
    public void setPrecioCoste(double precioCoste) {
        PrecioCoste = precioCoste;
    }
    public void setPrecioVenta(double precioVenta) {
        PrecioVenta = precioVenta;
    }
}
