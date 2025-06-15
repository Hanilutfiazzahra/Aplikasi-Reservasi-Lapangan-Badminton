package pemesanan;

import java.util.ArrayList;

public class RekapPemesanan {
    private static ArrayList<Pemesanan> histori = new ArrayList<>();

    public static void catat(Pemesanan pemesanan) {
        histori.add(pemesanan);
    }

    public static void hapus(Pemesanan pemesanan) {
        histori.remove(pemesanan);
    }

    public static ArrayList<Pemesanan> getHistori() {
        return histori;
    }

    public static void tampilRekap() {
        if (histori.isEmpty()) {
            System.out.println("Belum ada data pemesanan.");
        } else {
            System.out.println("=== Histori Pemesanan ===");
            for (Pemesanan p : histori) {
                System.out.println(p);
            }
        }
    }
}

