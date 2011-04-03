package phonebook;

class SpeedTest
{
    AVLTreeNode<PhoneBookEntry> byNameRoot;
    AVLTreeNode<PhoneBookEntry> byNumberRoot;
    int size;
    long time;
    double avarageTime;
    String filename;

    public String toString()
    {
        return "size='" + size + "', time='" + time + "', avarageTime='" + avarageTime + "', filename='" + filename + "', byNameRoot='" + byNameRoot + "', byNumberRoot='" + byNumberRoot + "'";
    }
}
