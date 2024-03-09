package data;

public enum Language {

    CG("Kako kupiti?"),
    EN("How to buy?"),
    RU("Как купить?");

    private final String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}