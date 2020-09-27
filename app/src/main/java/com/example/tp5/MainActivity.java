package com.example.tp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout add,list,delete,search_title,search_mots_cle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //delete_book
        add = (LinearLayout) findViewById(R.id.toAddBock);
        list =  (LinearLayout) findViewById(R.id.list_books);
        delete =  (LinearLayout) findViewById(R.id.delete_book);
        search_title = (LinearLayout) findViewById(R.id.search_title);
        search_mots_cle = (LinearLayout) findViewById(R.id.search_mots_clé);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openAddActivity();

            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListActivity();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteActivity();

            }
        });
        search_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_title();
            }
        });
        search_mots_cle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_mots_cle();
            }
        });
    }
    public void openAddActivity()
    {
        Intent i = new Intent().setClass(this,AddBock.class);
        startActivity(i);
    }
    public void openListActivity()
    {
        Intent i = new Intent().setClass(this,BooksListe.class);
        startActivity(i);

    }
    public void openDeleteActivity()
    {
        Intent i = new Intent().setClass(this,DeleteBook.class);
        startActivity(i);
    }
    public void search_title()
    {
        Intent i = new Intent().setClass(this,SearchTitle.class);
        startActivity(i);

    }
    public void Search_mots_cle()
    {
        Intent i = new Intent().setClass(this,SearchMotscle.class);
        startActivity(i);

    }
}
