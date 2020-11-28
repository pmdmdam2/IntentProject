package edu.pmdm.intentproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Actividad principal desde la que se llamará a las otras dos actividades, se les pasará un texto
 * y recibirá un texto como respuesta
 */
public class MainActivity extends AppCompatActivity {
    private final int SECOND_ACTIVITY=10;
    private final int THIRD_ACTIVITY=11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.primera_actividad));
        Button btBoton1 = findViewById(R.id.btBoton1a2);
        btBoton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent para cargar la segunda actividad
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("texto2","Este texto me lo ha enviado la actividad " +
                        MainActivity.class.getSimpleName());
                startActivityForResult(intent,SECOND_ACTIVITY);
            }
        });
        Button btBoton2 = findViewById(R.id.btBoton1a3);
        btBoton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent para cargar la tercera actividad
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                intent.putExtra("texto3","Este texto me lo ha enviado la actividad " +
                        MainActivity.class.getSimpleName());
                startActivityForResult(intent,THIRD_ACTIVITY);
            }
        });
    }

    /**
     * Método de callback que será invocado tras finalizar las actividades llamadas por los intents
     * asociados con cada botón.
     * @param requestCode Código relacionado con la petición, normalmente se asocia a la interfaz de
     *                    la actividad del intent. Lo asigna la actividad que llama a otra.
     * @param resultCode  Código relacionado con el resultado de la llamada a la actividad de un
     *                    intent. Lo asigna la actividad llamada.
     * @param data Intent con parámetros de respuesta que la actividad llamada asigna para que la
     *             actividad que llama pueda recogerlos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView tvTexto1;
        //si tras finalizar la actividad, el resultado no es correcto...
        if(resultCode!=RESULT_OK){
            tvTexto1 = findViewById(R.id.tvTexto1);
            tvTexto1.setText(R.string.result_ko);
            return;
        }
        //el resultado ha sido correcto, se obtienen los datos recibidos desde la actividad que ha
        //finalizado
        switch (requestCode){
            case SECOND_ACTIVITY:
                tvTexto1 = findViewById(R.id.tvTexto1);
                tvTexto1.setText(data.getExtras().getString("texto1"));
                break;
            case THIRD_ACTIVITY:
                tvTexto1 = findViewById(R.id.tvTexto1);
                tvTexto1.setText(data.getExtras().getString("texto1"));
                break;
        }
    }
}