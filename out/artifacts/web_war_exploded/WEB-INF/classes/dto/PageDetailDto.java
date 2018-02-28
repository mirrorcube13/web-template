package dto;

/**
 * Created by Andrey on 23.03.2017.
 */
public class PageDetailDto {
    private String sort;
    private String productType;
    private String like;

    public PageDetailDto() {
        sort = "id";
        like = "";
    }

    public PageDetailDto(String sort, String productType, String like) {
        this.sort = sort;
        this.productType = productType;
        this.like = like;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
