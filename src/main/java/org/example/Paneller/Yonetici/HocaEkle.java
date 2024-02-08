package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HocaEkle extends JFrame{
    private JTextField adInput;
    private JTextField soyadInput;
    private JTextField sicilNumarasiInput;
    private JTextField ilgiAlanlariInput;
    private JButton ekleButton;
    private JPanel panel1;
    private JButton geriButton;
    private JTextField kullaniciAdiInput;
    private JTextField sifreInput;

    HocaEkle(){
        ekleButton.setBackground(Color.lightGray);
        geriButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800, 600);
        setTitle("Hoca Ekle");
        setVisible(true);


        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                if((adInput.getText().trim().length()!=0 && soyadInput.getText().trim().length()!=0 && sicilNumarasiInput.getText().trim().length()!=0 && ilgiAlanlariInput.getText().trim().length()!=0 && kullaniciAdiInput.getText().trim().length()!=0 && sifreInput.getText().trim().length()!=0)){
                    boolean durum=sorgular.hocaEkle(adInput.getText(), soyadInput.getText(), sicilNumarasiInput.getText(), ilgiAlanlariInput.getText(), kullaniciAdiInput.getText(), sifreInput.getText());

                    if(durum){
                        JOptionPane.showMessageDialog(HocaEkle.this, "Eklendi");
                        adInput.setText("");
                        soyadInput.setText("");
                        sicilNumarasiInput.setText("");
                        ilgiAlanlariInput.setText("");
                        kullaniciAdiInput.setText("");
                        sifreInput.setText("");
                    }else{
                        JOptionPane.showMessageDialog(HocaEkle.this, "Hata oluştu.");
                    }
                }else{
                    JOptionPane.showMessageDialog(HocaEkle.this, "Boş bırakmayın");
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
                        YoneticiPaneli yoneticiPaneli=new YoneticiPaneli();
                        yoneticiPaneli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        yoneticiPaneli.setVisible(true);
                    }
                });
            }
        });
    }
}
