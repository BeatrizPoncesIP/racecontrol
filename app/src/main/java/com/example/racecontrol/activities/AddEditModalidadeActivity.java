package com.example.racecontrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.racecontrol.R;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.repositorios.ModalidadeRepository;

public class AddEditModalidadeActivity extends AppCompatActivity {

    private EditText editDescricao;
    private ModalidadeRepository modalidadeRepository;
    private Modalidade modalidade; // Objeto que será editado
    private Button btnSalvar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_modalidade);

        // Inicializa o repositório
        modalidadeRepository = new ModalidadeRepository(getApplicationContext());

        // Obtém referências aos componentes da interface
        editDescricao = findViewById(R.id.editDescricao);
        btnSalvar = findViewById(R.id.btnSalvarModalidade);
        btnCancelar = findViewById(R.id.btnCancelarModalidade);

        // Verifica se estamos editando uma modalidade existente
        modalidade = (Modalidade) getIntent().getSerializableExtra("modalidade");

        if (modalidade != null) {
            // Se for uma edição, preenche o campo de descrição
            editDescricao.setText(modalidade.getDescricao());
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Volta para a tela anterior
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtém os dados inseridos
                String descricao = editDescricao.getText().toString().trim();

                // Valida os dados
                if (descricao.isEmpty()) {
                    Toast.makeText(AddEditModalidadeActivity.this, "Modalidade Inválida!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Se for novo:
                if (modalidade == null) {
                    Modalidade novaModalidade = new Modalidade(descricao);
                    modalidadeRepository.inserirModalidade(novaModalidade);
                    Toast.makeText(AddEditModalidadeActivity.this, "Modalidade adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    // Se estamos editando
                    modalidade.setDescricao(descricao);
                    modalidadeRepository.editarModalidade(modalidade);
                    Toast.makeText(AddEditModalidadeActivity.this, "Modalidade editada com sucesso!", Toast.LENGTH_SHORT).show();
                }

                // Finaliza a activity e retorna à tela anterior
                finish();
            }
        });
    }
}