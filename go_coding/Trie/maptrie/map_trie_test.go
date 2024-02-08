package maptrie

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
		children    map[string]*TrieNode
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
			fmt.Printf("%v", trie.AutoCompelete("bu", 7))
		})
	}
}
