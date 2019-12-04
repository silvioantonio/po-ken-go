package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    private ImageButton imageButton;
    private ImageSwitcher imageSwitcher;
    private TextView textView;
    private MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;

    private int[] images = {
            R.drawable.fogo,
            R.drawable.planta,
            R.drawable.agua,
            R.drawable.escolha,
            R.drawable.luta_1x1,
            R.drawable.luta_1x2,
            R.drawable.luta_1x3,
            R.drawable.luta_2x2,
            R.drawable.luta_2x3,
            R.drawable.luta_3x3
    };
    int pokemon_1, pokemon_2, cont = 0, position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageButton = findViewById(R.id.button);
        imageSwitcher = findViewById(R.id.image_switcher);
        textView = findViewById(R.id.jogador);

        textView.setText("Player 1: sortear pokemon");

        mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.battle);
        int maxVolume = 100;
        int volumeAtual = 50;
        final float volume =(float) (Math.log(maxVolume - volumeAtual) / Math.log(maxVolume));

        mediaPlayer.start();
        mediaPlayer.setVolume( 1 - volume,  1 - volume);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                imageSwitcher.setBackgroundResource(images[3]);
                return imageView;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont < 2) {
                    Random r = new Random();
                    position = r.nextInt(3);
                    if(cont < 1)
                        pokemon_1 = position + 1;
                    else
                        pokemon_2 = position + 1;
                    imageSwitcher.setBackgroundResource(images[position]);
                    textView.setText("Player 2: sortear pokemon");
                    cont++;
                } else if (cont == 2) {
                    textView.setText("Fight");

                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.victory);
                        mediaPlayer.start();
                        mediaPlayer.setVolume( 1 - volume,  1 - volume);
                    }


                    switch (pokemon_1) {
                        case 1:
                            if (pokemon_2 == 1) {//fogo
                                imageSwitcher.setBackgroundResource(images[4]);
                                int player1 = pegaPontuacao("player1") + 1;
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player1", player1);
                                salvaNosharedPreference("player2", player2);
                            } else if (pokemon_2 == 2) {//planta
                                imageSwitcher.setBackgroundResource(images[5]);
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player2", player2);
                            } else {//agua
                                imageSwitcher.setBackgroundResource(images[6]);
                                int player1 = pegaPontuacao("player1") + 1;
                                salvaNosharedPreference("player1", player1);
                            }
                            break;
                        case 2:
                            if (pokemon_2 == 1) {
                                imageSwitcher.setBackgroundResource(images[5]);
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player2", player2);
                            } else if (pokemon_2 == 2) {
                                imageSwitcher.setBackgroundResource(images[7]);
                                int player1 = pegaPontuacao("player1") + 1;
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player1", player1);
                                salvaNosharedPreference("player2", player2);
                            } else {
                                imageSwitcher.setBackgroundResource(images[8]);
                                int player1 = pegaPontuacao("player1") + 1;
                                salvaNosharedPreference("player1", player1);
                            }
                            break;
                        case 3:
                            if (pokemon_2 == 1) {
                                imageSwitcher.setBackgroundResource(images[6]);
                                int player1 = pegaPontuacao("player1") + 1;
                                salvaNosharedPreference("player1", player1);
                            } else if (pokemon_2 == 2) {
                                imageSwitcher.setBackgroundResource(images[8]);
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player2", player2);
                            } else {
                                imageSwitcher.setBackgroundResource(images[9]);
                                int player1 = pegaPontuacao("player1") + 1;
                                int player2 = pegaPontuacao("player2") + 1;
                                salvaNosharedPreference("player1", player1);
                                salvaNosharedPreference("player2", player2);
                            }
                            break;
                    }
                    cont++;
                } else {
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }

                    startActivity(intent);
                }
                if(cont == 2) imageButton.setBackgroundResource(R.drawable.go);
            }
        });

    }

    private void salvaNosharedPreference(String player, int pontuacao) {
        //Toast.makeText(this, "salva" + player + ": " + pontuacao, Toast.LENGTH_SHORT).show();
        sharedPreferences = getSharedPreferences("score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(player, pontuacao);
        editor.commit();
    }

    private int pegaPontuacao(String player) {
        //Toast.makeText(this, "Pega pontucao do " + player, Toast.LENGTH_SHORT).show();
        sharedPreferences = getSharedPreferences("score", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(player,0);
    }
}
