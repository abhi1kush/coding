package arrtrie

import (
	"fmt"
	"testing"
)

type input struct {
	key string
	val any
}

func TestTrieNode_Put(t *testing.T) {
	type fields struct {
		value       any
		children    [ALPHABET_SIZE]*TrieNode
		isEndOfWord bool
	}

	type args struct {
		keys []input
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{
			name: "basic",
			args: args{
				keys: []input{
					{key: "buddha", val: 1},
					{key: "buddy", val: 2},
					{key: "bud", val: 3},
					{key: "sun", val: 4},
					{key: "super", val: 5},
				},
			},
		},
	}
	trie := NewTrieNode()
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			for _, input := range tt.args.keys {
				trie.Put(input.key, input.val)
			}
			for _, input := range tt.args.keys {
				if !trie.Contains(input.key) {
					t.Errorf("TrieNode.Contains returned false expected true")
				}
			}
			fmt.Printf("%v", trie.LexoGraphicalSort())
			fmt.Printf("%v", trie.AutoCompelete("bu", 7))
		})
	}
}

func Test_charToIndex(t *testing.T) {
	type args struct {
		ch rune
	}
	tests := []struct {
		name string
		args args
		want int32
	}{
		{
			name: "a",
			args: args{
				ch: 'a',
			},
			want: 0,
		},
		{
			name: "z",
			args: args{
				ch: 'z',
			},
			want: 25,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := charToIndex(tt.args.ch); got != tt.want {
				t.Errorf("charToIndex() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_indexToChar(t *testing.T) {
	type args struct {
		index int
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		{
			name: "0",
			args: args{
				index: 0,
			},
			want: "a",
		},
		{
			name: "25",
			args: args{index: 25},
			want: "z",
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := indexToChar(tt.args.index); got != tt.want {
				t.Errorf("indexToChar() = %v, want %v", got, tt.want)
			}
		})
	}
}
