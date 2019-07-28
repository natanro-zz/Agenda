package natrodrigues.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import natrodrigues.agenda.R;
import natrodrigues.agenda.dao.ContactDAO;
import natrodrigues.agenda.model.Contact;

public class NewContactForm extends AppCompatActivity {

    final private String APPBAR_TITLE = "Novo Contato";
    private EditText nameField, phoneField, emailField, adressField;
    final private ContactDAO dao = new ContactDAO(this);
    private Contact contact = new Contact();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_form);
        setTitle(APPBAR_TITLE);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        configureContactFields();

        if(contact != null){
            fillFormsField(contact);
        }
        configureContactSaveButton();
    }

    private void fillFormsField(Contact contact) {
        nameField.setText(contact.getName());
        phoneField.setText(contact.getPhone());
        emailField.setText(contact.getEmail());
        adressField.setText(contact.getAdress());
        this.contact = contact;
    }

    private void configureContactSaveButton() {
        Button saveContactButton = findViewById(R.id.activity_new_contact_save_button);
        saveContactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContactFromField();

                if(contact.getId() != null){
                    saveEditedContact();
                }else{
                    saveNewContact();
                }
            }
        });
    }

    private void saveEditedContact() {
        dao.editContact(contact);
        dao.close();
        finish();
    }

    private void saveNewContact() {
        dao.saveContact(contact);
        dao.close();
        finish();
    }

    private void setContactFromField() {
        contact.setName(nameField.getText().toString());
        contact.setPhone(phoneField.getText().toString());
        contact.setEmail(emailField.getText().toString());
        contact.setAdress(adressField.getText().toString());
    }

    private void configureContactFields() {
        nameField   = findViewById(R.id.activity_new_contact_name);
        phoneField  = findViewById(R.id.activity_new_contact_phone);
        emailField  = findViewById(R.id.activity_new_contact_email);
        adressField = findViewById(R.id.activity_new_contact_adress);
    }
}
