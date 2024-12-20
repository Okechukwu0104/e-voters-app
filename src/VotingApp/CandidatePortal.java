package VotingApp;

import java.util.ArrayList;

public class CandidatePortal extends VoterPortal {

    private static ArrayList<String> electionRequirementsApprovalList = new ArrayList<>();
    private boolean eligibilityStatus = false;

    private static ArrayList<String> partyList = new ArrayList<>();


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
