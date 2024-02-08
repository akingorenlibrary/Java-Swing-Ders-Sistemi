package org.example.Kisiler;

import java.util.ArrayList;

public class Hoca extends Kullanici{
    private String sicilNumarasi;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;
    private String ilgiAlanlari;
    private ArrayList<Ders> kriterDersler;
    public Ogrenci ogrenci;
    private Ders acilanDers;
    public Hoca(){
        kriterDersler = new ArrayList<>();
    }

    public Hoca(String ad){
        this.ad=ad;
    }

    public Hoca(Long id, String ad,String soyad, Ders acilanDers){
        super(id);
        this.ad=ad;
        this.soyad=soyad;
        this.acilanDers=acilanDers;
    }
    public Hoca(Long id, String sicilNumarasi, String ad, String kullaniciAdi, String sifre, String soyad, String ilgiAlanlari, Ders acilanDersler, ArrayList<Ders> kriterDersler) {
        super(id);
        this.sicilNumarasi = sicilNumarasi;
        this.ad = ad;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.soyad = soyad;
        this.ilgiAlanlari = ilgiAlanlari;
        this.acilanDers = acilanDers;
        this.kriterDersler = kriterDersler;
    }

    public String getSicilNumarasi() {
        return sicilNumarasi;
    }

    public void setSicilNumarasi(String sicilNumarasi) {
        this.sicilNumarasi = sicilNumarasi;
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

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getIlgiAlanlari() {
        return ilgiAlanlari;
    }

    public void setIlgiAlanlari(String ilgiAlanlari) {
        this.ilgiAlanlari = ilgiAlanlari;
    }

    public ArrayList<Ders> getKriterDersler() {
        return kriterDersler;
    }

    public void setKriterDersler(ArrayList<Ders> kriterDersler) {
        this.kriterDersler = kriterDersler;
    }

    public Ders getAcilanDers() {
        return acilanDers;
    }

    public void setAcilanDers(Ders acilanDersler) {
        this.acilanDers = acilanDersler;
    }

    public Ogrenci getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }
}
