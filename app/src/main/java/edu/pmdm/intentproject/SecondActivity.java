package edu.pmdm.intentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Segunda actividad, será llamada desde la primera y le devolverá un texto que, la primera,
 * pondrá en su texto principal
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //se pone un título a la barra de título de la actividad
        setTitle(getString(R.string.segunda_actividad));
        //se obtiene la referencia la botón de la interfaz
        Button btBoton2 = findViewById(R.id.btBoton2);
        //se implementa el evento clic sobre el botón
        btBoton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se crea un intent para devolver el mensaje de texto de respuesta
                Intent intent = new Intent();
                intent.putExtra("texto1",getString(R.string.msj_desde_a2) +
                        SecondActivity.class.getSimpleName());
                //se asigna el resultado de la actividad
                setResult(RESULT_OK,intent);
                //se finaliza la actividad
                finish();
            }
        });
        //se obtiene la referencia a la caja de texto de la interfaz de la actividad 3
        //y se asigna el texto recibido desde la actividad 1
        TextView tvTexto2 = findViewById(R.id.tvTexto2);
        tvTexto2.setText(getIntent().getExtras().getString("texto2"));
    }
}