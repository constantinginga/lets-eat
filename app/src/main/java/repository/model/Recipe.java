package repository.model;

public class Recipe {
    private final int id;
    private final String name;
    private final String img;

    public Recipe(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }
}
