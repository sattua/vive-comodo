package main.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String address;
    private Integer status; // 0 -created, 1- published, 2-disable, 3- taken

    @OneToMany(targetEntity=PublicationImage.class, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<PublicationImage> publicationImages;

    @OneToMany(targetEntity = PublicationAttribute.class, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<PublicationAttribute> publicationAttributes;

    public Publication() {}

    public Publication(String title, String description, String address) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.status = 0;
    }

    public Publication(String title, String description, String address, List<PublicationImage> images) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.status = 0;
        this.publicationImages = images;
    }

    public Publication(String title, String description, String address, Integer status, List<PublicationImage> publicationImages) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.status = status;
        this.publicationImages = publicationImages;
    }

    public Publication(String title, String description, String address, Integer status, List<PublicationImage> publicationImages, List<PublicationAttribute> publicationAttributes) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.status = status;
        this.publicationImages = publicationImages;
        this.publicationAttributes = publicationAttributes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PublicationImage> getImages() {
        return publicationImages;
    }

    public void setImages(List<PublicationImage> images) {
        this.publicationImages = images;
    }

    public List<PublicationImage> getPublicationImages() {
        return publicationImages;
    }

    public void setPublicationImages(List<PublicationImage> publicationImages) {
        this.publicationImages = publicationImages;
    }

    public List<PublicationAttribute> getPublicationAttributes() {
        return publicationAttributes;
    }

    public void setPublicationAttributes(List<PublicationAttribute> publicationAttributes) {
        this.publicationAttributes = publicationAttributes;
    }
}
