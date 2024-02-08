package org.example.Paneller.Ogrenci;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.Kisiler.Ders;
import org.example.Kisiler.Ogrenci;
import org.example.Veritabani.Sorgular;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class TranskriptYukleme extends JFrame{
    private JButton dosyaSecButton;
    private JButton yukleButton;
    private JLabel dosyaLabel;
    private JPanel panel1;
    private JButton devamEtButton;

    public TranskriptYukleme(Ogrenci ogrenci){

        dosyaSecButton.setBackground(Color.lightGray);
        yukleButton.setBackground(Color.lightGray);
        devamEtButton.setBackground(Color.lightGray);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileFilter pdfFilter = new FileNameExtensionFilter("PDF Dosyaları", "pdf");
        fileChooser.addChoosableFileFilter(pdfFilter);
        devamEtButton.setEnabled(false);
        yukleButton.setEnabled(false);

        dosyaSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File secilenDosya = fileChooser.getSelectedFile();
                    if (secilenDosya.getName().toLowerCase().endsWith(".pdf")) {
                        yukleButton.setEnabled(true);
                        dosyaLabel.setText("Seçilen Dosya: " + secilenDosya.getAbsolutePath());
                    } else {
                        JOptionPane.showMessageDialog(null, "Lütfen PDF dosyası seçin!");
                    }
                }
            }
        });

        yukleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Ders> dersler=new ArrayList<>();

                try {
                    File secilenDosya = fileChooser.getSelectedFile();
                    FileInputStream fileInputStream=new FileInputStream(secilenDosya);
                    PDDocument dokuman = PDDocument.load(fileInputStream);
                    PDFTextStripper textStr = new PDFTextStripper();
                    String pdfText = textStr.getText(dokuman);
                    dokuman.close();

                    String[] metin = pdfText.split("\\r?\\n");
                    for (String m : metin) {
                        Ders ders=new Ders();
                        for (int i = 0; i < m.length(); i++) {
                            String not=m.substring(i,i+2);
                            if(not.equals("AA")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("BA")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("BB")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("CB")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("CC")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("DC")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("DD")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("FF")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                            }else if(not.equals("FD")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }else if(not.equals("FG")){
                                ders.setAdi(m.substring(0,i-1).trim());
                                ders.setNotu(not);
                                dersler.add(ders);
                                break;
                            }
                        }
                    }
                } catch (Exception ex) {

                }

                Sorgular sorgular=new Sorgular();
                for(Ders d:dersler){
                    boolean sorguKontrol=sorgular.transkript_ders_ekleme(ogrenci.getId(), d.getNotu(), d.getAdi());
                    if(sorguKontrol==false){
                        JOptionPane.showMessageDialog(null, d.getAdi()+" dersi eklenirken hata oluştu");
                        break;
                    }
                }

                boolean durum=sorgular.ogrenciGenelNotOrtalamasiGuncelle(ogrenci.getId(), sorgular.ogrenciGenelNotOrtalamasiHesaplama(dersler));
                if(!durum){
                    JOptionPane.showMessageDialog(null, "Genel not ortalaması eklenirken hata oluştu");
                }

                sorgular.notDurumBelgesiniGuncelle(ogrenci.getId());
                JOptionPane.showMessageDialog(null, "Dosya yüklendi!");
                yukleButton.setText("Yüklendi");
                yukleButton.setEnabled(false);
                devamEtButton.setEnabled(true);
                dosyaSecButton.setEnabled(false);
            }
        });


        add(panel1);
        setSize(800,600);
        setTitle("Transkript Yükleme Paneli");


        devamEtButton.addActionListener(new ActionListener() {
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
    }
}
