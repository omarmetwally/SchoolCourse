package com.example.omarmetwally.schoolcourse;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {



    public   static final String Base_URL="https://stc-api.herokuapp.com/";
    private  static  RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient()
    {

        retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized RetrofitClient getInstance()
    {
        if(mInstance==null)
        {
            mInstance=new RetrofitClient();

        }
        return  mInstance;

    }

    public Api getApi()
    {
        return retrofit.create(Api.class);
    }








}
