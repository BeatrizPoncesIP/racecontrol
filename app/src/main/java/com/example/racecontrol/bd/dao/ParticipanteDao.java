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
    public List<Participante> getAllPart();

    @Query("SELECT * FROM Participante WHERE id = :id")
    public Participante getPartById(int id);

    @Insert
    public void insertPart(Participante participante);

    @Update
    public void updatePart(Participante participante);

    @Delete
    public void deletePart(Participante participante);
}