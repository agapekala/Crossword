package browser;
/*
    *Implementacja interfejsu Writer
 */
import crossword.Crossword;
import crossword.CwEntry;

import java.io.*;
import java.util.Iterator;

public class WriterImplemented implements Writer {
    private String filename;

    public WriterImplemented(String filename) {
        this.filename=filename;
    }
    /*
        *Funkcja tworząca nowy plik i zapisująca w niej hasła krzyżówki;
        * Zapisywane są tylko hasła poziome;
     */
    @Override
    public void write(Crossword cw) throws FileNotFoundException, IOException {
        File yourFile = new File(this.filename+getUniqueID()+".txt");
        yourFile.createNewFile();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(yourFile,true)));
        Iterator<CwEntry> it=cw.getROEntryIter();
        int i=0;
        while(it.hasNext()){
            CwEntry e=it.next();
            if(i!=0) {
                writer.println(e.getWord());
                writer.println(e.getClue());
                writer.println(e.getX());
                writer.println(e.getY());
                writer.println(e.getDir());
                writer.println('\n');
            }
            i++;
        }
        writer.close();

    }

    /*
        *Nadawanie krzyżówce id;
     */
    @Override
    public long getUniqueID() {
        return System.currentTimeMillis();
    }
}
