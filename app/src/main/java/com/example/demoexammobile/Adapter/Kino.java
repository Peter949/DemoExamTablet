package com.example.demoexammobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoexammobile.API.Class.movieParam;
import com.example.demoexammobile.Main.KinoActivity;
import com.example.demoexammobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;
//Класс-адаптер список для компонента RecyclerView
public class Kino extends RecyclerView.Adapter<Kino.ViewHolder>
{
    private List<movieParam> movies;
    private Context context;
    //Конструктор класса
    public Kino(List<movieParam> movies)
    {
        this.movies = movies;
    }
    //Привязка к макету
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }
    //Вставка и отправка данных
    @Override
    public void onBindViewHolder(Kino.ViewHolder holder, int position)
    {
        movieParam param = movies.get(position);
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + param.getPoster()).resize(500, 800).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, KinoActivity.class);
                intent.putExtra("age", param.getAge());
                intent.putExtra("name", param.getName());
                intent.putExtra("des", param.getDescription());
                intent.putExtra("image", param.getPoster());
                context.startActivity(intent);
            }
        });
    }
    //Возвращение общего числа фильмов
    @Override
    public int getItemCount()
    {
        if(movies == null)
        {
            return 0;
        }
        return movies.size();
    }
    //Вспомогтаельный класс представления компонентов
    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        ViewHolder(View view)
        {
            super(view);
            image = view.findViewById(R.id.imageView);
        }
    }
}
