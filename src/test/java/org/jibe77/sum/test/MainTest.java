package org.jibe77.sum.test;

import org.jibe77.sum.Main;
import org.jibe77.sum.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jibe on 11/07/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class MainTest {

    @Test
    public void mainWithoutResultTest() {
        // set up
        Integer[] array = new Integer[] {5, 4, 3};

        // launch
        Main m = new Main();
        List<Result> returnValue = m.home(array);

        // check
        assertThat(returnValue).isNotNull();
        assertThat(returnValue).isEqualTo(new ArrayList<Integer[]>());
    }

    @Test
    public void mainWithResultTest() {
        // set up
        Integer[] array = new Integer[] {4, 5, -1, -2, -7, 2, -5, -3, -7, -3, 1};

        // launch
        Main m = new Main();
        List<Result> returnValue = m.home(array);

        // check
        assertThat(returnValue).isNotNull();
        assertThat(returnValue.size()).isEqualTo(5);
        assertThat(returnValue.get(0).toString()).isEqualTo("-7 2 5");
        assertThat(returnValue.get(1).toString()).isEqualTo("-5 1 4");
        assertThat(returnValue.get(2).toString()).isEqualTo("-3 -2 5");
        assertThat(returnValue.get(3).toString()).isEqualTo("-3 -1 4");
        assertThat(returnValue.get(4).toString()).isEqualTo("-3 1 2");
    }

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
    }
}
