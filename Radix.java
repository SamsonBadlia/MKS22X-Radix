public class Radix{


  public static void radixsort( int[] data ) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] radix = new MyLinkedList[10];
  }

  private static int getDigit(int num, int digit){
    while ( digit > 1 ){
      num /= 10;
      digit--;
    }
    return num % 10;
  }

  public static int longest(int[] data){
    int max = Math.abs(data[0]); //starting reference
    for (int i = 1; i <= data.length - 1; i++){
      if (Math.abs(data[i]) >= max) max = Math.abs(data[i]); //if value at is greater, new largest
    }
    int digits = 0; //tracks number of digits for longest number
    while (max > 0){
      max /= 10;
      digits++;
    }
    return digits;
  }

  public static void main(String[] args) {
    int[] a = new int[]{1,2,-31};
    int[] b = new int[]{-11111, 10293,3, 46};
    int[] c = new int[]{ -121111 , 10293 , 3 , 46};
    System.out.println("Should be: 2 \nActual is:" + longest(a));
    System.out.println("Should be: 5 \nActual is:" + longest(b));
    System.out.println("Should be: 6 \nActual is:" + longest(c));

  }
}
