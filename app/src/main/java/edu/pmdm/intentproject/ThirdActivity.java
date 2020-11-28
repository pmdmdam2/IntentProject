package edu.pmdm.intentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Tercera actividad, será llamada desde la primera y le devolverá un texto que, la primera,
 * pondrá en su texto principal
 */
public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        //se pone un título a la barra de título de la actividad
        setTitle(getString(R.string.tercera_actividad));
        //se obtiene la referencia la botón de la interfaz
        Button btBoton2 = findViewById(R.id.btBoton3);
        //se implementa el evento clic sobre el botón
        btBoton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se crea un intent para devolver el mensaje de texto de respuesta
                Intent intent = new Intent();
                intent.putExtra("texto1",getString(R.string.msj_desde_a3) +
                        ThirdActivity.class.getSimpleName());
                //se asigna el resultado de la actividad
                setResult(RESULT_OK,intent);
                //se finaliza la actividad
                finish();
            }
        });
        //se obtiene la referencia a la caja de texto de la interfaz de la actividad 3
        //y se asigna el texto recibido desde la actividad 1
        TextView tvTexto3 = findViewById(R.id.tvTexto3);
        tvTexto3.setText(getIntent().getExtras().getString("texto3"));
    }
}