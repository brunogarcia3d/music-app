package sgl.midiplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyboardActivity extends Activity {

	private float weight = 1;
	private float weightSum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		//Intent intent = getIntent();
		//num_notes = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		Scales scale = new Scales();
		weightSum = scale.size()+1;
		ArrayList<Button> keys = new ArrayList<Button>();
		LinearLayout ll = (LinearLayout)findViewById(R.id.keyboardlayout);
		ll.setWeightSum(weightSum);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
		
		for (int i=0; i<=scale.size(); i++){
			keys.add(new Button(this));
			keys.get(i).setText("uh");
			ll.addView(keys.get(i),lp);
		}
		/**
		for (int i=0; i<=scale.size(); i++){
			keys.get(i).setText("asd");
		}
		
		for (Button btn : keys){
			//btn.setText("a");
			ll.addView(btn,lp);
		}
		
		/**
		Button btn2 = new Button(this);
		btn2.setText("Push me 2");
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
		ll.addView(btn2, lp2);
		**/
	}
}
