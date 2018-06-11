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

public class BuscarActualizarSin extends AppCompatActivity {
    EditText txtIdSin, txtSintoma, txtDescripcion;
    Button NuevoBtn,guardarBtn,cancelarBtn,regresarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_actualizar_sin);
        txtIdSin = (EditText) findViewById(R.id.edBuscarIdSin);
        txtSintoma= (EditText) findViewById(R.id.edSintomaEdit);
        txtDescripcion = (EditText) findViewById(R.id.edDesSintoEdit);
    }

    public void buscar(View v) {
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String idSin = txtIdSin.getText().toString();
        //Validar qu el id no este vacio
        if (idSin.trim().length()==0){
            txtIdSin.setError("Ingrese No. Sintoma");
        }else{
            try {
                Cursor fila = db.rawQuery("SELECT sintoma,descripcion FROM sintoma WHERE id="+idSin,null);
                if (fila.moveToFirst()){
                    txtSintoma.setText(fila.getString(0));
                    txtDescripcion.setText(fila.getString(1));
                }else {
                    Toast.makeText(this,"No se encontro el sintoma",Toast.LENGTH_SHORT).show();
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
        String idSintoma = txtIdSin.getText().toString();
        String sintoma = txtSintoma.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        if (sintoma.trim().length()==0){
            txtSintoma.setError("Ingrese Sintoma");
        }else if (descripcion.trim().length()==0) {
            txtDescripcion.setError("Ingrese Descripcion");
        }else {
            //El contentvalues envia los datos a la bd
            ContentValues registro = new ContentValues();
            registro.put("sintoma",sintoma);
            registro.put("descripcion",descripcion);
            int noREg = bd.update("sintoma",registro,"id="+idSintoma,null);
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
        confirmar.setMessage("Se borrará el sintoma '"+txtSintoma.getText().toString()+"', de SI para confirmar");
        confirmar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConexionSQLite conexion = new ConexionSQLite(getApplicationContext(), "Hospital", null, 1);
                SQLiteDatabase bd = conexion.getWritableDatabase();
                String idSintoma = txtIdSin.getText().toString();
                int noBor = bd.delete("sintoma","id="+idSintoma,null);
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
