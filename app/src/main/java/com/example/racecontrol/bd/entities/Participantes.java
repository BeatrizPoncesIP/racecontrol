package com.example.racecontrol.bd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Participantes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Modalidade modalidade;

    public Participantes(){}

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return "Nome = " + nome + "\nTelefone = " + telefone + "\nModalidade = " + modalidade.getDescricao();
    }
}
