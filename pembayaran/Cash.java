package pembayaran;

public class Cash implements Pembayaran {
    private double jumlah;

    public Cash(double jumlah) {
        this.jumlah = jumlah;
    }

    public void prosesPembayaran() {
        System.out.println("Pembayaran tunai sebesar Rp" + jumlah + " berhasil.");
    }

    public String getStatusPembayaran() {
        return "lunas";
    }
}

