package ir.co.ts.firstproject.database;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class DataManager {

    public List<String> getSecondListData() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100; ++i) {
            String number = getRandomNumber(0, 20);
            if (number != null && !number.isEmpty()) {
                number = groupSameNumber(number);
            }
            list.add(number);
        }
        return list;
    }

    private String getRandomNumber(int minDigit, int maxDigit) {
        int max = randInt(minDigit, maxDigit);
        if (max == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(max);
        stringBuilder.append(randInt(1, 9));

        for (int i = 0; i < max - 1; ++i) {
            int randomNum = randInt(0, 9);
            stringBuilder.append(randomNum);
        }

        return stringBuilder.toString();
    }

    private int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private String groupSameNumber(String number) {
        StringBuilder groupedNum = new StringBuilder();
        char preChar = number.charAt(0);
        groupedNum.append(preChar);

        final int size = number.length();
        for (int i = 1; i < size; ++i) {
            char n = number.charAt(i);
            if (n == preChar) {
                groupedNum.append(n);
            } else {
//                if ((i + 1) < size && number.charAt(i + 1) != n) {
                    groupedNum.append(',');
//                }
                groupedNum.append(n);
            }
            preChar = n;
        }

        return groupedNum.toString();
    }
}
