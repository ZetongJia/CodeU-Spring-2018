
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
<<<<<<< HEAD
  <% } else{ %>
        <a href="/login">Login</a>
=======
  <%  }

     }else{ %>
       <a href="/login">Login</a>
>>>>>>> 8c8c3225b26729c0504758f0d763e269e2dbf36c
  <% } %>

  <a href="/activityfeed">Activity Feed</a>

<<<<<<< HEAD
<<<<<<< HEAD

=======
=======
    <% if (last != null && last.getNotify() && user != null && listUsers != null) { %>
        <% for(User u : listUsers){
            if(u.getName().equals(user)){%>
                <a style="color:#FF0000;" href="/mentions"><b>Mentions</b></a>
                <% last.setNotify(false); %>
         <% }
        }%>

    <% }else{ %>
>>>>>>> 8c8c3225b26729c0504758f0d763e269e2dbf36c
  <a href="/mentions">Mentions</a>
    <% } %>

  <a href="/about.jsp">About</a>
>>>>>>> d900b0610eb02b6fdacc9c1d349cac3f2ae5e5d2
</nav>
