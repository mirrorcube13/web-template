package service;

import com.google.gson.*;
import dao.ItemDao;
import dao.OrderDao;
import dto.ItemDto;
import entity.Item;
import entity.Order;

import java.util.*;

/**
 * Created by Andrey on 18.01.2017.
 */
public class ItemsService {
    private static ItemsService INSTANCE = null;

    private ItemsService() {
    }

    public static ItemsService getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemsService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemsService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Item> getAllItems() {
        return new ItemDao().getAll();
    }

    public List<Item> getAllItemsOrderBy(String order) {
        return new ItemDao().getAllOrderBy(order);
    }
    public List<Item> searchItems(String like, String order, String type) {
        return new ItemDao().searchItems(like, order, type);
    }

    public void insertItem(Item item) {
        ItemDao itemDao = new ItemDao();
        itemDao.insert(item);
    }

    public Set<Item> getItemsFromBag(List<Integer> ids) {
        Set<Item> itemSet = new HashSet<>();
        for (Integer id : ids) {
            itemSet.add(new ItemDao().getById(id));
        }
        if(itemSet.size() == 0){
            return null;
        } else {
            return itemSet;
        }
    }

    public Item getItemById(int id) {
        return new ItemDao().getById(id);
    }

    public  HashMap<Item, Integer> parseItems(String json) {
        HashMap<Item, Integer> hashMap = new HashMap<>();
        JsonParser parser = new JsonParser();
        JsonObject mainObject = (JsonObject) parser.parse(json);
        JsonArray pItem = mainObject.getAsJsonArray("items");

        for (JsonElement item : pItem) {
            JsonObject itemObject = item.getAsJsonObject();
            hashMap.put(getItemById(itemObject.get("id").getAsInt()), itemObject.get("amount").getAsInt());
        }
        return hashMap;
    }
}
