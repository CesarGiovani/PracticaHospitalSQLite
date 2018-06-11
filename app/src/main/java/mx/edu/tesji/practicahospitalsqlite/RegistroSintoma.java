package mx.edu.tesji.practicahospitalsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.tesji.modelo.ConexionSQLite;

public class RegistroSintoma extends AppCompatActivity {
    private EditText txtSintoma,txtDescripcion;
    private Button NuevoBtn,guardarBtn,cancelarBtn,regresarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_sintoma);
        txtSintoma = findViewById(R.id.etSintoma);
        txtDescripcion = findViewById(R.id.etDescripcion);
        NuevoBtn = findViewById(R.id.btnNuevo);
        guardarBtn = findViewById(R.id.btnGuardar);
        cancelarBtn = findViewById(R.id.btnCancelar);
        regresarBtn = findViewById(R.id.btnRegresar);
        inhabilitar();
    }
    private void inhabilitar(){
        NuevoBtn.setEnabled(true);
        guardarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        regresarBtn.setEnabled(true);
        txtDescripcion.setText(null);
        txtSintoma.setText(null);
        txtSintoma.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }

    private void habilitar(){
        NuevoBtn.setEnabled(false);
        guardarBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        regresarBtn.setEnabled(false);
        txtDescripcion.setText(null);
        txtSintoma.setText(null);
        txtSintoma.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtSintoma.requestFocus();
    }
    public void nuevo(View v){
        habilitar();
    }
    public void cancelar(View v){
        inhabilitar();
    }

    public void regresar(View v){
        finish();
    }
    public void guardar(View v){
        //Crear un objeto de conexion
        ConexionSQLite conexion = new ConexionSQLite(this,"Hospital",null,1);
        //Crear un objeto SQLiteDatabase para manipular la BD y darle permisos
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String sintoma =  txtSintoma.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        if(sintoma.trim().length()==0){
            txtSintoma.setError("Ingrese Sintoma");
        }else if (descripcion.trim().length()==0){
            txtDescripcion.setError("Ingrese Descripcion");
        }else{
            try {
                ContentValues registro = new ContentValues();
                registro.put("sintoma",sintoma);
                registro.put("descripcion",descripcion);
                bd.insert("sintoma",null,registro);
                Toast.makeText(this,"Se registro correctamente",Toast.LENGTH_SHORT).show();
                inhabilitar();
                bd.close();
            }catch (Exception e) {
                Toast.makeText(this,"Sintoma existente",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
