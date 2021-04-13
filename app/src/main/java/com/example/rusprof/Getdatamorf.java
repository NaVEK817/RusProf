package com.example.rusprof;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
public class Getdatamorf {//класс для получение данных с БД для морфемики
    public Boolean status;//переменная для определения статуса
    public String sql;//парсированная строка из БД
    private ArrayList<Morfget> data;//массив для данных
    public void setData(ArrayList<Morfget> users){
        this.data = users;
    }//переводим массив данных в читаемый для андроида вид
    public ArrayList<Morfget>getData(){
        return this.data;
    }//определяем массив данных для работы с ним в самом коде
    public String getUsersString(){
        String str = "";
        for(Morfget u: data){
            str += u.getString() + "\n";
        }
        return str;
    }//перевод из парсированной строки в нормальную строку
    class Morfget {
        public String task, answer,part;
        public int id;
        public String getString(){
            return id + " " + task +""+ part+ " " + answer;
        }
    }//класс, который выводит нормальную строку из таблицы для работы в android
}
interface UserServicemorf{
    @GET("selectmorf.php")
    Call<Getdatamorf> getAnswer();
}//указываем файл,откуда передаётся БД
