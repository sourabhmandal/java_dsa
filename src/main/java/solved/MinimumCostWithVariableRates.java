package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MinimumCostWithVariableRates {

    public static void main(String[] args) {
        // Use getResourceAsStream to load the input file from resources
        InputStream inputStream = solved.MinimumCostWithVariableRates.class.getResourceAsStream("/input.txt");
        if (inputStream == null) {
            System.err.println("Input file not found!");
            return;
        }
        FastReader reader = new FastReader(inputStream);

        int testCaseCount = reader.nextInt();

        for(int i = 0; i < testCaseCount; i++) {
            List<BigInteger> integerList = Arrays.stream(reader.nextLine().split(" "))
                    .map(BigInteger::new)
                    .toList();

            BigInteger minimumTime = getMinTimeToCharge(integerList.get(0), integerList.get(1));
            System.out.println(minimumTime);
        }
    }

    // Solve
    static BigInteger getMinTimeToCharge(BigInteger startCharge, BigInteger finalCharge) {
        List<BigInteger []> rates = new ArrayList<>();
        rates.add(new BigInteger [] {new BigInteger("0"), new BigInteger("10"), new BigInteger("10")});
        rates.add(new BigInteger [] {new BigInteger("11"), new BigInteger("230"), new BigInteger("5")});
        rates.add(new BigInteger [] {new BigInteger("231"), new BigInteger("559"), new BigInteger("8")});
        rates.add(new BigInteger [] {new BigInteger("560"), new BigInteger("1009"), new BigInteger("2")});
        rates.add(new BigInteger [] {new BigInteger("1010"), new BigInteger("5000"), new BigInteger("7")});
        rates.add(new BigInteger [] {new BigInteger("10001"), BigInteger.valueOf(10).pow(9),
                new BigInteger("3")}
        );

        BigInteger total_time_taken = new BigInteger("0");
        BigInteger current_charge = startCharge;

        for(BigInteger[] charge_range : rates) {
            // S = 10, T = 50
            // step 1 - max((end - curr_charge), 1) / rate -- 1
            //          current_charge = current_charge + (step 1  * rate)  -- 20
            // step 2 - max(min(end, finalCharge) - curr_charge, 1) / rate -- 6
            //          current_charge = current_charge + (step 1  * rate)  -- 50

            BigInteger start = charge_range[0];
            BigInteger end = charge_range[1];
            BigInteger rate = charge_range[2];
            if(current_charge.compareTo(finalCharge) < 0 &&
                    current_charge.compareTo(start) >= 0 &&
                    current_charge.compareTo(end) <= 0) {



                BigInteger charging_done = end.min(finalCharge)
                        .subtract(current_charge)
                        .max(BigInteger.ONE);

                BigInteger time_taken = charging_done.divide(rate);
                if (charging_done.mod(rate).compareTo(BigInteger.ZERO) > 0) {
                    time_taken = time_taken.add(BigInteger.ONE); // Add 1 if there's a remainder
                }
                total_time_taken = total_time_taken.add(time_taken);
                current_charge = current_charge.add(time_taken.multiply(rate));

            }
        }


        return total_time_taken;
    }

    // FastReader class for efficient input handling
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;

            // Constructor to read from file
            public FastReader(InputStream inputStream) {
                br = new BufferedReader(new InputStreamReader(inputStream));
            }

            // Reads next word
            String next() {
                while (st == null || !st.hasMoreElements()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            // Reads next integer
            int nextInt() {
                return Integer.parseInt(next());
            }

            // Reads next long
            long nextLong() {
                return Long.parseLong(next());
            }

            // Reads next double
            double nextDouble() {
                return Double.parseDouble(next());
            }

            // Reads complete line
            String nextLine() {
                String str = "";
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return str;
            }

            BigInteger nextBigInteger() {
                return new BigInteger(next());
            }
        }


}
