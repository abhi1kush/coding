package trie

import (
	"fmt"
	"reflect"
	"testing"

	"github.com/abhi1kush/coding/go_coding/Trie/maptrie"
	"github.com/abhi1kush/coding/go_coding/trie/arrtrie"
)

type input struct {
	key string
	val any
}

func TestTrieNode_Put(t *testing.T) {
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
	var arrTrie Trie = arrtrie.NewTrieNode()
	var mapTrie Trie = maptrie.NewTrieNode()

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			for _, input := range tt.args.keys {
				arrTrie.Put(input.key, input.val)
				mapTrie.Put(input.key, input.val)
			}
			for _, input := range tt.args.keys {
				if !arrTrie.Contains(input.key) {
					t.Errorf("TrieNode.Contains returned false expected true")
				}
				if !mapTrie.Contains(input.key) {
					t.Errorf("TrieNode.Contains returned false expected true")
				}
			}
			fmt.Printf("%v", arrTrie.AutoCompelete("bu", 7))
			fmt.Printf("%v", mapTrie.AutoCompelete("bu", 7))
			if !reflect.DeepEqual(arrTrie.AutoCompelete("bu", 7), mapTrie.AutoCompelete("bu", 7)) {
				t.Errorf("Expecting same autoComplete from map and arr trie")
			}
		})
	}
}
