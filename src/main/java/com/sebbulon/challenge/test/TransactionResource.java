package com.sebbulon.challenge.test;

import com.codahale.metrics.annotation.Timed;
import com.sebbulon.challenge.api.SumOfChildren;
import com.sebbulon.challenge.api.Transaction;
import com.sebbulon.challenge.service.StorageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * Created by sebastianweikart on 13/03/2016.
 *
 * This resource represents the interface for our REST service, delivering all necessary CRUD logic and business logic
 */

@Path("/transactionservice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private StorageService transactionStorage;

    public TransactionResource(StorageService storage) {
        this.transactionStorage = storage;

    }


    @GET
    @Timed
    @Path("/transactions")
    public List<Transaction> fetch() {
        return transactionStorage.getService();
    }

    @PUT
    @Timed
    @Path("transactions/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(@PathParam("id") Long id, Transaction tx) {
        transactionStorage.getService().add(tx);
        tx.setId(id);
        try {
            return Response.created(new URI("/" + tx.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GET
    @Timed
    @Path("/types/{type}")
    public Object[] fetchByType(@PathParam("type") String type) {
        // we use parallelStream in the anticipation of huge data volumes - the array will be filtered by our predicate in parallel threads
        return transactionStorage.getService().parallelStream().filter(t -> Objects.equals(t.getType(), type)).toArray();
    }

    @GET
    @Timed
    @Path("/sum/{parentId}")
    public SumOfChildren sumTransactionsOfChildren(@PathParam("parentId") Long parentId) {
        // map reduce function which again in anticipation of big data volumes is using parallel streams
        double sum = transactionStorage.getService().parallelStream().filter(t -> Objects.equals(t.getParentId(), parentId)).mapToDouble(Transaction::getAmount).sum();
        return new SumOfChildren(BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }


}
