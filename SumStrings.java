import java.util.*;
import java.lang.*;

class SumStrings {
    public static void main(String[] args) {
        String str1 = "5.1";
        String str2 = "999.91";
        System.out.println(sumStrings(str1, str2));
    }

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

    public static double calculateDecimal(char[] c1, char[] c2) {
        int i = c1.length - 1;
        int j = c2.length - 1;
        double tens = 0.1;
        double sum = 0;
        double carryOver = 0;
        if(i > j) {
            for(int k = 0; k < i; k++) {
                tens = tens / 10;
            }
        }
        else {
            for(int k = 0; k < j; k++) {
                tens = tens / 10;
            }
        }
        while (i >= 0 || j >= 0) {
            double sumDigit = 0;
            if (i > j && i >= 0) {
                sumDigit = (c1[i] - '0');
                sumDigit *= tens;
                sum += sumDigit;
                tens *= 10;
                i--;
            }
            else if (i < j && j >= 0) {
                sumDigit = (c2[j] - '0');
                sumDigit *= tens;
                sum += sumDigit;
                tens *= 10;
                j--;
            }
            else {
                double num1 = 0;
                double num2 = 0;
                if (i >= 0) {
                    num1 = c1[i] - '0';
                }
                if (j >= 0) {
                    num2 = c2[j] - '0';
                }
                sumDigit = (num1 + num2 + carryOver);
                if (sumDigit > 9 && c1.length > 1 && c2.length > 1) {
                    carryOver = sumDigit / (tens * 10);
                    sumDigit = sumDigit % (tens * 10);
                }
                else {
                    carryOver = 0;
                }
                sumDigit *= tens;
                sum += sumDigit;
                tens *= 10;
                j--;
                i--;
            }
        }
        return sum;
    }

    public static int calculate(char[] c1, char[] c2) {
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
            sumDigit = (num1 + num2 + carryOver);
            // edge case when str1 and str2 are just one digit
            // then it'd calculate the carry over but not add it to our sum
            // as carryOver would never get evaluated a second time
            // carry over value also has to scale with tens to calculate
            // correct value
            if (sumDigit > 9 && c1.length > 1 && c2.length > 1) {
                carryOver = sumDigit / (tens * 10);
                sumDigit = sumDigit % (tens * 10);
            }
            else {
                carryOver = 0;
            }
            sumDigit *= tens;
            sum += sumDigit;
            tens *= 10;
            j--;
            i--;
        }
        return sum;
    }

    public static int sumStrings(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int i = c1.length - 1;
        int j = c2.length - 1;

        int c1DecimalPlace = decimalPlace(c1);
        int c2DecimalPlace = decimalPlace(c2);
        // if both numbers have decimals
        if(c1DecimalPlace != -1 && c2DecimalPlace != -1) {
            c1DecimalPlace++;
            c2DecimalPlace++;
            //copy the decimals into a new char array to pass it into our function
            char[] c1DecimalCopy = new char[c1.length - c1DecimalPlace];
            char[] c2DecimalCopy = new char[c2.length - c2DecimalPlace];
            for(int k = 0; k < c1DecimalCopy.length; k++) {
                c1DecimalCopy[k] = c1[c1DecimalPlace + k];
            }
            for(int k = 0; k < c2DecimalCopy.length; k++) {
                c2DecimalCopy[k] = c2[c2DecimalPlace + k];
            }
            // calculate the sum of the two decimals
            double calculateDecimals = calculateDecimal(c1DecimalCopy, c2DecimalCopy);
            System.out.println("Calculate decimals: " + calculateDecimals);
            // if there is a carry over
            // add the carry over value to one of our original numbers
            if(calculateDecimals > 0) {
                c1DecimalPlace--;
                char[] c1WithoutDecimal = new char[c1DecimalPlace];
                for(int k = 0; k < c1WithoutDecimal.length; k++) {
                    c1WithoutDecimal[k] = c1[k];
                }
                char[] calculateDecimalsAsString = Double.toString(calculateDecimals).toCharArray();
                char[] takeCarryOver = new char[1];
                takeCarryOver[0] = calculateDecimalsAsString[0];
                int addDecimalCarryOver = calculate(c1WithoutDecimal, takeCarryOver);
                c2DecimalPlace--;
                char[] c2WithoutDecimal = new char[c2DecimalPlace];
                for(int k = 0; k < c2WithoutDecimal.length; k++) {
                  c2WithoutDecimal[k] = c2[k];
                }
                int sum = calculate(c1WithoutDecimal, c2WithoutDecimal);
                sum += calculateDecimals;
                return sum;
            } else {
              // if there was only 1 string with a decimal:
              
            }
        }



        return 0;
    }
}
