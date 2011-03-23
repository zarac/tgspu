package phonebook;

// Basic node stored in AVL trees
// Note that this class is not accessible outside
// of package DataStructures

public class AVLNode_ex
{
    // Constructors
    AVLNode_ex( Comparable theElement )
    {
        this( theElement, null, null );
    }

    AVLNode_ex( Comparable theElement, AVLNode_ex lt, AVLNode_ex rt )
    {
        element  = theElement;
        left     = lt;
        right    = rt;
        height   = 0;
    }

    // Friendly data; accessible by other package routines
    Comparable element;      // The data in the node
    AVLNode_ex    left;         // Left child
    AVLNode_ex    right;        // Right child
    int        height;       // Height
}
