package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertarArticulo extends JFrame{

    private JPanel panel1;
    private JTextField tDescri;
    private JTextField tPro;
    private JTextField tCat;
    private JTextField tStock;
    private JTextField tPrecioC;
    private JTextField tPrecioV;
    private JButton insertarButton;
    private JButton cancelarButton;
    private JComboBox comboProv;
    private JComboBox comboCat;

    public InsertarArticulo(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        //rellenando combobox Proveedor
        Main.basedatos.rellenarComboProveedor(comboProv);

        //rellenando combobox Categoría
        Main.basedatos.rellenarComboCategoria(comboCat);

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Descripcion = tDescri.getText();
                String datosProveedor = (String) comboProv.getSelectedItem();
                String Proveedor = Main.basedatos.obtenerId(datosProveedor);
                String datosCategoria = (String) comboCat.getSelectedItem();
                String Categoria = Main.basedatos.obtenerId(datosCategoria);
                String Stock = tStock.getText();
                String PC = tPrecioC.getText();
                String PV = tPrecioV.getText();
                try{
                    Main.basedatos.insertarArticulo(Descripcion, Proveedor, Categoria, Stock, PC, PV);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                dispose();
                new VentanaPrincipal();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal();
            }
        });
    }

}
