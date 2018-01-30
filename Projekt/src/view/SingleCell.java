package view;

/*
    *Klasa reprezentująca jedną wypełnioną komórkę
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SingleCell{
    private int x;
    private int y;
    private Rectangle r=new Rectangle();
    private Text t=new Text();
    private String letter;
    private StackPane stack=new StackPane();
    public SingleCell(){

    }
/*
    *Ustawia wymiary, kolor, obramowanie i zawartość pojedynczej komórki
 */
    public SingleCell(int x, int y,Character letter){
        this.x=x;
        this.y=y;
        r.setWidth(30);
        r.setHeight(30);
        r.setX(x);
        r.setY(y);

        if(x==60){
            r.setFill(Color.WHEAT);
        }else{
            r.setFill(Color.rgb(255, 255, 204));
        }
        r.setStroke(Color.BLACK);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        t=new Text(Character.toString(letter));
        stack.getChildren().addAll(r,t);
    }

    public Rectangle getR() {
        return r;
    }

    public StackPane getStack() {
        return stack;
    }

}
