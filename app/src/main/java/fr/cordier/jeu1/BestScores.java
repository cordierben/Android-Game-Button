package fr.cordier.jeu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BestScores extends AppCompatActivity {

    private Button back;
    private DatabaseManager databaseManager;
    private TextView scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);

        this.back= (Button) findViewById(R.id.backmenu);
        this.scores=(TextView) findViewById(R.id.Best);
        databaseManager=new DatabaseManager(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(start);
                finish();
            }
        });


        List<ScoreData> Listscore=databaseManager.top();
        for(ScoreData score : Listscore){
            scores.append("" + score.getScore()+ " by "+score.getName()+"\n");
        }
        databaseManager.close();


    }
}
