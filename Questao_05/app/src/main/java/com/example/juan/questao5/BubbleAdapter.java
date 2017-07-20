package com.example.juan.questao5;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by juan on 16/07/17.
 */

public class BubbleAdapter extends BaseAdapter {

    private Context ctx;
    private List<Mensagem> lista;

    public BubbleAdapter(Context ctx, List<Mensagem> lista){
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mensagem mensagem = lista.get(position);

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.balao, null);

        TextView textView = (TextView) view.findViewById(R.id.message_text);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bubble_layout);
        LinearLayout parentLayout = (LinearLayout) view.findViewById(R.id.parent);

        textView.setText(mensagem.getTexto());

        if(mensagem.isSouEu()) {
            linearLayout.setBackgroundResource(R.drawable.bubble2);
            parentLayout.setGravity(Gravity.RIGHT);
        }else{
            linearLayout.setBackgroundResource(R.drawable.bubble1);
            parentLayout.setGravity(Gravity.LEFT);
        }

        return view;
    }
}
