package array_problems

func GetTwoSumIndices(arr []int, target int) (int, int) {
	diffMap := make(map[int]int)
	for currIndex, num := range arr {
		if index, found := diffMap[num]; found {
			return index, currIndex
		}
		diffMap[target-num] = currIndex
	}
	return -1, -1
}
