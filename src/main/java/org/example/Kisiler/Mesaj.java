package org.example.Kisiler;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mesaj {
    private Long id;
    private Long gonderen_id;
    private Long alici_id;
    private String mesaj;
    private LocalDateTime tarih;

    public Mesaj() {}

    public Mesaj(Long id, Long gonderen_id, Long alici_id, String mesaj, LocalDateTime tarih) {
        this.id = id;
        this.gonderen_id = gonderen_id;
        this.alici_id = alici_id;
        this.mesaj = mesaj;
        this.tarih = tarih;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGonderen_id() {
        return gonderen_id;
    }

    public void setGonderen_id(Long gonderen_id) {
        this.gonderen_id = gonderen_id;
    }

    public Long getAlici_id() {
        return alici_id;
    }

    public void setAlici_id(Long alici_id) {
        this.alici_id = alici_id;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}
