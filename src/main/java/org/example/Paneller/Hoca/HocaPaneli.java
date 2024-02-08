package org.example.Paneller.Hoca;

import org.example.Kisiler.Hoca;
import org.example.Paneller.Hoca.HocaMesajlar.HocaMesajlarPanel;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class HocaPaneli extends JFrame{
    private JButton mesajlarButton;
    private JButton ogrenciDersSecimiButton;
    private JPanel panel1;
    private JButton ogrenciListesiButton;

    public HocaPaneli(Hoca hoca) {

        mesajlarButton.setBackground(Color.lightGray);
        ogrenciDersSecimiButton.setBackground(Color.lightGray);
        ogrenciListesiButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800,600);
        setTitle("Hoca Paneli");

        ogrenciDersSecimiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<LocalDate> tarihler=new ArrayList<>();
                Sorgular sorgular=new Sorgular();
                tarihler=sorgular.dersSecimTarihleriniGetir();
                LocalDate simdikiTarih=LocalDate.now();
                if(( simdikiTarih.isAfter(tarihler.get(0)) || simdikiTarih.equals(tarihler.get(0)) ) && (simdikiTarih.isBefore(tarihler.get(1)) || simdikiTarih.equals(tarihler.get(1)) )){
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciDersSecimi ogrenciDersSecimi=new OgrenciDersSecimi();
                            ogrenciDersSecimi.paneliCiz(hoca);
                            ogrenciDersSecimi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciDersSecimi.setVisible(true);
                        }
                    });
                }else{
                    JOptionPane.showMessageDialog(null, "Ders seçim tarihlerini takip ediniz.");
                }
            }

        });

        mesajlarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaMesajlarPanel mesajlarPanel=new HocaMesajlarPanel(hoca);
                        mesajlarPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mesajlarPanel.setVisible(true);
                    }
                });
            }
        });

        ogrenciListesiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        OgrenciListesi ogrenciListesi=new OgrenciListesi(hoca);
                        ogrenciListesi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenciListesi.setVisible(true);
                    }
                });
            }
        });
    }
}
