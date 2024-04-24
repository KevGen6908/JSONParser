package org.example.ClassToParsed;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String director;
    private String[] actors;
    private ArrayList<String> genres;
    private int releaseYear;
    private boolean isAnimated;

    public Movie() {
        this.title = "";
        this.director = "";
        this.actors = new String[0];
        this.genres = new ArrayList<>();
        this.releaseYear = 0;
        this.isAnimated = false;
    }

    public Movie(String title, String director, String[] actors, ArrayList<String> genres, int releaseYear, boolean isAnimated) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.isAnimated = isAnimated;
    }

    @Override
    public String toString() {
        StringBuilder actorsStr = new StringBuilder("[");
        for (int i = 0; i < actors.length; i++) {
            actorsStr.append(actors[i]);
            if (i < actors.length - 1) {
                actorsStr.append(", ");
            }
        }
        actorsStr.append("]");

        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actorsStr.toString() +
                ", genres=" + genres +
                ", releaseYear=" + releaseYear +
                ", isAnimated=" + isAnimated +
                '}';
    }
}