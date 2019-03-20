import java.util.*;

public class Merge {
  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[] data){
    mergeSortH(data, 0, data.length-1);
  }

  public static void mergeSortH(int[] data, int lo, int hi) {
    if (data.length <= 1) {
      return;
    }
    int total = hi-lo;
    int middle = (hi+lo)/2;
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
    mergeSortH(left, 0, left.length-1);
    mergeSortH(right, 0, right.length-1);
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
  public static void main(String[] args) {
    int[] test = {10,9,8,7,6,5,4,3,2,1};
    System.out.println(Arrays.toString(test));
    mergeSortH(test,0,9);
    System.out.println(Arrays.toString(test));
  }
}
