package edu.cnm.deepdive.fizzbuzz.controller;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.fizzbuzz.R;
import edu.cnm.deepdive.fizzbuzz.model.Game;
import edu.cnm.deepdive.fizzbuzz.model.Round;
import edu.cnm.deepdive.fizzbuzz.view.RoundAdapter;
import java.util.List;
import org.w3c.dom.Text;

public class StatusActivity extends AppCompatActivity {

  private ListView roundList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_status); //turn arg into java object in memory
    roundList = findViewById(R.id.round_list);
    Game game = (Game) getIntent().getSerializableExtra(getString(R.string.game_data_key));
    List<Round> rounds = game.getRounds();
    int totalRounds = rounds.size();//for sets and lists, the # of elements contained is given by
    // size()
    int totalCorrect = 0;
    for (Round round : rounds) {
      if (round.isCorrect()) {
        totalCorrect++;
      }
    }
    TextView totalRoundsDisplay = findViewById(R.id.total_rounds);
    TextView totalCorrectDisplay = findViewById(R.id.total_correct);
    totalRoundsDisplay.setText(Integer.toString(totalRounds));
    totalCorrectDisplay.setText(Integer.toString(totalCorrect));
    RoundAdapter adapter = new RoundAdapter(this, game.getRounds());
    roundList.setAdapter(adapter);

  }
}
