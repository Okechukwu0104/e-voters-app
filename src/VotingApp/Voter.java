package VotingApp;

import java.time.LocalDate;
import java.util.Objects;

public class Voter {

    private static String name;
    private String gender;
    private String DOB;
    private String phoneNumber;
    private String address;
    private  String email;
    private  String password;
    private String votersId;



    private String myPvc;
    private boolean gottenPvc = false;
    public boolean hasVoted = false;


    public Voter(String firstname, String lastname, String gender, String DOB, String phoneNumber, String address, String email, String password, String votersId) {

        setName(firstname, lastname);
        setDOB(DOB);
        setAddress(address);
        setEmail(email);
        setPassword(password);
        setVotersId(votersId);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        getMyPvcNumber();
    }

    public static String getName() {
        return name;
    }

    public String getName2() {
        return name;
    }


    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }


    public String getMyPvcNumber() {

        if (this.myPvc == null || !gottenPvc) {
            INEC inec = new INEC();
            this.myPvc = inec.distributePVC();
            gottenPvc = true;
        }
        return this.myPvc;
    }








    public String getVotersId() {
        return votersId;
    }

    private void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validatePhoneNumber(String phoneNumber) {
        String prefix2 = phoneNumber.substring(0,4);
        if( !prefix2.equals("+234") || phoneNumber.length() != 14)throw new IllegalArgumentException("Phone number should begin with +234");
    }

    private void setGender(String gender) {
        verifyGender(gender);
        this.gender = gender;
    }

    private void verifyGender(String gender) {
        if(!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) throw new IllegalArgumentException("Input M or F");

    }


    public String getDetails(String password) {
        passwordValidation(password);

        String output = """
                Name: %s
                Gender: %s
                D.O.B: %s
                Contact Details: %s
                Address: %s
                Email: %s
                Voters Id: %s
                """;
            return String.format(output, getName(),this.getGender(),this.getDOB(),this.getPhoneNumber() ,this.getAddress(),this.getEmail(),this.getVotersId());
    }




    private void setName(String firstName, String lastName){
        validateName(firstName, lastName);
        firstName = Character.toUpperCase(firstName.charAt(0))+firstName.substring(1);
        lastName = Character.toUpperCase(lastName.charAt(0))+lastName.substring(1);
        name = firstName+ " " +lastName;
    }

    public String getEmail() {
        return email;
    }


    private void setDOB(String DOB) {
        validateDOB(DOB);
        this.DOB = DOB;
    }

    private void validateDOB(String dob) {
        int[] newDate = trimValidateAndReturnDobArray(dob);
        int date = newDate[0];
        int month = newDate[1];
        int year = newDate[2];
        DobChecker(date, month, year);

    }

    private static void DobChecker(int date, int month, int year) {
        if(date > 31 || date < 1) throw new IllegalArgumentException("Invalid Date of birth");
        if(month < 1 || month > 12) throw new IllegalArgumentException("Invalid month of birth");
        LocalDate today = LocalDate.now();
        if(year < today.getYear()-150 || year > today.getYear()) throw  new IllegalArgumentException("Invalid Year of birth");
    }

    private static int[] trimValidateAndReturnDobArray(String dob) {
        dob = dob.replace(" ","");
        if (dob.length() != 10) {
            throw new IllegalArgumentException("Input date using dd/mm/yyyy format");
        }
        String[] dobParts = dob.split("/");
        if (dobParts.length != 3) {
            throw new IllegalArgumentException("Input date using dd/mm/yyyy format");
        }
        int[] newDate = new int[dobParts.length];
        for (int index = 0; index < dobParts.length; index++) {
            newDate[index] = Integer.parseInt(dobParts[index]);
        }
        return newDate;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setVotersId(String votersId) {
        validateId(votersId);
        this.votersId = votersId;
    }

    private void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }


    private void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void validateEmail(String email) {
        email = email.replace(" ","");
        if(!email.contains("@") || !email.contains(".")){throw new IllegalArgumentException("Invalid Email");}

    }

    private void validateName(String firstName, String lastName) {
        if(firstName.isEmpty() || lastName.isEmpty()){throw new IllegalArgumentException("Your Name Cant Be Left Empty");}

    }

    void passwordValidation(String password) {
        if(!Objects.equals(this.password, password)) throw new IllegalArgumentException("Wrong password");

    }


    private void validatePassword(String password) {
        boolean containsLetter = false;
        boolean containsDigits = false;
        for(char content : password.toCharArray()){
            if(Character.isDigit(content)){containsDigits = true;}
            else if(Character.isLetter(content)){containsLetter = true;}
        }

        if(!containsLetter || !containsDigits){
            throw new IllegalArgumentException("password must contain letters , symbols and Digits");
        }
    }


    private void validateId(String votersId) {
        if(votersId == null || votersId.length() > 19) throw new IllegalArgumentException("Issued ID must be at most 19 Digits in length");

    }


    public boolean isGottenPvc() {
        return gottenPvc;
    }

    public void setGottenPvc(boolean gottenPvc) {
        this.gottenPvc = gottenPvc;
    }
}

