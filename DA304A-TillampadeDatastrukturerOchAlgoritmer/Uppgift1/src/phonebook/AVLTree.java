package phonebook;

public class AVLTree<Value> implements Dictionary<Value>
{
    int size;

    AVLTreeNode<Value> root;

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
                        System.out.println("putting " + key + " left of " + current.key);
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
                        System.out.println("putting " + key + " right of " + current.key);
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
        return null;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#find(String)
     */
    public Value find(String key)
    {
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

        return node;
    }

    /**
     * {@inheritDoc}
     * @see Dictionary#getNext()
     */
    public Value getNext()
    {
        return null;
    }
}

class AVLTreeNode<Value>
{
    String key;
    Value value;
    AVLTreeNode<Value> left;
    AVLTreeNode<Value> right;

    public AVLTreeNode(String key, Value value)
    {
        this.key = key;
        this.value = value;
    }
}
