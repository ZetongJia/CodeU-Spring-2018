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

import codeu.model.data.Message;
import codeu.model.store.persistence.PersistentStorageAgent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.MessageStore;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.mindrot.jbcrypt.BCrypt;

/** Servlet class responsible for the profile page. */
public class ProfileServlet extends HttpServlet{

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /**
   * Set up state for handling login-related requests. This method is only called when running in a
   * server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * This function fires when a user requests the /profile URL. It simply forwards the request to
   * profile.jsp. Also adds username to the URL.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

      String requestUrl = request.getRequestURI();
      String username = requestUrl.substring("/user/".length());
      User user = userStore.getUser(username);
      UUID userId = user.getId();

      List<Message> messagesByUser = MessageStore.getInstance().getUserMessages(userId);

      if(messagesByUser.size()==0){
        request.setAttribute("error", "Don't be shy! Send a message.");
      }
      request.setAttribute("username", username);
      request.setAttribute("usermessages", messagesByUser);
    request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
  }

  /**
   * This function fires when a user goes on their profile page. It gets the username and password from
   * the submitted form data, checks for validity and if correct adds the username to the session so
   * we know the user is logged in.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

          String aboutme = request.getParameter("aboutme");
          request.getSession().setAttribute("aboutme", aboutme);
          String username = (String) request.getSession().getAttribute("user");

          if(username == null){
            response.sendRedirect("/login");
            return;
          }

          User user = userStore.getUser(username);
          String cleanedAboutMeContent = Jsoup.clean(aboutme, Whitelist.basic());
          user.setAboutMe(cleanedAboutMeContent);
          userStore.updateUser(user);

          if(user == null){
            response.sendRedirect("/login");
            return;
          }


    response.sendRedirect("/user/"+request.getSession().getAttribute("user"));
  }
}
