package mx.edu.tesji.practicahospitalsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import mx.edu.tesji.modelo.ConexionSQLite;

public class RegistroMedico extends AppCompatActivity {
    private Spinner spEspecialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_medico);
        spEspecialidad= (Spinner) findViewById(R.id.spEspecialidad);
        llenarEspecialidad();
    }
    //Metodo para extraer datos de la tabla especialidad
    //Especialidad e imprimirlos en el spinner

    private void llenarEspecialidad(){
        ConexionSQLite conexion = new ConexionSQLite(this,"Hospital",null,1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor fila = db.rawQuery("SELECT * FROM Especialidad",null);

        int cont = fila.getCount()+1;

        String array[] = new String[cont];
        fila.moveToFirst();
        array[0]="--Seleccione--";
        int x=1;
        while (x<cont){
            array[x]=fila.getString(1);
            x++;
            fila.moveToNext();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        spEspecialidad.setAdapter(adaptador);
        db.close();
    }
}
