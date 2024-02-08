package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Ders;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Yonetici.YoneticiPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlinanDersler extends  JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton geriButton;

    public AlinanDersler(Ogrenci ogrenci){

        geriButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800,600);
        setTitle("Alınan Dersler");

        Sorgular sorgular=new Sorgular();
        ArrayList<Ders> alinanDersler = sorgular.ogrencininAldigiDersler(ogrenci.getId());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ders Adı");
        model.addColumn("Ders Notu");
        ArrayList<Ders> dersler = sorgular.ogrencininAldigiDersler(ogrenci.getId());
        for (Ders ders : dersler) {
            model.addRow(new Object[]{ders.getAdi(), ders.getNotu()});
        }
        table1.setModel(model);

        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        OgrenciPanel ogrenciPanel=new OgrenciPanel(ogrenci);
                        ogrenciPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenciPanel.setVisible(true);
                    }
                });
            }
        });
    }
}
