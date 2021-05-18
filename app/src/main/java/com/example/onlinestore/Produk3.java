package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Produk3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk3);
    }
    public void buyp3(View view) {
        Intent intent = new Intent(Produk3.this, Payment.class);
        startActivity(intent);
        finish();
    }
}