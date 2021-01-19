import java.util.*;

/**
 * - LeetCode : https://leetcode.com/problems/online-election/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   For q(t):
 *   Once we know the time zone that t is belong to.
 *   The key of this problem are two things: 1. current largest votes 2. the most recent winner.
 *
 *   1. If we keep recording the time and person information of the votes
 *      and categorize them according to the number of votes of a certain candidate.
 *
 *      For example:
 *      persons: 1  0  0  2  1  0  2
 *      times:   0  2  4  5  7  8  10
 *
 *      A:   0   1     2     3
 *           X   1,0   0,4   0,8
 *               0,2   1,7
 *               2,5   2,10
 *      (person,time) -> (1,0) is stored at A[1] because it is the 1st vote for person 1 at time 0.
 *
 *      So in order to get the result,
 *      we first search among A[i][0].time and find a A[k][0](A[k][0].time <= t < A[k + 1][0].time)
 *      because we have to figure out the largest number of votes among candidates at time t.
 *      Then we search among A[k][j].time and find a A[k][l](A[k][l].time <= t < A[k][l + 1].time)
 *      Time complexity: O(N+QlogN)
 *      Space complexity: O(N)
 *
 *   2. Instead of recording all the information, we can just store the winner and its time when the current winner is changed.
 *      For example:
 *      persons: 1  0  0  2  1  0  2
 *      times:   0  2  4  5  7  8  10
 *
 *      A: 0     1     2    3
 *        1,0   0,2   1:7   0:8
 *      Time complexity: O(N+QlogN)
 *      Space complexity: O(N)
 *
 *   3. Or we can store winner in every time.
 *      Time complexity: O(N+QlogN)
 *      Space complexity: O(N)
 *
 * - Test cases:
 *   1. persons = [0,1, 1, 0, 0, 1, 0]
 *      time =    [0,5,10,15,20,25,30]
 *      query = [3],[12],[25],[15],[24],[8]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc911OnlineElection {
    class Vote {
        int person, time;
        Vote(int p, int t) {
            person = p;
            time = t;
        }
    }
    HashMap<Integer,Integer> map = new HashMap<>();

/* Solution1 :
    List<List<Vote>> A;
    public lc911OnlineElection(int[] persons, int[] times) {
        A = new ArrayList();
        Map<Integer, Integer> count = new HashMap();
        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i], t = times[i];
            int c = count.getOrDefault(p, 0) + 1;

            count.put(p, c);
            while (A.size() <= c)
                A.add(new ArrayList<>());
            A.get(c).add(new Vote(p, t));
        }
    }

    public int q(int t) {
        if (map.containsKey(t)) return map.get(t);
        // Binary search on A[i][0].time for largest i such that A[i][0].time <= t
        int lo = 1, hi = A.size() - 1;
        while (lo <= hi) {
            int mi = (lo + hi) >> 1;
            if (A.get(mi).get(0).time <= t)
                lo = mi + 1;
            else
                hi = mi - 1;
        }
        int i = hi;

        // Binary search on A[i][j].time for largest j such that A[i][j].time <= t
        lo = 0; hi = A.get(i).size() - 1;
        while (lo <= hi) {
            int mi = (lo + hi) >> 1;
            if (A.get(i).get(mi).time <= t)
                lo = mi + 1;
            else
                hi = mi - 1;
        }

        int p = A.get(i).get(hi).person;
        map.put(t,p);
        return p;
    }
 */

/* Solution 2:
    List<Vote> A;
    public lc911OnlineElection(int[] persons, int[] times) {
        A = new ArrayList();
        Map<Integer, Integer> count = new HashMap();
        int leader = -1;  // current leader
        int m = 0;  // current number of votes for leader

        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i], t = times[i];
            int c = count.getOrDefault(p, 0) + 1;
            count.put(p, c);

            if (c >= m) {
                if (p != leader) {  // lead change
                    leader = p;
                    A.add(new Vote(leader, t));
                }
                m = c;
            }
        }
    }

    public int q(int t) {
        if (map.containsKey(t)) return map.get(t);

        int lo = 0, hi = A.size() - 1;
        while (lo <= hi) {
            int mi = (lo + hi) >> 1;
            if (A.get(mi).time <= t) {
                lo = mi + 1;
            }
            else
                hi = mi - 1;
        }

        int p = A.get(hi).person;
        map.put(t,p);

        return p;
    }
 */

    int[] tops;
    int[] times;
    public lc911OnlineElection(int[] persons, int[] times) {
        int n = persons.length;

        this.times = times;
        tops = new int[n];

        int[] freqs = new int[n+1];
        int top = -1; // current leader
        int max = 0;  // current number of votes for leader

        for (int i = 0; i < n; i++) {
            int p = persons[i];
            freqs[p]++;
            if (freqs[p] >= max) {
                max = freqs[p];
                top = p;
            }
            tops[i] = top;
        }
    }

    public int q(int t) {
        int index = Arrays.binarySearch(times, 0, times.length, t);
        if (index < 0) {
            index = -1 - index - 1;
        }
        return tops[index];
    }

    public static void main (String[] args) {
        int[] persons = {1,0,0,2,1,0,2};
        int[] time =    {0,2,4,5,7,8,10};

        lc911OnlineElection s = new lc911OnlineElection(persons, time);
        int res = s.q(7);
        System.out.println(res);
    }
}
