package app.model;

public class Pelanggan {
    private String id;
    private String nama;
    private String telp;
    private String kategori;
    private String alamat;
    private boolean isMember;

    public Pelanggan(String id, String nama, String telp, String kategori, String alamat, boolean isMember) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
        this.kategori = kategori;
        this.alamat = alamat;
        this.isMember = isMember;
    }

    public Pelanggan(String nama, String telp, String kategori, String alamat, boolean isMember) {
        this.nama = nama;
        this.telp = telp;
        this.kategori = kategori;
        this.alamat = alamat;
        this.isMember = isMember;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    @Override
    public String toString() {
        return "Pelanggan{" +
                "id=" + id + '\'' +
                "nama='" + nama + '\'' +
                "alamat='" + alamat + '\'' +
                "telp='" + telp + '\'' +
                "ident='" + kategori + '\'' +
                '}';
    }
}
