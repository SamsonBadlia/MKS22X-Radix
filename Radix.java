import java.util.*;

public class Radix{

  public static void radixsort( int[] data ) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < 20; i++){
      if (buckets[i] == null) buckets[i] = new MyLinkedList<Integer>(); //ensures everuything is instantiated
    }
    int digits = longest(data); //finds how many passes are needed
    for (int i = 1; i <= digits; i++){
      for (int j = 0; j < data.length; i++){
        //getsDigit at that place and then throws into correct
        int digitAtPlace = getDigit(data[i], i);
        if ( data[j] >= 0 ) buckets[ digitAtPlace + 10 ].add( data[j] );
        else buckets[ 9 - digitAtPlace ].add( data[j] );

        int counter = 0;
        for(int x = 0; j < 20; x++){
          int s = buckets[x].size();
          for(int k = 1; k <= s ; k++){
            int temp = buckets[x].removeFront();
            data[counter] = temp;
            counter++;
          }
        }

      }
    }

  }

  public static int getDigit(int num, int i){
    if ( num < 0) num *= -1;
    int counter =0;
    while(num > 0){
      num = num / 10;
      counter++;
    }
    if ( i > counter) return 0;
    if ( num < 0) num *= -1;
    while( i > 1){
      num /= 10;
      i--;
    }
    return num % 10;
  }

  public static int longest(int[] data){
    int max = Math.abs(data[0]); //starting reference
    for (int i = 1; i < data.length; i++){
      if (Math.abs(data[i]) >= max) max = Math.abs(data[i]); //if value at is greater, new largest
    }
    int digits = 0; //tracks number of digits for longest number
    while (max > 0){
      max /= 10;
      digits++;
    }
    return digits;
  }


  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
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
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
