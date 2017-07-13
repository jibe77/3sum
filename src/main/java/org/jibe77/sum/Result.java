package org.jibe77.sum;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jibe on 12/07/2017.
 */
public class Result implements Comparable<Result> {

    private Integer i, j, k;

    public Result(int i, int j, int k) {
        Integer[] results = new Integer[] {i, j, k};
        Arrays.sort(results);
        this.i = results[0];
        this.j = results[1];
        this.k = results[2];
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return  i +
                " " + j +
                " " + k;
    }

    @Override
    public int compareTo(Result o) {
        if (i == o.i) {
            if (j == o.j) {
                return k.compareTo(o.k);
            } else {
                return j.compareTo(o.j);
            }
        } else {
            return i.compareTo(o.i);
        }
    }
}
