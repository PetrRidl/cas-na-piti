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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PridaniNapoje extends AppCompatActivity {
    private Button voda250;
    private Button voda350;
    private Button voda500;
    private Button caj250;
    private Button caj350;
    private Button caj500;
    private Button dzus200;
    private Button dzus250;
    private Button dzus500;
    private Button pivo300;
    private Button pivo500;
    private Button pivo1000;
    private Button zpet;
    AlarmManager alarmManager;
    Calendar calendar;
    DB2 DB;
    static int drink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pridani_napoje);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);




        voda250 = findViewById(R.id.voda_250_ml);
        voda350 = findViewById(R.id.voda_350_ml);
        voda500 = findViewById(R.id.voda_500_ml);
        caj250 = findViewById(R.id.caj_250_ml);
        caj350 = findViewById(R.id.caj_350_ml);
        caj500 = findViewById(R.id.caj_500_ml);
        dzus200 = findViewById(R.id.dzus_200_ml);
        dzus250 = findViewById(R.id.dzus_250_ml);
        dzus500 = findViewById(R.id.dzus_500_ml);
        pivo300 = findViewById(R.id.pivo_300_ml);
        pivo500 = findViewById(R.id.pivo_500_ml);
        pivo1000 = findViewById(R.id.pivo_1000_ml);
        zpet = findViewById(R.id.pridani_zpet);

        zpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nahratStranku();
                setAlarm();

            }
        });

        voda250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(250);

            }
        });
        voda350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(350);
            }
        });
        voda500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(500);
            }
        });
        caj250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(200);
            }
        });
        caj350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(275);
            }
        });
        caj500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(400);
            }
        });
        dzus200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(160);
            }
        });
        dzus250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(200);
            }
        });
        dzus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(400);
            }
        });
        pivo300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(150);
            }
        });
        pivo500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(250);
            }
        });
        pivo1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridani(500);
            }
        });

    }
    public void pridani(int x)
    {
        drink = x;
        if(vloz(x))
        nahratStranku();
        setAlarm();

    }
    public void nahratStranku()
    {
        startActivity(new Intent(this, HlavniStrana.class));
    }

    public boolean vloz(int x)
    {
        String pomoc;
        int mezisoucet;
        DB = new DB2(this);
        Cursor res = DB.VypisDat();
        if(res.getCount() < 1){

            String vahaText = Integer.toString(x);
            Boolean checkinsertdata = DB.drinked(vahaText);
            if (checkinsertdata == true) {
                Toast.makeText(PridaniNapoje.this, "Uloženo!", Toast.LENGTH_SHORT).show();
                return true;

            } else {
                Toast.makeText(PridaniNapoje.this, "Mise selhala!", Toast.LENGTH_SHORT).show();
                return false;

            }
        }
        else{
            StringBuilder stringBuilder1 = new StringBuilder();

            while(res.moveToNext()){
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
    public void setTime()
    {
        calendar = Calendar.getInstance();
        int minuteCounter = 30;
        int hourCounter = 0;

        if(Calendar.getInstance().get(Calendar.MINUTE) >= 30)
        {
            hourCounter = 2;
            calendar.set(Calendar.HOUR_OF_DAY,Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+hourCounter);
            calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)-minuteCounter);

        }
        else if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>22){
            if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)<31)calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
            else calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH)+1);
            if(Calendar.getInstance().get(Calendar.MONTH) == 12 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 31)calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR)+1);
            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 0);

        }
        else{
            hourCounter = 1;
            calendar.set(Calendar.HOUR_OF_DAY,Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+hourCounter);
            calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)+minuteCounter);

        }
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);


    }
    public void setAlarm(){
        setTime();
        vypniUpozorneni();
        Intent intent = new Intent(PridaniNapoje.this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PridaniNapoje.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //Toast.makeText(this, String.valueOf(calendar.getTime()), Toast.LENGTH_SHORT).show();


        //Toast.makeText(this, "Upozornění nastaveno", Toast.LENGTH_SHORT).show();



    }
    public void vypniUpozorneni()
    {
        Intent intent = new Intent(PridaniNapoje.this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PridaniNapoje.this, 0, intent, 0);

        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);

    }
}
