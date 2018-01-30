package exceptions;
/*
    *Błąd wyrzucany, gdy zadeklarowany wymiary krzyżówki są za duże
 */

public class TooBigCwException extends Exception {

    public TooBigCwException(){
        super();
    }
    public TooBigCwException(String msg){
        super(msg);
    }
}
