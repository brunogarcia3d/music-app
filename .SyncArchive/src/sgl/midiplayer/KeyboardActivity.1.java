package sgl.midiplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyboardActivity extends Activity {

	final Activity me = this;
	private int num_notes;
	LinearLayout layout;
	LayoutParams params;
	LayoutParams layout_params;
	ArrayList<Button> keys;
	String[] txt = {"C", "D","E", "F", "G", "A", "B", "C"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.keyboard);
		Intent intent = getIntent();
		num_notes = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		/**
		Button c_key = (Button) findViewById(R.id.c1);
		c_key.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Toast.makeText(me, "key was pressed in "+num_notes, Toast.LENGTH_LONG).show();
			}
		});
		**/
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		for (int i=0; i<=num_notes; i++){
			keys.add(new Button(this));
			keys.get(i).setText(txt[i]);
			keys.get(i).setLayoutParams(params);
		}
		
		for (Button btn : keys){
			layout.addView(btn);
		}
		
		layout_params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		this.addContentView(layout, layout_params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.keyboard, menu);
		return true;
	}	

}
