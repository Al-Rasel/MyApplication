package test.rasel.myapplication;

/**
 * Created by Rasel on 6/11/2017.
 */

public class CategoryModel {



    String CategoryId;
    String CategoryNameEng;

    public CategoryModel(String categoryId, String categoryNameEng) {
        CategoryId = categoryId;
        CategoryNameEng = categoryNameEng;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryNameEng() {
        return CategoryNameEng;
    }

    public void setCategoryNameEng(String categoryNameEng) {
        CategoryNameEng = categoryNameEng;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "CategoryId='" + CategoryId + '\'' +
                ", CategoryNameEng='" + CategoryNameEng + '\'' +
                '}';
    }
}
