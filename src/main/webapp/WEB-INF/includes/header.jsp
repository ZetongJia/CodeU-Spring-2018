<nav>
  <a id="navTitle" href="/">CodeU Chat App <i>-BY TEAM ZETA</i></a>
  <a href="/conversations">Conversations</a>
  <% if(request.getSession().getAttribute("user") != null){ %>
    <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <a href="/user/">My Profile</a>
  <% } else{ %>
    <a href="/login">Login</a>
  <% } %>
  <a href="/about.jsp">About</a>
  <!--admin link will be restricted for admin in mvp-->
  <a href="/admin">Admin</a>
</nav>
