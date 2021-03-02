package com.company;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

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
            query = "INSERT INTO Proveedor (NombreProveedor, NIF, TeléfonoContacto) values ('" + Nombre + "', '" + NIF +"', '" + Telefono +"');";
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
            query = "INSERT INTO Articulo (Descripcion, Proveedor, Categoria, UnidadesStock, PrecioCoste, PrecioVenta) VALUES ('" + Descripcion + "', '" + Proveedor +"', '" + Categoria +"', '" + Stock +"', '" + PC +"', '" + PV +"');";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e) { //recojo la excepción del SQL
            e.printStackTrace();
        }finally{ //cierro el statement
            stmt.close();
        }
    }

    //function will modify CATEGORIA data
    public void actualizarCategoria(String Id, String Categoria) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "UPDATE Categoria SET NombreCategoria='"+Categoria+"'WHERE IdCategoria="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    //function will modify PROVEEDOR data
    public void actualizarProveedor(String Id, String Proveedor, String NIF, String Telefono) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "UPDATE Proveedor SET NombreProveedor='"+Proveedor+"', NIF='"+NIF+"', TeléfonoContacto='"+Telefono+"' WHERE IdProveedor="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    //function will modify ARTICULO data
    public void actualizarArticulo(String Id, String Desc, String Prov, String Cat, String Uni, String PC, String PV) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "UPDATE Articulo SET Descripcion='"+Desc+"', Proveedor='"+Prov+"', Categoria='"+Cat+"', UnidadesStock='"+Uni+"' " +
                    ", PrecioCoste='"+PC+"', PrecioVenta='"+PV+"' WHERE IdArticulo="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    //function will erase Category data
    public void eliminarCategoria(String Id) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "DELETE FROM Categoria WHERE IdCategoria="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    //function will erase Proveedor data
    public void eliminarProveedor(String Id) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "DELETE FROM Proveedor WHERE IdProveedor="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stmt.close();
        }
    }

    //function will erase Articulo data
    public void eliminarArticulo (String Id) throws Exception{
        String query;
        Statement stmt = null;
        try{
            query = "DELETE FROM Articulo WHERE IdArticulo="+Id+";";
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
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
            //filling combo with id - name
            while (rs.next()) {
                texto = rs.getString(1) + " - " + rs.getString(2);
                combo1.addItem(texto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //function to fill Proveedor Combobox that doesn't have any Articulo related
    public void rellenarComboProveedorsinArticulo(JComboBox combo1){
        String query = "SELECT * FROM Proveedor WHERE IdProveedor NOT IN (SELECT DISTINCT(Proveedor) FROM Articulo);";
        String texto;
        try{
            Statement stmt = Main.basedatos.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                //filling combo with id - name
                texto = rs.getString(1) + " - " + rs.getString(2);
                combo1.addItem(texto);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //function to fill Categoria combobox
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

    //function to fill Categoria Combobox that doesn't have any Articulo related
    public void rellenarComboCategoriaSinArticulo(JComboBox combo1){
        String query = "SELECT * FROM Categoria WHERE IdCategoria NOT IN (SELECT DISTINCT(Categoria) FROM Articulo);";
        String texto;
        try{
            Statement stmt = Main.basedatos.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                texto = rs.getString(1) + " - " + rs.getString(2);
                combo1.addItem(texto);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //function to fill Articulo combobox
    public void rellenarComboArticulo(JComboBox combo1){
        String query = "SELECT * FROM Articulo";
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

    //function to obtein a list of ARTICULOS according PROVEEDOR and CATEGORIAS chose
    public ArrayList<String> realizarBusqueda(String IdProv, String IdCat) throws Exception{
        ArrayList<String> listaArticulos = new ArrayList<>();
        String query = "SELECT a.Descripcion, p.NombreProveedor, c.NombreCategoria, a.UnidadesStock, a.PrecioCoste, a.PrecioVenta" +
                " FROM Articulo a, Proveedor p, Categoria c WHERE a.Proveedor = p.IdProveedor AND a.Categoria = c.IdCategoria";
        if(IdProv!="Selecciona una opción"){
            query = query + " AND p.IdProveedor = " + IdProv;
        }
        if(IdCat!="Selecciona una opción"){
            query = query + " AND c.IdCategoria = " + IdCat;
        }
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt=con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                listaArticulos.add(rs.getString(1) + " - " +
                        rs.getString(2) + " - " +
                        rs.getString(3) + " - " +
                        rs.getString(4) + " - " +
                        rs.getString(5) + " - " +
                        rs.getString(6) + " - ");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            rs.close();
            stmt.close();
        }
        return listaArticulos;
    }

    //function to split data
    public String[] separarDatos(String datos){
        String delimeter = " - ";
        String [] temp;
        temp = datos.split(delimeter);
        return temp;
    }
}
