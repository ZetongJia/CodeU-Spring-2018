package codeu.model.data;

import java.time.Instant;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Abstract Class representing any site activity.
 * Extended by User, Conversation, and Activity.
 * Handles everything involving Instants/creation time.
 */
public class Activity implements Comparable<Activity>{
  protected final Instant creation;

  /**
   * Constructs an Activity object
   * @param creation the creation time of this Activity
   */
  public Activity(Instant creation){
    this.creation = creation;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns Instant time in MM/DD/YYYY HH:MM:SS timeFormat. */
  public String timeFormat() {
    Date myDate = Date.from(this.getCreationTime());
    SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
    String formattedDate = formatter.format(myDate);
    return formattedDate;
  }

  @Override
  /** Compares Activities based on their creation time.
    * Sorts latest first.
    */
  public int compareTo(Activity ActivityBoi) {
    return this.getCreationTime().compareTo(ActivityBoi.getCreationTime());
  }
}
