package com.example.racecontrol.bd.repositorios;

import android.app.Application;
import com.example.racecontrol.bd.dao.ParticipanteDao;
import com.example.racecontrol.bd.database.AppDatabase;
import com.example.racecontrol.bd.entities.Participante;

import java.util.ArrayList;

public class repoPart {
    private AppDatabase bd;

    public repoPart(Application application) {
        AppDatabase bd = AppDatabase.getDatabase(application);
    }//conecta reposit´rio com o banco de dados... (incompleto)

    public void insertPart(Participante participante) {

    }

    public void updatePart(Participante participante) {

    }

    public void deletePart(Participante participante) {

    }

}
