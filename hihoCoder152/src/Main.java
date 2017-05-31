/**
 * Created by Shinelon on 2017/5/31.
 */
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Interval[] arrayA = new Interval[n];
        Interval[] arrayB = new Interval[m];
        for(int i = 0; i < n; i++) {
            Interval temp = new Interval();
            arrayA[i] = temp;
            arrayA[i].start = in.nextInt();
            arrayA[i].end = in.nextInt();
        }

        for(int i = 0; i < m; i++) {
            Interval temp = new Interval();
            arrayB[i] = temp;
            arrayB[i].start = in.nextInt();
            arrayB[i].end = in.nextInt();
        }

        PriorityQueue<Interval> queueA = new PriorityQueue<Interval>(n + m, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        PriorityQueue<Interval> queueB = new PriorityQueue<Interval>(n + m, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        for(Interval temp : arrayA)
            queueA.add(temp);
        for(Interval temp : arrayB)
            queueB.add(temp);
        LinkedList<Interval> listA = new LinkedList<>();
        LinkedList<Interval> listB = new LinkedList<>();
        while(!queueA.isEmpty()) {
            addToList(listA, queueA.poll());
        }

        while(!queueB.isEmpty()) {
            addToList(listB, queueB.poll());
        }
        int lengthA = 0;
        for(Interval temp : listA) {
            lengthA += (temp.end - temp.start);
        }
        int sumLength = 0;
        while(!listA.isEmpty() && !listB.isEmpty()) {
            Interval tempA = listA.poll();
            Interval tempB = listB.poll();
            if(tempB.end <= tempA.start) {
                listA.addFirst(tempA);
            }else if(tempB.start <= tempA.start  && tempB.end < tempA.end) {
                sumLength += tempB.end - tempA.start;
                Interval i = new Interval();
                i.start = tempB.end;
                i.end = tempA.end;
                if(i.end - i.start != 0)
                    listA.addFirst(i);
            }else if(tempB.start <= tempA.start && tempB.end >= tempA.end) {
                sumLength += tempA.end - tempA.start;
                Interval i = new Interval();
                i.start = tempA.end;
                i.end = tempB.end;
                if(i.end - i.start != 0)
                    listB.addFirst(i);
            }else if (tempB.start >= tempA.start && tempB.end <= tempA.end) {
                sumLength += tempB.end - tempB.start;
                Interval i = new Interval();
                i.start = tempB.end;
                i.end = tempA.end;
                if(i.end - i.start != 0)
                    listA.addFirst(i);
            }else if(tempB.end >= tempA.end && tempB.start <= tempA.end) {
                sumLength += tempA.end - tempB.start;
                Interval i = new Interval();
                i.start = tempA.end;
                i.end = tempB.end;
                if(i.end - i.start != 0)
                    listB.addFirst(i);
            }else {
                listB.addFirst(tempB);
            }
        }



        System.out.print(lengthA - sumLength);

    }

    public static void addToList(List<Interval> list, Interval temp) {
        if(list.size() == 0) {
            list.add(temp);
        }else{
            Interval a = list.get(list.size() - 1);
            if(a.end < temp.start) {
                list.add(temp);
            }else if(a.end >= temp.start && a.end < temp.end) {
                a.end = temp.end;
            }
        }
    }

}

class Interval {
    int start;
    int end;
}