package com.example.tp5;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class BooksListe extends AppCompatActivity {
    DBHelper db;
    LinearLayout list_here;
    ArrayList <Books> result;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_liste);

        db = new DBHelper(this);
        list_here = (LinearLayout) findViewById(R.id.list_here);

        all_books();
    }
    public void all_books()
    {

          layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

          if(db.numberOfRows() != 0)
          {
              result = db.ListerTousRDV();
              for(Books b : result)
              {

                  Button button = (Button) layoutInflater.inflate(R.layout.list_b_style,list_here, false);
                  button.setText(b.getTitre());

                  final int current_id = b.getId();

                  button.setOnClickListener(new View.OnClickListener() {
                      @Override

                      public void onClick(View v) {

                          update_book(current_id);
                      }
                  });
                  list_here.addView(button);


              }
          }
          else
          {
                  TextView empty =  (TextView) layoutInflater.inflate(R.layout.empty_result,list_here,false);
                  list_here.addView(empty);
          }


    }
    public void update_book(int id)
    {
        Intent i = new Intent(this,updateBook.class);
        i.putExtra("id",id);
        startActivity(i);

    }
}
