package data;

public enum Languages {


        Русский("Онлайн-курсы", "Создавайте онлайн-курсы на Stepik"),
        English("Online courses", "Top categories");


        public final String descriptionFirst;
        public final String descriptionSecond;


        Languages(String descriptionFirst, String descriptionSecond) {
                this.descriptionFirst = descriptionFirst;
                this.descriptionSecond = descriptionSecond;

        }
}
