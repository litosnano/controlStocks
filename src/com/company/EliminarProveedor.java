package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarProveedor extends JFrame{

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton eliminarButton;
    private JButton cancelarButton;

    public EliminarProveedor(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling the combobox with PROVEEDOR's information without ARTICULO
        Main.basedatos.rellenarComboProveedorsinArticulo(comboBox1);

        //filling the combobox with PROVEEDOR's information
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datoProv = (String) comboBox1.getSelectedItem();
                String IdProv = Main.basedatos.obtenerId(datoProv);
                try{
                    Main.basedatos.eliminarProveedor(IdProv);
                }catch(Exception ex){
                    ex.printStackTrace();
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
