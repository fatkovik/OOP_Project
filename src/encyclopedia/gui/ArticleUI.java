package src.encyclopedia.gui;

import javax.swing.JButton;

public class ArticleUI extends JButton {
    private int index;
    
    public ArticleUI (int index) {
        super();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
