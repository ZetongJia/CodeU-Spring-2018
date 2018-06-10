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
import java.util.UUID;

/** Class representing a registered user. */
public class User {
  private final UUID id;
  private final String name;
  private final String passwordHash;
  private final Instant creation;
  private long numMessages;
  private long numWords;
  private boolean isAdmin;

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
   * @param passwordHash the password hash of this User
   * @param creation the creation time of this User
   * @param isAdmin the admin status of this User
   */
  public User(UUID id, String name, String passwordHash, Instant creation, boolean isAdmin) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.creation = creation;
    this.numMessages = 0;
    this.numWords = 0;
    this.isAdmin = isAdmin;
  }

  /** Returns the ID of this User. */
  public UUID getId() {
    return id;
  }

  /** Returns the username of this User. */
  public String getName() {
    return name;
  }
  
  /** Returns the password hash of this User. */
  public String getPasswordHash() {
    return passwordHash;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns the number of messages sent by this User. */
  public long getNumMessages() {
    return numMessages;
  }

  /** Returns the number of messages sent by this User. */
  public void incrementNumMessages() {
    this.numMessages++;
  }

  /** Returns the number of words typed by this User. */
  public long getNumWords() {
    return numWords;
  }

  /** Returns the number of words sent by this User. */
  public void incrementNumWords(long words) {
    this.numWords += words;
  }  

  /** Returns true if user is admin. */
  public boolean getIsAdmin() {
    return isAdmin;
  }

}
