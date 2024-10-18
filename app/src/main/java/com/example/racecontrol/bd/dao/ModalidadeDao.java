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
    List<Modalidade> getAllMod();

    @Query("SELECT * FROM Modalidade WHERE idMod = :idMod")
    Modalidade getModById(int idMod);

    @Insert
    void insertMod(Modalidade modalidade);

    @Update
    void updateMod(Modalidade modalidade);

    @Delete
    void deleteMod(Modalidade modalidade);

    @Query("SELECT * FROM Modalidade WHERE idMod = :idMod") // Retorna a modalidade a partir do Id.
    Modalidade getModalidadeById(int idMod);
}