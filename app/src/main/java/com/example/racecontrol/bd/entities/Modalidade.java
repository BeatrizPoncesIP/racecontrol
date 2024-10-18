package com.example.racecontrol.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Modalidade {
    @PrimaryKey(autoGenerate = true)
    private int idMod;
    private String descricao;

    public Modalidade(int idMod, String descricao) {
        this.idMod = idMod;
        this.descricao = descricao;
    }

    // Getters e Setters
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

    public void setIdMod(int idMod) {
        this.idMod = idMod;
    }

    @NonNull
    @Override
    public String toString() {return this.getIdMod() + " " + this.getDescricao();}
}

