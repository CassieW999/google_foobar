import java.util.LinkedList;
import java.util.Queue;

public class dontGetVolunteered {
    public static int solution(int src, int dest) {
        if (src == dest) return 0;
        int shortest_path = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[64];
        q.add(src);
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                visited[curr] = true;
                if (curr == dest) return shortest_path;
                updateQueue(q, curr, visited);
            }
            shortest_path++;
        }
        return -1;
    }

    private static void updateQueue(Queue<Integer> q, int curr, boolean[] visited) {
        int row = curr / 8;
        int col = curr % 8;
        int[][] directions = {{-1, -2}, {-2, -1}, {-1, 2}, {1, -2}, {2, -1}, {-2, 1}, {1, 2}, {2, 1}};
        for (int i = 0; i < 8; i++) {
            int new_row = row + directions[i][0];
            int new_col = col + directions[i][1];
            int new_num = 8 * new_row + new_col;
            if (new_row >= 0 && new_row <= 7 && new_col >= 0 && new_col <= 7 && new_num >= 0 && new_num <= 63 && !visited[new_num]){
                q.add(new_num);
                visited[new_num] = true;
            }
        }
    }

    public static void main(String[] args) {
        int src = 19;
        int des = 36;
//        System.out.println(solution(src, des));
//        System.out.println(solution(19, 19));
//        System.out.println(solution(0,1));
        System.out.println(solution(50,63));
    }


}
