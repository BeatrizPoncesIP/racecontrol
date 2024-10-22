package com.example.racecontrol.bd.repositorios;

import android.content.Context;
import com.example.racecontrol.bd.database.AppDatabase;
import com.example.racecontrol.bd.entities.Modalidade;
import java.util.List;

public class ModalidadeRepository {

    private AppDatabase db;

    public ModalidadeRepository(Context context) {
        db = AppDatabase.getDatabase(context);
    }

    // Inserir Modalidade
    public void inserirModalidade(Modalidade modalidade) {
        this.db.modalidadeDao().insertMod(modalidade);
    }

    // Obter todas as Modalidades
    public List<Modalidade> obterModalidades() {
        return db.modalidadeDao().getAllMod();
    }

    // Editar Modalidade
    public void editarModalidade(Modalidade modalidade) {
        this.db.modalidadeDao().updateMod(modalidade);
    }

    // Remover Modalidade
    public void removerModalidade(Modalidade modalidade) {
        this.db.modalidadeDao().deleteMod(modalidade);
    }
}