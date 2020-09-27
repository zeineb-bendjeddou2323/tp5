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

import java.util.ArrayList;

public class SearchMotscle extends AppCompatActivity {

    private SearchView search_input;
    private ListView all_books_result;
    private ArrayList<Books> result;
    private BooksListe updateBook;
    private ArrayAdapter <String> list_adapter;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_motscle);

        // initialisation
        search_input = (SearchView) findViewById(R.id.search_mots_clé);
        all_books_result = (ListView) findViewById((R.id.books_list_output));
        db = new DBHelper(this);
        updateBook = new BooksListe();

        // add auto focus to the search view

        search_input.setFocusable(true);
        search_input.setIconified(false);


        // call to search method
        search_by_mots_cle();
    }

    public void search_by_mots_cle()
    {
        search_input.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                all_books_result.setAdapter(null);

                result = db.RechercherBooksByMotsCle(newText);

                list_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view_item_custum);


                // Toast.makeText(getApplicationContext(),result.getTitre(),Toast.LENGTH_SHORT).show();
                for(Books b:result)
                {
                    list_adapter.add(b.getTitre()+" ses mots clé : "+b.getMotCles());
                }
                if(list_adapter.isEmpty() || search_input.getQuery().toString().matches(""))
                {
                    list_adapter.clear();

                    if(search_input.getQuery().toString().matches(""))
                    {
                        list_adapter.add("pas de rusultat pour : \"  \" ");

                    }
                    else
                    {
                        list_adapter.add("pas de rusultat pour : "+newText);

                    }
                    all_books_result.setAdapter(list_adapter);
                }
                else
                {
                    all_books_result.setAdapter(list_adapter);

                    all_books_result.setAdapter(list_adapter);

                    //add click event to show update book intent
                    all_books_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String [] lisItem_text = ((String) parent.getItemAtPosition(position)).split("\\ ses");

                            // find the id of this book and call update method
                            int book_id = db.find_id_by_book_title(lisItem_text[0]).getId();

                            Intent i = new Intent().setClass(getApplicationContext(),updateBook.class);
                            i.putExtra("id",db.find_book_information(book_id).getId());
                            startActivity(i);

                        }
                    });
                }
                return false;
            }
        });


    };
}

