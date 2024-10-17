package com.example.racecontrol.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.racecontrol.bd.entities.Participantes;
import java.util.List;

@Dao
public interface ParticipantesDao {
    @Query("SELECT * FROM Participantes WHERE id=:idUsu LIMIT 1")
    Participantes getUser(int idUsu);

    @Query("SELECT * FROM Usuario")
    List<Usuario> getAll();

    @Insert
    void insertAll(Usuario usuario);
    @Update
    void update(Usuario usuario);
    @Delete
    void delete(Usuario usuario);
}
