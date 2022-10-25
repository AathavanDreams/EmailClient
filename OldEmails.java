package EmailClient;

import java.io.*;
import java.util.ArrayList;

class OldEmails implements Serializable {
    String email;
    String subject;
    String content;
    String date;
    String timeStamp;

    public OldEmails(String email,String subject, String content, String date, String timeStamp) {
        this.email = email;
        this.subject =subject;
        this.content=content;
        this.date=date;
        this.timeStamp=timeStamp;
    }

    public static void writeToFile(ArrayList<OldEmails> array) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("OldEmails.bin"));

        objectOutputStream.writeObject(array);
    }

    public static ArrayList<OldEmails> readFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("OldEmails.bin"));

        OldEmails oldEmails = null;

        ArrayList<OldEmails> oldEmailsArrayList = new ArrayList<>();
        oldEmailsArrayList = (ArrayList<OldEmails>) objectInputStream.readObject();

        return oldEmailsArrayList;
    }

}