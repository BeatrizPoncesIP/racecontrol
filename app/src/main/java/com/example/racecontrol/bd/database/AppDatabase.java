package com.example.racecontrol.bd.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.racecontrol.bd.dao.ParticipanteDao;
import com.example.racecontrol.bd.dao.ModalidadeDao;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.entities.Modalidade;

@Database(entities = {Participante.class, Modalidade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    // Método para obter a instância do banco de dados
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            // Cria a instância do banco de dados se ainda não existir
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ControleDeCorrida")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    // Métodos para acessar as tabelas Participante e Modalidade
    public abstract ParticipanteDao participanteDao();
    public abstract ModalidadeDao modalidadeDao();
}
