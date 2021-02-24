package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.Main.basedatos;

public class ModificarProveedor extends JFrame{

    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField tNombre;
    private JButton modificarButton;
    private JButton cancelarButton;
    private JTextField tNIF;
    private JTextField tTef;

    public ModificarProveedor(){

        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling the combobox with PROVEEDORES' information
        Main.basedatos.rellenarComboProveedor(comboBox1);

        //when a PROVEEDOR is selected it'll appear the data in the JTextField
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosProv = (String)comboBox1.getSelectedItem();
                String IdProv = Main.basedatos.obtenerId(datosProv);
                String query = "SELECT * FROM Proveedor WHERE IdProveedor="+IdProv+";";
                ResultSet rs = null;
                try{
                    Statement stmt = basedatos.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        tNombre.setText(rs.getString(2));
                        tNIF.setText(rs.getString(3));
                        tTef.setText(rs.getString(4));
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
                String datosProv = (String) comboBox1.getSelectedItem();
                String IdProv = Main.basedatos.obtenerId(datosProv);
                String Proveedor = tNombre.getText();
                String NIF = tNIF.getText();
                String Telefono = tTef.getText();
                try{
                    Main.basedatos.actualizarProveedor(IdProv,Proveedor, NIF, Telefono);
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
