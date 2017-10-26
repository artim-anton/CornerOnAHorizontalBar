package com.example.admin.corneronahorizontalbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListWorkout extends Activity {


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workout);


       /* List<Contact> allContacts = Contact.listAll(Contact.class);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(allContacts.toString());*/

        List<Contact> allContacts = Contact.listAll(Contact.class);

        // находим список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, R.layout.my_list_item, allContacts);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
    }

    public void onClearClick(View view) {
        Contact.deleteAll(Contact.class);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Базу очищено!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
