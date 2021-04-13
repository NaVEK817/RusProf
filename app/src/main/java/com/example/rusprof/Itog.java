package com.example.rusprof;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Itog extends AppCompatActivity {//класс для activity_itog
    static int all=0,ri=0,ot=0;// устанавливаем счётчики(перечисление в соответствии тому, как они идут друг за другом): всех задани, правильных ответов, оценку
    double per=0;// переменная для определения процентов
    View mainmenu,mistakes;
    TextView rig,pers,otm,al;
    @Override
    public void onBackPressed(){}//переопределяем кнопку назад телефона, чтобы пользователь не мог переключать активности вне кнопок приложения
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itog);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
        mainmenu=findViewById(R.id.mainmenu);// определяем кнопку для перехода к activity_main
        mistakes=findViewById(R.id.mistakes);// определяем кнопку для перехода к mistakes
        al=findViewById(R.id.textall);// определяем TextView для отображения счётчика заданий
        rig=findViewById(R.id.resh);// определяем TextView для отображения счётчика правильных ответов
        pers=findViewById(R.id.proc);// определяем TextView для отображения процентов правильновыполненных заданий
        otm=findViewById(R.id.oc);// определяем TextView для отображения отметки
        rig.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        pers.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        otm.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        al.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        mainmenu.setOnClickListener(new View.OnClickListener() {//при переходе мы также обнуляем все элементы и значения, дабы они не сохранялиь
            @Override
            public void onClick(View v) {
                Mistakes.punktgetArrayList.clear();
                Mistakes.orfogetArrayList.clear();
                Mistakes.morfgetArrayList.clear();
                all=0;
                ri=0;
                ot=0;
                per=0;
                Intent intent = new Intent(Itog.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Itog.this, Mistakes.class);
                startActivity(intent);
                finish();
            }
        });
        al.setText(String.valueOf(all));
        rig.setText(String.valueOf(ri));
        if(all!=0){//смотрим, чтобы пользователь сделал хоть одно задание
            per=1.0 * ri/all*100;
        }
        pers.setText(String.valueOf(Math.round(per)) + "%");
        Otsenka(per);//определяем оценку
        otm.setText(String.valueOf(ot));
    }
    public double Otsenka(double a){//создаём метод для определения оценки
        if(a>=90){
            ot=5;
        }
        if(a>=75 &&a<90){
            ot=4;
        }
        if(a>=60 && a<75){
            ot=3;
        }
        if(a<60){
            ot=2;
        }
        return 0;
    }
}
