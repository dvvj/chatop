package org.w4lorg.chatop;

public class UserDataTest {
  public static void main(String[] args) {
    UserData ud = new UserData("./userdata/");

    System.out.println(ud.getUid2sns().size());
    System.out.println(ud.getSid2UidSnPairs().size());
  }
}
