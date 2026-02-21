import java.util.*;

public class TopFrequents {
    private class Element {
        int number;
        int frequency;
        int index;
        Element(int number, int frequency, int index) {
            this.number = number;
            this.frequency = frequency;
            this.index = index;
        }
    }
    public List<Integer> findTopKFrequent(int[] nums, int k) {
        // Implementation to find the k most frequent elements
        Map<Integer, Element> frequencyMap = new HashMap<>();
        for (int i =0 ; i< nums.length;i++){
            if(frequencyMap.containsKey(nums[i])){
                frequencyMap.get(nums[i]).frequency++;
            }else{
                frequencyMap.put(nums[i], new Element(nums[i], 1, i));
            }
        }
        PriorityQueue<Element> minHeap = new PriorityQueue<>(
            (a,b) -> a.frequency - b.frequency == 0 ? b.index - a.index : a.frequency - b.frequency);
        for(Element entry : frequencyMap.values()){
            minHeap.offer(entry);
            if(minHeap.size()>k){
                minHeap.poll();
            }

        }
        List<Integer> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(minHeap.poll().number);
        }
        Collections.reverse(result);
        return result;
    }
}