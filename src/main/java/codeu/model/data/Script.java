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
  public final String path;

  /**
   * Constructs a new Script.
   *
   * @param theme the theme of this (movie) Script
   * @param path the path of this (movie) Script
   */
  public Script(String theme) {
    this.theme = theme;
    this.path = "WEB-INF/scripts/" + theme;
  }

  public Script(String theme, String path) {
    this.theme = theme;
    this.path = path;
  }

  /** Returns the theme of this Script. */
  public String getTheme() {
    return theme;
  }

  /** Returns the path of this Script. */
  public String getPath() {
    return path;
  }

}
