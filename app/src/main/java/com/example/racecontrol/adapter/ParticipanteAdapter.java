package com.example.racecontrol.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.racecontrol.R;
import com.example.racecontrol.activities.AddEditParticipanteActivity;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.repositorios.ParticipanteRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ParticipanteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Participante> participantes;
    private ParticipanteRepository participanteRepository; // Repositório de Participantes

    public ParticipanteAdapter(Context context, ArrayList<Participante> participantes) {
        this.context = context;
        this.participantes = participantes;
        this.participanteRepository = new ParticipanteRepository(context); // Inicializando o repositório
    }

    @Override
    public int getCount() {
        return participantes.size();
    }

    @Override
    public Object getItem(int position) {
        return participantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.adapter_participante_item, null);

        TextView txtNome = view.findViewById(R.id.participante_nome);
        TextView txtTelefone = view.findViewById(R.id.participante_telefone);
        TextView txtModalidade = view.findViewById(R.id.participante_modalidade);
        FloatingActionButton btnEdit = view.findViewById(R.id.btnEditarParticipante);
        FloatingActionButton btnExcluir = view.findViewById(R.id.btnExcluirParticipante);

        Participante participante = participantes.get(position);

        txtNome.setText(participante.getNome());
        txtTelefone.setText(participante.getTelefone());

        // Pega a modalidade pela chave estrangeira e verifica se o objeto não é nulo
        Modalidade modalidade = participanteRepository.obterModalidadeDoParticipante(participante.getIdMod());
        if (modalidade != null) {
            txtModalidade.setText(modalidade.getDescricao());
        } else {
            txtModalidade.setText("Não definida"); // Valor padrão caso a modalidade seja nula
        }

        // Ação de edição
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditParticipanteActivity.class);
            intent.putExtra("id", participante.getId());
            context.startActivity(intent);
        });

        // Ação de excluir
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participanteRepository.removerParticipante(participante);
                participantes.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}