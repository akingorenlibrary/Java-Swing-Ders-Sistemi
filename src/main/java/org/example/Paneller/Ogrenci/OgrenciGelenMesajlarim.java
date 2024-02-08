package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Kullanici;
import org.example.Kisiler.Mesaj;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Hoca.HocaMesajlar.HocaGelenMesajlarOku;
import org.example.Paneller.Hoca.HocaMesajlar.HocaMesajlarPanel;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciGelenMesajlarim extends JFrame {
    private JPanel panel1=new JPanel();
    ArrayList<Mesaj> gelenMesajlar=new ArrayList<>();
    ArrayList<Kullanici> kullanicilar=new ArrayList<>();
    ArrayList<Hoca> hocalar=new ArrayList<>();
    ArrayList<JLabel> metinler=new ArrayList<>();
    ArrayList<JButton> butonlar=new ArrayList<>();
    public OgrenciGelenMesajlarim(Ogrenci ogrenci) {

        Sorgular sorgular = new Sorgular();
        gelenMesajlar = sorgular.ogrenciyeGelenMesajlar(ogrenci.getId());

        JButton geriButton = new JButton("Geri");
        JLabel bosluk1 = new JLabel("");
        JLabel bosluk2 = new JLabel("");
        JLabel bosluk3 = new JLabel("");
        JLabel bilgiLabel = new JLabel("Listelenecek mesaj bulunamadı");
        JLabel bosluk4 = new JLabel("");

        //veritabanindaki tüm mesajlar kategorilerinin gonderenId ye göre
        boolean durum = false;
        for (Mesaj mesaj : gelenMesajlar) {
            durum = false;
            for (Kullanici k : kullanicilar) {
                if (k.getId().equals(mesaj.getGonderen_id())) {
                    durum = true;
                    break;
                }
            }

            if (durum == false) {
                Kullanici kullanici = new Kullanici();
                kullanici.setId(mesaj.getGonderen_id());
                kullanicilar.add(kullanici);
            }
        }

        //mesaj atan kullanıcların detaylı bilgisi alinir
        for (Kullanici k : kullanicilar) {
            hocalar.add(sorgular.hocaGetir(k.getId()));
        }


        panel1.setLayout(new GridLayout(hocalar.size() + 2, 2));

        panel1.add(geriButton);
        panel1.add(bosluk1);
        panel1.add(bosluk2);
        panel1.add(bosluk3);

        geriButton.setBackground(Color.lightGray);

        for (Hoca hoca : hocalar) {
            JLabel label = new JLabel(hoca.getAd() + " "+ hoca.getSoyad());
            JButton button = new JButton("Mesaj Oku");
            button.setBackground(Color.lightGray);

            metinler.add(label);
            butonlar.add(button);

            panel1.add(label);
            panel1.add(button);
        }

        if(hocalar.isEmpty()){
            panel1.add(bilgiLabel);
            panel1.add(bosluk4);
        }


        for (int i = 0; i < butonlar.size(); i++) {
            int finalI = i;
            butonlar.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciGelenMesajlariOku ogrenciGelenMesajlariOku = new OgrenciGelenMesajlariOku(ogrenci, hocalar.get(finalI).getId());
                            ogrenciGelenMesajlariOku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciGelenMesajlariOku.setVisible(true);
                        }
                    });
                }
            });
        }

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
        setSize(800, 600);
        setTitle("Gelen Mesajlar");
    }
}
