package com.silvio.pokengo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
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

    private int[] images = {R.drawable.fogo, R.drawable.planta, R.drawable.agua, R.drawable.placeholder};
    int position = -1;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageButton = findViewById(R.id.button);
        imageSwitcher = findViewById(R.id.image_switcher);
        textView = findViewById(R.id.jogador);

        textView.setText("Player 1");

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView.setImageResource(images[new Random().nextInt(images.length)]);
                if(cont < 2) {
                    if(position<images.length-1){
                        position++;
                        imageSwitcher.setBackgroundResource(images[position]);
                    }
                    textView.setText("Player 2");
                    cont++;
                } else if (cont == 2) {
                    imageSwitcher.setBackgroundResource(images[3]);
                    textView.setText("Figth");
                    cont++;
                } else {
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    //intent.putExtra("score", score);
                    startActivity(intent);
                }
            }
        });

    }
}
