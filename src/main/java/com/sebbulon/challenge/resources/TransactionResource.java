package com.sebbulon.challenge.resources;

import com.codahale.metrics.annotation.Timed;
import com.sebbulon.challenge.api.SumOfChildren;
import com.sebbulon.challenge.api.Transaction;
import com.sebbulon.challenge.service.StorageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * Created by sebastianweikart on 13/03/2016.
 */

@Path("/transactionservice/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private List<Transaction> transactionStorage;

    public TransactionResource(StorageService storage) {
        this.transactionStorage = storage.getService();

    }


    @GET
    @Timed
    public List<Transaction> fetch() {
        return transactionStorage;
    }

    @PUT
    @Timed
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(@PathParam("id") Long id, Transaction tx) {
        transactionStorage.add(tx);
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
        return transactionStorage.parallelStream().filter(t -> Objects.equals(t.getType(), type)).toArray();
    }

    @GET
    @Timed
    @Path("/sum/{parentId}")
    public SumOfChildren sumTransactionsOfChildren(@PathParam("parentId") long parentId) {
        // map reduce function which again in anticipation of big data volumes is using parallel streams
        double sum = transactionStorage.parallelStream().filter(t -> t.getParentId() == parentId).mapToDouble(Transaction::getAmount).sum();
        return new SumOfChildren(sum);
    }


}
