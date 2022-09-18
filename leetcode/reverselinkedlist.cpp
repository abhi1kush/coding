class Solution {

public:

    ListNode* reverseList(ListNode* head) {

        ListNode* next,*p=NULL,*q=NULL,*r=head;

        while(r!=NULL)

        {

            p=q;

            q=r;

            r=r->next;

            q->next=p;

            

        }

        return q;

    }

};
