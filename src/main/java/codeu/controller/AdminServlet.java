// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;


import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.data.Script;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet class responsible for the admin page. */
public class AdminServlet extends HttpServlet {

  /** Store class that gives access to Conversations. */
  private ConversationStore conversationStore;

  /** Store class that gives access to Messages. */
  private MessageStore messageStore;

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /**
   * Set up state for handling login-related requests. This method is only called when running in a
   * server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setConversationStore(ConversationStore.getInstance());
    setMessageStore(MessageStore.getInstance());
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the ConversationStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  /**
   * Sets the MessageStore used by this servlet. This function provides a common setup method for
   * use by the test framework or the servlet's init() function.
   */
  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * This function fires when a user requests the /admin URL. It simply forwards the request to
   * admin.jsp.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String username = (String) request.getSession().getAttribute("user");
    if (username == null || !username.equals("admin")) {
      // user is not logged in or is not admin, restrict page view
      response.sendRedirect("/login");
      return;
    }

    request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
  }

   /**
   * This function fires when a user submits the admin form for scripts. It gets the script value (movie scene) from
   * the submitted form data to pre-populate a conversation with messages (dialogue) by pre-registered users (characters).
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String theme = request.getParameter("script");
    Script script = new Script(theme);

    try {
      BufferedReader file = new BufferedReader(new FileReader(script.getPath()));
      String line;

      //creating new conversation
      Conversation conversation =
              new Conversation(UUID.randomUUID(), userStore.getUser("admin").getId(), theme, Instant.now());
      conversationStore.addConversation(conversation);

      while ((line = file.readLine()) != null) {

        String arr[] = line.split(":\\s", 2);

        //postfix " (bot)" is reserved for system generated users (characters)
        //these bots are not able to log in as normal users
        String character = arr[0] + " (bot)", dialogue = arr[1];

        //creating new user (character) with no password
        if (!userStore.isUserRegistered(character)) {
         User user = new User(UUID.randomUUID(), character, "", Instant.now(), false);
         userStore.addUser(user);
        }

        //creating new message
        Message message =
            new Message(
                UUID.randomUUID(),
                conversation.getId(),
                userStore.getUser(character).getId(),
                dialogue,
                Instant.now());
        messageStore.addMessage(message);
      }

    }catch(IOException e) {
      e.printStackTrace();
    }
    
    response.sendRedirect("/admin");
  }

}

