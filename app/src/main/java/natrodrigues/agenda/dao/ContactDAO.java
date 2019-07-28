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
    final private String DATABASE_UPDATE_TABLE_TO_V3 = "ALTER TABLE "+TABLE_NAME+
            " ADD COLUMN adress TEXT;";

    public ContactDAO(Context context) {
        super(context, "Agenda", null, 3);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY,name TEXT NOT NULL, " +
                "phone TEXT, email TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                /*upgrade from version 1 to verion 2*/
                String sql = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
                sqLiteDatabase.execSQL(sql);
                onCreate(sqLiteDatabase);
            case 2:
                /*upgrade from version 2 to version 3*/
                sqLiteDatabase.execSQL(DATABASE_UPDATE_TABLE_TO_V3);
        }
    }

    public List<Contact> getAllContacts(){
        String sql = "SELECT * FROM "+TABLE_NAME+";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()){
            Long id       = cursor.getLong(cursor.getColumnIndex("id"));
            String name   = cursor.getString(cursor.getColumnIndex("name"));
            String phone  = cursor.getString(cursor.getColumnIndex("phone"));
            String email  = cursor.getString(cursor.getColumnIndex("email"));
            String adress = cursor.getString(cursor.getColumnIndex("adress"));

            Contact contact = new Contact(id, name, phone, email, adress);
            contacts.add(contact);
        }

        cursor.close();

        return contacts;

    }

    public void saveContact(Contact newContact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(newContact);

        db.insert(TABLE_NAME, null, data);
    }

    private ContentValues getContentValues(Contact contact) {
        ContentValues data = new ContentValues();

        data.put("name",   contact.getName());
        data.put("phone",  contact.getPhone());
        data.put("email",  contact.getEmail());
        data.put("adress", contact.getAdress());
        return data;
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(contact.getId())};
        db.delete(TABLE_NAME, "id = ?", params);
    }

    public void editContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(contact);

        String[] params = {contact.getId().toString()};
        db.update(TABLE_NAME, data, "id = ?", params);
    }
}
