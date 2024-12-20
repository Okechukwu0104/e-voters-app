package VotingApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CandidateTest {
//
   @Test
    public void TestThatRegisteringCandidateIsSuccessful(){
        Candidate user = new Candidate("Okechukwu","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456");
        String output =
                """
                Candidate Name: Okechukwu Peter
                Gender: M
                D.O.B: 29/10/2001
                Contact Details: +2349049947055
                Candidate Id: ABC1234567890123456
                """;

        assertEquals(user.getDetails("080wwer"), output);
    }


    @Test
    public void TestToCheckIfRegisterMethodThrowsErrorOnWronglyInputtedParameters(){
        // name errors
        IllegalArgumentException nameError = assertThrows(IllegalArgumentException.class,()->
                new Candidate("","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456"));
        assertEquals(nameError.getMessage(), "Your Name Cant Be Left Empty");

        //DOB errors
        IllegalArgumentException DobError1 = assertThrows(IllegalArgumentException.class,()->
                new Candidate("erygvknr","Peter","M","29/10/2025","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456"));
        assertEquals(DobError1.getMessage(), "Invalid Year of birth");

        IllegalArgumentException DobError2 = assertThrows(IllegalArgumentException.class,()->
                new Candidate("erygvknr","Peter","M","29/10 2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC1234567890123456"));
        assertEquals(DobError2.getMessage(), "Input date using dd/mm/yyyy format");


        // email errors
        IllegalArgumentException emailError1 = assertThrows(IllegalArgumentException.class,()->
                new Candidate("iysdfuiugv","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10gmail.com","080wwer", "ABC1234567890123456"));
        assertEquals(emailError1.getMessage(), "Invalid Email");

        IllegalArgumentException emailError2 = assertThrows(IllegalArgumentException.class,()->
                new Candidate("iysdfuiugv","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail com","080wwer", "ABC1234567890123456"));
        assertEquals(emailError2.getMessage(), "Invalid Email");


        //password errors
        IllegalArgumentException passwordError = assertThrows(IllegalArgumentException.class,()->
                new Candidate("shdfkvjjj","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080", "ABC1234567890123456"));
        assertEquals(passwordError.getMessage(), "password must contain letters , symbols and Digits");

        IllegalArgumentException votersIdLengthError = assertThrows(IllegalArgumentException.class,()->
                new Candidate("shdfkvjjj","Peter","M","29/10/2001","+2349049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC123456009876543789"));
        assertEquals(votersIdLengthError.getMessage(), "Issued ID must be at most 19 Digits in length");


        //phone number errors
        IllegalArgumentException phoneNumberError = assertThrows(IllegalArgumentException.class,()->
                new Candidate("shdfkvjjj","Peter","M","29/10/2001","+09049947055","Ajao Estate lagos","okechukwuPeter10@gmail.com","080wwer", "ABC123456009876543789"));
        assertEquals(phoneNumberError.getMessage(), "Issued ID must be at most 19 Digits in length");

        // gender errors

    }

}
