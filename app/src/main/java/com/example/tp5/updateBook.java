package com.example.tp5;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class updateBook extends AppCompatActivity {

    private TextView book_title;
    private EditText title,aut_name,mots_clé,rusumé;
    private Books book_info;
    private DBHelper db = new DBHelper(this);
    private  Button btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        book_title = (TextView) findViewById(R.id.c_nom);
        title = (EditText) findViewById(R.id.titre);
        aut_name = (EditText) findViewById(R.id.nom_aut);
        mots_clé = (EditText) findViewById(R.id.mots_clé);
        rusumé = (EditText) findViewById(R.id.résumer);
        btn_update = (Button) findViewById(R.id.button_update);

        /// récupurer id de livre
        final int  book_id =  getIntent().getIntExtra("id",0);
        // remplir le formulaire par les donnés déja enregistré
        remplir_formulaire(book_id);

        /// modifier les information
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_book(book_id);
            }
        });

        }

        public void remplir_formulaire(int id)
        {
             book_info = db.find_book_information(id);

             book_title.setText("Livre : "+book_info.getTitre());
             title.setText(book_info.getTitre());
             aut_name.setText(book_info.getAuteur());
             mots_clé.setText(book_info.getMotCles());
             rusumé.setText(book_info.getRusumé());
        }

        public void update_book(int id)
        {
            if( (db.updateBooks(id,title.getText().toString(),aut_name.getText().toString(),
                           mots_clé.getText().toString(),rusumé.getText().toString()) ))
            {
                Toast.makeText(getApplicationContext(),"livre modifié avec succès",Toast.LENGTH_SHORT).show();

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
