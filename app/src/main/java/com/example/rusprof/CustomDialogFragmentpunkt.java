package com.example.rusprof;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class CustomDialogFragmentpunkt extends DialogFragment{//класс для кнопки zad в activity_punkt
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {//создаём диалоговое окно с инструкцией к заданию
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder.setTitle("Задание").setMessage("Дано предложение. Вам нужно проставить недостающие знаки препинания. Если вы уверены в своём ответе - нажмите Готово. Если вы хотите закончить - нажмите Итог. Под кнопкой Задание будет указан номер задания. ПОМНИТЕ: запятая ставится без пробела от первого слова, а перед следующим словом идёт пробел. Пример: слово, слово.").create();
    }
}