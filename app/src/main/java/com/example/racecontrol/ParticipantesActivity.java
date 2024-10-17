package com.example.racecontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.racecontrol.bd.database.AppDatabase;
import com.example.racecontrol.bd.entities.Participante;
import androidx.recyclerview.widget.DividerItemDecoration;
import com.example.racecontrol.decoracao.CustomDividerItemDecoration;
import java.util.List;

public class ParticipantesActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private ParticipanteAdapter participanteAdapter;
    private List<Participante> participanteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        db = AppDatabase.getDatabase(getApplicationContext());
        recyclerView = findViewById(R.id.listaParticipantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarParticipantes(); // Carrega a lista de participantes toda vez que a atividade é retomada
    }

    // Função para carregar os participantes do banco de dados e exibir na lista
    private void carregarParticipantes() {
        new Thread(() -> {
            participanteList = db.participanteDao().getAll(); // Busca todos os participantes
            runOnUiThread(() -> {
                if (participanteAdapter == null) {
                    participanteAdapter = new ParticipanteAdapter(participanteList, db.modalidadeDao(), new ParticipanteAdapter.OnItemClickListener() {
                        @Override
                        public void onEditClick(int position) {
                            // Ação de edição
                        }

                        @Override
                        public void onDeleteClick(int position) {
                            Participante participanteToDelete = participanteList.get(position);
                            new Thread(() -> {
                                db.participanteDao().delete(participanteToDelete);
                                carregarParticipantes();
                            }).start();
                        }
                    });
                    recyclerView.setAdapter(participanteAdapter);
                } else {
                    participanteAdapter.setParticipanteList(participanteList); // Atualiza a lista no Adapter
                }
            });
        }).start();
    }

    // Redireciona para a tela de adicionar participante
    public void addParticipante(View view) {
        // Intent intent = new Intent(this, AddEditParticipanteActivity.class);
        // startActivity(intent);
    }
}