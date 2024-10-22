package com.example.racecontrol.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.racecontrol.bd.entities.Modalidade;
import java.util.List;

@Dao
public interface ModalidadeDao {

    @Query("SELECT * FROM Modalidade")
    public List<Modalidade> getAllMod();

    @Query("SELECT * FROM Modalidade WHERE id = :idMod") // Retorna a modalidade a partir do Id.
    public Modalidade getModById(int idMod);

    @Insert
    public void insertMod(Modalidade modalidade);

    @Update
    public void updateMod(Modalidade modalidade);

    @Delete
    public void deleteMod(Modalidade modalidade);
}