package phonebook;

public class AVLTreeNode<Value>
{
    public String key;
    public Value value;
    public int height;
    public AVLTreeNode<Value> parent;
    public AVLTreeNode<Value> left;
    public AVLTreeNode<Value> right;

    public AVLTreeNode(String key, Value value)
    {
        this.key = key;
        this.value = value;
    }
}

