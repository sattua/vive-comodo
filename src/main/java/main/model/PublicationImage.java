package main.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PublicationImage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String url;
    private Integer listOrder;

    public PublicationImage(){

    }

    public PublicationImage(String url, Integer order) {
        this.url = url;
        this.listOrder = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }
}
