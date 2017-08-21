public class IfShortHand {
    public static void main(String[] args) {
        int a = 4;
        int result = a == 4 ? 1 : 8;

		System.out.println(result);

        // result will be 1
        // This is equivalent to

        /*int result;

        if (a == 4) {
            result = 1;
        } else {
            result = 8;
        }*/


    }
}