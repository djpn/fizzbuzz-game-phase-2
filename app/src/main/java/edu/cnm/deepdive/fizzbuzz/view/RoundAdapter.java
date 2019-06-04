package edu.cnm.deepdive.fizzbuzz.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import edu.cnm.deepdive.fizzbuzz.R;
import edu.cnm.deepdive.fizzbuzz.model.Round;
import edu.cnm.deepdive.fizzbuzz.model.Round.Category;
import java.util.List;

public class RoundAdapter extends ArrayAdapter<Round> {//each constructor has as its first statement
  // the invocation of the parent (super class [no parameter constructor])

  private String[] categoryNames;
  private Drawable correctDrawable;
  private Drawable incorrectDrawable;
  private String correctDescription;
  private String incorrectDescription;
  private int correctColor;
  private int incorrectColor;



  public RoundAdapter(@NonNull Context context, @NonNull List<Round> objects) {
    super(context, R.layout.round_item, objects); //removed "int resource" and replaced w/ R.lay....
    correctDrawable = context.getDrawable(R.drawable.check);
    incorrectDrawable = context.getDrawable(R.drawable.error);
    correctDescription = context.getString(R.string.correct_description);
    incorrectDescription = context.getString(R.string.incorrect_description);
    correctColor = ContextCompat.getColor(getContext(), R.color.correct);
    incorrectColor = ContextCompat.getColor(getContext(), R.color.incorrect);
    Category[] categories = Category.values();
    categoryNames = new String[categories.length];
    Resources res = context.getResources();
    String pkg = context.getPackageName();
    for (int i = 0; i < categories.length; i++) {
      String name = categories[i].toString();
      int id = res.getIdentifier(name, "string", pkg);
      categoryNames[i] = context.getString(id);
    }

  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View layout = (convertView != null) ?
        convertView :
        LayoutInflater.from(getContext()).inflate(R.layout.round_item, parent, false);
    TextView valueDisplay = layout.findViewById(R.id.value_display);
    TextView categoryDisplay = layout.findViewById(R.id.category_display);
    ImageView resultDisplay = layout.findViewById(R.id.result_display);
    Round round = getItem(position);//take data from the Round object and stuff it in the above
    // displays
    valueDisplay.setText(Integer.toString(round.getValue()));
    categoryDisplay.setText(categoryNames[round.getCategory().ordinal()]);
    if (round.isCorrect()) {
      resultDisplay.setImageDrawable(correctDrawable);
      resultDisplay.setContentDescription(correctDescription);
      layout.setBackgroundColor(correctColor);
    } else {
      resultDisplay.setImageDrawable(incorrectDrawable);
      resultDisplay.setContentDescription(incorrectDescription);
      layout.setBackgroundColor(incorrectColor);
    }
    return layout;//push errythang off the stack
  }
}
