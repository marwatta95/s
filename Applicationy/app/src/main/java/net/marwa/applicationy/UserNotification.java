package net.marwa.applicationy;

/**
 * Created by samar fares on 4/8/2018.
 */

public class UserNotification {
    String partyDate;
    String headName;
    String headPhone;
    public String pushID;
public UserNotification(String partyDate,String headName,String headPhone,String pushID)
{
    this.partyDate=partyDate;
    this.headName=headName;
    this.headPhone=headPhone;
    this.pushID=pushID;

}
}
