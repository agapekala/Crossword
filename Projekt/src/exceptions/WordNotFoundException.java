package exceptions;
/*
    *Błąd wyrzucany, gdy nie uda się znaleźć kolejnego hasla do krzyżówki
 */

public class WordNotFoundException extends Exception {
    public WordNotFoundException(){
        super();
    }

    public WordNotFoundException(String msg){
        super(msg);
    }
}
