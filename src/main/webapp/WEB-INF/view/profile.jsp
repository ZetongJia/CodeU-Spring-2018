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
<%@ page import ="java.lang.String"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Date"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.time.Instant"%>
<%@ page import ="java.time.format.DateTimeFormatter"%>
<%@ page import ="codeu.model.data.Message"%>
<%@ page import ="codeu.model.data.User"%>
<%@ page import ="codeu.model.store.basic.UserStore"%>

<!DOCTYPE html>
<html>
<head>
  <title>My Profile</title>
  <link rel="stylesheet" href="/css/main.css" type="text/css">

    <style>
      #chat {
        background-color: white;
        height: 500px;
        overflow-y: scroll
      }
    </style>

</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">

    <h1><%= request.getAttribute("username")%>'s Profile Page</h1>
    <hr/>

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <h2>About Me</h2>
    <% User user = UserStore.getInstance().getUser(String.valueOf(request.getAttribute("username")));
    %>
    <p><%= user.getAboutMe()%></p>

    <%if(request.getSession().getAttribute("user").equals(request.getAttribute("username"))) {%>
        <h3>Edit Your About Me</h3>

        <form action="/user/<%=request.getSession().getAttribute("user")%>" method="POST">
          <br/>
          <textarea rows="10" cols="110" name="aboutme"><%=user.getAboutMe()%></textarea>
          <br/><br/>
          <input type="submit" value="Submit"/>
        </form>
    <%}%>
    
    <hr/>
    <h2>Sent Messages</h2>

    <div id="chat">
        <ul>
          <%
          List<Message> sentMessages = (ArrayList<Message>) request.getAttribute("usermessages");

          for(Message message : sentMessages) {
              Date date = Date.from(message.getCreationTime());
          %>
              <li>
                <b><%out.print(date);%></b>
                <%out.println(message.getContent());%>
              </li>
          <%}
          %>
        </ul>
    </div>
    <hr/>

  </div>
</body>
</html>
