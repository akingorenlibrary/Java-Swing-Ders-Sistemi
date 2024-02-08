package org.example.Veritabani;

import org.example.Kisiler.*;

import java.time.LocalDate;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Sorgular {
    ArrayList<Ders> karsilikGelenDersler=new ArrayList<>();
    public boolean yoneticiGirisKontrol(String kullaniciAdi, String password){
        try (Connection connection = PostgreSQLConnection.getConnection()) {

            Yonetici yonetici=new Yonetici();
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM yoneticiler");
            while(rs.next()){
                yonetici.setId(rs.getLong("id"));
                yonetici.setEmail(rs.getString("kullanici_adi"));
                yonetici.setPassword(rs.getString("sifre"));
            }

            if(yonetici.getEmail().equals(kullaniciAdi) && yonetici.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Ogrenci> ogrencileriGetir(){
        ArrayList<Ogrenci> ogrenciler = new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ogrenciler");

            while(rs.next()){
                Ogrenci ogrenci = new Ogrenci();
                ogrenci.setId(rs.getLong("id"));
                ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                ogrenci.setKullanciAdi(rs.getString("kullanici_adi"));
                ogrenci.setSifre(rs.getString("sifre"));
                ogrenci.setAd(rs.getString("ad"));
                ogrenci.setSoyad(rs.getString("soyad"));
                ogrenci.setIlgiAlanlari(rs.getString("ilgi_alanlari"));
                ogrenci.setNotDurumBelgesi(rs.getBoolean("not_durum_belgesi"));
                ogrenci.setGenelNotOrtalamasi(rs.getInt("genel_not_ortalamasi"));
                ogrenciler.add(ogrenci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ogrenciler;
    }

    public ArrayList<Hoca> hocalariGetir(){
        ArrayList<Hoca> hocalar = new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hocalar");

            while(rs.next()){
                Hoca hoca = new Hoca();
                hoca.setId(rs.getLong("id"));
                hoca.setSicilNumarasi(rs.getString("sicil_numarasi"));
                hoca.setKullaniciAdi(rs.getString("kullanici_adi"));
                hoca.setSifre(rs.getString("sifre"));
                hoca.setAd(rs.getString("ad"));
                hoca.setSoyad(rs.getString("soyad"));
                hoca.setIlgiAlanlari(rs.getString("ilgi_alanlari"));
                hocalar.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hocalar;
    }


    public Ogrenci ogrenciGirisKontrol(String kullaniciAdi, String sifre){
        Ogrenci ogrenci=new Ogrenci();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT * FROM ogrenciler WHERE kullanici_adi = ? AND sifre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, kullaniciAdi);
                statement.setString(2, sifre);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    ogrenci.setId((long) rs.getInt("id"));
                    ogrenci.setAd(rs.getString("ad"));
                    ogrenci.setSoyad(rs.getString("soyad"));
                    ogrenci.setKullanciAdi(rs.getString("kullanici_adi"));
                    ogrenci.setSifre(rs.getString("sifre"));
                    ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                    ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                    ogrenci.setNotDurumBelgesi(rs.getBoolean("not_durum_belgesi"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenci;
    }

    public Hoca hocaGirisKontrol(String kullaniciAdi, String sifre){
        Hoca hoca=new Hoca();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT * FROM hocalar WHERE kullanici_adi = ? AND sifre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, kullaniciAdi);
                statement.setString(2, sifre);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    hoca.setId((long) rs.getInt("id"));
                    hoca.setAd(rs.getString("ad"));
                    hoca.setSoyad(rs.getString("soyad"));
                    hoca.setKullaniciAdi(rs.getString("kullanici_adi"));
                    hoca.setSifre(rs.getString("sifre"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoca;
    }


    public boolean hocaEkle(String ad, String soyad, String sicilNumarasi, String ilgiAlanlari, String kullaniciAdi, String sifre){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO hocalar (ad, soyad, sicil_numarasi, ilgi_alanlari, kullanici_adi, sifre) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ad);
            statement.setString(2, soyad);
            statement.setString(3, sicilNumarasi);
            statement.setString(4, ilgiAlanlari);
            statement.setString(5, kullaniciAdi);
            statement.setString(6, sifre);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            return false;
        }
    }


    public boolean hocaDersAtama(String hocaKullaniciAdi, String ders, String kontenjanBilgisi, int kredi){

        try (Connection connection = PostgreSQLConnection.getConnection()) {

            String sorgu = "SELECT id FROM hocalar WHERE kullanici_adi = ?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, hocaKullaniciAdi);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int hocaId = rs.getInt("id");

                    String sql = "INSERT INTO acilan_dersler (ders_adi, kontenjan_bilgisi, hoca_id, kredi) VALUES (?, ?, ?,?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                        insertStatement.setString(1, ders);
                        insertStatement.setLong(2, Long.parseLong(kontenjanBilgisi));
                        insertStatement.setInt(3, hocaId);
                        insertStatement.setInt(4, kredi);
                        insertStatement.executeUpdate();
                        return true;
                    }
                } else {
                    return false;
                }
            }

        }catch (SQLException e) {
            return false;
        }
    }


    public boolean transkript_ders_ekleme(Long ogrenciId, String dersNotu, String dersAdi){
        dersAdi=dersAdi.trim();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT * FROM acilan_dersler WHERE ders_adi=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, dersAdi);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int dersId = rs.getInt("id");
                    String sql = "INSERT INTO ogrencilerin_aldigi_dersler (ogrenci_id, ders_notu, ders_adi, ders_id) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                        insertStatement.setLong(1, ogrenciId);
                        insertStatement.setString(2, dersNotu);
                        insertStatement.setString(3, dersAdi);
                        insertStatement.setInt(4, dersId);
                        insertStatement.executeUpdate();
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean notDurumBelgesiniGuncelle(Long ogrenciId){
        try(Connection connection = PostgreSQLConnection.getConnection()){
            String sql = "UPDATE ogrenciler SET not_durum_belgesi = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, ogrenciId);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Ders> ogrencininAldigiDersler(Long ogrenciId){
        ArrayList<Ders> dersler=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT * FROM ogrencilerin_aldigi_dersler WHERE ogrenci_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, ogrenciId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Ders ders=new Ders();
                    String dersNotu = rs.getString("ders_notu");
                    String dersAdi = rs.getString("ders_adi");
                    Long dersId=rs.getLong("ders_id");
                    ders.setNotu(dersNotu);
                    ders.setAdi(dersAdi);
                    ders.setId(dersId);
                    dersler.add(ders);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dersler;
    }

    public ArrayList<Hoca> ogrencininYeniAldigiDersler(Long ogrenciId){
        ArrayList<Hoca> dersler=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT acilan_dersler.id AS ders_id, ogrencilerin_ders_secimleri.ogrenci_id, acilan_dersler.ders_adi, acilan_dersler.kredi, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi FROM ogrencilerin_ders_secimleri, acilan_dersler, hocalar WHERE ogrencilerin_ders_secimleri.ders_id=acilan_dersler.id AND hocalar.id=acilan_dersler.hoca_id AND ogrencilerin_ders_secimleri.ogrenci_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, ogrenciId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Hoca hoca=new Hoca();
                    Ders ders=new Ders();

                    ders.setAdi(rs.getString("ders_adi"));
                    ders.setKredi(rs.getInt("kredi"));
                    ders.setId(rs.getLong("ders_id"));
                    hoca.setAcilanDers(ders);

                    hoca.setAd(rs.getString("hoca_adi"));
                    hoca.setSoyad(rs.getString("hoca_soyadi"));

                    dersler.add(hoca);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dersler;
    }

    public ArrayList<Hoca> ogrenciDersSecimiAcilanDerslerListesi(){
        ArrayList<Hoca> hocalar=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT acilan_dersler.id,acilan_dersler.ders_adi,acilan_dersler.kontenjan_bilgisi, acilan_dersler.hoca_id, hocalar.ad, hocalar.soyad, hocalar.ilgi_alanlari FROM acilan_dersler,hocalar WHERE acilan_dersler.hoca_id=hocalar.id";
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery(sorgu);
            while(rs.next()){
                Hoca hoca=new Hoca();
                Ders ders=new Ders();
                hoca.setId((long) rs.getInt("hoca_id"));
                hoca.setAd(rs.getString("ad"));
                hoca.setSoyad(rs.getString("soyad"));
                hoca.setIlgiAlanlari(rs.getString("ilgi_alanlari"));

                ders.setId(rs.getLong("id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("kontenjan_bilgisi"));

                hoca.setAcilanDers(ders);
                hocalar.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hocalar;
    }

    public boolean ogrencininSectigiDersleriEkle(Hoca hocaveDers, Long ogrenciId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrencilerin_ders_secimleri (ders_id, ogrenci_id, hoca_onay_durum) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Math.toIntExact(hocaveDers.getAcilanDers().getId()));
            statement.setLong(2, ogrenciId);
            statement.setBoolean(3, false);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ogrencininSectigiDersiSil(Long ogrenciId, Hoca hocaveDers){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "DELETE FROM ogrencilerin_ders_secimleri WHERE ogrenci_id=? AND ders_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, ogrenciId);
                statement.setLong(2, hocaveDers.getAcilanDers().getId());
                statement.executeUpdate();
                statement.close();
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean mesajGonder(Long gonderenId, Long aliciId, String mesaj){
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO mesajlar (gonderen_id, alici_id, mesaj, tarih) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gonderenId);
            statement.setLong(2, aliciId);
            statement.setString(3, mesaj);
            statement.setTimestamp(4,timestamp);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Hoca> hocaPaneliOgrenciDersTalepleriFarkliHocaAyniDers(Long hocaId, String dersAdi){
        ArrayList<Hoca> ogrenciHocaDers=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT acilan_dersler.hoca_id, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi, acilan_dersler.id AS ders_id, acilan_dersler.ders_adi AS ders_adi, acilan_dersler.kontenjan_bilgisi AS ders_kontenjan_bilgisi, ogrencilerin_ders_secimleri.hoca_onay_durum AS ders_hoca_onay_durum, ogrenciler.id AS ogrenci_id, ogrenciler.ogrenci_no AS ogrenci_no, ogrenciler.ad AS ogrenci_adi, ogrenciler.soyad AS ogrenci_soyadi FROM acilan_dersler, ogrencilerin_ders_secimleri, ogrenciler, hocalar WHERE acilan_dersler.id=ogrencilerin_ders_secimleri.ders_id AND ogrencilerin_ders_secimleri.ogrenci_id=ogrenciler.id AND acilan_dersler.hoca_id=hocalar.id AND acilan_dersler.hoca_id != ? AND acilan_dersler.ders_adi=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            statement.setString(2, dersAdi);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Hoca hoca=new Hoca();
                Ders ders=new Ders();
                Ogrenci ogrenci=new Ogrenci();
                hoca.setId((long) rs.getInt("hoca_id"));
                hoca.setAd(rs.getString("hoca_adi"));
                hoca.setSoyad(rs.getString("hoca_soyadi"));


                ders.setId(rs.getLong("ders_id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("ders_kontenjan_bilgisi"));
                ders.setHocaOnayDurum(rs.getBoolean("ders_hoca_onay_durum"));

                ogrenci.setId(rs.getLong("ogrenci_id"));
                ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                ogrenci.setAd(rs.getString("ogrenci_adi"));
                ogrenci.setSoyad(rs.getString("ogrenci_soyadi"));


                hoca.setOgrenci(ogrenci);
                hoca.setAcilanDers(ders);
                ogrenciHocaDers.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciHocaDers;
    }
    public ArrayList<Hoca> hocaPaneliOgrenciDersTalepleri(Long hocaId){
        ArrayList<Hoca> ogrenciHocaDers=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT acilan_dersler.hoca_id, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi, acilan_dersler.id AS ders_id, acilan_dersler.ders_adi AS ders_adi, acilan_dersler.kontenjan_bilgisi AS ders_kontenjan_bilgisi, ogrencilerin_ders_secimleri.hoca_onay_durum AS ders_hoca_onay_durum, ogrenciler.id AS ogrenci_id, ogrenciler.ogrenci_no AS ogrenci_no, ogrenciler.ad AS ogrenci_adi, ogrenciler.soyad AS ogrenci_soyadi FROM acilan_dersler, ogrencilerin_ders_secimleri, ogrenciler, hocalar WHERE acilan_dersler.id=ogrencilerin_ders_secimleri.ders_id AND ogrencilerin_ders_secimleri.ogrenci_id=ogrenciler.id AND acilan_dersler.hoca_id=hocalar.id AND acilan_dersler.hoca_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Hoca hoca=new Hoca();
                Ders ders=new Ders();
                Ogrenci ogrenci=new Ogrenci();
                hoca.setId((long) rs.getInt("hoca_id"));
                hoca.setAd(rs.getString("hoca_adi"));
                hoca.setSoyad(rs.getString("hoca_soyadi"));


                ders.setId(rs.getLong("ders_id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("ders_kontenjan_bilgisi"));
                ders.setHocaOnayDurum(rs.getBoolean("ders_hoca_onay_durum"));

                ogrenci.setId(rs.getLong("ogrenci_id"));
                ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                ogrenci.setAd(rs.getString("ogrenci_adi"));
                ogrenci.setSoyad(rs.getString("ogrenci_soyadi"));


                hoca.setOgrenci(ogrenci);
                hoca.setAcilanDers(ders);
                ogrenciHocaDers.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciHocaDers;
    }

    public ArrayList<Ders> hocaninVerdigiDersler(Long hocaId){
        ArrayList<Ders> dersler=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu ="SELECT * FROM acilan_dersler WHERE hoca_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, hocaId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Ders ders=new Ders();
                    ders.setId(rs.getLong("id"));
                    dersler.add(ders);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dersler;
    }

    public boolean ogrenciDersTalepKabulEt(Long dersId, Long ogrenciId){
        try(Connection connection = PostgreSQLConnection.getConnection()){
            String sql = "UPDATE ogrencilerin_ders_secimleri SET hoca_onay_durum = ? WHERE ders_id = ? AND ogrenci_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, dersId);
            statement.setLong(3, ogrenciId);
            int guncellemeDurum = statement.executeUpdate();
            statement.close();

            if (guncellemeDurum > 0) {
                int kontenjanBilgisi = dersKontenjanBilgisiGetir(dersId);
                if(kontenjanBilgisi>0){
                    dersKontenjanBilgisiGuncelle(dersId);
                    return true;
                }else{
                    return false;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }


    public int dersKontenjanBilgisiGetir(Long dersId){
        int kontenjanBilgisi = 0;
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "SELECT * FROM acilan_dersler WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, dersId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    kontenjanBilgisi= rs.getInt("kontenjan_bilgisi");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return kontenjanBilgisi;
    }

    public boolean hocadanOgrenciyeDersTalebiGonder(Long hocaId, Long ogrenciId, Long dersId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO hocalardan_ogrencilere_talepler (hoca_id, ogrenci_id, ders_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, hocaId);
            statement.setLong(2, ogrenciId);
            statement.setLong(3, dersId);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hocadanOgrenciyeDersTalebiSil(Long hocaId, Long ogrenciId, Long dersId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "DELETE FROM hocalardan_ogrencilere_talepler WHERE ogrenci_id=? AND ders_id=? AND hoca_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, ogrenciId);
                statement.setLong(2, dersId);
                statement.setLong(3, hocaId);
                statement.executeUpdate();
                statement.close();
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Ders> ogrencininSectigiDersler(Long ogrenciId){
        ArrayList<Ders> dersler=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM ogrencilerin_ders_secimleri WHERE ogrenci_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, ogrenciId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
              Ders ders=new Ders();
              ders.setId(rs.getLong("ders_id"));
              ders.setHocaOnayDurum(rs.getBoolean("hoca_onay_durum"));
              dersler.add(ders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dersler;
    }

    public ArrayList<Hoca> hocalardanOgrencilereTaleplerinListesi(Long hocaId){
        ArrayList<Hoca> hocaDersOgrenciListesi=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM hocalardan_ogrencilere_talepler WHERE hoca_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
               Hoca hoca=new Hoca();
               Ders ders=new Ders();
               Ogrenci ogrenci=new Ogrenci();

                hoca.setId(rs.getLong("hoca_id"));
                ogrenci.setId(rs.getLong("ogrenci_id"));
                ders.setId(rs.getLong("ders_id"));

                hoca.setOgrenci(ogrenci);
                hoca.setAcilanDers(ders);

                hocaDersOgrenciListesi.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hocaDersOgrenciListesi;
    }

    public ArrayList<Hoca> hocalardanGelenTalepler(Long ogrenciId){
        ArrayList<Hoca> hocaDersListesi=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT hocalar.id AS hoca_id, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi, acilan_dersler.id AS ders_id, acilan_dersler.ders_adi, acilan_dersler.kontenjan_bilgisi, hocalardan_ogrencilere_talepler.ogrenci_id FROM hocalardan_ogrencilere_talepler, acilan_dersler, hocalar WHERE hocalardan_ogrencilere_talepler.ders_id=acilan_dersler.id AND hocalardan_ogrencilere_talepler.hoca_id=hocalar.id AND hocalardan_ogrencilere_talepler.ogrenci_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, ogrenciId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Hoca hoca=new Hoca();
                Ders ders=new Ders();

                hoca.setId(rs.getLong("hoca_id"));
                hoca.setAd(rs.getString("hoca_adi"));
                hoca.setSoyad(rs.getString("hoca_soyadi"));

                ders.setId(rs.getLong("ders_id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("kontenjan_bilgisi"));

                hoca.setAcilanDers(ders);
                hocaDersListesi.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hocaDersListesi;
    }

    public boolean dersSecimTarihleriniBelirle(Date baslangicTarihi, Date bitisTarihi){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ders_secim_tarihleri (baslangic_tarihi, bitis_tarihi) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            java.sql.Date baslangicTarihiYeni = new java.sql.Date(baslangicTarihi.getTime());
            java.sql.Date bitisTarihiYeni = new java.sql.Date(bitisTarihi.getTime());

            statement.setDate(1, baslangicTarihiYeni);
            statement.setDate(2, bitisTarihiYeni);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<LocalDate> dersSecimTarihleriniGetir(){
        ArrayList<LocalDate> tarihler=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM ders_secim_tarihleri";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            //statement.setLong(1, hocaId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                tarihler.add(rs.getDate("baslangic_tarihi").toLocalDate());
                tarihler.add(rs.getDate("bitis_tarihi").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarihler;
    }

    public boolean hocaninOgrenciyeGonderdigiDersTalebiniSilDerslerineEkle(Long hocaId, Long ogrenciId, Long dersId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "DELETE FROM hocalardan_ogrencilere_talepler WHERE ogrenci_id=? AND ders_id=? AND hoca_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setLong(1, ogrenciId);
                statement.setLong(2, dersId);
                statement.setLong(3, hocaId);
                statement.executeUpdate();
                statement.close();
                if(dersEkle(hocaId, ogrenciId, dersId)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean dersEkle(Long hocaId, Long ogrenciId, Long dersId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrencilerin_ders_secimleri (ders_id, ogrenci_id, hoca_onay_durum) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, hocaId);
            statement.setLong(2, ogrenciId);
            statement.setBoolean(3, true);
            statement.executeUpdate();
            statement.close();
            dersKontenjanBilgisiGuncelle(dersId);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Mesaj> hocayaGelenMesajlar(Long aliciId){
        ArrayList<Mesaj> mesajlar=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM mesajlar WHERE alici_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, aliciId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Mesaj mesaj=new Mesaj();
                mesaj.setId(rs.getLong("id"));
                mesaj.setAlici_id(rs.getLong("alici_id"));
                mesaj.setGonderen_id(rs.getLong("gonderen_id"));
                mesaj.setMesaj(rs.getString("mesaj"));
                mesaj.setTarih(rs.getTimestamp("tarih").toLocalDateTime());
                mesajlar.add(mesaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    public Ogrenci ogrenciGetir(Long ogrenciId){
        Ogrenci ogrenci=new Ogrenci();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM ogrenciler WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, ogrenciId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
              ogrenci.setId(rs.getLong("id"));
              ogrenci.setAd(rs.getString("ad"));
              ogrenci.setSoyad(rs.getString("soyad"));
              ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenci;
    }

    public ArrayList<Mesaj> ogrencininHocayaAttigiMesajlariGetir(Long hocaId, Long ogrenciId){
        ArrayList<Mesaj> mesajlar=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM mesajlar WHERE gonderen_id=? AND alici_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, ogrenciId);
            statement.setLong(2, hocaId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Mesaj mesaj=new Mesaj();
                mesaj.setId(rs.getLong("id"));
                mesaj.setGonderen_id(rs.getLong("gonderen_id"));
                mesaj.setAlici_id(rs.getLong("alici_id"));
                mesaj.setMesaj(rs.getString("mesaj"));
                mesaj.setTarih(rs.getTimestamp("tarih").toLocalDateTime());
                mesajlar.add(mesaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }
    
    public Long ogrenciNoIleOgrenciBul(String ogrenciNo){
        Long ogrenciId = null;
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM ogrenciler WHERE ogrenci_no=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setString(1, ogrenciNo);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ogrenciId=rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciId;
    }

    public ArrayList<Hoca> acilanDersler(){
        ArrayList<Hoca> acilanDersler=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT acilan_dersler.kredi AS ders_kredi, acilan_dersler.id AS ders_id, acilan_dersler.ders_adi, acilan_dersler.kontenjan_bilgisi, hocalar.id AS hoca_id, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi FROM acilan_dersler, hocalar WHERE acilan_dersler.hoca_id=hocalar.id";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Ders ders=new Ders();
                Hoca hoca=new Hoca();

                ders.setId(rs.getLong("ders_id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("kontenjan_bilgisi"));
                ders.setKredi(rs.getInt("ders_kredi"));

                hoca.setAcilanDers(ders);
                hoca.setId(rs.getLong("hoca_id"));
                hoca.setAd(rs.getString("hoca_adi"));
                hoca.setSoyad(rs.getString("hoca_soyadi"));

                acilanDersler.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acilanDersler;
    }

    public boolean ogrenciUret(int ogrenciSayisi){
        String[] notlar={"AA","BA","BB","CB","CC","DD","DC","FF","FD","FG"};
        String[] adSoyad={"Akın","Burak","Sezin","Derin","Hasan","Berkay","Berrin","Ece","Ahmet","Buğra","Nazlı","Şule","Eren","Dursun","Işıl","Kevser","Faruk","Mehmet","Hilal","Safa","Sinan","AliKemal","Mısra","Seyit","Sevgül","İbrahim","Didar","İlayda","Haydar","Ahmet", "Zeynep", "Mehmet", "Ayşe", "Emir", "Selin", "Cem", "Dilar", "Ali", "Aylin", "Can", "Deniz", "Ezgi", "Furkan", "Gizem", "Hakan", "İpek", "Jale", "Kaan", "Leyla", "Murat", "Nihan", "Oğuz", "Pınar", "Rıza", "Şeyma", "Tolga", "Ümit", "Vildan", "Yasin", "Zara", "Berke", "Çağla", "Doruk", "Ece", "Fikret", "Gökçe", "Halil", "İrem", "Jülide", "Kerem", "Lara", "Mustafa", "Nesrin", "Orhan", "Pelin", "Rüzgar", "Şule", "Tuncay", "Ülkü", "Vefa", "Yelda", "Zafer", "Begüm", "Caner", "Derya", "Emine", "Ferhat", "Gözde", "Hüseyin", "İclal", "Jülide", "Kemal", "Leyla", "Mert", "Nalan", "Osman", "Pelin", "Rabia", "Şükrü", "Türkan", "Ümit", "Vildan", "Yavuz", "Zerrin", "Berkay", "Cansu", "Deniz", "Efe", "Fatma", "Gizem", "Hakan", "İrem", "Jasmin", "Kerem", "Leyla", "Merve", "Nihat", "Onur", "Pınar", "Recep", "Şeyma", "Taylan", "Ufuk", "Volkan", "Yasemin", "Zeynep"};
        String[] ilgiAlanlari={"Bilgisayar Bilimleri","Yapay Zeka","Derin Öğrenme","Matematik","Fizik"};

        for (int x = 0; x < ogrenciSayisi; x++) {
            ArrayList<Ders> aldigiDersler=new ArrayList<>();
            aldigiDersler.clear();
            karsilikGelenDersler.clear();


            //ogrenci numarasi olusturma
            Random random=new Random();
            StringBuilder ogrenciNumarasi=new StringBuilder();
            for(int i=0;i<5;i++){
                ogrenciNumarasi.append(random.nextInt(10));
            }

            //ogrenci ders olusturma
            int dersSayisi=4;
            for (int a = 0; a < dersSayisi; a++) {
                Ders ders=new Ders();

                while(true){
                    boolean durum=false;
                    String dersAdi=dersAdiUret();

                    for (Ders d:aldigiDersler){
                        if(dersAdi.equals(d.getAdi())){
                            durum=true;
                        }
                    }

                    if(durum==false){
                        ders.setAdi(dersAdi);
                        ders.setNotu(notlar[random.nextInt(notlar.length)]);
                        break;
                    }
                }
                aldigiDersler.add(ders);
            }

            double genelNotOrtalamasi=ogrenciGenelNotOrtalamasiHesaplama(aldigiDersler);

            String ogrenciAdi=adSoyad[random.nextInt(adSoyad.length)];
            String ogrenciSoyadi=adSoyad[random.nextInt(adSoyad.length)];

            String kullaniciAdi=ogrenciAdi;
            String sifresi=ogrenciAdi;
            String ilgiAlani=ilgiAlanlari[random.nextInt(ilgiAlanlari.length)];
            boolean not_durum_belgesi=true;
            randomOgrenciKaydet(ogrenciNumarasi.toString(), aldigiDersler,genelNotOrtalamasi, ogrenciAdi, ogrenciSoyadi, kullaniciAdi, sifresi, ilgiAlani, not_durum_belgesi);
        }
        return true;
    }

    public void randomOgrenciKaydet(String ogrenciNo, ArrayList<Ders> aldigiDersler, double genelNotOrtalamasi, String ogrenciAdi, String ogrenciSoyadi, String kullaniciAdi, String sifresi, String ilgiAlani, boolean not_durum_belgesi){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrenciler (ogrenci_no, kullanici_adi, sifre, ad, soyad, ilgi_alanlari, genel_not_ortalamasi, not_durum_belgesi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ogrenciNo);
            statement.setString(2, kullaniciAdi);
            statement.setString(3, sifresi);
            statement.setString(4, ogrenciAdi);
            statement.setString(5, ogrenciSoyadi);
            statement.setString(6, ilgiAlani);
            statement.setDouble(7, genelNotOrtalamasi);
            statement.setBoolean(8, not_durum_belgesi);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        ogrencininAldigiDersleriKaydet(ogrenciNo);
    }

    public void ogrencininAldigiDersleriKaydet(String ogrenciNo) {
        Long ogrenciId = ogrenciNoIleOgrenciBul(ogrenciNo);

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrencilerin_aldigi_dersler (ogrenci_id, ders_notu, ders_adi, ders_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (Ders ders : karsilikGelenDersler) {
                statement.setLong(1, ogrenciId);
                statement.setString(2, ders.getNotu());
                statement.setString(3, ders.getAdi());
                statement.setLong(4, ders.getId());
                statement.executeUpdate();
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String dersAdiUret(){
        String[] dersler={"Fizik","Matematik","Yapay Zeka","Kesikli Matetmatik", "İşaret ve Sistemler", "İşletim Sistemleri", "Sayısal Yöntemler", "Programlama Laboratuvarı","Yazılım Laboratuvarı","Edebiyat"};
        Random random=new Random();
        return dersler[random.nextInt(dersler.length)];
    }


    public double ogrenciGenelNotOrtalamasiHesaplama(ArrayList<Ders> dersler){

        ArrayList<Hoca> acilanDerslerListe=new ArrayList<>();
        acilanDerslerListe=acilanDersler();

        for (Ders ders:dersler){
            for(Hoca hocaveders:acilanDerslerListe){
                if(ders.getAdi().equals(hocaveders.getAcilanDers().getAdi())){
                    Ders d=new Ders();
                    d.setNotu(ders.getNotu());
                    d.setAdi(hocaveders.getAcilanDers().getAdi());
                    d.setKredi(hocaveders.getAcilanDers().getKredi());
                    d.setId(hocaveders.getAcilanDers().getId());
                    karsilikGelenDersler.add(d);
                }
            }
        }

        double toplamKredi = 0.0;
        double sadeceKrediNotuToplama=0.0;
        for (Ders ders : karsilikGelenDersler) {
            String harfNotu = ders.getNotu();
            int krediNotu = ders.getKredi();
            sadeceKrediNotuToplama +=krediNotu;

            if (harfNotu.equals("AA")) {
                toplamKredi += 4.0 * krediNotu;
            } else if (harfNotu.equals("BA")) {
                toplamKredi += 3.5 * krediNotu;
            } else if (harfNotu.equals("BB")) {
                toplamKredi += 3.0 * krediNotu;
            } else if (harfNotu.equals("CB")) {
                toplamKredi += 2.5 * krediNotu;
            } else if (harfNotu.equals("CC")) {
                toplamKredi += 2.0 * krediNotu;
            } else if (harfNotu.equals("DC")) {
                toplamKredi += 1.5 * krediNotu;
            } else if (harfNotu.equals("DD")) {
                toplamKredi += 1.0 * krediNotu;
            } else if (harfNotu.equals("FF")) {
                toplamKredi += 0.0;
            }else if (harfNotu.equals("FG")) {
                toplamKredi += 0.0 * krediNotu;
            }
        }
        double genelNotOrtalamasi = toplamKredi / sadeceKrediNotuToplama;

        String genelNotString = String.format("%.2f", genelNotOrtalamasi);
        genelNotString=genelNotString.replace(",", ".");
        return Double.parseDouble(genelNotString);

    }

    public boolean ogrenciEkle(String ogrenciNo, String kullaniciAdi, String sifre, String ad, String soyad, String ilgiAlanari){
      try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrenciler (ogrenci_no, kullanici_adi, sifre, ad, soyad, ilgi_alanlari) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ogrenciNo);
            statement.setString(2, kullaniciAdi);
            statement.setString(3, sifre);
            statement.setString(4, ad);
            statement.setString(5, soyad);
            statement.setString(6, ilgiAlanari);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ogrenciGenelNotOrtalamasiGuncelle(Long ogrenciId, double genelNotOrtalamasi){
        try(Connection connection = PostgreSQLConnection.getConnection()){
            String sql = "UPDATE ogrenciler SET genel_not_ortalamasi = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, genelNotOrtalamasi);
            statement.setLong(2, ogrenciId);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean ogrenciNoIleOgrenciSil(String ogrenciNo){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "DELETE FROM ogrenciler WHERE ogrenci_no=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, ogrenciNo);
                statement.executeUpdate();
                statement.close();
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kullaniciAdiIleHocaSil(String kullaniciAdi){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu = "DELETE FROM hocalar WHERE kullanici_adi=?";
            try (PreparedStatement statement = connection.prepareStatement(sorgu)) {
                statement.setString(1, kullaniciAdi);
                statement.executeUpdate();
                statement.close();
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ogrenciGuncelle(String secim, String ogrenciNo, String guncelVeri){
        Long ogrenciId = ogrenciNoIleOgrenciBul(ogrenciNo);

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "UPDATE ogrenciler SET " + secim + " = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guncelVeri);
            statement.setLong(2, ogrenciId);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public boolean hocaGuncelle(String secim, String kullaniciAdi, String guncelVeri){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "UPDATE hocalar SET " + secim + " = ? WHERE kullanici_adi = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guncelVeri);
            statement.setString(2, kullaniciAdi);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Mesaj> ogrenciyeGelenMesajlar(Long aliciId){
        ArrayList<Mesaj> mesajlar=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM mesajlar WHERE alici_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, aliciId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Mesaj mesaj=new Mesaj();
                mesaj.setId(rs.getLong("id"));
                mesaj.setAlici_id(rs.getLong("alici_id"));
                mesaj.setGonderen_id(rs.getLong("gonderen_id"));
                mesaj.setMesaj(rs.getString("mesaj"));
                mesaj.setTarih(rs.getTimestamp("tarih").toLocalDateTime());
                mesajlar.add(mesaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    public Hoca hocaGetir(Long hocaId){
        Hoca hoca=new Hoca();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM hocalar WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                hoca.setId(rs.getLong("id"));
                hoca.setAd(rs.getString("ad"));
                hoca.setSoyad(rs.getString("soyad"));
                hoca.setKullaniciAdi(rs.getString("kullanici_adi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoca;
    }

    public ArrayList<Mesaj> hocaninOgrenciyeAttigiMesajlariGetir(Long hocaId, Long ogrenciId){
        ArrayList<Mesaj> mesajlar=new ArrayList<>();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM mesajlar WHERE gonderen_id=? AND alici_id=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            statement.setLong(2, ogrenciId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Mesaj mesaj=new Mesaj();
                mesaj.setId(rs.getLong("id"));
                mesaj.setGonderen_id(rs.getLong("gonderen_id"));
                mesaj.setAlici_id(rs.getLong("alici_id"));
                mesaj.setMesaj(rs.getString("mesaj"));
                mesaj.setTarih(rs.getTimestamp("tarih").toLocalDateTime());
                mesajlar.add(mesaj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    public Long hocaGetirKullaniciAdiIle(String kullaniciAdi){
        Long hocaId=null;
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM hocalar WHERE kullanici_adi=?";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setString(1, kullaniciAdi);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                hocaId=rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hocaId;
    }

    public boolean ogrencileriRastgeleDerslereAtama(){
        ArrayList<Ogrenci> dersSecimiYapanOgrenciler=new ArrayList<>();
        ArrayList<Ogrenci> tumOgrenciler=new ArrayList<>();
        ArrayList<Ogrenci> dersSecimiYapmayanOgrenciler=new ArrayList<>();
        ArrayList<Hoca> acilanDersler=new ArrayList<>();

        dersSecimiYapanOgrenciler.clear();
        tumOgrenciler.clear();
        dersSecimiYapanOgrenciler.clear();
        acilanDersler.clear();

        tumOgrenciler=ogrencileriGetir();
        dersSecimiYapanOgrenciler=dersSecimiYapanOgrenciOgrencilerinIdleri();
        acilanDersler=acilanDersler();

        for(int i=0; i<acilanDersler.size(); i++){
            if(dersKontenjanBilgisiGetir(acilanDersler.get(i).getAcilanDers().getId())<=0){
                acilanDersler.remove(i);
            }
        }

        if(acilanDersler.isEmpty()){
            return false;
        }

        //ders secimi yapmayan ogrencileri bulduk
        for (Ogrenci ogrenci:tumOgrenciler){
            boolean durum=false;
            for (Ogrenci o:dersSecimiYapanOgrenciler){
                if(ogrenci.getId().equals(o.getId())){
                    durum=true;
                    break;
                }
            }
            if(durum==false){
                dersSecimiYapmayanOgrenciler.add(ogrenci);
            }
        }

        for(Ogrenci ogrenci:dersSecimiYapmayanOgrenciler){
            Random random=new Random();
            boolean kontrol=ogrenciyiDerseEkle(acilanDersler.get(random.nextInt(acilanDersler.size())).getAcilanDers().getId(), ogrenci.getId());
            if(kontrol==false){
                return false;
            }
        }

        return true;
    }

    public boolean ogrenciyiDerseEkle(Long dersId, Long ogrenciId){
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "INSERT INTO ogrencilerin_ders_secimleri (ders_id, ogrenci_id, hoca_onay_durum) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, dersId);
            statement.setLong(2, ogrenciId);
            statement.setBoolean(3, true);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Ogrenci> dersSecimiYapanOgrenciOgrencilerinIdleri(){
        ArrayList<Ogrenci> ogrenciler=new ArrayList<>();
        ogrenciler.clear();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT * FROM ogrencilerin_ders_secimleri WHERE hoca_onay_durum=true";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Ogrenci ogrenci=new Ogrenci();
                ogrenci.setId(rs.getLong("ogrenci_id"));
                ogrenciler.add(ogrenci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciler;
    }

    public boolean ogrencilerNotOrtalamasinaGoreAtama(){
        ArrayList<Ogrenci> dersSecimiYapanOgrenciler=new ArrayList<>();
        ArrayList<Ogrenci> tumOgrenciler=new ArrayList<>();
        ArrayList<Ogrenci> dersSecimiYapmayanOgrenciler=new ArrayList<>();
        ArrayList<Hoca> acilanDersler=new ArrayList<>();

        dersSecimiYapanOgrenciler.clear();
        tumOgrenciler.clear();
        dersSecimiYapanOgrenciler.clear();
        acilanDersler.clear();

        tumOgrenciler=notOrtalamasinaGoreTumOgrencileriGetir();
        dersSecimiYapanOgrenciler=dersSecimiYapanOgrenciOgrencilerinIdleri();
        acilanDersler=acilanDersler();

        for(int i=0; i<acilanDersler.size(); i++){
            if(dersKontenjanBilgisiGetir(acilanDersler.get(i).getAcilanDers().getId())<=0){
                acilanDersler.remove(i);
            }
        }

        if(acilanDersler.isEmpty()){
            return false;
        }

        //ders secimi yapmayan ogrencileri bulduk
        for (Ogrenci ogrenci:tumOgrenciler){
            boolean durum=false;
            for (Ogrenci o:dersSecimiYapanOgrenciler){
                if(ogrenci.getId().equals(o.getId())){
                    durum=true;
                    break;
                }
            }
            if(durum==false){
                dersSecimiYapmayanOgrenciler.add(ogrenci);
            }
        }

        for(Ogrenci ogrenci:dersSecimiYapmayanOgrenciler){
            Random random=new Random();
            boolean kontrol=ogrenciyiDerseEkle(acilanDersler.get(random.nextInt(acilanDersler.size())).getAcilanDers().getId(), ogrenci.getId());
            if(kontrol==false){
                return false;
            }
        }

        return true;
    }

    public ArrayList<Ogrenci> notOrtalamasinaGoreTumOgrencileriGetir(){
        ArrayList<Ogrenci> ogrenciler=new ArrayList<>();
        ogrenciler.clear();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="    SELECT * FROM ogrenciler ORDER BY genel_not_ortalamasi DESC";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Ogrenci ogrenci=new Ogrenci();
                ogrenci.setId(rs.getLong("id"));
                ogrenciler.add(ogrenci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciler;
    }

    public boolean dersKontenjanBilgisiGuncelle(Long dersId){
        int kontenjanBilgisi = dersKontenjanBilgisiGetir(dersId);
        kontenjanBilgisi =kontenjanBilgisi-1;
        System.out.println("id: "+dersId+" kon: "+kontenjanBilgisi);
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "UPDATE acilan_dersler SET kontenjan_bilgisi=? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,kontenjanBilgisi );
            statement.setLong(2, dersId);
            statement.executeUpdate();
            statement.close();
        return true;
        } catch (SQLException e) {
        System.out.println("Hata: " + e.getMessage());
        return false;
        }
    }

    public ArrayList<Hoca> hocaPaneliOgrenciDersTalepleriFiltreli(Long hocaId){
        ArrayList<Hoca> ogrenciHocaDers=new ArrayList<>();
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sorgu="SELECT acilan_dersler.hoca_id, hocalar.ad AS hoca_adi, hocalar.soyad AS hoca_soyadi, acilan_dersler.id AS ders_id, acilan_dersler.ders_adi AS ders_adi, acilan_dersler.kontenjan_bilgisi AS ders_kontenjan_bilgisi, ogrencilerin_ders_secimleri.hoca_onay_durum AS ders_hoca_onay_durum, ogrenciler.id AS ogrenci_id, ogrenciler.ogrenci_no AS ogrenci_no, ogrenciler.ad AS ogrenci_adi, ogrenciler.soyad AS ogrenci_soyadi, ogrenciler.genel_not_ortalamasi FROM acilan_dersler, ogrencilerin_ders_secimleri, ogrenciler, hocalar WHERE acilan_dersler.id=ogrencilerin_ders_secimleri.ders_id AND ogrencilerin_ders_secimleri.ogrenci_id=ogrenciler.id AND acilan_dersler.hoca_id=hocalar.id AND acilan_dersler.hoca_id=? ORDER BY ogrenciler.genel_not_ortalamasi DESC";
            PreparedStatement statement = connection.prepareStatement(sorgu);
            statement.setLong(1, hocaId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Hoca hoca=new Hoca();
                Ders ders=new Ders();
                Ogrenci ogrenci=new Ogrenci();
                hoca.setId((long) rs.getInt("hoca_id"));
                hoca.setAd(rs.getString("hoca_adi"));
                hoca.setSoyad(rs.getString("hoca_soyadi"));


                ders.setId(rs.getLong("ders_id"));
                ders.setAdi(rs.getString("ders_adi"));
                ders.setKontenjanBilgisi(rs.getInt("ders_kontenjan_bilgisi"));
                ders.setHocaOnayDurum(rs.getBoolean("ders_hoca_onay_durum"));

                ogrenci.setId(rs.getLong("ogrenci_id"));
                ogrenci.setOgrenciNo(rs.getString("ogrenci_no"));
                ogrenci.setAd(rs.getString("ogrenci_adi"));
                ogrenci.setSoyad(rs.getString("ogrenci_soyadi"));


                hoca.setOgrenci(ogrenci);
                hoca.setAcilanDers(ders);
                ogrenciHocaDers.add(hoca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciHocaDers;
    }


}
