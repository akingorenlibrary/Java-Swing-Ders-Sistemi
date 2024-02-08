package org.example.Paneller.Hoca;

import org.example.Kisiler.Ders;
import org.example.Kisiler.Hoca;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciYeniAldigiDersler extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton geriButton;

    public OgrenciYeniAldigiDersler(Hoca hoca, Long ogrenciId){

        Sorgular sorgular=new Sorgular();
        ArrayList<Hoca> yeniAlinanDersler = sorgular.ogrencininYeniAldigiDersler(ogrenciId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ders Adı");
        model.addColumn("Ders Kredi");
        model.addColumn("Hoca Adı");
        model.addColumn("Hoca Soyadı");

        for (Hoca dersvehoca : yeniAlinanDersler) {
            model.addRow(new Object[]{dersvehoca.getAcilanDers().getAdi(), dersvehoca.getAcilanDers().getKredi(), dersvehoca.getAd(), dersvehoca.getSoyad()});
        }
        table1.setModel(model);

        geriButton.setBackground(Color.lightGray);

        geriButton.addActionListener(new ActionListener() {
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

        add(panel1);
        setSize(800,600);
        setTitle("Öğrencinin Yeni Aldığı Dersler");
    }
}
