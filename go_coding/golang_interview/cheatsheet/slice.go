package cheatsheet

import (
	"fmt"
	"slices"
)

func main() {
	SliceUse()
}

func SliceUse() {
	// declaration method 1
	var slice []int
	size := 10
	slice = make([]int, size) // elements can be assigned directly to the index < size
	fmt.Printf("%v\n", slice)

	// declaration method 2
	slice2 := make([]int, size) // elements can be assigned directly to the index < size
	fmt.Printf("%v\n", slice2)

	// declaration method 3
	slice3 := []int{}
	fmt.Printf("%v", slice3)

	// declaration method 4
	slice4 := []int{4, 7, 1, 8, 3, 0, 2, 67, 34, 4, 1} // use only append for adding elements

	// Add element
	val := 11
	slice4 = append(slice4, val)

	// Get length
	length := len(slice4)
	fmt.Printf("len %v\n", length)

	// Remove Last element
	slice4 = slice4[:len(slice4)-1]
	// alternative syntax
	slice4 = slice4[0 : len(slice4)-1]

	// Remove First element
	slice4 = slice4[1:]

	// sort in ascending order
	slices.Sort(slice4)
	fmt.Printf("%v\n", slice4)

	// sort in descending order
}
