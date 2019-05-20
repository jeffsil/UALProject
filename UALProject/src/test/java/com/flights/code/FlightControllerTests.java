package com.flights.code;


import static org.junit.Assert.assertTrue;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTests {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    
    @Autowired    
    private MockMvc mockMvc;
    
   @Test
   @Order(1)
   public void findFlightByFlightNumberDeparture() throws Exception {
       String res = "[{\"flightNumber\": \"1160\", \"carrier\": \"UA\", \"origin\": \"IAH\", \"departure\": \"2018-01-31T12:05:00\", \"destination\": \"ORD\", \"arrival\": \"2018-01-31T14:38:00\", \"aircraft\": \"Boeing 737-800\", \"distance\": \"925\", \"travelTime\": \"02:41\", \"status\": \"Arrived at Gate\"}]";

       assertTrue(
               ResultMatcher.matchJsonArray(
                       mockMvc.perform(get("/flights?flightNumber=1160&departure=2018-01-31T12:05:00"))
                               .andExpect(status().isOk())
                               .andReturn()
                               .getResponse()
                               .getContentAsString(),
                       res,
                       true
               )
       );
   }

   @Test
   @Order(2)
   public void findFlightByOriginDestinationDeparture() throws Exception {
       String res = "[{\"flightNumber\": \"1256\", \"carrier\": \"UA\", \"origin\": \"IAH\", \"departure\": \"2018-01-31T14:22:00\", \"destination\": \"ORD\", \"arrival\": \"2018-01-31T17:01:00\", \"aircraft\": \"Boeing 737-800\", \"distance\": \"925\", \"travelTime\": \"02:41\", \"status\": \"On Time\"}]";

       assertTrue(
               ResultMatcher.matchJsonArray(
                       mockMvc.perform(get("/flights?origin=IAH&destination=ORD&departure=2018-01-31T14:22:00"))
                               .andExpect(status().isOk())
                               .andReturn()
                               .getResponse()
                               .getContentAsString(),
                       res,
                       true
               )
       );
   }

   @Test
   @Order(3)
   public void flightNotFoundTest() throws Exception {
       mockMvc.perform(  get("/flights?origin=XXX&destination=YYY&departure=ZZZ") )
               .andExpect(status().isNotFound());
   }
       
}
