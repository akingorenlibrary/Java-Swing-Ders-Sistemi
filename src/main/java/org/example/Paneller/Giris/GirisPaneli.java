package org.example.Paneller.Giris;

import org.example.Paneller.Giris.GirisForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GirisPaneli extends JFrame{
    private JButton yoneticiPaneliButton;
    private JButton hocaPaneliButton;
    private JButton ogrenciPaneliButton;
    private JPanel panel1;
    public GirisPaneli(){

        yoneticiPaneliButton.setBackground(Color.lightGray);
        hocaPaneliButton.setBackground(Color.lightGray);
        ogrenciPaneliButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800,600);
        setTitle("Giriş");

        hocaPaneliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        yoneticiPaneliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pencereyi kapat
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GirisForm girisForm = new GirisForm();
                        girisForm.kullanici="yönetici";
                        girisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        girisForm.setVisible(true);
                    }
                });
            }
        });

        ogrenciPaneliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pencereyi kapat
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GirisForm girisForm = new GirisForm();
                        girisForm.kullanici="öğrenci";
                        girisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        girisForm.setVisible(true);
                    }
                });
            }
        });

        hocaPaneliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pencereyi kapat
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GirisForm girisForm = new GirisForm();
                        girisForm.kullanici="hoca";
                        girisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        girisForm.setVisible(true);
                    }
                });
            }
        });
    }
}
