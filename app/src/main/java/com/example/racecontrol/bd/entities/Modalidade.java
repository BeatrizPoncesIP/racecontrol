package com.example.racecontrol.bd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Modalidade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;

    // Getters e Setters
    public Modalidade(){}

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }
}

