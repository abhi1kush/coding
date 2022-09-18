/**

 * Definition for a binary tree node.

 * struct TreeNode {

 *     int val;

 *     TreeNode *left;

 *     TreeNode *right;

 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}

 * };

 */

class Solution {

public:

    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {

        TreeNode* small,*large;

        if(p->val<q->val)

            {

                small=p;

                large=q;

            }

        else if(p->val>q->val)

            {

                small=q;

                large=p;

            }

        else

            {

                return p;

            }

        

        if(small->val==root->val||large->val==root->val)

            return root;

        if(root->val>small->val && root->val<large->val)

            return root;

        else if(root->val>small->val&&root->val>large->val)

            lowestCommonAncestor(root->left,small,large);

        else 

            lowestCommonAncestor(root->right,small,large);

    }

};
