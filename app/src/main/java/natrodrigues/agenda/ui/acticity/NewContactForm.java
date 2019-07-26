package natrodrigues.agenda.ui.acticity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import natrodrigues.agenda.R;

public class NewContactForm extends AppCompatActivity {

    final private String APPBAR_TITLE = "Novo Contato";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_new_contact_form);
        setTitle(APPBAR_TITLE);

    }
}
