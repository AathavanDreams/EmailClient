package EmailClient;

public class Personal extends Recipient implements Wishable {
    private String birthdate;
    private String nickname;


    public Personal (String name, String email, String birthdate, String nickname){
        super(name,email);
        this.birthdate = birthdate;
        this.nickname = nickname;
    }


    public void SendBdayWish() throws Exception {
        JavaMailUtil.sendMail(super.getEmail(),"Happy Birthday","Wish you a Happy Birthday -Aathavan", "Bday");
    }

    public String getBday() {
        return birthdate;
    }

}
