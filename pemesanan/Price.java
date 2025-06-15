package pemesanan;

import user.Pelanggan;
import lapangan.Lapangan;

public class Price extends Pemesanan {
    

	public Price(Pelanggan pelanggan, Lapangan lapangan, String[] jadwal) {
		super(pelanggan, lapangan, jadwal);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
	    return "Pelanggan memesan " + getLapangan().getDetail() +
	           ", Kode Voucher: Tidak ada";
	}

	public double hitungTotalHarga(double jam) {
        return jam * getLapangan().getHargaPerJam();
    }
}
