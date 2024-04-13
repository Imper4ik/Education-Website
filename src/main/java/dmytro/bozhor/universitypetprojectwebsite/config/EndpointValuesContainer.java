package dmytro.bozhor.universitypetprojectwebsite.config;

public interface EndpointValuesContainer {
    String C_PLUS_PLUS = "/c-plus-plus";
    String C_SHARP = "/c-sharp";
    String JAVA = "/java";
    String PYTHON = "/python";
    String HOME = "/home";
    String REGISTER = "/register";
    String LOGIN = "/login";

    static String getHtmlPageByName(String fileName) {
        return "%s.html".formatted(fileName);
    }

}
