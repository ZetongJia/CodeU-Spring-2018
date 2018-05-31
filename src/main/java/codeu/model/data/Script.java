// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import java.time.Instant;
import java.io.*;
import codeu.model.data.*;
import codeu.model.store.basic.*;
import java.util.*;


/**
 * Class representing a script. Scripts are created by the admin, 
 * and also create new Users, Messages, and Conversations.
 */
public class Script {
  public final String theme;
  public final Map<String, List<String>> content;

  /**
   * Constructs a new Script.
   *
   * @param theme the theme of this (movie) Script
   * @param content the content of this (movie) Conversation
   */
  public Script(String theme) {
    this.theme = theme;
    this.content = mapScript();
  }

  /** Returns the theme of this Script. */
  public String getTheme() {
    return theme;
  }

  /** Returns the content of this Script. */
  public Map<String, List<String>> getContent() {
    System.out.println("GOT CONTENT!!");
    return content;
  }

  public Map<String, List<String>> mapScript(){
    System.out.println("MAPPING!!");
    Map<String, List<String>> map = new HashMap<String, List<String>>();

    try {

      BufferedReader file = new BufferedReader(new FileReader("scripts/" + theme));
      String line;

      while ((line = file.readLine()) != null) {

        String arr[] = line.split(":\\s", 2);
        String character = arr[0], dialogue = arr[1]; 

        if (map.get(character) == null) {
            map.put(character, new ArrayList<String>());
        }
        map.get(character).add(dialogue);
      }
      System.out.println(map);

    }catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("RETURNED!!");
    return map;
  }

}
