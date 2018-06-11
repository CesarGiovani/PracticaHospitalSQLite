package mx.edu.tesji.practicahospitalsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import mx.edu.tesji.modelo.ConexionSQLite;

public class ReporteEspecialidad extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_especialidad);
        lista = (ListView) findViewById(R.id.lstEspecialidades);
        llenarselaToda();
    }
    private void llenarselaToda(){
        ConexionSQLite conexion = new ConexionSQLite(this,"Hospital",null,1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor fila = db.rawQuery("SELECT * FROM Especialidad",null);

        int cont = fila.getCount();

        String array[] = new String[cont];
        fila.moveToFirst();

        int x=0;
        while (x<cont){
            array[x]=fila.getString(0)+" | "+fila.getString(1)+ " | " + fila.getString(2);
            x++;
            fila.moveToNext();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        lista.setAdapter(adaptador);
        db.close();
    }
}
