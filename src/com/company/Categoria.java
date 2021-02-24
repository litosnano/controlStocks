package com.company;

public class Categoria {

    private int IdCategoria;
    private String NombreCategoria;

    // Categoria Constructor
    public Categoria(int idCategoria, String nombreCategoria) {
        this.IdCategoria = idCategoria;
        this.NombreCategoria = nombreCategoria;
    }

    // Getters
    public int getIdCategoria() {
        return IdCategoria;
    }
    public String getNombreCategoria() {
        return NombreCategoria;
    }

    // Setters
    public void setNombreCategoria(String nombreCategoria) {
        NombreCategoria = nombreCategoria;
    }
}
