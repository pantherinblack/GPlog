import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class PLogPanel extends JTextArea {
    private String text = "";
    private LocalDate date;
    public PLogPanel(BaseScreen baseScreen, String docName, int factor, LocalDate date, String text){
        this(baseScreen, docName, factor, date);
        this.text = text;
        this.date = date;
    }

    public PLogPanel(BaseScreen baseScreen, String docName, int factor, LocalDate date) {
        setBounds(0,(16*factor),500,16);
        if (factor%2==0) setBackground(Color.GRAY);
        else setBackground(Color.LIGHT_GRAY);
        setText(date+" - "+docName.replace(".txt", ""));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (baseScreen.isDelete()) baseScreen.deletePLog(docName);
                else baseScreen.changePLog(docName, text);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
