package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarCategoria extends JFrame{

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton eliminarButton;
    private JButton cancelarButton;

    public EliminarCategoria(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //filling the combobox with CATEGORIA's information whitout ARTICULO
        Main.basedatos.rellenarComboCategoriaSinArticulo(comboBox1);

        //button that will erase Category selected from Database
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datosCat = (String)comboBox1.getSelectedItem();
                String IdCat =Main.basedatos.obtenerId(datosCat);
                try{
                    Main.basedatos.eliminarCategoria(IdCat);
                }catch(Exception ex){
                    ex.printStackTrace();;
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
