package data;

public enum Language {

    CG("Kako kupiti?"),
    EN("How to buy?"),
    RU("Как купить?");


    public final String description;

    Language(String description) {
        this.description = description;
    }
}