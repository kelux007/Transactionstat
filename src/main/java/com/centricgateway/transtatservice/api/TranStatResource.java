package com.centricgateway.transtatservice.api;

import com.centricgateway.transtatservice.utility.Utils;
import com.centricgateway.transtatservice.dao.TransactionDAO;
import com.centricgateway.transtatservice.dao.TransactionDAOImpl;
import com.centricgateway.transtatservice.model.Statistics;
import com.centricgateway.transtatservice.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/v1")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class TranStatResource {

    Transaction transaction;
    Statistics statistics;
    Response.ResponseBuilder responseBuilder = null;
    TransactionDAO transactionDAO;


    //RECEIVES REQUEST TO GET STATISTICS
    @GET
    @Path("/statistics")
    public Response statistics() {
        transactionDAO = new TransactionDAOImpl();
        statistics = transactionDAO.getStat();
        //sets response to API request
        responseBuilder = Response.status(Response.Status.OK).entity(statistics);
        return responseBuilder.build();
    }

    //RECEIVES REQUEST TO ADD A NEW TRANSACTION
    @POST
    @Path("/transactions")
    public Response addTransactions(String request) {
        try {
            //VALIDATE THE JSON STRING
            boolean isValidJson = Utils.isJsonValid(request);
            if(isValidJson){
                ObjectMapper mapper = new ObjectMapper();
                transaction = mapper.readValue(request, Transaction.class);
                //CALLS ADD TRANSACTION INTERFACE
                transactionDAO = new TransactionDAOImpl();
                String status = transactionDAO.addTransaction(transaction);
                if(status.equals("201")){
                    responseBuilder = Response.status(Response.Status.CREATED).entity("");
                }else if(status.equals("422")){
                    responseBuilder = Response.status(Response.Status.NOT_ACCEPTABLE).entity("Unprocessable Entity");
                }else if(status.equals("204")){
                    responseBuilder = Response.status(Response.Status.NO_CONTENT).entity("");
                }
            }
        } catch (IOException e) {
            responseBuilder = Response.status(Response.Status.BAD_REQUEST).entity("");
            return responseBuilder.build();
        }

        return responseBuilder.build();
    }

    //RECEIVES REQUEST TO DELETE ALL TRANSACTIONS
    @DELETE
    @Path("/transactions")
    public Response deleteAllTransactions() {
        transactionDAO = new TransactionDAOImpl();
        boolean status = transactionDAO.deleteAllTransaction();
        responseBuilder = Response.status(Response.Status.NO_CONTENT).entity("");
        return responseBuilder.build();
    }

}