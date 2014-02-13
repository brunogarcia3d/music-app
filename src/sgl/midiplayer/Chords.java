package sgl.midiplayer;

import java.util.ArrayList;
import java.util.Iterator;

public class Chords {
    
    //declarations:
    private ArrayList<Notes> chordNotes = new ArrayList<Notes>();
    private String name;
    
    static final int MAJOR = 0;
    static final int MINOR = 1;
    static final int AUG = 2;
    static final int DIM = 3;
    static final int MIN5AUG = 4;
    static final int MAJ5DIM = 5;
    static final int SUS2 = 6;
    static final int SUS4 = 7;
    
    /** CONSTRUCTORS **/
    public Chords() {create(new Notes("A"), MAJOR);}
    public Chords(Notes root, int name) {create(root, name);}
    
    public static ArrayList<Chords> toChords(ArrayList<Notes> notes){
    	ArrayList<Chords> result = new ArrayList<Chords>();
    	Chords temp = new Chords();
    	for (Notes n : notes){
    		for (int i=MAJOR; i<=SUS4; i++){ //each chord defined in the class
    			temp.create(n, i);
    			if (temp.containsAll(notes)){
    				result.add(new Chords(n,i));
    			}
    		}
    	}
    	return result;
    }
    
    private boolean containsAll(ArrayList<Notes> an){
    	ArrayList<String> caller = new ArrayList<String>();
    	ArrayList<String> als = new ArrayList<String>();
    	for (Notes n : this.getChord()){
    		caller.add(n.getName());
    	}
    	for (Notes n : an){
    		als.add(n.getName());
    	}
    	return caller.containsAll(als);
    }
    
    
    /** find all the chords with root 'note' that belong to scale 'scale' **/
    public static ArrayList<Chords> make (Scales scale, Notes note) {
        ArrayList<Chords> result = scale.harmonize();
        for (Iterator<?> it = result.iterator(); it.hasNext();){
            Chords c = (Chords)it.next();
            if (!(c.getChord().get(0).getName().equals(note.getName())))
                it.remove();
        }
        return result;
    }
    
    /** create a chord from a note and a type of chord **/
    public void create(Notes root, int name){
        chordNotes.clear();
        switch (name){
            case MAJOR:
                chordNotes.add(root);
                chordNotes.add(root.getNote(4));
                chordNotes.add(root.getNote(7));
                this.name = "M";
                break;
            case MINOR:
                chordNotes.add(root);
                chordNotes.add(root.getNote(3));
                chordNotes.add(root.getNote(7));
                this.name = "m";
                break;
            case AUG:
                chordNotes.add(root);
                chordNotes.add(root.getNote(4));
                chordNotes.add(root.getNote(8));
                this.name = "aug";
                break;
            case DIM:
                chordNotes.add(root);
                chordNotes.add(root.getNote(3));
                chordNotes.add(root.getNote(6));
                this.name = "dim";
                break;
            case MIN5AUG:
                chordNotes.add(root);
                chordNotes.add(root.getNote(3));
                chordNotes.add(root.getNote(8));
                this.name = "m(#5)";
                break;
            case MAJ5DIM:
                chordNotes.add(root);
                chordNotes.add(root.getNote(4));
                chordNotes.add(root.getNote(6));
                this.name = "M(b5)";
                break;
            case SUS2:
                chordNotes.add(root);
                chordNotes.add(root.getNote(2));
                chordNotes.add(root.getNote(7));
                this.name = "sus2";
                break;
            case SUS4:
                chordNotes.add(root);
                chordNotes.add(root.getNote(5));
                chordNotes.add(root.getNote(7));
                this.name = "sus4";
                break;            
        }
    }
    
    public ArrayList<Notes> getChord() {return chordNotes;}
    
    public Notes note(int pos){
    	return chordNotes.get(pos);
    }
    
    public String print() {
        String text = chordNotes.get(0).getName() + name + ": ";
        for (Notes n : chordNotes){
        	text += n.getName() + " ";
        }
        return text;
    }
}
