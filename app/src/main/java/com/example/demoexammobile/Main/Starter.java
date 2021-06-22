package com.example.demoexammobile.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.demoexammobile.API.Interface.API;
import com.example.demoexammobile.R;

import java.util.Timer;
import java.util.TimerTask;
//Имитация загрузки
public class Starter extends AppCompatActivity
{
    public static boolean ep;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        //Таймер и назначение времени для выполнения задачи переменной timerTask
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent;
                if(ep)
                {
                    intent = new Intent(Starter.this, SignIn.class);
                }
                else
                {
                    intent = new Intent(Starter.this, SignUp.class);
                }
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 2000L);
    }
}