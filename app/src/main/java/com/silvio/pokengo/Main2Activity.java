package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    private ImageButton imageButton;
    private ImageSwitcher imageSwitcher;
    private TextView textView;
    private MediaPlayer mediaPlayer;

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
                //imageView.setImageResource(images[new Random().nextInt(images.length)]);
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
                    textView.setText("Figth");

                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.victory);
                        mediaPlayer.start();
                        mediaPlayer.setVolume( 1 - volume,  1 - volume);
                    }

                    switch (pokemon_1){
                        case 1:
                            if (pokemon_2 == 1)
                                imageSwitcher.setBackgroundResource(images[4]);
                            else if (pokemon_2 == 2)
                                imageSwitcher.setBackgroundResource(images[5]);
                            else
                                imageSwitcher.setBackgroundResource(images[6]);
                            break;
                        case 2:
                            if (pokemon_2 == 1)
                                imageSwitcher.setBackgroundResource(images[5]);
                            else if (pokemon_2 == 2)
                                imageSwitcher.setBackgroundResource(images[7]);
                            else
                                imageSwitcher.setBackgroundResource(images[8]);
                            break;
                        case 3:
                            if (pokemon_2 == 1)
                                imageSwitcher.setBackgroundResource(images[6]);
                            else if (pokemon_2 == 2)
                                imageSwitcher.setBackgroundResource(images[8]);
                            else
                                imageSwitcher.setBackgroundResource(images[9]);
                            break;
                    }
                    cont++;
                } else {
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    //intent.putExtra("score", score);
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    startActivity(intent);
                }
            }
        });

    }
}
