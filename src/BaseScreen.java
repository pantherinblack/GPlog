import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class BaseScreen extends JFrame {
    private final JButton reloadButton = new JButton("Reload");
    private final JButton addButton = new JButton("Add PLog");
    private final JButton deleteButton = new JButton("Delete PLog");
    private final JPanel pLogPanel = new JPanel();
    private final Controller controller;
    private final BaseScreen thisScreen = this;
    private boolean delete=false;
    private final JPanel panel = new JPanel();

    public Controller getController() {
        return controller;
    }

    public BaseScreen(Controller controller) throws HeadlessException {
        setTitle("PLog");
        reloadButton.setBackground(Color.GRAY);
        addButton.setBackground(Color.GRAY);
        deleteButton.setBackground(Color.GRAY);
        pLogPanel.setBackground(Color.DARK_GRAY);
        this.controller = controller;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        setExtendedState(MAXIMIZED_BOTH);
        pLogPanel.setLayout(null);
        pLogPanel.setBounds(20,60,550,800);


        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(getBounds());
        panel.setLayout(null);
        add(panel);

        reloadButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.reload(thisScreen);
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
        reloadButton.setBounds(20,20,120,20);
        panel.add(reloadButton);

        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddPLog(thisScreen);
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
        addButton.setBounds(160, 20, 120, 20);
        panel.add(addButton);

        deleteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                delete= !delete;
                deleteReload();
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
        deleteButton.setBounds(300, 20, 120 ,20);
        panel.add(deleteButton);
        controller.reload(thisScreen);
        panel.add(pLogPanel);
    }

    public void deleteReload(){
        if (delete) deleteButton.setBackground(Color.RED);
        else deleteButton.setBackground(Color.GRAY);
    }

    public void deletePLog(String docName){
        controller.deletePLog(docName);
        controller.reload(thisScreen);
    }

    public boolean isDelete() {
        return delete;
    }

    public void addPLog(String docName){
        controller.addEntry(new PLog((docName+".txt")));

        new EditScreen(docName+".txt", thisScreen);

    }

    public void saveText(String docName, String text){
        if (text!=null && text!="") {
            int index = controller.searchIndexByDocName(docName);
            controller.setText(index, text);
        }
        controller.reload(thisScreen);
    }

    public void reloadPLogs(Vector<PLog> plogs){
        pLogPanel.removeAll();
        for (int i=0; i<plogs.size(); i++){
            pLogPanel.add(new PLogPanel(this, plogs.get(i).getDocName(), i, plogs.get(i).getDate(), plogs.get(i).getText()));
        }
        pLogPanel.invalidate();
        invalidate();
        pLogPanel.setVisible(false);
        pLogPanel.setVisible(true);
    }

    public void changePLog(String docname, String text){
        new EditScreen(docname, thisScreen, text);
    }
}
