package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Produk5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk5);
    }
    public void buyp5(View view) {
        Intent intent = new Intent(Produk5.this, Payment.class);
        startActivity(intent);
        finish();
    }
}