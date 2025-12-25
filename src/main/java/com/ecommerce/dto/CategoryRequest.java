package com.ecommerce.dto;


public class CategoryRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRequest(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }

}
