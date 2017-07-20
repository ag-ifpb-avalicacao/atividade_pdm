package com.example.juan.questao3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button buscar;
    TextView logradouro, bairro, municipio;
    EditText input;
    WSSolution webservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buscar = (Button) findViewById(R.id.buscar);
        logradouro = (TextView) findViewById(R.id.logradouro);
        bairro = (TextView) findViewById(R.id.bairro);
        municipio = (TextView) findViewById(R.id.municipio);
        input = (EditText) findViewById(R.id.input);
        webservice = new WSSolution();

        buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(verificaConexao()) {
                    //verifica se o texto possui o tamanho correto
                    if (!(input.getText().toString().length() < 8))
                        //chama a asynctask passando a url com o cep informado
                        new ChamarCepWS().execute("http://viacep.com.br/ws/" + input.getText().toString() + "/json/");
                    else
                        Toast.makeText(getApplicationContext(), "um cep possui 8 caracteres", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(MainActivity.this, "voce nao esta conectado a internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ChamarCepWS extends AsyncTask<String, Integer, JSONObject>{
        @Override
        public JSONObject doInBackground(String... url){
            Log.d("where", "doinbackground");

            JSONObject objectJson = null;
            try {
                //recupera do metodo um objeto json no formato de string
                String stringJson = webservice.getStringJsonFormat(url[0]);
                objectJson = new JSONObject(stringJson);
            }catch(JSONException ex){
                ex.printStackTrace();
            }

            return objectJson;
        }

        @Override
        public void onPostExecute(JSONObject objectJson){
            Log.d("where", "onpostexecute");
            try {
                //verifica se o objeto possui todas as informacoes necessarias
                //ou se ocorreu alguma falha ao ser recuperado do webservice
                if(!objectJson.has("erro") && !objectJson.has("except")) {
                    //atribui aos textviews os valores do objeto json corretos para os devidos campos
                    logradouro.setText(objectJson.getString("logradouro"));
                    bairro.setText(objectJson.getString("bairro"));
                    municipio.setText(objectJson.getString("localidade"));
                }else
                    Toast.makeText(getApplicationContext(), "digite um cep existente", Toast.LENGTH_LONG).show();
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
