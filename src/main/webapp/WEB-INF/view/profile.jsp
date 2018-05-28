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
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="codeu.model.data.Message"%>

<!DOCTYPE html>
<html>
<head>
  <title>My Profile</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">
    <h1><%=request.getSession().getAttribute("user")%>'s Profile Page</h1>
    <hr/>


    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <h2>About Me</h2>
    <p><%= request.getSession().getAttribute("aboutme")%></p>

    <h3>Edit Your About Me</h3>

    <form action="/user/<%=request.getSession().getAttribute("user")%>" method="POST">
      <br/>
      <textarea rows="10" cols="100" name="aboutme"><%= request.getSession().getAttribute("aboutme")%></textarea>
      <br/><br/>
      <input type="submit" value="Submit"/>
    </form>
    <hr/>

    <h2>Sent Messages</h2>
    <div class="messages">
      <p><%=request.getSession().getAttribute("usermessages")%></p>
      <%
      // retrieve your list from the request, with casting
      List<Message> sentMessages = request.getSession().getAttribute("usermessages");

      for(Message message : sentMessages) {
          System.out.println(message);
      }
      %>
    </div>
    <hr/>


  </div>
</body>
</html>
