package mx.edu.tesji.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {


    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE especialidad (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  especialidad TEXT NOT NULL UNIQUE," +
                "  descripcion TEXT NOT NULL" +
                ") ");

        db.execSQL("CREATE TABLE medico (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  cedula TEXT NOT NULL UNIQUE," +
                "  nombre TEXT NOT NULL," +
                "  aPaterno TEXT NOT NULL," +
                "  aMaterno TEXT NOT NULL," +
                "  idEsp INTEGER NOT NULL," +
                "  FOREIGN KEY (idEsp) REFERENCES especialidad (id)" +
                ")");

        db.execSQL("CREATE TABLE sintoma (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  sintoma TEXT NOT NULL," +
                "  descripcion TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE paciente (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  nombre TEXT NOT NULL," +
                "  aPaterno TEXT NOT NULL," +
                "  aMaterno TEXT DEFAULT NULL," +
                "  edad TEXT NOT NULL," +
                "  telefono TEXT DEFAULT NULL" +
                ") ");

        db.execSQL("CREATE TABLE consulta (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  fecha TEXT NOT NULL," +
                "  hora TEXT NOT NULL," +
                "  idPaciente INTEGER NOT NULL," +
                "  idMedico INTEGER NOT NULL," +
                "  FOREIGN KEY (idMedico) REFERENCES medico (id)," +
                "  FOREIGN KEY (idPaciente) REFERENCES paciente (id)" +
                ")");

        db.execSQL("CREATE TABLE detalleconsulta (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  idconsulta INTEGER NOT NULL," +
                "  idSintoma INTEGER NOT NULL," +
                "  FOREIGN KEY (idSintoma) REFERENCES sintoma (id)," +
                "  FOREIGN KEY (idconsulta) REFERENCES consulta (id)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
