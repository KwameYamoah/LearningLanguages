package com.company;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6,7}));
    }

        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int p1 = 0;
            int p2 = 0;
            int[] mergedArr = new int[nums1.length + nums2.length];
            boolean done = false;

            int mergedArrPos = 0;

            if(nums1.length == 0){
                if(nums2.length ==1){
                    return nums2[0];
                }

                double x = (double)(nums2.length + 1)/2;
                if(x==1 || x/2 == (int)x/2){
                    return nums2[(int)x-1];
                }
                else{
                    int c = (int)x -1;
                    return (double)(nums2[c] + nums2[c+1])/2;
                }
            }
            else if(nums2.length==0){
                if(nums1.length==1){
                    return nums1[0];
                }
                double x = (double)(nums1.length + 1)/2;
                if(x==1 || x/2 == (int)x/2){
                    return nums1[(int)x-1];
                }
                else{
                    int c = (int)x -1;
                    return (double)(nums1[c] + nums1[c+1])/2;
                }
            }
            while(!done){

                if(nums1[p1] < nums2[p2]){
                    mergedArr[mergedArrPos] = nums1[p1];
                    mergedArrPos++;
                    p1++;
                    if(p1 == nums1.length){
                        for(int i = p2; i < nums2.length; i++){
                            mergedArr[mergedArrPos] = nums2[i];
                            mergedArrPos++;
                        }
                        done = true;
                    }

                }
                else{
                    mergedArr[mergedArrPos] = nums2[p2];
                    mergedArrPos++;
                    p2++;
                    if(p2 == nums2.length){

                        for(int i = p1; i < nums1.length; i++){
                            mergedArr[mergedArrPos] = nums1[i];
                            mergedArrPos++;
                        }
                        done = true;
                    }
                }

            }

            double x = (double)(mergedArr.length + 1)/2;

            if(x==1 || (x/2 == (int)x/2)){
                return mergedArr[(int)x-1];
            }
            else{
                int c = (int)x-1;
                return (double)(mergedArr[c] + mergedArr[c+1])/2;
            }
        }
}
