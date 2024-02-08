package trie

type Trie interface {
	Put(key string, val any) bool
	Get(key string) any
	Contains(key string) bool
	AutoCompelete(str string, maxLen int) []string
}
