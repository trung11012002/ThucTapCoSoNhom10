/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Cart extends Item {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItem() {
        return items;
    }

    public Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getProduct_id() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getProduct_id()) != null) {
            Item m = getItemById(t.getProduct().getProduct_id());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getProduct_id() == id) {
                return i;
            }
        }
        return null;
    }

    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                txt = txt.trim();
                String[] s = txt.split("&");
                for (String i : s) {
                    i = i.trim();
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t = new Item(p, quantity, p.getPrice());
                    addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String txt = " 1:1          ,2:2,3:3";
        txt = txt.trim();
        if (txt != null && txt.length() != 0) {
            String[] s = txt.split(",");
            for (String i : s) {
                i = i.trim();
                String kq [] = i.split(":");
                int id = Integer.parseInt(kq[0]);
                int quantity = Integer.parseInt(kq[1]);
                System.out.println(id +" "+ quantity);
            }
        }

    }
}
