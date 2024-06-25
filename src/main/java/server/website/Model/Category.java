package server.website.Model;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private static ArrayList<Category> categories = new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        categories.add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getCategoryNameById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category.getName();
            }
        }
        return "Unknown category";
    }

    public static int getCategoryIdByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category.getId();
            }
        }
        return -1;
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }
}
