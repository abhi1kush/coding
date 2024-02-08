package array_problems

import "testing"

func TestGetTwoSumIndices(t *testing.T) {
	type args struct {
		arr    []int
		target int
	}
	tests := []struct {
		name  string
		args  args
		want  int
		want1 int
	}{
		{
			name: "Basic positive",
			args: args{
				arr:    []int{1, 3, 7, 9, 2},
				target: 11,
			},
			want:  3,
			want1: 4,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, got1 := GetTwoSumIndices(tt.args.arr, tt.args.target)
			if got != tt.want {
				t.Errorf("GetTwoSumIndices() got = %v, want %v", got, tt.want)
			}
			if got1 != tt.want1 {
				t.Errorf("GetTwoSumIndices() got1 = %v, want %v", got1, tt.want1)
			}
		})
	}
}
