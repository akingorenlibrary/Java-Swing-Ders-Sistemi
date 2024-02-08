package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class HocalardanGelenDersTaleperi extends JFrame{
    private JPanel panel1=new JPanel();
    ArrayList<Hoca> hocaDersListesi=new ArrayList<Hoca>();
    ArrayList<JLabel> metinler=new ArrayList<>();
    ArrayList<JButton> butonlar=new ArrayList<>();
    public HocalardanGelenDersTaleperi(Ogrenci ogrenci){

        Sorgular sorgular=new Sorgular();
        hocaDersListesi=sorgular.hocalardanGelenTalepler(ogrenci.getId());

        JButton geriButon=new JButton("Geri");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");

        panel1.setLayout(new GridLayout(hocaDersListesi.size()+2,2));

        panel1.add(geriButon);
        panel1.add(bosluk1);
        panel1.add(bosluk2);
        panel1.add(bosluk3);

        geriButon.setBackground(Color.lightGray);

        if(hocaDersListesi.isEmpty()){
            JLabel bildirimLabel=new JLabel();
            bildirimLabel.setText("Listelenecek talep yok.");
            panel1.add(bildirimLabel);
        }

        for(Hoca hocaDersliste:hocaDersListesi){
            JLabel label=new JLabel();
            JButton buton=new JButton();

            label.setText(hocaDersliste.getAd()+" "+hocaDersliste.getSoyad()+" | "+hocaDersliste.getAcilanDers().getAdi()+" | "+hocaDersliste.getAcilanDers().getKontenjanBilgisi());
            buton.setText("Talebi Kabul Et");
            buton.setBackground(Color.lightGray);

            metinler.add(label);
            butonlar.add(buton);

            panel1.add(label);
            panel1.add(buton);
        }

        for (int i = 0; i < butonlar.size(); i++) {
            int finalI = i;
            butonlar.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(butonlar.get(finalI).getText().equals("Talebi Kabul Et")){

                        if(sorgular.dersKontenjanBilgisiGetir(hocaDersListesi.get(finalI).getAcilanDers().getId())>0){
                            boolean kontrol=sorgular.hocaninOgrenciyeGonderdigiDersTalebiniSilDerslerineEkle(hocaDersListesi.get(finalI).getId(), ogrenci.getId(), hocaDersListesi.get(finalI).getAcilanDers().getId());
                            if(!kontrol){
                                JOptionPane.showMessageDialog(null, "Hata oluştu");
                            }
                            butonlar.get(finalI).setText("Talep Kabul Edildi");
                            butonlar.get(finalI).setBackground(Color.GRAY);
                            butonlar.get(finalI).setForeground(Color.WHITE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Yeterli Kontenjan yok");
                        }
                    }
                }
            });
        }

        geriButon.addActionListener(new ActionListener() {
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


        add(panel1);
        setSize(800, 600);
        setTitle("Hocalardan Gelen Ders Taleperi");
    }
}
