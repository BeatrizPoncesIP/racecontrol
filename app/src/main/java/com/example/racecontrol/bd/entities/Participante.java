package com.example.racecontrol.bd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = Modalidade.class, parentColumns = "id", childColumns = "idMod"))
public class Participante {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private int idMod;  // Chave estrangeira que faz referência a Modalidade

    public Participante(int id, String nome, String email, String cpf, String telefone, int idMod) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idMod = idMod;
    }

    // Getters e Setters
    public Participante(){}
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public String getCpf() {return cpf;}
    public String getTelefone() {return telefone;}

    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public int getIdMod() {
        return idMod;
    }

    public void setIdMod(int idMod) {
        this.idMod = idMod;
    }

    public void setId(int id) {
        this.id = id;
    }
}
