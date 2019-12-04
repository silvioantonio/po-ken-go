package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button novo_jogo, resetar_jogo;
    private Intent intent;
    private MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;
    private TextView player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        sharedPreferences = getSharedPreferences("score", MODE_PRIVATE);

        if(sharedPreferences.contains("player1")){
            int soma = 0;
            if (player1.getText().toString().length()>0)
                soma = Integer.parseInt( player1.getText().toString());
            soma += sharedPreferences.getInt("player1", soma);
            player1.setText(String.valueOf(soma));
        }
        if(sharedPreferences.contains("player2")){
            int soma = 0;
            if(player2.getText().toString().length()>0){
                soma = Integer.parseInt(player2.getText().toString());
                System.out.println("SOMA : "+soma);
            }
            soma += sharedPreferences.getInt("player2", soma);
            System.out.println("SOMA : : "+ soma);
            player2.setText(String.valueOf(soma));
        }

        int maxVolume = 100;
        int currentVolume = 30;
        float volume = (float) (Math.log(maxVolume - currentVolume) / Math.log(maxVolume));

        novo_jogo = findViewById(R.id.novo_jogo);
        resetar_jogo = findViewById(R.id.resetar_jogo);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.opening);

        mediaPlayer.setVolume(1-volume, 1-volume);
        mediaPlayer.start();

        /*if(getIntent().getExtras().getString("score") != null) {
            score = getIntent().getExtras().getString("score");
        } else {
            score = "";
        }*/

        novo_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        resetar_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                player1.setText("");
                player2.setText("");
            }
        });

    }
}
