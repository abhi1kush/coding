class Solution {

public:

    int climbStairs(int n) {

        int a=0,b=1,c=0;

        if(n==1||n==2)

                return n;

        while(n--)

        {

            c=a+b;

            a=b;

            b=c;

            

        }

        return c;

    }

    

};
