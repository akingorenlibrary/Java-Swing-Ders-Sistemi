package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HocaDersAtama extends JFrame{
    private JTextField hocaKullaniciAdiInput;
    private JTextField kontenjanBilgisiInput;
    private JButton ekleButton;
    private JPanel panel1;
    private JTextField dersInput;
    private JButton geriButton;
    private JTextField dersKrediInput;

    public HocaDersAtama() {

        ekleButton.setBackground(Color.lightGray);
        geriButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800, 600);
        setTitle("Hoca Ders Atama");
        setVisible(true);

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hocaKullaniciAdiInput.getText().trim().length()!=0 && dersInput.getText().trim().length()!=0 && kontenjanBilgisiInput.getText().trim().length()!=0 && dersKrediInput.getText().trim().length()!=0){
                    Sorgular sorgular=new Sorgular();
                    boolean durum=sorgular.hocaDersAtama(hocaKullaniciAdiInput.getText(), dersInput.getText(), kontenjanBilgisiInput.getText(), Integer.parseInt(dersKrediInput.getText()));
                    if(durum){
                        JOptionPane.showMessageDialog(HocaDersAtama.this, "Eklendi");
                        hocaKullaniciAdiInput.setText("");
                        dersInput.setText("");
                        kontenjanBilgisiInput.setText("");
                        dersKrediInput.setText("");
                    }else{
                        JOptionPane.showMessageDialog(HocaDersAtama.this, "Hata oluştu.");
                    }
                }else{
                    JOptionPane.showMessageDialog(HocaDersAtama.this, "Boş bırakmayın");
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
