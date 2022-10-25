package EmailClient;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JavaMail {
    public static void main(String[] args) throws Exception {

        EmailData emailData1 = new EmailData();

        emailData1.CreateRecipientObjectsFromFile("clientList.txt");
        JavaMailUtil.GetOldEmails();
        emailData1.sendBirthdayEmails();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    Enter option type:\s
                    1 - Adding a new recipient
                    2 - Sending an email
                    3 - Printing out all the recipients who have birthdays
                    4 - Printing out details of all the emails sent
                    5 - Printing out the number of recipient objects in the application
                    6 - Exit Application""");

            int option = scanner.nextInt();

            if (option == 6) {
                System.out.println("Closing system\n***");
                break;
            }

            switch (option) {
                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file
                // Hint: use methods for reading and writing files

                case 1 -> {
                    // input format - Official: nimal,nimal@gmail.com,ceo
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("""
                            Enter recepient data in given formats:\s
                            Official: nimal,nimal@gmail.com,ceo
                            Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
                            Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10
                            """);
                    String UserInput = scanner1.nextLine();
                    try {
                        FileWriter filewriter = new FileWriter("clientList.txt", true);
                        BufferedWriter writer = new BufferedWriter(filewriter);
                        writer.write("\n" + UserInput);
                        writer.close();
                        filewriter.close();
                        System.out.println("Successfully added new recepient");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    EmailData.CreateRecipientObjects(UserInput);
                    System.out.println("****-----------------------------------------------***");
                }
                case 2 -> {
                    // input format - email, subject, content
                    // code to send an email
                    System.out.println("Enter input in the form of email, subject, content");
                    Scanner scanner2 = new Scanner(System.in);
                    String emailStr = scanner2.nextLine();
                    String[] emailArray = emailStr.split("\\s*,\\s*"); //remove commas and spaces after commas.
                    String newEmail = emailArray[0];
                    String newEmailSubject = emailArray[1];
                    String newEmailContent = emailArray[2];
                    JavaMailUtil.sendMail(newEmail, newEmailSubject, newEmailContent, "Email");
                    System.out.println("****-----------------------------------------------***");
                }
                case 3 -> {
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.println("Enter the birth date you want to find recipients in format yyyy/MM/dd (ex: 2018/09/17)");
                    Scanner scanner3 = new Scanner(System.in);
                    String bdayofrecepients = scanner3.nextLine();
                    emailData1.PrintBdayrecipients(bdayofrecepients);
                    System.out.println("****-----------------------------------------------***");
                }
                case 4 -> {
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.println("Enter the date you want to get all the emails in the format yyyy/MM/dd (ex: 2018/09/17)");
                    Scanner scanner4 = new Scanner(System.in);
                    String bdayofOldMails = scanner4.nextLine();
                    emailData1.PrintOldEmails(bdayofOldMails);
                    System.out.println("****-----------------------------------------------***");
                }
                case 5 -> {
                    // code to print the number of recipient objects in the application
                    System.out.println("Number of recipient objects in the application is: " + Recipient.getNumber_of_total_recepients());
                    System.out.println("****-----------------------------------------------***");
                }
            }

            Scanner scanner5 = new Scanner(System.in);
            System.out.println("""
                    What do you want to do next? \s
                    1 - Do another activity
                    2 - Exit Application.""");

            int NextOption = scanner5.nextInt();
            if (NextOption == 2) {
                System.out.println("Closing system\n***");
                break;
            }
        }
    }

}


