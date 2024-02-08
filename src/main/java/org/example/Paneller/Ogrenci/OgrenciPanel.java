package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class OgrenciPanel extends JFrame {
    private JPanel panel1;
    private JButton alinanDerslerButton;
    private JButton dersSecimiButton;
    private JButton mesajlarimButton;
    private JButton hocalardanGelenDersTalepleriButton;

    public OgrenciPanel(Ogrenci ogrenci){

        dersSecimiButton.setBackground(Color.lightGray);
        alinanDerslerButton.setBackground(Color.lightGray);
        hocalardanGelenDersTalepleriButton.setBackground(Color.lightGray);
        mesajlarimButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800,600);
        setTitle("Öğrenci Panel");

        alinanDerslerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AlinanDersler alinanDersler=new AlinanDersler(ogrenci);
                        alinanDersler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        alinanDersler.setVisible(true);
                    }
                });
            }
        });

        dersSecimiButton.addActionListener(new ActionListener() {
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
                            DersSecimi dersSecimi=new DersSecimi();
                            dersSecimi.PaneliCiz(ogrenci);
                            dersSecimi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            dersSecimi.setVisible(true);
                        }
                    });
                }else{
                    JOptionPane.showMessageDialog(null, "Ders seçim tarihlerini takip ediniz.");
                }
            }
        });

        hocalardanGelenDersTalepleriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocalardanGelenDersTaleperi hocalardanGelenDersTaleperi=new HocalardanGelenDersTaleperi(ogrenci);
                        hocalardanGelenDersTaleperi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocalardanGelenDersTaleperi.setVisible(true);
                    }
                });
            }
        });

        mesajlarimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Gelen Mesajlarım","Mesaj Gönder"};

                int secim = JOptionPane.showOptionDialog(
                        null,
                        "Yapmak istediğiniz işlemi seçin:",
                        "Mesaj",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                String secimMetni=null;
                if (secim == 0) {
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciGelenMesajlarim ogrenciGelenMesajlarim=new OgrenciGelenMesajlarim(ogrenci);
                            ogrenciGelenMesajlarim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciGelenMesajlarim.setVisible(true);
                        }
                    });
                }
                else if (secim == 1) {
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciHocayaMesajGonder ogrenciHocayaMesajGonder=new OgrenciHocayaMesajGonder(ogrenci);
                            ogrenciHocayaMesajGonder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciHocayaMesajGonder.setVisible(true);
                        }
                    });
                }
            }
        });
    }
}
