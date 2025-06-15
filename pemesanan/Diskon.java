package pemesanan;

import java.util.HashMap;
import java.util.Map;

import lapangan.Lapangan;
import user.Pelanggan;
import user.User;

public class Diskon extends Pemesanan {
	private final Map<String, Double> vouchers = new HashMap<>();
    private String kodeVoucher;

    public Diskon(Pelanggan pelanggan, Lapangan lapangan, String[] jadwal, String kodeVoucher) {
        super(pelanggan, lapangan, jadwal);
        vouchers.put("HARIBAIK", 0.10);
        vouchers.put("DISKON10", 0.10);

        this.kodeVoucher = kodeVoucher.toUpperCase();
        if (!vouchers.containsKey(this.kodeVoucher)) {
            this.kodeVoucher = null;
        }
    }

    public String getKodeVoucher() {
        return kodeVoucher;
    }

    public double validasiKodeVoucher() {
        if (kodeVoucher != null && vouchers.containsKey(kodeVoucher)) {
            return vouchers.get(kodeVoucher);
        }
        return 0.0;
    }

    @Override
    public double hitungTotalHarga(double jam) {
        double hargaAwal = getLapangan().getHargaPerJam() * jam;
        if (kodeVoucher != null) {
            double diskon = hargaAwal * vouchers.get(kodeVoucher);
            return hargaAwal - diskon;
        }
        return hargaAwal;
    }

    @Override
    public String toString() {
        return "pelanggan memesan " + getLapangan().getDetail() +
               ", Kode Voucher: " + (kodeVoucher != null ? kodeVoucher : "Tidak ada");
    }
}
