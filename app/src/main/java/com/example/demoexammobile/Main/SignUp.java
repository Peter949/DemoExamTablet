package com.example.demoexammobile.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.util.StateSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoexammobile.API.Class.signUpParam;
import com.example.demoexammobile.API.Interface.API;
import com.example.demoexammobile.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//Класс регистрации пользователя
public class SignUp extends AppCompatActivity
{
    private API api;
    private Retrofit retrofit;
    private Button submit;
    private EditText email, password, repeat, firstName, lastName;
    private TextView change;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        //Переключение между макетами
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
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
    //Получение стартовых переменных
    private void init()
    {
        retrofit = API.retrofit;
        api = retrofit.create(API.class);
        submit = findViewById(R.id.authButton);
        email = findViewById(R.id.authEmail);
        password = findViewById(R.id.authPassword);
        repeat = findViewById(R.id.authRepeat);
        firstName = findViewById(R.id.authFirstName);
        lastName = findViewById(R.id.authLastName);
        change = findViewById(R.id.authChange);
    }
    //Проверка почты
    private boolean emailValid(String email)
    {
        Pattern emailPattern = Pattern.compile(".+@[a-z]+\\.[a-z]+");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }
    //Всплывающее окно
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
        if(email.getText().toString().equals("") || password.getText().toString().equals("") || repeat.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals(""))
        {
            Dialoger(SignUp.this, "Заполните все пустые поля!");
        }
        else if(!emailValid(email.getText().toString()))
        {
            Dialoger(SignUp.this, "Неправильная почта!");
        }
        else if(!password.getText().toString().equals(repeat.getText().toString()))
        {
            Dialoger(SignUp.this, "Пароли не совпадают!");
        }
        else
        {
            signUpParam param = new signUpParam();
            param.setEmail(email.getText().toString());
            param.setPassword(password.getText().toString());
            param.setFirstName(firstName.getText().toString());
            param.setLastName(lastName.getText().toString());
            Call<signUpParam> call = api.doRegister(param);
            call.enqueue(new Callback<signUpParam>()
            {
                @Override
                public void onResponse(Call<signUpParam> call, Response<signUpParam> response)
                {
                    if(response.isSuccessful())
                    {

                    }
                    else
                    {

                    }
                }
                @Override
                public void onFailure(Call<signUpParam> call, Throwable t)
                {
                    Dialoger(SignUp.this, "Успешная регистрация!");
                    Starter.ep = true;
                }
            });
        }
    }
}