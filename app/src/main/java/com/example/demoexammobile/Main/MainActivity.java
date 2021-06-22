package com.example.demoexammobile.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.demoexammobile.API.Class.kinoParam;
import com.example.demoexammobile.API.Class.movieParam;
import com.example.demoexammobile.API.Interface.API;
import com.example.demoexammobile.Adapter.Kino;
import com.example.demoexammobile.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//Основной класс приложения
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private RecyclerView recyclerView;
    private API api;
    private VideoView videoView;
    private List<movieParam> movies;
    private Retrofit retrofit;
    private Button start, pause, restart;
    @Override
    //Присвоение адаптера и менеджера с горизонтальной ориентацией
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.Start);
        pause = findViewById(R.id.Pause);
        restart = findViewById(R.id.Restart);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        restart.setOnClickListener(this);
        recyclerView = findViewById(R.id.RecyclerView);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        videoView = findViewById(R.id.videoView3);
        movies = new ArrayList<>();
        Kino adapter = new Kino(movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        func();
    }
    //Управление видеоплеера
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Start:
                videoView.start();
                break;
            case R.id.Pause:
                videoView.pause();
                break;
            case R.id.Restart:
                videoView.resume();
                videoView.start();
                break;
            default:
                break;
        }
    }
    //api - запрос
    private void func()
    {
        Call<List<movieParam>> call = api.getMovie();
        call.enqueue(new Callback<List<movieParam>>()
        {
            @Override
            public void onResponse(Call<List<movieParam>> call, Response<List<movieParam>> response)
            {
                if(response.isSuccessful())
                {
                    movies.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<movieParam>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Call<List<kinoParam>> call1 = api.getKino();
        call1.enqueue(new Callback<List<kinoParam>>()
        {
            @Override
            public void onResponse(Call<List<kinoParam>> call, Response<List<kinoParam>> response)
            {
                if(response.isSuccessful())
                {
                    videoView.setVideoURI(Uri.parse("http://cinema.areas.su/up/video/" + response.body().get(1).getPreview()));
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<List<kinoParam>> call, Throwable t)
            {

            }
        });
    }
}