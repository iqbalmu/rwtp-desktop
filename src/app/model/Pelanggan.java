package app.model;

public class Pelanggan {
    private int id;
    private String nama;
    private String telp;
    private String kategori;
    private String alamat;
    private String isMember;

    public Pelanggan(int id, String nama, String telp, String kategori, String alamat, String isMember) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
        this.kategori = kategori;
        this.alamat = alamat;
        this.isMember = isMember;
    }

    public Pelanggan(String nama, String telp, String kategori, String alamat, String isMember) {
        this.nama = nama;
        this.telp = telp;
        this.kategori = kategori;
        this.alamat = alamat;
        this.isMember = isMember;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    @Override
    public String toString() {
        return "Pelanggan{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                '}';
    }
}
