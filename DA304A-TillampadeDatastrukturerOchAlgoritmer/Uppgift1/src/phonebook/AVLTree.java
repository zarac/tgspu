package phonebook;

import java.util.ArrayList;

public class AVLTree<Value> implements Dictionary<Value>
{
    int size;

    AVLTreeNode<Value> root;

    // TODO : ? move to node class or create iterator class
    public AVLTreeNode<Value> pointer;

    /**
     * {@inheritDoc}
     * @see Dictionary#add(String,Value)
     */
    public void add(String key, Value value)
    {
        if (root == null)
            root = new AVLTreeNode<Value>(key, value);
        else 
        {
            AVLTreeNode<Value> current = root;
            while (true)
            {
                if (key.compareTo(current.key) <= 0)
                {
                    if (current.left == null)
                    {
                        current.left = new AVLTreeNode<Value>(key, value);
                        current.left.parent = current;
                        fixHeight(current);
                        balance(current);
                        break;
                    }
                    else
                        current = current.left;
                }
                else
                {
                    if (current.right == null)
                    {
                        current.right = new AVLTreeNode<Value>(key, value);
                        current.right.parent = current;
                        fixHeight(current);
                        balance(current);
                        break;
                    }
                    else
                        current = current.right;
                }
            }
        }

        size++;
    }

