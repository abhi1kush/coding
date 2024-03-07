package heap_problems

import (
	"reflect"
	"testing"
)

func Test_topKFrequent(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		{
			name: "Basic",
			args: args{
				nums: []int{1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4},
				k:    3,
			},
			want: []int{1, 4, 3},
		},
		{
			name: "Basic2",
			args: args{
				nums: []int{1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4},
				k:    3,
			},
			want: []int{1, 4, 3},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := topKFrequent(tt.args.nums, tt.args.k); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("topKFrequent() = %v, want %v", got, tt.want)
			}
		})
	}
}
