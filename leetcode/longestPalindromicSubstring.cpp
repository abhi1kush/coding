string manacher(string & s){
	//Transforming string "abc" to "^#a#b#c$"
	string ss = "^#";
	for(auto m:s){
		ss += m;
		ss += "#";
	}
	ss += "$";
	vector<int> p(ss.size(),0);

	//declared centre and right boundry, starting at first '#'
	int C=1,R=1;
	//Traversing modified string
	for(int i=1;i<ss.size();i++){
		int mirr = 2*C - i; // C - (i-C)
		//copying part of palindromic length from mirror if (p[mirror]<L) or R-i
		if(i<R)
			p[i] = min(R-i,p[mirr]);
		//expanding beyond 
		while(ss[i-(1+p[i])] == ss[i+(1+p[i])])
			p[i]++;
		//Updating Right boundry and centre if palindrome at(i) went outside of R. 
		if(R<i+p[i]){
			C = i;
			R = i+p[i];
		}
	}

	int maxx = -1,index = -1;
	for(int i=0;i<p.size();i++){
		if(maxx<p[i]){
			maxx = p[i];
			index = i;
		} 
	}

	string rets = ss.substr(index-maxx,maxx*2);
	string ret ="";
	//removing '#' '$'
	for(auto m:rets){
		if(m=='#'||m=='$'||m=='^')
			continue;
		ret += m;
	}
	return ret;     
}
