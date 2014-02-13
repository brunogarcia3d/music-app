package sgl.midiplayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Scales {
    
    private ArrayList<Notes> scaleNotes = new ArrayList<Notes>();
    private String name;
    
    /** SCALES: **/
    static final int MAJOR = 0;
    static final int DORIAN = 1;
    static final int PHRYGIAN = 2;
    static final int LYDIAN = 3;
    static final int MIXOLYDIAN = 4;
    static final int MINOR = 5;
    static final int LOCRIAN = 6;
    static final int H_MINOR = 7;
    static final int LOCRIAN_S6 = 8;
    static final int IONIAN_AUG = 9;
    static final int ROMANIAN = 10;
    static final int PHRYGIAN_DOM = 11;
    static final int LYDIAN_S2 = 12;
    static final int ULTRALOCRIAN = 13;
    static final int M_MINOR = 14;
    static final int JAVANESE = 15;
    static final int LYDIAN_AUG = 16;
    static final int OVERTONE = 17;
    static final int HINDU = 18;
    static final int SEMILOCRIAN = 19;
    static final int SUPERLOCRIAN = 20;
    static final int MAJ_PENTATONIC = 21;
    static final int MIN_PENTATONIC = 22;
    static final int JAPAN = 23;
    static final int INDIAN = 24;
    static final int AUG = 25;
    static final int DIM = 26;
    static final int SYMM = 27;
    static final int ENIG = 28;
    static final int MAJ_NAP = 29;
    static final int MIN_NAP = 30;
    static final int MIN_HUNG = 31;
    static final int KUMOI = 32;
    static final int BLUES = 33;
    static final int ARABIC = 34;
    
    /**CONSTRUCTORS**/
    public Scales(){create(new Notes("A"), MAJOR);}
    public Scales(Notes note, int name){create(note, name);}
    
    public String getName(){
    	return name;
    }
    
    /** returns an array of scales that contain the notes passed as arg **/
    //TODO I don't think this is working, containsAll needs to check string not object Note
    public static ArrayList<Scales> find(ArrayList<Notes> theNotes){
        Scales temp = new Scales();
        ArrayList<Scales> result = new ArrayList<Scales>();
        for (Notes r : theNotes)
            for (int i=0; i<=34; i++){
                temp.create(r, i);
                if (temp.getScale().containsAll(theNotes)){
                    Scales x = new Scales();
                    x.scaleNotes.clear();
                    x.scaleNotes.addAll(temp.getScale());
                    x.name = temp.name;
                    result.add(x);
                }
            }
        return result;      
    }
    
    public ArrayList<Notes> getScale(){return scaleNotes;}
    
    public Notes note(int pos){return scaleNotes.get(pos);}
    
    public int size(){return scaleNotes.size();}
    
    public String print(){
        String x = scaleNotes.get(0).getName() + " " + name + ": ";
        for (Notes n : scaleNotes) {
        	x += n.getName() + " ";
        }
        return x;
    }
    
    /** harmonize a scale (creates all possible chords using notes of the scale) **/
    public ArrayList<Chords> harmonize(){
        ArrayList<Chords> listChords = new ArrayList<Chords>();
        for (int n1 = 0; n1 < (this.getScale().size()-3); n1++)
            for (int n2 = n1+1; n2 < (this.getScale().size()-2); n2++)
                for (int n3 = n2+1; n3 < (this.getScale().size()); n3++)
                    listChords.addAll(Chords.toChords((ArrayList<Notes>)Arrays.asList
                    		(new Notes[]{this.getScale().get(n1), this.getScale().get(n2), this.getScale().get(n3)})));
        while (listChords.remove(null));
        return listChords;
    }

    
    /** creates a scale from a note and a type of scale**/
    private void create(Notes note, int n){
        scaleNotes.clear();
        switch (n){
            case MAJOR:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Major";
                break;
            case DORIAN:
            	scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Dorian";
                break;
            case PHRYGIAN:
            	scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Phrygian";
                break;
			case LYDIAN:
				scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Lydian";
                break;
            case MIXOLYDIAN:
				scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Mixolydian";
                break;
            case MINOR:
				scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Minor";
                break;
            case LOCRIAN:
				scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Locrian";
                break;
            case H_MINOR:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(11));
                this.name = "Harmonic Minor";
                break;
            case LOCRIAN_S6:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Locrian #6";
                break;
            case IONIAN_AUG:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Ionian Augmented";
                break;
            case ROMANIAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Romanian";
                break;
            case PHRYGIAN_DOM:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Phrigian Dominant";
                break;
            case LYDIAN_S2:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Lydian #2";
                break;
            case ULTRALOCRIAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(9));
                this.name = "Ultralocrian";
                break;
            case M_MINOR:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Melodic Minor";
                break;
            case JAVANESE:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Javanese";
                break;
            case LYDIAN_AUG:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Lydian Aug";
                break;
            case OVERTONE:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Overtone";
                break;
            case HINDU:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Hindu";
                break;
            case SEMILOCRIAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Semilocrian";
                break;
            case SUPERLOCRIAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Superlocrian";
                break;
            case MAJ_PENTATONIC:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                this.name = "Major Pentatonic";
                break;
            case MIN_PENTATONIC:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                this.name = "Minor Pentatonic";
                break;
            case JAPAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Japanese";
                break;
            case INDIAN:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(10));
                this.name = "Indian";
                break;
            case AUG:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                this.name = "Augmented";
                break;
            case DIM:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Diminished";
                break;
            case SYMM:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(10));
                this.name = "Symmetrical";
                break;
            case ENIG:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(10));
                scaleNotes.add(note.getNote(11));
                this.name = "Enigmatic";
                break;
            case MAJ_NAP:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Major Napolitan";
                break;
            case MIN_NAP:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(9));
                scaleNotes.add(note.getNote(11));
                this.name = "Minor Napolitan";
                break;
            case MIN_HUNG:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(11));
                this.name = "Minor Hungarian";
                break;
            case KUMOI:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(2));
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(9));
                this.name = "Kumoi";
                break;
            case BLUES:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(3));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(6));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(10));
                this.name = "Blues";
                break;
            case ARABIC:
                scaleNotes.add(note);
                scaleNotes.add(note.getNote(1));
                scaleNotes.add(note.getNote(4));
                scaleNotes.add(note.getNote(5));
                scaleNotes.add(note.getNote(7));
                scaleNotes.add(note.getNote(8));
                scaleNotes.add(note.getNote(11));
                this.name = "Arabic";
                break;
        }//end of switch    
    }//end of create  
    
}//end of class
