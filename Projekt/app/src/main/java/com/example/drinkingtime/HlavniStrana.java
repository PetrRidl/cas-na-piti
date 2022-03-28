package com.example.drinkingtime;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;



public class HlavniStrana extends AppCompatActivity {
    private Button button;
    private Calendar calendar;
    private Button pridani_napoje;
    private Button aktualizace;
    private Button smazPiti;
    private Button sportovni_aktivita;
    private Button upozorneni;
    static int kategorie;
    static int zatimVypito;
    static int kolikVypit;
    static int kolikVypito;
    private TextView vypit;
    private TextView vypito;
    boolean ukladani;



    DBHelper DB;
    DB2 DB2;    //kolik vypito
    DB3 DB3;    //kolik Vypit
    DB4 DB4; //pohlavi
    DBHistory DBHistory;

    TextView hodnota;

    ProgressBar postup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hlavnistrana);

        DB = new DBHelper(this);
        DB2 = new DB2(this);
        DB3 = new DB3(this);
        DB4 = new DB4(this);
        DBHistory = new DBHistory(this);
        Cursor res = DB.getData();
        if(res.getCount() < 1)
        {
            odkaz();

        }

        //vypocet();
        createNotificationChannel();
        vypit = findViewById(R.id.textViewVypit);
        vypito = findViewById(R.id.textViewVypito);
        vypit.setText(String.valueOf(kolikVypit));
        vypito.setText(String.valueOf(kolikVypito));
        //smazPiti = findViewById(R.id.smazat_piti);
        //hodnota = findViewById(R.id.hodnota);
        pridani_napoje = findViewById(R.id.pridani_napoje);
        sportovni_aktivita = findViewById(R.id.pridaniAktivity);
        postup = findViewById(R.id.progressBar);


        postup.setMax(kolikVypit);//kolikvypit
        postup.setProgress(kolikVypito);//kolikvypito
        //setTime();
        /*upozorneni = findViewById(R.id.upozorneni);
        upozorneni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();


            }
        });*/
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 0 && ukladani == false)ukladani=true;
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<8 && ukladani == true){
            UlozData();
            ukladani = false;
        }
        pridani_napoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pridaniNapoje();
            }
        });

        /*smazPiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(DB2.deleteData() && DB3.deleteData()) Toast.makeText(HlavniStrana.this, "Odstraněno šéfiku", Toast.LENGTH_SHORT).show();
                if(DBHistory.deleteData())Toast.makeText(HlavniStrana.this, "Odstraněno šéfiku", Toast.LENGTH_SHORT).show();
                else Toast.makeText(HlavniStrana.this, "chiba", Toast.LENGTH_SHORT).show();

            }
        });*/
        sportovni_aktivita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sportovniAktivita();
            }
        });
        button = (Button) findViewById(R.id.nastaveni);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odkaz();
                MainActivity.firstTime = true;
            }
        });
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<7 && DB2.VypisDat().getCount() > 0)UlozData();

        if(MainActivity.firstNoti)
        {
            setAlarm();
            MainActivity.firstNoti = false;
        }


    }
    public void odkaz()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void pridaniNapoje()
    {
        startActivity(new Intent(this, PridaniNapoje.class));
    }

    public void sportovniAktivita(){
        startActivity(new Intent(this, SportovniAktivita.class));
    }



    public void vypocet(){
        int vaha;
        int cisloPohlavi;
        boolean pohlavi;
        String textVaha;
        Cursor res = DB.getData();
        Cursor cursor1 = DB4.VypisPohlavi();
        StringBuilder stringBuilder2 = new StringBuilder();
        while(cursor1.moveToNext()){
            stringBuilder2.append(cursor1.getString(0));
        }


        cisloPohlavi = Integer.valueOf(stringBuilder2.toString());

        if(cisloPohlavi == 1)pohlavi = true; //je žena
        else pohlavi = false; //je muž
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(0));
            }
            textVaha = buffer.toString();
            vaha = Integer.valueOf(textVaha);

        int vypitNavic;
        Cursor res1 = DB3.VypisPiti();
        if(res1.getCount() > 0){StringBuffer buffer1 = new StringBuffer();
        while(res1.moveToNext())
        {
            buffer1.append(res1.getString(0));
        }
        vypitNavic = Integer.valueOf(buffer1.toString());}
        else vypitNavic = 0;


        if(vaha <45)kategorie = 1; // 1300
        else if(vaha < 50)kategorie = 2; // 1500
        else if(vaha < 55)kategorie = 3; // 1650
        else if(vaha < 60)kategorie = 4; // 1850
        else if(vaha < 65)kategorie = 5; // 2000
        else if(vaha < 70)kategorie = 6; // 2200
        else if(vaha < 75)kategorie = 7; // 2350
        else if(vaha < 80)kategorie = 8; // 2500
        else if(vaha < 85)kategorie = 9; // 2650
        else if(vaha < 90)kategorie = 10; // 2800
        else if(vaha < 95)kategorie = 11; // 2900
        else if(vaha < 100)kategorie = 12;
        else if(vaha < 105)kategorie = 13;
        else if(vaha < 110)kategorie = 14;
        else if(vaha < 115)kategorie = 15;
        else if(vaha < 120)kategorie = 16;
        else if(vaha < 125)kategorie = 17;

        else kategorie = 18; // 3000

        switch(kategorie){
            case 1:
                //if(res1.getCount() > 0)
                kolikVypit = 1300 + vypitNavic;
                if(pohlavi) kolikVypit-=260;
                //else kolikVypit = 1300;
                break;
            case 2:
                //if(res1.getCount() > 0)
                kolikVypit = 1500 + vypitNavic;
                if(pohlavi) kolikVypit-=240;
                break;
            case 3:
                //if(res1.getCount() > 0)
                kolikVypit = 1650 + vypitNavic;
                if(pohlavi) kolikVypit-=180;
                break;
            case 4:
                //if(res1.getCount() > 0)
                kolikVypit = 1850 + vypitNavic;
                if(pohlavi) kolikVypit-=200;
                break;
            case 5:
                //if(res1.getCount() > 0)
                kolikVypit = 2000 + vypitNavic;
                if(pohlavi) kolikVypit-=170;
                break;
            case 6:
                //if(res1.getCount() > 0)
                kolikVypit = 2200 + vypitNavic;
                if(pohlavi) kolikVypit-=210;
                break;
            case 7:
                //if(res1.getCount() > 0)
                kolikVypit = 2350 + vypitNavic;
                if(pohlavi) kolikVypit-=210;
                break;
            case 8:
                //if(res1.getCount() > 0)
                kolikVypit = 2500 + vypitNavic;
                if(pohlavi) kolikVypit-=230;
                break;
            case 9:
                //if(res1.getCount() > 0)
                kolikVypit = 2650 + vypitNavic;
                if(pohlavi) kolikVypit-=250;
                break;
            case 10:
                //if(res1.getCount() > 0)
                kolikVypit = 2800 + vypitNavic;
                if(pohlavi) kolikVypit-=270;
                break;
            case 11:
                //if(res1.getCount() > 0)
                kolikVypit = 2900 + vypitNavic;
                if(pohlavi) kolikVypit-=260;
                break;
            case 12:
                //if(res1.getCount() > 0)
                kolikVypit = 3060 + vypitNavic;
                if(pohlavi) kolikVypit-=310;
                break;
            case 13:
                //if(res1.getCount() > 0)
                kolikVypit = 3180 + vypitNavic;
                if(pohlavi) kolikVypit-=320;
                break;
            case 14:
                //if(res1.getCount() > 0)
                kolikVypit = 3290 + vypitNavic;
                if(pohlavi) kolikVypit-=330;
                break;
            case 15:
                //if(res1.getCount() > 0)
                kolikVypit = 3400 + vypitNavic;
                if(pohlavi) kolikVypit-=340;
                break;
            case 16:
                //if(res1.getCount() > 0)
                kolikVypit = 3500 + vypitNavic;
                if(pohlavi) kolikVypit-=350;
                break;
            case 17:
                //if(res1.getCount() > 0)
                kolikVypit = 3590 + vypitNavic;
                if(pohlavi) kolikVypit-=350;
                break;
            case 18:
                //if(res1.getCount() > 0)
                kolikVypit = 3800 + vypitNavic;
                if(pohlavi) kolikVypit-=380;
                break;



        }

        vypisDoFormu();

    }
    public void vypisDoFormu()
    {
        String helper;
        Cursor res = DB2.VypisDat();
        StringBuilder stringBuilder1 = new StringBuilder();
        if(res.getCount()>0) {
            while (res.moveToNext()) {
                stringBuilder1.append(res.getString(0));

            }
            helper = stringBuilder1.toString();
            kolikVypito = Integer.valueOf(helper);
        }
        else kolikVypito = 0;



    }
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "drinkingtimeReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("drinkingtime", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    public void saveDayStat(){

        /*String helper = String.valueOf(kolikVypito);
        Boolean checkinsertdata = DBHistory.insertData(helper);
        if(checkinsertdata==true)
        {
            Toast.makeText(HlavniStrana.this, "Uloženo!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(HlavniStrana.this, "Mise selhala!", Toast.LENGTH_SHORT).show();
        }*/

    }
    public void getStats(){
        Cursor cursor = DBHistory.vypisHistorie();
        StringBuilder stringBuilder = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));

        }

        String helper = String.valueOf(stringBuilder);

        Toast.makeText(HlavniStrana.this, helper, Toast.LENGTH_SHORT).show();

    }
    public void setTime(){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)+1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    public void setAlarm(){
        setTime();

        Intent intent = new Intent(HlavniStrana.this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(HlavniStrana.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //Toast.makeText(this, String.valueOf(calendar.getTime()), Toast.LENGTH_SHORT).show();


        //Toast.makeText(this, "Upozornění nastaveno", Toast.LENGTH_SHORT).show();



    }
    public void UlozData(){
        String vypito = String.valueOf(kolikVypito);
        kolikVypito = 0;
        DB2.deleteData();
        DB3.deleteData();
        /*Cursor cursor = DBHistory.vypisID();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));



        }


        String helper = String.valueOf(stringBuilder);

        Toast.makeText(HlavniStrana.this, helper, Toast.LENGTH_SHORT).show();

        //if(DBHistory.insertData(vypito, Integer.valueOf(helper)+1))Toast.makeText(this, "Funguje", Toast.LENGTH_SHORT).show();*/

    }






}
