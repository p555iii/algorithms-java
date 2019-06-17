package com.cyd.project.algorithms.recursion;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumNum {
    @Test
    public void test(){
        int[] arr = new int[100];
        IntStream.range(0,100).forEach(num->{
            arr[num] = num;
        });
        int sum = 0;
        for(int i = 0;i < arr.length;i++){
            sum+=i;
        }
        System.out.println(sum);
        System.out.println(sum(arr,0));
    }

    public int sum(int[] arr,int index){
        if(arr.length == index) {
            return 0;
        }
        return sum(arr,index+1)+arr[index];
    }
}
