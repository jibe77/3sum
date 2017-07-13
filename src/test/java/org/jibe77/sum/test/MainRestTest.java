package org.jibe77.sum.test;

import org.jibe77.sum.Main;
import org.jibe77.sum.Result;
import org.junit.Assert;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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
                .andExpect(content().string(equalTo("[{\"i\":-7,\"j\":2,\"k\":5},{\"i\":-5,\"j\":1,\"k\":4},{\"i\":-3,\"j\":-2,\"k\":5},{\"i\":-3,\"j\":1,\"k\":2},{\"i\":-2,\"j\":1,\"k\":1}]")));
    }
/*
    @Test
    public void mainWithResult2Test() {
        // set up
        Integer[] array = new Integer[] {-1, -6, -3, -7, 5, -8, 2, -8, 1};

        // launch
        Main m = new Main();
        List<Result> returnValue = m.home(array);

        // check
        assertThat(returnValue).isNotNull();
        assertThat(returnValue.size()).isEqualTo(3);
        assertThat(returnValue.get(0).toString()).isEqualTo("-7 2 5");
        assertThat(returnValue.get(1).toString()).isEqualTo("-6 1 5");
        assertThat(returnValue.get(2).toString()).isEqualTo("-3 1 2");
    }

    @Test
    public void mainWithResult3Test() {
        // set up
        Integer[] array = new Integer[] {-5, -1, -4, 2, 9, -9, -6, -1, -7};

        // launch
        Main m = new Main();
        List<Result> returnValue = m.home(array);

        // check
        assertThat(returnValue).isNotNull();
        assertThat(returnValue.size()).isEqualTo(2);
        assertThat(returnValue.get(0).toString()).isEqualTo("-5 -4 9");
        assertThat(returnValue.get(1).toString()).isEqualTo("-1 -1 2");
    }*/
}
