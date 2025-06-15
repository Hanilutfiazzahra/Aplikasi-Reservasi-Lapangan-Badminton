package user;

public class Pelanggan extends User {
    private String noHp;

    public Pelanggan(String id, String nama, String password, String email, String noHp) {
        super(id, nama, email, password);
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }
}
