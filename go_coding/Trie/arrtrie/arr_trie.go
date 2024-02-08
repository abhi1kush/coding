package arrtrie

import "fmt"

const (
	ALPHABET_SIZE  = 26
	FIRST_ALPHABET = 'a'
)

func charToIndex(ch rune) int32 {
	return ch - FIRST_ALPHABET
}

func indexToChar(index int) string {
	return fmt.Sprintf("%c", int(FIRST_ALPHABET+index))
}

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
	return len(t.children) == 0
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
