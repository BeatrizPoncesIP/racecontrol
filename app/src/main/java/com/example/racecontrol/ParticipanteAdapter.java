package com.example.racecontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.racecontrol.bd.entities.Participante;
import com.example.racecontrol.bd.dao.ModalidadeDao;
import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ParticipanteViewHolder> {

    private List<Participante> participanteList;
    private ModalidadeDao modalidadeDao; // Adicione esta linha para o ModalidadeDao
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public ParticipanteAdapter(List<Participante> participanteList, ModalidadeDao modalidadeDao, OnItemClickListener listener) {
        this.participanteList = participanteList;
        this.modalidadeDao = modalidadeDao; // Inicialize o ModalidadeDao
        this.listener = listener;
    }

    @NonNull
    @Override
    public ParticipanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_participante, parent, false);
        return new ParticipanteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteViewHolder holder, int position) {
        Participante participante = participanteList.get(position);
        String modalidadeNome = String.valueOf(modalidadeDao.getModalidadeById(participante.getIdMod())); // Buscando a modalidade
        holder.bind(participante, modalidadeNome, listener);
    }

    @Override
    public int getItemCount() {
        return participanteList.size();
    }

    public void setParticipanteList(List<Participante> participantes) {
        this.participanteList.clear(); // Limpa a lista atual
        this.participanteList.addAll(participantes); // Adiciona todos os novos participantes
        notifyDataSetChanged(); // Notifica que os dados mudaram
    }

    public static class ParticipanteViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView phoneTextView;
        public TextView modalidadeTextView; // Novo campo para a modalidade
        public Button editButton;
        public Button deleteButton;

        public ParticipanteViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.participante_nome);
            phoneTextView = itemView.findViewById(R.id.participante_telefone);
            modalidadeTextView = itemView.findViewById(R.id.participante_modalidade); // TextView para a modalidade
            editButton = itemView.findViewById(R.id.btnEditar);
            deleteButton = itemView.findViewById(R.id.btnExcluir);
        }

        public void bind(final Participante participante, String modalidadeNome, final OnItemClickListener listener) {
            nameTextView.setText(participante.getNome());
            phoneTextView.setText(participante.getTelefone());
            modalidadeTextView.setText(modalidadeNome);
            editButton.setOnClickListener(v -> listener.onEditClick(getAdapterPosition()));
            deleteButton.setOnClickListener(v -> listener.onDeleteClick(getAdapterPosition()));
        }
    }
}