package org.w4lorg.chatop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class SkypeNameController {

  private final static List<UidSkypeNamePair> _uid2names = Arrays.asList(
    new UidSkypeNamePair("uid001", "skype-name-001"),
    new UidSkypeNamePair("uid002", "skype-name-002"),
    new UidSkypeNamePair("uid004", "skype-name-004")
  );
  private final static Map<String, UidSkypeNamePair> uid2SkypeNameMap = _uid2names.stream().collect(
    Collectors.toMap(
      p -> p.uid,
      Function.identity()
    )
  );

  @PostMapping(path="/uids2SkypeNames")
  public List<UidSkypeNamePair> uids2SkypeNames(
    @RequestBody List<String> uids
  ) {
    return uids.stream().map(uid2SkypeNameMap::get)
      .filter(x -> x != null)
      .collect(Collectors.toList());
  }

}
