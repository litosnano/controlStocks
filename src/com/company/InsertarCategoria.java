package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertarCategoria extends JFrame{

    private JPanel panel1;
    private JTextField tCategoria;
    private JButton insertarButton;
    private JButton cancelarButton;

    public InsertarCategoria(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);


        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NombreCategoria = tCategoria.getText();
                try{
                    Main.basedatos.insertarCategoria(NombreCategoria);
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