    public void balance(AVLTreeNode<Value> node)
    {
        while (node != null)
        {
            // check if right rotate is needed
            if (getHeight(node.left) - getHeight(node.right) > 1)
            {
                // is left child is right heavy, rotate it left first (double right)
                if (getHeight(node.left.right) > getHeight(node.left.left))
                    rotateLeft(node.left);

                rotateRight(node);
            }
            // left rotate
            else if (getHeight(node.right) - getHeight(node.left) > 1)
            {
                // double left
                if (getHeight(node.right.left) > getHeight(node.right.right))
                    rotateRight(node.right);

                rotateLeft(node);
            }

            node = node.parent;
        }
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#remove(String)
     */
    public Value remove(String key)
    {
        AVLTreeNode<Value> node = removeNode(key);

        if (node == null)
            return null;

        return node.value;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#remove(String)
     */
    public AVLTreeNode<Value> removeNode(String key)
    {
        //System.out.println("removeNode():");

        AVLTreeNode<Value> node = (AVLTreeNode<Value>)findNode(key);
        if (node != null)
        {
            //   O
            //    \
            if (node.right == null)
            {
                // assuming balanced tree
                if (node == root)
                {
                    root = node.left;
                    if (node.left != null)
                        node.left.parent = root;
                }
                else
                {
                    if (node.parent.right == node)
                        node.parent.right = node.left;
                    else
                        node.parent.left = node.left;

                    if (node.left != null)
                        node.left.parent = node.parent;

                    fixHeight(node.parent);
                    balance(node.parent);
                }
            }

            //   O
            //  / \
            //     o
            else if (node.left == null)
            {
                // assuming balanced tree
                if (node == root)
                {
                    root = node.right;
                    root.parent = null;
                }
                else
                {
                    node.right.parent = node.parent;
                    if (node.parent.right == node)
                        node.parent.right = node.right;
                    else
                        node.parent.left = node.right;

                    fixHeight(node.parent);
                    balance(node.parent);
                }
            }

            //   O
            //  / \
            // o   o
            else
            {
                //   O
                //  / \
                // o   o
                //  \
                if (node.left.right == null)
                {
                    if (node == root)
                        root = node.left;
                    else if (node.parent.right == node)
                        node.parent.right = node.left;
                    else
                        node.parent.left = node.left;
                    node.left.right = node.right;
                    node.left.parent = node.parent;
                    node.right.parent = node.left;
                    fixHeight(node.left);
                    balance(node.left);
                }
                //   O
                //  / \
                // o   o
                //  \
                //   o
                else
                {
                    AVLTreeNode<Value> newRoot = getRightMost(node.left);
                    AVLTreeNode<Value> toBalance = newRoot.parent;
                    //newRoot.parent.right = newRoot.left;
                    if (newRoot.left != null)
                        newRoot.left.parent = newRoot.parent;
                    newRoot.parent.right = newRoot.left;

                    newRoot.left = node.left;
                    newRoot.left.parent = newRoot;
                    newRoot.right = node.right;
                    newRoot.right.parent = newRoot;
                    if (node == root)
                        root = newRoot;
                    else if (node.parent.right == node)
                        node.parent.right = newRoot;
                    else
                        node.parent.left = newRoot;
                    newRoot.parent = node.parent;

                    fixHeight(toBalance);
                    balance(toBalance);
                }
            }
        }

        size--;
        return node;
    }

    public AVLTreeNode<Value> getRightMost(AVLTreeNode<Value> node)
    {
        AVLTreeNode<Value> rightMost = node;
        while (rightMost.right != null)
            rightMost = rightMost.right;

        return rightMost;
    }

    public int getHeight(AVLTreeNode<Value> node)
    {
        return node == null ? 0 : node.height;
    }

    public void fixHeight(AVLTreeNode<Value> node)
    {
        while (node != null)
        {
            int max = Math.max(getHeight(node.left), getHeight(node.right));
            node.height = max + 1;
            node = node.parent;
        }
    }

    //public AVLTreeNode<Value>[] fuzzyFindNode(String key)
    //{
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        //ArrayList<AVLTreeNode<Value>> matches = new ArrayList<AVLTreeNode<Value>>();
        //while (node != null)
        //{
            //if (key.compareTo(node.key) < 0)
                //node = node.left;
            //else if (key.compareTo(node.key) > 0)
                //node = node.right;
            //else
                //return node;
        //}
        //return null;
    //}

    public AVLTreeNode<Value> findNode(String key)
    {
        AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        while (node != null)
        {
            if (key.compareTo(node.key) < 0)
                node = node.left;
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#find(String)
     */
    public Value find(String key)
    {
        AVLTreeNode<Value> node = (AVLTreeNode<Value>)findNode(key);
        if (node == null)
            return null;
        else
            return node.value;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#size()
     */
    public int size()
    {
        return size;
    }

    public AVLTreeNode<Value> getFirst()
    {
        //System.out.println("getFirst():");
        AVLTreeNode<Value> first = root;

        if (first == null)
        {
            //System.out.println("getFirst(): null");
            pointer = null;
            return null;
        }

        while (first.left != null)
            first = first.left;

        pointer = first;
        return first;
    }

    public AVLTreeNode<Value> getNext()
    {
        pointer = getNext(pointer);
        return pointer;
    }

    /**
     * Get next item, in-order. Can return null if empty tree or lonely node.
     *
     * {@inheritDoc}
     * @see Dictionary#getNext()
     */
    public AVLTreeNode<Value> getNext(AVLTreeNode<Value> currentNode)
    {
        return getNext(root, currentNode);
    }

    /**
     * Get next item, in-order. Can return null if empty tree or lonely node.
     *
     * {@inheritDoc}
     * @see Dictionary#getNext()
     */
    public AVLTreeNode<Value> getNext(AVLTreeNode<Value> root, AVLTreeNode<Value> currentNode)
    {
        AVLTreeNode<Value> next;

        // no node
        if (currentNode == null)
        {
            //System.out.println("getNext(): Node is 'null', can't get next.");
            return null;
        }
        else
        {
            //System.out.println("getNext(): " + currentNode.toString());
        }

        // on right, get left most or self
        //    O
        //     \
        //      o
        //     /
        //    x
        if (currentNode.right != null)
        {
            //System.out.println("getNext(): >");
            next = currentNode.right;
            while (next.left != null)
            {
                //System.out.println("getNext():<");
                next = next.left;
            }
            return next;
        }

        // first right parent
        //    x
        //   /
        //  o
        //   \
        //    O
        next = currentNode;
        //while (next.parent != null)
        while (next != root)
        {
            if (next == next.parent.left)
            {
                //System.out.println("getNext():parent was next yay..");
                return next.parent;
            }
            next = next.parent;
        }

        // no next
        //    x
        //System.out.println("getNext():null");
        return null;
    }

    public void rotateRight(AVLTreeNode<Value> node)
    {
        // in case we do manual rotate right in GUI
        if (node.left == null)
            return;

        AVLTreeNode<Value> newRoot = node.left;
        if (node == root)
        {
            root = newRoot;
            newRoot.parent = null;
        }
        else if (node.parent.left == node)
        {
            node.parent.left = newRoot;
            newRoot.parent = node.parent;
        }
        else
        {
            node.parent.right = node.left;
            newRoot.parent = node.parent;
        }

        node.left = newRoot.right;
        if (node.left != null)
            node.left.parent = node;
        newRoot.right = node;
        node.parent = newRoot;

        fixHeight(node);
    }

    public void rotateLeft(AVLTreeNode<Value> node)
    {
        // in case we do manual rotate left in GUI
        if (node.right == null)
            return;

        AVLTreeNode<Value> newRoot = node.right;

        if (node == root)
        {
            root = newRoot;
            newRoot.parent = null;
        }
        else if (node.parent.right == node)
        {
            node.parent.right = newRoot;
            newRoot.parent = node.parent;
        }
        else
        {
            node.parent.left = newRoot;
            newRoot.parent = node.parent;
        }

        node.right = newRoot.left;
        if (node.right != null)
            node.right.parent = node;
        newRoot.left = node;
        node.parent = newRoot;

        fixHeight(node);
    }
}
