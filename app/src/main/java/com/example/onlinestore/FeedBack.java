package com.example.onlinestore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FeedBack extends AppCompatActivity {
    private static final String FILE_NAME = "FEEDBACK.txt";
    EditText name, email, feedback ;
    Button send ;
    private String sendUrl="https://androveace.000webhostapp.com/getfeedback.php";
    private RequestQueue requestQueue;
    private static final String TAG = FeedBack.class.getSimpleName();
    int sucess ;
    private String TAG_SUCESS="sucess";
    private String TAG_MESSAGE="message";
    private String tag_json_obj ="json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        TextView textView = findViewById(R.id.textbaru);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);

        name = findViewById(R.id.name_text);
        email = findViewById(R.id.email_text);
        feedback = findViewById(R.id.feedback_text);

        send = (Button) findViewById(R.id.save_notes);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.feedbackmenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.payment:
                        startActivity(new Intent(getApplicationContext()
                                ,Payment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feedbackmenu:
                        return true;
                }
                return false;
            }
        });
    }

    public void savefb(View view) {
        SendFeedbackData();
        FBDialogbox();
        String text = feedback.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            feedback.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void SendFeedbackData(){
        StringRequest request=new StringRequest (Request.Method.POST, sendUrl,new Response.Listener<String>() {
            @Override
            public void onResponse (String response){
                try{
                    JSONObject jobj = new JSONObject(response);
                    sucess = jobj.getInt(TAG_SUCESS);
                    if (sucess == 1) {
                        Toast.makeText(FeedBack.this,jobj.getString(TAG_MESSAGE),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FeedBack.this,jobj.getString(TAG_MESSAGE),Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    Toast.makeText(FeedBack.this,"error Occure"+e,Toast.LENGTH_SHORT).show();
                }
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(FeedBack.this, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            public Map<String,String> getParams(){
                Map<String, String> params= new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("email",email.getText().toString());
                params.put("feedback",feedback.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,1,1.0f));
        requestQueue.add(request);
    }

    private void FBDialogbox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("FEEDBACK");

        alertDialogBuilder
                .setMessage("Thank For Your Feedback, Your Feedback Already Saved ")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("OKE",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    }