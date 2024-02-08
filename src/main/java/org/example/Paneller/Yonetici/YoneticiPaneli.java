package org.example.Paneller.Yonetici;

import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YoneticiPaneli extends JFrame{
    private JButton ogrenciListesiButton;
    private JPanel panel1;
    private JButton hocaListesiButton;
    private JButton hocaEkleButton;
    private JButton hocaSilButton;
    private JButton ogrenciEkleButton;
    private JButton ogrenciSilButton;
    private JButton hocaDersAtamaButton;
    private JButton acilanDerslerButton;
    private JButton dersSecimTarihleriBelirleButton;
    private JButton kalanOgrencileriDerslereAtamaButton;
    private JButton otomatikOgrenciOlusturmaButton;
    private JButton ogrenciGuncelleButton;
    private JButton hocaGuncelleButton;

    public YoneticiPaneli(){

        ogrenciListesiButton.setBackground(Color.lightGray);
        hocaEkleButton.setBackground(Color.lightGray);
        hocaSilButton.setBackground(Color.lightGray);
        hocaListesiButton.setBackground(Color.lightGray);
        ogrenciEkleButton.setBackground(Color.lightGray);
        ogrenciSilButton.setBackground(Color.lightGray);
        hocaDersAtamaButton.setBackground(Color.lightGray);
        dersSecimTarihleriBelirleButton.setBackground(Color.lightGray);
        acilanDerslerButton.setBackground(Color.lightGray);
        otomatikOgrenciOlusturmaButton.setBackground(Color.lightGray);
        kalanOgrencileriDerslereAtamaButton.setBackground(Color.lightGray);
        ogrenciGuncelleButton.setBackground(Color.lightGray);
        hocaGuncelleButton.setBackground(Color.lightGray);

        add(panel1);
        setSize(800,600);
        setTitle("Yönetici Paneli");

        ogrenciListesiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pencereyi kapat
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        OgrenciListesi ogrenci = new OgrenciListesi();
                        ogrenci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenci.setVisible(true);
                    }
                });
            }
        });

        hocaListesiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pencereyi kapat
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaListesi hocaListesi = new HocaListesi();
                        hocaListesi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaListesi.setVisible(true);
                    }
                });
            }
        });


        hocaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaEkle hocaEkle=new HocaEkle();
                        hocaEkle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaEkle.setVisible(true);
                    }
                });
            }
        });

        hocaDersAtamaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaDersAtama hocaDersAtama=new HocaDersAtama();
                        hocaDersAtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaDersAtama.setVisible(true);
                    }
                });
            }
        });

        dersSecimTarihleriBelirleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        DersSecimTarihleriBelirle dersSecimTarihleriBelirle=new DersSecimTarihleriBelirle();
                        dersSecimTarihleriBelirle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        dersSecimTarihleriBelirle.setVisible(true);
                    }
                });
            }
        });

        acilanDerslerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AcilanDersler acilanDersler=new AcilanDersler();
                        acilanDersler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        acilanDersler.setVisible(true);
                    }
                });
            }
        });

        otomatikOgrenciOlusturmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorgular sorgular=new Sorgular();
                String ogrenciSayisi=JOptionPane.showInputDialog(null,"Kaç öğrenci oluşturulsun");
                boolean kontrol=false;
                if(ogrenciSayisi != null && ogrenciSayisi.toString().trim().length()!=0){
                    kontrol=sorgular.ogrenciUret(Integer.parseInt(ogrenciSayisi));
                }
                if(kontrol){
                    JOptionPane.showMessageDialog(null, ogrenciSayisi+" öğrenci oluşturuldu");
                }else{
                    JOptionPane.showMessageDialog(null, "Hata oluştu");
                }
            }
        });

        ogrenciEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        OgrenciEkle ogrenciEkle=new OgrenciEkle();
                        ogrenciEkle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenciEkle.setVisible(true);
                    }
                });
            }
        });

        ogrenciSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogrenciNo=JOptionPane.showInputDialog(null,"Silmek istediğiniz öğrencinin numarasını girin");
                if(ogrenciNo != null && ogrenciNo.toString().trim().length()!=0){
                    Sorgular sorgular=new Sorgular();
                    boolean kontrol=sorgular.ogrenciNoIleOgrenciSil(ogrenciNo);
                    if(kontrol){
                        JOptionPane.showMessageDialog(null, "Silindi");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                }
            }
        });

        hocaSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi=JOptionPane.showInputDialog(null,"Silmek istediğiniz hocanın kullanıcı adını girin");
                if(kullaniciAdi != null && kullaniciAdi.toString().trim().length()!=0){
                    Sorgular sorgular=new Sorgular();
                    boolean kontrol=sorgular.kullaniciAdiIleHocaSil(kullaniciAdi);
                    if(kontrol){
                        JOptionPane.showMessageDialog(null, "Silindi");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                }
            }
        });

        ogrenciGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogrenciNo=JOptionPane.showInputDialog(null,"Günncellemek istediğiniz öğrencinin numarasını girin");
                if(ogrenciNo != null && ogrenciNo.toString().trim().length()!=0){
                    String[] options = {"Numarası", "Kullanıcı Adı", "Şifre", "Ad", "Soyad", "İlgi Alanları", "Genel Not Ortalaması"};

                    int secim = JOptionPane.showOptionDialog(
                            null,
                            "Yapmak istediğiniz işlemi seçin:",
                            "Güncelleme İşlemi",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    String secimMetni=null;
                    if (secim == 0) {
                        secimMetni="ogrenci_no";
                    } else if (secim == 1) {
                        secimMetni="kullanici_adi";
                    } else if (secim == 2) {
                        secimMetni="sifre";
                    } else if (secim == 3) {
                        secimMetni="ad";
                    }else if (secim == 4) {
                        secimMetni="soyad";
                    }else if (secim == 5) {
                        secimMetni="ilgi_alanlari";
                    }else if (secim == 6) {
                        secimMetni="genel_not_ortalamasi";
                    }

                    String guncelVeri=JOptionPane.showInputDialog(null,"Yenisini girin");
                    if(guncelVeri != null && guncelVeri.trim().length()!=0){
                        Sorgular sorgular=new Sorgular();
                        boolean kontrol=sorgular.ogrenciGuncelle(String.valueOf(secimMetni), ogrenciNo, guncelVeri);
                        if(kontrol){
                            JOptionPane.showMessageDialog(null, "Güncellendi");
                        }else{
                            JOptionPane.showMessageDialog(null, "Hata oluştu");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                }
            }
        });

        hocaGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String kullaniciAdi=JOptionPane.showInputDialog(null,"Günncellemek istediğiniz hocanın kullanıcı adını girin");
                if(kullaniciAdi != null && kullaniciAdi.toString().trim().length()!=0){
                    String[] options = {"Sicil Numarası", "Kullanıcı Adı", "Şifre", "Ad", "Soyad", "İlgi Alanları"};

                    int secim = JOptionPane.showOptionDialog(
                            null,
                            "Yapmak istediğiniz işlemi seçin:",
                            "Güncelleme İşlemi",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    String secimMetni=null;
                    if (secim == 0) {
                        secimMetni="sicil_numarasi";
                    } else if (secim == 1) {
                        secimMetni="kullanici_adi";
                    } else if (secim == 2) {
                        secimMetni="sifre";
                    } else if (secim == 3) {
                        secimMetni="ad";
                    }else if (secim == 4) {
                        secimMetni="soyad";
                    }else if (secim == 5) {
                        secimMetni="ilgi_alanlari";
                    }

                    String guncelVeri=JOptionPane.showInputDialog(null,"Yenisini girin");
                    if(guncelVeri != null && guncelVeri.trim().length()!=0){
                        Sorgular sorgular=new Sorgular();
                        boolean kontrol=sorgular.hocaGuncelle(String.valueOf(secimMetni), kullaniciAdi, guncelVeri);
                        if(kontrol){
                            JOptionPane.showMessageDialog(null, "Güncellendi");
                        }else{
                            JOptionPane.showMessageDialog(null, "Hata oluştu");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Boş bırakmayın");
                }
            }
        });

        kalanOgrencileriDerslereAtamaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Rastgele Atama", "Not ortalamasına göre atama", "Belirli derslere göre atama"};

                int secim = JOptionPane.showOptionDialog(
                        null,
                        "Yapmak istediğiniz işlemi seçin",
                        "Güncelleme İşlemi",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                Sorgular sorgular=new Sorgular();
                if(secim==0){
                    boolean kontrol=sorgular.ogrencileriRastgeleDerslereAtama();
                    if(kontrol){
                        JOptionPane.showMessageDialog(null, "Başarılı");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                }else if(secim==1){
                    boolean kontrol=sorgular.ogrencilerNotOrtalamasinaGoreAtama();
                    if(kontrol){
                        JOptionPane.showMessageDialog(null, "Başarılı");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                }else if(secim==2){
                    dispose();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            OgrenciDersAtama ogrenciDersAtama=new OgrenciDersAtama();
                            ogrenciDersAtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ogrenciDersAtama.setVisible(true);
                        }
                    });
                }
            }
        });
    }
}
