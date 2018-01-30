package dictionary;

/*
    *Klasa dziedzicząca po klasie CwDB;
    * Dodatkowo posiada funkcje losujące hasła z bazy
 */
import crossword.Entry;
import dictionary.CwDB;

import java.io.IOException;
import java.text.Collator;
import java.util.*;

public class InteliCwDB extends CwDB {
    public InteliCwDB(String filename) throws IOException {
        super(filename);
    }
/*
    *Znajduje wszystkie hasłe pasujące do podanego wzorca
 */
    public LinkedList<Entry> findAll(String pattern){
        LinkedList<Entry> list = new LinkedList<Entry>();
        for (Entry e : dict) {
            if (e.getWord().matches(pattern)) list.add(e);
        }
        return list;
    }

    public Entry getRandom(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(dict.size());
        return dict.get(randomInt);
    }
/*
    *Zwraca losowy wyraz o zadanej długości z bazy danych
 */
    public Entry getRandom(int length) throws Exception{
        LinkedList<Entry> help_list=new LinkedList<>();
        for(Entry e:dict){
            if(e.getWord().length()==length){
                help_list.add(e);
            }
        }
        if(help_list.isEmpty()){
            throw new Exception("Nie znaleziono słowa");
        }
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(help_list.size());
        return help_list.get(randomInt);
    }

    /*
        *Zwraca z bazy danych losowe haslo pasujące do danego wzorca
     */
    public Entry getRandom(String pattern) throws Exception{
        LinkedList<Entry> lista = findAll(pattern);
        if (lista.isEmpty()) throw new Exception("Nie znaleziono slowa pasujacego do regexa");
        Random rnd = new Random();
        return lista.get(rnd.nextInt(lista.size()));
    }
/*
    *Dodaje słowo do słownika, zachowując kolejność alfabetyczną
 */
    @Override
    public void add(String word, String clue) {
        Entry new_word = new Entry(word, clue);
        dict.add(new_word);
        Collections.sort(dict, new Comparator<Entry>() {
            private Collator collator=Collator.getInstance(new Locale("pl","PL"));
            @Override
            public int compare(Entry o1, Entry o2) {
                return collator.compare(o1.getWord(),o2.getWord());
            }
        });
    }
}
