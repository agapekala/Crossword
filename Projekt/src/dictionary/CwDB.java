package dictionary;

/*
    *Klasa reprezentująca bazę danych;
    * Hasła przechowywane są w liście LinkedList<Entry> dict;
 */
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import crossword.Entry;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class CwDB {
        protected LinkedList<Entry> dict=new LinkedList<>();
/*
    *Tworzy obiekt klasy zawierający listę haseł z pliku filename
 */
        public CwDB(String filename) throws IOException {
            this.createDB(filename);
        }
/*
    *Dodaje kolejne hasło do bazy danych
 */
        public void add(String word, String clue){
            Entry new_word=new Entry(word,clue);
            dict.add(new_word);

        }
        public Entry get(String word) throws Exception{
            for(Entry e: dict){
                if(e.getWord().equals(word)){
                    return e;
                }
            }
            throw new Exception("Nie znaleziono");
        }

        public void remove(String word) throws Exception{
            Entry index=this.get(word);
            dict.remove(dict.indexOf(index));
        }

        public void saveDB(String filename) throws IOException{
            File file=  new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter zapis = new PrintWriter(file.getAbsoluteFile());
            for(Entry e:dict){
                zapis.println(e.getWord());
                zapis.println(e.getClue());
            }
            zapis.close();
        }

        public int getSize(){
            return dict.size();
        }

/*
    *Wczytuje hasła z pliku i zapiuje je do listy
 */
        protected void createDB(String filename) throws IOException {

            try {
                Scanner file=new Scanner(new FileInputStream(filename));
                String word;
                String clue;
                while(file.hasNext()) {
                    word = file.nextLine();
                    clue = file.nextLine();
                    this.add(word, clue);
                }
                file.close();
                }catch(IOException e){
                    throw e;
                }
        }

    }

