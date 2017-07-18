package com.example.juan.questao1;

import android.app.ListActivity;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Cursor cursor = managedQuery(Contacts.People.CONTENT_URI, null, null, null, null);

        ListAdapter adpt = new SimpleCursorAdapter(
                this, android.R.layout.two_line_list_item , cursor, new String[] {
                Contacts.People.NAME}, new int[] {android.R.id.text1});

        setListAdapter(adpt);

    }
}

