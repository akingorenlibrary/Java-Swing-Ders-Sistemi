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

public class OgrencininAldigiDersler extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton geriButton;

    public OgrencininAldigiDersler(Hoca hoca, Long ogrenciId){

        Sorgular sorgular=new Sorgular();
        ArrayList<Ders> alinanDersler = sorgular.ogrencininAldigiDersler(ogrenciId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ders Adı");
        model.addColumn("Ders Notu");
        ArrayList<Ders> dersler = sorgular.ogrencininAldigiDersler(ogrenciId);
        for (Ders ders : dersler) {
            model.addRow(new Object[]{ders.getAdi(), ders.getNotu()});
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
        setTitle("Öğrencinin Aldığı Dersler");
    }
}
