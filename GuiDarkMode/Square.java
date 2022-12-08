package GuiDarkMode;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Square extends StackPane {
    String string;
    int colPos;
    int rowPos;
    boolean isClicked;
    Text text;
    Rectangle rect;
    int size;


    public Square(String string, int rowPos, int colPos, int size){
        this.string = string;
        this.text = new Text(this.string);
        this.rowPos = rowPos;
        this.colPos = colPos;
        this.isClicked = false;
        this.rect = new Rectangle(size, size);
        this.size = size;
        this.rect.setFill(null);
        this.rect.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(this.rect, this.text);
    }

    public void visualizeSelected(boolean bool){

    }

    public void makeSelected(){

    }



}
