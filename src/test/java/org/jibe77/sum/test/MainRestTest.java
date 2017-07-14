package org.jibe77.sum.test;

import org.jibe77.sum.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jibe on 11/07/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainRestTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private URL base;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/");
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void mainWithoutResultTest() throws Exception {
        this.mvc.perform(
                get("/sum/5,4,3"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void mainWithResultTest() throws Exception {
        this.mvc.perform(
                get("/sum/4,5,1,-2,-7,2,-5,-3,-7,-3,1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"i\":-7,\"j\":2,\"k\":5}," +
                                "{\"i\":-5,\"j\":1,\"k\":4}," +
                                "{\"i\":-3,\"j\":-2,\"k\":5}," +
                                "{\"i\":-3,\"j\":1,\"k\":2}," +
                                "{\"i\":-2,\"j\":1,\"k\":1}]")));
    }

    @Test
    public void mainWithResult2Test() throws Exception {
        this.mvc.perform(
                get("/sum/-1,-6,-3,-7,5,-8,2,-8,1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"i\":-7,\"j\":2,\"k\":5}," +
                                "{\"i\":-6,\"j\":1,\"k\":5}," +
                                "{\"i\":-3,\"j\":1,\"k\":2}]")));
    }

    @Test
    public void mainWithResult3Test() throws Exception {
        this.mvc.perform(
                get("/sum/-5,-1,-4,2,9,-9,-6,-1,-7"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                        "[{\"i\":-5,\"j\":-4,\"k\":9}," +
                                "{\"i\":-1,\"j\":-1,\"k\":2}]")));
    }
}
