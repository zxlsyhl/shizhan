import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] aa = {1,2,3,4,5,6,7,8,9};
        int[] bb = Arrays.copyOfRange(aa,1,3);
        System.out.println(bb);
    }
}
