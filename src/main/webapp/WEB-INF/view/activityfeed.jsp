<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Activity"%>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.store.basic.ConversationStore" %>
<%
ConversationStore conversationStore = (ConversationStore) request.getAttribute("conversationStore");
UserStore userStore = (UserStore) request.getAttribute("userStore");
List<Activity> ActivityList = (List<Activity>) request.getAttribute("activities");
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
    <h1 font-size="700px">Activity</h1>
    <h2 top-padding="50px" bottom-padding="50px">See all site activity here!</h2>
    <div id="feed">
      <ul>
        <%
          response.setIntHeader("Refresh", 5);
          for(Activity activity : ActivityList){
            if (activity instanceof Message){
              Message message = (Message) activity;
              String convoTitle = conversationStore.getConversation(message.getConversationId()).getTitle();
              String author = userStore.getUser(message.getAuthorId()).getName();
        %>
          <li><%= message.timeFormat() %>: <%= author %> sent a message in <%= convoTitle %>: <%= message.getContent() %></li>
        <%
            }

            else if (activity instanceof Conversation){
              Conversation conversation = (Conversation) activity;
        %>
          <li><%= conversation.timeFormat() %>: New Conversattion <%= conversation.getTitle() %> created!</li>
        <%
            }

            else if (activity instanceof User){
              User newUser = (User) activity;

        %>
          <li><%= newUser.timeFormat() %>: New User <b><%= newUser.getName() %></b> joined!</li>

        <%
            }
          }
        %>
      </ul>
    </div>

  </div>

</body>
</html>
