package mx.edu.tesji.practicahospitalsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-Especialidad*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*
            case R.id.itmRegistrarEspecialidad:
                Intent re = new Intent(MainActivity.this,RegistroEspecialidad.class);
                startActivity(re);
                break;
            case R.id.itmReporteEspe:
                Intent repe = new Intent(MainActivity.this,ReporteEspecialidad.class);
                startActivity(repe);
                break;
            case R.id.itmActualizaEspe:
                Intent buse = new Intent(MainActivity.this,BuscarActualizarEsp.class);
                startActivity(buse);
                break;
                //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-Sintoma*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*
            case R.id.itmRegistrarSintoma:
                Intent rs = new Intent(MainActivity.this,RegistroSintoma.class);
                startActivity(rs);
                break;
            case R.id.itmReporteSin:
                Intent reps = new Intent(MainActivity.this,ReporteSintoma.class);
                startActivity(reps);
                break;
            case R.id.itmActualizarSin:
                Intent buss = new Intent(MainActivity.this,BuscarActualizarSin.class);
                startActivity(buss);
                break;
                //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-Paciente*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*
            case R.id.itmRegistrarPaciente:
                Intent rp = new Intent(MainActivity.this,RegistroPaciente.class);
                startActivity(rp);
                break;
            case R.id.itmReportePac:
                Intent repp = new Intent(MainActivity.this,ReportePaciente.class);
                startActivity(repp);
                break;
            case R.id.itmActualizarPac:
                Intent busp = new Intent(MainActivity.this,BuscarActualizarPac.class);
                startActivity(busp);
                break;
            //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-Medico*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*
            case R.id.itmRegistrarMed:
                Intent rm = new Intent(MainActivity.this,RegistroMedico.class);
                startActivity(rm);
                break;
        }
        return true;
    }
}
