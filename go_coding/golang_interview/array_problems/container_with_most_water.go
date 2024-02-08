package array_problems

// import "fmt"

func TotalWater(arr []int) int {
	n := len(arr)
	leftMaxArr := make([]int, n)
	rightMaxArr := make([]int, n)

	leftMax := arr[0]
	rightMax := arr[len(arr)-1]
	for i := 0; i < len(arr); i++ {
		leftMax = max(leftMax, arr[i])
		rightMax = max(rightMax, arr[i])
		leftMaxArr[i] = leftMax
		rightMaxArr[n-1-i] = rightMax
	}

	totalWater := 0
	for i := 0; i < len(arr)-1; i++ {
		// fmt.Printf("i:%d, totalWater: %v,  currH:%v leftMax[i]%v rightMax[i+1]%v\n", i, totalWater, arr[i], leftMaxArr[i], rightMaxArr[i+1])
		totalWater += min(leftMaxArr[i], rightMaxArr[i+1])
	}
	return totalWater
}
