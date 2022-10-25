package EmailClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class EmailData {
    static ArrayList<Wishable> BdayWishRecipients;

    public EmailData(){
        BdayWishRecipients = new ArrayList<>();
    }

    public void CreateRecipientObjectsFromFile(String fileName){
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {

                String LineFromFile = scanner.nextLine();

                CreateRecipientObjects(LineFromFile);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void CreateRecipientObjects(String RecipientData){
        String[] DataArray = RecipientData.split("\\s*:\\s*"); //remove commas and spaces after commas.

        String infoString = DataArray[1];

        String[] InfoArray = infoString.split("\\s*,\\s*"); //remove commas and spaces after commas.


        String typeOfRecipient = DataArray[0];
        if (typeOfRecipient.equals("Official")){
            Official official = new Official(InfoArray[0],InfoArray[1],InfoArray[2]);

        } else if (typeOfRecipient.equals("Personal")) {
            Personal personal = new Personal(InfoArray[0], InfoArray[2], InfoArray[3], InfoArray[1]);
            BdayWishRecipients.add(personal);

        } else {
            OfficeFriend officeFriend = new OfficeFriend(InfoArray[0], InfoArray[1], InfoArray[2], InfoArray[3]);
            BdayWishRecipients.add(officeFriend);
        }

    }
    public void sendBirthdayEmails() throws Exception {
        String Todaydate = new SimpleDateFormat("MM/dd").format(Calendar.getInstance().getTime());

        for (Wishable bdayWishRecipient : BdayWishRecipients) {

            if (bdayWishRecipient.getBday().substring(5).equals(Todaydate)) {
                bdayWishRecipient.SendBdayWish();
            }
        }
    }

    public void PrintBdayrecipients(String bdayrecepients){
        for (Wishable bdayWishRecipient : BdayWishRecipients) {
            if (!bdayrecepients.equals(bdayWishRecipient.getBday())) {
                continue;
            }
            System.out.println(bdayWishRecipient.getName());
        }
    }

    public void PrintOldEmails(String bday) throws IOException, ClassNotFoundException {
        try {
            ArrayList<OldEmails> ReadEmails = OldEmails.readFile();

            for (OldEmails oldEmail : ReadEmails) {
                if (oldEmail.date.equals(bday)) {
                    System.out.println("Email address: " + oldEmail.email + " Subject: " + oldEmail.subject + " Content: " + oldEmail.content + " Time stamp:" + oldEmail.timeStamp);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}