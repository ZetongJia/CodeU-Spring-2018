package codeu.controller;

import codeu.model.data.Activity;
import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.ArrayList;
import java.lang.NullPointerException;

/** Servlet class responsible for the activity feed page. */
public class MentionServlet extends HttpServlet {

  /** Store class that gives access to Conversations. */
  private ConversationStore conversationStore;

  /** Store class that gives access to Messages. */
  private MessageStore messageStore;

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /** Set up state for handling requests. */
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
   * This function fires when a user navigates to the activity feed page. It gets all the conversation associated
   * with that user and fetches the messages in each Conversation in chronological order.
   * It then forwards to activityfeed.jsp for rendering.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException, NullPointerException {
    // The get function needs to know who the user is, and if one is not logged in, they need to.
    String username = (String) request.getSession().getAttribute("user");
    User user = userStore.getUser(username);
    if (user == null) {
      // user isn't logged in, redirect to login page
      System.out.println("User was null");
      response.sendRedirect("/login");
      return;
    }

    List<Conversation> conversations = conversationStore.getAllConversations();

    List<Message> messages = new ArrayList<>();

    List<Message> nonMentioned = new ArrayList<>();

    List<Activity> MentionList = new ArrayList<>();

    /**
     * this removes all conversations not associated with the user from the list
     * and adds meaages associated with all conversations to a list.
     */
    for (Conversation conversation : conversations) {
        for (UUID member : conversation.members) {
          if (!(user.getId().equals(member))) {
          conversations.remove(conversation);
        } else {
          messages.addAll(messageStore.getMessagesInConversation(conversation.getId()));
        }
      }
    }

    for (Message message : messages) {
      if(message.usersMentioned().size() != 0){
        //usersMentioned.addAll(message.usersMentioned());
        for (User userMention : message.usersMentioned()) {
          boolean mentioned = false;
          if(userMention != null){
            if(userMention.getId().equals(user.getId())) {
              mentioned = true;
            }

            if(!mentioned){
              nonMentioned.add(message);
            }
          }
        }
      } else {
        nonMentioned.add(message);
      }
    }

    messages.removeAll(nonMentioned);
    MentionList.addAll(messages);
    Collections.sort(MentionList);

    System.out.println("Mention List sorted");

    request.setAttribute("conversationStore", this.conversationStore);
    request.setAttribute("userStore", this.userStore);
    request.setAttribute("mentions", MentionList);
    request.getRequestDispatcher("/WEB-INF/view/mentions.jsp").forward(request, response);
  }
}
