package com.example.demoexammobile.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoexammobile.API.Class.signInParam;
import com.example.demoexammobile.API.Interface.API;
import com.example.demoexammobile.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//Класс входа пользователя
public class SignIn extends AppCompatActivity
{
    private EditText email, password;
    private TextView change;
    private Button submit;
    private API api;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        api = API.retrofit.create(API.class);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        change = findViewById(R.id.change);
        submit = findViewById(R.id.button);
        //Кнопки перехода между макетами
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                func();
            }
        });
        change.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    //проверка почты
    private boolean emailValid(String email)
    {
        Pattern pattern = Pattern.compile(".+@[a-z]+\\.[a-z]+");
        Matcher emailMatcher = pattern.matcher(email);
        return emailMatcher.matches();
    }
    //всплывающее окно
    private void Dialoger(Activity activity, String error)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).setTitle("Сообщение").setMessage(error).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        }).create();
        alertDialog.show();
    }
    //api - запрос
    private void func()
    {
        if(email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Dialoger(SignIn.this, "Заполните все пустые полня!");
        }
        else if(!emailValid(email.getText().toString()))
        {
            Dialoger(SignIn.this, "Неправильная почта!");
        }
        else
        {
            signInParam param = new signInParam();
            param.setEmail(email.getText().toString());
            param.setPassword(password.getText().toString());
            Call<signInParam> call = api.doAuth(param);
            call.enqueue(new Callback<signInParam>()
            {
                @Override
                public void onResponse(Call<signInParam> call, Response<signInParam> response)
                {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Успешный вход!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Dialoger(SignIn.this, "Неправильно введенные данные!");
                    }
                }

                @Override
                public void onFailure(Call<signInParam> call, Throwable t)
                {
                    Dialoger(SignIn.this, t.getMessage());
                }
            });
        }
    }
}