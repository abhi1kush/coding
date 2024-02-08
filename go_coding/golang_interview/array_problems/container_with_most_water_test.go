package array_problems

import "testing"

func TestMaxWater(t *testing.T) {
	type args struct {
		arr []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{
			name: "Basic positive",
			args: args{
				arr: []int{7, 1, 2, 3, 9},
			},
			want: 7 * 4,
		},
		{
			name: "Basic Positive 2",
			args: args{
				arr: []int{4, 8, 1, 2, 3, 9},
			},
			want: 4 + 4*8,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := TotalWater(tt.args.arr); got != tt.want {
				t.Errorf("MaxWater() = %v, want %v", got, tt.want)
			}
		})
	}
}
