package org.example.solr;


import java.util.ArrayList;

public class Films {

    public String id;
    public ArrayList<String> directed_by;
    public String initial_release_date;
    public ArrayList<String> genre;
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getDirected_by() {
        return directed_by;
    }

    public void setDirected_by(ArrayList<String> directed_by) {
        this.directed_by = directed_by;
    }

    public String getInitial_release_date() {
        return initial_release_date;
    }

    public void setInitial_release_date(String initial_release_date) {
        this.initial_release_date = initial_release_date;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Films{" +
                "id='" + id + '\'' +
                ", directed_by=" + directed_by +
                ", initial_release_date='" + initial_release_date + '\'' +
                ", genre=" + genre +
                ", name='" + name + '\'' +
                '}';
    }
}
