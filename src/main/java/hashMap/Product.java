package hashMap;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private final int id;
    private final String title;

    public Product(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id && Objects.equals(title, product.title);
    }

    @Override
    public String toString() {
        return "hashMap.Product{" + "id=" + id + ", title='" + title + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public int compareTo(Product p) {
        return this.title.compareTo(p.title);
    }
}
