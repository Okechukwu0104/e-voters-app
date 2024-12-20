package VotingApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CandidatePortalTest {
    CandidatePortal candidate ;
    @BeforeEach
    public void instantiation() {
        candidate = new CandidatePortal();
    }

    @Test
    public void TestForCandidateRegistration(){
        candidate.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.register("Okechukwu","Peter","F","29/10/2008","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.register("Okechukwu","Peter","f","29/10/2002","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.register("Okechukwu","Peter","m","29/10/2010","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");

        assertEquals(candidate.getCounter(),5);

    }
    @Test
    public void TestTatCandidateIsntEligibleUnlessFillsThe_ElectionRequirements_Form(){
        candidate.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        assertFalse(candidate.getEligibilityStatus());

    }


    @Test
    public void testThatYouCanFindARegisteredCandidate(){
        candidate.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","0809ja", "ABC1234567890123456");

        Voter user  = candidate.findRegisteredVoter("okechukwuPeter10@yahoo.com","0809ja");
        assertEquals(user.getEmail(), "okechukwuPeter10@yahoo.com");



        IllegalArgumentException CantFindUserError = assertThrows(IllegalArgumentException.class,()->
                candidate.findRegisteredVoter("kwuPeter10@yahoo.com","080ja"));
        assertEquals(CantFindUserError.getMessage(), "Citizen not found...Please Register");

    }


    @Test
    public void testThatYOuCanBecomeEligibleAfterFillingProcess(){
        candidate.register("okechukwu","peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        candidate.electionRequirements("pdp","The umbrella","president","Graduated from Afe-Babalola university and completer NYSC","Senior software Engineer","Former Vice President 2021-2024","We dont have light in this country...ill make sure there is 247 light using solar.","yes");

        candidate.register("kechukwu","Antohny","M","29/10/2003","+2349049947055","Ajao Estate Niger","okechukwuPeter10@Yahool.com","080elisatar8r", "EOB1234561820423456");
        candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes");


        assertEquals(CandidatePortal.getqCandidatesCounter(),2);
    }



    @Test
    public void testAllErrorPossibilitiesForElectionRequirementsMethod(){
        candidate.register("kechukwu","Antohny","M","29/10/2003","+2349049947055","Ajao Estate Niger","okechukwuPeter10@Yahool.com","080elisatar8r", "EOB1234561820423456");
//        candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes");

        IllegalArgumentException partyNameError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(partyNameError.getMessage(), "Pls input your partyName");

        IllegalArgumentException sloganError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(sloganError.getMessage(), "Pls input your slogan");

        IllegalArgumentException positionError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(positionError.getMessage(), "Pls input your political position of pursuit");

        IllegalArgumentException politicalExperienceError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(politicalExperienceError.getMessage(), "Pls input your political experience");

        IllegalArgumentException educationHistoryError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock junior high school","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(educationHistoryError.getMessage(), "You Are Ineligible To Campaign In Election Based On Eligibility Parameters");

        IllegalArgumentException manifestoError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...","yes"));
        assertEquals(manifestoError.getMessage(), "Pls input more manifesto Information.(more than 30 characters)");

        IllegalArgumentException professionHistoryError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Smooth Criminal","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","yes"));
        assertEquals(professionHistoryError.getMessage(), "You Are Ineligible To Campaign In Election Based On Eligibility Parameters");

        IllegalArgumentException declarationOfEligibilityError = assertThrows(IllegalArgumentException.class,()->
                candidate.electionRequirements("apc","The broom for change","president","Graduated from Babcock university and im a chartered Accountant","Senior Accountant","Former President 2018-2020","I dont care what Apc said...ill do better than all theyve said in their manifesto","maybe"));
        assertEquals(declarationOfEligibilityError.getMessage(), "Input yes or no to agree or disagree to the given terms");

        INEC inec = new INEC();
        System.out.println(inec.displayAllCandidates());

    }

}
