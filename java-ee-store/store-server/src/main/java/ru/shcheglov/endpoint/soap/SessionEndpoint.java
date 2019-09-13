package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SessionDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.dto.UserDTO;
import ru.shcheglov.service.SessionService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @Inject
    private SessionService sessionService;

    private SessionDTO sessionDTO;

    @Override
    @WebMethod
    public SessionDTO openSession(
            @WebParam(name = "login", partName = "login") final String login,
            @WebParam(name = "password", partName = "password") final String password
    ) {
        final SessionDTO opened = sessionService.open(login, password);
        this.sessionDTO = opened;
        return opened;
    }

    @Override
    @WebMethod
    public ResultDTO closeSession() {
        sessionService.closeAll(sessionDTO);
        return new SuccessDTO();
    }

    @Override
    @WebMethod
    public UserDTO getUserFromSession() {
        return sessionService.getUser(sessionDTO);
    }
}
