package touglebutton.cursoandroid.com.weather.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import touglebutton.cursoandroid.com.weather.R;

public class MainActivity extends Activity {

    private TextView textoCidade;
    private TextView textoTemperatura;
    private EditText buscaCidade;
    private ImageView iconeClima;
    private Button botaoConsultar;
    private String cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscaCidade = findViewById(R.id.buscaCidadeId);
        botaoConsultar =findViewById(R.id.botaoConsultaId );
        textoCidade = findViewById(R.id.textoCidadeId);
        textoTemperatura = findViewById(R.id.textoTempId);

        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        String idJSON;
        String cidadeJSON;
        String paisJSON;

        try {

            jsonObject = (JSONObject) parser.parse(new FileReader("R.raw.citylist.json"));
            idJSON = (String) jsonObject.get("id");
            cidadeJSON = (String) jsonObject.get("name");
            paisJSON = (String) jsonObject.get("country");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        botaoConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cidade = buscaCidade.getText().toString();

                textoCidade.setText(cidade);




            }
        });

        textoCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, segundaActivity.class));

                Intent intent = new Intent(MainActivity.this, segundaActivity.class);
                String cidade = "";
                cidade = textoCidade.getText().toString();
                Bundle bundle = new Bundle();

                bundle.putString("cidade", cidade);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });



    }
}
