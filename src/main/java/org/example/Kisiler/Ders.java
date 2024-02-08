package org.example.Kisiler;

public class Ders {
    private Long id;
    private String adi;
    private String notu;
    private boolean hocaOnayDurum;

    private int kredi;
    private int kontenjanBilgisi;

    public Ders(String adi, String notu) {
        this.adi = adi;
        this.notu = notu;
    }

    public Ders(Long id, String adi, String notu) {
        this.id=id;
        this.adi = adi;
        this.notu = notu;
    }

    public Ders() {
    }

    public Ders(String adi) {
        this.adi=adi;
    }

    public Long getId() {
        return id;
    }

    public int getKontenjanBilgisi() {
        return kontenjanBilgisi;
    }

    public void setKontenjanBilgisi(int kontenjanBilgisi) {
        this.kontenjanBilgisi = kontenjanBilgisi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getNotu() {
        return notu;
    }

    public void setNotu(String notu) {
        this.notu = notu;
    }

    public boolean isHocaOnayDurum() {
        return hocaOnayDurum;
    }

    public void setHocaOnayDurum(boolean hocaOnayDurum) {
        this.hocaOnayDurum = hocaOnayDurum;
    }

    public int getKredi() {
        return kredi;
    }

    public void setKredi(int kredi) {
        this.kredi = kredi;
    }
}
