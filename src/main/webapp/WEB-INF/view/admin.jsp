<%--
  Copyright 2017 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>

<%@ page import="codeu.model.store.basic.*" %>

<!DOCTYPE html>
<html>
<head>
  <title>Admin Page</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">

    <div>
      <%
      UserStore userStore = UserStore.getInstance();
      ConversationStore conversationStore = ConversationStore.getInstance();
      MessageStore messageStore = MessageStore.getInstance();
      %>

      <h1>Administration</h1>
      <hr>
      <h2>Site Statistics</h2>
      <p>Here are some site stats:
        <ul>
          <li>Users: <%= userStore.getNumOfUsers() %> </li>
          <li>Conversations: <%= conversationStore.getNumOfConversations() %> </li>
          <li>Messages: <%= messageStore.getNumOfMessages() %> </li>
          <li>Most Active User: <%= userStore.getMostActiveUser() %> </li>
          <li>Newest User: <%= userStore.getNewestUser() %> </li>
          <li>Wordiest User: <%= userStore.getWordiestUser() %> </li>
        </ul>
      </p>
      <hr>
      <h2>Import Data</h2>
        <form action=/admin method=POST>
          <p>
            Create a conversation from a movie scene:
            <select name="script">
              <option value="Up.txt">Up</option>
              <option value="WallE.txt">Wall-E</option>
              <option value="FindingNemo.txt">Finding Nemo</option>
            </select>
          </p>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>

  </div>

</body>
</html>
