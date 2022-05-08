package services;

import model.Message;

import javax.ws.rs.core.Response;

public class ResponsesServices {

    public ResponsesServices(){}

    public Response successfully(){
        return Response
                .ok(new Message("Operacion exitosa"))
                .header("Content-Type", "application/json")
                .build();
    }

    public Response unsuccessfully(){
        return Response
                .status(500)
                .entity(new Message("Operacion fallida"))
                .header("Content-Type", "application/json")
                .build();
    }
}
