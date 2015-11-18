package main.entities;


import java.util.LinkedList;
import java.util.List;

public class Product {
    int pId;
    String name;
    double price;
    double weight;
    Manufacturer manufacturer;
    int categoryId;

    public Product(){}

    public Product(int id, String name,double price,double weight,Manufacturer man,int category){

        this.pId = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = man;
        this.categoryId = category;
    }

    public Product(String name,double price,double weight,Manufacturer man,int category){

        this.pId = 0;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = man;
        this.categoryId = category;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public enum Manufacturer{
        RUSSIA("Russia"),USA("USA"),CHINA("China"),FRANCE("France"),INDIA("India"),ITALY("Italy"),JAPAN("Japan"),GERMANY("Germany"),ENGLAND("England");
        String country;
        Manufacturer(String country){
            this.country = country;
        }
        public String getCountry() {
            return country;
        }
    }
    public static String[] getManufacturers(){
        List<String> values = new LinkedList<>();
        for (Manufacturer m : Manufacturer.values()){
            values.add(m.getCountry());
        }
        String [] vals = new String[values.size()];
        int i = 0;
        for (String v : values){
            vals[i] = v;
            i++;
        }
        return  vals;
    }
    public static Manufacturer getManufacturer(String country){
        for (Product.Manufacturer m : Product.Manufacturer.values()){
            if (m.getCountry().equals(country)){
                return m;
            }
        }
        return null;
    }
}
