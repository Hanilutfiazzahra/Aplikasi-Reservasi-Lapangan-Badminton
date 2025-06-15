package user;

public abstract class User {
    protected String id;
    protected String nama;
    protected String email;
    protected String password;

    public User(String id, String nama, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public String getNama() { 
    	return nama; 
    }
    public String getEmail() {
    	return email;
    }
}
