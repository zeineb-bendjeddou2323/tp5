package com.example.tp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteBook extends AppCompatActivity {


    private RadioGroup output;
    private ArrayList <Books> result;
    private  LayoutInflater layoutInflater;
    private DBHelper db;
    private Button delete_b;
    private LinearLayout radioGroupContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        output = (RadioGroup) findViewById(R.id.list_here_delete);
        db = new DBHelper(this);
        radioGroupContainer = (LinearLayout) findViewById(R.id.radioGroupContainer);



         all_book();

    }

    public void all_book()
    {
         layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
         if(db.numberOfRows() != 0)
         {
             result = db.ListerTousRDV();

             for(Books b : result)
             {

                 RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.radio_list,output,false);

                 radioButton.setText(b.getTitre());
                 radioButton.setId(b.getId());
                 output.addView(radioButton);
             }
             /// add delete button
                Button delete_btn = (Button) layoutInflater.inflate(R.layout.delete_book_btn,output,false);
                output.addView(delete_btn);

             // add delete book method
             delete_b = (Button) findViewById(R.id.delete_b);
             delete();
         }
         else

         {
             radioGroupContainer.removeViewAt(1);
             TextView empty =  (TextView) layoutInflater.inflate(R.layout.empty_result,radioGroupContainer,false);
             radioGroupContainer.addView(empty);
         }


    }
    /// delete
     public void delete()
     {

          delete_b.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if(output.getCheckedRadioButtonId() == -1)
                  {
                      Toast.makeText(getApplicationContext(),"il faut séléctioner un livre !",Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      db.deleteContact(output.getCheckedRadioButtonId());
                      Toast.makeText(getApplicationContext(),"livre suprimé",Toast.LENGTH_SHORT).show();
                      output.removeAllViews();
                      all_book();
                  }

              }
          });

     }
}
