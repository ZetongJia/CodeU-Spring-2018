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
  <title>Admin Page</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">
    <h1>Administration</h1>
    <hr>
    <h2>Site Statistics</h2>
    <p>Here are some site stats:
      <ul>
        <li>Users:</li>
        <li>Conversations:</li>
        <li>Messages:</li>
        <li>Most Active User:</li>
        <li>Wordiest User:</li>
      </ul>
    </p>
    <hr>
    <h2>Import Data</h2>
      <form action=/ method=POST>
        <p>
          From source:
          <select name="cars">
            <option value="opt1">Opt1</option>
            <option value="opt2">Opt2</option>
            <option value="opt3">Opt3</option>
          </select>
        </p>
        <input type="submit" value="Submit">
      </form>
    </div>

  </div>

</body>
</html>
