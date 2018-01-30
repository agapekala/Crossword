package crossword;

/*
    *Klasa dziedzicząca po Entry;
    *Zawiera dodatkowo pola informujące o współrzędnych hasła i jego kierunku;
 */
import crossword.Direction;

public class CwEntry extends Entry {
    private int x;
    private int y;
    private Direction d;

    public CwEntry(String word, String clue, int x, int y, Direction d) {
        super(word, clue);
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    public Direction getDir(){
        return d;
    }
}
