package natrodrigues.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import natrodrigues.agenda.R;
import natrodrigues.agenda.dao.ContactDAO;
import natrodrigues.agenda.model.Contact;

public class ContactList extends AppCompatActivity {

    final private String APPBAR_TITLE = "Contatos";
    final private ContactDAO dao = new ContactDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        setTitle(APPBAR_TITLE);
        configureFABAddContact();
    }


    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    private void configureList() {
        ListView contactList = findViewById(R.id.activity_contact_list_listview);
        List<Contact> contacts = dao.allContacts();
        dao.close();
        contactList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contacts
        ));
    }

    private void configureFABAddContact() {
        FloatingActionButton addContactButton = findViewById(R.id.activity_contact_list_add_contact);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewContactForm();
            }
        });
    }

    private void openNewContactForm() {
        startActivity(new Intent(this, NewContactForm.class));
    }
}
