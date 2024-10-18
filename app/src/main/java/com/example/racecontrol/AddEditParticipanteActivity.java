package com.example.racecontrol;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.racecontrol.bd.dao.ParticipanteDao;
import com.example.racecontrol.bd.dao.ModalidadeDao;
import com.example.racecontrol.bd.database.AppDatabase;
import com.example.racecontrol.bd.entities.Modalidade;
import com.example.racecontrol.bd.entities.Participante;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddEditParticipanteActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextCPF, editTextTelefone;
    private Spinner spinnerModalidade;
    private Button buttonSalvar, buttonCancelar;
    private ParticipanteDao participanteDao;
    private ModalidadeDao modalidadeDao;
    private ExecutorService executorService;
    private Participante participante; // Usado para edição
    private List<Modalidade> modalidades; // Lista de modalidades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_participante);

        // Inicializando os campos
        editTextNome = findViewById(R.id.editNome);
        editTextEmail = findViewById(R.id.editEmail);
        editTextCPF = findViewById(R.id.editCPF);
        editTextTelefone = findViewById(R.id.editTelefone);
        spinnerModalidade = findViewById(R.id.spinnerModalidade);
        buttonSalvar = findViewById(R.id.btnSalvar);
        buttonCancelar = findViewById(R.id.btnCancelar);

        // Inicializando o DAO e o executor
        participanteDao = AppDatabase.getDatabase(this).participanteDao();
        modalidadeDao = AppDatabase.getDatabase(this).modalidadeDao();
        executorService = Executors.newSingleThreadExecutor();

        // Carregar modalidades
        carregarModalidades();

        // Verifica se estamos editando um participante existente
        if (getIntent().hasExtra("id")) {
            int participanteId = getIntent().getIntExtra("id", -1);
            carregarParticipante(participanteId);
        }

        buttonSalvar.setOnClickListener(v -> salvarParticipante());
        buttonCancelar.setOnClickListener(v -> finish()); // Voltar para a tela anterior
    }

    private void carregarModalidades() {
        executorService.execute(() -> {
            modalidades = modalidadeDao.getAll(); // Busca todas as modalidades
            runOnUiThread(() -> {
                ArrayAdapter<Modalidade> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modalidades);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModalidade.setAdapter(adapter);
            });
        });
    }

    private void carregarParticipante(int id) {
        executorService.execute(() -> {
            participante = participanteDao.getById(id); // Usa o método getById
            runOnUiThread(() -> {
                if (participante != null) {
                    editTextNome.setText(participante.getNome());
                    editTextEmail.setText(participante.getEmail());
                    editTextCPF.setText(participante.getCpf());
                    editTextTelefone.setText(participante.getTelefone());
                    // Ajustar o spinner para a modalidade certa
                    int spinnerPosition = 0; // Posição padrão
                    for (int i = 0; i < modalidades.size(); i++) {
                        if (modalidades.get(i).getId() == participante.getIdMod()) {
                            spinnerPosition = i;
                            break;
                        }
                    }
                    spinnerModalidade.setSelection(spinnerPosition);
                }
            });
        });
    }

    private void salvarParticipante() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String cpf = editTextCPF.getText().toString().trim();
        String telefone = editTextTelefone.getText().toString().trim();
        Modalidade modalidadeSelecionada = (Modalidade) spinnerModalidade.getSelectedItem();
        int idMod = modalidadeSelecionada != null ? modalidadeSelecionada.getId() : -1; // Pega o ID da modalidade

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se estamos editando ou adicionando
        executorService.execute(() -> {
            if (participante == null) {
                // Adicionando novo participante
                participante = new Participante();
                participante.setNome(nome);
                participante.setEmail(email);
                participante.setCpf(cpf);
                participante.setTelefone(telefone);
                participante.setIdMod(idMod);
                participanteDao.insert(participante);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Participante adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    finish(); // Voltar após salvar
                });
            } else {
                // Editando participante existente
                participante.setNome(nome);
                participante.setEmail(email);
                participante.setCpf(cpf);
                participante.setTelefone(telefone);
                participante.setIdMod(idMod);
                participanteDao.update(participante);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Participante atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    finish(); // Voltar após atualizar
                });
            }
        });
    }
}