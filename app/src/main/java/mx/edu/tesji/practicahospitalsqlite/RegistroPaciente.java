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

public class RegistroPaciente extends AppCompatActivity {
    private EditText txtNombre,txtPaterno,txtMaterno,txtEdad,txtTel;
    private Button NuevoBtn,guardarBtn,cancelarBtn,regresarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_paciente);
        txtNombre = findViewById(R.id.etNomPac);
        txtPaterno = findViewById(R.id.etAPatPac);
        txtMaterno = findViewById(R.id.etAMatPac);
        txtEdad = findViewById(R.id.etEdadPac);
        txtTel = findViewById(R.id.etTelefonoPac);
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
        txtNombre.setText(null);
        txtNombre.setEnabled(false);
        txtPaterno.setText(null);
        txtPaterno.setEnabled(false);
        txtMaterno.setText(null);
        txtMaterno.setEnabled(false);
        txtEdad.setText(null);
        txtEdad.setEnabled(false);
        txtTel.setText(null);
        txtTel.setEnabled(false);

    }
    private void habilitar(){
        NuevoBtn.setEnabled(false);
        guardarBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        regresarBtn.setEnabled(false);
        txtNombre.setText(null);
        txtNombre.setEnabled(true);
        txtPaterno.setText(null);
        txtPaterno.setEnabled(true);
        txtMaterno.setText(null);
        txtMaterno.setEnabled(true);
        txtEdad.setText(null);
        txtEdad.setEnabled(true);
        txtTel.setText(null);
        txtTel.setEnabled(true);
        txtNombre.requestFocus();
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
        String nombre =  txtNombre.getText().toString();
        String paterno = txtPaterno.getText().toString();
        String materno = txtMaterno.getText().toString();
        String edad = txtEdad.getText().toString();
        String telefono = txtTel.getText().toString();
        if(nombre.trim().length()==0){
            txtNombre.setError("Ingrese Paciente");
        }else if (paterno.trim().length()==0){
            txtPaterno.setError("Ingrese Apellido");
        }else if (edad.trim().length()==0){
            txtEdad.setError("Ingrese Edad");
        }else if (telefono.trim().length()==0){
            txtTel.setError("Ingrese Telefono");
        }else{
            try {
                ContentValues registro = new ContentValues();
                registro.put("nombre",nombre);
                registro.put("aPaterno",paterno);
                registro.put("aMaterno",materno);
                registro.put("edad",edad);
                registro.put("telefono",telefono);
                bd.insert("paciente",null,registro);
                Toast.makeText(this,"Se agrego paciente correctamente",Toast.LENGTH_SHORT).show();
                inhabilitar();
                bd.close();
            }catch (Exception e) {
                Toast.makeText(this,"Error, intente de nuevo",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
