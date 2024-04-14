package dmytro.bozhor.universitypetprojectwebsite.config;

import dmytro.bozhor.universitypetprojectwebsite.util.EndpointValuesContainer;

public interface SecurityUtil {

    String USERNAME_PARAMETER = "email";
    String PASSWORD_PARAMETER = "password";
    String FAILURE_URL = EndpointValuesContainer.LOGIN + "?error";

}
