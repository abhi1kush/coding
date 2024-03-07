package maptrie

import "fmt"

type TrieNode struct {
	value       any
	children    map[string]*TrieNode
	isEndOfWord bool
}

func NewTrieNode() *TrieNode {
	return &TrieNode{
		children: map[string]*TrieNode{},
	}
}

// Put reurns true if new value set in last key node, false if overwritten older value.
func (t *TrieNode) Put(key string, val any) bool {
	currNode := t
	for _, char := range key {
		ch := fmt.Sprintf("%c", char)
		child := currNode.children[ch]
		if child == nil {
			if currNode.children == nil {
				currNode.children = map[string]*TrieNode{}
			}
			currNode.children[ch] = NewTrieNode()
		}
		currNode = currNode.children[ch]
	}
	isReplaced := currNode.value != nil
	currNode.value = val
	currNode.isEndOfWord = true
	return isReplaced
}

// Get: Returns value stored in final node in walk of input key.
// if key does not exist in trie then it returns false.
func (t *TrieNode) Get(key string) any {
	currNode := t
	for _, char := range key {
		ch := fmt.Sprintf("%c", char)
		child := currNode.children[ch]
		if child == nil {
			return nil
		}
		currNode = child
	}
	return currNode.value
}

// Contains: Returns true if input key string exist in trie otherwise returns false.
func (t *TrieNode) Contains(key string) bool {
	currNode := t
	for _, char := range key {
		ch := fmt.Sprintf("%c", char)
		child := currNode.children[ch]
		if child == nil {
			return false
		}
		currNode = child
	}
	return true
}

// AutoCompelete: Returns an arrray of all possible autocompletion of a partial typed word.
// Input Param
// str string: partially typed string
// maxLen int: maximum length bound for autocompleted words.
func (t *TrieNode) AutoCompelete(str string, maxLen int) []string {
	currNode := t
	for _, char := range str {
		ch := fmt.Sprintf("%c", char)
		child := currNode.children[ch]
		if child == nil {
			return nil
		}
		currNode = child
	}
	//explore possible words.
	autoCompleteList := []string{}
	autoCompeleteRec(str, currNode, &autoCompleteList, 0, maxLen)
	return autoCompleteList
}

func (t *TrieNode) isLeaf() bool {
	return t.children == nil
}

func autoCompeleteRec(str string, t *TrieNode, result *[]string, currRecDetph int, maxLen int) {
	if t.isLeaf() {
		return
	}

	if t.isEndOfWord {
		*result = append(*result, str)
	}

	if currRecDetph >= maxLen {
		return
	}

	for key, child := range t.children {
		autoCompeleteRec(str+key, child, result, currRecDetph+1, maxLen)
	}
}
