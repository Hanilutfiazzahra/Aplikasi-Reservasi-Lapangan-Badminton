package main;

import mission.utils.MissionUtils;
import user.Admin;
import user.Pelanggan;
import user.User;
import peralatan.*;
import lapangan.*;
import pembayaran.*;
import pemesanan.*;
import java.util.ArrayList;

public class App {
	static ArrayList<Pemesanan> daftarPemesanan = new ArrayList<>();
	
	
    public static void main(String[] args) {
        boolean aktif = true;
        User pelanggan = null;
        
        ArrayList<Lapangan> daftarLapangan = new ArrayList<>();
        daftarLapangan.add(new LapanganIndoor("L001", "Lapangan Indoor A", "Gedung A", 50000, new String[]{"AC", "Lampu"}));
        daftarLapangan.add(new LapanganOutdoor("L002", "Lapangan Outdoor B", "Area Terbuka", 30000));
        
        while (aktif) {
            System.out.println("\n=== Sistem Pemesanan Badminton ===");
            System.out.println("Siapakah kamu?");
            System.out.println("1. Admin");
            System.out.println("2. Pelanggan");
            System.out.println("3. Keluar");
            int role = -1;
            try {
                System.out.print("Pilih (1/2/3): ");
                role = Integer.parseInt(MissionUtils.getUserInput());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka 1, 2, atau 3.");
                continue; 
            }
            boolean regis = false;

            if (role == 1) {
                System.out.print("Email: ");
                String email = MissionUtils.getUserInput();
                System.out.print("Password: ");
                String pass = MissionUtils.getUserInput();

                if (email.equals("admin@email.com") && pass.equals("admin123")) {
                    User admin = new Admin("admin1", "Admin", email, pass);
                    boolean jalan = true;
                    while (jalan) {
                        System.out.println("\n=== MENU ADMIN ===");
                        System.out.println("1. Lihat Histori Pemesanan");
                        System.out.println("2. Check Out Pesanan");
                        System.out.println("3. Kembali");
                        System.out.print("Pilih: ");
                        int pilih = Integer.parseInt(MissionUtils.getUserInput());
                        switch (pilih) {
                            case 1:
                            	 ((Admin) admin).lihatHistori();
                                RekapPemesanan.tampilRekap();
                                break;
                            case 2:
                            	if (daftarPemesanan.isEmpty()) {
                                    System.out.println("Pesanan saat ini kosong.");
                                } else {
                                    Checkout checkout = new Checkout(daftarPemesanan);
                                    checkout.hapusPesananBerdasarkanIndex();
                                }
                                break;
                            case 3:
                                jalan = false;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    }
                } else {
                    System.out.println("Email atau password salah.");
                }

            } else if (role == 2) {
            	boolean running = true;
                GudangPeralatan.reset();
                GudangPeralatan.tambah(new Peralatan("Raket", 10, 10000));
                GudangPeralatan.tambah(new Peralatan("Shuttlecock", 20, 5000));
                GudangPeralatan.tambah(new Peralatan("Sepatu", 5, 15000));

                
                
                while (running) {
                    try {
                    	
                    	if(!regis) {
                    		
                    		System.out.println("=== REGISTRASI PELANGGAN ===");
                            System.out.print("ID: ");
                            String id = MissionUtils.getUserInput();
                            if (id.equals("")) {
                            	System.out.println("ID tidak boleh kosong");
                            	break;
                            }
                            System.out.print("Nama: ");
                            String nama = MissionUtils.getUserInput();
                            if (nama.equals("")) {
                            	System.out.println("Nama tidak boleh kosong");
                            	break;
                            }
                            System.out.print("Email: ");
                            String email = MissionUtils.getUserInput();
                            if (email.equals("")) {
                            	System.out.println("email tidak boleh kosong");
                            	break;
                            }
                            
                            System.out.print("Password: ");
                            String password = MissionUtils.getUserInput();
                            if (password.equals("")) {
                            	System.out.println("Password tidak boleh kosong");
                            	break;
                            }
                            
                            System.out.print("No HP: ");
                            String noHp = MissionUtils.getUserInput();
                            if (noHp.equals("")) {
                            	System.out.println("No HP tidak boleh kosong");
                            	break;
                            }

                            pelanggan = new Pelanggan(id, nama, password, email, noHp);
                            regis = true;
                    	}
                    	
                    	
                        System.out.println("=== MENU PEMESANAN LAPANGAN BADMINTON ===");
                        System.out.println("1. Pesan Lapangan");
                        System.out.println("2. Sewa Peralatan");
                        System.out.println("3. Lihat Daftar Peralatan");
                        System.out.println("4. Lihat Daftar Lapangan");
                        System.out.println("9. Kembali");
                        System.out.print("Pilih menu: ");
                        int menu = Integer.parseInt(MissionUtils.getUserInput());

                        switch (menu) {
                            case 1:
				
                            	  System.out.println("=== PILIH LAPANGAN ===");
                                  for (int i = 0; i < daftarLapangan.size(); i++) {
                                      System.out.println((i + 1) + ". " + daftarLapangan.get(i).getDetail());
                                  }
                                  System.out.print("Pilih lapangan (angka): ");
                                  int pilih = Integer.parseInt(MissionUtils.getUserInput()) - 1;

                                  if (pilih < 0 || pilih >= daftarLapangan.size()) {
                                      throw new IllegalArgumentException("Pilihan lapangan tidak valid.");
                                  }

                                  Lapangan lapangan = daftarLapangan.get(pilih);

                                  System.out.print("Masukkan jam mulai (contoh: 8 untuk jam 08.00): ");
                                  int jamMulai = Integer.parseInt(MissionUtils.getUserInput());

                                  System.out.print("Masukkan durasi sewa (jam): ");
                                  int durasi = Integer.parseInt(MissionUtils.getUserInput());
                                  if (durasi <= 0 || jamMulai < 8 || jamMulai + durasi > 22)
                                      throw new IllegalArgumentException("Jam tidak valid atau melebihi jam operasional (08.00 - 22.00)");
                                  
                                String[] jamList = new String[durasi];
                                for (int i = 0; i < durasi; i++) {
                                    jamList[i] = String.format("%02d-%02d", jamMulai + i, jamMulai + i + 1);
                                }

                                if (!lapangan.cekSemuaJamTersedia(jamList)) {
                                    System.out.println("Salah satu jam yang diminta sudah dipesan. Silakan pilih jam lain.");
                                    break;
                                }

                                lapangan.pesanJam(jamList);
                                Price pemesanan = new Price((Pelanggan) pelanggan, lapangan, jamList);
                                double total = pemesanan.hitungTotalHarga(durasi);
                                System.out.println("Total harga: Rp" + total);

                                System.out.print("Apakah Anda memiliki voucher diskon? (ya/tidak): ");
                                String voucherResponse = MissionUtils.getUserInput().toLowerCase();

                                if (voucherResponse.equals("ya")) {
                                    System.out.print("Masukkan kode diskon: ");
                                    String kodeDiskon = MissionUtils.getUserInput();

                                    
                                    Diskon diskon = new Diskon((Pelanggan) pelanggan, lapangan, jamList, kodeDiskon); 
                                    double diskonHarga = diskon.validasiKodeVoucher(); 

                                    if (diskonHarga > 0) {
                                        double potongan = total * diskonHarga;
                                        total = total - potongan;
                                        System.out.println("Diskon yang diterima: Rp" + potongan);
                                        System.out.println("Total harga setelah diskon: Rp" + total);
                                    } else if (!kodeDiskon.isEmpty()) {
                                        System.out.println("Kode voucher salah.");
                                        System.out.println("Total harga : Rp" + total);
                                    }
                                    
                                    daftarPemesanan.add(diskon);
                                    RekapPemesanan.catat(diskon);
                                } else {
                                	System.out.println("Total harga : Rp" + total);
                                	
                                	daftarPemesanan.add(pemesanan);
                                    RekapPemesanan.catat(pemesanan);
                                }
                                
                                

                                System.out.println("=== PILIH METODE PEMBAYARAN ===");
                                System.out.println("1. Cash");
                                System.out.println("2. E-Wallet");
                                System.out.println("3. Transfer");
                                System.out.print("Pilih 1/2/3: ");
                                int metode = Integer.parseInt(MissionUtils.getUserInput());

                                Pembayaran pembayaran;
                                if (metode == 1) {
                                    pembayaran = new Cash(total);
                                } else if (metode == 2) {
                                	System.out.println("Pilih jenis E-Wallet:");
        	                        System.out.println("1. GoPay");
        	                        System.out.println("2. OVO");
        	                        System.out.println("3. Dana");
        	                        System.out.print("Pilihan eWallet: ");
        	                        int ewalletChoice = Integer.parseInt(MissionUtils.getUserInput());
        	
        	                        String eWalletName = switch (ewalletChoice) {
        	                            case 1 -> "GoPay";
        	                            case 2 -> "OVO";
        	                            case 3 -> "Dana";
        	                            default -> throw new IllegalArgumentException("Pilihan E-Wallet tidak valid.");
        	                        };
        	
        	                        System.out.print("Masukkan nomor telepon E-Wallet: ");
        	                        String nomor = MissionUtils.getUserInput();
        	                        pembayaran = new EWallet(total, eWalletName, nomor);
                                } else if (metode == 3) {
                                	System.out.println("=== Informasi Rekening Tujuan ===");
        	                    	System.out.println("Silakan transfer ke rekening berikut:");
        	                    	System.out.println("Bank Tujuan        : " + Transfer.getBankTujuan());
        	                    	System.out.println("No. Rekening Tujuan: " + Transfer.getRekeningTujuan());
        	                    	System.out.println();

        	                    	System.out.println("Pilih bank Anda:");
        	                    	System.out.println("1. BCA");
        	                    	System.out.println("2. BRI");
        	                    	System.out.println("3. BNI");
        	                    	System.out.println("4. Mandiri");
        	                    	System.out.println("5. Lainnya");

        	                    	System.out.print("Pilihan (1-5): ");
        	                    	int bankChoice = Integer.parseInt(MissionUtils.getUserInput());

        	                    	String bankPengirim;
        	                    	switch (bankChoice) {
        	                    	    case 1 -> bankPengirim = "BCA";
        	                    	    case 2 -> bankPengirim = "BRI";
        	                    	    case 3 -> bankPengirim = "BNI";
        	                    	    case 4 -> bankPengirim = "Mandiri";
        	                    	    case 5 -> {
        	                    	        System.out.print("Masukkan nama bank Anda: ");
        	                    	        bankPengirim = MissionUtils.getUserInput();
        	                    	    }
        	                    	    default -> throw new IllegalArgumentException("Pilihan bank tidak valid.");
        	                    	}

        	                    	System.out.print("Masukkan nomor rekening Anda: ");
        	                    	String rekeningPengirim = MissionUtils.getUserInput();

        	                    	pembayaran = new Transfer(total, bankPengirim, rekeningPengirim);
                                } else {
                                    throw new IllegalArgumentException("Metode pembayaran tidak valid.");
                                }

                                pembayaran.prosesPembayaran();
                                System.out.println("Status Pembayaran: " + pembayaran.getStatusPembayaran());
                                break;

                            case 2:
                                ArrayList<Peralatan> semua = GudangPeralatan.getDaftar();
                                double totalBiayaPeralatan = 0;
                                System.out.println("=== Sewa Peralatan ===");
                                for (Peralatan p : semua) {
                                    System.out.print("Sewa berapa " + p.getNama() + "? (stok: " + p.getInfo() + "): ");
                                    int jumlah = Integer.parseInt(MissionUtils.getUserInput());
                                    if (jumlah > 0) {
                                        p.sewa(jumlah);
                                        totalBiayaPeralatan += jumlah * p.getHarga();
                                    }
                                }
                                System.out.println("Total biaya sewa peralatan: Rp" + totalBiayaPeralatan);
                               
                                
                                System.out.println("=== PILIH CARA PEMBAYARAN ===");
                                System.out.println("1. Cash");
                                System.out.println("2. E-Wallet");
                                System.out.println("3. Transfer");
                                System.out.print("Pilih 1/2/3: ");
                                int cara = Integer.parseInt(MissionUtils.getUserInput());

                                Pembayaran bayar;
                                if (cara == 1) {
                                    pembayaran = new Cash(totalBiayaPeralatan);
                                } else if (cara == 2) {
                                	System.out.println("Pilih jenis E-Wallet:");
        	                        System.out.println("1. GoPay");
        	                        System.out.println("2. OVO");
        	                        System.out.println("3. Dana");
        	                        System.out.print("Pilihan eWallet: ");
        	                        int ewalletChoice = Integer.parseInt(MissionUtils.getUserInput());
        	
        	                        String eWalletName = switch (ewalletChoice) {
        	                            case 1 -> "GoPay";
        	                            case 2 -> "OVO";
        	                            case 3 -> "Dana";
        	                            default -> throw new IllegalArgumentException("Pilihan E-Wallet tidak valid.");
        	                        };
        	
        	                        System.out.print("Masukkan nomor telepon E-Wallet: ");
        	                        String nomor = MissionUtils.getUserInput();
        	                        pembayaran = new EWallet(totalBiayaPeralatan, eWalletName, nomor);
                                } else if (cara == 3) {
                                	System.out.println("=== Informasi Rekening Tujuan ===");
        	                    	System.out.println("Silakan transfer ke rekening berikut:");
        	                    	System.out.println("Bank Tujuan        : " + Transfer.getBankTujuan());
        	                    	System.out.println("No. Rekening Tujuan: " + Transfer.getRekeningTujuan());
        	                    	System.out.println();

        	                    	System.out.println("Pilih bank Anda:");
        	                    	System.out.println("1. BCA");
        	                    	System.out.println("2. BRI");
        	                    	System.out.println("3. BNI");
        	                    	System.out.println("4. Mandiri");
        	                    	System.out.println("5. Lainnya");

        	                    	System.out.print("Pilihan (1-5): ");
        	                    	int bankChoice = Integer.parseInt(MissionUtils.getUserInput());

        	                    	String bankPengirim;
        	                    	switch (bankChoice) {
        	                    	    case 1 -> bankPengirim = "BCA";
        	                    	    case 2 -> bankPengirim = "BRI";
        	                    	    case 3 -> bankPengirim = "BNI";
        	                    	    case 4 -> bankPengirim = "Mandiri";
        	                    	    case 5 -> {
        	                    	        System.out.print("Masukkan nama bank Anda: ");
        	                    	        bankPengirim = MissionUtils.getUserInput();
        	                    	    }
        	                    	    default -> throw new IllegalArgumentException("Pilihan bank tidak valid.");
        	                    	}

        	                    	System.out.print("Masukkan nomor rekening Anda: ");
        	                    	String rekeningPengirim = MissionUtils.getUserInput();

        	                    	pembayaran = new Transfer(totalBiayaPeralatan, bankPengirim, rekeningPengirim);
                                } else {
                                    throw new IllegalArgumentException("Metode pembayaran tidak valid.");
                                }

                                pembayaran.prosesPembayaran();
                                System.out.println("Status Pembayaran: " + pembayaran.getStatusPembayaran());
                                break;

                                
						case 3:
                                System.out.println("=== DAFTAR PERALATAN ===");
                                for (Peralatan p : GudangPeralatan.getDaftar()) {
                                    System.out.println(p.getInfo());
                                }
                                break;

                            case 4:
                                System.out.println("=== DAFTAR LAPANGAN ===");
                                for (Lapangan l : daftarLapangan) {
                                    System.out.println(l.getDetail());
                                    for (String jamItem : l.getJadwal()) {
                                        System.out.println(" - " + jamItem + (l.isTerpesan(jamItem) ? " (Sudah Dipesan)" : " (Tersedia)"));
                                    }
                                }
                                break;

                            case 9:
                                System.out.println("Kembali ke menu utama.");
                                running = false;
                                break;

                            default:
                                System.out.println("Menu tidak tersedia.");
                        }
                    } catch (Exception e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                    System.out.println();
                }

            } else if (role == 3) {
                System.out.println("Terima kasih telah menggunakan sistem.");
                aktif = false;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
