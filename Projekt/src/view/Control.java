package view;

/*
    *Interfejs graficzny programu
 */
import browser.CwBrowser;
import com.sun.javafx.font.freetype.HBGlyphLayout;
import crossword.*;
import dictionary.InteliCwDB;
import exceptions.TooBigCwException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;


public class Control extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Lista wszystkich utworzonych krzyżówek
        LinkedList<Crossword> crosswords = new LinkedList<>();

        CwBrowser cwBrowser=new CwBrowser();
        CurrentCw currentcw=new CurrentCw();
        currentcw.setCurrent(-1);

        Pane pane = new Pane();
        BorderPane p2 = new BorderPane();
        primaryStage.setTitle("Krzyżówki :)");
        Scene scene = new Scene(p2, 800, 700, Color.WHITE);

        VBox h = new VBox();
        HBox v = new HBox(5);
        v.setPadding(new Insets(10, 60, 10, 10));
        h.setPadding(new Insets(10, 60, 10, 10));
        v.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-color: black;");
        h.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-color: black;");


//        Rectangle r = new Rectangle();
//        r.setX(10);
//        r.setY(10);
//        r.setHeight(30);
//        r.setWidth(30);


        Strategy s = new SimpleStrategy();

        //Utworzenie bazy danych z pliku zawierającego hasła
        InteliCwDB dane = new InteliCwDB("/home/agnieszka/IdeaProjects/Projekt/src/cwdb.txt");
        Text t = new Text();

        //Lista wyświetlająca wszystkie wygenerowane i wczytane krzyżówki
        //Pozwala na wyświetlenie zaznaczonej krzyżówki
        final ListView<String> cws = new ListView<>();
        cws.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int c=cws.getSelectionModel().getSelectedIndex();
                h.getChildren().clear();
                pane.getChildren().clear();
                printCwEmpty(crosswords.get(c),pane,h,p2);
                currentcw.setCurrent(c);
            }
        });

        //Utworzenie wszystich przyscisków
        Button b1=new Button("Generuj Krzyżówkę");
        Button b2=new Button("Rozwiąż");
        Button b3=new Button("Zapisz krzyżówkę");
        Button b4=new Button("Wczytaj krzyżówki");
        Button b5=new Button("Drukuj do pdf");

        //Pola tekstowe do ustawienia wymiarów krzyżówki
        TextField height=new TextField();
        TextField width=new TextField();

        //Przycisk generujący krzyżówki
        b1.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    //Utworzenie tablicy na podstawie wprowadzonych wymiarów;
                    Board b=new Board(Integer.parseInt(height.getText()),Integer.parseInt(width.getText()));
                    Crossword c=new Crossword(System.currentTimeMillis(),b);
                    c.setCwDB(dane);
                    c.generate(s);
                    crosswords.add(c);
                    //Dodanie nowej krzyżówki do listy dostępnych krzyżówek;
                    cws.getItems().add("Krzyżówka"+crosswords.size());
                    h.getChildren().clear();
                    pane.getChildren().clear();
                    currentcw.setCurrent(crosswords.size()-1);
                    //Wyrysowanie krzyżówki
                    printCwEmpty(c,pane,h,p2);
                }catch (TooBigCwException e) {
                    infoWindow("Za duże wymiary krzyżówki!");
                }catch (Exception e){
                    infoWindow("Nie udało się wygenerować krzyżówki");
                }
            }
        });

        //Przycisk wyświetlający rozwiązania
        b2.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    pane.getChildren().clear();
                    printCwSolve(crosswords.get(currentcw.getCurrent()), pane, h,p2);
                }catch (Exception e){

                }
            }
        });


        final DirectoryChooser dirChooser = new DirectoryChooser();

        //Przycisk zapisujący krzyżówkę do wybranego katalogu
        b3.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try{
                    String path="";
                    File selectedDirectory =
                            dirChooser.showDialog(primaryStage);
                    if(selectedDirectory != null) {
                        path=selectedDirectory.getPath()+"/";
                    }
                    cwBrowser.setW(path);
                    cwBrowser.getW().write(crosswords.get(currentcw.getCurrent()));
                    infoWindow("Zapisano");
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        //Przycisk wczytujący krzyżówki z podanego katalogu;
        //Wyrzuca błąd w razie nieprawidłowego pliku;
        b4.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    String path="";
                    File selectedDirectory =
                            dirChooser.showDialog(primaryStage);
                    if(selectedDirectory != null) {
                        path=selectedDirectory.getPath();
                    }
                    cwBrowser.setR(path);
                    //browser.ReaderImplement r = new browser.ReaderImplement();
                    int add=cwBrowser.getR().getAllCws().size();
                    crosswords.addAll(cwBrowser.getR().getAllCws());
                    for(int i=crosswords.size()-add; i<crosswords.size();i++){
                        cws.getItems().add("Krzyżówka"+crosswords.get(i).getID());
                    }
                }catch (Exception e){
                   infoWindow("Zły format");
                }
            }
        });

        //Przycisk drukujący do pdf
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrinterJob job = PrinterJob.createPrinterJob();
                if(job != null){
                    job.showPrintDialog(primaryStage); // Window must be your main Stage
                    job.printPage(p2.getCenter());
                    job.endJob();
                }
            }
        });

        /*
            *Ustawienie wszystkich elementów w scenie
         */
        Text height_text=new Text("Wysokość: ");
        Text wid_text=new Text("Szerokość: ");
        height.setPrefWidth(40);
        width.setPrefWidth(40);
        HBox opis1=new HBox(5);
        HBox opis2 =new HBox(5);
        opis1.getChildren().addAll(height_text,height);
        opis2.getChildren().addAll(wid_text,width);
        VBox inputs=new VBox(5);
        VBox inputs2=new VBox(5);
        VBox inputs3=new VBox(5);
        VBox inputs4=new VBox(5);
        //inputs.setPrefWidth(100);
        inputs.getChildren().addAll(opis1,opis2);
        inputs2.getChildren().addAll(b1,b2);
        inputs3.getChildren().addAll(b3,b4);
        inputs4.getChildren().addAll(b5);

        v.getChildren().addAll(inputs,inputs2,inputs3,inputs4);

        p2.setBottom(h);
        p2.setRight(cws);
        p2.setTop(v);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String []argv) {
        launch(argv);

    }

    /*
        *Funkcja wyświetlająca nowe okno z zadaną informacją;
     */
    public void infoWindow(String msg){
        Stage stage=new Stage();
        BorderPane root = new BorderPane();
        Scene scene=new Scene(root,400,100,Color.WHITE);
        Text t=new Text(msg);
        root.setCenter(t);
        stage.setScene(scene);
        stage.show();
    }

    /*
        *Funkcja rozdzielająca za długie definicje na dwie linijki
     */
    public String changeClue(String clue,int num){
        String result;
        result=clue.substring(0,num)+"\n"+clue.substring(num,clue.length());
        return result;
    }

    /*
        *Funkcja rysująca pustą krzyżówkę;
        * Wykorzystuje klasę SingleCell
     */
    public void printCwEmpty(Crossword c, Pane pane, VBox h, BorderPane p){
        Iterator<CwEntry> it=c.getROEntryIter();
        SingleCellToFill cell=new SingleCellToFill();
        Text t=new Text();
        int i=0;
        while(it.hasNext()){
            CwEntry e=it.next();
            String word=e.getWord();
            if(i!=0){
                Text cyfra=new Text(Integer.toString(i)+".");
                cyfra.setY(e.getX()+(i+0.75)*30);
                cyfra.setX(30);
                pane.getChildren().add(cyfra);
                for (int k = 0; k < word.length(); k++) {
                    cell=new SingleCellToFill(e.getY()+(k+2)*30,e.getX() + i * 30);
                    pane.getChildren().add(cell.getR());
                }
                String tekst;
                if(e.getClue().length()>100){
                    tekst=changeClue(e.getClue(),100);
                }else{tekst=e.getClue();}
                t=new Text(i+". "+tekst);
                t.setX(0);
                t.setY(i);
                h.getChildren().addAll(t);
            }
            i++;
        }
        p.setCenter(null);
        p.setCenter(pane);
    }

    /*
        *Funkcja rysująca krzyżówkę wypełnioną;
        * Wykorzystuje SingleCellToFill;
     */
    public void printCwSolve(Crossword c, Pane pane, VBox h, BorderPane p)throws Exception{
        Iterator<CwEntry> it=c.getROEntryIter();
        SingleCell cell=new SingleCell();
        int i=0;
        while(it.hasNext()){
            CwEntry e=it.next();
            String word=e.getWord();
            if(i!=0) {
                Text cyfra=new Text(Integer.toString(i)+".");
                cyfra.setY(e.getX()+(i+0.75)*30);
                cyfra.setX(30);
                pane.getChildren().add(cyfra);
                for (int k = 0; k < word.length(); k++) {
                    cell = new SingleCell(e.getY()+(k+2)*30,e.getX() + i * 30 ,word.charAt(k));
                    pane.getChildren().add(cell.getStack());
                }
            }
            i++;
        }
        p.setCenter(pane);
    }

}
