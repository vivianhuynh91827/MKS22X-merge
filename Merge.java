import java.util.*;

public class Merge {
  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[] data){
    int[] temp = new int[data.length];
    for (int i = 0; i < data.length; i++){
      temp[i] = data[i];
    }
    mergesort(data,temp,0, data.length-1);
  }

  public static void insertionsort(int[] data, int lo, int hi) {
    // if (lo < 0 || hi >= data.length || hi < lo) return;
    for (int i = lo + 1 ; i <= hi; i ++) {
      int cur = data[i];
      int x = i - 1;
      boolean sorted = false;
      while (!sorted) {
        if ( x < lo || data[x] < cur) {
          data[x + 1] = cur;
          sorted = true;
        }
        else {
          data[x + 1] = data[x];
          x--;
        }
      }
    }
  }
  //not optimized
  public static void mergesort(int[] data, int lo, int hi) {
    if (data.length <= 1) {
      return;
    }

    if (hi - lo < 43) { //if the size is less than 43, call insertion sort instead
      insertionsort(data,lo,hi);
      return;
    }
    int total = hi-lo; //length of section to sort
    int middle = (hi+lo)/2; //middle index
    int[] left = new int[middle-lo+1];
    int[] right = new int[hi-middle];
    for (int i = 0; i < left.length; i ++) {
      left[i]= data[lo+i]; //copy left side of the array
    }
    for (int i = 0; i < right.length; i ++) {
      right[i]= data[middle+i+1]; //copy right side of the array
    }
    // System.out.println(Arrays.toString(left));
    // System.out.println(Arrays.toString(right));
    // System.out.println("HI");
    mergesort(left, 0, left.length-1);
    mergesort(right, 0, right.length-1);
    int l = 0; //current index of left side
    int r = 0; //current index of right side
    for (int i = 0; i < data.length; i++) {
      if (l < left.length && r < right.length) { //Compare both values, add the smaller one
        if (left[l]<=right[r]) {
          data[i] = left[l];
          l++;
        }
        else if (right[r]<left[l]) {
          data[i] = right[r];
          r++;
        }
      }
      else if (l >= left.length && r < right.length) {
        data[i] = right[r];
        r++;
      }
      else if (l < left.length && r >= right.length) {
        data[i] = left[l];
        l++;
      }
    }
  }
//optimized
  private static void merge(int[] data, int[] temp, int lo1, int lo2, int hi1, int hi2) {
    int l = lo1;
    int r = hi1;
    for (int i = lo1; i <=hi2; i++) {
      if (r > hi2 || (l < lo2 && (temp[r] > temp[l]))) {
        data[i] = temp[l];
        l++;
      }
      else {
        data[i] = temp[r];
        r++;
      }
    }
  }
 //optimized
  public static void mergesort(int[] data, int[] temp, int lo, int hi) {
    if (hi - lo < 44) { //if the size is less than 43, call insertion sort instead
      insertionsort(data,lo,hi);
      return;
    }
    int middle = (hi+lo)/2; //middle index
    int lo1 = lo; //lower bound of left side
    int lo2 = middle; //upper bounds of left side
    int hi1 = middle+1; // lower bound of right side
    int hi2 = hi; //upper bound of right side
    // System.out.println(lo1 + ", " + lo2 + ", " + hi1 + ", " + hi2 );
    mergesort(temp, data, lo1, lo2);
    mergesort(temp, data, hi1, hi2);

    merge(data, temp, lo1, lo2, hi1, hi2);
  }

  // public static void main(String[] args) {
  //   // int[] test = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46};
  //   int[] test = {10,1,2,3,4,5,6,7,8,9,54,5,7,45,7,457,45,7};
  //   System.out.println(Arrays.toString(test));
  //   // mergesort(test);
  //   insertionsort(test,0,17);
  //   System.out.println(Arrays.toString(test));
  // }
  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tmerge /builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Merge.mergesort(data2, 0 ,data2.length-1);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
