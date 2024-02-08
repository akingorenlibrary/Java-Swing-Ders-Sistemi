package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OgrenciGuncelle extends JFrame {

    private JTextField eskiBilgiInput;
    private JTextField yeniBilgiInput;
    private JButton guncelleButton;
    private JButton geriButton;
    private JLabel eskiBilgiLabel;
    private JLabel yeniBilgiLabel;
    private JPanel panel1=new JPanel();

    public OgrenciGuncelle(String secim, String ogrenciNo, String guncelVeri){

        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                boolean kontrol=sorgular.ogrenciGuncelle(secim, ogrenciNo, guncelVeri);
                if(kontrol){
                    JOptionPane.showMessageDialog(null, "Güncellendi");
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
    }
}
