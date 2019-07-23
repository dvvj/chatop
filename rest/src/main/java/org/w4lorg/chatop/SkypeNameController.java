package org.w4lorg.chatop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class SkypeNameController {

//  private final static List<UidSkypeNamePair> _uid2names = Arrays.asList(
//    new UidSkypeNamePair("uid001", "skype-name-001"),
//    new UidSkypeNamePair("uid002", "skype-name-002"),
//    new UidSkypeNamePair("uid004", "skype-name-004")
//  );
//  private final static Map<String, UidSkypeNamePair> uid2SkypeNameMap = _uid2names.stream().collect(
//    Collectors.toMap(
//      p -> p.uid,
//      Function.identity()
//    )
//  );

  private final String userDataPath = "/appdata/";


  private final UserData userData = new UserData(userDataPath);

  @PostMapping(path="/uids2SkypeNames")
  public List<UidSkypeNamePair> uids2SkypeNames(
    @RequestBody List<String> uids
  ) {
    return uids.stream().map(userData.getUid2sns()::get)
      .filter(x -> x != null)
      .collect(Collectors.toList());
  }

//  private static Map<String, List<UidSkypeNamePair>> generateSid2SkypeNamesMap() {
//    Map<String, List<UidSkypeNamePair>> res = new HashMap<>();
//
//    res.put(
//      "sid01",
//      Arrays.asList(
//        new UidSkypeNamePair("uid001", "skype-name-001"),
//        new UidSkypeNamePair("uid002", "skype-name-002")
//      )
//    );
//
//    res.put(
//      "sid02",
//      Arrays.asList(
//        new UidSkypeNamePair("uid001", "skype-name-001"),
//        new UidSkypeNamePair("uid003", "skype-name-003"),
//        new UidSkypeNamePair("uid004", "skype-name-004")
//      )
//    );
//    return res;
//  }
//
//  private final static Map<String, List<UidSkypeNamePair>> sid2SkypeNameMap = generateSid2SkypeNamesMap();



  @PostMapping(path="/sid2SkypeNames")
  public List<UidSkypeNamePair> sid2SkypeNames(
    @RequestBody String sid
  ) {
    List<UidSkypeNamePair> res = userData.getSid2UidSnPairs().get(sid);
    return res != null ? res : new ArrayList<>(0);
  }

}
