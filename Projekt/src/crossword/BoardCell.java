package crossword;
/*
    *Klasa reprezentująca pojedynczą komórkę krzyżówki;
 */

import crossword.Board;

public class BoardCell {
    private String content;
    private int x;//współrzędne komórki
    private int y;
    //private boolean Hstart, HEnd,HIn,VStart,VEnd,VIn;
    public BoardCell(){
        content="";
    } //konstruktor domyślny, tworzy komórkę z pustym Stringiem

    public void setContent(String content){
        this.content=content;
    }

    public String getContent(){
        return this.content;
    }



}

