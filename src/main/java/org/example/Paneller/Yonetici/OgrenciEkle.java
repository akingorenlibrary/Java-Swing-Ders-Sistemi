package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OgrenciEkle extends JFrame{
    private JTextField ogrenciNoInput;
    private JTextField kullaniciAdiInput;
    private JTextField sifreInput;
    private JTextField adInput;
    private JTextField soyadInput;
    private JTextArea ilgiAlanariInput;
    private JButton ekleButton;
    private JButton geriButton;
    private JPanel panel1;

    public OgrenciEkle(){

        geriButton.setBackground(Color.lightGray);
        ekleButton.setBackground(Color.lightGray);

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                boolean kontrol=sorgular.ogrenciEkle(ogrenciNoInput.getText(), kullaniciAdiInput.getText(), sifreInput.getText(), adInput.getText(), soyadInput.getText(), ilgiAlanariInput.getText());
                if(kontrol){
                    JOptionPane.showMessageDialog(null, "Eklendi");
                    ogrenciNoInput.setText("");
                    kullaniciAdiInput.setText("");
                    sifreInput.setText("");
                    adInput.setText("");
                    soyadInput.setText("");
                    ilgiAlanariInput.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Hata oluştu");
                }
            }
        });

        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        YoneticiPaneli yoneticiPaneli=new YoneticiPaneli();
                        yoneticiPaneli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        yoneticiPaneli.setVisible(true);
                    }
                });
            }
        });


        add(panel1);
        setSize(800,600);
        setTitle("Yönetici Paneli");
    }
}
