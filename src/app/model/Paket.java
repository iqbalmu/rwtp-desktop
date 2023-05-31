package app.model;

public class Paket {
    private int id;
    private String nopol;
    private String namaPengirim;
    private String hpPengirim;
    private String namaPenerima;
    private String hpPenerima;
    private String alamatPenerima;
    private int kuantitas;
    private String status;

    public Paket(String nopol, String namaPengirim, String hpPengirim, String namaPenerima, String hpPenerima, String alamatPenerima, int kuantitas, String status) {
        this.nopol = nopol;
        this.namaPengirim = namaPengirim;
        this.hpPengirim = hpPengirim;
        this.namaPenerima = namaPenerima;
        this.hpPenerima = hpPenerima;
        this.alamatPenerima = alamatPenerima;
        this.kuantitas = kuantitas;
        this.status = status;
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

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getHpPengirim() {
        return hpPengirim;
    }

    public void setHpPengirim(String hpPengirim) {
        this.hpPengirim = hpPengirim;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getHpPenerima() {
        return hpPenerima;
    }

    public void setHpPenerima(String hpPenerima) {
        this.hpPenerima = hpPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
