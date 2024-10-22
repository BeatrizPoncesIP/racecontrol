package com.example.racecontrol.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.racecontrol.R;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.repositorios.ParticipanteRepository;
import java.util.ArrayList;

public class ParticipanteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Participante> participantes;
    private ParticipanteRepository participanteRepository; // Repositório de Participantes

    public ParticipanteAdapter(Context context, ArrayList<Participante> participantes) {
        this.context = context;
        this.participantes = participantes;
        this.participanteRepository = new ParticipanteRepository(context); // Inicializa o repositório
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
        return participantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.adapter_participante_item, null);
        Participante participante = participantes.get(position);

        // Busca a Modalidade pelo idMod usando o repositório de participantes
        Modalidade modalidade = participanteRepository.obterModalidadeDoParticipante(participante.getIdMod());

        TextView txtNome = view.findViewById(R.id.participante_nome);
        TextView txtTelefone = view.findViewById(R.id.participante_telefone);
        TextView txtModalidade = view.findViewById(R.id.participante_modalidade);

        txtNome.setText(participante.getNome());
        txtTelefone.setText(participante.getTelefone());

        if (modalidade != null) {
            txtModalidade.setText(modalidade.getDescricao()); // Exibe a descrição da Modalidade
        } else {
            txtModalidade.setText("Modalidade Desconhecida");
        }

        return view;
    }
}