package com.example.racecontrol.bd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Modalidade {

    @PrimaryKey(autoGenerate = true)
    private int idMod;
    private String descricao;

    public Modalidade(){}

    public int getIdMod() {
        return idMod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
