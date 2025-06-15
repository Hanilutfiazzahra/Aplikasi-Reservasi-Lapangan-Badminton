package pembayaran;

public class EWallet implements Pembayaran {
    private double jumlah;
    private String nama;
    private String nomor;

    public EWallet(double jumlah, String nama, String nomor) {
        this.jumlah = jumlah;
        this.nama = nama;
        this.nomor = nomor;
    }

    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui E-Wallet " + nama + " berhasil.");
    }

    public String getStatusPembayaran() {
        return "lunas";
    }
}
