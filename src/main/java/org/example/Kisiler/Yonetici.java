package org.example.Kisiler;

public class Yonetici extends Kullanici{
    private String kullaniciAdi;
    private String sifre;

    public Yonetici(){}

    public Yonetici(Long id, String email, String password){
        super(id);
        this.kullaniciAdi=email;
        this.sifre=password;
    }

    public String getEmail() {
        return kullaniciAdi;
    }

    public void setEmail(String email) {
        this.kullaniciAdi = email;
    }

    public String getPassword() {
        return sifre;
    }

    public void setPassword(String password) {
        this.sifre = password;
    }
}
