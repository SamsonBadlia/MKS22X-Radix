import java.util.*;

public class Radix{

  public static void radixsort(int[] data){
    @SuppressWarnings({"unchecked" , "rawtypes"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < 20; i++){
      buckets[i] = new MyLinkedList<>();
    }
    MyLinkedList<Integer> list = new MyLinkedList<>();

    int max = 0;
    for (int i = 0; i < data.length; i++){
      if (Math.abs(data[i]) > Math.pow(10, max)) max = (int)Math.ceil(Math.log10(Math.abs(data[i])));
      list.add(data[i]);
    }

    int index = 0;
    while (index < max){
      while (list.size() > 0){
        int value = list.removeFront();
        int digit = (int)(value / Math.pow(10, index)) % 10;
        if (digit < 0) buckets[9 + digit].add(value);
        else buckets[10 + digit].add(value);
      }
      list = buckets[0];
      for (int i = 1; i < 20; i++){
        list.extend(buckets[i]);
      }
      for (int i = 0; i < 20; i++){
        buckets[i] = new MyLinkedList<>();
      }
      index++;
    }

    for (int i = 0; i < data.length; i++){
      data[i] = list.removeFront();
    }
    
  }


  public static void main(String[]args){
    System.out.println("Size\t\tlongest Value\tquick/builtin ratio ");
    int[]longest_LIST = {1000000000,500,10};
    for(int longest : longest_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*longest);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Radix.radixsort(data2);
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
        System.out.println(size +"\t\t"+longest+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
