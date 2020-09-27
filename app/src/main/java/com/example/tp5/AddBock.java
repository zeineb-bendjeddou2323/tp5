package com.example.tp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddBock extends AppCompatActivity {

    private EditText titre,nom_aut,mots_clé,rusumé;
    private DBHelper db;
    private Button add_bock;
    private BooksListe books_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bock);
        // initialisation des variable
          titre = (EditText) findViewById(R.id.titre);
        nom_aut = (EditText) findViewById(R.id.nom_aut);
        mots_clé = (EditText) findViewById(R.id.mots_clé);
        rusumé = (EditText) findViewById(R.id.résumer);
        add_bock = (Button) findViewById(R.id.button_add);
        db = new DBHelper(this);
        books_list = new BooksListe();
        ///  function appelle
          add_bock();

        }
         // initialisation des variable


        public void add_bock()
        {
            add_bock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(titre.getText().toString().matches("") ||  nom_aut.getText().toString().matches("") ||
                            mots_clé.getText().toString().matches("") || rusumé.getText().toString().matches(""))
                    {

                        Toast.makeText(getApplicationContext(),"tous les champs doivent être remplis", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        if( db.insertBooks(titre.getText().toString(),
                                nom_aut.getText().toString(),mots_clé.getText().toString()
                                ,rusumé.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"livre ajouté avec succès",Toast.LENGTH_SHORT).show();

                            // redirect to the main  page
                            Intent i = new Intent().setClass(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"il ya une erreur",Toast.LENGTH_SHORT).show();


                        }
                    }

                }
            });
        }
}
