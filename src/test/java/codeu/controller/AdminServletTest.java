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
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AdminServletTest {

  private AdminServlet adminServlet;
  private HttpServletRequest mockRequest;
  private HttpSession mockSession;
  private HttpServletResponse mockResponse;
  private RequestDispatcher mockRequestDispatcher;
  private ConversationStore mockConversationStore;
  private MessageStore mockMessageStore;
  private UserStore mockUserStore;

  @Before
  public void setup() {
    adminServlet = new AdminServlet();

    mockRequest = Mockito.mock(HttpServletRequest.class);
    mockSession = Mockito.mock(HttpSession.class);
    Mockito.when(mockRequest.getSession()).thenReturn(mockSession);

    mockResponse = Mockito.mock(HttpServletResponse.class);
    mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
    Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/admin.jsp"))
        .thenReturn(mockRequestDispatcher);

    mockConversationStore = Mockito.mock(ConversationStore.class);
    adminServlet.setConversationStore(mockConversationStore);

    mockMessageStore = Mockito.mock(MessageStore.class);
    adminServlet.setMessageStore(mockMessageStore);

    mockUserStore = Mockito.mock(UserStore.class);
    adminServlet.setUserStore(mockUserStore);
  }

  @Test
  public void testDoGet() throws IOException, ServletException {

    Mockito.when(mockSession.getAttribute("user")).thenReturn("admin");

    User fakeUser =
    new User(
        UUID.randomUUID(),
        "admin",
        "$2a$10$bBiLUAVmUFK6Iwg5rmpBUOIBW6rIMhU1eKfi3KR60V9UXaYTwPfHy",
        Instant.now(),
        "",
        true);
    Mockito.when(mockUserStore.getUser("admin")).thenReturn(fakeUser);

    adminServlet.doGet(mockRequest, mockResponse);
    Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }

  @Test
  public void testDoGet_UserNotLoggedIn() throws IOException, ServletException {

    Mockito.when(mockSession.getAttribute("user")).thenReturn(null);
    adminServlet.doGet(mockRequest, mockResponse);
    Mockito.verify(mockResponse).sendRedirect("/login");
  }

  @Test
  public void testDoGet_InvalidUser() throws IOException, ServletException {
    Mockito.when(mockSession.getAttribute("user")).thenReturn("not_admin");

    User fakeUser =
        new User(
            UUID.randomUUID(),
            "not_admin",
            "$2a$10$bBiLUAVmUFK6Iwg5rmpBUOIBW6rIMhU1eKfi3KR60V9UXaYTwPfHy",
            Instant.now(),
            "",
            false);
    Mockito.when(mockUserStore.getUser("not_admin")).thenReturn(fakeUser);

    adminServlet.doGet(mockRequest, mockResponse);
    Mockito.verify(mockResponse).sendRedirect("/login");
  }

  @Test
  public void testDoPost() throws IOException, ServletException{

    Mockito.when(mockRequest.getParameter("script")).thenReturn("Sample.txt");

    User fakeUser =
    new User(
        UUID.randomUUID(),
        "admin",
        "$2a$10$bBiLUAVmUFK6Iwg5rmpBUOIBW6rIMhU1eKfi3KR60V9UXaYTwPfHy",
        Instant.now(),
        "",
        true);
    Mockito.when(mockUserStore.getUser("admin")).thenReturn(fakeUser);

    UUID fakeConversationId = UUID.randomUUID();
    Conversation fakeConversation =
        new Conversation(UUID.randomUUID(), UUID.randomUUID(), "Sample.txt", Instant.now());
    Mockito.when(mockConversationStore.getConversationWithTitle("Sample.txt"))
        .thenReturn(fakeConversation);

    User fakeUser1 =
        new User(
            UUID.randomUUID(),
            "ALICE (bot)",
            "",
            Instant.now(),
            "",
            false);
    Mockito.when(mockUserStore.getUser("ALICE (bot)")).thenReturn(fakeUser1);

    User fakeUser2 =
        new User(
            UUID.randomUUID(),
            "AMNA (bot)",
            "",
            Instant.now(),
            "",
            false);
    Mockito.when(mockUserStore.getUser("AMNA (bot)")).thenReturn(fakeUser2);

    List<Message> fakeMessageList = new ArrayList<>();
    fakeMessageList.add(
        new Message(
            UUID.randomUUID(),
            fakeConversationId,
            fakeUser1.getId(),
            "a",
            "unread",
            Instant.now()));

    fakeMessageList.add(
        new Message(
            UUID.randomUUID(),
            fakeConversationId,
            fakeUser2.getId(),
            "b",
            "unread",
            Instant.now()));

    Mockito.when(mockMessageStore.getMessagesInConversation(fakeConversationId))
        .thenReturn(fakeMessageList);

    adminServlet.doPost(mockRequest, mockResponse);

    Mockito.verify(mockResponse).sendRedirect("/admin");

  }

}
