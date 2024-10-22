package com.example.racecontrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.racecontrol.R;
import com.example.racecontrol.adapter.ParticipanteAdapter;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.repositorios.ParticipanteRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ParticipantesActivity extends AppCompatActivity {

    private ListView listViewParticipantes;
    private FloatingActionButton btnAdicionarParticipante;
    private ParticipanteAdapter participanteAdapter;
    private ParticipanteRepository participanteRepository;
    private ArrayList<Participante> listaDeParticipantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        // Inicializa o repositório
        participanteRepository = new ParticipanteRepository(this);

        // Inicializa a lista de participantes
        listaDeParticipantes = (ArrayList<Participante>) participanteRepository.obterParticipantes();

        // Configura o ListView e o Adapter
        listViewParticipantes = findViewById(R.id.listaParticipantes);
        participanteAdapter = new ParticipanteAdapter(this, listaDeParticipantes);
        listViewParticipantes.setAdapter(participanteAdapter);

        // Botão para adicionar novo participante
        btnAdicionarParticipante = findViewById(R.id.btnAdicionarParticipante);
        btnAdicionarParticipante.setOnClickListener(v -> {
            Intent intent = new Intent(ParticipantesActivity.this, AddEditParticipanteActivity.class);
            startActivity(intent);
        });

        // Botão para voltar
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Volta para a MainActivity
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza a lista quando voltar para a activity
        listaDeParticipantes.clear();
        listaDeParticipantes.addAll(participanteRepository.obterParticipantes());
        participanteAdapter.notifyDataSetChanged();
    }
}