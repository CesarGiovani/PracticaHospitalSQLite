package mx.edu.tesji.practicahospitalsqlite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private final int DURACION = 2000;   //Constante del tiempo de ejecucion del hilo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Crea un hilo que llama a la activity principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();   //Cierra la actividad Actual
                Intent i= new Intent(Splash.this, MainActivity.class); //Se llama al activity "principal"
                startActivity(i);
            }
        },2000);    //Colocamos el tiempo de retraso de ejecucion del hilo en milisegundos
    }
}
