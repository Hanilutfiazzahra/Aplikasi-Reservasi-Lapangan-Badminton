package lapangan;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lapangan {
    private String id;
    private String nama;
    private String lokasi;
    private double hargaPerJam;
    private Map<String, Boolean> jadwal;

    public Lapangan(String id, String nama, String lokasi, double hargaPerJam) {
        this.id = id;
        this.nama = nama;
        this.lokasi = lokasi;
        this.hargaPerJam = hargaPerJam;
        this.jadwal = new LinkedHashMap<>();
        tambahJadwalSeharian();
    }

    private void tambahJadwalSeharian() {
        for (int jam = 8; jam < 22; jam++) {
            String range = String.format("%02d-%02d", jam, jam + 1);
            jadwal.put(range, false);
        }
    }

    public boolean cekKetersediaan(String jam) {
        return jadwal.containsKey(jam) && !jadwal.get(jam);
    }

    public boolean cekSemuaJamTersedia(String[] jamList) {
        for (String j : jamList) {
            if (!cekKetersediaan(j)) return false;
        }
        return true;
    }

    public void pesanJam(String[] jamList) {
        if (!cekSemuaJamTersedia(jamList)) {
            throw new IllegalArgumentException("Salah satu jam sudah dipesan.");
        }
        for (String j : jamList) {
            jadwal.put(j, true);
        }
    }
    
    public void batalkanJam(String[] jamList) {
        for (String jam : jamList) {
            if (jadwal.containsKey(jam)) {
                jadwal.put(jam, false);
            }
        }
    }

    public String[] getJadwal() {
        return jadwal.keySet().toArray(new String[0]);
    }

    public boolean isTerpesan(String jam) {
        return jadwal.getOrDefault(jam, false);
    }

    public double getHargaPerJam() {
        return hargaPerJam;
    }

    public String getDetail() {
        return nama + " di " + lokasi + " Rp" + hargaPerJam + "/jam";
    }
}
