package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListadoArticulos extends JFrame{

    private JPanel panel1;
    private JComboBox comboProv;
    private JComboBox comboCat;
    private JButton buscarButton;
    private JTextField tDes;
    private JTextField tProv;
    private JTextField tCat;
    private JTextField tUn;
    private JTextField tPC;
    private JTextField tPV;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton menúPrincipalButton;
    private JLabel mensaje;

    ArrayList<String> listaArticulos;
    int currentArt;

    public ListadoArticulos(){
        // setting panel
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        //at first anything is visible
        tDes.setVisible(false);
        tProv.setVisible(false);
        tCat.setVisible(false);
        tUn.setVisible(false);
        tPC.setVisible(false);
        tPV.setVisible(false);
        anteriorButton.setVisible(false);
        siguienteButton.setVisible(false);
        mensaje.setVisible(false);

        //filling different combobox with their own information
        Main.basedatos.rellenarComboProveedor(comboProv);
        Main.basedatos.rellenarComboCategoria(comboCat);

        //when some options are selected this will provide the informations in the each JTextField
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentArt = 0;
                String datosProv = (String) comboProv.getSelectedItem();
                String Prov = Main.basedatos.obtenerId(datosProv);
                String datosCat = (String) comboCat.getSelectedItem();
                String Cat = Main.basedatos.obtenerId(datosCat);
                try{
                    listaArticulos = Main.basedatos.realizarBusqueda(Prov, Cat);
                    if(listaArticulos.size()==0){
                        tDes.setVisible(false);
                        tProv.setVisible(false);
                        tCat.setVisible(false);
                        tUn.setVisible(false);
                        tPC.setVisible(false);
                        tPV.setVisible(false);
                        anteriorButton.setVisible(false);
                        siguienteButton.setVisible(false);
                        mensaje.setVisible(true);
                    }else{
                        String [] datos = Main.basedatos.separarDatos(listaArticulos.get(currentArt));
                        tDes.setVisible(true);
                        tDes.setText(datos[0]);
                        tProv.setVisible(true);
                        tProv.setText(datos[1]);
                        tCat.setVisible(true);
                        tCat.setText(datos[2]);
                        tUn.setVisible(true);
                        tUn.setText(datos[3]);
                        tPC.setVisible(true);
                        tPC.setText(datos[4]);
                        tPV.setVisible(true);
                        tPV.setText(datos[5]);
                        mensaje.setVisible(false);
                        anteriorButton.setVisible(true);
                        anteriorButton.setEnabled(false);
                        siguienteButton.setVisible(true);
                        if (currentArt == listaArticulos.size() - 1) {
                            siguienteButton.setEnabled(false);
                        } else {
                            siguienteButton.setEnabled(true);
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        //function for SIGUIENTE button
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentArt++;
                String[] datos = Main.basedatos.separarDatos(listaArticulos.get(currentArt));
                tDes.setText(datos[0]);
                tProv.setText(datos[1]);
                tCat.setText(datos[2]);
                tUn.setText(datos[3]);
                tPC.setText(datos[4]);
                tPV.setText(datos[5]);
                if(currentArt==listaArticulos.size()-1){
                    siguienteButton.setEnabled(false);
                    anteriorButton.setEnabled(false);
                }
                anteriorButton.setVisible(true);
                anteriorButton.setEnabled(true);
            }
        });

        //function for ANTERIOR button
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentArt--;
                String[] datos = Main.basedatos.separarDatos(listaArticulos.get(currentArt));
                tDes.setText(datos[0]);
                tProv.setText(datos[1]);
                tCat.setText(datos[2]);
                tUn.setText(datos[3]);
                tPC.setText(datos[4]);
                tPV.setText(datos[5]);
                if(currentArt==0){
                    anteriorButton.setEnabled(false);
                    siguienteButton.setEnabled(true);
                }
                siguienteButton.setVisible(true);
                siguienteButton.setEnabled(true);
            }
        });

        //function for button to come back to the MENU

        menúPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal();
            }
        });
    }
}
