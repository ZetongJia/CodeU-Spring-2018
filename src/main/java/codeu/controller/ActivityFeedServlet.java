package codeu.controller;

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

/** Servlet class responsible for the activity feed page. */
public class ActivityFeedServlet extends HttpServlet {

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
      throws IOException, ServletException, PersistentDataStoreException {
    // The get function needs to know who the user is, aList<Conversation> conversations = conversationStore.getAllConversations();nd if one is not logged in, they need to.
    User user = userStore.getUser(username);
    if (user == null) {
      // couldn't find conversation, redirect to conversation list
      System.out.println("User was null");
      response.sendRedirect("/login");
      return;
    }

    List<Conversation> conversations = conversationStore.loadConversations();

    List<Message> messages = new ArrayList<>();

    /**
     * this removes all conversations not associated with the user from the list
     * and adds meaages associated with all conversations to a list.
     */
    for (Conversation conversation : conversations) {
        for (UUID member : conversation.members) {
          if (!(user.getId().equals(member))) {
          conversations.remove(new Conversation(conversation));
        } else {
          messages.add(messageStore.getMessagesInConversation(conversation.getId()));
        }
      }
    }

    Collections.sort(messages);

    request.getRequestDispatcher("/WEB-INF/view/activityfeed.jsp").forward(request, response);
  }
}
