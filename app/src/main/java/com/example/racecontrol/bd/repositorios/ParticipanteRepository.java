package com.example.racecontrol.bd.repositorios;

import android.content.Context;
import com.example.racecontrol.bd.dao.ParticipanteDao;
import com.example.racecontrol.bd.dao.ModalidadeDao;
import com.example.racecontrol.bd.database.AppDatabase;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.entities.Modalidade;

import java.util.List;

public class ParticipanteRepository {

    private AppDatabase db;
    private ParticipanteDao participanteDao;
    private ModalidadeDao modalidadeDao;

    public ParticipanteRepository(Context context) {
        // Inicializa o banco de dados e os DAOs
        db = AppDatabase.getDatabase(context);
        participanteDao = db.participanteDao();
        modalidadeDao = db.modalidadeDao();
    }

    // Inserir Participante
    public void inserirParticipante(Participante participante) {
        // Verifica se a modalidade associada ao idMod existe
        Modalidade modalidade = modalidadeDao.getModById(participante.getIdMod());
        if (modalidade != null) {
            participanteDao.insertPart(participante);
        } else {
            throw new IllegalArgumentException("Modalidade Inválida! ID: " + participante.getIdMod());
        }
    }

    // Obter todos os Participantes
    public List<Participante> obterParticipantes() {
        return participanteDao.getAllPart();
    }

    // Editar Participante
    public void editarParticipante(Participante participante) {
        // Verifica se a modalidade associada ao idMod existe antes de atualizar
        Modalidade modalidade = modalidadeDao.getModById(participante.getIdMod());
        if (modalidade != null) {
            participanteDao.updatePart(participante);
        } else {
            throw new IllegalArgumentException("Modalidade Inválida! ID: " + participante.getIdMod());
        }
    }

    // Remover Participante
    public void removerParticipante(Participante participante) {
        participanteDao.deletePart(participante);
    }

    // Obter Modalidade de um Participante
    public Modalidade obterModalidadeDoParticipante(int idMod) {
        return modalidadeDao.getModById(idMod);
    }
}