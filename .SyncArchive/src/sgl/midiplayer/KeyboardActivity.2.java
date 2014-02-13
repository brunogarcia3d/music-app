package sgl.midiplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyboardActivity extends Activity {

	private float weight = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		//Intent intent = getIntent();
		//num_notes = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		Scales scale = new Scales();
		int weightSum = scale.size()+1;
		ArrayList<Button> keys = new ArrayList<Button>();
		
		Button btn = new Button(this);
		btn.setText("Push me");
		LinearLayout ll = (LinearLayout)findViewById(R.id.keyboardlayout);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
		ll.addView(btn, lp);
		
		Button btn2 = new Button(this);
		btn.setText("Push me 2");
		LinearLayout ll2 = (LinearLayout)findViewById(R.id.keyboardlayout);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
		ll2.addView(btn2, lp2);
	}
}
