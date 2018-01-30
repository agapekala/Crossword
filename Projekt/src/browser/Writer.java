package browser;
/*
    *Interfejs klasy odpowiadającej za zapisywanie krzyżówek
 */
import crossword.Crossword;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Writer {
    public void write(Crossword cw) throws FileNotFoundException, IOException;
    public long getUniqueID();
}
