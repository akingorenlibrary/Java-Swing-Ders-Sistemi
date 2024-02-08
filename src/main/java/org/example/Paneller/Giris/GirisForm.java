package org.example.Paneller.Giris;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Hoca.HocaPaneli;
import org.example.Paneller.Ogrenci.OgrenciPanel;
import org.example.Paneller.Ogrenci.TranskriptYukleme;
import org.example.Paneller.Yonetici.YoneticiPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GirisForm extends JFrame{
    private JTextField KullaniciAdiInput;
    private JTextField sifreInput;
    private JButton girisYapButton;
    private JPanel panel1;
    private JButton geri;
    public String kullanici;
    public GirisForm(){
        girisYapButton.setBackground(Color.lightGray);
        geri.setBackground(Color.lightGray);
        add(panel1);
        setSize(800,600);
        setTitle("Giriş");

        girisYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                boolean kontrol=false;

                if(kullanici.equals("yönetici")){
                    kontrol=sorgular.yoneticiGirisKontrol(KullaniciAdiInput.getText(), sifreInput.getText());

                    if(kontrol){
                        dispose();
                        YoneticiPaneli yoneticiPaneli=new YoneticiPaneli();
                        yoneticiPaneli.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Eposta veya şifre hatalı.");
                    }
                }
                else if(kullanici.equals("öğrenci")){
                    Ogrenci ogrenci=new Ogrenci();
                    ogrenci=sorgular.ogrenciGirisKontrol(KullaniciAdiInput.getText(), sifreInput.getText());
                    if(ogrenci.getId() != null){

                        if(ogrenci.notDurumBelgesi){
                            //pencereyi kapat
                            dispose();

                            Ogrenci finalOgrenci = ogrenci;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    OgrenciPanel ogrenciPanel = new OgrenciPanel(finalOgrenci);
                                    ogrenciPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    ogrenciPanel.setVisible(true);
                                }
                            });
                        }else{
                            //pencereyi kapat
                            dispose();

                            Ogrenci finalOgrenci = ogrenci;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    TranskriptYukleme transkriptYukleme = new TranskriptYukleme(finalOgrenci);
                                    transkriptYukleme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    transkriptYukleme.setVisible(true);
                                }
                            });
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Eposta veya şifre hatalı.");
                    }
                }
                else if(kullanici.equals("hoca")){
                    Hoca hoca=new Hoca();
                    hoca=sorgular.hocaGirisKontrol(KullaniciAdiInput.getText(), sifreInput.getText());

                    if(hoca.getId()!=null){
                        //pencereyi kapat
                        dispose();

                        Hoca finalHoca = hoca;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                HocaPaneli hocaPaneli = new HocaPaneli(finalHoca);
                                hocaPaneli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                hocaPaneli.setVisible(true);
                            }
                        });

                    }else{
                        JOptionPane.showMessageDialog(null, "Eposta veya şifre hatalı.");
                    }
                }
            }
        });

        geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GirisPaneli girisPaneli=new GirisPaneli();
                        girisPaneli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        girisPaneli.setVisible(true);
                    }
                });
            }
        });

    }
}
