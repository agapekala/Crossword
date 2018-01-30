package view;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
    *Klasa reprezentująca jedną pustą komórkę
 */
public class SingleCellToFill {

    private int x;
    private int y;
    //private TextArea t =new TextArea();
    private Rectangle r=new Rectangle();
    private StackPane stack=new StackPane();

    public Rectangle getR() {
        return r;
    }

    public SingleCellToFill(int x, int y) {
        this.x = x;
        this.y = y;
        r.setWidth(30);
        r.setHeight(30);
        //t.setPrefWidth(0.5);
        //t.setPrefHeight(0.5);
        //t.setBackground(Background.EMPTY);

        r.setX(x);
        r.setY(y);
        if(x==60){
            r.setFill(Color.WHEAT);
        }else{
            r.setFill(Color.rgb(255, 255, 204));
        }
        r.setStroke(Color.BLACK);
        //stack.setLayoutX(x);
        //stack.setLayoutY(y);
        //stack.getChildren().addAll(r,t);
    }

    public SingleCellToFill() {
    }

    public StackPane getStack() {
        return stack;

    }

    public void setStack(StackPane stack) {
        this.stack = stack;
    }
}
