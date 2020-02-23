package fr.cordier.jeu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {

    private ImageView button;
    private TextView score;
    private TextView time;
    private int clicks = 0;
    private Timer temps=new Timer();
    private int number =20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        this.button= (ImageView) findViewById(R.id.button);
        this.score=(TextView) findViewById(R.id.score);
        this.time=(TextView) findViewById(R.id.timer);

        temps.schedule(new TimerTask() {
            @Override
            public void run() {
                Game.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        number--;
                        time.setText("" + number);
                        if (number<0) {
                            Intent start=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(start);
                            finish();
                            System.exit(0);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clicks++;
                                button.setX((float) Math.random()*((850)+1));
                                button.setY((float) Math.random()*((1400)+1));
                                score.setText("" + clicks);
                            }
                        });
                    }

                });
            }
        },10,1000);

    }
}

