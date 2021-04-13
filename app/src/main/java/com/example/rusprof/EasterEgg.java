package com.example.rusprof;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class EasterEgg extends AppCompatActivity {//Эта активность создана для людей, которые любят находить секреты)))))[//класс для activity_easteregg]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateregg);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// устанавливаем только вертикальную ориентацию телефона
    }
}
