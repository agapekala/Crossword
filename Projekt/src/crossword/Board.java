package crossword;

/*
    *Klasa zawierająca tablicę z komórkami krzyżówki
 */
import exceptions.TooBigCwException;

import java.util.LinkedList;

public class Board {
    private BoardCell[][] board; //tablica z obiektami klasy BoardCell (pojedyncze komórki);
    private int width;
    private int height;


    public Board(){
        height=10;
        width=10;
        board=new BoardCell[height][width];
    }

    /*
        *Konstruktor generujący tablicę o podanych wymiarach;
        * Wyrzuca błąd gdzy wymiary są za duże;
     */
    public Board(int x, int y) throws TooBigCwException {
        if(x>10 || y>15){
            throw new TooBigCwException();
        }
        height=x;
        width=y;
        board=new BoardCell[height][width];
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public BoardCell getCell(int x, int y){
        return board[x][y];
    }

    public void setCell(int x, int y, BoardCell c){
        board[x][y]=c;
    }

    public LinkedList<BoardCell> getStartCells(){
        return null;
    }

    /*
        *Funkcja tworząca wzorzec, który służy do wyszukiwania kolejnych haseł krzyżówki
     */
    public String createPattern(int fromx, int fromy, int tox, int toy){
        String pattern="";
        pattern+="^";
            int ilosc = toy-fromy;
            String first = board[fromx][0].getContent();
            first = first.toLowerCase();
            pattern+=first+"[A-Za-z]+";
            pattern+="$";
            return pattern;
    }
}
