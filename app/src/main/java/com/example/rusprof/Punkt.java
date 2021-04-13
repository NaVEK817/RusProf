package com.example.rusprof;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Punkt extends AppCompatActivity {//класс для activity_punkt
    EditText task;
    TextView chet;
    ArrayList<Getdatapunkt.Punktget> a;// ArrayList, где содержатся все передванный строки БД
    View zad,ready,itg;
    @Override
    public void onBackPressed(){}//переопределяем кнопку назад телефона, чтобы пользователь не мог переключать активности вне кнопок приложения
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkt);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
        Punkt.AsyncSelectTask asyncSelectTask = new Punkt.AsyncSelectTask();// подключаемся к БД
         if (HasConnection.hasConnection(this)) {//проверяем на Интернет подключение
            asyncSelectTask.execute(this.getString(R.string.connection));// подключаемся к БД
        }
        else{
            AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
            quitDialog.setCancelable(false);
            quitDialog.setTitle("Без Интернета приложение не работает.");
            quitDialog.setPositiveButton("Выйти из приложения", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quitDialog.show();
        }
        Itog.all=0;// устанавливаем счётчик заданий
        Itog.ri=0;// устанавливаем счётчик верно решённых заданий
        zad=findViewById(R.id.zad);// определяем кнопку для отображения диалогового окна с инструкцией к заданиям
        ready = findViewById(R.id.ready);// определяем кнопку для перехода к новому заданию
        task=findViewById(R.id.texttaskpunkt);// определяем само задание
       // task.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        chet=findViewById(R.id.kol_zad);// определяем строку для отображения номера задания
        chet.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        itg=findViewById(R.id.itg);// определяем кнопку для перехода в активность activity_itog
        zad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragmentpunkt dialog = new CustomDialogFragmentpunkt();
                dialog.show(getSupportFragmentManager(), "custom");
            }
        });
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {//определяем правильность введённого ответа. Подчёркиваем красным если неправильный, а зелёным - правильный
                if(task.getText().toString().equals(a.get(0).answer)){
                    Itog.ri++;
                    task.setBackgroundColor(ContextCompat.getColor( Punkt.this, R.color.greenColor));
                }
                else {
                    task.setBackgroundColor(ContextCompat.getColor( Punkt.this, R.color.redColor));
                    if(!Mistakes.punktgetArrayList.contains(a.get(0))){//проверяем, есть ли этот элемент для корректной работы приложения
                        Mistakes.punktgetArrayList.add(a.get(0)); //добавляем элемент в специальный лист, который собирает задания, на которые ответили неправильно
                    }
                }
                AsyncTask asyncTask = new AsyncTask() {//поток для смены заданий
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        v.setEnabled(false);//отключаем кнопкку для корректной работ приложения
                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {//задержка в переходе, чтобы было понятно пользователю правильность введёного ответа
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Object o) {// непосредственно переход заданий путём удаления 0 элемента ArrayList<Getdatapunkt.Punktget> a. Если элементов больше нет - переход к активности  activity_itog
                        super.onPostExecute(o);
                        if(a.size() > 1) {
                            a.remove(0);//убираем старое задание
                            task.setText(a.get(0).task);//устанавливаем новое задание
                        }
                        else{
                            Intent intent = new Intent(Punkt.this, Itog.class);
                            startActivity(intent);
                            finish();
                        }
                        Itog.all++;
                        chet.setText(String.valueOf(Itog.all+1));
                        task.setBackgroundColor(ContextCompat.getColor( Punkt.this, R.color.proz));//меняем цвет, дабы он не оставался красным/зелёным при переходе на новое задание
                        v.setEnabled(true);//включаем кнопкку для корректной работ приложения
                    }
                };
                asyncTask.execute();
            }
        });
        chet.setText(String.valueOf(Itog.all+1));
        itg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Punkt.this, Itog.class);
                startActivity(intent);
                finish();
            }
        });
        task.addTextChangedListener(new TextWatcher() {//через метод настраиваем кнопку enter на скрытия клавиатуры
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {
                for(int i = s.length()-1; i >= 0; i--){
                    if(s.charAt(i) == '\n'){
                        s.delete(i, i + 1);
                        HideKeyboard.hideKeyboard(Punkt.this);
                        task.clearFocus();
                        return;
                    }
                }
            }
        });
    }
    class AsyncSelectTask extends AsyncTask<String, Integer, Getdatapunkt> {// создаём поток для взаимодействия с БД
        @Override
        protected void onPreExecute(){}
        @Override
        protected void onProgressUpdate(Integer...  values){
            super.onProgressUpdate(values);
        }
        @Override
        protected Getdatapunkt doInBackground(String... strings) {
            Getdatapunkt answer = null;
            Retrofit retrofit = new Retrofit.Builder().baseUrl(strings[0]).addConverterFactory(GsonConverterFactory.create()).build();
            UserServicepunkt service = retrofit.create(UserServicepunkt.class);
            try {
                Call<Getdatapunkt> call2 = service.getAnswer();
                Response<Getdatapunkt> response = call2.execute();
                answer = response.body();
                Boolean status = response.body().status;
                ArrayList<Getdatapunkt.Punktget> users = new ArrayList<>(response.body().getData());
                String sql = response.body().sql;
            }catch (Exception e){
                e.printStackTrace();
            }
            return answer;
        }
        @Override
        protected void onPostExecute(Getdatapunkt answer){
            super.onPostExecute(answer);
            if(answer != null){
                a = answer.getData();
                Collections.shuffle(a);
                task.append(a.get(0).task);
            }
//            else{
//                AlertDialog.Builder quitDialog = new AlertDialog.Builder(getApplicationContext());
//                quitDialog.setCancelable(false);
//                quitDialog.setTitle("Без Интернета приложение не работает.");
//                quitDialog.setPositiveButton("Выйти из приложения", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                quitDialog.show();
//            }
        }
    }
}
