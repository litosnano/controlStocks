package com.company;

import javax.swing.*;
import java.sql.*;

public class BDD {

    private static String datosConexion = "jdbc:mysql://localhost/";
    private static String baseDatos = "ControlStock";
    private static String usuario = "root";
    private static String password = "";//your own password
    public Connection con;

    //DataBase constructor
    public BDD(){
        try {
            con = DriverManager.getConnection(datosConexion + "?useSSL=true", usuario, password);
            try {
                crearBDD(); //llamo a la función para crear la base de datos si no existe
                crearTablaProveedor(); //
                crearTablaCategoria(); //llamo a la función que crea la tabla director si no existe
                crearTablaArticulo(); //llamo a la función que crea la tabla pelicula si no existe
            } catch (Exception e) { //recojo las posibles excepciones
                e.printStackTrace();
            }
        }catch(SQLException e){ //recojo la excepción del SQL
            e.printStackTrace();
        }
    }

    //function creates the Database
    private void crearBDD() throws Exception {
        String query = "create database if not exists " + baseDatos + ";";
        Statement stmt = null; //clase para crear una sentencia de ejecución contra el servidor
        try {
            //ejecutamos la creación de la base de datos con conexión solo a servidor
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            //sobreescribimos el "con" para que apartir de ahora sea una conexión a la BDD recien creada
            con = DriverManager.getConnection(datosConexion + baseDatos + "?useSLL=true", usuario, password);
        } catch (SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }
        finally { //cierro el statement
            stmt.close();
        }
    }

    //function creates Proveedor table inside BDD
    private void crearTablaProveedor() throws Exception{
        String query;
        Statement stmt = null;
        try{ //Creating Proveedor table
            query = "create table if not exists Proveedor("
                    + "IdProveedor int primary key auto_increment, "
                    + "NombreProveedor varchar(30), "
                    + "NIF varchar(9), "
                    + "TeléfonoContacto int);";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function creates Categoria table inside BDD
    private void crearTablaCategoria() throws Exception{
        String query;
        Statement stmt = null;
        try{ //Creating Categoria table
            query = "create table if not exists Categoria("
                    + "IdCategoria int primary key auto_increment, "
                    + "NombreCategoria varchar(20));";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function creates Artículo table inside BDD
    private void crearTablaArticulo() throws Exception{
        String query;
        Statement stmt = null;
        try{ //Creating Articulo table
            query = "create table if not exists Articulo("
                    + "IdArticulo int primary key auto_increment, "
                    + "Descripcion varchar(30), "
                    + "Proveedor int, "
                    + "Categoria int, "
                    + "UnidadesStock int, "
                    + "PrecioCoste double, "
                    + "PrecioVenta double, "
                    + "foreign key (Proveedor) references Proveedor(IdProveedor), "
                    + "foreign key (Categoria) references Categoria(IdCategoria));";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function allows to insert new category (CATEGORIA) inside CATEGORIA's table
    public void insertarCategoria(String Nombre) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "INSERT INTO Categoria (NombreCategoria) values ('" + Nombre + "');";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function allows to insert new supplier (PROVEEDOR) inside PROVEEDOR's table
    public void insertarProveedor(String Nombre, String NIF, String Telefono) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "INSERT INTO PROVEEDOR (NombreProveedor, NIF, TeléfonoContacto) values ('" + Nombre + "', '" + NIF +"', '" + Telefono +"');";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function allows to insert new article (ARTICULO) inside ARTICULO's table
    public void insertarArticulo(String Descripcion, String Proveedor, String Categoria, String Stock, String PC, String PV) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "INSERT INTO Director (Nombre, Apellidos) values ('" + Descripcion + "', '" + Proveedor +"', '" + Categoria +"', '" + Stock +"', '" + PC +"', '" + PV +"');";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function to fill Proveedor combobox
    public void rellenarComboProveedor(JComboBox combo1){
        String query = "SELECT * FROM Proveedor";
        String texto;
        try {
            Statement stmt = Main.basedatos.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //Cargamos el combo con el id separador nombre
            while (rs.next()) {
                texto = rs.getString(1) + " - " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
                combo1.addItem(texto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //function to fill Proveedor combobox
    public void rellenarComboCategoria(JComboBox combo1){
        String query = "SELECT * FROM Categoria";
        String texto;
        try {
            Statement stmt = Main.basedatos.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //Cargamos el combo con el id separador nombre
            while (rs.next()) {
                texto = rs.getString(1) + " - " + rs.getString(2);
                combo1.addItem(texto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //function to split data and to get whatever's Id
    public String obtenerId(String datos){
        String delimiter = " - ";
        String[] temp;
        temp = datos.split(delimiter);
        return temp[0];
    }
}
