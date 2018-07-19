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
    List<Activity> MentionList = (List<Activity>) request.getAttribute("mentions");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Mentions</title>
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <%-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script> --%>

    <style>
        #mentions {
            background-color: white;
            height: 500px;
            overflow-y: scroll
        }
    </style>

</head>

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div id="container">
    <h1 font-size="700px">Mentions</h1>

    <h2 top-padding="50px" bottom-padding="50px">See all your mentions here!
        <a href="" style="float: right">&#8635;</a></h2>
    <div id="mentions">
        <ul>
            <%
                for(Activity mention : MentionList){
                    if (mention instanceof Message){
                        Message message = (Message) mention;
                        String convoTitle = conversationStore.getConversation(message.getConversationId()).getTitle();
                        String author = userStore.getUser(message.getAuthorId()).getName();
                        if(!request.getSession().getAttribute("user").equals(author))
                          message.setMessageSeen();
            %>
            <li><%= message.timeFormat() %>: <b><%= author %></b> mentioned you in <a href="/chat/<%= convoTitle %>"><%= convoTitle %></a>: <%= message.getContent() %></li>
            <%
                    }
                }
            %>
        </ul>
    </div>

</div>

<%-- <script>
  $(document).ready(function() {
    setInterval(function()
    {
      $('#mentions').load(document.URL + ' #mentions');
    }, 7000);
  });
</script> --%>

</body>
</html>
