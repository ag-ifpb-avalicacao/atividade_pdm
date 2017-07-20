package com.example.juan.questao3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WSSolution {

    public String getStringJsonFormat(String urlString){
        String retorno = "";
        try {
            //estabele uma conexao http com o a url passada
            URL url = new URL(urlString);
            int codigoResposta;
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            codigoResposta = conexao.getResponseCode();

            InputStream is;

            Log.d("type", conexao.getContentType());

            //se a conexao for bem sucedida, um stream com os dados requistados sera retornado
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
                //converte os dados do stream para string
                retorno = inputToString(is);
            }else {
                is = conexao.getErrorStream();
            }
            is.close();
            conexao.disconnect();
        }catch(IOException ex){
            ex.printStackTrace();
        }
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
