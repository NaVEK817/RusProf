package com.example.rusprof;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
public class Getdatapunkt {//парсированная строка из БД для пунктуации
    public Boolean status;//переменная для определения статуса
    public String sql;//парсированная строка из БД
    private ArrayList<Punktget> data;//массив для данных
    public void setData(ArrayList<Punktget> users){
        this.data = users;
    }//переводим массив данных в читаемый для андроида вид
    public ArrayList<Punktget>getData(){
        return this.data;
    }//определяем массив данных для работы с ним в самом коде
    public String getUsersString(){
        String str = "";
        for(Punktget u: data){
            str += u.getString() + "\n";
        }
        return str;
    }//перевод из парсированной строки в нормальную строку
    class Punktget {
        public String task, answer;
        public int id;
        public String getString(){
            return id + " " + task + " " + answer;
        }
    }//класс, который выводит нормальную строку из таблицы для работы в android
}
interface UserServicepunkt{
    @GET("selectpunkt.php")
    Call<Getdatapunkt> getAnswer();
}//указываем файл,откуда передаётся БД
