package org.example.Paneller.Yonetici;

import org.example.Kisiler.Hoca;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AcilanDersler extends JFrame{
    private JPanel panel1;
    ArrayList<Hoca> acilanDersler=new ArrayList<Hoca>();
    public AcilanDersler(){
        JButton geri = new JButton("Geri");
        panel1 = new JPanel(new BorderLayout());


        Sorgular sorgular = new Sorgular();
        acilanDersler = sorgular.acilanDersler();
        Object[] baslik = {"Ders Adı","Kredi","Kontenjan Bilgisi", "Hoca Adı","Hoca Soyadı"};

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(baslik);

        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);

        for (Hoca hocaDersBilgisi : acilanDersler) {
            tableModel.addRow(new Object[]{hocaDersBilgisi.getAcilanDers().getAdi(),hocaDersBilgisi.getAcilanDers().getKredi(), hocaDersBilgisi.getAcilanDers().getKontenjanBilgisi(), hocaDersBilgisi.getAd(), hocaDersBilgisi.getSoyad()});
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
