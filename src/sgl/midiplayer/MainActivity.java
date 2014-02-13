package sgl.midiplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "sgl.midiplayer.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void createKeyboard(View view){
		Intent intent = new Intent(this, KeyboardActivity.class);
		Spinner spScale = (Spinner) findViewById(R.id.spinner_scale);
		Spinner spRoot = (Spinner) findViewById(R.id.spinner_root);
		String[] msg = new String[2]; 
		msg[0] = spRoot.getSelectedItem().toString();
		msg[1] = Integer.toString(spScale.getSelectedItemPosition());
		intent.putExtra(EXTRA_MESSAGE, msg);
		startActivity(intent);
	}

}
