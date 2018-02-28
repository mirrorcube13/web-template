package entity;

/**
 * Created by Andrey on 02.01.2017.
 */
public class Item implements Entity {
    private int id;
    private String name;
    private String description;
    private double price;
    private int remainingAmount;
    private String image;
    private ProductType productType;


    public String getproductType() {
        return this.productType.name();
    }

    public void setproductType(String productType) {
        this.productType = ProductType.valueOf(productType);
    }

    public Item(int id, String name, String description, double price, int remainingAmount, String image, String productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
        this.image = image;
        this.productType = ProductType.valueOf(productType);
    }

    public Item(String name, String description, double price, int remainingAmount, String image, String productType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
        this.image = image;
        this.productType = ProductType.valueOf(productType);
    }

    public Item(String name, String description, double price, int remainingAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return  false;
        }
        if(!(obj instanceof  Item)) {
            return false;
        }
        Item otherItem = (Item) obj;
        return name.equals(otherItem.getName())
                && description.equals(otherItem.getDescription()) && id == otherItem.getId();
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getDescription() + " " + getPrice()
                + " " + getRemainingAmount();
    }

    public void setId(int id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getId() {
        return id;
    }
}
