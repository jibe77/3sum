package org.jibe77.sum;

import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * from https://www.reddit.com/r/dailyprogrammer/comments/6melen/20170710_challenge_323_easy_3sum/
 *
 * Description

 In computational complexity theory, the 3SUM problem asks if a given set of n real numbers contains three elements that sum to zero. A naive solution works in O(N2) time, and research efforts have been exploring the lower complexity bound for some time now.
 Input Example

 You will be given a list of integers, one set per line. Example:
 9 -6 -5 9 8 3 -4 8 1 7 -4 9 -9 1 9 -9 9 4 -6 -8
 Output Example

 Your program should emit triplets of numbers that sum to 0. Example:
 -9 1 8
 -8 1 7
 -5 -4 9
 -5 1 4
 -4 1 3
 -4 -4 8
 Challenge Input

 4 5 -1 -2 -7 2 -5 -3 -7 -3 1
 -1 -6 -3 -7 5 -8 2 -8 1
 -5 -1 -4 2 9 -9 -6 -1 -7
 Challenge Output

 -7 2 5
 -5 1 4
 -3 -2 5
 -3 -1 4
 -3 1 2

 -7 2 5
 -6 1 5
 -3 1 2

 -5 -4 9
 -1 -1 2
 */
@RestController
@EnableAutoConfiguration
public class Main {

    Logger logger = LoggerFactory.getLogger(Main.class);

    @RequestMapping(value = "/sum/{integers}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Result> home(@PathVariable("integers") Integer[] integers) {
        List<Result> results = new ArrayList<Result>();
        for (int i = 0 ; integers.length-i >= 2 ; i++) {
            logger.debug("Iterating over index " + i + " with value " + integers[i]);
            for (int j = (i + 1); j < integers.length; j++) {
                logger.debug("Interating over second integer on index " + j + " with value " + integers[j]);
                for (int k = (j + 1); k < integers.length; k++) {
                    logger.debug("Interating over third integer on index " + k + " with value " + integers[k]);
                    int result = integers[i] + integers[j] + integers[k];
                    if (result == 0) {
                        logger.info("add to result list " + integers[i] + " " + integers[j] + " " + integers[k] +
                                " because result is 0.");
                        addToResult(results, integers[i], integers[j], integers[k]);
                    } else {
                        logger.debug("ignore because != 0.");
                    }
                }
            }

        }
        Collections.sort(results);
        return results;
    }

    private void addToResult(List<Result> results, Integer integer, Integer integer1, Integer integer2) {
        Result result = new Result(integer, integer1, integer2);
        if (resultsArrayAlreadyExist(results, result) == false) {
            results.add(result);
        }
    }

    private boolean resultsArrayAlreadyExist(List<Result> results, Result result) {
        for (Result resultIteration : results) {
            if (resultIteration.toString().equals(result.toString())) {
                logger.info(result.toString() + " already found in result list, ignoring it then.");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
