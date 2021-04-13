package com.example.rusprof;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {//класс для activity_main
    ImageButton secret, mainchoose, exitall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
        secret = findViewById(R.id.easter_egg);//определяем кнопку для выхода в секетную активность activity_easteregg
        mainchoose = findViewById(R.id.mainchoose_btn);//определяем кнопку для перехода в меню выбора activity_setting
        exitall = findViewById(R.id.exitALL);//определяем кнопку для выхода из приложения
        secret.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EasterEgg.class);
                startActivity(intent);
            }
        });
        mainchoose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                finish();
            }
        });
        exitall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        if (HasConnection.hasConnection(this)) {//проверяем на Интернет подключение
//        }
//        else{
//            AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
//            quitDialog.setCancelable(false);
//            quitDialog.setTitle("Без Интернета приложение не работает.");
//            quitDialog.setPositiveButton("Выйти из приложения", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            quitDialog.show();
//        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Вы точно хотите выйти из приложения?");
        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        quitDialog.show();
    }//переопределение кнопки выхода назад через телефон
}