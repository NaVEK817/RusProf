package com.example.rusprof;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
public class Settings extends AppCompatActivity {//класс для activity_setting
    ImageButton ofrosetbtn,punktsetbtn,morfsetbtn;
    @Override
    public void onBackPressed(){}//переопределяем кнопку назад телефона, чтобы пользователь не мог переключать активности вне кнопок приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
        ofrosetbtn=findViewById(R.id.ofrosetbtn);//определяем кнопку для перехода в активность activity_orfo
        punktsetbtn=findViewById(R.id.punktsetbtn);//определяем кнопку для перехода в активность activity_punkt
        morfsetbtn=findViewById(R.id.morfsetbtn);//определяем кнопку для перехода в активность activity_morf
        ofrosetbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Orfo.class);
                startActivity(intent);
                finish();
            }
        });
        punktsetbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Punkt.class);
                startActivity(intent);
                finish();
            }
        });
        morfsetbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Morf.class);
                startActivity(intent);
                finish();
            }
        });
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(Settings.this);//выводим интсрукцию к действиям на экран
        builder.setTitle("Инструкция");
        builder.setMessage("Перед вами сейчас меню выбора разделов русского языка с соответствующимим тестами. Чтобы начать тест, вам нужно выбрать один из этих разделов. Чтобы узнать инструкции по выполнению теста, вам следует нажать на кнопке Задание.После выполнения задания, перед вами будет представлена ваша статистика и оценка. 5 ставится за 90% выполненных верно заданий. 4 ставится за 75% выполненных верно заданий и менее 90%. 3 ставится за 60% выполненных верно заданий и менее 75%. 2 ставится за менее 60% выполненных верно заданий.");
        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
