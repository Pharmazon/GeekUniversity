package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SessionDTO;
import ru.shcheglov.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@WebService
public interface ISessionEndpoint {

    @WebMethod
    SessionDTO openSession(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password
    );

    @WebMethod
    ResultDTO closeSession();

    @WebMethod
    UserDTO getUserFromSession();

}
