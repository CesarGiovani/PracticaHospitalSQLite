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

public class RegistroEspecialidad extends AppCompatActivity {

    private EditText txtEspecialidad,txtDescripcion;
    private Button NuevoBtn,guardarBtn,cancelarBtn,regresarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_especialidad);
        txtEspecialidad = findViewById(R.id.etEspecialidad);
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
        txtEspecialidad.setText(null);
        txtEspecialidad.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }

    private void habilitar(){
        NuevoBtn.setEnabled(false);
        guardarBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        regresarBtn.setEnabled(false);
        txtDescripcion.setText(null);
        txtEspecialidad.setText(null);
        txtEspecialidad.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtEspecialidad.requestFocus();
    }

    public void nuevo(View v){
        habilitar();
    }

    public void guardar(View v){
        //Crear un objeto de conexion
        ConexionSQLite conexion = new ConexionSQLite(this,"Hospital",null,1);
        //Crear un objeto SQLiteDatabase para manipular la BD y darle permisos
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String especialidad =  txtEspecialidad.getText().toString();
        String descripcion = txtDescripcion.getText().toString();

        if(especialidad.trim().length()==0){
            txtEspecialidad.setError("Ingrese Especialidad");
        }else if (descripcion.trim().length()==0){
            txtDescripcion.setError("Ingrese descripcion");
        }else{
            try {
                //creamos objeto de contentValues para darle los valores del registro
                ContentValues registro = new ContentValues();
                registro.put("especialidad",especialidad);
                registro.put("descripcion",descripcion);
                bd.insert("especialidad",null,registro);
                Toast.makeText(this,"Especialidad agregada correctamente",Toast.LENGTH_SHORT).show();
                inhabilitar();
                bd.close();
            }catch (Exception e) {
                Toast.makeText(this,"Especialidad existente",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cancelar(View v){
        inhabilitar();
    }

    public void regresar(View v){
        finish();
    }
}
