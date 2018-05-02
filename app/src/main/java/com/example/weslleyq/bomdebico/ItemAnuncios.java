package com.example.weslleyq.bomdebico;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



/**
 * Created by WeslleyQ on 24/04/2018.
 */

public class ItemAnuncios {

    private String titulo;
    private String descricao;
    private int imagenId;
    private int classificacao;

    public ItemAnuncios(String titulo, String descricao, int classificacao, int imagenId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagenId = imagenId;
        this.classificacao = classificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}
