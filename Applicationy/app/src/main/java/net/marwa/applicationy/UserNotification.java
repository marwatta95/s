package net.marwa.applicationy;

/**
 * Created by samar fares on 4/8/2018.
 */

public class UserNotification {
    String partyDate;
    String headName;
    String headPhone;
    public String pushID;

    public UserNotification(){}
public UserNotification(String partyDate,String headName,String headPhone)
{
    this.partyDate=partyDate;
    this.headName=headName;
    this.headPhone=headPhone;


}
    public void setPushID(String pushID){
        this.pushID=pushID;
    }
}
