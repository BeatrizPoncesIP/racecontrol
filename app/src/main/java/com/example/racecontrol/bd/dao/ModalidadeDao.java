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
    List<Modalidade> getAll();

    @Query("SELECT * FROM Modalidade WHERE id = :id")
    Modalidade getById(int id);

    @Insert
    void insert(Modalidade modalidade);

    @Update
    void update(Modalidade modalidade);

    @Delete
    void delete(Modalidade modalidade);

    @Query("SELECT * FROM Modalidade WHERE id = :idMod") // Retorna a modalidade a partir do Id.
    Modalidade getModalidadeById(int idMod);
}