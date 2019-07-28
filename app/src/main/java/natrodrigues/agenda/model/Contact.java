package natrodrigues.agenda.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name, phone, email, adress;
    private Long id;

    public Contact(){}

    public Contact(Long id, String name, String phone, String email, String adress) {
        this.id     = id;
        this.name   = name;
        this.email  = email;
        this.phone  = phone;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + " - " +name;
    }

    public Long getId() {
        return id;
    }
}
