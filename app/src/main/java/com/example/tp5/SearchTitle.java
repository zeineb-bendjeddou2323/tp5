package com.example.tp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class SearchTitle extends AppCompatActivity {
    SearchView search_title;
    DBHelper db;
    BooksListe updateBook;
    ArrayList <Books> result;
    ListView books_list;
    ArrayAdapter <String> list_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_title);

        search_title = (SearchView) findViewById(R.id.search_title);
        books_list = (ListView) findViewById(R.id.books_list);
        updateBook= new BooksListe();

        db = new DBHelper(this);

        // add auto focus to the search view

        search_title.setFocusable(true);
        search_title.setIconified(false);

        search_book_title();

    }



    public void search_book_title()
    {
            search_title.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {


                    books_list.setAdapter(null);

                    result = db.RechercherBooksByTitre(newText);

                    list_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view_item_custum);


                    // Toast.makeText(getApplicationContext(),result.getTitre(),Toast.LENGTH_SHORT).show();
                    for(Books b:result)
                    {
                        list_adapter.add(b.getTitre());
                    }
                    if(list_adapter.isEmpty() || search_title.getQuery().toString().matches(""))
                    {
                        list_adapter.clear();
                        if(search_title.getQuery().toString().matches(""))
                        {
                            list_adapter.add("pas de rusultat pour : \"  \" ");

                        }
                        else
                        {
                            list_adapter.add("pas de rusultat pour : "+newText);

                        }
                        books_list.setAdapter(list_adapter);
                    }
                    else
                    {
                        books_list.setAdapter(list_adapter);

                        //add click event to show update book intent
                        books_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                   String lisItem_text = (String) parent.getItemAtPosition(position);

                                   // find the id of this item and call update method
                                int book_id = db.find_id_by_book_title(lisItem_text).getId();

                                Intent i = new Intent().setClass(getApplicationContext(),updateBook.class);
                                i.putExtra("id",db.find_book_information(book_id).getId());
                                startActivity(i);

                            }
                        });
                    }


                    return false;

                }
                });

    }


}
