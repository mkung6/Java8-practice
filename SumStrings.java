import java.util.*;

class SumStrings {
    public static void main(String[] args) {
        String str1 = "0.9";
        String str2 = "999";
        System.out.println(sumStrings(str1, str2));
    }

    // public static int calculate(String str1, String str2) {
    //     char[] c1 = str1.toCharArray();
    //     char[] c2 = str2.toCharArray();
    //     int c1DecimalPlace = decimalPlace(c1);
    //     int c2DecimalPlace = decimalPlace(c2);
    //     int decimalSum = 0;
    //     int sum = 0;
    //     if (c1DecimalPlace != -1 && c2DecimalPlace != -1) {
    //         decimalSum = sumStrings(str1.substring(c1DecimalPlace + 1).toCharArray(), str2.sustring(c2DecimalPlace + 1).toCharArray());
    //         sum = sumStrings(str1.substring(0, c1DecimalPlace - 1).toCharArray(), str2.substring(0, c2DecimalPlace - 1).toCharArray());
    //     }
    // }

    // return the index of decimal place
    public static int decimalPlace(char[] c) {
        for(int i = 0; i < c.length; i++) {
            if(c[i] == '.') {
                return i;
            }
        }
        return -1;
    }

    public static boolean isNegative(char[] c) {
        if(c.length >= 1 && c[0] == '-') {
            return true;
        }
        return false;
    }

    public static int sumStrings(char[] c1, char[] c2) {
        int i = c1.length - 1;
        int j = c2.length - 1;
        int sum = 0;
        int carryOver = 0;
        int tens = 1;
        while (i >= 0 || j >= 0) {
            int num1 = 0;
            int num2 = 0;
            int sumDigit = 0;
            // convert to number
            if (i >= 0) {
                num1 = c1[i] - '0';
            }
            if (j >= 0) {
                num2 = c2[j] - '0';
            }
            System.out.println("num1: " + num1);
            System.out.println("num2: " + num2);
            sumDigit = (num1 + num2 + carryOver);
            System.out.println("sumDigit: " + sumDigit);
            // edge case when str1 and str2 are just one digit
            // then it'd calculate the carry over but not add it to our sum
            // as carryOver would never get evaluated a second time
            // carry over value also has to scale with tens to calculate
            // correct value
            if (sumDigit > 9 && c1.length > 1 && c2.length > 1) {
                System.out.println("inside if");
                carryOver = sumDigit / (tens * 10);
                sumDigit = sumDigit % (tens * 10);
            }
            else {
                carryOver = 0;
            }
            System.out.println("carryOver: " + carryOver);
            sumDigit *= tens;
            sum += sumDigit;
            System.out.println("sum: " + sum);
            tens *= 10;
            j--;
            i--;
        }
        return sum;
    }

    // public static int getNum(Character c) {
    //     switch(c) {
    //         case '1':
    //             return 1;
    //         case '2':
    //             return 2;
    //         case '3':
    //             return 3;
    //         case '4':
    //             return 4;
    //         case '5':
    //             return 5;
    //         case '6':
    //             return 6;
    //         case '7':
    //             return 7;
    //         case '8':
    //             return 8;
    //         case '9':
    //             return 9;
    //         case '0':
    //             return 0;
    //     }
    //     return -1;
    // }
}
