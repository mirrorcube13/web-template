package dto;

import entity.Item;
import entity.Order;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andrey on 26.01.2017.
 */
public class ShoppingBagDto {
    private List<Integer> ids = new ArrayList<Integer>();

    public void addItem(int id) {
        ids.add(id);
    }

    public  List<Integer> getIds() {
        return new ArrayList<Integer>(ids);
    }

    public static ShoppingBagDto get(HttpSession session) {
        ShoppingBagDto bag = (ShoppingBagDto) session.getAttribute("bag");
        if (bag == null) {
            bag = new ShoppingBagDto();
            session.setAttribute("bag", bag);
        }
        return bag;
    }

}
