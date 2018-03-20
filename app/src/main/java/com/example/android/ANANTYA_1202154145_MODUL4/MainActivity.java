package com.example.android.ANANTYA_1202154145_MODUL4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method untuk menginisiasikan ke instance berikutnya
    public void searchImage(View view) {
        //digunakan untuk menginisialisasikan ke activity berikutnya
        Intent intent = new Intent(this, ImageSearch.class);
        startActivity(intent);
    }

    //method untuk menginisiasikan ke instance berikutnya
    public void listName(View view) {
        //digunakan untuk menginisialisasikan ke activity berikutnya
        Intent intent = new Intent(this, ListName.class);
        startActivity(intent);
    }
}
