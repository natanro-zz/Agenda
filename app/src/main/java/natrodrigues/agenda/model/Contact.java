package natrodrigues.agenda.model;

public class Contact {

    private String name, phone, email;

    public Contact(String name, String phone, String email) {
        this.name  = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
