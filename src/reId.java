public class reId {
    public static String solution(int i) {
        // Your code here
        String allPrimes = getPrimeNums();
        return allPrimes.substring(i, i + 5);
    }

    public static String getPrimeNums(){
        String primes = "";
        int prime = 2;
        while (primes.length() < 10005){
            primes += prime + "";
            prime++;
            while (!isPrime(prime)){
                prime++;
            }
        }
        return primes;
    }

    public static boolean isPrime(int m){
        for (int i = 2; i < m; i++){
            if (m % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(10000));
    }
}
