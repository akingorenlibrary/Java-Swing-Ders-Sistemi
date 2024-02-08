package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Ders;
import org.example.Kisiler.Hoca;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Yonetici.YoneticiPaneli;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class DersSecimi extends JFrame {
    private ArrayList<JLabel> hocalarveDersAdlari = new ArrayList<>();
    private ArrayList<JButton> dersEkleCikarButton = new ArrayList<>();
    ArrayList<Hoca> hocaListesi=new ArrayList<>();
    private boolean filtrelemeButonDurum=false;
    ArrayList<Hoca> filtrelenmisHocaveDersListesi=new ArrayList<>();
    JPanel panel1= new JPanel();
    Sorgular sorgular=new Sorgular();

    ArrayList<Ders> ogrencininSectigiDersler=new ArrayList<>();
    public DersSecimi(){}
    public void PaneliCiz(Ogrenci ogrenci){

        JButton geriButton=new JButton("Geri");
        JButton filtrele=new JButton("Filtrele");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        hocalarveDersAdlari.clear();
        dersEkleCikarButton.clear();

        geriButton.setBackground(Color.lightGray);
        filtrele.setBackground(Color.lightGray);

        if(filtrelemeButonDurum==false){
            veritabanindanHocalariveDersleriCek();
        }else{
            hocaListesi=filtrelenmisHocaveDersListesi;
            filtrele.setText("Filtreyi Kaldır");
            filtrele.setBackground(Color.GRAY);
            filtrele.setForeground(Color.WHITE);
        }
        ogrencininSectigiDersler=sorgular.ogrencininSectigiDersler(ogrenci.getId());

        panel1.setLayout(new GridLayout(hocaListesi.size()+2,2));
        panel1.add(geriButton);
        panel1.add(filtrele);
        panel1.add(bosluk1);
        panel1.add(bosluk2);

        //Hoca ders ve kontenjan bilgilerini alip labellere yerlestirme
        for (int i = 0; i < hocaListesi.size(); i++) {
            String metin=hocaListesi.get(i).getAd()+" "+hocaListesi.get(i).getSoyad()+" | "+hocaListesi.get(i).getAcilanDers().getAdi()+" | "+hocaListesi.get(i).getAcilanDers().getKontenjanBilgisi();
            JLabel label = new JLabel(metin);
            JButton button=new JButton();

            boolean durum=false;
            for(Ders d:ogrencininSectigiDersler){
                if(d.getId().equals(hocaListesi.get(i).getAcilanDers().getId())){
                    if(d.isHocaOnayDurum()){
                        button.setText("Ders Onaylandı");
                        button.setBackground(Color.GRAY);
                        button.setForeground(Color.WHITE);
                    }else{
                        button.setText("Talepi İptal Et");
                        button.setBackground(Color.GRAY);
                        button.setForeground(Color.WHITE);
                    }
                    durum=true;
                }
            }

            if(durum==false){
                button.setText("Talep Gönder");
                button.setBackground(Color.lightGray);
            }

            hocalarveDersAdlari.add(label);
            dersEkleCikarButton.add(button);

            panel1.add(label);
            panel1.add(button);
        }

            //Hoca ders ve kontenjan bilgilerine tiklandiginda pencere acilmasi
            for (int i = 0; i < hocaListesi.size(); i++) {
                String hocaAdi = hocaListesi.get(i).getAd();
                int finalI = i;
                ArrayList<Hoca> finalHocaListesi2 = hocaListesi;
                hocalarveDersAdlari.get(i).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Object[] secenekler = {"İlgi Alanları", "Hocaya Mesaj Gönder", "İptal"};
                        String hocaIlgiAlanlari= finalHocaListesi2.get(finalI).getIlgiAlanlari();
                        int secim = JOptionPane.showOptionDialog(null,hocaAdi, "Seçenekler",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, secenekler, secenekler[2]);

                        if(secim==0){
                            JOptionPane.showMessageDialog(null, "İlgi Alanları: " +hocaIlgiAlanlari);
                        }else if(secim==1){
                            String mesaj=JOptionPane.showInputDialog(null,hocaAdi+" "+"kişisine mesaj gönder");
                            if(mesaj.trim().length()!=0){
                                if(sorgular.mesajGonder(ogrenci.getId(), finalHocaListesi2.get(finalI).getId(), mesaj)){
                                    JOptionPane.showMessageDialog(null, "Mesaj gönderildi");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Mesaj gönderilemedi");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                            }
                        }
                    }
                });
            }

            //talep gonder butonuna basildiginda
            for (int x = 0; x < dersEkleCikarButton.size(); x++) {
                int index=x;
                ArrayList<Hoca> finalHocaListesi = hocaListesi;
                dersEkleCikarButton.get(x).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dersEkleCikarButton.get(index).setEnabled(false);
                        if(dersEkleCikarButton.get(index).getText().equals("Talep Gönder")){

                            if(!(sorgular.ogrencininSectigiDersleriEkle(finalHocaListesi.get(index), ogrenci.getId()))){
                                JOptionPane.showMessageDialog(null, "Onay gönderilirken hata oluştu");
                            }

                            dersEkleCikarButton.get(index).setText("Talepi İptal Et");
                            dersEkleCikarButton.get(index).setForeground(Color.white);
                            dersEkleCikarButton.get(index).setBackground(Color.gray);
                        }else if(dersEkleCikarButton.get(index).getText().equals("Talepi İptal Et")){

                            if(sorgular.ogrencininSectigiDersiSil(ogrenci.getId(),hocaListesi.get(index))){
                                JOptionPane.showMessageDialog(null, "Talep iptal edildi");
                                dersEkleCikarButton.get(index).setText("Talep Gönder");
                                dersEkleCikarButton.get(index).setBackground(Color.lightGray);
                                dersEkleCikarButton.get(index).setForeground(Color.BLACK);
                            }else{
                                JOptionPane.showMessageDialog(null, "Onay kaldırılırken hata oluştu");
                            }
                        }
                        dersEkleCikarButton.get(index).setEnabled(true);
                    }
                });
            }

            //Filtrele butonuna basildiginda
            ArrayList<Hoca> finalHocaListesi4= hocaListesi;
            filtrele.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filtrele.setEnabled(false);
                    if(filtrele.getText().equals("Filtrele")){
                        filtrelenmisHocaveDersListesi.clear();
                        String metin=JOptionPane.showInputDialog(null,"İlgi alanınızı giriniz");
                        if(metin != null && metin.trim().length()!=0){
                            for(int i=0;i<finalHocaListesi4.size();i++){
                                if(finalHocaListesi4.get(i).getIlgiAlanlari().toLowerCase().trim().contains(metin.toLowerCase().trim())==true){
                                    filtrelenmisHocaveDersListesi.add(finalHocaListesi4.get(i));
                                }
                            }
                            filtrele.setText("Filtrelendi");
                            filtrele.setBackground(Color.GRAY);
                            filtrele.setForeground(Color.WHITE);
                            filtrelemeButonDurum=true;
                            panelYenile(ogrenci);
                        }else{
                            JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                        }
                    }else if(filtrele.getText().equals("Filtreyi Kaldır")){
                        filtrele.setText("Filtrele");
                        filtrele.setBackground(Color.LIGHT_GRAY);
                        filtrele.setForeground(Color.BLACK);
                        filtrelemeButonDurum=false;
                        panelYenile(ogrenci);
                    }
                    filtrele.setEnabled(true);
                }
            });

            geriButton.addActionListener(new ActionListener() {
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
            setTitle("Ders Seçimi");
    }

    public void panelYenile(Ogrenci ogrenci){
        hocalarveDersAdlari.clear();
        dersEkleCikarButton.clear();

        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        PaneliCiz(ogrenci);
    }

    public void veritabanindanHocalariveDersleriCek(){
        Sorgular sorgular=new Sorgular();
        hocaListesi=sorgular.ogrenciDersSecimiAcilanDerslerListesi();
    }

}
