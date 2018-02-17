package touglebutton.cursoandroid.com.weather.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Date;

import touglebutton.cursoandroid.com.weather.R;

public class segundaActivity extends Activity {

    private TextView textoCidadeSemana;
    private TextView dataAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textoCidadeSemana = findViewById(R.id.textoCidadeSemanalId);
        dataAtual = findViewById(R.id.dataAtualid);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String cidade = bundle.getString("cidade");
        textoCidadeSemana.setText(cidade);

        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        dataAtual.setText(currentDateTimeString);


    }
}
