package net.tnemc.item.data;

import net.tnemc.item.JSONHelper;
import net.tnemc.item.SerialItemData;
import org.json.simple.JSONObject;

import java.util.UUID;

public abstract class SkullData<T> implements SerialItemData<T> {

  private String owner;

  /**
   * Converts the {@link SerialItemData} to a JSON object.
   *
   * @return The JSONObject representing this {@link SerialItemData}.
   */
  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.put("name", "skull");
    if(owner != null) json.put("owner", owner);
    return json;
  }

  /**
   * Reads JSON data and converts it back to a {@link SerialItemData} object.
   *
   * @param json The JSONHelper instance of the json data.
   */
  @Override
  public void readJSON(JSONHelper json) {
    if(json.has("owner")) owner = json.getString("owner");
  }

  /**
   * Used to determine if some data is equal to this data. This means that it has to be an exact copy
   * of this data. For instance, book copies will return false when compared to the original.
   *
   * @param data The data to compare.
   *
   * @return True if similar, otherwise false.
   */
  @Override
  public boolean equals(SerialItemData<? extends T> data) {
    if(data instanceof SkullData) {
      SkullData<?> compare = (SkullData<?>)data;
      return owner.equalsIgnoreCase(compare.owner);
    }
    return false;
  }

  /**
   * Used to determine if some data is similar to this data. This means that it doesn't have to be a
   * strict equals. For instance, book copies would return true when compared to the original, etc.
   *
   * @param data The data to compare.
   *
   * @return True if similar, otherwise false.
   */
  @Override
  public boolean similar(SerialItemData<? extends T> data) {
    return equals(data);
  }
}