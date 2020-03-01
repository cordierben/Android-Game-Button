package fr.cordier.jeu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ResultPage extends AppCompatActivity {

    private TextView scoreView;
    private DatabaseManager databaseManager;
    private TextView points;
    private Button menu;
    private Button retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);

        scoreView = (TextView) findViewById(R.id.scoreView);
        databaseManager= new DatabaseManager(this);
        points= (TextView) findViewById(R.id.resultatpoints);
        menu=(Button) findViewById(R.id.buttonResult);
        retry=(Button) findViewById(R.id.PlayAgain);

        ScoreData score=databaseManager.score();
        points.setText("" + score.getScore());
        databaseManager.close();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(start);
                finish();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(getApplicationContext(), Game.class);
                startActivity(start);
                finish();
            }
        });
    }
}
