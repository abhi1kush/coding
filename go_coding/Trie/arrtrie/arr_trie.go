package arrtrie

import "fmt"

const (
	ALPHABET_SIZE  = 26
	FIRST_ALPHABET = 'a'
)

type TrieNode struct {
	value       any
	children    [ALPHABET_SIZE]*TrieNode
	isEndOfWord bool
}

func NewTrieNode() *TrieNode {
	return &TrieNode{}
}

// Put reurns true if new value set in last key node, false if overwritten older value.
func (t *TrieNode) Put(key string, val any) bool {
	currNode := t
	for _, ch := range key {
		if len(currNode.children) == 0 {
			currNode.children = [ALPHABET_SIZE]*TrieNode{}
		}
		index := charToIndex(ch)
		child := currNode.children[index]
		if child == nil {
			currNode.children[index] = NewTrieNode()
		}
		currNode = currNode.children[index]
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
	for _, ch := range key {
		child := currNode.children[charToIndex(rune(ch))]
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
	for _, ch := range key {
		child := currNode.children[charToIndex(rune(ch))]
		if child == nil {
			return false
		}
		currNode = currNode.children[charToIndex(rune(ch))]
	}
	return true
}

func deleteRec(currNode *TrieNode, str string, index int) {
	if index >= len(str) || nil == currNode {
		return
	}

	deleteRec(currNode.children[charToIndex(rune(str[index]))], str, index+1)

	if len(str)-1 == index && currNode.children[charToIndex(rune(str[index]))] != nil {
		currNode.children[charToIndex(rune(str[index]))].isEndOfWord = false
	}

	if currNode.children[charToIndex(rune(str[index]))].isLeaf() {
		currNode.children[charToIndex(rune(str[index]))] = nil
	}
}

func (t *TrieNode) Delete(str string) {
	if nil == t {
		return
	}

	if !t.Contains(str) {
		return
	}

	deleteRec(t, str, 0)
}

// AutoCompelete: Returns an arrray of all possible autocompletion of a partial typed word.
// Input Param
// str string: partially typed string
// maxLen int: maximum length bound for autocompleted words.
func (t *TrieNode) AutoCompelete(str string, maxLen int) []string {
	currNode := t
	for _, ch := range str {
		child := currNode.children[charToIndex(rune(ch))]
		if child == nil {
			return nil
		}
		currNode = currNode.children[charToIndex(rune(ch))]
	}
	//explore possible words.
	autoCompleteList := []string{}
	autoCompeleteRec(str, currNode, &autoCompleteList, 0, maxLen)
	return autoCompleteList
}

func (t *TrieNode) isLeaf() bool {
	for _, child := range t.children {
		if child != nil {
			return false
		}
	}
	return true
}

func autoCompeleteRec(str string, t *TrieNode, result *[]string, currRecDetph int, maxLen int) {
	if t == nil || t.isLeaf() {
		return
	}

	if t.isEndOfWord {
		*result = append(*result, str)
	}

	if currRecDetph >= maxLen {
		return
	}
	for i, child := range t.children {
		autoCompeleteRec(str+indexToChar(i), child, result, currRecDetph+1, maxLen)
	}
}

// LexoGraphicalSort: Returns all words stored in trie in lexographical order.
func (t *TrieNode) LexoGraphicalSort() []string {
	result := []string{}
	lexoGraphicalSortRec("", t, &result)
	return result
}

func lexoGraphicalSortRec(str string, t *TrieNode, result *[]string) {
	if t == nil {
		return
	}
	if t.isLeaf() || t.isEndOfWord {
		*result = append(*result, str)
		if t.isLeaf() {
			return
		}
	}
	for i, child := range t.children {
		lexoGraphicalSortRec(str+indexToChar(i), child, result)
	}
}

func charToIndex(ch rune) int32 {
	return ch - FIRST_ALPHABET
}

func indexToChar(index int) string {
	return fmt.Sprintf("%c", int(FIRST_ALPHABET+index))
}
