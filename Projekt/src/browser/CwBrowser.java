package browser;
/*
* Klasa odpowiadająca za wczytywanie i zapisywanie krzyżówek;
* Zawiera klasy Reader i Writer do zapisywania i wczytywania krzyżówek;

 */

public class CwBrowser {
    private String path;
    private WriterImplemented w;
    private ReaderImplement r;

    public CwBrowser(){
        path="";
        w=new WriterImplemented(path);
        r=new ReaderImplement();
        r.setPath(path);
    }


    public CwBrowser(String path) {
        this.path = path;
        w=new WriterImplemented(path);
        r=new ReaderImplement();
        r.setPath(path);
    }


    public WriterImplemented getW() {
        return w;
    }

    public ReaderImplement getR() {
        return r;
    }

    /*
        * Funkcja ustawiająca podaną przez użytkownika ścieżkę do katalogu z krzyżówkami
     */
    public void setW(String path) {
        this.w = new WriterImplemented(path);
    }

    public void setPath(String path) {

        this.path = path;
    }

    public void setR(String path) {
        this.r.setPath(path);
    }
}
