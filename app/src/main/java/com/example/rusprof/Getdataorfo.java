package com.example.rusprof;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
public class Getdataorfo {//класс для получение данных с БД для орфоэпии
        public Boolean status;//переменная для определения статуса
        public String sql;//парсированная строка из БД
        private ArrayList<Orfoget> data;//массив для данных
        public void setData(ArrayList<Orfoget> users){
            this.data = users;
        }//переводим массив данных в читаемый для андроида вид
        public ArrayList<Orfoget>getData(){
            return this.data;
        }//определяем массив данных для работы с ним в самом коде
        public String getUsersString(){
            String str = "";
            for(Orfoget u: data){
                str += u.getString() + "\n";
            }
            return str;
        }//перевод из парсированной строки в нормальную строку
    class Orfoget {
        public String task;
        public int id, answer;
        public String getString(){
            return id + " " + task + " " + answer;
        }
     }//класс, который выводит нормальную строку из таблицы для работы в android
    }
interface UserServiceorfo{
    @GET("selectorfo.php")
    Call<Getdataorfo> getAnswer();
}//указываем файл,откуда передаётся БД
