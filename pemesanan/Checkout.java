package pemesanan;

import java.util.ArrayList;

import mission.utils.MissionUtils;

public class Checkout {
    private ArrayList<Pemesanan> daftarPemesanan;
    protected String[] jadwal;

    public Checkout(ArrayList<Pemesanan> daftarPemesanan) {
    this.daftarPemesanan = daftarPemesanan;
    }

    public void tampilkanPesanan() {
        if (daftarPemesanan.isEmpty()) {
            System.out.println("Tidak ada pesanan.");
            return;
        }
        System.out.println("=== Daftar Pesanan ===");
        for (int i = 0; i < daftarPemesanan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarPemesanan.get(i));
        }
    }
    
    

    public void hapusPesananBerdasarkanIndex() {
        tampilkanPesanan();

        if (daftarPemesanan.isEmpty()) return;

        System.out.print("Pilih nomor pesanan yang ingin dihapus: ");
        try {
            String input = MissionUtils.getUserInput();
            int pilihan = Integer.parseInt(input);

            if (pilihan < 1 || pilihan > daftarPemesanan.size()) {
                System.out.println("Nomor tidak valid.");
                return;
            }

            Pemesanan dihapus = daftarPemesanan.remove(pilihan - 1);
            dihapus.getLapangan().batalkanJam(dihapus.getJadwal());
            RekapPemesanan.hapus(dihapus);
            System.out.println("Pesanan berhasil dihapus:\n" + dihapus);
        } catch (NumberFormatException e) {
            System.out.println("Input bukan angka yang valid.");
        }
    }

	
    
    
}
