package string_problems

func MatchTypedOutString(source string, target string) bool {
	i := len(source) - 1
	j := len(target) - 1
	for i >= 0 || j >= 0 {
		if (i >= 0 && source[i] == '#') || (j >= 0 && target[j] == '#') {
			if i >= 0 && source[i] == '#' {
				backCount := 2
				for backCount > 0 {
					i--
					backCount--
					if i >= 0 && source[i] == '#' {
						backCount += 2
					}
				}
			}
			if j >= 0 && target[j] == '#' {
				backCount := 2
				for backCount > 0 {
					j--
					backCount--
					if j >= 0 && target[j] == '#' {
						backCount += 2
					}
				}
			}
		} else {
			if i < 0 && j >= 0 || j < 0 && i >= 0 {
				return false
			}
			if i >= 0 && j >= 0 && source[i] != target[j] {
				return false
			}
			i--
			j--
		}
	}
	return true
}
