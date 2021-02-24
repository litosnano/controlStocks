package com.company;

import javax.swing.*;
import java.awt.event.*;

public class InsertarProveedor extends JFrame{

    private JPanel panel1;
    private JTextField tProveedor;
    private JTextField tNIF;
    private JTextField tTef;
    private JButton insertarButton;
    private JButton cancelarButton;

    public InsertarProveedor() {
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
                String NombreProveedor = tProveedor.getText();
                String NIF = tNIF.getText();
                String Tef = tTef.getText();
                try {
                    Main.basedatos.insertarProveedor(NombreProveedor, NIF, Tef);
                } catch (Exception ex) {
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
