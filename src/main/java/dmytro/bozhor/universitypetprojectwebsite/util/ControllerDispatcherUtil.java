package dmytro.bozhor.universitypetprojectwebsite.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerDispatcherUtil {

    public static String sendRedirect(String endpoint) {
        return "redirect:%s".formatted(endpoint);
    }

    public static String sendRedirectWithError(String endpoint) {
        return "redirect:%s?error".formatted(endpoint);
    }

}
