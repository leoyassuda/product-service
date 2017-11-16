package br.com.lny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 3762641811657211050L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_product_id")
    private Product parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> children;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

    /*Default constructor*/
    public Product() {}

    public Product(String name, String description, Product parent, Set<Product> children, Set<Image> images) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.children = children;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getParent() {
        return parent;
    }

    public void setParent(Product parent) {
        this.parent = parent;
    }

    public Set<Product> getChildren() {
        return children;
    }

    public void setChildren(Set<Product> children) {
        this.children = children;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", children=" + children +
                ", images=" + images +
                '}';
    }

}