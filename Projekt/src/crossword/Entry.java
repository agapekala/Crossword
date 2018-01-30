package crossword;
/*
    *Klasa reprezentująca hasło;
    * Zawiera dane hasło i definicję;
 */
public class Entry {
    private String word;
    private String clue;

    public Entry(String word, String clue) {
        this.word = word;
        this.clue = clue;
    }

    public String getClue() {
        return clue;
    }

    public String getWord() {
        return word;
    }
}
