import java.io.File;
import java.util.Vector;

public class Controller {
    Vector<PLog> pLogs = new Vector<>();
    public void getAllEntry(){
        String[] files = new File(new File("").getAbsolutePath()+"\\Plog").list();

        for (String file : files){
            pLogs.add(new PLog(file));
        }
        pLogs.sort(new PLogDateComparator());
    }

    public void clearAllEntry(){
        pLogs.clear();
    }

    public void addEntry(PLog pLog){
        pLogs.add(pLog);
    }

    public int searchIndexByDocName(String docName){
        for (int i=0; i<pLogs.size(); i++){
            if (pLogs.get(i).getDocName().equalsIgnoreCase(docName)){
                return i;
            }
        }
        return -1;
    }


    public String getText(int index){
        return pLogs.get(index).getText();
    }

    public void setText(int index, String text){
        pLogs.get(index).setText(text);
    }

    public void deletePLog(String docName){
        if (searchIndexByDocName(docName)!=-1){
            pLogs.remove(searchIndexByDocName(docName)).deleteFile();
        }
    }

    public void saveAllEntry(){
        for (PLog pLog : pLogs){
            pLog.storeFile();
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        BaseScreen baseScreen = new BaseScreen(controller);
        baseScreen.reloadPLogs(controller.pLogs);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            controller.saveAllEntry();
        }));

    }

    public void reload(BaseScreen baseScreen){
        saveAllEntry();
        clearAllEntry();
        getAllEntry();
        baseScreen.reloadPLogs(pLogs);
    }

}
