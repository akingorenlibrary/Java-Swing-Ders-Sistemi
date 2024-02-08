package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OgrenciHocayaMesajGonder extends JFrame{
    private JTextField hocaKullaniciAdi;
    private JTextArea mesajKutusu;
    private JButton gonderButton;
    private JButton geriButton;
    private JPanel panel1;

    public OgrenciHocayaMesajGonder(Ogrenci ogrenci) {

        gonderButton.setBackground(Color.lightGray);
        geriButton.setBackground(Color.lightGray);

        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                Long hocaId=sorgular.hocaGetirKullaniciAdiIle(hocaKullaniciAdi.getText());

                if(mesajKutusu.getText().trim().length()!=0 && hocaKullaniciAdi.getText().trim().length()!=0){
                    if(hocaId!=null){
                        if(sorgular.mesajGonder(ogrenci.getId(), hocaId, mesajKutusu.getText())){
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
                        OgrenciPanel ogrenciPanel = new OgrenciPanel(ogrenci);
                        ogrenciPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenciPanel.setVisible(true);
                    }
                });
            }
        });

        add(panel1);
        setSize(800,600);
        setTitle("Mesaj Gönder");
    }
}
