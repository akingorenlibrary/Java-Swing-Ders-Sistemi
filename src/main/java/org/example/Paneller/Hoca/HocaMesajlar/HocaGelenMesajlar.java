package org.example.Paneller.Hoca.HocaMesajlar;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Kullanici;
import org.example.Kisiler.Mesaj;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Hoca.HocaPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HocaGelenMesajlar extends JFrame{
    private JPanel panel1=new JPanel();
    ArrayList<Mesaj> gelenMesajlar=new ArrayList<>();
    ArrayList<Kullanici> kullanicilar=new ArrayList<>();
    ArrayList<Ogrenci> ogrenciler=new ArrayList<>();
    ArrayList<JLabel> metinler=new ArrayList<>();
    ArrayList<JButton> butonlar=new ArrayList<>();
    public HocaGelenMesajlar(Hoca hoca){

        Sorgular sorgular=new Sorgular();
        gelenMesajlar=sorgular.hocayaGelenMesajlar(hoca.getId());

        JButton geriButton=new JButton("Geri");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");

        //veritabanindaki tüm mesajlar kategorilerinin gonderenId ye göre
        boolean durum=false;
        for(Mesaj mesaj:gelenMesajlar){
            durum=false;
            for (Kullanici k:kullanicilar){
                if(k.getId().equals(mesaj.getGonderen_id())){
                    durum=true;
                    break;
                }
            }

            if(durum==false){
                Kullanici kullanici=new Kullanici();
                kullanici.setId(mesaj.getGonderen_id());
                kullanicilar.add(kullanici);
            }
        }

        //mesaj atan kullanıcların detaylı bilgisi alinir
        for (Kullanici k:kullanicilar){
            ogrenciler.add(sorgular.ogrenciGetir(k.getId()));
        }


        panel1.setLayout(new GridLayout(ogrenciler.size()+2,2));

        panel1.add(geriButton);
        panel1.add(bosluk1);
        panel1.add(bosluk2);
        panel1.add(bosluk3);

        geriButton.setBackground(Color.lightGray);

        for(Ogrenci ogrenci:ogrenciler){
            JLabel label=new JLabel(ogrenci.getOgrenciNo()+" "+ogrenci.getAd()+" "+ogrenci.getSoyad());
            JButton button=new JButton("Mesaj Oku");
            button.setBackground(Color.lightGray);

            metinler.add(label);
            butonlar.add(button);

            panel1.add(label);
            panel1.add(button);
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
                            HocaGelenMesajlarOku hocaGelenMesajlarOku=new HocaGelenMesajlarOku(hoca, ogrenciler.get(finalI).getId());
                            hocaGelenMesajlarOku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            hocaGelenMesajlarOku.setVisible(true);
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
                        HocaMesajlarPanel hocaMesajlarPanel=new HocaMesajlarPanel(hoca);
                        hocaMesajlarPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaMesajlarPanel.setVisible(true);
                    }
                });
            }
        });

        add(panel1);
        setSize(800, 600);
        setTitle("Gelen Mesajlar");
    }
}
