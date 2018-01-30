package crossword;
/*
    *Klasa reprezentująca krzyżówkę;
    * Posiada funkcje generujące krzyżówkę
 */
import dictionary.InteliCwDB;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Crossword {
    private LinkedList<CwEntry> entries=new LinkedList<>(); //lista haseł
    private Board b;
    private InteliCwDB cwdb;
    private final long ID;

    public Crossword(){
        ID=-1;
        b=new Board();
    }

    public Crossword(int id){
        ID=id;
    }

    public Crossword(long id, Board b){
        ID=id;
        this.b=b;
    }
/*
    *Tworzy iterator po hasłach krzyżówki, tylko do odczytu;
 */
    public Iterator<CwEntry> getROEntryIter(){
        return Collections.unmodifiableList(entries).iterator();
    }

    /*
        *Zwraca kopię tablicy
     */
    public Board getBoardCopy() throws  Exception{
        Board new_board=new Board(b.getHeight(),b.getWidth());
        for(int i=0; i<b.getHeight(); i++){
            for(int j=0; j<b.getWidth(); j++){
                new_board.setCell(i,j,b.getCell(i,j));
            }
        }
        return new_board;
    }

    public InteliCwDB getCwDB(){
        return cwdb;
    } //wczytuje bazę haseł do tworzenia krzyżówki

    public void setCwDB(InteliCwDB cwdb){
        this.cwdb=cwdb;
    }

    /*
        *Sprawdza czy słowo zostało już użyte w krzyżówce
     */
    public boolean contains(String word){
        boolean check=false;
        for(Entry e:entries){
            if(e.getWord().equals(word)){
                check=true;
            }
        }
        return check;
    }

    /*
        *Dodaje kolejne słowo do listy haseł;
     */
    public final void addCwEntry(CwEntry cwe, Strategy s){
        entries.add(cwe);
        s.updateBoard(b,cwe);
    }

    /*
        *Genereuje krzyżówkę na postawie podanej strategii
     */
    public final void generate(Strategy s) throws Exception{
        CwEntry e = null;
        int i=0;
        while((e = s.findEntry(this)) != null){
            addCwEntry(e,s);
            System.out.println(e.getWord());
        }
    }
    public long getID(){
        return ID;
    }
}
