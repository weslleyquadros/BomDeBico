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
    public String telefone;
    public String datacadastro;



    public ItemAnuncios(String titulo, String descricao, String telefone, String datacadastro) {
            this.titulo = titulo;
            this.descricao = descricao;
            this.telefone = telefone;
            this.datacadastro = datacadastro;
       // this.imagenId = imagenId;
      //  this.classificacao = classificacao;
    }
    public ItemAnuncios(){

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
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

}
