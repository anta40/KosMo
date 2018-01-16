package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kost;

import android.graphics.drawable.Drawable;

/**
 * Created by user on 30/03/2017.
 */

public class Kost {
    public String judul;
    public String deskripsi;
    public String lokasi;
    public Drawable foto;
    public String kamar;
    public String latitute, longitude;

    public Kost(String judul, String deskripsi, String kamar, String lokasi, String latitude, String longitude, Drawable foto)
    {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.kamar = kamar;
        this.latitute = latitude;
        this.longitude = longitude;
        this.foto = foto;
    }
}
