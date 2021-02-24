package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame{

    public JPanel panel1;
    private JButton insertarArticuloButton;
    private JButton modificarArticuloButton;
    private JButton eliminarArticuloButton;
    private JButton insertarProveedorButton;
    private JButton modificarProveedorButton;
    private JButton eliminarProveedorButton;
    private JButton insertarCategoriaButton;
    private JButton modificarCategoriaButton;
    private JButton eliminarCategoriaButton;
    private JButton consultaStockButton;

    public VentanaPrincipal() {
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        insertarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try{
                    new InsertarCategoria();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        insertarProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try{
                    new InsertarProveedor();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        insertarArticuloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try{
                    new InsertarArticulo();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });



    }
}
