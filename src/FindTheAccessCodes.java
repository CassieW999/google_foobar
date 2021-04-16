public class FindTheAccessCodes {
    public static int solution(int[] l) {
        // Your code here
        int ans = 0;
        int n = l.length;
        if (l.length < 3) return ans;
        // define the mid one first
        for (int i = 1; i < n - 1; i++) {
            int countBefore = 0;
            int countAfter = 0;
            int curr = l[i];
            for (int j = 0; j < i; j++) {
                if (curr % l[j] == 0){
                    countBefore++;
                }
            }
            for (int k = i + 1; k < n; k++) {
                if (l[k] % curr == 0){
                    countAfter++;
                }
            }
            ans += countAfter * countBefore;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] l = {};
        System.out.println(solution(l));
    }
}
