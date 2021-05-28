package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ImageView btnGo = null;

    private EditText name = null;
    private EditText email = null;
    private EditText phone = null;
    private EditText date = null;
    private RadioButton radioMale = null;
    private RadioButton radioFemale = null;
    private Button inscriptionBtn = null;

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


                        setContentView(R.layout.invitation_page);


                        congrats = findViewById(R.id.congrats);
                        invit = findViewById(R.id.invitation);

                        congrats.setText("Radi ra tskhef");
                        invit.setText("Full name: "+name.getText()+"\n"+
                                        "Email: "+email.getText()+"\n"+
                                        "Phone; "+phone.getText()+"\n"+
                                        "Sexe: "+sexe+"\n"+
                                        "Birthday: "+date.getText()+
                                        "\n\n"
                        );

                    }
                });


            }
        });
    }


}