import java.io.*;
import java.time.LocalDate;

public class PLog {
    private LocalDate date;
    private BufferedReader reader1;
    private BufferedReader reader2;
    private FileWriter writer;
    private final String docName;
    private String text;

    public String getDocName() {
        return docName;
    }

    public PLog(String docName){
        this.docName = docName;
        if (new File((new File("").getAbsolutePath()+"\\Plog\\"+docName)).exists()){
            try {
                readFile();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be read");
            } catch (IOException ignored) {}

        } else {
            date = LocalDate.now();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void readFile() throws IOException {
        reader1 = new BufferedReader(new FileReader((new File("").getAbsolutePath()+"\\Plog\\"+docName)));
        reader2 = new BufferedReader(new FileReader((new File("").getAbsolutePath()+"\\Plog\\"+docName)));
        int lines = (int) reader2.lines().count();
        String strDate = reader1.readLine();
        String[] strArDate = strDate.split("\\.");
        date = LocalDate.of(Integer.parseInt(strArDate[2]),Integer.parseInt(strArDate[1]),Integer.parseInt(strArDate[0]));
        text="";
        for (int i=1; i<lines; i++){
            text+= reader1.readLine();
            if (i<lines-1){
                text+="\n";
            }
        }
        reader1.close();
        reader2.close();
    }

    public LocalDate getDate() {
        return date;
    }

    public void storeFile(){
        if (text!=null && text!="") {
            try {
                writer = new FileWriter((new File("").getAbsolutePath()+"\\Plog\\" + docName), false);
                writer.write(date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear() + "\n" + text);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteFile(){
        new File(new File("").getAbsolutePath()+"\\Plog\\" + docName).delete();
    }
}
