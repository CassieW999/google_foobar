public class plsPassCodedMessages {
    public static int solution(int[] l) {
        // Your code here
        int[] buckets = new int[10];
        int sum = 0;
        for(int i = 0; i < l.length; i++){
            sum += l[i];
            buckets[l[i]]++;
        }
        sum %= 3;
        int left1 = buckets[1] + buckets[4] + buckets[7];
        int left2 = buckets[2] + buckets[5] + buckets[8];
        if(sum == 1){
            if(left1 > 0){
                left1--;
            } else {
                left2 -= 2;
            }
        } else if(sum == 2){
            if(left2 > 0){
                left2--;
            } else {
                left1 -= 2;
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i = 9; i >= 0; i--){
            if(i % 3 == 1){
                //余数为1，则为[1, 4, 7]，拿大的
                while(left1 > 0 && buckets[i] > 0){
                    buckets[i]--;
                    left1--;
                    res.append(i);
                }
            } else if(i % 3 == 2){
                //余数为2，则为[2, 6, 8]，拿大的
                while(left2 > 0 && buckets[i] > 0){
                    buckets[i]--;
                    left2--;
                    res.append(i);
                }
            } else {
                //余数为0，则本身为3的倍数，都要加上[0, 3, 6, 9]
                while(buckets[i] > 0){
                    buckets[i]--;
                    res.append(i);
                }
            }
        }
        String ans = res.toString();
        if (ans.equals("")) return 0;
        if (ans.charAt(0) == '0') return 0;
        return Integer.valueOf(ans);
    }

    public static void main(String[] args) {
        int[] nums = {};
        System.out.println(plsPassCodedMessages.solution(nums));
    }
}
