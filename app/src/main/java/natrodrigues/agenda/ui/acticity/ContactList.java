package natrodrigues.agenda.ui.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import natrodrigues.agenda.R;
import natrodrigues.agenda.dao.ContactDAO;

public class ContactList extends AppCompatActivity {

    final private String APPBAR_TITLE = "Contatos";
    final private ContactDAO dao = new ContactDAO();

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
        ListView contactList = findViewById(R.id.acticity_contact_list_listview);
        contactList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.allContacts()
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
