package codeu.model.store.basic;

import codeu.model.data.Notification;
import codeu.model.store.persistence.PersistentStorageAgent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class NotificationStoreTest {

  private NotificationStore notificationStore;
  private PersistentStorageAgent mockPersistentStorageAgent;

  private final UUID CONVERSATION_ID_ONE = UUID.randomUUID();
  private final Notification NOTIFICATION_ONE =
      new Notification(
          UUID.randomUUID(),
          UUID.randomUUID(),
          CONVERSATION_ID_ONE,
          UUID.randomUUID(),
          Instant.ofEpochMilli(1000));
  private final Notification NOTIFICATION_TWO =
      new Notification(
          UUID.randomUUID(),
          UUID.randomUUID(),
          CONVERSATION_ID_ONE,
          UUID.randomUUID(),
          Instant.ofEpochMilli(2000));

  @Before
  public void setup() {
    mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
    notificationStore = NotificationStore.getTestInstance(mockPersistentStorageAgent);

    final List<Notification> notificationList = new ArrayList<>();
    notificationList.add(NOTIFICATION_ONE);
    notificationList.add(NOTIFICATION_TWO);
    notificationStore.setNotifications(notificationList);
  }

  @Test
  public void testGetNotificationsInConversation() {
    List<Notification> resultNotifications = notificationStore.getNotificationsInConversation(CONVERSATION_ID_ONE);

    Assert.assertEquals(2, resultNotifications.size());
    assertEquals(NOTIFICATION_ONE, resultNotifications.get(0));
    assertEquals(NOTIFICATION_TWO, resultNotifications.get(1));
  }

  @Test
  public void testAddNotification() {
    UUID inputConversationId = UUID.randomUUID();
    Notification inputNotification =
        new Notification(
            UUID.randomUUID(),
            UUID.randomUUID(),
            inputConversationId,
            UUID.randomUUID(),
            Instant.now());

    notificationStore.addNotification(inputNotification);
    Notification resultNotification = notificationStore.getNotificationsInConversation(inputConversationId).get(0);

    assertEquals(inputNotification, resultNotification);
    Mockito.verify(mockPersistentStorageAgent).writeThrough(inputNotification);
  }

  private void assertEquals(Notification expectedNotification, Notification actualNotification) {
    Assert.assertEquals(expectedNotification.getId(), actualNotification.getId());
    Assert.assertEquals(expectedNotification.getMessageId(), actualNotification.getMessageId());
    Assert.assertEquals(expectedNotification.getConversationId(), actualNotification.getConversationId());
    Assert.assertEquals(expectedNotification.getMentionedUserId(), actualNotification.getMentionedUserId());
    Assert.assertEquals(expectedNotification.getCreationTime(), actualNotification.getCreationTime());
  }
}
