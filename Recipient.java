package EmailClient;


public abstract class Recipient {
    private String name;
    private String email;
    private int id;
    private static int number_of_total_recepients = 0;

    public Recipient(String name, String email){
        this.name = name;
        this.email = email;
        number_of_total_recepients = number_of_total_recepients+1;
        this.id = number_of_total_recepients;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public static int getNumber_of_total_recepients() {
        return number_of_total_recepients;
    }
}