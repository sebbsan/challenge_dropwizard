package com.sebbulon.challenge.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.sebbulon.challenge.api.Transaction;
import com.sebbulon.challenge.service.StorageService;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by sebastianweikart on 14/03/2016.
 * testing resource methods
 */
public class TransactionResourceTest {

    private static final StorageService dao = mock(StorageService.class);

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransactionResource(dao))
            .build();


    private final Transaction tx = new Transaction(987654321, 200.12, "car", 123456789L);
    private final Transaction txToPut = new Transaction(909090909, 200.12, "car", 123456789L);
    private List<Transaction> txList = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        txList.add(tx);
        when(dao.getService()).thenReturn(txList);
    }

    @After
    public void tearDown() throws Exception {
        reset(dao);
    }

    @Test
    public void testFetch() throws Exception {
        assertThat(resources.client().target("/transactionservice/transactions").request().get(new GenericType<ImmutableList<Transaction>>() {}))
                .isEqualTo(ImmutableList.of(tx));
        verify(dao).getService();
    }

    @Test
    public void testAdd() throws Exception {
        resources.client().target("/transactionservice/transactions/909090909").request().put(Entity.json(txToPut));
        verify(dao).getService();
        assertThat(resources.client().target("/transactionservice/transactions").request().get(new GenericType<ImmutableList<Transaction>>() {}))
               .isEqualTo(ImmutableList.of(tx, txToPut));
    }

}