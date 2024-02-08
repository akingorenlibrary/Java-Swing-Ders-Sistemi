package org.example.Paneller.Hoca.HocaMesajlar;

import org.example.Kisiler.Hoca;
import org.example.Paneller.Hoca.HocaPaneli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HocaMesajlarPanel extends JFrame{
    private JButton gelenMesajlarButton;
    private JButton mesajGonderButon;
    private JPanel panel1;
    private JButton geriButton;

    public HocaMesajlarPanel(Hoca hoca) {
        gelenMesajlarButton.setBackground(Color.lightGray);
        mesajGonderButon.setBackground(Color.lightGray);
        geriButton.setBackground(Color.lightGray);

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

        gelenMesajlarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaGelenMesajlar hocaGelenMesajlar=new HocaGelenMesajlar(hoca);
                        hocaGelenMesajlar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaGelenMesajlar.setVisible(true);
                    }
                });
            }
        });


        add(panel1);
        setSize(800, 600);
        setTitle("Mesajlar");

        mesajGonderButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HocaOgrenciyeMesajGonder hocaOgrenciyeMesajGonder=new HocaOgrenciyeMesajGonder(hoca);
                        hocaOgrenciyeMesajGonder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hocaOgrenciyeMesajGonder.setVisible(true);
                    }
                });
            }
        });


    }
}
