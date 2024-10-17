package com.example.racecontrol.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.racecontrol.bd.entities.Participante;
import java.util.List;

@Dao
public interface ParticipanteDao {

    @Query("SELECT * FROM Participante")
    List<Participante> getAll();

    @Query("SELECT * FROM Participante WHERE id = :id")
    Participante getById(int id);

    @Insert
    void insert(Participante participante);

    @Update
    void update(Participante participante);

    @Delete
    void delete(Participante participante);
}