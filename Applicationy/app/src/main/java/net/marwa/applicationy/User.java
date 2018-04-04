package net.marwa.applicationy;

import java.util.LinkedList;

/**
 * Created by samar fares on 2/15/2018.
 */

public class User {

    public int type;
    public String firstName;
    public String lastName;
    public String gender;
    public String address;
    public String dateOfBirth;
public  LinkedList<Party> myparties;


    public User(){

        }

        public User(int type,String firstName, String lastName, String gender,String address,String dateOfBirth) {
         this.type=type;  this.firstName=firstName;this.lastName=lastName;this.gender=gender;this.address=address;
         this.dateOfBirth=dateOfBirth;
        myparties=new LinkedList<Party>(  );
        }
public void addParty(Party p){

     myparties.add( p );

}

}
