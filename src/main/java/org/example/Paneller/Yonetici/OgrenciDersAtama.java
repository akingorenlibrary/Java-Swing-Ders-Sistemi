package org.example.Paneller.Yonetici;

import org.example.Kisiler.Hoca;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciDersAtama extends JFrame{
    private JPanel panel1=new JPanel();

    public OgrenciDersAtama() {
        ArrayList<Hoca> acilanDersler=new ArrayList<Hoca>();
        Sorgular sorgular=new Sorgular();
        acilanDersler=sorgular.acilanDersler();

        DefaultListModel<String> listeModeli = new DefaultListModel<>();
        for (Hoca hoca : acilanDersler) {
            listeModeli.addElement(hoca.getAcilanDers().getAdi()+" | "+hoca.getAcilanDers().getKontenjanBilgisi()+" | "+hoca.getAd()+" "+hoca.getSoyad());
        }

        JList acilanDerslerInput = new JList<>(listeModeli);

        JScrollPane scrollPane = new JScrollPane(acilanDerslerInput);

        JLabel ogrenciNoLabel=new JLabel("Öğrenci No: ");
        JTextField ogrenciNotTextField= new JTextField();
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");
        JLabel bosluk4=new JLabel("");
        JLabel bosluk5=new JLabel("");
        JLabel bosluk6=new JLabel("");
        JLabel bosluk7=new JLabel("");
        JLabel bosluk8=new JLabel("");
        JLabel bosluk9=new JLabel("");
        JLabel acilanDerslerLabel=new JLabel("Açılan Dersler: ");
        JButton ogrenciyiDerseEkleButon=new JButton("Öğrenciyi derse ekle");
        JButton geriButton=new JButton("Geri");

        ogrenciyiDerseEkleButon.setBackground(Color.lightGray);
        geriButton.setBackground(Color.lightGray);

        panel1.setLayout(new GridLayout(5,3));

        panel1.add(ogrenciNoLabel);
        panel1.add(ogrenciNotTextField);
        panel1.add(bosluk1);

        panel1.add(acilanDerslerLabel);
        panel1.add(scrollPane);
        panel1.add(bosluk2);

        panel1.add(ogrenciyiDerseEkleButon);
        panel1.add(bosluk3);
        panel1.add(bosluk4);

        panel1.add(geriButton);
        panel1.add(bosluk5);
        panel1.add(bosluk6);

        panel1.add(bosluk7);
        panel1.add(bosluk8);
        panel1.add(bosluk9);


        ArrayList<Hoca> finalAcilanDersler = acilanDersler;
        ogrenciyiDerseEkleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long ogrenciId=sorgular.ogrenciNoIleOgrenciBul(ogrenciNotTextField.getText());
                int dersKontenjan=sorgular.dersKontenjanBilgisiGetir(finalAcilanDersler.get(acilanDerslerInput.getSelectedIndex()).getAcilanDers().getId());
                System.out.println("finalAcilanDersler.get(acilanDerslerInput.getSelectedIndex()).getAcilanDers().getId(): "+finalAcilanDersler.get(acilanDerslerInput.getSelectedIndex()).getAcilanDers().getId());
                if(dersKontenjan>0){
                    boolean kontrol=sorgular.ogrenciyiDerseEkle(finalAcilanDersler.get(acilanDerslerInput.getSelectedIndex()).getAcilanDers().getId(), ogrenciId);
                    if(kontrol){
                        JOptionPane.showMessageDialog(null, "Eklendi");
                        ogrenciNotTextField.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hata oluştu");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Yeterli kontenjan yok");
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

        add(panel1);
        setSize(800, 600);
        setTitle("Öğrenciyi Derse Ekle");
        setVisible(true);
    }
}
