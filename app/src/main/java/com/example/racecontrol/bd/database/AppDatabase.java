package com.example.racecontrol.bd.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.racecontrol.bd.dao.UsuarioDao;
import com.example.racecontrol.bd.entities.Participantes;
import com.example.racecontrol.bd.entities.Modalidade;

@Database(entities = {Participantes.class, Modalidade.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTANCE;
    public static AppDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class,"ControleDeCorridas")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public abstract UsuarioDao usuarioDao();
}