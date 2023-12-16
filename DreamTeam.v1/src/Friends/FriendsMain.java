package Friends;

import Network.NetworkDriver;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class FriendsMain {

    NetworkDriver networkDriver = new NetworkDriver();

    public void createFriendsList(String username) {    //to be run when an account is created
        try {
            Statement createStatement = networkDriver.network.createStatement();
            createStatement.executeUpdate("create table "+username+"Friends (username varchar(50),status varchar(20));");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int sendRequest(String sendingUser,String recievingUser) {
        try{Statement requestStatement = networkDriver.network.createStatement();
            ResultSet userSet = requestStatement.executeQuery("select * from "+sendingUser+"Friends");
            while (userSet.next()) {
                if (recievingUser.equals(userSet.getString("username"))) {
                    if(userSet.getString("status").equals("friends")){
                        System.out.println("Case: you are already friends with this user");
                        return 1;
                    }else if(userSet.getString("status").equals("sent")){
                        System.out.println("Case: you have already sent this user a friend request");
                        return 2;
                    }else if(userSet.getString("status").equals("recieved")){
                        System.out.println("Case: user has already sent you a friend request");
                        //could either auto accept request or just tell the user to go to friends request page
                        return 3;
                        }
                    }
                }
            Statement sendStatement = networkDriver.network.createStatement();
            sendStatement.executeUpdate("insert into "+recievingUser+"Friends values ('"+sendingUser+"','recieved')");
            sendStatement.executeUpdate("insert into "+sendingUser+"Friends values ('"+sendingUser+"','sent')");
        return 0;
    }catch(Exception e){}
        return 4;
    }

    public void acceptRequest(String sendingUser,String recievingUser) {
        try{
        Statement acceptStatement = networkDriver.network.createStatement();
        acceptStatement.executeUpdate("update "+recievingUser+"Friends set status='friends' where username='"+sendingUser+"'");
        acceptStatement.executeUpdate("update "+sendingUser+"Friends set status='friends' where username='"+recievingUser+"'");
        }catch(Exception e){
            System.out.println(e);
        }}
    public void denyRequest(String sendingUser,String recievingUser){
        try{
            Statement denyStatement = networkDriver.network.createStatement();
            denyStatement.executeUpdate("delete from "+recievingUser+" where username='"+sendingUser+"'");
            denyStatement.executeUpdate("delete from "+sendingUser+" where username='"+recievingUser+"'");
        }catch(Exception e){
            System.out.println(e);
        }}

}

