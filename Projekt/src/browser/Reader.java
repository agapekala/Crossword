package browser;
/*
* Interfejs klasy odpowiadającej za wczytywanie krzyżówek
 */

import crossword.Crossword;
import exceptions.TooBigCwException;

import java.io.IOException;
import java.util.LinkedList;

/*
    *Funkcja tworzy listę krzyżówek z wybranego katalogu
 */
public interface Reader {
    public LinkedList<Crossword> getAllCws() throws IOException, ClassNotFoundException, TooBigCwException;
}
