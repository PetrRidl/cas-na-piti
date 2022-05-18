package com.example.drinkingtime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class PridaniNapojeVlastni extends AppCompatActivity {

    private Button voda;
    private Button pivo;
    private Button dzus;
    private Button caj;
    private Button zpet;
    private Button odeslat;
    private Button kava;
    private Button mineralka;
    private Button mleko;
    private Button limonada;
    private int typNapoje;
    EditText hodnota;
    AlarmManager alarmManager;
    Calendar calendar;
    DB2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pridani_napoje_vlastni);
        DB = new DB2(this);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        voda = findViewById(R.id.Pridani2Voda);
        pivo = findViewById(R.id.Pridani2Pivo);
        dzus = findViewById(R.id.Pridani2Dzus);
        caj = findViewById(R.id.Pridani2Caj);
        kava = findViewById(R.id.pridani2kava);
        mineralka = findViewById(R.id.pridani2mineralka);
        mleko = findViewById(R.id.pridani2mleko);
        limonada = findViewById(R.id.pridani2limo);
        zpet = findViewById(R.id.Pridani2Zpet);
        odeslat = findViewById(R.id.Pridani2Odeslat);
        hodnota = findViewById(R.id.hodnotaPridani2);

        voda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.voda));
                voda.setTextColor(getResources().getColor(R.color.white));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 1;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));


            }
        });
        pivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.pivo));
                pivo.setTextColor(getResources().getColor(R.color.white));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 4;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));
            }
        });
        dzus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.dzus));
                dzus.setTextColor(getResources().getColor(R.color.white));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 2;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));

            }
        });
        caj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.caj));
                caj.setTextColor(getResources().getColor(R.color.white));
                typNapoje = 3;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));
            }
        });
        zpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PridaniNapoje();
            }
        });
        odeslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HlavniStrana();
                vypocet();
            }
        });
        kava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 5;
                kava.setBackgroundColor(getResources().getColor(R.color.kava));
                kava.setTextColor(getResources().getColor(R.color.white));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));
            }
        });
        limonada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 6;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.limonada));
                limonada.setTextColor(getResources().getColor(R.color.white));
            }
        });
        mleko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 7;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.white));
                mineralka.setTextColor(getResources().getColor(R.color.mineralka));
                mleko.setBackgroundColor(getResources().getColor(R.color.mleko));
                mleko.setTextColor(getResources().getColor(R.color.white));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));
            }
        });
        mineralka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voda.setBackgroundColor(getResources().getColor(R.color.white));
                voda.setTextColor(getResources().getColor(R.color.voda));
                pivo.setBackgroundColor(getResources().getColor(R.color.white));
                pivo.setTextColor(getResources().getColor(R.color.pivo));
                dzus.setBackgroundColor(getResources().getColor(R.color.white));
                dzus.setTextColor(getResources().getColor(R.color.dzus));
                caj.setBackgroundColor(getResources().getColor(R.color.white));
                caj.setTextColor(getResources().getColor(R.color.caj));
                typNapoje = 8;
                kava.setBackgroundColor(getResources().getColor(R.color.white));
                kava.setTextColor(getResources().getColor(R.color.kava));
                mineralka.setBackgroundColor(getResources().getColor(R.color.mineralka));
                mineralka.setTextColor(getResources().getColor(R.color.white));
                mleko.setBackgroundColor(getResources().getColor(R.color.white));
                mleko.setTextColor(getResources().getColor(R.color.mleko));
                limonada.setBackgroundColor(getResources().getColor(R.color.white));
                limonada.setTextColor(getResources().getColor(R.color.limonada));
            }
        });


    }

    public void HlavniStrana() {
        startActivity(new Intent(this, HlavniStrana.class));

    }

    public void PridaniNapoje() {
        startActivity(new Intent(this, PridaniNapoje.class));
    }

    public void pridani(int x) {

        if (vloz(x))
            nahratStranku();
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 21 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 5)
            setAlarm();

    }

    public void nahratStranku() {
        startActivity(new Intent(this, HlavniStrana.class));
    }

    public boolean vloz(int x) {
        String pomoc;
        int mezisoucet;
        DB = new DB2(this);
        Cursor res = DB.VypisDat();
        if (res.getCount() < 1) {

            String vahaText = Integer.toString(x);
            Boolean checkinsertdata = DB.drinked(vahaText);
            if (checkinsertdata == true) {
                return true;
            } else {
                return false;
            }
        } else {
            StringBuilder stringBuilder1 = new StringBuilder();

            while (res.moveToNext()) {
                stringBuilder1.append(res.getString(0));
            }
            pomoc = stringBuilder1.toString();
            mezisoucet = Integer.parseInt(pomoc);
            x = mezisoucet + x;

            String vahaText = Integer.toString(x);
            Boolean checkinsertdata = DB.update(vahaText);
            if (checkinsertdata == true) {
                //Toast.makeText(PridaniNapoje.this, "Uloženo!", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                //Toast.makeText(PridaniNapoje.this, "Mise selhala!", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
    }

    public void setTime() {
        calendar = Calendar.getInstance();
        int minuteCounter = 30;
        int hourCounter = 0;

        if (Calendar.getInstance().get(Calendar.MINUTE) >= 30) {
            hourCounter = 2;
            calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + hourCounter);
            calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE) - minuteCounter);

        } else {
            hourCounter = 1;
            calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + hourCounter);
            calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE) + minuteCounter);

        }
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


    }

    public void setAlarm() {
        setTime();
        vypniUpozorneni();
        Intent intent = new Intent(PridaniNapojeVlastni.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PridaniNapojeVlastni.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //Toast.makeText(this, String.valueOf(calendar.getTime()), Toast.LENGTH_SHORT).show();


        //Toast.makeText(this, "Upozornění nastaveno", Toast.LENGTH_SHORT).show();


    }

    public void vypniUpozorneni() {
        Intent intent = new Intent(PridaniNapojeVlastni.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PridaniNapojeVlastni.this, 0, intent, 0);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);

    }
    public void vypocet()
    {
        if(typNapoje < 1){
            Toast.makeText(this, "Vyberte prosím nápoj", Toast.LENGTH_SHORT).show();
        }
        else{
            float hodnotaNapoje = 10;
        float x = Integer.valueOf(hodnota.getText().toString());
        switch (typNapoje){
            case 1:
               hodnotaNapoje = 10;
               break;
            case 2:
                hodnotaNapoje = 8;
                break;
            case 3:
                hodnotaNapoje = 8;
                break;
            case 4:
                hodnotaNapoje = 5;
                break;
            case 5:
                hodnotaNapoje = 8;
                break;
            case 6:
                hodnotaNapoje = 9;
                break;
            case 7:
                hodnotaNapoje = 10;
                break;
            case 8:
                hodnotaNapoje = 9;
                break;

        }
        x = x * hodnotaNapoje;
        x = x / 10;
        pridani(Math.round(x));
        }

    }
}
