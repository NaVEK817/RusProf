package com.example.rusprof;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class CustomDialogFragmentorfo extends DialogFragment{//класс для кнопки zad в activity_orfo
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {//создаём диалоговое окно с инструкцией к заданию
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder.setTitle("Задание").setMessage("Дано слово. Вам нужно отметить букву, на которую падает ударение. Если вы уверены в своём ответе - нажмите Готово. Если вы хотите закончить - нажмите Итог. Под кнопкой Задание будет указан номер задания.").create();
    }
}
