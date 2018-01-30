package browser;
/*
    *Klasa implementująca funkcje z interfejsu Reader
 */
import browser.Reader;
import crossword.*;
import exceptions.TooBigCwException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReaderImplement implements Reader {

    private String path;

    public void setPath(String path){
        this.path=path;
    }

    /*
    *Funkcja tworzy listę wszystkich krzyżówek czytając z każdego pliku w katalogu
    *
 */
    @Override
    public LinkedList<Crossword> getAllCws() throws IOException, ClassNotFoundException, TooBigCwException {
        LinkedList<Crossword> crosswords = new LinkedList<>();
        File folder=new File(path);
        String word,clue;
        Direction dir;
        SimpleStrategy s=new SimpleStrategy();
        int x,y;
        long id=0;
        Crossword c=new Crossword();

        for (final File fileEntry : folder.listFiles()) {

            String nazwa=fileEntry.getName();
            nazwa=nazwa.substring(0,nazwa.length()-4);
            id=Long.parseLong(nazwa);
            Board b=new Board(10,15);
            c=new Crossword(id,b);
            Scanner in= new Scanner(fileEntry);
            while(in.hasNext()){
                word=in.nextLine(); //wczytanie hasła z krzyżówki
                clue=in.nextLine(); //wczytanie definicji
                x=Integer.parseInt(in.nextLine());
                y=Integer.parseInt(in.nextLine()); //wczytanie współrzędnych x i y
                String enum_value=in.nextLine(); //wczytanie kierunku hasła
                enum_value.replace("\n","");
                dir=Direction.valueOf(enum_value);

                CwEntry entry=new CwEntry(word,clue,x,y,dir);
                c.addCwEntry(entry,s); //dodanie do krzyżówki kolejnego wzytanego hasła
                in.nextLine();
                in.nextLine();
            }
            crosswords.add(c);


        }
        return crosswords;
    }
}
