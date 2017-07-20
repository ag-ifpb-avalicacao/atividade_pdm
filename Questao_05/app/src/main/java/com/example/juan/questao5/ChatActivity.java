package com.example.juan.questao5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView listMensagens;
    private ImageButton enviar;
    private EditText textBox;
    private AttMensagensTasks attTask;
    private PersistirMensagensTasks persistTask;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat);

        //atualiza as mensagens na tela
        attMensagensNaTela();

        listMensagens = (ListView) findViewById(R.id.msgListView);
        enviar = (ImageButton) findViewById(R.id.enviar);
        textBox = (EditText) findViewById(R.id.texto);

        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (textBox.getText().toString() != "" || textBox.getText() == null) {
                    //recupera a informacao sobre o usuario
                    String remetente = "pessoa" + getIntent().getExtras().getInt("badge");

                    //String utilizada para refatorar os caracteres ivalidos na requisicao
                    String msgRefactor = textBox.getText().toString();
                    msgRefactor = msgRefactor.replace(" ", "+");
                    msgRefactor = msgRefactor.replace("\n", "+");

                    Mensagem msg = new Mensagem(remetente, msgRefactor, true);

                    //chamar serviço para persistir a mensagem no banco
                    try {
                        persisteMensagemGet(msg);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    //atualiza as mensagens na tela
                    attMensagensNaTela();
                    textBox.setText("");
                }

            }
        });
    }

    public void attMensagensNaTela(){
        //chamar serviço para ler as mensagens do banco
        attTask = new AttMensagensTasks();
        attTask.execute("https://chatpdm.herokuapp.com/db?action=read");
    }

    public void persisteMensagemGet(Mensagem msg) throws JSONException{
        persistTask = new PersistirMensagensTasks();
        //string que representa os parametros que vao ser concatenados a url
        String paramentros = "id="+msg.getId()+"&"+
                "remetente="+msg.getRemetente()+"&"+
                "mensagem="+ msg.getTexto();

        //chama o webservice passando a url com seus parametros
        persistTask.execute("https://chatpdm.herokuapp.com/db?action=add&"+paramentros);
    }

    private class AttMensagensTasks extends AsyncTask<String, Integer, List<Mensagem>>{

        @Override
        public List<Mensagem> doInBackground(String... urls){
            HRKSolution service;
            List<Mensagem> mensagens = new ArrayList<>();
            try {
                service = new HRKSolution(urls[0]);
                //recebe uma lista de mensagens lida do banco
                //lista que sera usada para ser adicionada ao adapter do listview
                mensagens = service.getMensagens();
            }catch (Exception ex){
                ex.printStackTrace();
            }

            //vai alterar os valores do campo booleano que representa se o usuario enviou determinada mensagem
            for (Mensagem msg: mensagens){
                if(!msg.getRemetente().equals("pessoa"+getIntent().getExtras().getInt("badge")))
                    msg.setSouEu(false);
            };

            return mensagens;
        }

        @Override
        public void onPostExecute(List<Mensagem> mensagens){
            //adiciona no listview um adapter com a lista retornada a partir do webservice
            listMensagens.setAdapter(new BubbleAdapter(getApplicationContext(), mensagens));
        }

    }

    private class PersistirMensagensTasks extends AsyncTask<String, Integer, Integer> {

        @Override
        public Integer doInBackground(String... urls) {
            HRKSolution service;
            int codigo = 0;
            try {
                service = new HRKSolution(urls[0]);
                //codigo da resposta da requiscao apos tentar adicionar uma mensagem
                //usada apenas para fins de log
                codigo = service.addMensagem();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return codigo;
        }

        @Override
        public void onPostExecute(Integer integer) {
            Log.d("postExecute", integer.toString());
        }

    }

}
