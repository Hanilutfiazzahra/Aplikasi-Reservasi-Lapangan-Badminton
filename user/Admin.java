package user;

public class Admin extends User {
    public Admin(String id, String nama, String email, String password) {
        super(id, nama, email, password);
    }

    public void lihatHistori() {
        System.out.println("Admin membuka histori pemesanan.");
    }
}
