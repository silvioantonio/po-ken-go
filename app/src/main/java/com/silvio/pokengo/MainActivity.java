package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button novo_jogo, resetar_jogo;
    private Intent intent;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novo_jogo = findViewById(R.id.novo_jogo);
        resetar_jogo = findViewById(R.id.resetar_jogo);

        /*if(getIntent().getExtras().getString("score") != null) {
            score = getIntent().getExtras().getString("score");
        } else {
            score = "";
        }*/

        novo_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        resetar_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
