package org.example.Paneller.Hoca;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciListesi extends JFrame {
    ArrayList<Ogrenci> ogrenciler=new ArrayList<>();
    ArrayList<JButton> butonlar=new ArrayList<JButton>();
    ArrayList<JButton> butonlar2=new ArrayList<JButton>();
    ArrayList<JLabel> metinler=new ArrayList<>();
    private JPanel panel1=new JPanel();
    public OgrenciListesi(Hoca hoca){
        Sorgular sorgular=new Sorgular();
        ogrenciler=sorgular.ogrencileriGetir();

        panel1.setLayout(new GridLayout(ogrenciler.size()+1,3));
        JButton geri=new JButton("Geri");
        geri.setBackground(Color.lightGray);
        JLabel bosluk=new JLabel("");
        JLabel bosluk2=new JLabel("");

        panel1.add(geri);
        panel1.add(bosluk);
        panel1.add(bosluk2);


        for(Ogrenci ogrenci:ogrenciler){
            JButton b=new JButton("Aldığı dersleri göster");
            JButton s=new JButton("Yeni aldığı dersleri göster");
            JLabel l=new JLabel(ogrenci.getOgrenciNo()+" "+ogrenci.getAd()+" "+ogrenci.getSoyad());

            b.setBackground(Color.lightGray);
            s.setBackground(Color.lightGray);

            metinler.add(l);
            butonlar.add(b);
            butonlar2.add(s);

            panel1.add(l);
            panel1.add(b);
            panel1.add(s);
        }

        add(panel1);
        setSize(800,600);
        setTitle("Öğrenci Listesi");

        for (int a=0; a<butonlar.size(); a++){
            int finalA = a;
            butonlar.get(a).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrencininAldigiDersler ogrencininAldigiDersler=new OgrencininAldigiDersler(hoca, ogrenciler.get(finalA).getId());
                            ogrencininAldigiDersler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrencininAldigiDersler.setVisible(true);
                        }
                    });
                }
            });
        }

        for (int a=0; a<butonlar2.size(); a++){
            int finalA = a;
            butonlar2.get(a).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciYeniAldigiDersler ogrenciYeniAldigiDersler=new OgrenciYeniAldigiDersler(hoca, ogrenciler.get(finalA).getId());
                            ogrenciYeniAldigiDersler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciYeniAldigiDersler.setVisible(true);
                        }
                    });
                }
            });
        }

        geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaPaneli hocaPaneli=new HocaPaneli(hoca);
                        hocaPaneli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaPaneli.setVisible(true);
                    }
                });
            }
        });
    }
}
