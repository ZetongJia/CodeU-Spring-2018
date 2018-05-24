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
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">
    <h1>Profile Page</h1>

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <h2>About Me</h2>

    <form action="/submit" method="POST">
      <label for="aboutme">Edit your About Me </label>
      <br/>
      <textarea rows="50" cols="100" name="aboutme" id="aboutme"></textarea>
      <br/>
      <input type="text" name="aboutme" id="aboutme">
      <br/>
      <label for="conversations">Sent Messages </label>
      <br/>
      <input type="password" name="password" id="password">
      <br/><br/>
      <button type="submit">Submit</button>
    </form>

  </div>
</body>
</html>
