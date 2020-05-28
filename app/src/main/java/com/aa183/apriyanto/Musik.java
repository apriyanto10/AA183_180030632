package com.aa183.apriyanto;


import java.util.Date;

public class Musik {

    private int idMusik;
    private String judul;
    private Date tanggal;
    private String gambar;
    private String pencipta;
    private String penyanyi;

    public Musik(int idMusik, String judul, Date tanggal, String gambar, String pencipta, String penyanyi) {
        this.idMusik = idMusik;
        this.judul = judul;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.pencipta = pencipta;
        this.penyanyi = penyanyi;
    }

    public int getIdMusik() {
        return idMusik;
    }

    public void setIdMusik(int idMusik) {
        this.idMusik = idMusik;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPencipta() {
        return pencipta;
    }

    public void setPencipta(String pencipta) {
        this.pencipta = pencipta;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        this.penyanyi = penyanyi;
    }
}
