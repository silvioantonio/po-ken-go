package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    private ImageButton imageButton;
    private ImageView imageView;
    private TextView textView;

    private int[] images = {R.drawable.fogo, R.drawable.planta};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageButton = findViewById(R.id.button);
        imageView = findViewById(R.id.imagem);
        textView = findViewById(R.id.jogador);

        textView.setText("Player 1");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(images[new Random().nextInt(images.length)]);
                textView.setText("Player 2");
            }
        });

    }
}
