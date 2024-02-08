package string_problems

import (
	"testing"
)

func TestMatchTypedOutString(t *testing.T) {
	type args struct {
		source string
		target string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{
			name: "basic",
			args: args{
				source: "a",
				target: "",
			},
			want: false,
		},
		{
			name: "basic_0",
			args: args{
				source: "ab#c",
				target: "ad#c",
			},
			want: true,
		},
		{
			name: "basic_positive",
			args: args{
				source: "ab#c",
				target: "ac",
			},
			want: true,
		},
		{
			name: "basic_positive 2",
			args: args{
				source: "abd##c",
				target: "acx#",
			},
			want: true,
		},
		{
			name: "basic_positive 3",
			args: args{
				source: "abd##c#",
				target: "acx#",
			},
			want: false,
		},
		{
			name: "basic_positive 4",
			args: args{
				source: "aa#c##",
				target: "",
			},
			want: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := MatchTypedOutString(tt.args.source, tt.args.target); got != tt.want {
				t.Errorf("MatchTypedOutString() = %v, want %v", got, tt.want)
			}
		})
	}
}
