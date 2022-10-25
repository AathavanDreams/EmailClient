package EmailClient;

public class OfficeFriend extends Recipient implements Wishable {
    private String designation;
    private String birthdate;

    public OfficeFriend (String name, String email, String designation, String birthdate){
        super(name,email);
        this.birthdate = birthdate;
        this.designation = designation;
    }

    public void SendBdayWish() throws Exception {
        JavaMailUtil.sendMail(super.getEmail(),"Happy Birthday","Hugs and love on your birthday. -Aathavan", "Bday");
    }

    public String getBday() {
        return birthdate;
    }
}
