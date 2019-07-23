package org.w4lorg.chatop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserData {
  private final static ObjectMapper _objMapper = new ObjectMapper();
  private final static TypeReference<List<UidSkypeNamePair>> _TypeRef_UidSkypeNamePair = new TypeReference<List<UidSkypeNamePair>>() {};
  private final static TypeReference<List<SidUids>> _TypeRef_SidUids = new TypeReference<List<SidUids>>() {};

  public static <T> List<T> readValues(String j, TypeReference<List<T>> typeRef) {
    try {
      return _objMapper.readValue(j, typeRef);
    }
    catch (Exception ex) {
      throw new RuntimeException("error reading json", ex);
    }
  }

  static Map<String, List<UidSkypeNamePair>> readSid2UidsMap(String dataPath, Map<String, UidSkypeNamePair> uid2sns) {
    try {
      FileInputStream ins = new FileInputStream(dataPath + "sid2uids.json");
      String str = IOUtils.toString(ins, StandardCharsets.UTF_8);
      List<SidUids> res = readValues(str, _TypeRef_SidUids);
      ins.close();
      return res.stream().collect(
        Collectors.toMap(
          p -> p.sid,
          p -> p.uids.stream().map(uid2sns::get).collect(Collectors.toList())
        )
      );
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  static Map<String, UidSkypeNamePair> readUid2SkypeNamesMap(String dataPath) {
    try {
      FileInputStream ins = new FileInputStream(dataPath + "uid2sn.json");
      String str = IOUtils.toString(ins, StandardCharsets.UTF_8);
      List<UidSkypeNamePair> res = readValues(str, _TypeRef_UidSkypeNamePair);
      ins.close();
      return res.stream().collect(
        Collectors.toMap(
          p -> p.uid,
          Function.identity()
        )
      );
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private final Map<String, List<UidSkypeNamePair>> sid2UidSnPairs;
  private final Map<String, UidSkypeNamePair> uid2sns;
  public UserData(String dataPath) {
    this.uid2sns = readUid2SkypeNamesMap(dataPath);
    this.sid2UidSnPairs = readSid2UidsMap(dataPath, uid2sns);
  }

  public Map<String, List<UidSkypeNamePair>> getSid2UidSnPairs() {
    return sid2UidSnPairs;
  }

  public Map<String, UidSkypeNamePair> getUid2sns() {
    return uid2sns;
  }
}
