package org.w4lorg.chatop;

import java.util.List;

public class SidUids {
  public String sid;
  public List<String> uids;

  public SidUids() { }

  public SidUids(
    String sid,
    List<String> uids
  ) {
    this.sid = sid;
    this.uids = uids;
  }
}
