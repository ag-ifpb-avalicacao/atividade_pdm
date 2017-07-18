package com.example.juan.questao5;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by juan on 16/07/17.
 */

public class HRKSolution {

    private URL url;

    public HRKSolution(String url) throws MalformedURLException{
        this.url = new URL(url);
    }

    //metodo que tenta realizar uma conexao com a url
    public Integer addMensagem() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int codigo = connection.getResponseCode();
        connection.disconnect();
        return codigo;
    }

    //metodo quer retorna uma lista de mensagens instanciadas a partir
    //dos valores de um array de objetos json que representam as mensagens
    public List<Mensagem> getMensagens() throws JSONException, IOException{
        List<Mensagem> mensagens = new ArrayList<>();
        //chama o metodo que retorna o objeto json
        JSONArray jArray = getJSONObject().getJSONArray("mensagens");
        for (int i = 0; i < jArray.length(); i++){
            JSONObject object = jArray.getJSONObject(i);
            Mensagem msg = new Mensagem(
                    object.getLong("id"),
                    object.getString("remetente"),
                    object.getString("texto"),
                    object.getBoolean("souEu")
            );
            mensagens.add(msg);
            Log.d("msg stored", msg.toString());
        }
        return mensagens;
    }

    //metodo que retorna um objeto json
    private JSONObject getJSONObject() throws IOException, JSONException {
        //chama o metodo que retorna uma string do objeto json retornado pela resposta
        String object = getJSONString();
        return new JSONObject(object);
    }

    //metodo que realiza uma conexao com a url passada e retorna uma string que representa os dados do json
    //retornado pela resposta da requisicao
    private String getJSONString() throws IOException{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream is;

        Log.d("type", connection.getContentType());

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            is = connection.getInputStream();
        else
            is = connection.getErrorStream();

        //chama o metodo que converte os dados do stream em string
        String retorno = inputToString(is);

        connection.disconnect();
        is.close();

        return retorno;
    }

    //método que converte os dados de um stream em uma string
    private String inputToString(InputStream is) {
        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linha;

            while((linha = br.readLine())!=null)
                sb.append(linha);

            br.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
