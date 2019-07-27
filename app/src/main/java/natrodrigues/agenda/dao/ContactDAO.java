package natrodrigues.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import natrodrigues.agenda.model.Contact;


public class ContactDAO extends SQLiteOpenHelper {

    final private String TABLE_NAME = "Contacts";

    public ContactDAO(Context context) {
        super(context, "Agenda", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_NAME+" (name TEXT NOT NULL, " +
                "phone TEXT, email TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<Contact> allContacts(){
        String sql = "SELECT * FROM "+TABLE_NAME+";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()){
            String name  = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            Contact contact = new Contact(name, phone, email);
            contacts.add(contact);
        }

        cursor.close();

        return contacts;

    }

    public void saveContact(Contact newContact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("name",  newContact.getName());
        data.put("phone", newContact.getPhone());
        data.put("email", newContact.getEmail());

        db.insert(TABLE_NAME, null, data);
    }

}
