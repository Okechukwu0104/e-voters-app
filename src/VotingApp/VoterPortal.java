package VotingApp;

import java.util.ArrayList;



public class VoterPortal {

    private static ArrayList<Voter> votersList = new ArrayList<>();
    private static ArrayList<String> votersHashedPasswords = new ArrayList<>();

    private long counter = 0;

    public Voter register(String firstName, String lastname, String gender, String DOB, String phoneNumber, String address, String email, String password, String votersId) {
        Voter voter = new Voter(firstName, lastname,gender,DOB,phoneNumber,address, email, password, votersId);
        hashAndStorePassword(password);
        votersList.add(voter);
        counter++;
        return voter;
    }

    public long getCounter() {
        return counter;
    }

//                              get or generate Pvc !!!

    public Voter findRegisteredVoter(String email , String password){
        for( Voter user : votersList){
            if(user.getEmail().equalsIgnoreCase(email) && validatePassword(password)) return user;
        }
        throw new IllegalArgumentException("Citizen not found...Please Register");
    }

    public static Voter verifyRegisteredVoter( String pvc, String password){
        for( Voter user : votersList){

            if(user.getMyPvcNumber().equalsIgnoreCase(pvc) ){
                user.passwordValidation(password);
                return user;
            }
        }

        throw new IllegalArgumentException("Citizen not found");
    }


    private static boolean validatePassword(String password) {
        for (String hashedPass : votersHashedPasswords) {
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
        votersHashedPasswords.add(hashedPassword);
    }


    private void hashAndStorePassword(String password) {
        hashAndAdd(password);
    }



}
