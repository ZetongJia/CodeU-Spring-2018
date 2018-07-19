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
  <title>CodeU Chat App</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <jsp:include page="/WEB-INF/includes/header.jsp"/>

  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>About the CodeU Chat App</h1>
      <p>
        Hello everyone, we are ZETA! Our group members consist of Alice, Aubin,
        Tania and Tina. Throughout the summer, we've updated this chat app
        with some awesome improvements. Enjoy!
      </p>
      <p>

      </p>

      <ul>
        <li><strong>Alice:</strong> The admin page is restricted to the user with username
          admin (tmp pw: 123 for demo purposes). Admin page shows caculated chat statistics
          from user activity and conversation history. There is also an import data feature
          to migrate messages and users from existing conversations, specially from movie scripts.
          I have also created a notifications class and store for the @mention tagging feature;</li>
        <li><strong>Aubin:</strong> The Activity Feed is a webpage where you can view all
          activity happening across the site pertaining to you. It works by creating a list
          of all site Activity in reverse chronological order. It then serves all of those
          Activities to you. The Mentions page works similarly to the Activity Feed, however
          it differs because it only creates a list of messages. Then a message function is
          used to search for all the users mentioned (@) in the message. If you are mentioned,
          then the message, who mentioned you, and the conversation you were mentioned in
          appears on the Mentions page.</li>
        <li><strong>Tania:</strong> Designed and implemented profile pages for each user
          with an about me section to describe themselves and a section that lists all of
          their previously sent messages. About me sections are only editable by users themselves.
          After creating the profile pages, the chat room was modified so that all usernames led to
          that user's profile page, making it viewable by others. Moreover, a feature to detect the @
          symbol in user messages before it was sent was added in order to implement the ability to mention/tag
          other users in messages.
          </li>
        <li><strong>Tina:</strong> The chat layout is designed .</li>

      </ul>

    </div>
  </div>
</body>
</html>
