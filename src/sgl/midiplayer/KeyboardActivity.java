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

	private String[] msg;
	private Scales scale;
    private ArrayList<Notes> oct_scale = new ArrayList<Notes>();
	
	/** KEYS VARIABLES **/
	private float weight = 1; //used to proportionally distribute the keys
	private float weightSum;
	private boolean color = true;
	private ArrayList<Button> keys;
	
	/** SOUND VARIABLES **/
	private static final int sampleRate = 8000;
	private static final double duration = 1; // seconds
	private static double dnumSamples = duration * sampleRate;
    private static final int numSamples = (int) dnumSamples;
    private static double sample[] = new double[numSamples];
    private byte generatedSnd[] = new byte[2*numSamples];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		
		Intent intent = getIntent(); //contains root and scale info
		msg = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
		scale = new Scales(new Notes(msg[0]), Integer.parseInt(msg[1]));
		Tuning.just(scale);
		
		//make an 8 note scale from the 7 note scale by adding octave of root
		for (Notes n : scale.getScale()){
			oct_scale.add(n);
		}
		oct_scale.add(new Notes(scale.getScale().get(0).getName()));
		oct_scale.get(7).setFreq(oct_scale.get(0).getFreq()*2);
		
		//create keyboard
		weightSum = scale.size()+1;
		keys = new ArrayList<Button>();
		LinearLayout ll = (LinearLayout)findViewById(R.id.keyboardlayout);
		ll.setWeightSum(weightSum);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
		for (int i=0; i<oct_scale.size(); i++){
			keys.add(new Button(this));
			keys.get(i).setText(oct_scale.get(i).getName()+" "+oct_scale.get(i).getFreq());
			keys.get(i).setBackgroundColor(color());
			keys.get(i).setOnClickListener(this);
			ll.addView(keys.get(i),lp);
		}
	}
	
	//alternate the color of the keys
	private int color(){
		color = !color;
		if (color){
			return Color.GRAY;
		} else {
			return Color.WHITE;
		}
	}
	
	//TODO: move genTone() and playSOund() to a separate static class
    private byte[] genTone(double freq){
        for (int i = 0; i < numSamples; ++i) sample[i] = Math.sin(freq * 2 * Math.PI * i / (sampleRate));
        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        int i = 0;
        int ramp = numSamples/20; // Amplitude ramp as a percent of sample count
        for (i = 0; i< ramp; ++i) {                                     // Ramp amplitude up (to avoid clicks)
            double dVal = sample[i];
                                                                        // Ramp up to maximum
            final short val = (short) ((dVal * 32767 * i/ramp));
                                                                        // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }


        for (i = i; i< numSamples - ramp; ++i) {                        // Max amplitude for most of the samples
            double dVal = sample[i];
                                                                        // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
                                                                        // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        for (i = i; i< numSamples; ++i) {                               // Ramp amplitude down
            double dVal = sample[i];
                                                                        // Ramp down to zero
            final short val = (short) ((dVal * 32767 * (numSamples-i)/ramp ));
                                                                        // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
        return generatedSnd;
    }
    
    private void playSound(byte[] sound){
        final byte[] play = sound;
        (new Thread(new Runnable() {
				@Override
                public void run() {
                        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, numSamples, AudioTrack.MODE_STATIC);
                        audioTrack.write(play, 0, play.length);
                        audioTrack.play();
                }})).start();
    }

	
	@Override
	public void onClick(View arg0) {
		
		int i;
		for (Button btn : keys){
			if (arg0 == btn){
				i = keys.indexOf(btn);
				playSound(genTone((oct_scale.get(i).getFreq()*2)));
			}
		}		
		
	}
}
