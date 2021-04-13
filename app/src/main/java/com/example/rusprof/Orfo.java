package com.example.rusprof;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
public class Orfo extends AppCompatActivity {//класс для activity_orfo
    TextView chet;
    WordView task;
    View zad, ready, itg;
    ArrayList<Getdataorfo.Orfoget> a;// ArrayList, где содержатся все передванный строки БД
    @Override
    public void onBackPressed(){}//переопределяем кнопку назад телефона, чтобы пользователь не мог переключать активности вне кнопок приложения
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orfo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
        AsyncSelectTask asyncSelectTask = new AsyncSelectTask();
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
        zad = findViewById(R.id.zad);// определяем кнопку для отображения диалогового окна с инструкцией к заданиям
        ready = findViewById(R.id.ready);// определяем кнопку для перехода к новому заданию
        chet=findViewById(R.id.kol_zad);// определяем строку для отображения номера задания
        chet.setTypeface(Typeface.create("System", Typeface.NORMAL));// устанавливаем шрифт
        itg= findViewById(R.id.itg);// определяем кнопку для перехода в активность activity_itog
        task= findViewById(R.id.tV);// определяем само задание
        task.invalidate();// отрисовывам задание
        zad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragmentorfo dialog = new CustomDialogFragmentorfo();
                dialog.show(getSupportFragmentManager(), "custom");
            }
        });
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {//определяем правильность введённого ответа. Подчёркиваем красным если неправильный, а зелёным - правильный
                if(WordView.number==a.get(0).answer){
                    task.setBackgroundColor(ContextCompat.getColor( Orfo.this, R.color.greenColor));
                    Itog.ri++;
                }
                else {
                    task.setBackgroundColor(ContextCompat.getColor( Orfo.this, R.color.redColor));
                    if(!Mistakes.orfogetArrayList.contains(a.get(0))){//проверяем, есть ли этот элемент для корректной работы приложения
                        Mistakes.orfogetArrayList.add(a.get(0)); //добавляем элемент в специальный лист, который собирает задания, на которые ответили неправильно
                    }
                }
                AsyncTask asyncTask = new AsyncTask() {//создаём поток для смены заданий
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
                    protected void onPostExecute(Object o) {// непосредственно переход заданий путём удаления 0 элемента ArrayList<Getdataorfo.Orfoget> a. Если элементов больше нет - переход к активности  activity_itog
                        super.onPostExecute(o);
                        if(a.size() > 1) {
                            a.remove(0);//убираем старое задание
                            task.ClearWord();//стираем старый рисунок
                            task.setWord(a.get(0).task);//устанавливаем новое задание
                            task.invalidate();// отрисовывам задание
                        }
                        else{
                            Intent intent = new Intent(Orfo.this, Itog.class);
                            startActivity(intent);
                            finish();
                        }
                        Itog.all++;
                        chet.setText(String.valueOf(Itog.all+1));
                        task.setBackgroundColor(ContextCompat.getColor( Orfo.this, R.color.proz));//меняем цвет, дабы он не оставался красным/зелёным при переходе на новое задание
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
                Intent intent = new Intent(Orfo.this, Itog.class);
                startActivity(intent);
                finish();
            }
        });
    }
    class AsyncSelectTask extends AsyncTask<String, Integer, Getdataorfo> {// создаём поток для взаимодействия с БД
        @Override
        protected void onPreExecute(){
//            if(a==null && a.isEmpty()){
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
        @Override
        protected void onProgressUpdate(Integer...  values){
            super.onProgressUpdate(values);
        }
        @Override
        protected Getdataorfo doInBackground(String... strings) {//создаём запрос для получания данных
            Getdataorfo answer = null;
            Retrofit retrofit = new Retrofit.Builder().baseUrl(strings[0]).addConverterFactory(GsonConverterFactory.create()).build();
            UserServiceorfo service = retrofit.create(UserServiceorfo.class);
            try {
                Call<Getdataorfo> call = service.getAnswer();
                Response<Getdataorfo> response = call.execute();
                answer = response.body();
                Boolean status = response.body().status;
                ArrayList<Getdataorfo.Orfoget> users = new ArrayList<>(response.body().getData());
                String sql = response.body().sql;
            }catch (Exception e){
                e.printStackTrace();
            }
            return answer;
        }
        @Override
        protected void onPostExecute(Getdataorfo answer){//перемешиваем элементы листа ради обновления заданий
            super.onPostExecute(answer);
            if(answer != null){
                a = answer.getData();
                Collections.shuffle(a);
                task.setWord(a.get(0).task);
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



