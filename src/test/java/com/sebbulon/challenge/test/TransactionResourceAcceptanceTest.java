package com.sebbulon.challenge.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.sebbulon.challenge.api.SumOfChildren;
import com.sebbulon.challenge.api.Transaction;
import com.sebbulon.challenge.service.StorageService;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import java.util.Arrays;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by sebastianweikart on 14/03/2016.
 * testing resource methods
 */
public class TransactionResourceAcceptanceTest {

    private static final StorageService dao = mock(StorageService.class);

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static final Transaction[] carTransactions = {
            new Transaction(100, 200.12, "car", null),
            new Transaction(101, 100, "car", 100L),
            new Transaction(105, -0.12, "car", 103L),
            new Transaction(106, 1.12, "car", 103L),
            new Transaction(107, 50.12, "car", 103L),
            new Transaction(108, 10.12, "car",123456789L),
            new Transaction(110, 100.12, "car", 123456789L)
    };


    private static final SumOfChildren sum = new SumOfChildren(-90.74);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransactionResource(dao))
            .build();


    @Before
    public void setUp() throws Exception {
        when(dao.getService()).thenReturn(Arrays.asList(MAPPER.readValue(fixture("fixtures/transactions.json"), Transaction[].class)));
    }

    @After
    public void tearDown() throws Exception {
        reset(dao);
    }

    @Test
    public void testFetchByType() throws Exception {

        List<Transaction> myCarTxs = resources.client().target("/transactionservice/types/car").request().get(new GenericType<ImmutableList<Transaction>>() {});

        assertThat(myCarTxs).usingFieldByFieldElementComparator().contains(carTransactions);

        verify(dao).getService();


    }

    @Test
    public void testSum() throws Exception {
        SumOfChildren mySum = resources.client().target("/transactionservice/sum/123456789").request().get(new GenericType<SumOfChildren>() {});

        assertThat(mySum.getSum()).isEqualTo(sum.getSum());

    }
}