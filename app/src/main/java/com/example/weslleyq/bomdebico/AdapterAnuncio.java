package com.example.weslleyq.bomdebico;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by WeslleyQ on 25/04/2018.
 */

public class AdapterAnuncio extends RecyclerView.Adapter<AdapterAnuncio.ItemViewHolder>{

    ArrayList<ItemAnuncios> lista;

    public AdapterAnuncio(ArrayList<ItemAnuncios> lista) {
        this.lista=lista;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_celula,null,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txtTitulo.setText(lista.get(position).getTitulo());
        holder.txtDescricao.setText(lista.get(position).getDescricao());
        holder.foto.setImageResource(lista.get(position).getImagenId());
        holder.classificacao.setRating(lista.get(position).getClassificacao());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo,txtDescricao;
        ImageView foto;
        RatingBar classificacao;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtTitulo= (TextView) itemView.findViewById(R.id.titulo);
            txtDescricao= (TextView) itemView.findViewById(R.id.descricao);
            foto= (ImageView) itemView.findViewById(R.id.imageAnuncio);
            classificacao = (RatingBar) itemView.findViewById(R.id.ratingBar);
            classificacao.setEnabled(true);
        }
    }


}
