package com.example.intentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private final int TODO_OK=20;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String key = "texto";
        //recibo el intent de la actividad A
        Intent itActA = getIntent();
        if(itActA!=null && itActA.getExtras().size()>0
                && itActA.getExtras().get(key)!=null){
            TextView tvActB = findViewById(R.id.tvActB);
            tvActB.setText(itActA.getExtras().get(key).toString());
        }
        Button btActA = findViewById(R.id.btActA);
        btActA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itActA = new Intent(SecondActivity.this,MainActivity.class);
                itActA.putExtra(key,"Texto enviado desde la actividad secundaria");
                //llamar al setResult para pasar los resultados obtenidos en la actividad
                //secundaria
                setResult(TODO_OK,itActA);
                finish();
            }
        });
    }
}