package dmytro.bozhor.universitypetprojectwebsite.util;

public interface EndpointValuesContainer {
    String EMPTY_PATH = "/";
    String C_PLUS_PLUS = "/c-plus-plus";
    String C_SHARP = "/c-sharp";
    String JAVA = "/java";
    String PYTHON = "/python";
    String HOME = "/home";
    String REGISTER = "/register";
    String LOGIN = "/login";
    String LOGOUT = "/logout";

    static String getHtmlPageByName(String fileName) {
        return "%s.html".formatted(fileName);
    }

}
