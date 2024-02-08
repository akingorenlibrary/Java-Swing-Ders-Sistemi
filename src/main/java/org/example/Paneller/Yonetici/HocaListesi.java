package org.example.Paneller.Yonetici;

import org.example.Kisiler.Ders;
import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Yonetici.YoneticiPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HocaListesi extends JFrame{
    private JPanel panel1;

    private ArrayList<Hoca> hocalar;

    public HocaListesi() {
        JButton geri = new JButton("Geri");
        panel1 = new JPanel(new BorderLayout());

        hocalar = new ArrayList<>();
        Sorgular sorgular = new Sorgular();
        hocalar = sorgular.hocalariGetir();
        Object[] baslik = {"Sicil Numarası","Kullanıcı Adı", "Şifre","Ad", "Soyad", "İlgi Alanları"};

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(baslik);

        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);

        for (Hoca hoca : hocalar) {
            tableModel.addRow(new Object[]{hoca.getSicilNumarasi(), hoca.getKullaniciAdi(), hoca.getSifre(), hoca.getAd(), hoca.getSoyad(), hoca.getIlgiAlanlari()});
        }
        table1.setModel(tableModel);

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
        setTitle("Hoca Listesi");
        setVisible(true);
    }
}
