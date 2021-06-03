package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB_sqlite db = new DB_sqlite(this);

    private ImageView btnGo = null;

    private EditText name = null;
    private EditText email = null;
    private EditText phone = null;
    private EditText date = null;
    private EditText runnerId = null;
    private RadioButton radioMale = null;
    private RadioButton radioFemale = null;
    private Button inscriptionBtn = null;
    private Button updateBtn = null;
    private Button deleteBtn = null;
    private ListView runnersList;


    private TextView congrats = null;
    private TextView invit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.imageView3);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.form_page);

                inscriptionBtn = findViewById(R.id.inscriptionBtn);
                deleteBtn = findViewById(R.id.deleteBtn);
                updateBtn = findViewById(R.id.updateBtn);

                name = findViewById(R.id.name);
                email = findViewById(R.id.email);
                phone = findViewById(R.id.phone);
                date = findViewById(R.id.birthday);
                radioMale = findViewById(R.id.radioMale);
                radioFemale = findViewById(R.id.radioFemale);


                inscriptionBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence sexe=null;

                        if (radioMale.isChecked()){
                            sexe=radioMale.getText();
                        }else if (radioFemale.isChecked()){
                            sexe=radioFemale.getText();
                        }else {
                            sexe = "";
                        }


                        Boolean result = db.insertData(name.getText().toString(), email.getText().toString(), Integer.parseInt(phone.getText().toString()), sexe.toString(), date.getText().toString());

                        if (result == true){
                            Toast.makeText(MainActivity.this, "Done2", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "NOT Done", Toast.LENGTH_SHORT).show();

                        }

                        setContentView(R.layout.invitation_page);


                        invit = findViewById(R.id.invitation);
                        runnersList = findViewById(R.id.runnersList);
                        showRunners();

                        invit.setText("Full name: "+name.getText()+"\n"+
                                        "Email: "+email.getText()+"\n"+
                                        "Phone; "+phone.getText()+"\n"+
                                        "Sexe: "+sexe+"\n"+
                                        "Birthday: "+date.getText()+
                                        "\n\n"
                        );

                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete();
                    }
                });


            }
        });
    }


    public void showRunners(){
        ArrayList<String> runners = db.allRecords();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, runners);
        runnersList.setAdapter(arrayAdapter);
    }

    public void delete(){

        runnerId = findViewById(R.id.runnerId);

        db.deleteRunner(runnerId.getText().toString());

        System.out.println("DELETED");

        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();

    }


}