package pembayaran; 

public class Transfer implements Pembayaran { 

	private static final String BANK_TUJUAN = "Mandiri"; 
	private static final String NO_REKENING_TUJUAN = "1234567890"; 
	private double jumlah; 
	private String bankPengirim; 
	private String rekeningPengirim;
	private String status; 
	
	public Transfer(double jumlah, String bankPengirim, String rekeningPengirim) { 	
		this.jumlah = jumlah; 		
		this.bankPengirim = bankPengirim; 		
		this.rekeningPengirim = rekeningPengirim; 		
		this.status = status; 
	} 
	
	@Override 
	public void prosesPembayaran() { 
		System.out.println("=== Detail Transfer ==="); 				
		System.out.println("Bank Tujuan : " + BANK_TUJUAN); 		
		System.out.println("No. Rekening Tujuan: " + NO_REKENING_TUJUAN); 		
		System.out.println();
		System.out.println("Dari:"); 		
		System.out.println("- Bank Anda : " + bankPengirim); 		
		System.out.println("- No. Rekening : " + rekeningPengirim); 
		System.out.println("Jumlah Transfer : Rp" + jumlah);
		System.out.println("Transfer berhasil."); 
		
		this.status = "Sukses"; 
	
	} 		 
	
	@Override 	
	public String getStatusPembayaran() { 	
		return this.status; 
	} 
	
	public static String getBankTujuan() { 
		return BANK_TUJUAN; 	
	} 	
	
	public static String getRekeningTujuan() {
		return NO_REKENING_TUJUAN;
	}
}
