package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DersSecimTarihleriBelirle extends JFrame{
    private JTextField baslangicTarihi;
    private JTextField bitisTarihi;
    private JButton kaydetButton;
    private JPanel panel1;
    private JButton geriButton;

    public DersSecimTarihleriBelirle(){

        geriButton.setBackground(Color.lightGray);
        kaydetButton.setBackground(Color.lightGray);

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date baslangic = dateFormat.parse(baslangicTarihi.getText());
                    Date bitis = dateFormat.parse(bitisTarihi.getText());

                    if(sorgular.dersSecimTarihleriniBelirle(baslangic, bitis)){
                        JOptionPane.showMessageDialog(null, "Kaydedildi");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(panel1);
        setSize(800,600);
        setTitle("Ders Seçim Tarihleri Belirleme");

        geriButton.addActionListener(new ActionListener() {
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
    }
}
