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

}
