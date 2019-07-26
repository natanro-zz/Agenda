package natrodrigues.agenda.ui.activity;

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
    private EditText nameField, phoneField, emailField;
    final private ContactDAO dao = new ContactDAO();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_form);
        setTitle(APPBAR_TITLE);
        configureContactFields();
        configureContactSaveButton();
        finish();
    }

    private void configureContactSaveButton() {
        Button saveContactButton = findViewById(R.id.activity_new_contact_save_button);
        saveContactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Contact newContact = createContact();
                saveNewContact(newContact);
            }
        });
    }

    private void saveNewContact(Contact newContact) {
        dao.saveContact(newContact);
    }

    private Contact createContact() {
        String name  = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        String email = emailField.getText().toString();
        return new Contact(name, phone, email);
    }

    private void configureContactFields() {
        nameField  = findViewById(R.id.activity_new_contact_name);
        phoneField = findViewById(R.id.activity_new_contact_phone);
        emailField = findViewById(R.id.activity_new_contact_email);
    }
}
