package crossword;
/*
    *Klasa reprezentująca metodę tworenia prostej krzyżówki;
    * Dziedziczy po klasie Strategy
 */
import crossword.Crossword;
import exceptions.WordNotFoundException;

import java.util.Iterator;
import java.util.Random;

public class SimpleStrategy extends Strategy {

    /*
        *Funkcja znajdująca kolejne hasła do krzyżówki;
     */
    @Override
    public CwEntry findEntry(Crossword cw) throws Exception{
        Iterator<CwEntry> it = cw.getROEntryIter();
        //Jeśli lista haseł nie ma żadnych elementów to losowane jest hasło główne(pionowe)
        if (!it.hasNext()) {
            Entry n = cw.getCwDB().getRandom(cw.getBoardCopy().getHeight());
            CwEntry haslo = new CwEntry(n.getWord(),n.getClue(),0,0, Direction.VERT);
            return haslo;
        }
        //Gdy lista zawiera już jakieś hasła losowane są kolejne hasła poziome na podstawie utworzonego wzorca
        else {
            CwEntry first = it.next();
            int i=0;
            while (it.hasNext()) {
                i++;
                it.next();
            }
            int licz=0;
            if (i==first.getWord().length()){
                return null;
            }
            Random rnd = new Random();
            int rand_num = rnd.nextInt(cw.getBoardCopy().getWidth()-3)+2;
            String pat = cw.getBoardCopy().createPattern(first.getX()+i, first.getY(), first.getX()+i, rand_num);
            Entry e = cw.getCwDB().getRandom(pat);
            while (e==null) {
                try {
                    rand_num = rnd.nextInt(cw.getBoardCopy().getWidth()-3)+2;
                    pat = cw.getBoardCopy().createPattern(first.getX()+i, first.getY(), first.getX()+i, rand_num);
                    e = cw.getCwDB().getRandom(pat);
                    if (e!=null && cw.contains(e.getWord())) e=null;
                }
                catch (Exception exp) {
                    e=null;
                }
                licz++;
                if (licz>500) throw new WordNotFoundException("Nie znaleziono wyrazu");
            }
            CwEntry haslo = new CwEntry(e.getWord(),e.getClue(),i,0, Direction.HORIZ);
            return haslo;
        }
    }

    /*
        *Aktualizacja stanu tablicy
     */
    @Override
    public void updateBoard(Board b, CwEntry e) {
        int x=e.getX();
        int y=e.getY();
        Direction d=e.getDir();
        if (d.equals(Direction.HORIZ)) {
            for (int i=y;i<e.getWord().length();i++) {
                BoardCell c = new BoardCell();
                c.setContent(e.getWord().substring(i-y, i-y+1));
                b.setCell(x, i, c);
            }
        }
        else {
            for (int i=x;i<e.getWord().length();i++) {
                BoardCell c = new BoardCell();
                c.setContent(e.getWord().substring(i-x, i-x+1));
                b.setCell(i, y, c);
            }
        }
    }
}
