package com.example.rusprof;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class Mistakes extends AppCompatActivity {
   static TextView mistakes;
    View mainmenu;
    static ArrayList<Getdataorfo.Orfoget> orfogetArrayList = new ArrayList<Getdataorfo.Orfoget>();// создаём лист для захвата ошибочных ответов по орфоэпии
    static ArrayList<Getdatamorf.Morfget> morfgetArrayList = new ArrayList<Getdatamorf.Morfget>();// создаём лист для захвата ошибочных ответов по морфемике
    static ArrayList<Getdatapunkt.Punktget> punktgetArrayList = new ArrayList<Getdatapunkt.Punktget>();// создаём лист для захвата ошибочных ответов по пунктуации
    @Override
    public void onBackPressed(){}//переопределяем кнопку назад телефона, чтобы пользователь не мог переключать активности вне кнопок приложения
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistakes);
        mistakes=findViewById(R.id.TEXT_STATUS_ID);// определяем TextView, где будут отображатся все "ошибочные" элементы
        mistakes.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        mistakes.setMovementMethod(new ScrollingMovementMethod());// устанавливаем метод прокрутки TextView, дабы все элементы уместились
        mainmenu=findViewById(R.id.mainmenu);//определяем кнопку для перехода в главное меню
        for(Getdatapunkt.Punktget el : punktgetArrayList){//выводим правильные ответы на пунктуацию
            mistakes.setText(mistakes.getText()+String.valueOf(el.answer) + "\n");
        }
        String s;
        for(Getdataorfo.Orfoget el : orfogetArrayList){//выводим правильные ответы на орфоэпию
            s = el.task.substring(0, el.answer - 1) + ("" + el.task.charAt(el.answer-1)).toUpperCase() + el.task.substring(el.answer);
            mistakes.setText(mistakes.getText()+s + "\n");
        }
        for(Getdatamorf.Morfget el : morfgetArrayList){//выводим правильные ответы на пунктуацию
            mistakes.setText(mistakes.getText()+ String.valueOf(el.task) + " - " + String.valueOf(el.answer) +"\n");
        }
        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mistakes.this, Itog.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
