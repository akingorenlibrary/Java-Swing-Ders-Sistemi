package org.example.Paneller.Ogrenci;

import org.example.Kisiler.Hoca;
import org.example.Kisiler.Mesaj;
import org.example.Kisiler.Ogrenci;
import org.example.Paneller.Hoca.HocaMesajlar.HocaMesajlarPanel;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OgrenciGelenMesajlariOku extends JFrame{
    private JPanel panel1=new JPanel();
    ArrayList<Mesaj> hocaninOgrenciyeAttigiMesajlar=new ArrayList<>();
    public  OgrenciGelenMesajlariOku(Ogrenci ogrenci, Long hocaId){

        Sorgular sorgular=new Sorgular();
        hocaninOgrenciyeAttigiMesajlar=sorgular.hocaninOgrenciyeAttigiMesajlariGetir(hocaId, ogrenci.getId());

        JButton geriButon=new JButton("Geri");
        JLabel bosluk1=new JLabel("");
        JLabel bosluk2=new JLabel("");
        JLabel bosluk3=new JLabel("");
        geriButon.setBackground(Color.lightGray);

        panel1.setLayout(new GridLayout(hocaninOgrenciyeAttigiMesajlar.size()+2,2));
        panel1.add(geriButon);
        panel1.add(bosluk3);
        panel1.add(bosluk1);
        panel1.add(bosluk2);

        for (int i = 0; i < hocaninOgrenciyeAttigiMesajlar.size(); i++) {
            JLabel mesaj=new JLabel();
            JLabel tarih=new JLabel();

            mesaj.setText(hocaninOgrenciyeAttigiMesajlar.get(i).getMesaj());
            tarih.setText(hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getDayOfMonth()+"/"+hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getMonthValue()+"/"+hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getYear()+" "+hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getHour()+":"+hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getMinute()+":"+hocaninOgrenciyeAttigiMesajlar.get(i).getTarih().getSecond());

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
                        OgrenciGelenMesajlarim ogrenciGelenMesajlarim=new OgrenciGelenMesajlarim(ogrenci);
                        ogrenciGelenMesajlarim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ogrenciGelenMesajlarim.setVisible(true);
                    }
                });
            }
        });

        add(panel1);
        setSize(800, 600);
        setTitle("Gelen Mesajlar Oku");
    }
}
