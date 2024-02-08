package org.example.Paneller.Hoca.HocaMesajlar;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HocaOgrenciyeMesajGonder extends JFrame{
    private JTextArea mesajKutusu;
    private JButton geriButton;
    private JButton gonderButton;
    private JPanel panel1;
    private JTextField ogrenciNo;

    public HocaOgrenciyeMesajGonder(Hoca hoca
    ) {
        geriButton.setBackground(Color.lightGray);
        gonderButton.setBackground(Color.lightGray);

        Sorgular sorgular=new Sorgular();

        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Long ogrenciId=sorgular.ogrenciNoIleOgrenciBul(ogrenciNo.getText());
                if(mesajKutusu.getText().trim().length()!=0 && ogrenciNo.getText().trim().length()!=0){
                    if(ogrenciId!=null){
                        if(sorgular.mesajGonder(hoca.getId(), ogrenciId, mesajKutusu.getText())){
                            JOptionPane.showMessageDialog(null, "Mesaj Gönderildi");
                            mesajKutusu.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Hata Oluştu");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Böyle bir öğrenci yok");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                }
            }
        });

        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaMesajlarPanel hocaMesajlarPanel=new HocaMesajlarPanel(hoca);
                        hocaMesajlarPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaMesajlarPanel.setVisible(true);
                    }
                });
            }
        });

        add(panel1);
        setSize(800, 600);
        setTitle("Mesaj Gönder");
    }
}
