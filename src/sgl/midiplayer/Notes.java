package sgl.midiplayer;

public class Notes {

    private String name;
    private double freq;
    private int midi;
    private static String[] chromatic = 
       {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#",
        "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    
    /** CONSTRUCTORS**/
    public Notes() {
    	name = "A";
    	midi = 69;
    }
    public Notes(String note) {
    	name = note;
    	if (note == "A"){midi=57;}
    	else if (note == "A#"){midi=58;}
    	else if (note == "B"){midi=59;}
    	else if (note == "C"){midi=60;}
    	else if (note == "C#"){midi=61;}
    	else if (note == "D"){midi=62;}
    	else if (note == "D#"){midi=63;}
    	else if (note == "E"){midi=64;}
    	else if (note == "F"){midi=65;}
    	else if (note == "F#"){midi=66;}
    	else if (note == "G"){midi=67;}
    	else if (note == "G#"){midi=68;}
    }
    public Notes(String note, double f){
    	name = note;
    	freq = f;
    }
    
    /** returns a note transported x semitones **/
    public Notes getNote(int semitones){
        int index = 0;
        if (name == null) name ="A";
        while (chromatic[index].equals(name) == false)
            index++;
        return new Notes(chromatic[index+semitones]);
    }
    
    /** returns the interval as the number of semitones between the two notes **/
    public static int getInterval(Notes a, Notes b){
    	int first = 0;
    	while (chromatic[first].equals(a.getName()) == false) first++;
    	int sec = 0;
    	while (chromatic[sec].equals(b.getName()) == false) sec++;
    	return sec-first;
    }
    
    public static String[] getChromatic() {return chromatic;}    
    public void setFreq(double f){freq = f;}
    public void setMidi(int i){midi = i;}
    public double getFreq(){return freq;}
    public String getName(){return name;}
    public int getMidi(){return midi;}
    
}//end of class
