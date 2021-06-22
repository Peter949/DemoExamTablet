package com.example.demoexammobile.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoexammobile.R;
import com.squareup.picasso.Picasso;
//Класс показывает обложку фильма
public class KinoActivity extends AppCompatActivity
{
    private TextView des, name, age;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kino);
        //Получение данных
        Intent intent = getIntent();
        des = findViewById(R.id.desKino);
        name = findViewById(R.id.nameKino);
        age = findViewById(R.id.ageKino);
        image = findViewById(R.id.imageKino);
        des.setText(intent.getStringExtra("des"));
        name.setText(intent.getStringExtra("name"));
        Picasso.with(this).load("http://cinema.areas.su/up/images/" + intent.getStringExtra("image")).resize(500, 800).into(image);
        int colorAge = intent.getIntExtra("age", 0);
        //Использование оператора switch для присвоения цвета и текста
        switch (colorAge)
        {
            case 0:
                age.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.holo_green_light), PorterDuff.Mode.MULTIPLY);
                break;
            case 6:
                age.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.holo_green_light), PorterDuff.Mode.MULTIPLY);
                break;
            case 12:
                age.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.holo_orange_light), PorterDuff.Mode.MULTIPLY);
                break;
            case 16:
                age.setBackgroundColor(Color.YELLOW);
                age.setText("16" + "+");
                break;
            case 18:
                age.setBackgroundColor(Color.RED);
                age.setText("18" + "+");
                break;
        }
    }
}