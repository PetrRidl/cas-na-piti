package com.example.drinkingtime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button smazat;
    private Button vypis;
    private Button zpet;
    private Calendar calendar;
    EditText vahaForm;
    TextView zkouska;
    Switch pohlavi;
    String pomoc;
    private PendingIntent pendingIntent;
    public AlarmManager alarmManager;



    DB4 DB4;
    DBHelper DB;
    int vaha;
    static boolean firstNoti = false;
    static boolean firstTime = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nastaveni);
        DB = new DBHelper(this);
        DB4 = new DB4(this);
        Cursor res = DB.getData();
        pohlavi = findViewById(R.id.pohlavi);
        zpet = findViewById(R.id.nastaveni_zpet);
        zpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res.getCount() > 0)nactiStranu();
                else Toast.makeText(MainActivity.this, "Zadejte prosím údaje", Toast.LENGTH_SHORT).show();
            }
        });

        vahaForm = findViewById(R.id.vahaForm);
        if(res.getCount()> 0)vypisDoFormu();

        button = (Button) findViewById(R.id.button_odeslat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(Integer.valueOf(vahaForm.getText().toString()) > 600)Toast.makeText(MainActivity.this, "Dobrý pokus, ale tolik neváží ani tvoje matka", Toast.LENGTH_SHORT).show();
                else if(Integer.valueOf(vahaForm.getText().toString()) < 10)Toast.makeText(MainActivity.this, "Zadejte prosím skutečnou váhu", Toast.LENGTH_SHORT).show();
                else {
                    if (res.getCount() < 1) {
                        vloz();
                        firstNoti = true;
                        nactiStranu();

                    } else {
                        uprav();
                        nactiStranu();
                        vypisDoFormu();

                    }
                }
            }
        });
        /*smazat = (Button) findViewById(R.id.smazat);
        smazat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smaz();


            }
        });*/


    }

    public void vloz()
    {
        String vahaText = vahaForm.getText().toString();
        String pomocPohlavi;
        pohlavi = findViewById(R.id.pohlavi);
        if(pohlavi.isChecked())pomocPohlavi = "1";
        else pomocPohlavi = "2";
        //vaha = Integer.valueOf(vahaText);

        Boolean checkinsertdata = DB.insertUser(vahaText);
        if(checkinsertdata==true)
        {
            //Toast.makeText(MainActivity.this, "Uloženo!", Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(MainActivity.this, "Mise selhala!", Toast.LENGTH_SHORT).show();
        }
        Boolean checkinsertdata2 = DB4.saveGender(pomocPohlavi);
        if(checkinsertdata2==true)
        {
            //Toast.makeText(MainActivity.this, "Upraveno!", Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(MainActivity.this, pomocPohlavi, Toast.LENGTH_SHORT).show();
        }
    }

    public void uprav()
    {
        String pomocPohlavi;
        String vahaText = vahaForm.getText().toString();
        pohlavi = findViewById(R.id.pohlavi);
        if(pohlavi.isChecked())pomocPohlavi = "1";
        else pomocPohlavi = "2";
        //vaha = Integer.valueOf(vahaText);

        Boolean checkinsertdata = DB.updateUser(vahaText);
        if(checkinsertdata==true)
        {
            //Toast.makeText(MainActivity.this, "Upraveno!", Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(MainActivity.this, "Seš natvrdlej?", Toast.LENGTH_SHORT).show();
        }
        Boolean checkinsertdata2 = DB4.update(pomocPohlavi);
        if(checkinsertdata2==true)
        {
            //Toast.makeText(MainActivity.this, "Upraveno!", Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(MainActivity.this, pomocPohlavi, Toast.LENGTH_SHORT).show();
        }
    }
    public void smaz()
    {
        String vahaText = vahaForm.getText().toString();
        Boolean checkdeletedata = DB.deleteData();

        if(checkdeletedata == true)
        {
            Toast.makeText(MainActivity.this, "Smazáno", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Neexistují data, která by mohla být smazána", Toast.LENGTH_SHORT).show();
        }
        vypisDoFormu();

    }
    public void nactiStranu()
    {
        Intent intent = new Intent(this, HlavniStrana.class);
        startActivity(intent);
    }

    public void vypisDoFormu()
    {
        int cisloPohlavi;
        Cursor cursor = DB.getData();
        Cursor cursor1 = DB4.VypisPohlavi();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder1.append(cursor.getString(0));

        }
        while(cursor1.moveToNext()){
            stringBuilder2.append(cursor1.getString(0));
        }
        vahaForm.setText(stringBuilder1);
        cisloPohlavi = Integer.valueOf(stringBuilder2.toString());
        if(cisloPohlavi == 1){
            pomoc = "žena";
            pohlavi.setChecked(true);
        }
        else pomoc = "muž";

    }





}