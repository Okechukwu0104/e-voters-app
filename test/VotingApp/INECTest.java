package VotingApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class INECTest {
    @Test
    public void TestThatCandidatesCanBeDisplayed(){
        CandidatePortal candidate = new CandidatePortal();

        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        candidate.register("belly","Antohny","M","29/10/2003","+2349049947055","Ajao Estate Niger","okechukwuPeter10@Yahool.com","080elisatar8r", "EOB1234561820423456");
        candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes");

//        System.out.println(INEC.displayAllCandidates());
    }

    @Test
    public void TestThatVoterCanBEFoundByINECbyVoterPortal(){
        VoterPortal voter = new VoterPortal();
        Voter voter1 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","080anyaer", "ABC1234567890123456");
        String pvc1 = voter1.getMyPvcNumber() ;

        Voter foundUser = VoterPortal.verifyRegisteredVoter(pvc1,"080anyaer");
        assertEquals(foundUser.getVotersId(),"ABC1234567890123456");

        foundUser.passwordValidation("080anyaer");
    }

    @Test
    public void TestThatAsAVoterYouCanCastVoteAndItIsRecordedProperly(){
        VoterPortal voter = new VoterPortal();
        CandidatePortal candidate = new CandidatePortal();
        INEC inec = new INEC();

        Voter voter1 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","080anyaer", "ABC1234567890123456");
        Voter voter2 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        Voter voter3 = voter.register("john","Bellion","M","29/12/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@fmail.com","0809ja", "ABC1234567890123456");

        String pvc1 = voter1.getMyPvcNumber();
        String pvc2 = voter2.getMyPvcNumber();
        String pvc3 = voter3.getMyPvcNumber();

        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        candidate.register("bella","Antohny","M","29/10/2003","+2349049947055","Ajao Estate Niger","okechukwuPeter10@Yahool.com","080elisatar8r", "EOB1234561820423456");
        candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes");

        inec.recordElection(pvc1,1,"080anyaer");
        inec.recordElection(pvc3,2,"0809ja");
        inec.recordElection(pvc2,2,"080wwer");

        System.out.println(inec.displayElectionResult(pvc2));


    }




    @Test
    public void TestThatAsAVoterYouCanCastAVoteOnlyOnce(){
        VoterPortal voter = new VoterPortal();
        CandidatePortal candidate = new CandidatePortal();
        INEC inec = new INEC();

        Voter voter1 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","080anyaer", "ABC1234567890123456");
        String pvc1 = voter1.getMyPvcNumber();

        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        inec.recordElection(pvc1,1,"080anyaer");

        IllegalArgumentException votingAgainError = assertThrows(IllegalArgumentException.class,()->
                inec.recordElection(pvc1,1,"080anyaer"));
        assertEquals(votingAgainError.getMessage(),"You have voted already");
    }




    @Test
    public void TestThatAsACandidateYouCanCastVoteAndItIsRecordedProperly(){
        VoterPortal voter = new VoterPortal();
        CandidatePortal candidate = new CandidatePortal();
        INEC inec = new INEC();

        Voter voter1 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","080anyaer", "ABC1234567890123456");
        Voter voter2 = voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        Voter voter3 = voter.register("john","Bellion","M","29/12/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@fmail.com","0809ja", "ABC1234567890123456");

        String pvc1 = voter1.getMyPvcNumber();
        String pvc2 = voter2.getMyPvcNumber();
        String pvc3 = voter3.getMyPvcNumber();

        Candidate voter4 = candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","033sorry", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");
        String canPvc1 = voter4.getMyPvcNumber() ;

        Candidate voter5 = candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("apga","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");
        String canPvc2 = voter5.getMyPvcNumber() ;


        candidate.register("bella","Antohny","M","29/10/2003","+2349049947055","Ajao Estate Niger","okechukwuPeter10@Yahool.com","080elisatar8r", "EOB1234561820423456");
        candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes");

        inec.recordElection(pvc1,1,"080anyaer");
        inec.recordElection(pvc3,2,"0809ja");
        inec.recordElection(pvc2,2,"080wwer");
        inec.recordElectionForCandidates(canPvc1,1,"033sorry");
        inec.recordElectionForCandidates(canPvc2,3,"080wwer");

        System.out.println(inec.displayElectionResult(pvc2));


    }



}
