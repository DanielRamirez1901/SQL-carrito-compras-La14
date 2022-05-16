package services;

import model.Message;

public class Response {

    public Response(){}

    public javax.ws.rs.core.Response successfully(){
        return javax.ws.rs.core.Response
                .ok(new Message("Operacion exitosa"))
                .header("Content-Type", "application/json")
                .build();
    }

    public javax.ws.rs.core.Response unsuccessfully(){
        return javax.ws.rs.core.Response
                .status(500)
                .entity(new Message("Operacion fallida"))
                .header("Content-Type", "application/json")
                .build();
    }
}
