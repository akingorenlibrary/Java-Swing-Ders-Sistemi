package org.example.Paneller.Yonetici;

import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Yonetici.YoneticiPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciListesi extends JFrame {
    private JPanel panel1;
    private ArrayList<Ogrenci> ogrenciler;

    public OgrenciListesi() {
        JButton geri = new JButton("Geri");
        panel1 = new JPanel(new BorderLayout());

        ogrenciler = new ArrayList<>();
        Sorgular sorgular = new Sorgular();
        ogrenciler = sorgular.ogrencileriGetir();
        Object[] baslik = {"Öğrenci No","Kullanıcı Adı", "Şifre","Ad", "Soyad", "İlgi Alanları", "Not Durum Belgesi", "Genel Not Ortalaması"};
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(baslik);

        for (Ogrenci ogr : ogrenciler) {
            Object[] data = {ogr.getOgrenciNo(),ogr.getKullanciAdi(), ogr.getSifre(), ogr.getAd(), ogr.getSoyad(), ogr.getIlgiAlanlari(), ogr.getNotDurumBelgesi(), ogr.getGenelNotOrtalamasi()};
            tableModel.addRow(data);
        }

        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);

        geri.addActionListener(new ActionListener() {
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

        panel1.add(scrollPane, BorderLayout.CENTER);
        geri.setBackground(Color.lightGray);
        panel1.add(geri, BorderLayout.NORTH);
        add(panel1);
        setSize(1000, 800);
        setTitle("Öğrenci Paneli");
        setVisible(true);
    }

}
