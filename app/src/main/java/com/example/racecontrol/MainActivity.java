package com.example.racecontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnParticipantes;
    private Button btnModalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParticipantes = findViewById(R.id.btnParticipantes);
        btnModalidade = findViewById(R.id.btnModalidade);

        btnParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentParticipante = new Intent(MainActivity.this, ParticipantesActivity.class);
                startActivity(intentParticipante);
            }
        });

        btnModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentModalidade = new Intent(MainActivity.this, ModalidadesActivity.class);
                startActivity(intentModalidade);
            }
        });
    }
}