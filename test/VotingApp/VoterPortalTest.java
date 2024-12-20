package VotingApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoterPortalTest {
    VoterPortal voter ;
    @BeforeEach
    public void instantiation() {
       voter = new VoterPortal();
    }

    @Test
    public void TestForVoterRegistration(){
        voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        voter.register("Okechukwu","Peter","F","29/10/2008","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        voter.register("Okechukwu","Peter","f","29/10/2002","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        voter.register("Okechukwu","Peter","m","29/10/2010","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");

        assertEquals(voter.getCounter(),5);
    }


    @Test
    public void testThatYouCanFindARegisteredUser(){
        voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        voter.register("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@yahoo.com","0809ja", "ABC1234567890123456");

        Voter user  = voter.findRegisteredVoter("okechukwuPeter10@yahoo.com","0809ja");
        assertEquals(user.getEmail(), "okechukwuPeter10@yahoo.com");



        IllegalArgumentException CantFindUserError = assertThrows(IllegalArgumentException.class,()->
                voter.findRegisteredVoter("kwuPeter10@yahoo.com","080ja"));
        assertEquals(CantFindUserError.getMessage(), "Citizen not found...Please Register");

    }




}
