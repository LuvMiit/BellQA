package org.example.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "avatarLinksNotUniqueProvider")
    public Iterator<Object[]> avatarLinksNotUniqueProvider(){
        List<Object[]> links= new ArrayList<>();
        links.add(new Object[]{List.of(
                "https://reqres.in/img/faces/7-image.jpg",
                "https://reqres.in/img/faces/7-image.jpg",
                "https://reqres.in/img/faces/8-image.jpg",
                "https://reqres.in/img/faces/9-image.jpg",
                "https://reqres.in/img/faces/1-image.jpg"

        )});
         return links.iterator();
    }
    @org.testng.annotations.DataProvider(name = "avatarLinksUniqueProvider")
    public Iterator<Object[]> avatarLinksUniqueProvider(){
        List<Object[]> links= new ArrayList<>();
        links.add(new Object[]{List.of(
                "https://reqres.in/img/faces/7-image.jpg",
                "https://reqres.in/img/faces/6-image.jpg",
                "https://reqres.in/img/faces/8-image.jpg",
                "https://reqres.in/img/faces/9-image.jpg",
                "https://reqres.in/img/faces/1-image.jpg"

        )});
        return links.iterator();
    }
}
