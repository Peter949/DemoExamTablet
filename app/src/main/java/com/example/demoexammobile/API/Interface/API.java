package com.example.demoexammobile.API.Interface;

import com.example.demoexammobile.API.Class.kinoParam;
import com.example.demoexammobile.API.Class.movieParam;
import com.example.demoexammobile.API.Class.signInParam;
import com.example.demoexammobile.API.Class.signUpParam;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
//Интерфейс для POST и GET запросов
public interface API
{
    //Статичный ретрофит, любые классы могут обратится к нему для присвоения api
    public static Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
    //Запросы POST и GET
    @POST("auth/register")
    Call<signUpParam> doRegister(@Body signUpParam param);

    @POST("auth/login")
    Call<signInParam> doAuth(@Body signInParam param);

    @GET("movies?filter=new")
    Call<List<movieParam>> getMovie();

    @GET("movies/1/episodes")
    Call<List<kinoParam>> getKino();
}
