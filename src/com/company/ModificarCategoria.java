package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.Main.basedatos;

public class ModificarCategoria extends JFrame {

    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField tNombre;
    private JButton modificarButton;
    private JButton cancelarButton;

    public ModificarCategoria(){

        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling the combobox with CATEGORIA's information
        Main.basedatos.rellenarComboCategoria(comboBox1);

        //when a CATEGORIA is selected it'll appear the data in the JTextField
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosCate = (String)comboBox1.getSelectedItem();
                String IdCate = Main.basedatos.obtenerId(datosCate);
                String query = "SELECT * FROM Categoria WHERE IdCategoria="+IdCate+";";
                ResultSet rs = null;
                try{
                    Statement stmt = basedatos.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    rs = stmt.executeQuery(query);
                    if(rs.next()){
                        tNombre.setText(rs.getString(2));
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
                String datosCate = (String) comboBox1.getSelectedItem();
                String IdCategoria = Main.basedatos.obtenerId(datosCate);
                String Categoria = tNombre.getText();
                try{
                    Main.basedatos.actualizarCategoria(IdCategoria,Categoria);
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
