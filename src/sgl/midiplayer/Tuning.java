package sgl.midiplayer;

public class Tuning {
	
	private static byte[] sysex = {
			(byte)0xF0,(byte)0x7E,			//universal non-real time header
			(byte)0x7F,						//all devices
			(byte)0x08,						//midi tuning standard
			(byte)0x09,						//scale/8ve tuning dump 2-byte
			(byte)0x00,(byte)0x00,(byte)0x01,//channel options
			//freq for 12 notes 2 bytes each (24 bytes)
			(byte)0x40, (byte)0x00, //C  sysex[8] sysex [9]
			(byte)0x40, (byte)0x00, //C# sysex[10] sysex [11]
			(byte)0x40, (byte)0x00, //D  sysex[12] sysex [13]
			(byte)0x40, (byte)0x00, //D# sysex[14] sysex [15]
			(byte)0x40, (byte)0x00, //E  sysex[16] sysex [17]
			(byte)0x40, (byte)0x00, //F  sysex[18] byte [19]
			(byte)0x40, (byte)0x00, //F# sysex[20] byte [21]
			(byte)0x40, (byte)0x00, //G  sysex[22] byte [23]
			(byte)0x40, (byte)0x00, //G# sysex[24] byte [25]
			(byte)0x40, (byte)0x00, //A  sysex[26] byte [27]
			(byte)0x40, (byte)0x00, //A# sysex[28] byte [29]
			(byte)0x40, (byte)0x00, //B  sysex[30] byte [31]
			(byte)0xF7
			};
	
	/** uses equal temp intervals to calculate freq of root passed as an argument (Note n) based on
	 *  the freq chosen for A (double Aref). Each semitone is aprox 1.05946
	 *  Two semitones is 1.05946^2, and so on
	 **/
	private static double setRef(Notes n, double Aref){
		double d = (double)Notes.getInterval(new Notes(), n);
		return (Aref*Math.pow(1.05946, d));
	}
	
	/** Tune any scale using equal temperament ratios **/
	public static byte[] eqTemp(Scales scale){
		double ref = setRef(scale.getScale().get(0), 220);
		if (scale.getName().equals("Major")){
			double[] intervals = {1.0, 1.121, 1.26, 1.335, 1.499, 1.682, 1.889, 2.0};
			for (int i = 0; i<scale.size(); i++){
				scale.getScale().get(i).setFreq(ref*intervals[i]);
			}		
		}
		for (int i=8; i<32; i+=2){
			sysex[i] = (byte)0x40;
		}
		for (int i=9; i<32; i+=2){
			sysex[i] = (byte)0x00;
		}
		return sysex;
	}
	 
	/** Tune any scale using just intonation ratios **/
	public static byte[] just(Scales scale){
		scale.getScale().get(0).setFreq(setRef(scale.getScale().get(0), 220.0));
		double f = scale.getScale().get(0).getFreq();
		
		if (scale.getName().equals("Major")){		
			double[][] arr = {{1,1},{9,8},{5,4},{4,3},{3,2},{27,16},{15,8}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Dorian")){
			double[][] arr = {{1,1},{10,9},{32,27},{4,3},{3,2},{5,3},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Phrygian")){
			double[][] arr = {{1,1},{16,15},{6,5},{27,20},{3,2},{8,5},{9,5}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Lydian")){
			double[][] arr = {{1,1},{9,8},{81,64},{45,32},{3,2},{27,16},{15,8}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Mixolydian")){
			double[][] arr = {{1,1},{9,8},{5,4},{4,3},{3,2},{5,3},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}

		} else if (scale.getName().equals("Minor")){
			double[][] arr = {{1,1},{10,9},{32,27},{4,3},{40,27},{128,81},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}

		} else if (scale.getName().equals("Locrian")){
			double[][] arr = {{1,1},{16,15},{6,5},{4,3},{64,45},{8,5},{9,5}};

			for (int i=1; i<scale.size(); i++){ 
				changeSysex(scale.getScale().get(i).getName(), 
						(f*arr[i][0]/arr[i][1])/scale.getScale().get(i).getFreq());
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Harmonic Minor")){		
			double[][] arr = {{1,1},{10,9},{32,27},{4,3},{10,27},{128,81},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Locrian #6")){
			double[][] arr = {{1,1},{16,15},{6,5},{4,3},{64,45},{8,5},{9,5}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Ionian Augmented")){
			double[][] arr = {{1,1},{9,8},{5,4},{4,3},{3,2},{27,16},{15,8}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Romanian")){
			double[][] arr = {{1,1},{10,9},{32,27},{4,3},{3,2},{5,3},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
			
		} else if (scale.getName().equals("Phrigian Dominant")){
			double[][] arr = {{1,1},{16,15},{6,5},{27,20},{3,2},{8,5},{9,5}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}

		} else if (scale.getName().equals("Lydian #2")){
			double[][] arr = {{1,1},{9,8},{81,64},{45,32},{3,2},{27,16},{15,8}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}

		} else if (scale.getName().equals("Ultralocrian")){
			double[][] arr = {{1,1},{9,8},{5,4},{4,3},{3,2},{5,3},{16,9}};
			for (int i=1; i<scale.size(); i++){ 
				scale.getScale().get(i).setFreq(f*arr[i][0]/arr[i][1]); 
			}
		}
		return sysex;
	}
	
	private static void changeSysex(String note, double offset){
		double cents;
		cents = 3986.44608 * Math.log10(offset); //convert frq ratio to cents
		byte[] steps = convert(cents*81.92); //convert cents to steps (in byte sysex message)
		if (note.equals("A")){
			sysex[26]=steps[0];
			sysex[27]=steps[1];
		} else if (note.equals("A#")){
			sysex[28]=steps[0];
			sysex[29]=steps[1];
		} else if (note.equals("B")){
			sysex[30]=steps[0];
			sysex[31]=steps[1];
		} else if (note.equals("C")){
			sysex[8]=steps[0];
			sysex[9]=steps[1];
		} else if (note.equals("C#")){
			sysex[10]=steps[0];
			sysex[11]=steps[1];
		} else if (note.equals("D")){
			sysex[12]=steps[0];
			sysex[13]=steps[1];
		} else if (note.equals("D#")){
			sysex[14]=steps[0];
			sysex[15]=steps[1];
		} else if (note.equals("E")){
			sysex[16]=steps[0];
			sysex[17]=steps[1];
		} else if (note.equals("F")){
			sysex[18]=steps[0];
			sysex[19]=steps[1];
		} else if (note.equals("F#")){
			sysex[20]=steps[0];
			sysex[21]=steps[1];
		} else if (note.equals("G")){
			sysex[22]=steps[0];
			sysex[23]=steps[1];
		} else if (note.equals("G#")){
			sysex[26]=steps[0];
			sysex[27]=steps[1];
		}
	}
	
	private static byte[] convert(double steps){
		byte[] result = {0,0};
		int s = (int)Math.round(steps);
		if (s==0){
			result[0]=(byte)0x40;
			result[1]=(byte)0x00;
		} else if (s>0){
			int h = s/128;
			result[0]=(byte)((0x40)+(h));
			result[1]=(byte)(s-(128*h));
		} else { //s < 0
			s = Math.abs(s);
			int h = s/128;
			result[0]=(byte)((0x39)-(h));
			if (h==0){
				result[1]=(byte)(128 - (s-(127*h)));
			} else {
				result[1]=(byte)(128 - (s-(127*h))/h);
			}
		}
		return result;		
	}

} //end of class
