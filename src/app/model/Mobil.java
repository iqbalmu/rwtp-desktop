package app.model;

public class Mobil {

    private int id;
    private String nopol;
    private String jenis;
    private String kelas;
    private String status;
    private int id_sopir;
    private String nama_sopir;

    public Mobil(String nopol, String jenis, String kelas, String status, int id_sopir) {
        this.nopol = nopol;
        this.jenis = jenis;
        this.kelas = kelas;
        this.status = status;
        this.id_sopir = id_sopir;
    }

    public Mobil(int id, String nopol, String jenis, String kelas, String status, int id_sopir) {
        this.id = id;
        this.nopol = nopol;
        this.jenis = jenis;
        this.kelas = kelas;
        this.status = status;
        this.id_sopir = id_sopir;
    }

    public Mobil() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_sopir() {
        return id_sopir;
    }

    public void setId_sopir(int id_sopir) {
        this.id_sopir = id_sopir;
    }

    @Override
    public String toString() {
        return getNopol() + " | " + getJenis();
    }

    public String getNama_sopir() {
        return nama_sopir;
    }

    public void setNama_sopir(String nama_sopir) {
        this.nama_sopir = nama_sopir;
    }
}
