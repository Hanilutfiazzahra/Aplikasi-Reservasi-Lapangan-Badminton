package peralatan;

public class Peralatan {
    private String nama;
    private int stok;
    private double harga;

    public Peralatan(String nama, int stok, double harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public boolean tersedia() {
        return stok > 0;
    }

    public void sewa(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
            System.out.println(jumlah + " " + nama + " berhasil disewa.");
        } else {
            System.out.println(nama + " tidak cukup stok.");
        }
    }

    public void tambahStok(int jumlah) {
        stok += jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public String getInfo() {
        return nama + " - stok: " + stok + ", harga: Rp" + harga;
    }

    public String getNama() {
        return nama;
    }
}
