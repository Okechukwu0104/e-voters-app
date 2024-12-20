package VotingApp;

import java.util.ArrayList;
import java.util.Random;

public class INEC {
    private static int[] electionRecord = new int[(int) CandidatePortal.getqCandidatesCounter()];

    private static ArrayList<String> PVCList = new ArrayList<>();
    private static final String[] STATES = {"AB", "LA", "KAN", "FCT", "EN", "OG","PH", "KAT","JIG","DEL","EB","BEN"};



    public static String displayAllCandidates(){
        CandidatePortal candidates = new CandidatePortal();
        return candidates.viewQualifiedCandidates();
    }

    public  void recordElection(String pvcNumber, int candidateIndex, String password){

      Voter foundVoter = VoterPortal.verifyRegisteredVoter(pvcNumber, password);
        validateCandidateIndexAndRecordVote(foundVoter,candidateIndex);

    }


    public String displayElectionResult( String PvcNumber){
        if (verifyPvc(PvcNumber)) {

            CandidatePortal.viewParties();
            displayVotes();


        } throw new IllegalArgumentException("Viewing results is for voters at the momment");

    }

    private boolean verifyPvc(String inputtedPvcNumber) {
        for(String pvc : PVCList){
            if(pvc.equalsIgnoreCase(inputtedPvcNumber)) return true;
        }
        throw new IllegalArgumentException("PVC not found");
    }

    private static void validateCandidateIndexAndRecordVote(Voter foundVoter, int candidateIndex) {
        if (foundVoter.hasVoted) throw new IllegalArgumentException("You have voted already");
        if (candidateIndex < 1 || candidateIndex > CandidatePortal.getqCandidatesCounter())
            throw new IllegalArgumentException("Invalid candidate number");

        electionRecord[candidateIndex -1 ]++;
        foundVoter.hasVoted = true;
    }



    public String distributePVC() {

        Random random = new Random();

        // random state code
        String stateCode = STATES[random.nextInt(STATES.length)];

        // random registration area (3 digits)
        int regArea = 100 + random.nextInt(900);

        // random unique id (7 digits)
        int uniqueId = 1000000 + random.nextInt(9000000);
        String PvcNumber = String.format("%s-%03d-%07d", stateCode, regArea, uniqueId);
        PVCList.add(PvcNumber);
        return PvcNumber;
    }

    private StringBuilder displayVotes() {
        StringBuilder votes = new StringBuilder();
        for (long totalVote : electionRecord) {
            votes.append(totalVote).append(" ");
        }
        return votes;
    }

    public String getPVCList() {
        return PVCList.toString();
    }
}
