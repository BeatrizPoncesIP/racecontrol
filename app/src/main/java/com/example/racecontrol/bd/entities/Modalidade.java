package com.example.racecontrol.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Modalidade implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;

    public Modalidade(String descricao) {
        this.descricao = descricao;
    }

    public Modalidade() {
    }

    public Modalidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getDescricao();
    }
}