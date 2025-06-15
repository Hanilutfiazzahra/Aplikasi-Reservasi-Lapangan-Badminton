package pemesanan;

import lapangan.Lapangan;
import user.Pelanggan;

public abstract class Pemesanan {
	private Pelanggan pelanggan;
    private Lapangan lapangan;
    protected String[] jadwal;
	
	public Pemesanan(Pelanggan pelanggan, Lapangan lapangan, String[] jadwal) {
		super();
		this.pelanggan = pelanggan;
		this.lapangan = lapangan;
		this.jadwal = jadwal;
	}
	
	
	
	public String[] getJadwal() {
		return jadwal;
	}



	public void setJadwal(String[] jadwal) {
		this.jadwal = jadwal;
	}



	public Lapangan getLapangan() {
		return lapangan;
	}

	public abstract double hitungTotalHarga(double jam);
    
    
}
