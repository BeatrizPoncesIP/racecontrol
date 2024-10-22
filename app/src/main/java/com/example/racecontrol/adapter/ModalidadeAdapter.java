package com.example.racecontrol.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.racecontrol.R;
import com.example.racecontrol.activities.AddEditModalidadeActivity;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.repositorios.ModalidadeRepository;
import java.util.ArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModalidadeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Modalidade> modalidades;
    private ModalidadeRepository modalidadeRepository;

    public ModalidadeAdapter(Context context, ArrayList<Modalidade> modalidades) {
        this.context = context;
        this.modalidades = modalidades;
        this.modalidadeRepository = new ModalidadeRepository(context);
    }

    @Override
    public int getCount() {
        return modalidades.size();
    }

    @Override
    public Object getItem(int position) {
        return modalidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modalidades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.adapter_modalidade_item, null);

        Modalidade modalidade = modalidades.get(position);

        TextView txtId = view.findViewById(R.id.modalidade_id);
        TextView txtDescricao = view.findViewById(R.id.modalidade_descricao);
        FloatingActionButton btnEditar = view.findViewById(R.id.btnEditarModalidade); // Mude para FloatingActionButton
        FloatingActionButton btnExcluir = view.findViewById(R.id.btnExcluirModalidade); // Mude para FloatingActionButton

        txtId.setText(String.valueOf(modalidade.getId()));
        txtDescricao.setText(modalidade.getDescricao());

        // Ação de editar
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEditModalidadeActivity.class);
                intent.putExtra("modalidade", modalidade); // Passa o objeto Modalidade
                context.startActivity(intent);
            }
        });

        // Ação de excluir
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalidadeRepository.removerModalidade(modalidade);
                modalidades.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}