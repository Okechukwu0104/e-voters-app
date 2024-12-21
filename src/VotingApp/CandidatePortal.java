package VotingApp;


import java.util.ArrayList;

public class CandidatePortal extends VoterPortal {

    private static ArrayList<String> electionRequirementsApprovalList = new ArrayList<>();
    private boolean eligibilityStatus = false;

    private static ArrayList<Candidate> CandidatesList = new ArrayList<>();
    private static ArrayList<String> candidatesHashedPasswords = new ArrayList<>();

    private static ArrayList<String> partyList = new ArrayList<>();

    private int counter = 0;

    public static Candidate verifyRegisteredCandidate(String pvcNumber, String password) {
        for( Candidate user : CandidatesList){

            if(user.getMyPvcNumber().equalsIgnoreCase(pvcNumber) ){
                user.passwordValidation(password);
                return user;
            }
        }
        throw new IllegalArgumentException("Citizen not found");
    }

    @Override
    public Candidate register(String firstName, String lastname, String gender, String DOB, String phoneNumber, String address, String email, String password, String votersId) {
        Candidate candidate = new Candidate(firstName, lastname,gender,DOB,phoneNumber,address, email, password, votersId);
        hashAndStorePassword(password);
        CandidatesList.add(candidate);

        counter++;
        return candidate;
    }
    private static boolean validatePassword(String password) {
        for (String hashedPass : candidatesHashedPasswords) {
            if(hashedPass.equals(hashAndReturn(password)))return true;
        };
        throw new IllegalArgumentException("Wrong Password");
    }

    private static String hashAndReturn(String password) {
        String hashedPassword = "";
        for (int count = 0; count < password.length(); count++) {
            char digit = password.charAt(count);
            if(Character.isLetterOrDigit(digit)){ digit = (char) ('A' + (digit % 26));}
            hashedPassword += digit;
        }
        return hashedPassword;

    }




    private void hashAndAdd(String password) {
        String hashedPassword = "";
        for (int count = 0; count < password.length(); count++) {
            char digit = password.charAt(count);
            if(Character.isLetterOrDigit(digit)){ digit = (char) ('A' + (digit % 26));}
            hashedPassword += digit;
        }
        candidatesHashedPasswords.add(hashedPassword);
    }


    private void hashAndStorePassword(String password) {
        hashAndAdd(password);
    }


    public void electionRequirements(String partyName, String slogan, String position, String educationHistory, String professionHistory, String politicalExperience, String Manifesto, String responseToDOE) {
        partyName = partyName.toUpperCase();
        educationalAndProfessionalBackground(educationHistory, professionHistory);
        politicsInfo(partyName,slogan,position,politicalExperience,Manifesto);
        declarationOfEligibility(responseToDOE);
        partyList.add(partyName);

        if(eligibilityStatus){
            String report = """
                    Candidate Name: %s
                    Candidate Party: %s
                    Aimed Position: %s
                    Political Experience: %s
                    Manifesto: %s 
                    """;
            String info = String.format(report,Candidate.getName(),partyName,position,politicalExperience,Manifesto);
            electionRequirementsApprovalList.add(info);

        }


    }


    public String viewQualifiedCandidates(){
        StringBuilder candidatesString = new StringBuilder();
        for (String candidate : electionRequirementsApprovalList) {
            candidatesString.append(candidate).append("\n");
        } return candidatesString.toString();
    }

    public static String viewParties(){
        StringBuilder candidatesString = new StringBuilder();
        for (String party : partyList) {
            candidatesString.append(party).append(" ");
        } return candidatesString.toString();
    }

    public boolean getEligibilityStatus(){
        return eligibilityStatus;
    }

    @Override
    public long getCounter() {
        return super.getCounter();
    }

    public static int getqCandidatesCounter() {
        return electionRequirementsApprovalList.size();
    }

    private void politicsInfo(String partyName, String slogan, String position, String politicalExperience, String manifesto) {
        politicsInfoCheck(partyName,slogan,position,politicalExperience,manifesto);
    }

    private void politicsInfoCheck(String partyName, String slogan, String position, String politicalExperience, String manifesto) {
        if (partyName == null || partyName.isEmpty()) {
            throw new IllegalArgumentException("Pls input your partyName");
        } if (slogan == null || slogan.isEmpty()) {
            throw new IllegalArgumentException("Pls input your slogan");
        } if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Pls input your political position of pursuit");
        } if (politicalExperience == null || politicalExperience.isEmpty()) {
            throw new IllegalArgumentException("Pls input your political experience");
        } if (manifesto == null || manifesto.isEmpty()) {
            throw new IllegalArgumentException("Pls input your manifesto");
        }
            if(manifesto.length() < 30 ) throw new IllegalArgumentException("Pls input more manifesto Information.(more than 30 characters)");
            if(politicalExperience.length() < 20)throw new IllegalArgumentException("Pls input more Political Exp. Information.(more than 30 characters)");

    }


    private void educationalAndProfessionalBackground(String educationHistory, String professionHistory) {
        validateEducation(educationHistory);
        validateProfession(professionHistory);
    }


    private void validateProfession(String professionHistory) {
        if(professionHistory.isEmpty())throw new IllegalArgumentException("Pls input your latest Educational Status");
        for(ProfessionKeywords keyword : ProfessionKeywords.values()){
            if(professionHistory.toLowerCase().contains(keyword.name())) throw new IllegalArgumentException("You Are Ineligible To Campaign In Election Based On Eligibility Parameters");
        }
    }

    private void validateEducation(String educationHistory) {
        if(educationHistory.isEmpty())throw new IllegalArgumentException("Pls input your latest Educational Status");
        for(EducationKeywords keyword : EducationKeywords.values()){
            if(educationHistory.toLowerCase().contains(keyword.name())) throw new IllegalArgumentException("You Are Ineligible To Campaign In Election Based On Eligibility Parameters");
        }
    }

    public void declarationOfEligibility(String response){
        if(validateResponse(response)) eligibilityStatus = true;
        //return all the list of everything plus the eligibility status and save it to the arraylist

    }

    private boolean validateResponse(String response) {
        if(!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) throw new IllegalArgumentException("Input yes or no to agree or disagree to the given terms");
        else if(response.equalsIgnoreCase("yes")){
            return true;
        }else return false;
    }

    public String getCandidatesName() {
        return Candidate.getName();
    }

}
