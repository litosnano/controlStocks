package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarArticulo extends JFrame{

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton eliminarButton;
    private JButton cancelarButton;

    public EliminarArticulo(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling the combobox with ARTICULO's information
        Main.basedatos.rellenarComboArticulo(comboBox1);

        //filling the combobox with PROVEEDOR's information
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosArt = (String) comboBox1.getSelectedItem();
                String IdArt = Main.basedatos.obtenerId(datosArt);
                try{
                    Main.basedatos.eliminarArticulo(IdArt);
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
