package org.example.Kisiler;

import javax.swing.*;

public class Ogrenci extends Kullanici {
    public String ogrenciNo;
    public String kullanciAdi;
    public String sifre;
    public String ad;
    public String soyad;
    public String ilgiAlanlari;
    public int anlasmaTalepSayisi;
    public String anlasmaDurumu;
    public boolean notDurumBelgesi;
    public float genelNotOrtalamasi;

    public Ogrenci(Long id, String kullanciAdi,String sifre, String ogrenciNo, String ad, String soyad, String ilgiAlanlari, int anlasmaTalepSayisi, String anlasmaDurumu, boolean notDurumBelgesi, float genelNotOrtalamasi) {
        super(id);
        this.ogrenciNo = ogrenciNo;
        this.ad = ad;
        this.soyad = soyad;
        this.sifre=sifre;
        this.kullanciAdi=kullanciAdi;
        this.ilgiAlanlari = ilgiAlanlari;
        this.anlasmaTalepSayisi = anlasmaTalepSayisi;
        this.anlasmaDurumu = anlasmaDurumu;
        this.notDurumBelgesi = notDurumBelgesi;
        this.genelNotOrtalamasi = genelNotOrtalamasi;
    }
    public Ogrenci() {}
    public String getOgrenciNo() {
        return ogrenciNo;
    }

    public void setOgrenciNo(String ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }

    public String getKullanciAdi() {
        return kullanciAdi;
    }

    public void setKullanciAdi(String kullanciAdi) {
        this.kullanciAdi = kullanciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getIlgiAlanlari() {
        return ilgiAlanlari;
    }

    public void setIlgiAlanlari(String ilgiAlanlari) {
        this.ilgiAlanlari = ilgiAlanlari;
    }

    public int getAnlasmaTalepSayisi() {
        return anlasmaTalepSayisi;
    }

    public void setAnlasmaTalepSayisi(int anlasmaTalepSayisi) {
        this.anlasmaTalepSayisi = anlasmaTalepSayisi;
    }

    public String getAnlasmaDurumu() {
        return anlasmaDurumu;
    }

    public void setAnlasmaDurumu(String anlasmaDurumu) {
        this.anlasmaDurumu = anlasmaDurumu;
    }

    public boolean getNotDurumBelgesi() {
        return notDurumBelgesi;
    }

    public void setNotDurumBelgesi(boolean notDurumBelgesi) {
        this.notDurumBelgesi = notDurumBelgesi;
    }

    public float getGenelNotOrtalamasi() {
        return genelNotOrtalamasi;
    }

    public void setGenelNotOrtalamasi(float genelNotOrtalamasi) {
        this.genelNotOrtalamasi = genelNotOrtalamasi;
    }
}
