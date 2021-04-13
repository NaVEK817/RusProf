package com.example.rusprof;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
public class WordView extends View{//класс для отрисовки задания в activity_orfo
    static int number=-1;//переменное, для определения положения ударения. Изначально стоит -1 во избежании ошибок
    private String word = "";//переменная для самого слова, в котором надо поставить ударение
    private float x = 0, y = 0;//координаты ударения
    Bitmap bitmap;//переменная для знака ударения
    public void setWord(String word){// метод для установления слова
        this.word=word;
        invalidate();
    }
    public String getWord(){// метод для взятия значения слова.
        invalidate();
        return word;
    }
    public WordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WordsView);
        word = typedArray.getString(R.styleable.WordsView_word);
        typedArray.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.znak);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){//переопределяем метод для того, чтобы когда пользователь коснулся букву, то над ним установилось ударение
        x = event.getX();
        y = event.getY();
        invalidate();
        return super.onTouchEvent(event);
    }
    public void ClearWord(){// метод для очистки значений при переходе к новому заданию
        this.x=-1;
        this.y=-1;
        this.number=-1;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(word.length() == 0){// если нет слова, то ничего не будет выводиться
            return;
        }
        Paint changedLetterBackgroundPaint=new Paint();//фон после нажатия
        Paint letterBackgroundPaint=new Paint();//задний фон буквы
        Paint letterPaint=new Paint();//рисунок маленькой буквы
        Paint bigLetterPaint=new Paint();//рисунок большой буквы
        letterPaint.setColor(Color.BLACK);//устанавливаем цвет рисунка
        changedLetterBackgroundPaint.setColor(Color.CYAN);//устанавливаем цвет рисунка
        letterBackgroundPaint.setColor(Color.parseColor("#00F44336"));//устанавливаем цвет рисунка
        int margin = 20;//устанавливаем границу от экрана
        int canvasSize = (canvas.getWidth() - margin * 2);//устанавливаем ширину экрана
        int letterSize = canvasSize / word.length();//получаем ширину буквы
        int marginTop = letterSize;// устанавливаем отступ сверху
        letterPaint.setTextSize(letterSize);//устанавливаем ширину буквы
        letterPaint.setTypeface(Typeface.create("System", Typeface.NORMAL));//устанавливаем шрифт
        bigLetterPaint.setTextSize(letterSize * 3);//устанавливаем ширину большой буквы
        for(int i = 0; i < word.length(); i++){//отрисовываем буквы
            if(margin + i * letterSize <= x && x <= margin + (i + 1) * letterSize - 2 && marginTop <= y && y <= marginTop + letterSize){
                number=i+1;
                canvas.drawBitmap(bitmap,
                        new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                        new Rect(margin + i * letterSize, marginTop - letterSize, margin + (i + 1) * letterSize, marginTop),
                        changedLetterBackgroundPaint);
            }
            else{
                canvas.drawRect(margin + i * letterSize,marginTop, margin + (i + 1) * letterSize - 2, marginTop + letterSize, letterBackgroundPaint);
            }
            if(x < margin && y < marginTop || x > margin + word.length() - 1 && y > marginTop + letterSize ){
                number = -1;
            }
            canvas.drawText(String.valueOf(word.charAt(i)),margin + i * letterSize, marginTop + letterSize, letterPaint);
        }
    }
}
