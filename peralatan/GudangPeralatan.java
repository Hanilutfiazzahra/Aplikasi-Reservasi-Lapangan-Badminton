package peralatan;

import java.util.ArrayList;

public class GudangPeralatan {
    private static ArrayList<Peralatan> daftar = new ArrayList<>();

    public static void tambah(Peralatan p) {
        daftar.add(p);
    }

    public static ArrayList<Peralatan> getDaftar() {
        return daftar;
    }

    public static void reset() {
        daftar.clear();
    }

    public static double hitungTotalBiaya(ArrayList<Integer> jumlahSewa) {
        double total = 0;
        for (int i = 0; i < daftar.size(); i++) {
            total += jumlahSewa.get(i) * daftar.get(i).getHarga();
        }
        return total;
    }
}
