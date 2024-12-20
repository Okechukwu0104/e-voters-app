package VotingApp;

public class Candidate extends Voter {

    public Candidate(String firstname, String lastname,String gender, String DOB,String PhoneNumber, String address, String email, String password, String candidateId) {
        super(firstname, lastname,gender, DOB,PhoneNumber, address, email, password, candidateId);
    }



    @Override
    public String getDetails(String password) {
        passwordValidation(password);

        String output = """
                Candidate Name: %s
                Gender: %s
                D.O.B: %s
                Contact Details: %s
                Candidate Id: %s
                """;
        return String.format(output, this.getName(), this.getGender(), this.getDOB(), this.getPhoneNumber(), this.getVotersId());
    }



}
