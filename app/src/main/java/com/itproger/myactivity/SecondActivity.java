package com.itproger.myactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button sec_activ_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentManager fragmentManager = getSupportFragmentManager();      //получаем все доступные фрагменты
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();       //возможность перехода между  фрагментами
        FirstFragment firstFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.fragment, firstFragment);          //заменяем содержимое фрагмента на новый фрагмент
        fragmentTransaction.commit();

        sec_activ_btn = findViewById(R.id.sec_activ_btn);
        sec_activ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Change_fragment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(view.getId()){
            case R.id.fragment_btn1:
                FirstFragment firstFragment = new FirstFragment();
                fragmentTransaction.replace(R.id.fragment, firstFragment);
                break;
            case R.id.fragment_btn2:
                SecondFragment secondFragment = new SecondFragment();
                fragmentTransaction.replace(R.id.fragment, secondFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
