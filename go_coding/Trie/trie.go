package trie

type Trie interface {
	// Put reurns true if new value set in last key node, false if overwritten older value.
	Put(key string, val any) bool
	// Get: Returns value stored in final node in walk of input key.
	// if key does not exist in trie then it returns false.
	Get(key string) any
	// Contains: Returns true if input key string exist in trie otherwise returns false.
	Contains(key string) bool
	// AutoCompelete: Returns an arrray of all possible autocompletion of a partial typed word.
	// param str string: partially typed string
	// param maxLen int: maximum length bound for autocompleted words.
	AutoCompelete(str string, maxLen int) []string
}
