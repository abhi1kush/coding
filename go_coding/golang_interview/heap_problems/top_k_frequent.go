package heap_problems

import(
	"container/heap"
)

type pair struct {
    num, count int
}

type MaxHeap []pair

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(l, r int) bool { return h[l].count > h[r].count }
func (h MaxHeap) Swap(l, r int){ h[l],h[r] = h[r], h[l] }
func (h *MaxHeap)Push(val any) {*h = append(*h, val.(pair))}
func (h *MaxHeap) Pop() any {
    x := (*h)[len(*h)-1]
    *h = (*h)[0:len(*h)-1]
    return x
}

func topKFrequent(nums []int, k int) []int {
    freqMap := make(map[int]int)
    for _, num := range nums {
        freqMap[num]++
    }

    maxHeap := &MaxHeap{}
    for num, freq := range freqMap {
        *maxHeap = append(*maxHeap, pair{num, freq})
    }

    heap.Init(maxHeap)
    topKFreqNums := []int{}
    for i := 0; i < k; i++ {
        topKFreqNums = append(topKFreqNums, heap.Pop(maxHeap).(pair).num)
    }
    return  topKFreqNums
}