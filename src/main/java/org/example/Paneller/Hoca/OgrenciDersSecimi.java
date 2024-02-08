package org.example.Paneller.Hoca;

import org.example.Kisiler.Ders;
import org.example.Kisiler.Hoca;
import org.example.Paneller.Ogrenci.OgrenciPanel;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciDersSecimi extends JFrame {
    private JPanel panel1=new JPanel();
    private ArrayList<JLabel> ogrenciHocaDersAdi = new ArrayList<>();
    private ArrayList<JButton> butonlar = new ArrayList<>();
    private ArrayList<Ders> hocaninVerdigiDersler=new ArrayList<Ders>();
    private ArrayList<Hoca> ogrenciHocaDersListesi=new ArrayList<>();
    private ArrayList<Hoca> ogrenciHocaDersListesiFarkliHocaAyniDers=new ArrayList<>();

    ArrayList<Hoca> hocalardanOgrencilereTalepler=new ArrayList<>();
    private boolean filtreleButon=false;
    public OgrenciDersSecimi(){}

    public void paneliCiz(Hoca hoca){
        add(panel1);
        setSize(1200,700);
        setTitle("Öğrenci Ders Seçimi");

        JButton geriButton=new JButton("Geri");
        JButton filtrele=new JButton("Filtrele");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");

        geriButton.setBackground(Color.lightGray);
        filtrele.setBackground(Color.lightGray);
        bosluk1.setBackground(Color.lightGray);
        bosluk2.setBackground(Color.lightGray);
        bosluk3.setBackground(Color.lightGray);

        Sorgular sorgular=new Sorgular();

        if(filtreleButon==false){
            ogrenciHocaDersListesi=sorgular.hocaPaneliOgrenciDersTalepleri(hoca.getId());
            hocalardanOgrencilereTalepler=sorgular.hocalardanOgrencilereTaleplerinListesi(hoca.getId());

            for(Hoca hocaOgrenciDers:ogrenciHocaDersListesi){
                for(Hoca h2:sorgular.hocaPaneliOgrenciDersTalepleriFarkliHocaAyniDers(hoca.getId(),hocaOgrenciDers.getAcilanDers().getAdi())){
                    ogrenciHocaDersListesiFarkliHocaAyniDers.add(h2);
                }
            }

            for (Hoca h:ogrenciHocaDersListesiFarkliHocaAyniDers){
                ogrenciHocaDersListesi.add(h);
            }
        }else{
            ogrenciHocaDersListesi=sorgular.hocaPaneliOgrenciDersTalepleriFiltreli(hoca.getId());
            hocalardanOgrencilereTalepler=sorgular.hocalardanOgrencilereTaleplerinListesi(hoca.getId());

            for(Hoca hocaOgrenciDers:ogrenciHocaDersListesi){
                for(Hoca h2:sorgular.hocaPaneliOgrenciDersTalepleriFarkliHocaAyniDers(hoca.getId(),hocaOgrenciDers.getAcilanDers().getAdi())){
                    ogrenciHocaDersListesiFarkliHocaAyniDers.add(h2);
                }
            }

            for (Hoca h:ogrenciHocaDersListesiFarkliHocaAyniDers){
                ogrenciHocaDersListesi.add(h);
            }
        }




        panel1.setLayout(new GridLayout(ogrenciHocaDersListesi.size()+2,2));
        panel1.add(geriButton);
        panel1.add(filtrele);
        panel1.add(bosluk2);
        panel1.add(bosluk3);


        for (int i = 0; i < ogrenciHocaDersListesi.size(); i++) {
            String metin="Hoca Bilgisi: "+ogrenciHocaDersListesi.get(i).getAd()+" "+ogrenciHocaDersListesi.get(i).getSoyad()+" | Ders Bilgisi: "+ogrenciHocaDersListesi.get(i).getAcilanDers().getAdi()+" | Kontenjan: "+ogrenciHocaDersListesi.get(i).getAcilanDers().getKontenjanBilgisi()+" | Öğrenci No: "+ogrenciHocaDersListesi.get(i).getOgrenci().getOgrenciNo();
            JButton button =new JButton();

            if(ogrenciHocaDersListesi.get(i).getAcilanDers().isHocaOnayDurum()){
                button.setText("Talep Kabul Edildi");
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
            }else{
                button.setText("Talebi Kabul Et");
                button.setBackground(Color.lightGray);
                button.setForeground(Color.BLACK);
            }

            for (Hoca ho:ogrenciHocaDersListesiFarkliHocaAyniDers){
                if(ho.getOgrenci().getId().equals(ogrenciHocaDersListesi.get(i).getOgrenci().getId()) && ho.getAcilanDers().getId().equals(ogrenciHocaDersListesi.get(i).getAcilanDers().getId()) && ho.getId().equals(ogrenciHocaDersListesi.get(i).getId())){
                    button.setText("Talep Gönder");
                    button.setBackground(Color.lightGray);
                    button.setForeground(Color.BLACK);
                }
            }

            for(Hoca hoca1:hocalardanOgrencilereTalepler){
                if(hoca1.getOgrenci().getId().equals(ogrenciHocaDersListesi.get(i).getOgrenci().getId()) && hoca1.getAcilanDers().getId().equals(ogrenciHocaDersListesi.get(i).getAcilanDers().getId())){
                    button.setText("Talebi İptal Et");
                    button.setBackground(Color.GRAY);
                    button.setForeground(Color.WHITE);
                }
            }

            JLabel label = new JLabel(metin);

            ogrenciHocaDersAdi.add(label);
            butonlar.add(button);

            panel1.add(label);
            panel1.add(button);
        }

        if(ogrenciHocaDersListesi.isEmpty()){
            JLabel uyari=new JLabel("Listelenecek ders istediği bulunamadı");
            JLabel bosluk4=new JLabel("");
            panel1.add(uyari);
            panel1.add(bosluk4);
        }


        for (int i = 0; i < butonlar.size(); i++) {
            int finalI = i;
            butonlar.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    butonlar.get(finalI).setEnabled(false);
                    if(butonlar.get(finalI).getText().equals("Talebi Kabul Et")){
                        if(sorgular.dersKontenjanBilgisiGetir(ogrenciHocaDersListesi.get(finalI).getAcilanDers().getId())>0){
                            if(sorgular.ogrenciDersTalepKabulEt(ogrenciHocaDersListesi.get(finalI).getAcilanDers().getId(), ogrenciHocaDersListesi.get(finalI).getOgrenci().getId())==false){
                                JOptionPane.showMessageDialog(null, "Hata oluştu");
                            }else{
                                butonlar.get(finalI).setText("Talep Kabul Edildi");
                                butonlar.get(finalI).setBackground(Color.GRAY);
                                butonlar.get(finalI).setForeground(Color.WHITE);
                                paneliYenile(hoca);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Yeterli kontenjan yok");
                        }

                    }else if(butonlar.get(finalI).getText().equals("Talep Gönder")){
                        boolean kontrol=sorgular.hocadanOgrenciyeDersTalebiGonder(hoca.getId(), ogrenciHocaDersListesi.get(finalI).getOgrenci().getId(), ogrenciHocaDersListesi.get(finalI).getAcilanDers().getId());
                        if(kontrol){
                            butonlar.get(finalI).setText("Talebi İptal Et");
                            butonlar.get(finalI).setBackground(Color.GRAY);
                            butonlar.get(finalI).setForeground(Color.WHITE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Hata oluştu");
                        }
                    }else if(butonlar.get(finalI).getText().equals("Talebi İptal Et")){
                        boolean kontrol=sorgular.hocadanOgrenciyeDersTalebiSil(hoca.getId(), ogrenciHocaDersListesi.get(finalI).getOgrenci().getId(), ogrenciHocaDersListesi.get(finalI).getAcilanDers().getId());
                        if(kontrol){
                            butonlar.get(finalI).setText("Talep Gönder");
                            butonlar.get(finalI).setBackground(Color.lightGray);
                            butonlar.get(finalI).setForeground(Color.BLACK);
                            paneliYenile(hoca);
                        }else{
                            JOptionPane.showMessageDialog(null, "Hata oluştu");
                        }
                    }
                    butonlar.get(finalI).setEnabled(true);
                }
            });
        }

        //Geri butonuna basıldığında
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


        filtrele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Genel Not ortalamasına göre sırala"};

                int secim = JOptionPane.showOptionDialog(
                        null,
                        "Yapmak istediğiniz işlemi seçin",
                        "Filtrele",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if(secim==0){
                    filtreleButon=true;
                    paneliYenile(hoca);
                }
            }
        });
    }

    public void paneliYenile(Hoca hoca){
        ogrenciHocaDersAdi.clear();
        ogrenciHocaDersListesi.clear();
        butonlar.clear();

        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        paneliCiz(hoca);
    }

}
