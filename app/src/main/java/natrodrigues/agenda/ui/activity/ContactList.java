package natrodrigues.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import natrodrigues.agenda.R;
import natrodrigues.agenda.dao.ContactDAO;
import natrodrigues.agenda.model.Contact;

public class ContactList extends AppCompatActivity {

    final private String APPBAR_TITLE = "Contatos";
    final private ContactDAO dao = new ContactDAO(this);
    private ListView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        setTitle(APPBAR_TITLE);
        contactList = findViewById(R.id.activity_contact_list_listview);
        configureFABAddContact();
        registerForContextMenu(contactList);
    }


    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Editar");
        menu.add(Menu.NONE, 2, Menu.NONE, "Deletar");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =  (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Contact contact = (Contact) contactList.getItemAtPosition(menuInfo.position);

        switch (item.getItemId()){
            case 1:
                editContact(contact);
                break;
            case 2:
                deleteContact(contact);
                configureList();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void editContact(Contact contact) {
        Intent editOnFormIntent = new Intent(this, NewContactForm.class);
        editOnFormIntent.putExtra("contact", contact);
        startActivity(editOnFormIntent);
    }

    private void deleteContact(Contact contact) {
        dao.deleteContact(contact);
        dao.close();
    }

    private void configureList() {
        contactList = findViewById(R.id.activity_contact_list_listview);
        List<Contact> contacts = dao.getAllContacts();
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
