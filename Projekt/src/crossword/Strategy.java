package crossword;
/*
    *Klasa abstrakcyjna, służąca jako "baza" do strategii prostej i skomplikowanej;
 */
import crossword.Crossword;

public abstract class Strategy {
    public abstract CwEntry findEntry(Crossword cw) throws Exception;
    public abstract void updateBoard(Board b, CwEntry e);
}
