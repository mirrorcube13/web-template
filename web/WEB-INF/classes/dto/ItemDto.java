package dto;

/**
 * Created by Andrey on 28.01.2017.
 */
public class ItemDto {
    private int id;
    private int amount;

    public ItemDto(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
