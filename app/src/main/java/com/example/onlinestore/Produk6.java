package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Produk6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk6);
    }
    public void buyp6(View view) {
        Intent intent = new Intent(Produk6.this, Payment.class);
        startActivity(intent);
        finish();
    }
}