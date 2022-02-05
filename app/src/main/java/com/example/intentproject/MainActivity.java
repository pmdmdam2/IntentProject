package com.example.intentproject;

import static androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS;
import static androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Ejemplo de intent explícito con control de resultados
 * del final de la acción. La actividad principal llama a la secundaria
 * pasándole información y espera a que la actividad secundaria termine
 * y le devuelva los resultados obtenidos en la misma
 */
public class MainActivity extends AppCompatActivity {
    private final int TODO_OK = 20;
    private final ActivityResultRegistry mRegistry;
    private ActivityResultLauncher<Intent> launcher;
    private final String KEY="texto";

    /**
     * Constructor predeterminado. Se utiliza para instancia el registro
     * de resultados de la actividad. La clase ActivityResultRegistry es
     * abstracta, la insntanciación se realiza mediante una clase interna
     * anónima derivada, se implementa por tanto el método abstracto onLaunch
     * de la misma. Este método de callback será llamada cuando el launcher
     * solicite la ejecución de la acción.
     */
    public MainActivity() {
        this.mRegistry = new ActivityResultRegistry() {
            @Override
            public <I, O> void onLaunch(int requestCode,
                                        @NonNull ActivityResultContract<I, O> contract, I input,
                                        @Nullable ActivityOptionsCompat options) {
                //actividad que se encargará de controlar el resultado de la
                //llamada
                ComponentActivity activity = MainActivity.this;
                //creación del intent a partir de la actividad origen
                Intent intent = contract.createIntent(activity, input);
                //caché de la actividad
                Bundle optionsBundle = null;
                //se comprueba si el intent tiene extras para cargar la clase
                //correspondiente
                if (intent.getExtras() != null && intent.getExtras().getClassLoader() == null) {
                    intent.setExtrasClassLoader(activity.getClassLoader());
                }
                if (ACTION_REQUEST_PERMISSIONS.equals(intent.getAction())) {
                    //si la acción solicitada requiere permisos se debe controlar
                    //aquí
                } else if (ACTION_INTENT_SENDER_REQUEST.equals(intent.getAction())) {
                    //si la acción es para un intent implícito que quiere
                    //enviar información se debe controlar aquí
                } else {
                    //se ejecuta la llamada a la actividad destino a través del intent
                    //el código de petición, la actividad origen y la caché de datos de la misma
                    ActivityCompat.startActivityForResult(activity, intent, requestCode, optionsBundle);
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btActB = findViewById(R.id.btActB);
        btActB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itActB = new Intent(MainActivity.this, SecondActivity.class);
                itActB.putExtra("texto", getString(R.string.textoDeAaB));
                //se lanza la ejecución de la otra actividad
                launcher.launch(itActB);
            }
        });
        this.setUpLauncher();
    }

    /**
     * Método de callback que es llamado por la actividad llamada desde el intent
     *
     * @param requestCode Código según el tipo de petición
     * @param resultCode  Código con el resultado de la actividad
     * @param data        Datos resultantes de la actividad
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TODO_OK) {
            Toast.makeText(this, getResources().getString(R.string.texto_ok),
                    Toast.LENGTH_LONG).show();
            TextView tvActA = findViewById(R.id.tvActA);
            tvActA.setText(data.getExtras().get(this.KEY).toString());
        }
    }

    /**
     * Método para inicializar y configurar el launcher
     */
    private void setUpLauncher(){
        //se registra el nombre de la llamada con un nombre único
        //(no debe existir en el registrador), se le pasa el tipo
        //de entrada/salida (en este caso es un itent explícito
        //hacia otra actividad, la actividad origen controla los
        //resultados de la llamada. No se configura el callback porque
        //ya se hace en el momento de lanzar la llamada onLaunch de mRegistry
        this.launcher = this.mRegistry.register("ActividadPrincipal",
                new ActivityResultContracts.StartActivityForResult(),
                null);
    }
}