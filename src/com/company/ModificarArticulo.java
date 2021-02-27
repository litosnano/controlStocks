package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.Main.basedatos;

public class ModificarArticulo extends JFrame{

    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField tDes;
    private JTextField tUni;
    private JTextField tPC;
    private JTextField tPV;
    private JButton modificarButton;
    private JButton cancelarButton;
    private JComboBox comboProv;
    private JComboBox comboCat;

    public ModificarArticulo(){

        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling different combobox with their own information
        Main.basedatos.rellenarComboArticulo(comboBox1);

        //when an ARTICULO is selected it'll appear the data in the JTextField
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosArt = (String)comboBox1.getSelectedItem();
                String IdArt = Main.basedatos.obtenerId(datosArt);
                String query = "SELECT * FROM Articulo WHERE IdArticulo="+IdArt+";";
                ResultSet rs = null;
                try{
                    Statement stmt = basedatos.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        tDes.setText(rs.getString(2));
                        Main.basedatos.rellenarComboProveedor(comboProv);
                        Main.basedatos.rellenarComboCategoria(comboCat);
                        tUni.setText(rs.getString(5));
                        tPC.setText(rs.getString(6));
                        tPV.setText(rs.getString(7));
                    }
                }catch(SQLException el) {
                    el.printStackTrace();
                }
            }
        });

        //button that will modify Category Database including what has been included in the JTextField
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosArt = (String) comboBox1.getSelectedItem();
                String IdArt = Main.basedatos.obtenerId(datosArt);
                String Descrip = tDes.getText();
                String datosProv = (String) comboProv.getSelectedItem();
                String Prov = Main.basedatos.obtenerId(datosProv);
                String datosCat = (String) comboCat.getSelectedItem();
                String Categ = Main.basedatos.obtenerId(datosCat);
                String Stock = tUni.getText();
                String PC = tPC.getText();
                String PV = tPV.getText();
                try{
                    Main.basedatos.actualizarArticulo(IdArt, Descrip, Prov, Categ, Stock, PC, PV);
                }catch (Exception el){
                    el.printStackTrace();
                }
                dispose();
                new VentanaPrincipal();
            }
        });

        //button to come back to the menu
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal();
            }
        });
    }
}
