package com.example.drinkingtime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SportovniAktivita extends AppCompatActivity {

    SeekBar intenzita;
    Button getValue;
    EditText cas;
    int hodnotaAktivity;
    DB3 DB3;
    int zatimVypito;
    int vypocet;
    private Button zpet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sportovni_aktivita);
        DB3 = new DB3(this);
        //if(DB3.deleteData() == true)Toast.makeText(SportovniAktivita.this, "Povedlo se", Toast.LENGTH_SHORT).show();
        getValue = findViewById(R.id.odeslani_aktivity);
        intenzita = findViewById(R.id.intenzita);
        cas = findViewById(R.id.cas);
        hodnotaAktivity = intenzita.getProgress();
        zpet = findViewById(R.id.aktivita_zpet);

        zpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hlavniStrana();
            }
        });


        getValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hodnotaAktivity = intenzita.getProgress();
                Cursor res = DB3.VypisPiti();
                vypocet();
                if(res.getCount() > 0){
                    vypis();
                    update(vypocet + zatimVypito);
                    hlavniStrana();

                }
                else{
                    zapis(vypocet);
                    hlavniStrana();
                }

            }
        });

    }
    public void zapis(int x)
    {

        String zapisnik = String.valueOf(x);

        Boolean checkinsertdata = DB3.toDrink(zapisnik);
        if(checkinsertdata==true)
        {
            Toast.makeText(SportovniAktivita.this, "Uloženo!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SportovniAktivita.this, "Mise selhala!", Toast.LENGTH_SHORT).show();
        }


    }
    public void update(int x)
    {
        String zapisnik = String.valueOf(x);

        Boolean checkinsertdata = DB3.update(zapisnik);
        if(checkinsertdata==true)
        {
            Toast.makeText(SportovniAktivita.this, "Upraveno!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SportovniAktivita.this, "Neupraveno!", Toast.LENGTH_SHORT).show();
        }
    }
    public void vypis()
    {
        String helper;
        Cursor res = DB3.VypisPiti();
        StringBuilder stringBuilder1 = new StringBuilder();

        while(res.moveToNext()){
            stringBuilder1.append(res.getString(0));

        }
        helper = stringBuilder1.toString();
        zatimVypito = Integer.valueOf(helper);
    }
    public void vypocet()
    {
        int cas1 = Integer.valueOf(cas.getText().toString());
        vypocet = hodnotaAktivity * cas1;
    }
    public void hlavniStrana()
    {

        startActivity(new Intent(this, HlavniStrana.class));

    }


}
