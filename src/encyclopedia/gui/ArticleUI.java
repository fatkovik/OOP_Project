package src.encyclopedia.gui;

import javax.swing.*;
import java.awt.*;

public class ArticleUI extends JButton {
    private int index;
    

    public ArticleUI (int index) {
        super();
        this.index = index;
        this.setBackground(EncyclopediaGUI.background);

        ImageIcon icon = new ImageIcon("src/encyclopedia/gui/articleicon.png");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        this.setHorizontalAlignment(LEFT);
        this.setIcon(icon);
    

        //this.setBorder(null);
    }


    
    /** 
     * @return int
     */
    public int getIndex() {
        return index;
    }
}
