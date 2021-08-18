package com.itproger.myactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button convert_btn;
    private EditText user_textField;
    private TextView result_textView;
    private ImageButton btn_next_activity;
    private ImageButton imageButton_turn;
    private ImageView imageView_turn1;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        convert_btn = findViewById(R.id.convert_btn);
        user_textField = findViewById(R.id.user_textField);
        result_textView = findViewById(R.id.result_textView);
        btn_next_activity = findViewById(R.id.btn_next_activity);
        imageButton_turn = findViewById(R.id.imageButton_turn);
        imageView_turn1 = findViewById(R.id.imageView_turn1);

        int orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageButton_turn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    if (count > 3)
                        count = 1;

                    switch (count) {
                        case 1:
                            imageView_turn1.setImageResource(R.drawable.ic_back1);
                            break;
                        case 2:
                            imageView_turn1.setImageResource(R.drawable.cycle_refresh_turn_icon);
                            break;
                        case 3:
                            imageView_turn1.setImageResource(R.drawable.ic_back2);
                            break;
                        default:
                            imageView_turn1.setImageResource(R.drawable.ic_like);
                            break;
                    }
                }
            });
        }

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            convert_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Вы хотите произвести конвертацию в мили?");
                    builder.setCancelable(false);    //Эта функция дает право на отмену всплывающего окна
                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {         //создаем кнопку и отслеживаем действие нажатия на кнопку
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Convert();
                        }
                    });

                    builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Действие отменено", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Конвертация данных");
                    alertDialog.show();
                }
            });

            btn_next_activity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("com.itproger.myactivity.SecondActivity");
                    startActivity(intent);
                }
            });
        }
    }
        @SuppressLint("SetTextI18n")
        public void Convert () {
            String text = user_textField.getText().toString();
            if (!text.equals("")) {
                float number = Float.parseFloat(text);
                number *= 1.6;      //для конвертации миль в километры
                result_textView.setText(Float.toString(number));
                convert_btn.setText("Готово");
                convert_btn.setBackgroundColor(Color.parseColor("#81F79F"));
            } else {
//            convert_btn.setText("Ошибка");
                Toast.makeText(getApplicationContext(), "Вы не ввели число", Toast.LENGTH_LONG).show();
                convert_btn.setText("Конвертировать");

            }
        }

        //ВТОРОЙ ВАРИАНТ привязки функции к отклику на нажатие кнопки  + прописать в .xml в общих аттрибутах->onClick  функцию Convert
//    @SuppressLint("SetTextI18n")
//    public void Convert(View v){
//        EditText user_textField = findViewById(R.id.user_textField);
//        TextView result_textView = findViewById(R.id.result_textView);
//
//        String text = user_textField.getText().toString();
//
//        if(!text.equals("")){
//            float number = Float.parseFloat(text);
//            number *= 1.6;      //для конвертации миль в километры
//            result_textView.setText(Float.toString(number));
//        }

    }

