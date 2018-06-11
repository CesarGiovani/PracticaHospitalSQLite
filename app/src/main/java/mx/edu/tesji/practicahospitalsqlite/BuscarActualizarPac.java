package mx.edu.tesji.practicahospitalsqlite;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.tesji.modelo.ConexionSQLite;

public class BuscarActualizarPac extends AppCompatActivity {
    EditText txtIdPac, txtNombre, txtAPat,txtAMat,txtEdad,txtTel;
    Button NuevoBtn,guardarBtn,cancelarBtn,regresarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_actualizar_pac);
        txtIdPac = (EditText) findViewById(R.id.edBuscarIdPac);
        txtNombre = (EditText) findViewById(R.id.etBuscarNomPac);
        txtAPat = (EditText) findViewById(R.id.etBuscarAPatPac);
        txtAMat = (EditText) findViewById(R.id.etBuscarAMatPac);
        txtEdad = (EditText) findViewById(R.id.etBuscarEdadPac);
        txtTel = (EditText) findViewById(R.id.etBuscarTelefonoPac);
    }
    public void buscar(View v) {
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String idPac = txtIdPac.getText().toString();
        //Validar qu el id no este vacio
        if (idPac.trim().length()==0){
            txtIdPac.setError("Ingrese No. Paciente");
        }else{
            try {
                Cursor fila = db.rawQuery("SELECT * FROM paciente WHERE id="+idPac,null);
                if (fila.moveToFirst()){
                    txtNombre.setText(fila.getString(1));
                    txtAPat.setText(fila.getString(2));
                    txtAMat.setText(fila.getString(3));
                    txtEdad.setText(fila.getString(4));
                    txtTel.setText(fila.getString(5));
                }else {
                    Toast.makeText(this,"No se encontro paciente",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this,"Error, no sabe utilizar la app",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    public void actualizar(View v){
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String idPaciente = txtIdPac.getText().toString();
        String nombre = txtNombre.getText().toString();
        String aPat = txtAPat.getText().toString();
        String aMat = txtAMat.getText().toString();
        String edad = txtEdad.getText().toString();
        String tel = txtTel.getText().toString();
        if (idPaciente.trim().length()==0){
            txtIdPac.setError("Ingrese Id");
        }else if (nombre.trim().length()==0) {
            txtNombre.setError("Ingrese Nombre");
        }else if (aPat.trim().length()==0) {
            txtAPat.setError("Ingrese Apellido");
        }else if (edad.trim().length()==0) {
            txtEdad.setError("Ingrese Edad");
        }else if (tel.trim().length()==0) {
            txtTel.setError("Ingrese Telefono");
        }else {
            //El contentvalues envia los datos a la bd
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("aPaterno",aPat);
            registro.put("aMaterno",aMat);
            registro.put("edad",edad);
            registro.put("telefono",tel);
            int noREg = bd.update("paciente",registro,"id="+idPaciente,null);
            if (noREg != 0){
                Toast.makeText(this,"Actualizacion Exitosa",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Error, no se pudo actualizar",Toast.LENGTH_SHORT).show();
            }
        }
        bd.close();
    }

    public void eliminar(View v){
        //Crear el Dialog de eliminacion
        AlertDialog.Builder confirmar = new AlertDialog.Builder(this);
        confirmar.setTitle("¿Seguro que quiere borrar el registro?");
        confirmar.setMessage("Se borrará el paciente '"+txtNombre.getText().toString()+"', de SI para confirmar");
        confirmar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConexionSQLite conexion = new ConexionSQLite(getApplicationContext(), "Hospital", null, 1);
                SQLiteDatabase bd = conexion.getWritableDatabase();
                String idPaciente = txtIdPac.getText().toString();
                int noBor = bd.delete("paciente","id="+idPaciente,null);
                if (noBor != 0){
                    Toast.makeText(getApplicationContext(),"Borrado exitoso",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Error, no se pudo eliminar",Toast.LENGTH_SHORT).show();
                }
                bd.close();
            }
        });
        confirmar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        confirmar.show();
    }

}
