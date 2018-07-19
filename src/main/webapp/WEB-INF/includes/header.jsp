
<%@ page import="codeu.model.data.User" %>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.MessageStore" %>
<%
    List<User> listUsers = null;
    Message last = MessageStore.getInstance().getLastMessage();
    if(last != null) {
        listUsers = last.usersMentioned();
    }
    String user = (String) request.getSession().getAttribute("user");

%>
<nav>
  <a id="navTitle" href="/">CodeU Chat App</a>

  <a href="/about.jsp">About</a>

  <a href="/conversations">Conversations</a>

  <% if(request.getSession().getAttribute("user") != null){ %>
        <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
        <a href="/user/<%=request.getSession().getAttribute("user")%>">My Profile</a>
  <% } else if (request.getSession().getAttribute("user")=="admin") { %>
        <a href="/admin">Admin</a>
  <%  
     }else{ %>
       <a href="/login">Login</a>
  <% } %>

  <a href="/activityfeed">Activity Feed</a>

    <% if (last != null && last.getNotify() && user != null && listUsers != null) { %>
        <% for(User u : listUsers){
            if(u.getName().equals(user)){%>
                <a style="color:#FF0000;" href="/mentions"><b>Mentions</b></a>
                <% last.setNotify(false); %>
         <% }
        }%>

    <% }else{ %>
  <a href="/mentions">Mentions</a>
    <% } %>

  <a href="/about.jsp">About</a>
</nav>
