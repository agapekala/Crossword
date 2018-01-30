package crossword;
/*
    *Klasa do zaawansowanej metody (niewykorzystane);
 */
import crossword.Crossword;

import java.util.Iterator;
import crossword.Entry;

public class AdvancedStrategy extends Strategy {
    @Override
    public CwEntry findEntry(Crossword cw) throws Exception {
        Iterator<CwEntry> it = cw.getROEntryIter();
        if (!it.hasNext()) {
            Entry n = cw.getCwDB().getRandom(cw.getBoardCopy().getHeight());
            CwEntry haslo = new CwEntry(n.getWord(),n.getClue(),0,0, Direction.VERT);
            return haslo;
        }
        else {

        }
        return null;
    }

    @Override
    public void updateBoard(Board b, CwEntry e) {

    }
}
