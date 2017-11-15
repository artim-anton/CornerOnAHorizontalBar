package com.example.admin.corneronahorizontalbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListWorkout extends Activity {

    private static final int CM_DELETE_ID = 1;

    TextView textView;
    List<Contact> allContacts;
    ListView lvMain;
    ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workout);

        allContacts = Contact.listAll(Contact.class);

        lvMain = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        adapter = new ArrayAdapter<Contact>(this, R.layout.my_list_item, allContacts);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
        registerForContextMenu(lvMain);
    }

    public void onClearClick(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListWorkout.this);
        alertDialog.setTitle("Подтвердить удаление...");
        alertDialog.setMessage("Вы уверены, что хотите очистить записи?");

        // задаем иконку
        //alertDialog.setIcon(R.drawable.delete);

        // Обработчик на нажатие ДА
        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Contact.deleteAll(Contact.class);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Базу очищено!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Обработчик на нажатие НЕТ
        alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Код который выполнится после закрытия окна
                dialog.cancel();
            }
        });

        // показываем Alert
        alertDialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            allContacts.remove(acmi.position);
            // уведомляем, что данные изменились
            adapter.notifyDataSetChanged();
            Contact contact = Contact.findById(Contact.class,item.getItemId());
            contact.delete();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
