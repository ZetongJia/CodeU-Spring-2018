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

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.store.persistence.PersistentStorageAgent;
import java.time.Instant;
import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ScriptTest {

  // private UserStore userStore;
  // private PersistentStorageAgent mockPersistentStorageAgent;

  // @Before
  // public void setup() {
  //   mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
  //   userStore = UserStore.getTestInstance(mockPersistentStorageAgent);
  // }

  @Test
  public void testCreate() {
    String theme = "Sample.txt";
    Script script = new Script(theme);

    Map<String, List<String>> map = new HashMap<String, List<String>>()
    {{
    	put("ALICE", new ArrayList<String>());
    	put("AMNA", new ArrayList<String>());
    }};

    map.get("ALICE").add("a");
    map.get("AMNA").add("b");

    Assert.assertEquals(theme, script.getTheme());
    Assert.assertEquals(map, script.getContent());


  }
}
