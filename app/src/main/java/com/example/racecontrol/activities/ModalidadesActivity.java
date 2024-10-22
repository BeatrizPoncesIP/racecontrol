package com.example.racecontrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.racecontrol.R;
import com.example.racecontrol.adapter.ModalidadeAdapter;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.repositorios.ModalidadeRepository;
import java.util.ArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModalidadesActivity extends AppCompatActivity {

    private ListView listViewModalidades;
    private FloatingActionButton btnAdicionarModalidade; // Mude para FloatingActionButton
    private ModalidadeAdapter modalidadeAdapter;
    private ModalidadeRepository modalidadeRepository;
    private ArrayList<Modalidade> listaDeModalidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidades);

        // Inicializa o repositório
        modalidadeRepository = new ModalidadeRepository(this);

        // Inicializa a lista de modalidades
        listaDeModalidades = new ArrayList<>(modalidadeRepository.obterModalidades());

        // Configura o ListView e o Adapter
        listViewModalidades = findViewById(R.id.listaModalidades);
        modalidadeAdapter = new ModalidadeAdapter(this, listaDeModalidades);
        listViewModalidades.setAdapter(modalidadeAdapter);

        // Botão para adicionar nova modalidade
        btnAdicionarModalidade = findViewById(R.id.btnAdicionarModalidade);
        btnAdicionarModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModalidadesActivity.this, AddEditModalidadeActivity.class);
                startActivity(intent);
            }
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
        listaDeModalidades.clear();
        listaDeModalidades.addAll(modalidadeRepository.obterModalidades());
        modalidadeAdapter.notifyDataSetChanged();
    }
}