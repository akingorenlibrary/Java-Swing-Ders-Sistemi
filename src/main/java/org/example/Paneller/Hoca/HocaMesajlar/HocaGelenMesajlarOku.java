package org.example.Paneller.Hoca.HocaMesajlar;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Mesaj;
import org.example.Paneller.Ogrenci.OgrenciPanel;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HocaGelenMesajlarOku extends JFrame{
    private JPanel panel1=new JPanel();
    ArrayList<Mesaj> ogrencininHocayaAttigiMesajlar=new ArrayList<>();
    public  HocaGelenMesajlarOku(Hoca hoca, Long ogrenciId){

        Sorgular sorgular=new Sorgular();
        ogrencininHocayaAttigiMesajlar=sorgular.ogrencininHocayaAttigiMesajlariGetir(hoca.getId(), ogrenciId);

        JButton geriButon=new JButton("Geri");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");
        geriButon.setBackground(Color.lightGray);

        panel1.setLayout(new GridLayout(ogrencininHocayaAttigiMesajlar.size()+2,2));
        panel1.add(geriButon);
        panel1.add(bosluk3);
        panel1.add(bosluk1);
        panel1.add(bosluk2);

        for (int i = 0; i < ogrencininHocayaAttigiMesajlar.size(); i++) {
            JLabel mesaj=new JLabel();
            JLabel tarih=new JLabel();

            mesaj.setText(ogrencininHocayaAttigiMesajlar.get(i).getMesaj());
            tarih.setText(ogrencininHocayaAttigiMesajlar.get(i).getTarih().getDayOfMonth()+"/"+ogrencininHocayaAttigiMesajlar.get(i).getTarih().getMonthValue()+"/"+ogrencininHocayaAttigiMesajlar.get(i).getTarih().getYear()+" "+ogrencininHocayaAttigiMesajlar.get(i).getTarih().getHour()+":"+ogrencininHocayaAttigiMesajlar.get(i).getTarih().getMinute()+":"+ogrencininHocayaAttigiMesajlar.get(i).getTarih().getSecond());

            panel1.add(mesaj);
            panel1.add(tarih);
        }


        geriButon.addActionListener(new ActionListener() {
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
        setTitle("Gelen Mesajlar Oku");
    }
}
