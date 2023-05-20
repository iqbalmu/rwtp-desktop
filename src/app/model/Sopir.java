package app.model;

public class Sopir {
    private int id_sopir;
    private String nama;
    private String phone;
    private String alamat;
    private String ktp;
    private String sim;

    public Sopir(int id_sopir, String nama) {
        this.id_sopir = id_sopir;
        this.nama = nama;
    }

    public Sopir(int id_sopir, String nama, String phone, String alamat, String ktp, String sim) {
        this.id_sopir = id_sopir;
        this.nama = nama;
        this.phone = phone;
        this.alamat = alamat;
        this.ktp = ktp;
        this.sim = sim;
    }

    public Sopir( String nama, String phone, String alamat, String ktp, String sim) {
        this.nama = nama;
        this.phone = phone;
        this.alamat = alamat;
        this.ktp = ktp;
        this.sim = sim;
    }

    @Override
    public String toString() {
        return this.getNama();
    }

    public int getId_sopir() {
        return id_sopir;
    }

    public void setId_sopir(int id_sopir) {
        this.id_sopir = id_sopir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }
}
