package com.sebbulon.challenge.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbulon.challenge.api.Transaction;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sebastianweikart on 14/03/2016.
 *
 * test if the serialisation and de-serialisation of our data object works
 */
public class TransactionDAOTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Transaction tx = new Transaction(987654321, 200.12, "car", 123456789L);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/transaction.json"), Transaction.class));

        assertThat(MAPPER.writeValueAsString(tx)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Transaction tx = new Transaction(987654321, 200.12, "car", 123456789L);
        assertThat(MAPPER.readValue(fixture("fixtures/transaction.json"), Transaction.class))
                .isEqualTo(tx);
    }

}
