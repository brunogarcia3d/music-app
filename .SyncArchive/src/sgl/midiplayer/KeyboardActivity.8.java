package sgl.midiplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class KeyboardActivity extends Activity implements OnClickListener{

	private float weight = 1;
	private float weightSum;
	private boolean color = true;
	private ArrayList<Button> keys;
	private static final int duration = 1; // seconds
    private static final int sampleRate = 8000;
    private static final int numSamples = duration * sampleRate;
    private Scales scale;
    private ArrayList<Notes> oct_scale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		
		//Intent intent = getIntent();
		//num_notes = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		scale = new Scales(); //A major
		Tune.eqTemp(scale);
		for (Notes n : scale.getScale()){
			oct_scale.add(n);
		}
		oct_scale.add(new Notes(scale.getScale().get(0).getName(), scale.getScale().get(0).getFreq()+12.0));
		
		weightSum = scale.size()+1;
		keys = new ArrayList<Button>();
		LinearLayout ll = (LinearLayout)findViewById(R.id.keyboardlayout);
		ll.setWeightSum(weightSum);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
		
		for (int i=0; i<oct_scale.size(); i++){
			keys.add(new Button(this));
			keys.get(i).setText(oct_scale.get(i).getName());
			keys.get(i).setBackgroundColor(color());
			keys.get(i).setOnClickListener(this);
			ll.addView(keys.get(i),lp);
		}
	}
	
	private int color(){
		color = !color;
		if (color){
			return Color.GRAY;
		} else {
			return Color.WHITE;
		}
	}
	
    private byte[] genTone(double freq){
        double[] sample = new double[numSamples];
        for (int i = 0; i < numSamples; ++i) sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freq));
        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        byte[] sound = new byte[2 * numSamples];
        int idx = 0;
        for (double dVal : sample) {
                short val = (short) (dVal * 32767);
                sound[idx++] = (byte) (val & 0x00ff);
                sound[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
        return sound;
    }
    
    private void playSound(byte[] sound){
        final byte[] play = sound;
        (new Thread(new Runnable() {
                @SuppressWarnings("deprecation")
				@Override
                public void run() {
                        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, numSamples, AudioTrack.MODE_STATIC);
                        audioTrack.write(play, 0, numSamples);
                        audioTrack.play();
                }})).start();
    }

	@Override
	public void onClick(View arg0) {
		int i;
		for (Button btn : keys){
			if (arg0 == btn){
				i = keys.indexOf(btn);
				playSound(genTone((oct_scale.get(i).getFreq())));
			}
		}		
	}
}
