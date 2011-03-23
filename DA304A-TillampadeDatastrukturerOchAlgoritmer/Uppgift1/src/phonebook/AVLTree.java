package phonebook;

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
        {
            root = new AVLTreeNode<Value>(key, value);
            System.out.println("new root, " + key);
        }
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
                        System.out.println("putting " + key + " left of " + current.key);
                        incHeight(current);
                        // IAMHERE
                        //if (nodeHeight(current.left.height
                        break;
                    }
                    else
                        current = current.left;
                }
                else
                {
                    if (current.right == null)
                    {
                        //current.height++;
                        current.right = new AVLTreeNode<Value>(key, value);
                        current.right.parent = current;
                        System.out.println("putting " + key + " right of " + current.key);
                        incHeight(current);
                        break;
                    }
                    else
                        current = current.right;
                }
            }
        }

        size++;
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
        AVLTreeNode<Value> node = (AVLTreeNode<Value>)findNode(key);
        if (node == null)
            return null;

        //  O
        //   \
        else if (node.right == null)
        {
            //   |
            //   O    x
            //  / \
            // x
            if (node.parent == null)
            {
                root = node.left;
                root.parent = null;
            }

            //     x   x
            //    /   /
            //   O   ?
            //  / \
            // ?
            else if (node.parent.left == node)
            {
                node.parent.left = node.left;
                decHeight(node.parent);
            }

            // x     x   O
            //  \     \ '
            //   O     ?
            //  / \
            // ?
            else
            {
                node.parent.right = node.left;
                decHeight(node.parent);
            }
        }

        //   O
        //  / \
        //     o
        else if (node.left == null)
        {
            //   |    |
            //   O    x
            //  / \
            //     x
            if (node.parent == null)
            {
                root = node.right;
                node.right.parent = null;
            }

            //     o    o
            //    /    /
            //   O    x
            //  / \
            //     x
            else if (node == node.parent.left)
            {
                node.parent.left = node.right;
                decHeight(node.parent);
            }

            // o    o
            //  \    \
            //   O    x
            //  / \
            //     x
            else
            {
                node.parent.right = node.right;
                decHeight(node.parent);
            }
        }

        //   |
        //   O
        //  / \
        else if (node.parent == null)
            root = null;

        //   o
        //   |
        //   O
        //  / \
        else
        {
            // o
            //  \
            //   O
            //  / \
            if (node.parent.right == node)
            {
                node.parent.right = null;
                decHeight(node.parent);
            }

            //
            //    o
            //   /
            //  O
            // / \
            else
            {
                node.parent.left = null;
                decHeight(node.parent);
            }
        }

        size--;
        return node;
    }

    // increase height of parents recursively
    void incHeight(AVLTreeNode<Value> node)
    {
        node.height++;
        while (node != null)
        {
            System.out.println("cur" + node.height + " other" + node.height);
            if (node.parent != null
                    && ((node.parent.left == node
                            && node.parent.right != null
                            && node.height > node.parent.right.height)
                        || (node.parent.right == node
                            && node.parent.left != null
                            && node.height > node.parent.left.height)))
            {
                System.out.println("hejja");
                node.height++;
                node = node.parent;
            }
            else
                node = null;
        }
    

    void decHeight(AVLTreeNode<Value> node)
    {
        while (node != null)
        {
            node.height--;
            node = node.parent;
        }
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#findNode(String)
     */
    public AVLTreeNode<Value> findNode(String key)
    {
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        while (node != null)
        {
            // if equal return self
            if (key.compareTo(node.key) < 0)
                node = node.left;
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            else if (key.compareTo(node.key) == 0)
            {
                return node;
            }
                //return (AVLTreeNode<Value>)node;
            // if less go left
            // if more go righ
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#find(String)
     */
    public Value find(String key)
    {
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)find(key);
        AVLTreeNode<Value> node = (AVLTreeNode<Value>)findNode(key);
        //AVLTreeNode<Value> node = (AVLTreeNode<Value>)root;
        while (node != null)
        {
            // if equal return self
            if (key.compareTo(node.key) < 0)
                node = node.left;
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            else if (key.compareTo(node.key) == 0)
            {
                return node.value;
            }
                //return (AVLTreeNode<Value>)node;
            // if less go left
            // if more go righ
        }
        return null;
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
        AVLTreeNode<Value> node = root;

        while (node.left != null)
            node = node.left;

        pointer = node;
        return node;
    }

    /**
     * Get next item, in-order. Can return null if empty tree or lonely node.
     *
     * {@inheritDoc}
     * @see Dictionary#getNext()
     */
    public AVLTreeNode<Value> getNext()
    {
        AVLTreeNode<Value> node;

        // empty tree
        if (pointer == null)
        {
            System.out.println("null");
            return null;
        }

        // on right, get left most or self
        //    O
        //     \
        //      o
        //     /
        //    x
        if (pointer.right != null)
        {
            System.out.println(" >");
            node = pointer.right;
            while (node.left != null)
            {
                System.out.println("<");
                node = node.left;
            }
            pointer = node;
            return node;
        }

        // first right parent
        //    x
        //   /
        //  o
        //   \
        //    O
        node = pointer;
        while (node.parent != null)
        {
            if (node == node.parent.left)
            {
                System.out.println("parent was next yay..");
                pointer = node.parent;
                return node.parent;
            }
            node = node.parent;
        }

        // no next
        //    x
        System.out.println("null");
        pointer = null;
        return null;
    }

    public void rotateRight(AVLTreeNode<Value> node)
    {
        AVLTreeNode<Value> newRoot = node.left;
        newRoot.parent = node.parent;
        node.parent = newRoot;
        node.left = newRoot.right;
        newRoot.right = node;
    }

    public void rotateLeft(AVLTreeNode<Value> node)
    {
        // TODO : Update heights..
        // TODO : check if node.parent == null ... update root
        // same on rotateRight()
        AVLTreeNode<Value> newRoot = node.right;
        newRoot.parent = node.parent;
        node.parent = newRoot;
        node.right = newRoot.left;
        newRoot.left = node;
    }
}
