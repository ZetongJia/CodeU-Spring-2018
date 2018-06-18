<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
List<Conversation> conversations = (List<Conversation>) request.getAttribute("conversations");
List<Message> messages = (List<Message>) request.getAttribute("messages");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Activity Feed</title>
  <link rel="stylesheet" href="/css/main.css" type="text/css">

  <style>
    #feed {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
  </style>

</head>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">
    <div id="feed">
      <ul>
        <% for(int i = 0; i < messages.length; i++){ %>
          <li><%= messages[i].getCreationTime() %>: User sent a message: <%= messages[i].getContent() %></li>
        <% } %>
      </ul>
    </div>

    <hr/>

    <hr/>

  </div>

</body>
</html>
