package com.example.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.payment:
                        startActivity(new Intent(getApplicationContext()
                                ,Payment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feedbackmenu:
                        startActivity(new Intent(getApplicationContext()
                                ,FeedBack.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void produk1(View view) {
        Intent intent = new Intent(MainActivity.this,produk1.class);
        startActivity(intent);
        finish();
    }

    public void produk2(View view) {
        Intent intent = new Intent(MainActivity.this,Produk2.class);
        startActivity(intent);
        finish();
    }

    public void produk3(View view) {
        Intent intent = new Intent(MainActivity.this,Produk3.class);
        startActivity(intent);
        finish();
    }

    public void produk4(View view) {
        Intent intent = new Intent(MainActivity.this,Produk4.class);
        startActivity(intent);
        finish();
    }

    public void produk5(View view) {
        Intent intent = new Intent(MainActivity.this,Produk5.class);
        startActivity(intent);
        finish();
    }

    public void produk6(View view) {
        Intent intent = new Intent(MainActivity.this,Produk6.class);
        startActivity(intent);
        finish();
    }
}
